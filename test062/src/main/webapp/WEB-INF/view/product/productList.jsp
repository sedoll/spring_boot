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
    <title>상품 목록</title>
</head>
<body>
<header class="header">

</header>
<jsp:include page="../include/header.jsp"/>
<div class="container">
    <h2 style="text-align: center;">상품 목록</h2>
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
        <thead class="thead-light">
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성일</th>
            <th>읽은횟수</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${productList}">
                <tr class="table-info">
                    <td>${product.no}</td>
                    <td><a href="${path0}/product/getFileboard?no=${product.no}">${product.title}</a></td>
                    <td>${product.resdate}</td>
                    <td>${product.cnt}
                    </td>
                    <td>
<%--                        <c:if test="${not empty board.fileList}">--%>
<%--                            <c:forEach items="${board.fileList}" varStatus="status">--%>
<%--                                <img src="${path0}/resources/images/${board.fileList.get(status.index).savefile}" alt="디스크이미지" style="width:24px;height:24px;"/>--%>
<%--                            </c:forEach>--%>
<%--                        </c:if>--%>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        </tbody>
    </table>
    <c:if test="${not empty principal}">
        <a href="${path0}/product/fileUpload">판매하기</a>
    </c:if>
</div>
<div class="footer l-box">
    <p>
        <a href="#">Try now</a> for 14 days. No credit card required. Header image courtesy of <a href="http://unsplash.com/">Unsplash</a>.
    </p>
</div>
<script src="${path0}/resource/js/datatables.js"></script>
</body>
</html>