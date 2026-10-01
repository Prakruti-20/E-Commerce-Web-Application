<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.fashionstore.model.User" %>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>

<style>

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}

.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 40px;
    background: #111;
    color: white;
}

/* LOGO */
.logo a {
    color: white;
    text-decoration: none;
    font-size: 20px;
    font-weight: 600;
}

/* LINKS */
.nav-links a {
    color: white;
    text-decoration: none;
    margin: 0 12px;
    font-size: 15px;
}

.nav-links a:hover {
    color: #ffcc00;
}

/* RIGHT SECTION */
.auth-buttons {
    display: flex;
    gap: 10px;
    align-items: center;
}

/* CART ICON */
.cart-icon {
    color: white;
    font-size: 20px;
    text-decoration: none;
    margin-right: 10px;
    position: relative;
}

.cart-icon:hover {
    color: #ffcc00;
}

/* LOGIN BUTTON */
.login-btn {
    padding: 8px 14px;
    background: #007bff;
    border: none;
    color: white;
    border-radius: 5px;
    cursor: pointer;
}

/* LOGOUT BUTTON */
.logout-btn {
    padding: 8px 14px;
    background: #dc3545;
    border: none;
    color: white;
    border-radius: 5px;
    cursor: pointer;
}

.user-name {
    margin-right: 10px;
    font-weight: 600;
}

</style>

<%
    User user = (User) session.getAttribute("loggedInUser");
%>

<div class="navbar">

    <!-- LOGO -->
    <div class="logo">
        <a href="${pageContext.request.contextPath}/home">
            FashionStore
        </a>
    </div>

    <!-- NAV LINKS -->
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <a href="${pageContext.request.contextPath}/products">Products</a>
    </div>

    <!-- AUTH + CART -->
    <div class="auth-buttons">

        <!-- CART ICON -->
        <a href="${pageContext.request.contextPath}/cart" class="cart-icon">
            <i class="fa-solid fa-cart-shopping"></i>
        </a>

        <% if (user != null) { %>

            <span class="user-name">Hi, <%= user.getName() %></span>

            <a href="${pageContext.request.contextPath}/logout">
                <button class="logout-btn">Logout</button>
            </a>

        <% } else { %>

            <a href="${pageContext.request.contextPath}/login">
                <button class="login-btn">Login</button>
            </a>
            
            <a href="<%= request.getContextPath() %>/logout">
    <button class="logout-btn">
        Logout
    </button>
</a>

        <% } %>

    </div>

</div>