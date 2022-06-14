package com.lincoln.servlet;

import java.io.UnsupportedEncodingException;
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
            throws UnsupportedEncodingException {
         // 解决中文乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        this.request = request;
        this.response = response;
            String op = request.getParameter("op");
            switch (op) {
                case "login" :
                    try {
                        // login
                        login();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "send" :
                    // 接收消息
                    rec();
                    break;
                default:
                    break;
            }

    }

    /**
     * 用户 login
     * @throws Exception
     */
    public void login() throws Exception{
        // 获取用户登录名
        String username = request.getParameter("username");
        // 获取用户password
        String password = request.getParameter("password");
        if ("123".equals(password)) {
            response.getWriter().print("success");
        } else {
            response.getWriter().print("username or password error!!!");
        }
    }

    /**
     * 用户 收消息
     */
    public void rec() {
        // 获取用户发送的消息
        String msg = request.getParameter("msg");
        System.out.println("msg: " + msg);
    }
}
