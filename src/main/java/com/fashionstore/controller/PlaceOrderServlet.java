package com.fashionstore.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // CLEAR CART

        HttpSession session = request.getSession();

        session.removeAttribute("cart");

        // OPEN SUCCESS PAGE

        request.getRequestDispatcher(
                "/WEB-INF/views/order-success.jsp")
                .forward(request, response);
    }
}