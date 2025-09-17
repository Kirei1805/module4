<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head><title>Edit Settings</title></head>
<body>
<h2>Settings</h2>

<form:form method="post" action="${pageContext.request.contextPath}/settings/save"
           modelAttribute="emailSettings">
    <p>
        <form:label path="language">Languages</form:label>
        <form:select path="language" items="${languages}"/>
    </p>

    <p>
        <form:label path="pageSize">Page Size</form:label>
        Show <form:select path="pageSize" items="${pageSizes}"/> emails per page
    </p>

    <p>
        <form:checkbox path="spamFilter"/> Enable spams filter
    </p>

    <p>
        <form:label path="signature">Signature</form:label><br/>
        <form:textarea path="signature" rows="4" cols="40"/>
    </p>

    <input type="submit" value="Update"/>
    <a href="${pageContext.request.contextPath}/settings">Cancel</a>
</form:form>

</body>
</html>
