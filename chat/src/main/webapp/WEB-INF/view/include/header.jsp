<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path1" value="${pageContext.request.contextPath}" />
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/> <%-- 아이디 가져오기 --%>
    <sec:authentication property="authorities" var="authorities"/> <%-- 인증권한 정보 가져오기 --%>
</sec:authorize>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${path1}/">Home</a>
            </li>
            <li class="nav-item">
                <a href="${path1}/common/boardList" class="nav-link">게시판</a>
            </li>

            <%-- 로그인 하지 않은 경우 --%>
            <c:if test="${empty principal}">
                <li class="nav-item">
                    <a href="${path1}/common/join" class="nav-link">회원가입</a>
                </li>
                <li class="nav-item">
                    <a href="${path1}/common/login" class="nav-link">로그인</a>
                </li>
            </c:if>

            <%-- 로그인을 한 경우 --%>
            <c:if test="${not empty principal}">
                <li class="nav-item active">아이디: ${principal}</li>
                <li class="nav-item active">직급: ${authorities}</li>
                <li class="nav-item">
                    <a href="${path1}/user/updateForm?name=${principal}" class="nav-link">수정하기</a>
                </li>
                <li class="nav-item">
                    <form action="${path1}/logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="submit" class="nav-link">로그아웃</button>
                    </form>
                </li>
                <li class="nav-item">
                    <a href="${path1}/user/withdraw?id=${principal}" class="nav-link">탈퇴하기</a>
                </li>
            </c:if>

            <%-- admin인 경우에 보여지도록 설정, jstl el --%>
            <c:if test="${authorities eq '[ADMIN]'}">
                <li class="nav-item"><a href="${path1}/admin/admIndex" class="nav-link">관리자 페이지</a></li>
            </c:if>
            <%-- admin인 경우에 보여지도록 설정, spring el --%>
            <%--        <sec:authorize access="hasAuthority('ADMIN')">--%>
            <%--            <li class="nav-item"><a href="${path1}/admin/admIndex" class="nav-link">관리자 페이지</a></li>--%>
            <%--        </sec:authorize>--%>

            <%-- emp인 경우에 보여지도록 설정, jstl el --%>
            <c:if test="${authorities eq '[EMP]'}">
                <li class="nav-item"><a href="${path1}/emp/empIndex" class="nav-link">직원 페이지</a></li>
            </c:if>
            <%-- emp인 경우에 보여지도록 설정  spring el--%>
            <%--        <sec:authorize access="hasAuthority('EMP')">--%>
            <%--            <li class="nav-item"><a href="${path1}/emp/empIndex" class="nav-link">직원 페이지</a></li>--%>
            <%--        </sec:authorize>--%>

            <%-- user인 경우에 보여지도록 설정, jstl el --%>
            <c:if test="${authorities eq '[USER]'}">
                <li class="nav-item"><a href="${path1}/user/userIndex" class="nav-link">마이 페이지</a></li>
            </c:if>
            <%-- emp인 경우에 보여지도록 설정  spring el--%>
            <%--        <sec:authorize access="hasAuthority('EMP')">--%>
            <%--            <li class="nav-item"><a href="${path1}/emp/empIndex" class="nav-link">직원 페이지</a></li>--%>
            <%--        </sec:authorize>--%>
        </ul>
    </div>
</nav>