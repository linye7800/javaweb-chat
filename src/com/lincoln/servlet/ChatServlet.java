package com.lincoln.servlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author linye
 * Description: Login Servlet
 */
@WebServlet(name = "ChatServlet", value = "/ChatServlet")
public class ChatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;

    private HttpServletResponse response;

    public ChatServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse HttpServletResponse)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
         // 解决中文乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        this.request = request;
        this.response = response;
            String op = request.getParameter("op");
            // 返回给浏览器的内容
            String result = "";
            switch (op) {
                case "login" :
                    try {
                        // login
                        result = login();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "send" :
                    // 接收消息
                    try {
                        result = rec();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "get" :
                    // 接收消息
                    try {
                        // 获取聊天列表的内容
                        result = getMessageList();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "userList" :
                    // 接收消息
                    try {
                        // 获取聊天列表的内容
                        result = getUserListList();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    break;
            }

            response.getWriter().print(result);

    }

    /**
     * 用户 login
     * @throws Exception
     */
    public String login() throws Exception{
        // 获取用户登录名
        String username = request.getParameter("username");
        // 获取用户password
        String password = request.getParameter("password");
        if ("123".equals(password)) {
            // 获取session中的用户信息
            HttpSession session = request.getSession();
            // 把用户信息放入session
            session.setAttribute("username", username);
            // 添加用户名到在线列表
            addUserList(username);
            return "success";
        } else {
            return "username or password error!!!";
        }
    }

    /**
     * 服务 收消息
     */
    public String rec() throws Exception{
        // 获取用户发送的消息
        String msg = request.getParameter("msg");
//        System.out.println("msg: " + msg);
        // 把消息放在全局对象里面
        ServletContext sc = request.getServletContext();
        List<String> chatList = (List<String>)sc.getAttribute("msgList");
        if (chatList == null) {
            // 第一条消息
            chatList = new ArrayList<String>();
        }
        // 获取session中的用户信息
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute("username"));
        // 发送时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        // 组装消息
        String content = username + " 于 " + sdf.format(date) + "说： " + msg;
        // 将消息内容加入到list
        chatList.add(content);
        // 更新到全局对象
        sc.setAttribute("msgList", chatList);
        return  getMessageList();
    }

    /**
     * 获取聊天列表的内容
     * @return
     */
    public String getMessageList() {
        StringBuilder sb = new StringBuilder();
        ServletContext sc = request.getServletContext();
        List<String> chatList = (List<String>)sc.getAttribute("msgList");
        if (null != chatList) {
            for (String temp : chatList) {
                sb.append(temp);
                sb.append("<br/>");
            }
        }
        return String.valueOf(sb);
    }

    /**
     * add user to userList
     * @param uname
     */
    public void addUserList(String uname) {
        ServletContext servletContext  = request.getServletContext();
        List<String> userList = (List<String>)servletContext.getAttribute("userList");
        if (userList == null) {
            // 第一个用户
            userList = new ArrayList<>();
        }
        // 添加用户名到在线列表
        userList.add(uname);
        servletContext.setAttribute("userList", userList);
    }

    /**
     * get user list
     */
    public String getUserListList() {
        StringBuilder sb = new StringBuilder();
        ServletContext servletContext  = request.getServletContext();
        List<String> userList = (List<String>)servletContext.getAttribute("userList");
        if (null != userList) {
            for (String username : userList) {
                sb.append(username);
                sb.append("<br/>");
            }
        }
        return String.valueOf(sb);
    }

}
