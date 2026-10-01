package com.fashionstore.util;

import java.math.BigDecimal;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.CartItemDAO;
import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.OrderItemDAO;
import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.ProductVariantDAO;
import com.fashionstore.dao.UserDAO;
import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.dao.impl.CartItemDAOImpl;
import com.fashionstore.dao.impl.CategoryDAOImpl;
import com.fashionstore.dao.impl.OrderDAOImpl;
import com.fashionstore.dao.impl.OrderItemDAOImpl;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.dao.impl.ProductVariantDAOImpl;
import com.fashionstore.dao.impl.UserDAOImpl;



import com.fashionstore.model.Cart;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.Category;
import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductVariant;
import com.fashionstore.model.User;

public class DAOTest {

	public static void main(String[] args) {

		// =========================
		// USER DAO TEST
		// =========================

		System.out.println("========== USER DAO TEST ==========");

		UserDAO userDAO = new UserDAOImpl();

		User user = userDAO.getUserById(1);

		if(user != null) {

			System.out.println(user.getUserId());
			System.out.println(user.getName());
			System.out.println(user.getEmail());
		}

		List<User> users = userDAO.getAllUsers();

		for(User u : users) {

			System.out.println(
					u.getUserId() + " | " +
					u.getName() + " | " +
					u.getEmail()
					);
		}



		// =========================
		// CATEGORY DAO TEST
		// =========================

		System.out.println("\n========== CATEGORY DAO TEST ==========");

		CategoryDAO categoryDAO = new CategoryDAOImpl();

		Category category = categoryDAO.getCategoryById(1);

		if(category != null) {

			System.out.println(category.getCategoryId());
			System.out.println(category.getCategoryName());
		}

		List<Category> categories = categoryDAO.getAllCategories();

		for(Category c : categories) {

			System.out.println(
					c.getCategoryId() + " | " +
					c.getCategoryName()
					);
		}



		// =========================
		// PRODUCT DAO TEST
		// =========================

		System.out.println("\n========== PRODUCT DAO TEST ==========");

		ProductDAO productDAO = new ProductDAOImpl();

		Product product = productDAO.getProductById(1);

		if(product != null) {

			System.out.println(product.getProductId());
			System.out.println(product.getProductName());
			System.out.println(product.getBasePrice());
		}

		List<Product> products = productDAO.getAllProducts();

		for(Product p : products) {

			System.out.println(
					p.getProductId() + " | " +
					p.getProductName() + " | " +
					p.getBasePrice()
					);
		}

		System.out.println("\nProducts Between 1000 and 2000");

		List<Product> filteredProducts =
				productDAO.getProductByPriceRange(
						new BigDecimal("1000"),
						new BigDecimal("2000")
						);

		for(Product p : filteredProducts) {

			System.out.println(
					p.getProductName() + " | " +
					p.getBasePrice()
					);
		}



		// =========================
		// PRODUCT VARIANT DAO TEST
		// =========================

		System.out.println("\n========== PRODUCT VARIANT DAO TEST ==========");

		ProductVariantDAO variantDAO = new ProductVariantDAOImpl();

		ProductVariant variant = variantDAO.getVariantById(1);

		if(variant != null) {

			System.out.println(variant.getVariantId());
			System.out.println(variant.getSize());
			System.out.println(variant.getStock());
		}

		List<ProductVariant> variants =
				variantDAO.getVariantsByProductId(1);

		for(ProductVariant pv : variants) {

			System.out.println(
					pv.getVariantId() + " | " +
					pv.getSize() + " | " +
					pv.getVariantPrice()
					);
		}



		// =========================
		// CART DAO TEST
		// =========================

		System.out.println("\n========== CART DAO TEST ==========");

		CartDAO cartDAO = (CartDAO) new CartDAOImpl();

		Cart cart = cartDAO.getCartByUserId(1);

		if(cart != null) {

			System.out.println(cart.getCartId());
			System.out.println(cart.getUserId());
		}



		// =========================
		// CART ITEM DAO TEST
		// =========================

		System.out.println("\n========== CART ITEM DAO TEST ==========");

		CartItemDAO cartItemDAO = new CartItemDAOImpl();

		List<CartItem> cartItems =
				cartItemDAO.getCartItemsByCartId(1);

		for(CartItem ci : cartItems) {

			System.out.println(
					ci.getCartItemId() + " | " +
					ci.getVariantId() + " | " +
					ci.getQuantity()
					);
		}

		BigDecimal total = cartItemDAO.getCartTotal(1);

		System.out.println("Cart Total = " + total);



		// =========================
		// ORDER DAO TEST
		// =========================

		System.out.println("\n========== ORDER DAO TEST ==========");

		OrderDAO orderDAO = new OrderDAOImpl();

		Order order = orderDAO.getOrderById(1);

		if(order != null) {

			System.out.println(order.getOrderId());
			System.out.println(order.getTotalAmount());
			System.out.println(order.getOrderStatus());
		}

		List<Order> orders = orderDAO.getOrdersByUserId(1);

		for(Order o : orders) {

			System.out.println(
					o.getOrderId() + " | " +
					o.getTotalAmount() + " | " +
					o.getOrderStatus()
					);
		}



		// =========================
		// ORDER ITEM DAO TEST
		// =========================

		System.out.println("\n========== ORDER ITEM DAO TEST ==========");

		OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

		List<OrderItem> orderItems =
				orderItemDAO.getOrderItemsByOrderId(1);

		for(OrderItem oi : orderItems) {

			System.out.println(
					oi.getOrderItemId() + " | " +
					oi.getVariantId() + " | " +
					oi.getQuantity() + " | " +
					oi.getPrice()
					);
		}



		System.out.println("\n========== ALL DAO TESTS COMPLETED ==========");
	}
}