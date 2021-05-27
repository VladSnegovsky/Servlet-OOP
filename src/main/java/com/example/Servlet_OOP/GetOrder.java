package com.example.Servlet_OOP;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "getorder", value = "/get-order")
public class GetOrder extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        String visitor = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login"))
                visitor = cookie.getValue();
            response.addCookie(cookie);
        }

        int buckwheat = Integer.parseInt(request.getParameter("buckwheat"));
        int rice = Integer.parseInt(request.getParameter("rice"));
        int compote = Integer.parseInt(request.getParameter("compote"));
        int cutlet = Integer.parseInt(request.getParameter("cutlet"));

        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

        try (Connection connection = connectionPool.getConnection();){
            String sql = "insert into orders (visitor, buckwheat, rice, compote, cutlet, cost, calculated) values(?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, visitor);
            statement.setInt(2, buckwheat);
            statement.setInt(3, rice);
            statement.setInt(4, compote);
            statement.setInt(5, cutlet);
            statement.setInt(6, 0);
            statement.setBoolean(7, false);
            statement.executeUpdate();
            statement.close();

            String sql0 = "select * from orders where visitor=? and buckwheat=? and rice=? and compote=? and cutlet=?;";
            PreparedStatement statement0 = connection.prepareStatement(sql0);
            statement0.setString(1, visitor);
            statement0.setInt(2, buckwheat);
            statement0.setInt(3, rice);
            statement0.setInt(4, compote);
            statement0.setInt(5, cutlet);
            ResultSet resultSet = statement0.executeQuery();
            int id = 0;
            while (resultSet.next()){
                id = resultSet.getInt(1);
            }
            statement0.close();

            Cookie orderId = new Cookie("id", String.valueOf(id));
            response.addCookie(orderId);

            String sql1 = "insert into paidorders (ordernum, buckwheat, rice, compote, cutlet, paid) values(?, ?, ?, ?, ?, ?);";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, id);
            statement1.setInt(2, buckwheat);
            statement1.setInt(3, rice);
            statement1.setInt(4, compote);
            statement1.setInt(5, cutlet);
            statement1.setBoolean(6, false);
            statement1.executeUpdate();
            statement1.close();
            connectionPool.releaseConnection(connection);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }

        Cookie cookieBuckwheat = new Cookie("buckwheat", request.getParameter("buckwheat"));
        Cookie cookieRice = new Cookie("rice", request.getParameter("rice"));
        Cookie cookieCompote = new Cookie("compote", request.getParameter("compote"));
        Cookie cookieCyberCutlet = new Cookie("cutlet", request.getParameter("cutlet"));

        cookieBuckwheat.setMaxAge(30*60);
        cookieRice.setMaxAge(30*60);
        cookieCompote.setMaxAge(30*60);
        cookieCyberCutlet.setMaxAge(30*60);

        response.addCookie(cookieBuckwheat);
        response.addCookie(cookieRice);
        response.addCookie(cookieCompote);
        response.addCookie(cookieCyberCutlet);
        response.sendRedirect("waitingprice.jsp");
    }

    public void destroy() {
    }
}