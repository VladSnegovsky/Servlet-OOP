package com.example.Servlet_OOP;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "checkstatus", value = "/check-status")
public class CheckStatus extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        int id = 0;
        boolean calculated = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id"))
                id = Integer.parseInt(cookie.getValue());
            response.addCookie(cookie);
        }

        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

        try (Connection connection = connectionPool.getConnection();){
            String sql = "select * from orders where id=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getInt(2) == 1)
                    calculated = true;
            }
            statement.close();
            connectionPool.releaseConnection(connection);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }

        if (calculated){
            response.sendRedirect("payment.jsp");
        } else {
            response.sendRedirect("waitingprice.jsp");
        }
    }

    public void destroy() {
    }
}
