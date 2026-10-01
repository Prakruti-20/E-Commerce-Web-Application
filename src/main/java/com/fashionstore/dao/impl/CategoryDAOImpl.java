package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.model.Category;
import com.fashionstore.util.DBConnection;

public class CategoryDAOImpl implements CategoryDAO {

    private static final String INSERT_CATEGORY =
            "INSERT INTO categories(category_name, description) VALUES(?,?)";

    private static final String UPDATE_CATEGORY =
            "UPDATE categories SET category_name=?, description=? WHERE category_id=?";

    private static final String DELETE_CATEGORY =
            "DELETE FROM categories WHERE category_id=?";

    private static final String GET_CATEGORY_BY_ID =
            "SELECT * FROM categories WHERE category_id=?";

    private static final String GET_CATEGORY_BY_NAME =
            "SELECT * FROM categories WHERE category_name=?";

    private static final String GET_ALL_CATEGORIES =
            "SELECT * FROM categories ORDER BY category_name";

    @Override
    public boolean addCategory(Category category) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY)) {

            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getDescription());

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
    public boolean updateCategory(Category category) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)) {

            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setInt(3, category.getCategoryId());

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
    public boolean deleteCategory(int categoryId) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY)) {

            preparedStatement.setInt(1, categoryId);

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
    public Category getCategoryById(int categoryId) {

        Category category = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_BY_ID)) {

            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                category = mapCategory(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public Category getCategoryByName(String categoryName) {

        Category category = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_BY_NAME)) {

            preparedStatement.setString(1, categoryName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                category = mapCategory(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CATEGORIES);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                categories.add(mapCategory(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    private Category mapCategory(ResultSet resultSet) throws Exception {

        Category category = new Category();

        category.setCategoryId(resultSet.getInt("category_id"));
        category.setCategoryName(resultSet.getString("category_name"));
        category.setDescription(resultSet.getString("description"));

        return category;
    }
}