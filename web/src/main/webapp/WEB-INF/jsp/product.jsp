<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Телефон</title>
</head>
<body>
<%@ include file="header.jsp" %>


<c:forEach var="product" items="${requestScope.products}">
  <h2> Модель: ${product.model} Бренд: ${product.brand}</h2>
  <h2>Цена: ${product.price}</h2>
</c:forEach>

<%@include file="footer.jsp" %>

</body>
</html>
