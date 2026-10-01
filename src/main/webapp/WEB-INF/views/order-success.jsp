<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Confirmed</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
      rel="stylesheet">

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Poppins', sans-serif;
}

body{
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
    background:linear-gradient(135deg,#f5f7fa,#e4efe9);
    overflow:hidden;
}

/* MAIN CARD */

.success-card{
    width:450px;
    background:white;
    padding:45px 35px;
    border-radius:25px;
    text-align:center;
    box-shadow:0 10px 30px rgba(0,0,0,0.12);
    animation:popup 0.5s ease;
    position:relative;
}

/* SUCCESS ICON */

.success-icon{
    width:120px;
    height:120px;
    margin:auto;
    border-radius:50%;
    background:linear-gradient(135deg,#28c76f,#20b15a);
    display:flex;
    justify-content:center;
    align-items:center;
    color:white;
    font-size:65px;
    box-shadow:0 10px 20px rgba(40,199,111,0.3);
}

/* TITLE */

.success-title{
    margin-top:25px;
    font-size:34px;
    font-weight:700;
    color:#1f2937;
}

/* MESSAGE */

.success-message{
    margin-top:15px;
    font-size:15px;
    color:#6b7280;
    line-height:1.8;
}

/* ORDER INFO */

.order-info{
    margin-top:30px;
    background:#f9fafb;
    border-radius:15px;
    padding:18px;
    text-align:left;
}

.order-row{
    display:flex;
    justify-content:space-between;
    margin-bottom:12px;
    font-size:14px;
}

.order-row:last-child{
    margin-bottom:0;
}

.label{
    color:#6b7280;
    font-weight:500;
}

.value{
    color:#111827;
    font-weight:600;
}

/* BUTTON */

.home-btn{
    display:inline-block;
    margin-top:30px;
    padding:14px 28px;
    border-radius:12px;
    text-decoration:none;
    background:linear-gradient(135deg,#28c76f,#20b15a);
    color:white;
    font-weight:600;
    transition:0.3s;
}

.home-btn:hover{
    transform:translateY(-2px);
    box-shadow:0 8px 18px rgba(40,199,111,0.3);
}

/* FLOATING DOTS */

.dot{
    position:absolute;
    border-radius:50%;
    background:#28c76f33;
}

.dot1{
    width:14px;
    height:14px;
    top:20px;
    left:25px;
}

.dot2{
    width:20px;
    height:20px;
    bottom:25px;
    right:30px;
}

.dot3{
    width:10px;
    height:10px;
    top:80px;
    right:50px;
}

/* ANIMATION */

@keyframes popup{

    from{
        opacity:0;
        transform:scale(0.7);
    }

    to{
        opacity:1;
        transform:scale(1);
    }
}

/* RESPONSIVE */

@media(max-width:500px){

    .success-card{
        width:90%;
        padding:35px 25px;
    }

    .success-title{
        font-size:28px;
    }
}

</style>

</head>

<body>

<div class="success-card">

    <!-- FLOATING DOTS -->

    <div class="dot dot1"></div>
    <div class="dot dot2"></div>
    <div class="dot dot3"></div>

    <!-- SUCCESS ICON -->

    <div class="success-icon">
        <!--
Source - https://stackoverflow.com/q/658044
Posted by Vlad Gudim, modified by community. See post 'Timeline' for change history
Retrieved 2026-05-14, License - CC BY-SA 3.0
-->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
&#10004;
</body>
</html>
        
    </div>

    <!-- TITLE -->

    <h1 class="success-title">
        Order Confirmed!
    </h1>

    <!-- MESSAGE -->

    <p class="success-message">

        Thank you for shopping with FashionStore.
        Your order has been placed successfully
        and will be delivered soon.

    </p>

    <!-- ORDER DETAILS -->

    <div class="order-info">

        <div class="order-row">

            <span class="label">
                Status
            </span>

            <span class="value">
                Confirmed
            </span>

        </div>

        <div class="order-row">

            <span class="label">
                Payment
            </span>

            <span class="value">
                Successful
            </span>

        </div>

        <div class="order-row">

            <span class="label">
                Delivery
            </span>

            <span class="value">
                3 - 5 Days
            </span>

        </div>

    </div>

    <!-- BUTTON -->

    <a href="<%= request.getContextPath() %>/home"
       class="home-btn">

       Continue Shopping

    </a>

</div>

</body>
</html>