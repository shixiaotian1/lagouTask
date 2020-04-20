<%--
  Created by IntelliJ IDEA.
  User: LoveLy
  Date: 2020/4/12
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<h4>用户登录</h4>
<form action="/login" method="post">
    <br/>
    用&nbsp;户&nbsp;名：<input type="text" name="username"><br/><br/>
    密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"><br/><br/>
    <input type="submit" value="登录">
    ${msg}
</form>
</body>
</html>