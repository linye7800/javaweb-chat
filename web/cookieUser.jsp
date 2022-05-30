<%--
  Created by IntelliJ IDEA.
  User: linye
  Date: 2022/5/18
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Cookie User</title>
  </head>
  <body>
    <%
      // 从浏览器获取cookie
      Cookie[] cookies = request.getCookies();
      String username = null;
      if (null != cookies) {
        for (Cookie cookie : cookies) {
          // 遍历本地浏览器中所有cookie
          if (cookie.getName().equals("cookie_name")) {
            username = cookie.getValue();
            out.println("<p>cookie - name = " + cookie.getName() +"</p>");
            out.println("<p>cookie - value = " + cookie.getValue() +"</p>");
          }
        }
      }
      // 判断cookie是否失效
      if (null != username) {
        out.println("<p>cookie - username = " + username +"</p>");
      } else {
        response.sendRedirect("login.jsp");
      }

    %>
  </body>
</html>
