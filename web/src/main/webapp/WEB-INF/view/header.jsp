<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
    <fmt:setLocale value="ru_RU"/>
    <fmt:setBundle basename="translations"/>

    <h5> Мобильные телефоны. </h5> <h5><a href="/products">На главную</a> Apple | Samsung | Xiaomi | Honor | Google</h5>
</div>
<sec:authorize access="isAuthenticated()">
    <b>Привет ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}</b>
    <form action="/logout" method="post">
        <input type="submit" value="Logout">
    </form>
</sec:authorize>

<%--<div id="locale">--%>
<%--    <form action="${pageContext.request.contextPath}/locale" method="post">--%>
<%--        <button type="submit" name="lang" value="ru_RU">RU</button>--%>
<%--        <button type="submit" name="lang" value="en_US">EN</button>--%>
<%--    </form>--%>

</div>
<%--<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'en_US')}"/>--%>
<%--<fmt:setBundle basename="translations"/>--%>
</div>
