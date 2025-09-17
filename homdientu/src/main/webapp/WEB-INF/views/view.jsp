<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head><title>Settings</title></head>
<body>
<h2>Settings</h2>
<p><b>Language:</b> ${settings.language}</p>
<p><b>Page Size:</b> ${settings.pageSize} emails/page</p>
<p><b>Spam Filter:</b> ${settings.spamFilter ? 'Enabled' : 'Disabled'}</p>
<p><b>Signature:</b><pre>${settings.signature}</pre></p>

<a href="${pageContext.request.contextPath}/settings/edit">Update</a>
</body>
</html>
