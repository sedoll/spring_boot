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
    <title>get</title>
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
        <h2>${userInfo.name} 회원 정보</h2>
    </div>
    <table class="table container">
        <thead>
        <tr>
            <th scope="col">이름</th>
            <th scope="col">아이디</th>
            <th scope="col">등급</th>
            <th scope="col">이메일</th>
            <th scope="col">주소</th>
            <th scope="col">전화번호</th>
            <th scope="col">비고</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty userInfo}">
            <tr>
                <td>${userInfo.name}</td>
                <td>${userInfo.id}</td>
                <td>${userInfo.lev}</td>
                <td>${userInfo.email}</td>
                <td>${userInfo.addr}</td>
                <td>${userInfo.tel}</td>
                <td><a href="${path}/user/updateForm.do?id=${userInfo.id}">수정</a></td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
