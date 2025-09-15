
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Result</title>
</head>
<body>
<h2>Calculation Result</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<c:if test="${empty error}">
    <p>
            ${num1}
        <c:choose>
            <c:when test="${operator == 'add'}"> + </c:when>
            <c:when test="${operator == 'sub'}"> - </c:when>
            <c:when test="${operator == 'mul'}"> × </c:when>
            <c:when test="${operator == 'div'}"> / </c:when>
        </c:choose>
            ${num2} = <b>${result}</b>
    </p>
</c:if>

<br/>
<a href="/">Back to Calculator</a>
</body>
</html>

