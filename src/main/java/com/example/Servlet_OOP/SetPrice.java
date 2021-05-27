package com.example.Servlet_OOP;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "setprice", value = "/set-price")
public class SetPrice extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String price = request.getParameter("price");
        int id = 0;

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id"))
                id = Integer.parseInt(cookie.getValue());
            response.addCookie(cookie);
        }

        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

        try (Connection connection = connectionPool.getConnection();){
            String sql = "update orders set cost=? where id=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(price));
            statement.setString(2, String.valueOf(id));
            statement.executeUpdate();
            statement.close();

            String sql1 = "update orders set calculated=1 where id=?;";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, String.valueOf(id));
            statement1.executeUpdate();
            statement1.close();
            connectionPool.releaseConnection(connection);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("getnew-orders");
    }

    public void destroy() {
    }
}