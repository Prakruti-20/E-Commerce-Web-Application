package com.fashionstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "view";
        }

        switch (action) {

            case "add":
                addToCart(request, response);
                break;

            case "clear":
                clearCart(request, response);
                break;

            default:
                viewCart(request, response);
                break;
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    	int variantId =
    	        Integer.parseInt(request.getParameter("id"));

    	int quantity =
    	        Integer.parseInt(request.getParameter("quantity"));

    	String size =
    	        request.getParameter("size");

        HttpSession session = request.getSession();

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;

        for (CartItem item : cart) {

            if (item.getVariantId() == variantId) {

                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }

        if (!found) {

            CartItem item = new CartItem();

            item.setVariantId(variantId);
            item.setQuantity(quantity);
            item.setSize(size);

            cart.add(item);
        }

        session.setAttribute("cart", cart);

        System.out.println("ITEM ADDED SUCCESSFULLY");

        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();

        session.removeAttribute("cart");

        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void viewCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
               .forward(request, response);
    }
}