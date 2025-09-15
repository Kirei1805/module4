<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Personal Calculator</title>
</head>
<body>
<h2>Simple Calculator</h2>
<form action="calculate" method="post">
    Number 1: <input type="text" name="num1" required/><br/><br/>
    Number 2: <input type="text" name="num2" required/><br/><br/>
    Operator:
    <select name="operator">
        <option value="add">Addition (+)</option>
        <option value="sub">Subtraction (-)</option>
        <option value="mul">Multiplication (×)</option>
        <option value="div">Division (/)</option>
    </select>
    <br/><br/>
    <input type="submit" value="Calculate"/>
</form>
</body>
</html>
