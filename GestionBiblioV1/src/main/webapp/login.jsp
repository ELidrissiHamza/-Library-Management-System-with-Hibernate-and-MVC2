<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/03/2023
  Time: 09:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <form class="login-form" action="loginVerfy">
        <h1>Login</h1>
        <div class="input-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="input-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="input-group">
            <button type="submit">Login</button>
        </div>
    </form>
</div>
</body>
<style>
    * {
        box-sizing: border-box;
    }

    body {
        margin: 0;
        padding: 0;
        font-family: sans-serif;
        background-color: #f2f2f2;
    }

    .container {
        max-width: 500px;
        margin: 0 auto;
        padding: 50px 20px;
    }

    .login-form {
        background-color: #fff;
        padding: 30px;
        border-radius: 5px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }

    .login-form h1 {
        margin-top: 0;
        text-align: center;
    }

    .input-group {
        margin-bottom: 20px;
    }

    .input-group label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    .input-group input {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: none;
        background-color: #f7f7f7;
    }

    .input-group button {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: none;
        background-color: #4CAF50;
        color: #fff;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .input-group button:hover {
        background-color: #3e8e41;
    }

</style>
</html>
