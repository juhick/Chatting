package com.chatting;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "Chat")
public class Chat extends HttpServlet {
    private String chat_record = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        ServletContext context = request.getSession().getServletContext();
        String input_textarea = request.getParameter("input_textarea");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        String time = dateFormat.format( now );
        String username = (String)request.getSession().getAttribute("username");
        chat_record += username + "(" + time + "):\n" + input_textarea + "\n"; //聊天记录存储
        context.setAttribute("input_textarea", chat_record);
        request.getRequestDispatcher("jsp/chat.jsp").forward(request, response);//跳转到当前聊天输入界面，界面布局不变
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
