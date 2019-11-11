package com.chatting;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "sign_in")
public class sign_up extends HttpServlet {

    //数据库URL
    private  static String JDBC_DRIVER;
    private static String DB_URL;

    //数据库的用户名和密码
    private static String USER;
    private static String PASS;

    static {
        try{
            ResourceBundle rb = ResourceBundle.getBundle("/resources/db", Locale.getDefault());
            JDBC_DRIVER = rb.getString("db.JDBC_DRIVER");
            DB_URL = rb.getString("db.DB_URL");
            USER = rb.getString("db.USER");
            PASS = rb.getString("db.PASS");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;

        boolean isLogup = false;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");

        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT password FROM user WHERE username = '" + userName + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                out.println("<p>该用户已存在</p>");
                out.println("<a href='/jsp/sign_up.jsp'>点击返回重试</a>");
            }else{
                sql = "INSERT INTO user(username, password) values ('"+userName + "','" + passWord +"')";
                stmt.executeUpdate(sql);
                out.println("注册成功");
                isLogup = true;
            }

            //完成后关闭
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try{
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (isLogup){
            ServletContext context = request.getSession().getServletContext();
            Integer count = (Integer) context.getAttribute("numberOnline");
            if(count == null){
                count = 1;
            } else {
                count++;
            }
            context.setAttribute("numberOnline", count);
            request.getSession().setAttribute("username", userName);
            request.getRequestDispatcher("/jsp/chat.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
