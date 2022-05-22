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
    <title>Insert title here</title>
  </head>
  <body>
    <%
      String username = String.valueOf(session.getAttribute("session_username"));
      System.out.println("<h1> Get username in session: " + username + "<h1/>");
      System.out.println("<h1> Username: " + username + "<h1/>");
    %>
  </body>
</html>
