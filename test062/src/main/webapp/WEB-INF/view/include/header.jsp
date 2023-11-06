<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path1" value="${pageContext.request.contextPath}" />
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/> <%-- 아이디 가져오기 --%>
    <sec:authentication property="authorities" var="authorities"/> <%-- 인증권한 정보 가져오기 --%>
</sec:authorize>

<div class="pure-menu pure-menu-horizontal">
    <a href="${path1}/" class="pure-menu-heading">Your Logo</a>
    <ul class="pure-menu-list">

        <li class="pure-menu-item"><a href="${path1}/common/boardList" class="pure-menu-link">게시판</a></li>

        <%-- 로그인 하지 않은 경우 --%>
        <c:if test="${empty principal}">
            <li class="pure-menu-item"><a href="${path1}/common/join" class="pure-menu-link">회원가입</a></li>
            <li class="pure-menu-item"><a href="${path1}/common/login" class="pure-menu-link">로그인</a></li>
        </c:if>

        <%-- 로그인을 한 경우 --%>
        <c:if test="${not empty principal}">
            <li class="pure-menu-item">아이디: ${principal}</li>
            <li class="pure-menu-item">직급: ${authorities}</li>
            <li class="pure-menu-item"><a href="${path1}/user/updateForm?name=${principal}" class="pure-menu-link">수정하기</a></li>
            <li class="pure-menu-item">
                <form action="${path1}/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button type="submit" class="pure-menu-link">로그아웃</button>
                </form>
            </li>
            <li class="pure-menu-item"><a href="${path1}/user/withdraw?id=${principal}" class="pure-menu-link">탈퇴하기</a></li>
        </c:if>

        <%-- admin인 경우에 보여지도록 설정, jstl el --%>
        <c:if test="${authorities eq '[ADMIN]'}">
            <li class="pure-menu-item"><a href="${path1}/admin/admIndex" class="pure-menu-link">관리자 페이지</a></li>
        </c:if>
        <%-- admin인 경우에 보여지도록 설정, spring el --%>
<%--        <sec:authorize access="hasAuthority('ADMIN')">--%>
<%--            <li class="pure-menu-item"><a href="${path1}/admin/admIndex" class="pure-menu-link">관리자 페이지</a></li>--%>
<%--        </sec:authorize>--%>

        <%-- emp인 경우에 보여지도록 설정, jstl el --%>
        <c:if test="${authorities eq '[EMP]'}">
            <li class="pure-menu-item"><a href="${path1}/emp/empIndex" class="pure-menu-link">관리자 페이지</a></li>
        </c:if>
        <%-- emp인 경우에 보여지도록 설정  spring el--%>
<%--        <sec:authorize access="hasAuthority('EMP')">--%>
<%--            <li class="pure-menu-item"><a href="${path1}/emp/empIndex" class="pure-menu-link">직원 페이지</a></li>--%>
<%--        </sec:authorize>--%>
    </ul>
</div>