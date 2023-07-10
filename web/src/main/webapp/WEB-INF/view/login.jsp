<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="translations"/>
<head>
    <title>Login</title>
</head>
<body>
<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/login" method="post">

    <label for="email"><fmt:message key="page.login.email"/>:
        <input type="text" name="email" id="email" required>
    </label> <br>

    <label for="password"><fmt:message key="page.login.password"/>:
        <input type="password" name="password" id="password" required>
    </label> <br>

    <button type="submit"><fmt:message key="page.login.submit"/></button>

    <a href="${pageContext.request.contextPath}/registration">
        <button type="button"><fmt:message key="page.login.register.button"/></button>
    </a>
</form>

<c:if test="${param.error == true}">
    <div style="color: crimson">
        <fmt:message key="page.login.error"/>
    </div>
</c:if>

<%@include file="footer.jsp" %>

</body>
</html>