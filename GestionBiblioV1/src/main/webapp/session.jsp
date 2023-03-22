<%
  String user= (String) session.getAttribute("username");
  if(session.getAttribute("username")==null)
  {
    response.sendRedirect("login.jsp");
  }
%>