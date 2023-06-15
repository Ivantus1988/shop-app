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


<form action="${pageContext.request.contextPath}/products" method="get">
    <label for="pageId"><fmt:message key="page.products.selectNumberPages"/></label><br>
    <input type="text" id="pageId" name="page"><br>

    <p1><fmt:message key="page.products.quantityPositions"/></p1>
    <select name="limit">
        <option value="2">2</option>
        <option value="4">4</option>
        <option value="10" selected>10</option>
    </select>

    <p1><fmt:message key="page.products.selectMaxPrice"/></p1>
    <select name="price">
        <option value="400">400</option>
        <option value="800">800</option>
        <option value="1200">1200</option>
        <option value="1600">1600</option>
        <option value="2000">2000</option>
        <option value="3000" selected>3000</option>
    </select>
    <br>

    <p1><fmt:message key="page.products.selectMemorySize"/></p1>
    <select name="memory_size">
        <option value="32">32</option>
        <option value="64">64</option>
        <option value="128">128</option>
        <option value="256">256</option>
        <option value="512">512</option>
        <option value="1000" selected>1000</option>
    </select>

    <p1><fmt:message key="page.products.selectRamSize"/></p1>
    <select name="ram">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="4">4</option>
        <option value="8">8</option>
        <option value="16">16</option>
        <option value="32" selected>32</option>
    </select>
    <br>

    <input type="submit" value="Submit">
</form>


<c:forEach var="product" items="${requestScope.products}">
    <h2><fmt:message key="page.products.brand"/> : ${product.brand}, <fmt:message key="page.products.model"/>
        : ${product.model}.</h2>
    <h5><a href=${pageContext.request.contextPath}/products?id=${product.id}><fmt:message
            key="page.products.moreDetailed"/> </a></h5>
</c:forEach>

<%@include file="footer.jsp" %>

</body>
</html>