package com.example.Servlet_OOP;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie cookieLogin = new Cookie("login", request.getParameter("login"));
        Cookie cookiePassword = new Cookie("password", request.getParameter("password"));

        String password = "";
        String user = "";

        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();){
            String sql = "select * from users where name='" + request.getParameter("login") + "';";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                password = resultSet.getString(3);
                user = resultSet.getString(4);
            }
            statement.close();
            connectionPool.releaseConnection(connection);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }

        cookieLogin.setMaxAge(30*60);
        cookiePassword.setMaxAge(30*60);

        if (password.equals(String.valueOf(request.getParameter("password")))){
            response.addCookie(cookieLogin);
            response.addCookie(cookiePassword);
            if (user.equals("admin")){ response.sendRedirect("getnew-orders"); }
            else { response.sendRedirect("menu.jsp"); }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    public void destroy() {
    }
}