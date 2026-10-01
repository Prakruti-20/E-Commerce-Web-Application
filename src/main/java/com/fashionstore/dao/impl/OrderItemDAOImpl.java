package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.OrderItemDAO;
import com.fashionstore.model.OrderItem;
import com.fashionstore.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    private static final String INSERT_ORDER_ITEM =
            "INSERT INTO order_items(order_id, variant_id, quantity, price) VALUES(?,?,?,?)";

    private static final String GET_ORDER_ITEM_BY_ID =
            "SELECT * FROM order_items WHERE order_item_id=?";

    private static final String GET_ORDER_ITEMS_BY_ORDER_ID =
            "SELECT * FROM order_items WHERE order_id=?";

    private static final String DELETE_ORDER_ITEM =
            "DELETE FROM order_items WHERE order_item_id=?";

    private static final String DELETE_ORDER_ITEMS_BY_ORDER_ID =
            "DELETE FROM order_items WHERE order_id=?";

    @Override
    public boolean addOrderItem(OrderItem orderItem) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM)) {

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getVariantId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setBigDecimal(4, orderItem.getPrice());

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
    public boolean addOrderItems(List<OrderItem> orderItems) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM)) {

            for (OrderItem orderItem : orderItems) {

                preparedStatement.setInt(1, orderItem.getOrderId());
                preparedStatement.setInt(2, orderItem.getVariantId());
                preparedStatement.setInt(3, orderItem.getQuantity());
                preparedStatement.setBigDecimal(4, orderItem.getPrice());

                preparedStatement.addBatch();
            }

            int[] rows = preparedStatement.executeBatch();

            if (rows.length > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {

        OrderItem orderItem = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEM_BY_ID)) {

            preparedStatement.setInt(1, orderItemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                orderItem = mapOrderItem(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {

        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEMS_BY_ORDER_ID)) {

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orderItems.add(mapOrderItem(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public boolean deleteOrderItem(int orderItemId) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEM)) {

            preparedStatement.setInt(1, orderItemId);

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
    public boolean deleteOrderItemsByOrderId(int orderId) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEMS_BY_ORDER_ID)) {

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

    private OrderItem mapOrderItem(ResultSet resultSet) throws Exception {

        OrderItem orderItem = new OrderItem();

        orderItem.setOrderItemId(resultSet.getInt("order_item_id"));
        orderItem.setOrderId(resultSet.getInt("order_id"));
        orderItem.setVariantId(resultSet.getInt("variant_id"));
        orderItem.setQuantity(resultSet.getInt("quantity"));
        orderItem.setPrice(resultSet.getBigDecimal("price"));

        return orderItem;
    }
}