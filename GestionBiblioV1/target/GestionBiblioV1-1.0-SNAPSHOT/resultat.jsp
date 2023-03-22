<%
  String message = request.getAttribute("msg").toString();
%>
<script>
  alert("<%=message%>");
</script>
