<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/03/2023
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>

<nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
  <div>
    <a href="https://github.com/ELidrissiHamza" class="navbar-brand"> Welcome <%=user%></a>
    <a href="<%=request.getContextPath()%>/list" class="navbar-brand"> Books Management </a>
    <a href="<%=request.getContextPath()%>/listadherent" class="navbar-brand"> Adherents Management </a>
  </div>
  <a href="<%=request.getContextPath()%>/listemprunt" class="navbar-brand"> Emprunts Management </a>

  <ul class="navbar-nav ml-auto">
    <li>
      <form class="nav-link" action="logout">
        <input type="submit" value="logout" class="btn btn-primary">
      </form>
    </li>
  </ul>
</nav>
