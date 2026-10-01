# FashionStore - E-Commerce Web Application

A full-stack Java EE dynamic web application for online fashion retail, built using the MVC (Model-View-Controller) architecture, Jakarta Servlets, JSP/JSTL, and MySQL.

---

## 📌 Features

- **User Authentication**: Secure user registration, login, and session-managed logout.
- **Product Catalog**: Browse fashion products categorized by Men, Women, Kids, and Accessories.
- **Product Details & Variants**: View product details with size options (S, M, L, XL), dynamic pricing, and stock verification.
- **Shopping Cart**:
  - Add items with selected size variants
  - Increment/decrement quantities
  - Remove items or clear entire cart
  - Persistent cart stored in database per user
- **Checkout & Order Processing**:
  - Review order summary and shipping details
  - Choose payment methods (e.g., Cash on Delivery)
  - Order placement with automated inventory reduction
- **Order Confirmation**: Order success page with order breakdown and itemized details.

---

## 🛠️ Technology Stack

| Layer | Technology |
|---|---|
| **Backend Language** | Java (JDK 21) |
| **Web Framework / API** | Jakarta Servlet 6.0, JSP 3.1, JSTL 3.0 |
| **Server / Servlet Container** | Apache Tomcat 10+ |
| **Database** | MySQL 8.0+ |
| **Persistence / Architecture** | JDBC (DAO Pattern & Connection Pooling) |
| **Build Tool** | Apache Maven |
| **Frontend** | HTML5, CSS3, Vanilla JavaScript |

---

## 📂 Project Structure

```text
FashionStore/
├── pom.xml                               # Maven project dependencies & build configuration
├── schema.sql                            # Database schema and table definitions
├── src/
│   └── main/
│       ├── java/com/fashionstore/
│       │   ├── controller/               # Jakarta Servlets (Cart, Checkout, Home, Auth, Product, etc.)
│       │   ├── dao/                      # DAO Interfaces (CartDAO, ProductDAO, UserDAO, etc.)
│       │   ├── dao/impl/                 # JDBC implementations of DAOs
│       │   ├── model/                    # Domain models (User, Product, Cart, Order, etc.)
│       │   └── util/                     # Database connection helper & test utilities
│       └── webapp/
│           ├── assets/                   # CSS stylesheets
│           ├── images/                   # Product & UI images
│           ├── WEB-INF/
│           │   ├── views/                # JSP views (home, products, cart, checkout, login, etc.)
│           │   │   └── partials/         # Reusable JSP components (navbar, footer)
│           │   └── web.xml               # Web application deployment descriptor
│           └── index.jsp                 # Landing page redirector
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

1. **Java Development Kit (JDK 21+)** installed and configured in your `PATH`.
2. **Apache Tomcat 10.1+** installed.
3. **MySQL Server 8.0+** running locally.
4. **Eclipse IDE for Enterprise Java and Web Developers** or **IntelliJ IDEA Ultimate**.

---

### Database Setup

1. Open your MySQL client (MySQL Workbench, Command Line, or DBeaver).
2. Execute the included [`schema.sql`](schema.sql) file:
   ```sql
   SOURCE path/to/schema.sql;
   ```
3. Update database credentials if needed in `src/main/java/com/fashionstore/util/DBConnection.java` or pass environment variables:
   ```env
   DB_URL=jdbc:mysql://localhost:3306/fashion_store
   DB_USER=root
   DB_PASSWORD=your_mysql_password
   ```

---

### Running the Project

#### Using Eclipse IDE:
1. Open Eclipse and choose **File > Import... > Existing Maven Projects**.
2. Select the `FashionStore` directory.
3. Right-click the project > **Properties > Targeted Runtimes** and check **Apache Tomcat v10.1**.
4. Right-click the project > **Run As > Run on Server** and select Tomcat 10.1.
5. The application will launch at `http://localhost:8080/FashionStore/`.

#### Using Maven Command Line:
```bash
# Package the WAR file
mvn clean package

# Deploy the generated WAR file from target/FashionStore-0.0.1-SNAPSHOT.war to Tomcat's webapps directory.
```

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
