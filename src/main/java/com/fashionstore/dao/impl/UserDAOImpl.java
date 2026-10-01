package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.model.User;
import com.fashionstore.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    

    private static final String LOGIN_USER =
            "SELECT * FROM users WHERE email=? AND password=?";

    private static final String GET_USER_BY_ID =
            "SELECT * FROM users WHERE user_id=?";

    private static final String GET_USER_BY_EMAIL =
            "SELECT * FROM users WHERE email=?";

    private static final String GET_USER_BY_PHONE =
            "SELECT * FROM users WHERE phone=?";

    private static final String UPDATE_USER =
            "UPDATE users SET name=?, email=?, phone=?, address=?, city=?, state=?, pincode=? WHERE user_id=?";

    private static final String UPDATE_PASSWORD =
            "UPDATE users SET password=? WHERE user_id=?";

    private static final String GET_ALL_USERS =
            "SELECT * FROM users ORDER BY created_at DESC";

    @Override
    public boolean registerUser(User user) {
    	System.out.println("Inside registerUser()");

        boolean result = false;
        
        String INSERT_USER =
                "INSERT INTO users(name, email, phone, password, address, city, state, pincode) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getCity());
            preparedStatement.setString(7, user.getState());
            preparedStatement.setString(8, user.getPincode());
            System.out.println("BEFORE INSERT");

            
            int rows = preparedStatement.executeUpdate();
            
            System.out.println("ROWS INSERTED = " + rows);

            result =(rows > 0);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User loginUser(String email, String password) {

        User user = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = mapUser(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserById(int userId) {

        User user = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = mapUser(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        User user = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = mapUser(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserByPhone(String phone) {

        User user = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_PHONE)) {

            preparedStatement.setString(1, phone);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = mapUser(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean updateUser(User user) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setString(6, user.getState());
            preparedStatement.setString(7, user.getPincode());
            preparedStatement.setInt(8, user.getUserId());

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
    public boolean updatePassword(int userId, String newPassword) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userId);

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
    public boolean emailExists(String email) {

        boolean exists = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public boolean phoneExists(String phone) {

        boolean exists = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_PHONE)) {

            preparedStatement.setString(1, phone);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    private User mapUser(ResultSet resultSet) throws Exception {

        User user = new User();

        user.setUserId(resultSet.getInt("user_id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setPassword(resultSet.getString("password"));
        user.setAddress(resultSet.getString("address"));
        user.setCity(resultSet.getString("city"));
        user.setState(resultSet.getString("state"));
        user.setPincode(resultSet.getString("pincode"));
        user.setCreatedAt(resultSet.getTimestamp("created_at"));

        return user;
    }
    @Override
    public boolean isEmailExists(String email) {

        boolean exists = false;

        String sql = "SELECT 1 FROM users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
}