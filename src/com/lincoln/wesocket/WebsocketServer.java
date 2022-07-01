package com.lincoln.wesocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

        sendMsg("有一个客户端连上了， 叫： " + username);
    }

    @OnClose
    public void close() {
        System.out.println("客户端连接断开！~~");
    }

    @OnMessage
    public void receiveMessage(String content) {
        System.out.println("收到客户端发来的消息" + content);
    }

    public void sendMsg(String msg) {
        for (Session s : clients.values()) {
            s.getAsyncRemote().sendText(msg);
        }
    }

}
