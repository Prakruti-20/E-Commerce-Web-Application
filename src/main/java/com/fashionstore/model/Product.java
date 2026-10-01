package com.fashionstore.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {

    private int productId;
    private int categoryId;
    private String productName;
    private String brand;
    private String description;
    private String imagePath;
    private BigDecimal basePrice;
    private Timestamp createdAt;

    // Default Constructor
    public Product() {

    }

    // Parameterized Constructor
    public Product(int productId, int categoryId, String productName,
                   String brand, String description, String imagePath,
                   BigDecimal basePrice, Timestamp createdAt) {

        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.brand = brand;
        this.description = description;
        this.imagePath = imagePath;
        this.basePrice = basePrice;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // toString()

    @Override
    public String toString() {
        return "Product [productId=" + productId +
                ", categoryId=" + categoryId +
                ", productName=" + productName +
                ", brand=" + brand +
                ", description=" + description +
                ", imagePath=" + imagePath +
                ", basePrice=" + basePrice +
                ", createdAt=" + createdAt + "]";
    }

	public void setActive(boolean boolean1) {
		// TODO Auto-generated method stub
		
	}

	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}
}