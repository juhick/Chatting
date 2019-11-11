<%--
  Created by IntelliJ IDEA.
  User: qhq13
  Date: 2019/11/10
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + '/';
%>
<html>
<head>
    <title>Servlet聊天室</title>
    <base href="<%=basePath%>">
</head>
<body>
    <%
        HttpSession session1 = request.getSession();
        session1.invalidate();
    %>
    <p>退出成功！</p>
</body>
</html>
