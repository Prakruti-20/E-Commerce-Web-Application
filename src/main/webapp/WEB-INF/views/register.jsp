<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<style>
body {
    font-family: 'Poppins', sans-serif;
    background: linear-gradient(135deg, #f5f5f5, #e9ecef);
    margin: 0;
    padding: 0;
}

/* FORM CONTAINER */
.register-container {
    width: 430px;
    margin: 40px auto;
    background: white;
    padding: 28px;
    border-radius: 14px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.10);
}

/* TITLE */
h2 {
    text-align: center;
    margin-bottom: 20px;
    font-weight: 600;
}

/* INPUTS */
input, textarea, select {
    width: 100%;
    padding: 11px;
    margin-bottom: 12px;
    border: none;
    border-radius: 10px;
    background: #f3f4f6;
    outline: none;
    transition: 0.3s;
    box-shadow: inset 0 1px 3px rgba(0,0,0,0.08);
}

input:focus, textarea:focus, select:focus {
    background: #fff;
    box-shadow: 0 0 6px rgba(0,123,255,0.25);
}

textarea {
    resize: none;
    height: 70px;
}

/* ✅ FIXED ROW (EQUAL SPACE LEFT & RIGHT) */
.row {
    display: flex;
    justify-content: space-between;
    gap: 14px;   /* equal gap between fields */
    margin-bottom: 12px;
}

/* EACH FIELD TAKES EQUAL SPACE */
.row input,
.row select {
    flex: 1;     /* equal width */
}

/* BUTTON */
.register-btn {
    width: 100%;
    padding: 12px;
    background: #28a745;
    color: white;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    font-weight: 500;
    transition: 0.3s;
}

.register-btn:hover {
    background: #218838;
}

/* LOGIN TEXT */
.login-text {
    text-align: center;
    margin-top: 15px;
    font-size: 14px;
}

.login-text a {
    color: #007bff;
    text-decoration: none;
}
</style>
</head>

<body>

<div class="register-container">

    <h2>Create Account</h2>

    <form action="${pageContext.request.contextPath}/register" method="post">

        <input type="text" name="name" placeholder="Full Name" required>

        <input type="email" name="email" placeholder="Email" required>

        <input type="password" name="password" placeholder="Password" required>
        
        <input type="text" name="phone" placeholder="Enter Phone" required>
        
        <input type="text" name="address" placeholder="Enter Address" required>
        <!-- CITY + STATE -->
        <div class="row">
            <input type="text" name="city" placeholder="City" required>
            <input type="text" name="state" placeholder="State" required>
        </div>

        <!-- PINCODE + COUNTRY -->
        <div class="row">
            <input type="text" name="pincode" placeholder="Pincode" required>

            <select name="country" required>
                <option value="">Country</option>
                <option value="India">India</option>
                <option value="USA">USA</option>
                <option value="UK">UK</option>
                <option value="Canada">Canada</option>
                <option value="Australia">Australia</option>
                <option value="Germany">Germany</option>
                <option value="Other">Other</option>
            </select>
        </div>

        <button type="submit" class="register-btn">Register</button>

    </form>

    <div class="login-text">
        Already have an account?
        <a href="${pageContext.request.contextPath}/login">Login here</a>
    </div>

</div>

</body>
</html>