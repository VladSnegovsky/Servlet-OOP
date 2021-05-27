package com.example.Servlet_OOP;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "getneworders", value = "/getnew-orders")
public class GetNewOrders extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();

        boolean newOrder = false;
        int id = 0;
        String buckwheat = "";
        String rice = "";
        String compote = "";
        String cutlet = "";

        for (Cookie cookie : cookies) {
            response.addCookie(cookie);
        }

        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

        try (Connection connection = connectionPool.getConnection();){
            String sql = "select * from orders where calculated=0;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                newOrder = true;
                id = resultSet.getInt(1);
                buckwheat = String.valueOf(resultSet.getInt(3));
                rice = String.valueOf(resultSet.getInt(4));
                compote = String.valueOf(resultSet.getInt(5));
                cutlet = String.valueOf(resultSet.getInt(6));
                break;
            }
            statement.close();
            connectionPool.releaseConnection(connection);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }

        if (newOrder){
            Cookie cookieId = new Cookie("id", String.valueOf(id));
            Cookie cookieBuckwheat = new Cookie("buckwheat", buckwheat);
            Cookie cookieRice = new Cookie("rice", rice);
            Cookie cookieCompote = new Cookie("compote", compote);
            Cookie cookieCyberCutlet = new Cookie("cutlet", cutlet);

            response.addCookie(cookieId);
            response.addCookie(cookieBuckwheat);
            response.addCookie(cookieRice);
            response.addCookie(cookieCompote);
            response.addCookie(cookieCyberCutlet);

            response.sendRedirect("orders.jsp");
        } else {
            response.sendRedirect("noorders.jsp");
        }

    }

    public void destroy() {
    }
}