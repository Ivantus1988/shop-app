<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="translations"/>

<head>
    <title>Create</title>
</head>

<body>
<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/product/create" method="post">

    <label for="modelId">Brand</label><br>
    <select name="brand">
        <option value="APPLE">Apple</option>
        <option value="SAMSUNG">Samsung</option>
        <option value="XIAOMI">Xiaomi</option>
        <option value="HONOR">Honor</option>
        <option value="HUAWEI">Huawei</option>
        <option value="NOKIA">Nokia</option>
        <option value="GOOGLE">Google</option>
    </select><br>

    <label for="modelId">Model</label><br>
    <input type="text" id="modelId" name="model"><br>

    <label for="screenSizeId">Screen size</label><br>
    <input type="text" id="screenSizeId" name="screenSize"><br>

    <label for="screenResolutionId">Screen resolution</label><br>
    <input type="text" id="screenResolutionId" name="screenResolution"><br>

    <label for="ramId">Ram</label><br>
    <input type="text" id="ramId" name="ram"><br>

    <label for="memorySizeId">Memory size</label><br>
    <input type="text" id="memorySizeId" name="memorySize"><br>

    <label for="priceId">Price</label><br>
    <input type="text" id="priceId" name="price"><br>

    <label for="descriptionId">Description</label><br>
    <input type="text" id="descriptionId" name="description"><br>

    <input type="submit" value="Submit">
</form>

<%@include file="footer.jsp" %>

</body>
</html>