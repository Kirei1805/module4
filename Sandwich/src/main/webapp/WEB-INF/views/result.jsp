<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>Selected Condiments</title>
</head>
<body>
<h2>Your Selected Condiments:</h2>
<c:if test="${empty condiments}">
  <p>No condiments selected.</p>
</c:if>
<c:if test="${not empty condiments}">
  <ul>
    <c:forEach var="c" items="${condiments}">
      <li>${c}</li>
    </c:forEach>
  </ul>
</c:if>
<br/>
<a href="/">Back</a>
</body>
</html>
