package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/productDetail")
public class ProductDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("products");
            return;
        }

        int productId = Integer.parseInt(idParam);

        Product product = productDAO.getProductById(productId);

        if (product == null) {
            response.sendRedirect("products");
            return;
        }

        request.setAttribute("product", product);

        request.getRequestDispatcher("/WEB-INF/views/product-details.jsp")
                .forward(request, response);
    }
}