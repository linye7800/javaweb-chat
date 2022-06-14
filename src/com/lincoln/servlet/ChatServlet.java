package com.lincoln.servlet;

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

    public ChatServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // 解决中文乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取用户登录名
        String username = request.getParameter("username");
        // 获取用户password
        String password = request.getParameter("password");
        if ("123".equals(password)) {
            response.getWriter().println("success");
        } else {
            response.getWriter().println("username or password error!!!");
        }
    }
}
