<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path0" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/> <%-- 아이디 가져오기 --%>
    <sec:authentication property="authorities" var="authorities"/> <%-- 인증권한 정보 가져오기 --%>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../include/head.jsp"/>
    <title>게시판 상세</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="banner"></div>
<div class="l-content" style="width:1280px;margin:20px auto;">
    <h2 style="text-align: center;">게시판 상세</h2>
    <table class="table" id="myTable">
        <tbody>
            <tr>
                <th>번호</th>
                <td>${board.id}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${board.name}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${board.title}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${board.content}</td>
            </tr>
            <tr>
                <th>조회수</th>
                <td>${board.cnt}</td>
            </tr>
            <tr>
                <th>게시일</th>
                <td>${board.resdate}</td>
            </tr>
            <tr>
                <th>비고</th>
                <c:if test="${principal eq board.name or authorities eq '[ADMIN]'}">
                    <td><a href="${path0}/board/boardUpdate?id=${board.id}">수정</a></td>
                    <td><a href="${path0}/board/boardDelete?id=${board.id}">삭제</a></td>
                </c:if>
            </tr>
        </tbody>
    </table>
</div>
<div class="l-content">

</div>
<div class="footer l-box">
    <p>
        <a href="#">Try now</a> for 14 days. No credit card required. Header image courtesy of <a href="http://unsplash.com/">Unsplash</a>.
    </p>
</div>
</body>
</html>