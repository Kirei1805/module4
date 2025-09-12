<%--
  Created by IntelliJ IDEA.
  User: Lợi
  Date: 9/12/2025
  Time: 9:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Currency Converter</h2>
<form action="convert" method="get">
    Số tiền: <input type="text" name="amount"><br><br>

    Từ:
    <select name="from">
        <option value="USD">USD</option>
        <option value="VND">VND</option>
        <option value="EUR">EUR</option>
    </select>

    Đến:
    <select name="to">
        <option value="USD">USD</option>
        <option value="VND">VND</option>
        <option value="EUR">EUR</option>
    </select>

    <br><br>
    <button type="submit">Convert</button>
</form>
</body>
</html>

