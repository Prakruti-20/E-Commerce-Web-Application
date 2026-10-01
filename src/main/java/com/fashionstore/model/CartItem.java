package com.fashionstore.model;

public class CartItem {

    private int cartItemId;
    private int cartId;
    private int variantId;
    private int quantity;
    private String size;

    // Default Constructor

    public CartItem() {

    }

    // Parameterized Constructor

    public CartItem(int cartItemId,
                    int cartId,
                    int variantId,
                    int quantity,
                    String size) {

        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.size = size;
    }

    // GETTERS AND SETTERS

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // SIZE

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {

        return "CartItem [cartItemId=" + cartItemId
                + ", cartId=" + cartId
                + ", variantId=" + variantId
                + ", quantity=" + quantity
                + ", size=" + size
                + "]";
    }
}