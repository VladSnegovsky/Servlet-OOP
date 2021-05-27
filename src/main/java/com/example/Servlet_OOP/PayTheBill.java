package com.example.Servlet_OOP;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "paythebill", value = "/paythe-bill")
public class PayTheBill extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        int id = 0;
        int price = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id"))
                id = Integer.parseInt(cookie.getValue());
            else if (cookie.getName().equals("price"))
                price = Integer.parseInt(cookie.getValue());
            else if (cookie.getName().equals("login"))
                response.addCookie(cookie);
            else if (cookie.getName().equals("password"))
                response.addCookie(cookie);
        }

        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

        try (Connection connection = connectionPool.getConnection();){
            String sql = "update paidorders set paid=1 where ordernum=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
            statement.close();

            String sql1 = "insert into bookkeeping (orderid, price) values(?, ?);";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, String.valueOf(id));
            statement1.setInt(2, price);
            statement1.executeUpdate();
            statement1.close();
            connectionPool.releaseConnection(connection);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("paymentdone.jsp");
    }

    public void destroy() {
    }
}