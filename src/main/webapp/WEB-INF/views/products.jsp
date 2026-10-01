<%@ page import="java.util.List" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Product" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.fashionstore.model.User" %>

<%
    User loggedInUser =
            (User) session.getAttribute("loggedInUser");

    if(loggedInUser == null){

        response.sendRedirect(
                request.getContextPath() + "/login");

        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>

<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f5f5f5;
    }

    h2 {
        text-align: center;
        margin-bottom: 30px;
    }

    .product-container {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        justify-content: center;
    }

    .product-card {
        width: 220px;
        background: white;
        border-radius: 10px;
        padding: 15px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        text-align: center;
        transition: transform 0.2s;
    }

    .product-card:hover {
        transform: scale(1.05);
    }

    .product-card img {
        width: 150px;
        height: 150px;
        object-fit: cover;
        border-radius: 8px;
    }

    .price {
        color: green;
        font-weight: bold;
        margin-top: 5px;
    }

    .btn {
        display: inline-block;
        margin-top: 10px;
        padding: 8px 12px;
        background: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 5px;
    }

    .btn:hover {
        background: #0056b3;
    }
    
</style>

</head>
<body>

<h2>All Products</h2>
<div class="page-layout">

    <div class="filter-panel">
        <!-- filters -->
    </div>

    <div class="products-panel">
        <!-- products -->
    </div>

</div>
<form action="products" method="get">

    <input type="text" name="keyword"
           
           placeholder="Search products..." />

    

    <input type="number" name="minPrice"
           value="₹{minPrice != null ? minPrice : ''}"
           placeholder="Min Price" />

    <input type="number" name="maxPrice"
           value="₹{maxPrice != null ? maxPrice : ''}"
           placeholder="Max Price" />

    <select name="sortBy">
        <option value="">Sort By</option>

        <option value="lowToHigh"
            <c:if test="₹{sortBy == 'lowToHigh'}">selected</c:if>>
            Price Low to High
        </option>

        <option value="highToLow"
            <c:if test="₹{sortBy == 'highToLow'}">selected</c:if>>
            Price High to Low
        </option>
    </select>

    <button type="submit">Apply Filters</button>
    <a href="products" class="clear-btn"><button>Clear</button></a>

</form><br><br><br>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<div class="product-container">

<%
    if (products != null) {
        for (Product p : products) {
%>

    <div class="product-card">
        <img src="<%= request.getContextPath() + "/" + p.getImagePath() %>">

        <h3><%= p.getProductName() %></h3>

        <p><%= p.getBrand() %></p>

        <p class="price">₹<%= p.getBasePrice() %></p>

        <a class="btn" href="productDetail?id=<%= p.getProductId() %>">
            View Details
        </a>
    </div>

<%
        }
    } else {
%>

    <p>No products found.</p>

<%
    }
%>

</div>

</body>
