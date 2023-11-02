<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path2" value="${pageContext.request.contextPath}"/>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<header>
    <c:if test="${empty principal}">
        <button type="button" onclick="location.href='/user/join'" class="pure-button pure-button-primary">가입하기</button>
        <a href="${path2}/user/login" class="pure-button pure-button-primary">로그인</a>
    </c:if>
    <c:if test="${not empty principal}">
        <h2>${principal}</h2>
        <button type="button" onclick="location.href='/user/update?id=${principal}'" class="pure-button pure-button-primary">수정하기</button>
        <form action="/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button type="submit" class="pure-button pure-button-primary">로그아웃</button>
        </form>
        <form action="/delete" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button type="submit" class="pure-button pure-button-primary">탈퇴하기</button>
        </form>
    </c:if>
</header>
