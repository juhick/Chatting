<%--
  Created by IntelliJ IDEA.
  User: qhq13
  Date: 2019/10/21
  Time: 14:27
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
    <link href="../assets/pages/css/index.css" rel="stylesheet" type="text/css">
    <script src="../assets/pages/js/index.js"></script>
  </head>
  <body>
    <div class="main">
      <div class="choice">
        <div class="select">
          <a href="/jsp/sign_up.jsp"><button type="button">注册</button></a>
          <a href="/jsp/sign_in.jsp"><button type="button">登录</button></a>
        </div>
      </div>
    </div>
  </body>
</html>
