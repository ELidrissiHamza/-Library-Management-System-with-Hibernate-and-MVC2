
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Books Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<%
    String user= null;
    if(session.getAttribute("username")==null)
    {
        response.sendRedirect("login.jsp");
    }
    else  user= (String) session.getAttribute("username");
%>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: blue">
        <div>
            <a href="https://github.com/ELidrissiHamza" class="navbar-brand"> Welcome <%=user%></a>
            <a href="https://github.com/ELidrissiHamza" class="navbar-brand"> Books
                Management Application </a>
        </div>

        <ul class="navbar-nav">

            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Books</a></li>
            <li><form class="nav-link" action="logout"> <input style="float: right;" type="submit" value="logout" > </form>
            </li>

        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Books</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New Book</a>
            <div class="input-group mb-1 mt-3">
                <form action="retrieve">
                    <div style="display: flex; flex-wrap: wrap;">
                        <div style="flex: 1; padding-right: 10px;">
                            <input  type="text" name="isbn" class="form-control" placeholder="LIVRE ISBN" aria-label="Recipient's username" aria-describedby="button-addon2">
                        </div>
                        <div style="flex: 0 0 150px;">
                            <button class="btn btn-outline-success" type="submit" id="button-addon2">Retrieve</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ISBN</th>
                <th>TITLE</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="livre" items="${listLivre}">

                <tr>
                    <td><c:out value="${livre.isbn}" /></td>
                    <td><c:out value="${livre.titre}" /></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>
</body>
</html>