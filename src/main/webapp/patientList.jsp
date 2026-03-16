<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Patients:</h2>
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null)
    {
  %>
      <p style="color: red;"><%= errorMessage %></p>
  <%
    }
  %>
  <ul>
  <%
  List<String> patients = (List<String>) request.getAttribute("patientNames");

  if (patients != null)
  {
    for (int i = 0; i < patients.size(); i++)
    {
  %>
      <li>
        <a href="patientInfo?row=<%=i%>"><%=patients.get(i)%></a>
        |
        <a href="deletePatient?row=<%=i%>" style="color:red; font-size:0.8em;" onclick="return confirm('Are you sure you want to delete this patient?');">Delete</a>
      </li>
  <%
    }
  }
  %>
  </ul>
<a href="addPatient" style="display:inline-block; margin-bottom: 15px; font-weight: bold;">+ Add New Patient</a>
<br><br>
<a href="saveJson" style="padding: 10px; background-color: #eee; border: 1px solid #ccc; text-decoration: none;">Export to JSON</a>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
