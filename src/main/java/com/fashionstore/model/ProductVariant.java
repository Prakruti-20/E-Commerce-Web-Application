package com.fashionstore.model;

import java.math.BigDecimal;

public class ProductVariant {

    private int variantId;
    private int productId;
    private String size;
    private int stock;
    private BigDecimal variantPrice;

    // Default Constructor
    public ProductVariant() {

    }

    // Parameterized Constructor
    public ProductVariant(int variantId, int productId, String size,
                          int stock, BigDecimal variantPrice) {

        this.variantId = variantId;
        this.productId = productId;
        this.size = size;
        this.stock = stock;
        this.variantPrice = variantPrice;
    }

    // Getters and Setters

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getVariantPrice() {
        return variantPrice;
    }

    public void setVariantPrice(BigDecimal variantPrice) {
        this.variantPrice = variantPrice;
    }

    // toString()

    @Override
    public String toString() {
        return "ProductVariant [variantId=" + variantId +
                ", productId=" + productId +
                ", size=" + size +
                ", stock=" + stock +
                ", variantPrice=" + variantPrice + "]";
    }
}