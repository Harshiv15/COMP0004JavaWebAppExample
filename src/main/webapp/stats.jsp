<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Statistics</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h1>Dashboard Statistics</h1>

  <h3>Oldest Patient</h3>
  <p><%= request.getAttribute("oldest") %></p>

  <h3>Patients Per City</h3>
  <ul>
    <%
       Map<String, Integer> cityStats = (Map<String, Integer>) request.getAttribute("cityStats");
       if(cityStats != null) {
           for(Map.Entry<String, Integer> entry : cityStats.entrySet()) {
    %>
               <li><strong><%= entry.getKey() %>:</strong> <%= entry.getValue() %></li>
    <%     }
       }
    %>
  </ul>

  <h3>Patients Born On The Same Day</h3>
    <ul>
      <%
         Map<String, Integer> sameBirthdays = (Map<String, Integer>) request.getAttribute("sameBirthdays");
         if(sameBirthdays != null) {
             for(Map.Entry<String, Integer> entry : sameBirthdays.entrySet()) {
      %>
                 <li><strong><%= entry.getKey() %>:</strong> <%= entry.getValue() %></li>
      <%     }
         }
      %>
    </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>