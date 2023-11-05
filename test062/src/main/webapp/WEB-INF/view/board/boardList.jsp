<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path0" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../include/head.jsp"/>
    <title>게시판 목록</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="banner"></div>
<div class="l-content" style="width:1280px;margin:20px auto;">
    <h2 style="text-align: center;">게시판 목록</h2>
    <div class="search_from">
        <select name="select_filter" class="select_filter">
            <option value="0">번호</option>
            <option value="1">이름</option>
            <option value="2">작성자</option>
            <option value="3">조회수</option>
            <option value="4">게시일</option>
        </select>
        <input type="text" name="search_filter" class="search_filter">
    </div>
    <table class="table" id="myTable">
        <thead>
        <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">조회수</th>
            <th scope="col">게시일</th>
            <th scope="col">비고</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${boardList}" var="board" varStatus="status">
            <tr>
                <td scope="row">${status.count}</td>
                <td><a href="${path0}/common/getBoard?id=${board.id}">${board.title}</a></td>
                <td>${board.name}</td>
                <td>${board.cnt}</td>
                <td>${board.resdate}</td>
                <td></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${not empty principal}">
        <a href="${path0}/board/boardInsert?name=${principal}">게시글 작성</a>
    </c:if>
</div>
<div class="l-content">

</div>
<div class="footer l-box">
    <p>
        <a href="#">Try now</a> for 14 days. No credit card required. Header image courtesy of <a href="http://unsplash.com/">Unsplash</a>.
    </p>
</div>
<script src="${path0}/resource/js/datatables.js"></script>
</body>
</html>