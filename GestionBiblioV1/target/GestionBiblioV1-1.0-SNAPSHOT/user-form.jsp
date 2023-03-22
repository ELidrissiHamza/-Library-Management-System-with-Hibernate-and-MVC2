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
            <%
                boolean verif = false; //
            %>
        <c:if test="${user != null}">
           <form action="update" method="post">
                       <%
                           verif = true; //
                        %>
                </c:if>
                <c:if test="${user == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit Book
                            </c:if>
                            <c:if test="${user == null}">
                                Add New Book
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input autocomplete="off" type="hidden" name="id" value="<c:out value='${user.isbn}'/> " />
                    </c:if>

                    <fieldset class="form-group">
                        <label>ISBN BOOK</label> <input type="text"
                                                        value="<c:out value='${user.isbn}' />" class="form-control"
                                                        name="name" required="required" <% if (verif) { %> disabled <% } %>>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>TITLE BOOK</label> <input type="text" autocomplete="off"
                                                         value="<c:out value='${user.titre}' />" class="form-control"
                                                         name="email">
                    </fieldset>
                        <fieldset class="form-group">
                            <label>N°Exemplaire</label> <input type="text" autocomplete="off"
                                                             value="<c:out value='' />" class="form-control"
                                                             name="nbrex">
                        </fieldset>


                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>