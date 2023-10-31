<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../include/head.jsp"/>
    <title>list</title>
    <style>
        .subject {
            text-align: center;
            margin-top: 100px;
            margin-bottom: 50px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../include/header.jsp"/>
    <div class="subject">
        <h2>회원목록</h2>
    </div>
    <div class="container">
        <div class="search_from">
            <select name="select_filter" class="select_filter">
                <option value="0">번호</option>
                <option value="1">이름</option>
                <option value="2">아이디</option>
            </select>
            <input type="text" name="search_filter" class="search_filter">
        </div>
        <table class="table" id="myTable">
            <thead>
            <tr>
                <th scope="col">번호</th>
                <th scope="col">이름</th>
                <th scope="col">아이디</th>
                <th scope="col">등급</th>
                <th scope="col">상태</th>
                <th scope="col">비고</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user" varStatus="status">
                <tr>
                    <td scope="row">${status.count}</td>
                    <td><a href="${path}/user/getUser.do?id=${user.id}">${user.name}</a></td>
                    <td>${user.id}</td>
                    <td>${user.lev}</td>
                    <td>${user.act}</td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="${path}/resources/js/datatables.js"></script>
</body>
</html>
