package com.fashionstore.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.model.Product;
import com.fashionstore.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private static final String INSERT_PRODUCT =
            "INSERT INTO products(category_id, product_name, brand, description, image_path, base_price) VALUES(?,?,?,?,?,?)";

    private static final String UPDATE_PRODUCT =
            "UPDATE products SET category_id=?, product_name=?, brand=?, description=?, image_path=?, base_price=? WHERE product_id=?";

    private static final String DELETE_PRODUCT =
            "DELETE FROM products WHERE product_id=?";

    private static final String GET_PRODUCT_BY_ID =
            "SELECT * FROM products WHERE product_id=?";

    private static final String GET_ALL_PRODUCTS =
            "SELECT * FROM products ORDER BY created_at DESC";

    private static final String GET_ALL_ACTIVE_PRODUCTS =
            "SELECT * FROM products ORDER BY created_at DESC";

    private static final String GET_PRODUCTS_BY_CATEGORY =
            "SELECT * FROM products WHERE category_id=? ORDER BY created_at DESC";

    private static final String SEARCH_PRODUCTS =
            "SELECT * FROM products WHERE product_name LIKE ? OR brand LIKE ? OR description LIKE ?";

    private static final String GET_PRODUCTS_BY_PRICE_RANGE =
            "SELECT * FROM products WHERE base_price BETWEEN ? AND ?";

    private static final String GET_PRODUCTS_SORTED_BY_PRICE_ASC =
            "SELECT * FROM products ORDER BY base_price ASC";

    private static final String GET_PRODUCTS_SORTED_BY_PRICE_DESC =
            "SELECT * FROM products ORDER BY base_price DESC";

    @Override
    public boolean addProduct(Product product) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {

            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getBrand());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setString(5, product.getImagePath());
            preparedStatement.setBigDecimal(6, product.getBasePrice());
            preparedStatement.setBoolean(7, product.isActive());

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
    public boolean updateProduct(Product product) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {

            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getBrand());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setString(5, product.getImagePath());
            preparedStatement.setBigDecimal(6, product.getBasePrice());
            preparedStatement.setBoolean(7, product.isActive());
            preparedStatement.setInt(8, product.getProductId());

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
    public boolean deleteProduct(int productId) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {

            preparedStatement.setInt(1, productId);

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
    public Product getProductById(int productId) {

        Product product = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = mapProduct(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getAllActiveProducts() {

        List<Product> products = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ACTIVE_PRODUCTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> products = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY)) {

            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> products = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCTS)) {

            String searchText = "%" + keyword + "%";

            preparedStatement.setString(1, searchText);
            preparedStatement.setString(2, searchText);
            preparedStatement.setString(3, searchText);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {

        List<Product> products = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_PRICE_RANGE)) {

            preparedStatement.setBigDecimal(1, minPrice);
            preparedStatement.setBigDecimal(2, maxPrice);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductsSortedByPriceAsc() {

        List<Product> products = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_SORTED_BY_PRICE_ASC);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductsSortedByPriceDesc() {

        List<Product> products = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_SORTED_BY_PRICE_DESC);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getLatestProducts(int limit) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products ORDER BY created_at DESC LIMIT ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, limit);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getFilteredProducts(Integer categoryId, String keyword,
            BigDecimal minPrice, BigDecimal maxPrice, String sortBy) {

        List<Product> products = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");

        if (categoryId != null) {
            sql.append(" AND category_id = ").append(categoryId);
        }

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (product_name LIKE '%")
               .append(keyword)
               .append("%' OR brand LIKE '%")
               .append(keyword)
               .append("%')");
        }

        if (minPrice != null) {
            sql.append(" AND base_price >= ").append(minPrice);
        }

        if (maxPrice != null) {
            sql.append(" AND base_price <= ").append(maxPrice);
        }

        if ("lowToHigh".equals(sortBy)) {
            sql.append(" ORDER BY base_price ASC");
        } else if ("highToLow".equals(sortBy)) {
            sql.append(" ORDER BY base_price DESC");
        } else {
            sql.append(" ORDER BY created_at DESC");
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
    @Override
    public List<Product> getRelatedProducts(int categoryId, int excludeProductId, int limit) {

        List<Product> products = new ArrayList<>();

        String query =
                "SELECT * FROM products WHERE category_id=? AND product_id!=? LIMIT ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, excludeProductId);
            preparedStatement.setInt(3, limit);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    private Product mapProduct(ResultSet resultSet) throws Exception {

        Product product = new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setCategoryId(resultSet.getInt("category_id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setBrand(resultSet.getString("brand"));
        product.setDescription(resultSet.getString("description"));
        product.setImagePath(resultSet.getString("image_path"));
        product.setBasePrice(resultSet.getBigDecimal("base_price"));
        product.setCreatedAt(resultSet.getTimestamp("created_at"));

        return product;
    }

	
}