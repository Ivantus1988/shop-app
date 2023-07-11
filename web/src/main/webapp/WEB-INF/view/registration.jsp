<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="translations"/>

<html>
<head>
    <title>Registration</title>
</head>
<body>

<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="name"><fmt:message key="page.login.name" />:</label><br>
    <input type="text" id="name" name="name"><br>

    <label for="surname"><fmt:message key="page.login.surname" />:</label><br>
    <input type="text" id="surname" name="surname"><br>

    <label for="email"><fmt:message key="page.login.email" />:</label><br>
    <input type="email" id="email" name="email"><br>

    <label for="password"><fmt:message key="page.login.password" />:</label><br>
    <input type="password" id="password" name="password"><br>

    <label for="gender"><fmt:message key="page.login.gender" />:</label><br>

    <select name="gender" id="gender">
        <option value="MALE"><fmt:message key="page.login.gender.male"/> </option>
        <option value="FEMALE"><fmt:message key="page.login.gender.female"/> </option>
    </select>

    <label for="city"><fmt:message key="page.login.password" />:</label><br>
    <input type="text" id="city" name="city"><br>

    <label for="street"><fmt:message key="page.login.password" />:</label><br>
    <input type="text" id="street" name="street"><br>

    <label for="building"><fmt:message key="page.login.password" />:</label><br>
    <input type="text" id="building" name="building"><br>

    <label for="flat"><fmt:message key="page.login.password" />:</label><br>
    <input type="text" id="flat" name="flat"><br>

    <button type="submit"><fmt:message key="page.login.registration" /></button>
</form>

<%@include file="footer.jsp" %>

</body>
</html>