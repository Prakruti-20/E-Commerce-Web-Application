package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.Order;

public interface OrderDAO {
	boolean placOrder(Order order);
	Order getOrderById(int orderId);
	List<Order> getOrdersByUserId(int userId);
	List<Order> getAllOrders();
	boolean updateOrdersStatus(int orderId, String orderStatus);
	boolean deleteOrder(int orderId);
  
    
    

}