<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path0" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="./include/head.jsp"/>
    <title>로그인</title>
</head>
<body>
<jsp:include page="./include/header.jsp"/>
<div class="container" style="width:1280px;margin:20px auto;">
    <h2 style="text-align: center;">로그인</h2>
    <form class="pure-form pure-form-aligned" action="${path0}/auth" method="post" style="width:600px;margin:15px auto;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="form-group">
            <label for="aligned-name">아이디</label>
            <input type="text" class="form-control" name="name" id="aligned-name" placeholder="Id" pattern="^[a-z0-9]*$" minlength="4" maxlength="16" required/>
        </div>
        <div class="form-group">
            <label for="aligned-password">비밀번호</label>
            <input type="password" class="form-control" name="password" id="aligned-password" placeholder="Password" pattern="^\d{4,}(?:[a-z@^*]*)?$" minlength="4" maxlength="16" required/>
        </div><br>
        <div class="pure-controls">
            <button type="submit" class="btn btn-primary">LOG IN</button>
        </div>
    </form>
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