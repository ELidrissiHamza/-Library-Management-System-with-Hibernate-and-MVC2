<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/03/2023
  Time: 01:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
                <form action="insertemprunt" method="post">
                    <caption>
                        <h2>
                                Add New Emprunt
                        </h2>
                    </caption>



                        <label>CIN ADHERENT</label>
                        <select class="form-select form-select-lg mb-3" name="cin" aria-label=".form-select-lg example">
                            <option selected>CIN Adherent</option>
                            <c:forEach var="adherent" items="${listAdherant}">
                            <option value="${adherent.cin}" ><c:out value="${adherent.cin}" /></option>
                            </c:forEach>
                        </select>


                    <fieldset class="form-group">
                        <label>ISBN BOOK</label>
                        <select class="form-select" name="isbn" aria-label="Default select example">
                            <option selected>ISBN BOOK</option>
                            <c:forEach var="livre" items="${listLivre}">
                                <option value="${livre.isbn}" ><c:out value="${livre.isbn}" /></option>
                            </c:forEach>
                        </select>
                    </fieldset>




                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
<style>
    .container {
        margin-top: 50px;
        background-color: #F5F5F5;
        padding: 20px;
        border-radius: 10px;
    }

    .card {
        border: none;
    }

    .card-body {
        text-align: center;
    }

    form {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    h2 {
        color: #007BFF;
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 20px;
    }

    label {
        color: #212529;
        font-weight: bold;
    }

    select {
        margin-bottom: 20px;
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: 1px solid #ced4da;
    }

    button {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: none;
        color: #fff;
        background-color: #28A745;
        font-weight: bold;
        transition: all 0.3s ease;
    }

    button:hover {
        background-color: #218838;
        cursor: pointer;
    }

</style>
</html>
