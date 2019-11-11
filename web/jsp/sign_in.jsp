<%--
  Created by IntelliJ IDEA.
  User: qhq13
  Date: 2019/10/31
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Servlet聊天室</title>
    <base href="<%=basePath%>">
    <link href="../assets/pages/css/sign_in.css" rel="stylesheet" type="text/css">
    <script src="../assets/pages/js/sign_in.js"></script>
</head>
<body>
    <div class="main">
        <div class="signup">
            <div class="input">
                <form action="sign_in" method="post" name="sign_up" onsubmit="return validateForm()">
                    <p>用户名: <input type="text" name="username" /></p>
                    <p>密码: <input type="password" name="password" /></p>
                    <input type="submit" value="完成" />
                </form>
            </div>
        </div>
    </div>
</body>
</html>
