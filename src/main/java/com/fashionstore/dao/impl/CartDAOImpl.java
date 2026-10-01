package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.model.Cart;
import com.fashionstore.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    private static final String CREATE_CART =
            "INSERT INTO cart(user_id) VALUES(?)";

    private static final String GET_CART_BY_ID =
            "SELECT * FROM cart WHERE cart_id=?";

    private static final String GET_CART_BY_USER_ID =
            "SELECT * FROM cart WHERE user_id=?";

    private static final String DELETE_CART =
            "DELETE FROM cart WHERE cart_id=?";

    private static final String CHECK_CART_EXISTS =
            "SELECT * FROM cart WHERE user_id=?";

    @Override
    public boolean createCart(int userId) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CART)) {

            preparedStatement.setInt(1, userId);

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
    public Cart getCartById(int cartId) {

        Cart cart = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_BY_ID)) {

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cart = mapCart(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public Cart getCartByUserId(int userId) {

        Cart cart = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_BY_USER_ID)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cart = mapCart(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public Cart getOrCreateCartByUserId(int userId) {

        Cart cart = getCartByUserId(userId);

        if (cart == null) {

            boolean created = createCart(userId);

            if (created) {
                cart = getCartByUserId(userId);
            }
        }

        return cart;
    }

    @Override
    public boolean deleteCart(int cartId) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART)) {

            preparedStatement.setInt(1, cartId);

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
    public boolean cartExistsByUserId(int userId) {

        boolean exists = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CART_EXISTS)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    private Cart mapCart(ResultSet resultSet) throws Exception {

        Cart cart = new Cart();

        cart.setCartId(resultSet.getInt("cart_id"));
        cart.setUserId(resultSet.getInt("user_id"));
        cart.setCreatedAt(resultSet.getTimestamp("created_at"));

        return cart;
    }
}