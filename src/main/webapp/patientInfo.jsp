<%@ page import="uk.ac.ucl.DataFrame" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>

<body>
<jsp:include page="/header.jsp"/>

<div class="main">

<h1>Patient Information</h1>

<%
String errorMessage = (String) request.getAttribute("errorMessage");
if (errorMessage != null)
{
%>
<p style="color:red;"><%= errorMessage %></p>
<%
}
else
{
DataFrame df = (DataFrame) request.getAttribute("df");
Integer rowObj = (Integer) request.getAttribute("row");

if (df != null && rowObj != null)
{
int row = rowObj;
ArrayList<String> columns = (ArrayList<String>) request.getAttribute("columnNames");
%>

<table border="1">
<tr>
<th>Field</th>
<th>Value</th>
</tr>

<%
for (String col : columns)
{
%>

<tr>
<td><%= col %></td>
<td><%= df.getValue(col, row) %></td>
</tr>

<%
}
%>

</table>

<%
}
}
%>

<br>
<a href="patientList">Back to patient list</a>

</div>

<jsp:include page="/footer.jsp"/>
</body>
</html>