
<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.CartItem" %>
<%@ page import="com.fashionstore.model.Product" %>
<%@ page import="com.fashionstore.dao.impl.ProductDAOImpl" %>

<%
    List<CartItem> cart =
        (List<CartItem>) session.getAttribute("cart");

    ProductDAOImpl productDAO = new ProductDAOImpl();
%>

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
<title>My Cart</title>

<style>

body{
    font-family: Arial, sans-serif;
    background:#f5f5f5;
    margin:0;
    padding:30px;
}

.cart-container{
    max-width:900px;
    margin:auto;
}

.cart-title{
    text-align:center;
    margin-bottom:30px;
}

.cart-item{
    display:flex;
    gap:20px;
    background:white;
    padding:20px;
    border-radius:10px;
    margin-bottom:20px;
    box-shadow:0 2px 8px rgba(0,0,0,0.1);
    align-items:center;
}

.cart-image img{
    width:140px;
    height:140px;
    object-fit:cover;
    border-radius:10px;
}

.cart-details{
    flex:1;
}

.cart-details h3{
    margin:0 0 10px;
}

.brand{
    color:#666;
    margin-bottom:8px;
}

.price{
    color:green;
    font-size:18px;
    font-weight:bold;
    margin-bottom:8px;
}

.quantity{
    margin-bottom:10px;
}

.actions{
    margin-top:25px;
    display:flex;
    gap:15px;
}

.btn{
    padding:10px 18px;
    border:none;
    border-radius:6px;
    text-decoration:none;
    color:white;
    cursor:pointer;
}

.shop-btn{
    background:#007bff;
}

.clear-btn{
    background:#dc3545;
}

.empty-cart{
    text-align:center;
    background:white;
    padding:40px;
    border-radius:10px;
}

</style>

</head>
<body>

<div class="cart-container">

<h1 class="cart-title">My Cart</h1>

<%
if(cart == null || cart.isEmpty()){
%>

    <div class="empty-cart">
        <h2>Your cart is empty</h2>
        <br>
        <a href="<%= request.getContextPath() %>/products" class="btn shop-btn">
            Continue Shopping
        </a>
    </div>

<%
}else{

    for(CartItem item : cart){

        Product product = productDAO.getProductById(item.getVariantId());

        if(product != null){
%>

<div class="cart-item">

    <div class="cart-image">
        <img src="<%= request.getContextPath() + "/" + product.getImagePath() %>"
             alt="Product Image">
    </div>

    <div class="cart-details">

        <h3><%= product.getProductName() %></h3>

        <p class="brand">
            Brand: <%= product.getBrand() %>
        </p>

        <p class="price">
            Rs <%= product.getBasePrice() %>
        </p>

        <p class="quantity">
            Quantity: <%= item.getQuantity() %>
        </p>
        
<p>
    <b>Size:</b>
    <%= item.getSize() %>
</p>

        <p>
            <%= product.getDescription() %>
        </p>

    </div>

</div>

<%
        }
    }
}
%>

<div class="actions">

    <a href="<%= request.getContextPath() %>/products"
       class="btn shop-btn">
       Continue Shopping
    </a>

    <a href="<%= request.getContextPath() %>/cart?action=clear"
       class="btn clear-btn">
       Clear Cart
    </a>
    <a href="<%= request.getContextPath() %>/checkout"
   class="btn"
   style="background:#28a745;">
   Proceed to Checkout
</a>

</div>

</div>

</body>
</html>

