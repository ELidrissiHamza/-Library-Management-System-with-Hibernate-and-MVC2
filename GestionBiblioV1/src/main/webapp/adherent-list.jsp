
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
<%@ include file="session.jsp" %>
<header>
    <%@ include file="navbar.jsp" %>

</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Adherents</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/newadherant" class="btn btn-success">Add
                New Adherent</a>
            <div class="input-group mb-1 mt-3">
                <form action="retrieveadherant">
                    <div style="display: flex; flex-wrap: wrap;">
                        <div style="flex: 1; padding-right: 10px;">
                            <input autocomplete="off" type="text" name="cin" class="form-control" placeholder="Adherent CIN" aria-label="Recipient's username" aria-describedby="button-addon2">
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
                <th>CIN</th>
                <th>Last Name</th>
                <th>First Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="Adherant" items="${listAdherant}">

                <tr>
                    <td><c:out value="${Adherant.cin}" /></td>
                    <td><c:out value="${Adherant.nom}" /></td>
                    <td><c:out value="${Adherant.prenom}" /></td>
                    <td><a href="editadherant?id=<c:out value='${Adherant.cin}' />" >Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="deleteadherant?id=<c:out value='${Adherant.cin}' />" >Delete</a></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>
</body>
</html>