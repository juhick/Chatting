package com.chatting;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "sign_up")
public class sign_in extends HttpServlet {

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

    public sign_in(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        //设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");

        boolean isLogin = false;

        try{
            //注册JDBC驱动器
            Class.forName(JDBC_DRIVER);

            //打开一个连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //执行SQL查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT password FROM user WHERE username = '" + userName + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                String pass = rs.getString("password");

                if(pass.equals(passWord)){
                    out.println("登录成功!");
                    isLogin = true;
                }else{
                    out.println("<p>密码错误！</p>");
                    out.println("<a href='/jsp/sign_in.jsp'>点击返回重试</a>");
                }

            }else{
                out.println("<p>该用户不存在！</p>");
                out.println("<a href='/jsp/sign_in.jsp'>点击返回重试</a>");
            }

            //完成后关闭
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR：未找到JDBC驱动！");
        } catch (SQLException e) {
            System.out.println("ERROR：数据库连接失败！");
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


        if(isLogin){
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
