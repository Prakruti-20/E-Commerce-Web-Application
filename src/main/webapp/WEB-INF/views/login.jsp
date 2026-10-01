<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<style>
body {
    font-family: 'Poppins', sans-serif;
    background: #f5f5f5;
    margin: 0;
    padding: 0;
}

/* LOGIN BOX */
.login-container {
    width: 360px;
    margin: 100px auto;
    background: white;
    padding: 25px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.1);
}

/* TITLE */
.login-container h2 {
    text-align: center;
    margin-bottom: 20px;
}

/* INPUTS */
.login-container input {
    width: 100%;
    padding: 11px;
    margin-bottom: 12px;
    border: none;
    border-radius: 8px;
    background: #f3f4f6;
    outline: none;
}

/* BUTTON */
.login-btn {
    width: 100%;
    padding: 12px;
    background: #007bff;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
}

.login-btn:hover {
    background: #0056b3;
}

/* REGISTER TEXT */
.register-text {
    text-align: center;
    margin-top: 15px;
    font-size: 14px;
}

.register-text a {
    color: #28a745;
    text-decoration: none;
}

.register-text a:hover {
    text-decoration: underline;
}

/* ERROR */
.error {
    color: red;
    text-align: center;
    margin-bottom: 10px;
}
</style>

</head>

<body>

<%
String message = (String) session.getAttribute("message");

if (message != null) {
%>
<script>
    alert("<%= message %>");
</script>
<%
    session.removeAttribute("message");
}
%>

<div class="login-container">

    <h2>Login</h2>

    <!-- ERROR MESSAGE -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p class="error"><%= error %></p>
    <%
        }
    %>

    <!-- LOGIN FORM (IMPORTANT FIX HERE) -->
    <form action="<%= request.getContextPath() %>/login" method="post">

        <input type="email" name="email" placeholder="Enter Email" required>

        <input type="password" name="password" placeholder="Enter Password" required>

        <button type="submit" class="login-btn">Login</button>

    </form>

    <!-- REGISTER LINK -->
    <div class="register-text">
        Don't have an account?
        <a href="<%= request.getContextPath() %>/register">Register here</a>
    </div>

</div>

</body>
</html>