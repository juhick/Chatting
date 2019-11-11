<%--
  Created by IntelliJ IDEA.
  User: qhq13
  Date: 2019/11/10
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Servlet聊天室</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="../assets/pages/css/chat.css" type="text/css">
    <script src="../assets/pages/js/chat.js"></script>
    <script>
        window.setInterval("getNumber();", 1000);
        window.setInterval("getContent();", 1000);
        function getNumber() {
            var xmlhttp;
            xmlhttp = new XMLHttpRequest();

            xmlhttp.onreadystatechange = function(){
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
                    document.getElementById("count").innerText = "当前访问人数为：" + xmlhttp.responseText.trim() + "人";
                }
            };

            xmlhttp.open("POST", "getCount", true);
            xmlhttp.send();
        }

        function getContent() {
            var xmlhttp;
            xmlhttp = new XMLHttpRequest();

            xmlhttp.onreadystatechange = function(){
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
                    if(null!=xmlhttp.responseText&&""!=xmlhttp.responseText.trim()&&"null"!=xmlhttp.responseText.trim()){
                        document.getElementById("content").innerHTML = xmlhttp.responseText == null?"":xmlhttp.responseText;
                    }
                }
            };

            xmlhttp.open("POST", "getContent", true);
            xmlhttp.send();
        }
    </script>
</head>
<body onload="getNumber()">
<div class="main">
    <div class="chat">
        <p>欢迎你！<%=username%></p>
        <p id="count">当前访问人数为：<%=application.getAttribute("numberOnline")%>人</p>
        <form action="Chat" onsubmit="return validData()" method="post" name="ms">
            <textarea readonly="readonly" rows="15" cols=50 name="show_textarea" id="content"><%=application.getAttribute("input_textarea")==null?"":application.getAttribute("input_textarea")%></textarea>
            <textarea cols="50" rows="3" name="input_textarea"></textarea>
            <p><input type="submit" value="发送" name="button_one"></p>
            <p><input type="button" value="退出聊天室" name="button_two" onclick="window.location.href='jsp/log_out.jsp'"></p>
        </form>
    </div>
</div>
</body>
</html>
