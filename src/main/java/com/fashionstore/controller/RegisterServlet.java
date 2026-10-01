package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.dao.impl.UserDAOImpl;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("REGISTER SERVLET CALLED");
    	System.out.println("Calling registerUser()");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // FORM DATA
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String pincode = request.getParameter("pincode");
        String country = request.getParameter("country");
        

        if (phone == null || phone.trim().isEmpty()) {
            phone = "N/A";
        }

        // CHECK EMAIL EXISTS
        if (userDAO.isEmailExists(email)) {
            request.setAttribute("error", "Email already exists");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                   .forward(request, response);
            return;
        }

        // CREATE USER OBJECT
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);
        user.setCity(city);
        user.setState(state);
        user.setPincode(pincode);
        user.setCountry(country);
        

        // REGISTER USER
        boolean registered = userDAO.registerUser(user);

        if (registered) {

            // SUCCESS MESSAGE (POPUP)
            request.getSession().setAttribute("message", "Registered Successfully!");

            // REDIRECT TO LOGIN
            response.sendRedirect(request.getContextPath() + "/login");
            return;
            

        } else {

            request.setAttribute("error", "Registration failed. Try again.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                   .forward(request, response);
        }
    }
}