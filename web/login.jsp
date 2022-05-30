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
  <form action="LoginServlet" method="post">
    username: <input type="text" name="username"/><br/>
    <input type="submit" value="Login">
  </form>

  <a href="sessionUser.jsp"> 验证session  </a>
  <a href="cookieUser.jsp"> 验证cookie </a>
  </body>
</html>
