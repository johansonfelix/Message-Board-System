<%--
  Created by IntelliJ IDEA.
  User: felix
  Date: 2020-11-15
  Time: 5:43 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="css/stylesheet.css">
    <link rel="stylesheet" href="css/login.css">

    <title>MessageBoard | Login</title>
</head>

<body>
<div class="wrapper fadeInDown">
    <div id="formContent">


        <!-- Logo -->
        <div class="logo fadeIn first">
            <img src="logo.png" id="icon" alt="logo" />
        </div>
        <%String message =(String) request.getAttribute("message");
        if(message!=null) {%>
        <p style="color:brown;"><%=message%></p>
        <%}%>

        <!-- Login Form -->
        <form action="LoginServlet" method="post">
            <input type="email" id="email" class="fadeIn second" name="email" placeholder="Email">
            <input type="text" id="password" class="fadeIn third" name="password" placeholder="Password">
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>

        <!-- Remind Passowrd -->
        <div id="formFooter">
            <a class="underlineHover" href="#">Forgot Password?</a>
        </div>

    </div>
</div>
</body>

</html>