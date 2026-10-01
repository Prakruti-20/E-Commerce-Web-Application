<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.CartItem" %>

<%
    List<CartItem> cart =
        (List<CartItem>) session.getAttribute("cart");
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
<title>Checkout</title>

<style>

body{
    font-family:Arial;
    background:#f5f5f5;
    padding:30px;
}

.checkout-container{
    max-width:800px;
    margin:auto;
    background:white;
    padding:30px;
    border-radius:10px;
}

input, textarea{
    width:100%;
    padding:12px;
    margin-top:10px;
    margin-bottom:20px;
    border:1px solid #ccc;
    border-radius:5px;
}

button{
    background:#28a745;
    color:white;
    border:none;
    padding:12px 20px;
    border-radius:5px;
    cursor:pointer;
}

.cart-summary{
    margin-top:30px;
    background:#fafafa;
    padding:20px;
    border-radius:10px;
}

</style>

</head>

<body>

<div class="checkout-container">

    <h2>Checkout</h2>

    <form action="<%= request.getContextPath() %>/placeOrder"
      method="post">

    <label>Full Name</label>

    <input type="text"
           name="name"
           required>

    <label>Phone Number</label>

    <input type="text"
           name="phone"
           required>

    <label>Delivery Address</label>

    <textarea name="address"
              rows="4"
              required></textarea>

    <label>Payment Method</label>

    <select name="paymentMethod">

        <option value="COD">
            Cash On Delivery
        </option>

        <option value="UPI">
            UPI
        </option>

        <option value="CARD">
            Card
        </option>

    </select>

    <br><br>

    <button type="submit"
            class="place-order-btn">

        Place Order

    </button>

</form>
</div>

</body>
</html>