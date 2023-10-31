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
    <title>아이디 로그인</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<h2>로그인</h2>
<form action="${path}/user/idLogin.do" method="post">
    <div>
        <input type="text" name="id" id="id" minlength="4" maxlength="90" placeholder="아이디" pattern="^[a-zA-Z0-9@.]*$" required>
    </div>
    <div>
        <input type="password" name="pw" id="pw" minlength="4" maxlength="16" placeholder="비밀번호"  required>
        <!-- pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" -->
    </div>
    <div>
        <input type="submit" value="로그인">
    </div>
</form>
</body>
</html>
