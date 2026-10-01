<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fashion Store | Home</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
</head>
<body>

    <jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

    <!-- HERO SECTION -->
    <section class="hero">
        <div class="hero-content">
            <p class="hero-subtitle">NEW SEASON COLLECTION</p>
            <h1 class="hero-title">Elevate Your Style With FashionStore</h1>
            <p class="hero-description">
                Discover premium fashion for men, women, and kids.
                Explore the latest trends, timeless essentials, and everyday style
                crafted for comfort and confidence.
            </p>

            <div class="hero-buttons">
                <a href="${pageContext.request.contextPath}/products" class="primary-btn">Shop Now</a>
                <a href="${pageContext.request.contextPath}/products" class="secondary-btn">View Collection</a>
            </div>
        </div>
    </section>

    <!-- CATEGORIES SECTION -->
    <section class="categories">
        <div class="container">
            <h2 class="section-title">Shop By Category</h2>
            <p class="section-subtitle">Find the latest styles across all fashion collections</p>

            <div class="category-grid">
                <div class="category-card">
                    <img src=" https://tse3.mm.bing.net/th/id/OIP.v04A_o66BTqv1KjTcsg6mgHaFt?rs=1&pid=ImgDetMain&o=7&rm=3"alt="Men">
                    <div class="category-overlay">
                        <h3>Men</h3>
                    </div>
                </div>

                <div class="category-card">
                    <img src="https://mir-s3-cdn-cf.behance.net/projects/404/70dbb8188718637.Y3JvcCwyOTQ1LDIzMDQsNjUsMA.jpg" alt="Women">
                    <div class="category-overlay">
                        <h3>Women</h3>
                    </div>
                </div>

                <div class="category-card">
                    <img src="https://tse3.mm.bing.net/th/id/OIP.7UIGvrEmg4665A_1pGtc5AHaLH?rs=1&pid=ImgDetMain&o=7&rm=3" alt="Kids">
                    <div class="category-overlay">
                        <h3>Kids</h3>
                    </div>
                </div>

                <div class="category-card">
                    <img src="https://tse1.mm.bing.net/th/id/OIP.q4YrRW6p7arYm2s0Aq4DnwHaFf?rs=1&pid=ImgDetMain&o=7&rm=3" alt="Footwear">
                    <div class="category-overlay">
                        <h3>Footwear</h3>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- FEATURED PRODUCTS SECTION -->
    <section class="featured-products">
        <div class="container">
            <div class="products-header">
                <div class="products-header-left">
                    <h2>Featured Products</h2>
                    <p>Popular picks loved by our customers</p>
                </div>

                <a href="${pageContext.request.contextPath}/products" class="primary-btn">See All Products</a>
            </div>

            <div class="products-grid">
                <div class="product-card">
                    <div class="product-image-container">
                        <img src="https://tse3.mm.bing.net/th/id/OIP.VrzKTGnYQnoLMbFQ-xSQhgHaJj?rs=1&pid=ImgDetMain&o=7&rm=3" alt="Product 1">
                        <div class="product-badge">New</div>
                    </div>
                    <div class="product-info">
                        <p class="product-category">Men</p>
                        <h3 class="product-title">Casual Shirt</h3>
                        <p class="product-price">₹1299</p>
                        <div class="product-actions">
                            <button class="add-cart-btn">Add to Cart</button>
                            <button class="view-btn"><i class="fa-solid fa-eye"></i></button>
                        </div>
                    </div>
                </div>

                <div class="product-card">
                    <div class="product-image-container">
                        <img src="https://i.pinimg.com/originals/ab/51/e7/ab51e73955e8bca2bd7dcf8a707c6109.jpg" alt="Product 2">
                        <div class="product-badge">Trending</div>
                    </div>
                    <div class="product-info">
                        <p class="product-category">Women</p>
                        <h3 class="product-title">Elegant Dress</h3>
                        <p class="product-price">₹2499</p>
                        <div class="product-actions">
                            <button class="add-cart-btn">Add to Cart</button>
                            <button class="view-btn"><i class="fa-solid fa-eye"></i></button>
                        </div>
                    </div>
                </div>

                <div class="product-card">
                    <div class="product-image-container">
                        <img src="https://a.storyblok.com/f/165154/1456x816/8d26a88690/11_cool-t-shirt-designs.png/m/" alt="Product 3">
                        <div class="product-badge">Best Seller</div>
                    </div>
                    <div class="product-info">
                        <p class="product-category">Kids</p>
                        <h3 class="product-title">Printed T-Shirt</h3>
                        <p class="product-price">₹699</p>
                        <div class="product-actions">
                            <button class="add-cart-btn">Add to Cart</button>
                            <button class="view-btn"><i class="fa-solid fa-eye"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- OFFER SECTION -->
    <section class="offer-section">
        <p class="offer-subtitle">Exclusive Offer</p>
        <h2 class="offer-title">Upgrade Your Wardrobe With Premium Styles</h2>
        <p class="offer-description">
            Enjoy stylish fashion collections designed for every occasion.
            Explore the latest arrivals and find your perfect look today.
        </p>

        <a href="${pageContext.request.contextPath}/products" class="primary-btn">Explore Now</a>
    </section>

    <jsp:include page="/WEB-INF/views/partials/footer.jsp" />

</body>
</html>