package com.lincoln.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author linye
 * Description: Login Servlet
 */
@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取用户登录名
        String username = request.getParameter("username");

        // 获取7天内是否免登陆
        String saveDay7 = request.getParameter("chkUserRemember");
        Integer cookieDay = 7;

        // 放入session
        // 1. 获取session
        HttpSession session = request.getSession();
        // 2. 放入session
        session.setAttribute("session_username", username);
        // 3. 设置session有限期 默认15分钟
        session.setMaxInactiveInterval(5);
        // 4. 创建cookie
        if (null != saveDay7) {
            Cookie cookie = new Cookie("cookie_name", username);
            // 5. 设定cookie有效期 (7天免登陆)
            cookie.setMaxAge(cookieDay * 24 * 60 * 60);
            response.addCookie(cookie);
        }

        // 6. 写入cookie到浏览器

        // 跳转到验证页面 - session
//        response.sendRedirect("sessionUser.jsp");
        // 跳转到验证页面 - cookie
        response.sendRedirect("cookieUser.jsp");
    }
}
