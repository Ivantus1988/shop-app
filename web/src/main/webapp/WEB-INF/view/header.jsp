<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="translations"/>

<h5> Мобильные телефоны. </h5> <h5><a href="/products">На главную</a> Apple | Samsung | Xiaomi | Honor | Google</h5>
</div>
<c:if test="${sessionScope.user != null}">
    <form action="${pageContext.request.contextPath}/logout" method="get">
        <input type="submit" value="Logout">
    </form>

</c:if>

<%--<div id="locale">--%>
<%--    <form action="${pageContext.request.contextPath}/locale" method="post">--%>
<%--        <button type="submit" name="lang" value="ru_RU">RU</button>--%>
<%--        <button type="submit" name="lang" value="en_US">EN</button>--%>
<%--    </form>--%>

</div>
<%--<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'en_US')}"/>--%>
<%--<fmt:setBundle basename="translations"/>--%>
</div>
