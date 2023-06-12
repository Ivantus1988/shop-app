<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="translations"/>

<head>
    <title>${product.model}</title>
</head>

<body>
<%@ include file="header.jsp" %>

<h2> <fmt:message key="page.product.model" /> : ${product.model}.
    <fmt:message key="page.product.brand" />: ${product.brand}.</h2>
<h2><fmt:message key="page.product.screenSize" />: ${product.screen.screenSize},
    <fmt:message key="page.product.screenResolution" />: ${product.screen.screenResolution}.</h2>
<h2><fmt:message key="page.product.ram" />: ${product.ram},
    <fmt:message key="page.product.memorySize" /> ${product.memorySize}.</h2>
<h2><fmt:message key="page.product.price" />: ${product.price}.</h2>
<h3><fmt:message key="page.product.description" />: ${product.description}</h3>

<%@include file="footer.jsp" %>

</body>
</html>