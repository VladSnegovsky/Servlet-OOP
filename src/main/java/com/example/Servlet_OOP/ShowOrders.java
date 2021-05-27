//package com.example.Servlet_OOP;
//
//import java.io.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//
//@WebServlet(name = "showorder", value = "/show-order")
//class ShowOrder extends HttpServlet {
//    private String message;
//
//    public void init() {
//        message = "Hello World!";
//    }
//
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.sendRedirect("orders.jsp");
//    }
//
//    public void destroy() {
//    }
//}