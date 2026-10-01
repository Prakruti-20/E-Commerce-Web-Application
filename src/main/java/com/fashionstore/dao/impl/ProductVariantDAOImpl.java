package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.ProductVariantDAO;
import com.fashionstore.model.ProductVariant;
import com.fashionstore.util.DBConnection;

public class ProductVariantDAOImpl implements ProductVariantDAO {

    private static final String INSERT_VARIANT =
            "INSERT INTO product_variants(product_id, size, stock_quantity, variant_price) VALUES(?,?,?,?)";

    private static final String UPDATE_VARIANT =
            "UPDATE product_variants SET product_id=?, size=?, stock_quantity=?, variant_price=? WHERE variant_id=?";

    private static final String DELETE_VARIANT =
            "DELETE FROM product_variants WHERE variant_id=?";

    private static final String GET_VARIANT_BY_ID =
            "SELECT * FROM product_variants WHERE variant_id=?";

    private static final String GET_VARIANT_BY_PRODUCT_AND_SIZE =
            "SELECT * FROM product_variants WHERE product_id=? AND size=?";

    private static final String GET_VARIANTS_BY_PRODUCT_ID =
            "SELECT * FROM product_variants WHERE product_id=?";

    private static final String GET_ALL_VARIANTS =
            "SELECT * FROM product_variants";

    private static final String UPDATE_STOCK =
            "UPDATE product_variants SET stock=? WHERE variant_id=?";

    private static final String CHECK_STOCK =
            "SELECT stock FROM product_variants WHERE variant_id=?";

    @Override
    public boolean addProductVariant(ProductVariant productVariant) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VARIANT)) {

            preparedStatement.setInt(1, productVariant.getProductId());
            preparedStatement.setString(2, productVariant.getSize());
            preparedStatement.setInt(3, productVariant.getStock());
            preparedStatement.setBigDecimal(4, productVariant.getVariantPrice());

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
    public boolean updateProductVariant(ProductVariant productVariant) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VARIANT)) {

            preparedStatement.setInt(1, productVariant.getProductId());
            preparedStatement.setString(2, productVariant.getSize());
            preparedStatement.setInt(3, productVariant.getStock());
            preparedStatement.setBigDecimal(4, productVariant.getVariantPrice());
            preparedStatement.setInt(5, productVariant.getVariantId());

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
    public boolean deleteProductVariant(int variantId) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VARIANT)) {

            preparedStatement.setInt(1, variantId);

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
    public ProductVariant getVariantById(int variantId) {

        ProductVariant productVariant = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_VARIANT_BY_ID)) {

            preparedStatement.setInt(1, variantId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                productVariant = mapVariant(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productVariant;
    }

    @Override
    public ProductVariant getVariantByProductAndSize(int productId, String size) {

        ProductVariant productVariant = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_VARIANT_BY_PRODUCT_AND_SIZE)) {

            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, size);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                productVariant = mapVariant(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productVariant;
    }

    @Override
    public List<ProductVariant> getVariantsByProductId(int productId) {

        List<ProductVariant> variants = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_VARIANTS_BY_PRODUCT_ID)) {

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                variants.add(mapVariant(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return variants;
    }

    @Override
    public List<ProductVariant> getAllVariants() {

        List<ProductVariant> variants = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_VARIANTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                variants.add(mapVariant(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return variants;
    }

    @Override
    public boolean updateStock(int variantId, int stockQuantity) {

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STOCK)) {

            preparedStatement.setInt(1, stockQuantity);
            preparedStatement.setInt(2, variantId);

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
    public boolean isStockAvailable(int variantId, int requiredQuantity) {

        boolean available = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_STOCK)) {

            preparedStatement.setInt(1, variantId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int stock = resultSet.getInt("stock_quantity");

                if (stock >= requiredQuantity) {
                    available = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return available;
    }

    private ProductVariant mapVariant(ResultSet resultSet) throws Exception {

        ProductVariant productVariant = new ProductVariant();

        productVariant.setVariantId(resultSet.getInt("variant_id"));
        productVariant.setProductId(resultSet.getInt("product_id"));
        productVariant.setSize(resultSet.getString("size"));
        productVariant.setStock(resultSet.getInt("stock"));
        productVariant.setVariantPrice(resultSet.getBigDecimal("variant_price"));

        return productVariant;
    }
}