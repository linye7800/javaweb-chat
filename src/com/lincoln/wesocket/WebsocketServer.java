package com.lincoln.wesocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lincoln.domain.InfoResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * description:
 *
 * @author linye
 * @date 2022年07月01日 10:41 PM
 */

@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfig.class)
public class WebsocketServer {

    // 客户端集合 websocket 的session
    private static Map<String, Session> clients = new ConcurrentHashMap<String, Session>();

    // http 浏览器的session
    private HttpSession httpSession;

    private String username;


    @OnOpen
    public void open(Session session, EndpointConfig endpointConfig) {
        // 当一个客户端连接时，进入此方法

        // 获取http里面的httpsession会话
        httpSession = (HttpSession)endpointConfig.getUserProperties().get(HttpSession.class.getName());

        // 获取当前登录的用户名
        username = String.valueOf(httpSession.getAttribute("username"));

        // 将websocket连接的客户端的session放入集合client
        clients.put(username, session);

//        sendMsg("有一个客户端连上了， 叫： " + username);
        sendMsgByType("messageList");

        sendMsgByType("userList");
    }

    @OnClose
    public void close() {
        System.out.println("客户端连接断开！~~");
    }

    /**
     * 向所有的客户端发消息
     * @param content
     */
    @OnMessage
    public void receiveMessage(String content) {
        System.out.println("收到客户端发来的消息: " + content);

        makeMessage(content);

        sendMsgByType("messageList");
    }

    /**
     * 组装消息
     * @param content
     */
    public void makeMessage(String content) {
        if (null != content && !("").equals(content.trim())) {
            // 获取servlet全局对象
            ServletContext sc = httpSession.getServletContext();
            List<String> strList =  (List<String>)sc.getAttribute("MessageList");
            if (null == strList) {
                // 第一条消息
                strList = new ArrayList<>();
            }
            // 发送时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            // 组装消息
            String chatContent = username + " 于 " + sdf.format(date) + "说： " + content;
            strList.add(chatContent);
            //更新全局对象
            sc.setAttribute("MessageList", strList);
        }
    }

    /**
     * 获取消息
     * @return
     */
    public String getChatList() {
        StringBuilder builder = new StringBuilder();

        // 获取servlet全局对象
        ServletContext sc = httpSession.getServletContext();
        List<String> strList =  (List<String>)sc.getAttribute("MessageList");
        if (null != strList) {
            for (String tempMessage : strList) {
                builder.append(tempMessage).append("<br/>");
            }
        }
        return String.valueOf(builder);
    }

    /**
     * 获取用户列表
     * @return
     */
    public String getUserList() {
        StringBuilder builder = new StringBuilder();

        // 获取servlet全局对象
        ServletContext sc = httpSession.getServletContext();
        List<String> userList =  (List<String>)sc.getAttribute("userList");
        if (null != userList) {
            for (String tempUserList : userList) {
                builder.append(tempUserList).append("<br/>");
            }
        }
        return String.valueOf(builder);
    }

    /**
     * 向所有的客户端发送消息 (根据类型)
     * @param type
     */
    public void sendMsgByType(String type) {
        InfoResponse info = new InfoResponse();
        String infoResponse;
        switch (type){
            // 用户聊天列表
//            case "messageList":
//                infoResponse = "{\"op\":\"messageList\",\"value\":\""+getChatList()+"\"}";
//                break;
            // 在线人员列表
            case "userList":
                infoResponse = "{\"op\":\"userList\",\"value\":\""+getUserList()+"\"}";
                info.setOp("userList");
                info.setValue(getUserList());
                break;
            default:
                infoResponse = null;
                break;
        }
        if (null == infoResponse) {
            return;
        }
        JSONObject json = null;
        for (Session s : clients.values()) {
            // 转换成json格式
//            json = JSONObject.parseObject(infoResponse);
            String jsonStr = JSON.toJSONString(info);
            System.out.println(jsonStr);
            s.getAsyncRemote().sendText(jsonStr);

        }
    }
}


