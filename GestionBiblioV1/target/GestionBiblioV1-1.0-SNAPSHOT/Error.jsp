<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <title>Error</title>
</head>
<body>
<center>
  <h1>Error</h1>
  <h2><%=exception.getMessage() %><br/> </h2>
  <h2><%=request.getParameter("err") %><br/> </h2>
  <a  href="<%=request.getContextPath()%>/list">Go Back</a>
</center>
</body>
<style>
  body {
    background-color: #f2f2f2;
    font-family: Arial, sans-serif;
  }

  center {
    margin-top: 100px;
  }

  h1 {
    font-size: 36px;
    color: #333333;
  }

  h2 {
    font-size: 24px;
    color: #666666;
  }

  a {
    display: block;
    margin-top: 30px;
    text-align: center;
    color: #ffffff;
    background-color: #337ab7;
    border-radius: 5px;
    padding: 10px 20px;
    text-decoration: none;
    font-size: 18px;
  }

  a:hover {
    background-color: #286090;
  }

</style>
</html>