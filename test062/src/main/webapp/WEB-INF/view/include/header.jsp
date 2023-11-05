<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path1" value="${pageContext.request.contextPath}" />
<sec:authorize access="isAuthenticated()"> <%-- 로그인 확인 --%>
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<div class="pure-menu pure-menu-horizontal">
    <a href="${path1}/" class="pure-menu-heading">Your Logo</a>
    <ul class="pure-menu-list">
        <c:if test="${empty principal}"> <%-- 로그인 하지 않은 경우 --%>
            <li class="pure-menu-item"><a href="${path1}/common/join" class="pure-menu-link">회원가입</a></li>
            <li class="pure-menu-item"><a href="${path1}/common/login" class="pure-menu-link">로그인</a></li>
        </c:if>
        <c:if test="${not empty principal}"> <%-- 로그인을 한 경우 --%>
            <li class="pure-menu-item">${principal}</li>
            <li class="pure-menu-item"><a href="${path1}/user/updateForm?id=${principal}" class="pure-menu-link">수정하기</a></li>
            <li class="pure-menu-item">
                <form action="${path1}/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button type="submit" class="pure-menu-link">로그아웃</button>
                </form>
            </li>
            <li class="pure-menu-item"><a href="${path1}/user/withdraw?id=${principal}" class="pure-menu-link">탈퇴하기</a></li>
        </c:if>
        <li class="pure-menu-item"><a href="${path1}/admin/admIndex" class="pure-menu-link">관리자 페이지</a></li>
<%--        <li class="pure-menu-item"><a href="${path1}" class="pure-menu-link">Home</a></li>--%>
<%--        <li class="pure-menu-item pure-menu-selected"><a href="${path1}" class="pure-menu-link">Pricing</a></li>--%>
<%--        <li class="pure-menu-item"><a href="${path1}" class="pure-menu-link">Contact</a></li>--%>
    </ul>
</div>