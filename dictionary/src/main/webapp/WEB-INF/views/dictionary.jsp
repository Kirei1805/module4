<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Từ điển Anh - Việt</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
<body>
<div class="container">
    <h1>Từ điển Anh - Việt</h1>
    <c:url var="searchUrl" value="/dictionary" />
    <form method="get" action="${searchUrl}">
        <label for="word">Nhập từ tiếng Anh:</label>
        <input id="word" name="word" type="text" value="${fn:escapeXml(word)}" placeholder="Ví dụ: hello" />
        <button type="submit">Tra cứu</button>
    </form>

    <c:if test="${not empty meaning}">
        <div class="result">
            <strong>Nghĩa:</strong>
            <span>${meaning}</span>
        </div>
    </c:if>

    <c:if test="${empty meaning && not empty message}">
        <div class="error">${message}</div>
    </c:if>

    <p style="margin-top:20px"><a href="/">Về trang chủ</a></p>
</div>
</body>
</html>

