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
    <jsp:include page="../../include/head.jsp"/>
    <title>회원정보수정</title>
</head>
<body>
<jsp:include page="../../include/header.jsp"/>
<h2>회원정보수정</h2>
<form action="${path}/user/update.do" method="post" onsubmit="return joinCheck(this)">
    <div class="register_info_id">
        <p>아이디</p>
        <input type="text" name="id" id="id" value="${user.id}" readonly>
    </div>
    <div>
        <p>이름</p>
        <input type="text" name="name" id="name" minlength="1" maxlength="50" value="${user.name}"
               placeholder="이름" pattern="^[가-힣]+$" required>
    </div>
    <div>
        <input type="password" name="pw" id="pw" minlength="4" maxlength="16" placeholder="비밀번호" value="${user.pw}"
               required>
        <!-- pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" -->
    </div>
    <div>
        <input type="password" name="pw2" id="pw2" minlength="4" maxlength="16" placeholder="비밀번호 확인" value="${user.pw}"
               required>
    </div>
    <div>
        <input type="email" name="email" id="email" minlength="10" maxlength="90" placeholder="이메일"
               value="${user.email}"
               pattern="^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}" required>
    </div>
    <div>
        <input type="tel" name="tel" id="tel" placeholder="010-0000-0000" pattern="(010)-\d{3,4}-\d{4}"
               value="${user.tel}" required>
    </div>
    <div>
        <input type="text" name="addr" id="addr" maxlength="280" placeholder="주소" value="${user.addr}"
               pattern="^[가-힣a-zA-z0-9]+$">
    </div>
    <div>
        <input type="submit" value="회원정보수정">
        <input type="reset" value="초기화">
    </div>
</form>
<script>
    function joinCheck(f) {
        if (f.pw.value != f.pw2.value) {
            alert("비밀번호와 비밀번호 확인이 서로 다릅니다.");
            f.pw.focus();
            return false;
        }
        if (f.idck.value != "yes") {
            alert("아이디 중복 체크를 하지 않으셨습니다.");
            return false;
        }
    }
</script>
</body>
</html>
