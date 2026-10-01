package com.fashionstore.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {

    private int orderId;
    private int userId;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String orderStatus;
    private Timestamp orderDate;

    // Default Constructor
    public Order() {

    }

    // Parameterized Constructor
    public Order(int orderId, int userId, BigDecimal totalAmount,
                 String paymentMethod, String orderStatus,
                 Timestamp orderDate) {

        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    // Getters and Setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    // toString()

    @Override
    public String toString() {
        return "Order [orderId=" + orderId +
                ", userId=" + userId +
                ", totalAmount=" + totalAmount +
                ", paymentMethod=" + paymentMethod +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate + "]";
    }

	public void setShippingAddress(String string) {
		// TODO Auto-generated method stub
		
	}

	public String getShippingAddress() {
		// TODO Auto-generated method stub
		return null;
	}
}