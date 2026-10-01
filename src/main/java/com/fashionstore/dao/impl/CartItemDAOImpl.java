package com.fashionstore.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CartItemDAO;
import com.fashionstore.model.CartItem;
import com.fashionstore.util.DBConnection;

public class CartItemDAOImpl implements CartItemDAO {

    private static final String INSERT =
            "INSERT INTO cart_items(cart_id, variant_id, quantity) VALUES(?,?,?)";

    private static final String SELECT_BY_ID =
            "SELECT cart_item_id, cart_id, variant_id, quantity FROM cart_items WHERE cart_item_id=?";

    private static final String SELECT_BY_CART_VARIANT =
            "SELECT cart_item_id, cart_id, variant_id, quantity FROM cart_items WHERE cart_id=? AND variant_id=?";

    private static final String SELECT_BY_CART =
            "SELECT cart_item_id, cart_id, variant_id, quantity FROM cart_items WHERE cart_id=?";

    private static final String UPDATE_BY_ID =
            "UPDATE cart_items SET quantity=? WHERE cart_item_id=?";

    private static final String UPDATE_BY_CART_VARIANT =
            "UPDATE cart_items SET quantity=? WHERE cart_id=? AND variant_id=?";

    private static final String DELETE_BY_ID =
            "DELETE FROM cart_items WHERE cart_item_id=?";

    private static final String DELETE_BY_CART_VARIANT =
            "DELETE FROM cart_items WHERE cart_id=? AND variant_id=?";

    private static final String CLEAR =
            "DELETE FROM cart_items WHERE cart_id=?";

    private static final String COUNT =
            "SELECT COUNT(*) FROM cart_items WHERE cart_id=?";

    private static final String TOTAL =
            "SELECT SUM(quantity) FROM cart_items WHERE cart_id=?";

    // ================= INSERT =================
    @Override
    public boolean addCartItem(CartItem cartItem) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setInt(1, cartItem.getCartId());
            ps.setInt(2, cartItem.getVariantId());
            ps.setInt(3, cartItem.getQuantity());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= UPDATE =================
    @Override
    public boolean updateCartItemQuantity(int cartItemId, int quantity) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_BY_ID)) {

            ps.setInt(1, quantity);
            ps.setInt(2, cartItemId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCartItemQuantityByCartAndVariant(int cartId, int variantId, int quantity) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_BY_CART_VARIANT)) {

            ps.setInt(1, quantity);
            ps.setInt(2, cartId);
            ps.setInt(3, variantId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= DELETE =================
    @Override
    public boolean removeCartItem(int cartItemId) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_BY_ID)) {

            ps.setInt(1, cartItemId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeCartItemByCartAndVariant(int cartId, int variantId) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_BY_CART_VARIANT)) {

            ps.setInt(1, cartId);
            ps.setInt(2, variantId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean clearCart(int cartId) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(CLEAR)) {

            ps.setInt(1, cartId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= SELECT SINGLE =================
    @Override
    public CartItem getCartItemById(int cartItemId) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, cartItemId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return map(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public CartItem getCartItemByCartIdAndVariantId(int cartId, int variantId) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_CART_VARIANT)) {

            ps.setInt(1, cartId);
            ps.setInt(2, variantId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return map(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= SELECT LIST =================
    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {

        List<CartItem> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_CART)) {

            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= COUNT =================
    @Override
    public int getCartItemCount(int cartId) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(COUNT)) {

            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ================= TOTAL =================
    @Override
    public BigDecimal getCartTotal(int cartId) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(TOTAL)) {

            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }

    // ================= MAPPER =================
    private CartItem map(ResultSet rs) throws Exception {

        CartItem item = new CartItem();

        item.setCartItemId(rs.getInt("cart_item_id"));
        item.setCartId(rs.getInt("cart_id"));
        item.setVariantId(rs.getInt("variant_id"));
        item.setQuantity(rs.getInt("quantity"));

        return item;
    }
}