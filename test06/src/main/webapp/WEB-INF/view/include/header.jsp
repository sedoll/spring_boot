<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path2" value="<%=request.getContextPath() %>"/>
<header>
    <c:if test="${slev eq 'ADMIN'}">
        <a href="${path2}/user/userList.do">회원정보보기</a>
    </c:if>
    <c:if test="${not empty sid}">
        <a href="${path2}/user/getUser.do?id=${sid}">내정보보기</a>
    </c:if>
    <c:if test="${empty sid}">
        <a href="${path2}/user/loginForm.do">로그인</a>
        <a href="${path2}/user/insertForm.do">회원가입</a>
    </c:if>
    <c:if test="${not empty sid}">
        <a href="${path2}/user/logout.do">로그아웃</a>
    </c:if>
</header>
