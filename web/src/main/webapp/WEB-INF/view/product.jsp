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

<h2><fmt:message key="page.product.model"/> : ${product.model}.
    <fmt:message key="page.product.brand"/>: ${product.brand}.</h2>
<h2><fmt:message key="page.product.screenSize"/>: ${product.screen.screenSize},
    <fmt:message key="page.product.screenResolution"/>: ${product.screen.screenResolution}.</h2>
<h2><fmt:message key="page.product.ram"/>: ${product.ram},
    <fmt:message key="page.product.memorySize"/> ${product.memorySize}.</h2>
<h2><fmt:message key="page.product.price"/>: ${product.price}.</h2>
<h3><fmt:message key="page.product.description"/>: ${product.description}</h3>

<%@include file="footer.jsp" %>

<sec:authorize access="hasAuthority('ADMIN')">
    <form action="${pageContext.request.contextPath}/product/${product.id}/delete" method="post">
        <button>Delete</button>
    </form>
</sec:authorize>

<sec:authorize access="hasAuthority('ADMIN')">
    <form action="${pageContext.request.contextPath}/product/${product.id}/update" method="post">

        <label for="brandId">Model</label><br>
        <input type="text" id="brandId" name="brand" value="${product.brand}"><br>

        <label for="modelId">Model</label><br>
        <input type="text" id="modelId" name="model" value="${product.model}"><br>

        <label for="screenSizeId">Screen size</label><br>
        <input type="text" id="screenSizeId" name="screenSize" value="${product.screen.screenSize}"><br>

        <label for="screenResolutionId">Screen resolution</label><br>
        <input type="text" id="screenResolutionId" name="screenResolution"
               value="${product.screen.screenResolution}"><br>

        <label for="ramId">Ram</label><br>
        <input type="text" id="ramId" name="ram" value="${product.ram}"><br>

        <label for="memorySizeId">Memory size</label><br>
        <input type="text" id="memorySizeId" name="memorySize" value="${product.ram}"><br>

        <label for="priceId">Price</label><br>
        <input type="text" id="priceId" name="price" value="${product.price}"><br>

        <label for="descriptionId">Description</label><br>
        <input type="text" id="descriptionId" name="description" value="${product.description}"><br>

        <input type="submit" value="Update">
    </form>
</sec:authorize>

</body>
</html>