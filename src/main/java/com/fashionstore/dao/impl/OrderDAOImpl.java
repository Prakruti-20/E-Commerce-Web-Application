package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.model.Order;
import com.fashionstore.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private static final String PLACE_ORDER =
            "INSERT INTO orders(user_id, total_amount, order_status, payment_method) VALUES(?,?,?,?)";

    private static final String GET_ORDER_BY_ID =
            "SELECT * FROM orders WHERE order_id=?";

    private static final String GET_ORDERS_BY_USER_ID =
            "SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC";

    private static final String GET_ALL_ORDERS =
            "SELECT * FROM orders ORDER BY order_date DESC";

    private static final String UPDATE_ORDER_STATUS =
            "UPDATE orders SET order_status=? WHERE order_id=?";

    private static final String DELETE_ORDER =
            "DELETE FROM orders WHERE order_id=?";

    @Override
    public boolean placOrder(Order order) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PLACE_ORDER)) {

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setBigDecimal(2, order.getTotalAmount());
            preparedStatement.setString(3, order.getOrderStatus());
            preparedStatement.setString(4, order.getPaymentMethod());
            
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public Order getOrderById(int orderId) {

        Order order = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID)) {

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = mapOrder(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_USER_ID)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orders.add(mapOrder(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                orders.add(mapOrder(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public boolean updateOrdersStatus(int orderId, String orderStatus) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {

            preparedStatement.setString(1, orderStatus);
            preparedStatement.setInt(2, orderId);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteOrder(int orderId) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {

            preparedStatement.setInt(1, orderId);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    private Order mapOrder(ResultSet resultSet) throws Exception {

        Order order = new Order();

        order.setOrderId(resultSet.getInt("order_id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setTotalAmount(resultSet.getBigDecimal("total_amount"));
        order.setOrderStatus(resultSet.getString("order_status"));
        order.setPaymentMethod(resultSet.getString("payment_method"));
        order.setOrderDate(resultSet.getTimestamp("order_date"));

        return order;
    }
}