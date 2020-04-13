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
    <title>简历列表</title>
</head>
<body>
当前用户：${USER_SESSION.username}<a href="${pageContext.request.contextPath}/logout">退出</a><br/>
<h4>简历列表</h4>
<a onclick="insertResume()">新增</a>
<div class="row margin-top-20">
    <table class="table" border="1">
        <thead>
        <tr>
            <th class="seq">id</th>
            <th>名称</th>
            <th>地址</th>
            <th>电话</th>
            <th>删除</th>
            <th>修改</th>
        </tr>
        </thead>
        <tbody id="resumeTable">
        </tbody>
    </table>
</div>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script>
    window.onload =function()
    {
        // 发送ajax请求
        $.ajax({
            url: '/resume/queryAll',
            type: 'POST',
            data: '',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            success: function (data) {
                var str1 = "";
                //清空table中的html
                $("#resumeTable").html("");
                for(var i = 0;i<data.length;i++){
                    str1 = "<tr>" +
                        "<td>"+data[i].id + "</td>" +
                        "<td>"+data[i].name + "</td>" +
                        "<td>"+data[i].address + "</td>" +
                        "<td>"+data[i].phone + "</td>" +
                        "<td><a onclick=deleteResume(" + data[i].id + ")>删除</a></td>" +
                        "<td><a onclick=updateResume(" + data[i].id + ")>修改</a></td>" +
                        "</tr>";
                    $("#resumeTable").append(str1);
                };
            }
        });
    }

    /**
     * 根据id删除数据
     * @param id
     */
    function deleteResume(id){
        // 发送ajax请求
        $.ajax({
            url: '/resume/deleteResume?id=' + id,
            type: 'GET',
            data: id,
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            success: function (data) {
                alert("删除成功！");
                // window.location.href="/main"
                window.location.href="/main"
            }
        });
    }

    function insertResume(){
        window.location.href="/insert"
    }

    function updateResume(id){
        window.location.href="/resume/queryInfo?id=" + id;
    }

</script>
</body>
</html>