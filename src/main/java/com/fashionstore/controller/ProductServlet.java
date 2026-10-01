package com.fashionstore.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.CategoryDAOImpl;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Category;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("ProductServlet called");

        Integer categoryId = parseIntOrNull(request.getParameter("categoryId"));
        String keyword = trimToNull(request.getParameter("keyword"));
        BigDecimal minPrice = parseBigDecimal(request.getParameter("minPrice"));
        BigDecimal maxPrice = parseBigDecimal(request.getParameter("maxPrice"));
        String sortBy = trimToNull(request.getParameter("sortBy"));

        List<Product> products = productDAO.getFilteredProducts(
                categoryId, keyword, minPrice, maxPrice, sortBy
        );

        List<Category> categories = categoryDAO.getAllCategories();

        // PASS DATA TO JSP
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);

        // KEEP FILTER VALUES (IMPORTANT FOR UI)
        request.setAttribute("selectedCategoryId", categoryId);
        request.setAttribute("keyword", keyword);
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("sortBy", sortBy);

        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
                .forward(request, response);
    }
    private Integer parseIntOrNull(String value) {
        try {
            if (value == null || value.trim().isEmpty()) {
                return null;
            }
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    private BigDecimal parseBigDecimal(String value) {
        try {
            if (value == null || value.trim().isEmpty()) {
                return null;
            }
            return new BigDecimal(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String trimToNull(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value.trim();
        
    }
}