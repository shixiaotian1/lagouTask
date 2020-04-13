<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018-04-07
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增简历</title>
</head>
<body>
<h4>更新简历</h4>
<form action="/resume/insertResume" method="post">
    <input type="hidden" name="id" value="${resume.id}"><br><br>
    名&nbsp;&nbsp;称：<input type="text" name="name" value="${resume.name}"><br><br>
    地&nbsp;&nbsp;址：<input type="text" name="address" value="${resume.address}"><br><br>
    电&nbsp;&nbsp;话：<input type="text" name="phone" value="${resume.phone}"><br>
    <br/>
    <input type="submit" value="更新">
</form>

<script type="text/javascript" src="/js/jquery.min.js"></script>
<script>

</script>
</body>
</html>