<%@ page import="uk.ac.ucl.model.ModelFactory" %>
<%@ page import="uk.ac.ucl.model.Model" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Add New Patient</title>
</head>
<body>
<jsp:include page="/header.jsp"/>

<div class="main">
  <h1>Add New Patient</h1>

  <form method="POST" action="addPatient">
    <table>
    <%
      Model model = ModelFactory.getModel();
      List<String> columns = model.getDataFrame().getColumnNames();

      for(String col : columns) {
    %>
      <tr>
        <td><label for="<%= col %>"><strong><%= col %>:</strong></label></td>
        <td><input type="text" id="<%= col %>" name="<%= col %>" /></td>
      </tr>
    <% } %>
    </table>

    <br>
    <input type="submit" value="Save Patient" style="padding: 10px 20px; font-weight: bold;" />
  </form>

  <br>
  <a href="patientList">Cancel</a>
</div>

<jsp:include page="/footer.jsp"/>
</body>
</html>