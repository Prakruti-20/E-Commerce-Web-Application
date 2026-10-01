-- ========================================================
-- FashionStore E-Commerce Database Schema
-- Database: fashion_store
-- ========================================================

CREATE DATABASE IF NOT EXISTS fashion_store;
USE fashion_store;

-- 1. Users Table
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    address TEXT,
    city VARCHAR(50),
    state VARCHAR(50),
    pincode VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Categories Table
CREATE TABLE IF NOT EXISTS categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL,
    description TEXT
);

-- 3. Products Table
CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT NOT NULL,
    product_name VARCHAR(150) NOT NULL,
    brand VARCHAR(100),
    description TEXT,
    image_path VARCHAR(255),
    base_price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE
);

-- 4. Product Variants Table
CREATE TABLE IF NOT EXISTS product_variants (
    variant_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    size VARCHAR(20) NOT NULL,
    stock_quantity INT DEFAULT 0,
    variant_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);

-- 5. Cart Table
CREATE TABLE IF NOT EXISTS cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- 6. Cart Items Table
CREATE TABLE IF NOT EXISTS cart_items (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT NOT NULL,
    variant_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id) ON DELETE CASCADE,
    FOREIGN KEY (variant_id) REFERENCES product_variants(variant_id) ON DELETE CASCADE
);

-- 7. Orders Table
CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    order_status VARCHAR(50) DEFAULT 'PENDING',
    payment_method VARCHAR(50) DEFAULT 'COD',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- 8. Order Items Table
CREATE TABLE IF NOT EXISTS order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    variant_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (variant_id) REFERENCES product_variants(variant_id) ON DELETE CASCADE
);
