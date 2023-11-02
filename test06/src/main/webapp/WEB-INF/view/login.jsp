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
<jsp:include page="include/header.jsp"/>
<h2>로그인</h2>
<%-- spring security를 이용해 로그인 --%>
<form class="pure-form pure-form-aligned" action="${path}/auth" method="post" style="width:600px;margin:15px auto;">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <fieldset>
        <div class="pure-control-group">
            <label for="aligned-name">Username</label>
            <input type="text" name="id" id="aligned-name" placeholder="Id" required/>
            <span class="pure-form-message-inline">This is a required field.</span>
        </div>
        <div class="pure-control-group">
            <label for="aligned-password">Password</label>
            <input type="password" name="password" id="aligned-password" placeholder="Password" required/>
        </div>
        <div class="pure-controls">
            <button type="submit" class="pure-button pure-button-primary">LOG IN</button>
        </div>
    </fieldset>
</form>
</body>
</html>
