<%@ page import="com.fashionstore.model.Product" %>

<%
    Product p = (Product) request.getAttribute("product");

    if (p == null) {
        response.sendRedirect(request.getContextPath() + "/products");
        return;
    }
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
<title>Product Details</title>

<style>

body{
    font-family:Arial, sans-serif;
    background:#f5f5f5;
    margin:0;
    padding:0;
}

.container{
    display:flex;
    gap:40px;
    padding:40px;
    max-width:1000px;
    margin:40px auto;
    background:white;
    border-radius:12px;
    box-shadow:0 2px 10px rgba(0,0,0,0.1);
}

.img-box img{
    width:350px;
    height:350px;
    object-fit:cover;
    border-radius:10px;
}

.details{
    flex:1;
}

h2{
    margin-bottom:15px;
}

.brand{
    margin-bottom:10px;
    color:#444;
}

.desc{
    margin:15px 0;
    color:#666;
    line-height:1.6;
}

.price{
    color:green;
    font-size:26px;
    font-weight:bold;
    margin:15px 0;
}

.select-box{
    margin-top:20px;
}

.select-box label{
    display:block;
    margin-bottom:8px;
    font-weight:bold;
}

.select-box select,
.select-box input{
    padding:10px;
    width:150px;
    border:1px solid #ccc;
    border-radius:6px;
    outline:none;
}

.button-group{
    margin-top:30px;
    display:flex;
    gap:15px;
}

.btn{
    padding:12px 20px;
    border:none;
    border-radius:6px;
    text-decoration:none;
    color:white;
    cursor:pointer;
    font-size:15px;
}

.btn-cart{
    background:#28a745;
}

.btn-cart:hover{
    background:#1f7d36;
}

.btn-back{
    background:#007bff;
}

.btn-back:hover{
    background:#0056b3;
}

</style>

</head>

<body>

<div class="container">

    <!-- PRODUCT IMAGE -->

    <div class="img-box">

        <img src="<%= request.getContextPath() + "/" + p.getImagePath() %>"
             alt="Product Image">

    </div>

    <!-- PRODUCT DETAILS -->

    <div class="details">

        <h2><%= p.getProductName() %></h2>

        <p class="brand">
            <b>Brand:</b> <%= p.getBrand() %>
        </p>

        <p class="desc">
            <%= p.getDescription() %>
        </p>

        <p class="price">
            Rs <%= p.getBasePrice() %>
        </p>

        <!-- ADD TO CART FORM -->

        <form action="<%= request.getContextPath() %>/cart"
              method="get">

            <input type="hidden"
                   name="action"
                   value="add">

            <input type="hidden"
                   name="id"
                   value="<%= p.getProductId() %>">

            <!-- SIZE -->

            <div class="select-box">

                <label>Select Size</label>

                <select name="size" required>

                    <option value="">Choose Size</option>

                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>

                </select>

            </div>

            <!-- QUANTITY -->

            <div class="select-box">

                <label>Quantity</label>

                <input type="number"
                       name="quantity"
                       value="1"
                       min="1"
                       max="10"
                       required>

            </div>

            <!-- BUTTONS -->

            <div class="button-group">

                <button type="submit"
                        class="btn btn-cart">

                    Add to Cart

                </button>

                <a href="<%= request.getContextPath() %>/products"
                   class="btn btn-back">

                    Back to Products

                </a>

            </div>

        </form>

    </div>

</div>

</body>
</html>