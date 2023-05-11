<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="translations"/>

<head>
    <title>Catalog</title>
</head>

<body>
<%@ include file="header.jsp" %>

<h1><fmt:message key="page.products.hello"/> ${sessionScope.user.name} ${sessionScope.user.surname}.</h1>

<c:forEach var="product" items="${requestScope.products}">
    <h2><fmt:message key="page.products.brand"/> : ${product.brand}, <fmt:message key="page.products.model" /> : ${product.model}.</h2>
    <h5><a href=${pageContext.request.contextPath}/products?id=${product.id}><fmt:message key="page.products.moreDetailed" /> </a></h5>
</c:forEach>

<%@include file="footer.jsp" %>

</body>
</html>