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
    <title>회원가입</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<h2>회원가입</h2>
<form action="${path}/user/insert.do" method="post" onsubmit="return joinCheck(this)">
    <div class="register_info_id">
        <p>아이디는 영어 소문자, 숫자만 가능합니다.</p>
        <input type="text" name="id" id="id" minlength="5" maxlength="18" placeholder="아이디" pattern="^[a-z0-9]+$"
               required>
        <button type="button" id="ck_btn" class="button is-link" onclick="idCheck()">중복확인</button>
        <input type="hidden" name="idck" id="idck" value="no">
    </div>
    <div>
        <c:if test="${empty qid }">
            <p id="msg" style="padding-left:0.5rem">아직 아이디 중복 체크를 하지 않으셨습니다.</p>
        </c:if>
        <c:if test="${not empty qid }">
            <p id="msg" style="padding-left:0.5rem">아이디 중복 체크후 수정하였습니다.</p>
        </c:if>
    </div>
    <div>
        <p>이름은 한글만 입력이 가능합니다.</p>
        <input type="text" name="name" id="name" minlength="1" maxlength="50" placeholder="이름" pattern="^[가-힣]+$"
               required>
    </div>
    <div>
        <input type="password" name="pw" id="pw" minlength="4" maxlength="16" placeholder="비밀번호" required>
        <!-- pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" -->
    </div>
    <div>
        <input type="password" name="pw2" id="pw2" minlength="4" maxlength="16" placeholder="비밀번호 확인" required>
    </div>
    <div>
        <input type="email" name="email" id="email" minlength="10" maxlength="90" placeholder="이메일"
               pattern="^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}" required>
    </div>
    <div>
        <input type="tel" name="tel" id="tel" placeholder="010(02,0505)-0000-0000" pattern="\d{2,4}-\d{3,4}-\d{4}" required>
    </div>
    <div>
        <input type="text" name="addr" id="addr" maxlength="280" placeholder="주소" pattern="^[가-힣a-zA-z0-9]+$">
    </div>
    <div>
        <input type="submit" value="회원가입">
    </div>
</form>
<script>
    $(document).ready(function () {
        $("#id").keyup(function () {
            $("#idck").val("no");
            if ($(this).val() != "") {
                $("#msg").html("<strong>아이디 입력중입니다.</strong>");
                $("#id").focus();
            } else {
                $("#msg").html("아직 아이디 중복 체크를 하지 않으셨습니다.");
            }
        });
    });

    function idCheck() {
        if ($("#id").val() == "") {
            alert("아이디를 입력하지 않으셨습니다.");
            $("#id").focus();
            return;
        }
        var params = {id: $("#id").val()} //전송되어질 데이터를 객체로 묶음
        $.ajax({
            url: "${path}/user/idCheck.do",	//아이디가 전송되어질 곳
            type: "post",		//전송방식
            dataType: "json",	//데이터 반환 방식
            data: params,		//전송방식이 post인 경우 객체로 묶어서 전송
            success: function (result) {
                console.log(result.result);
                var idChk = result.result;	//true 또는 false를 받음
                if (idChk == false) {	//사용할 수 없는 아이디
                    $("#idck").val("no");
                    $("#msg").html("<strong style='color:red'>중복 되거나 조건에 맞지 않는 아이디 입니다.</strong>");
                    $("#id").focus();
                } else if (idChk == true) {	//사용 가능한 아이디
                    $("#idck").val("yes");
                    $("#msg").html("<strong style='color:blue'>사용가능한 아이디 입니다.</strong>");
                } else if (idck == "") {
                    $("#msg").html("<strong>아이디가 확인되지 않았습니다. 다시 시도해주시기 바랍니다.</strong>");
                }
            }
        });
    }
</script>

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

    function findAddr() {
        new daum.Postcode({
            oncomplete: function (data) {
                console.log(data);
                var roadAddr = data.roadAddress;
                var jibunAddr = data.jibunAddress;
                document.getElementById("postcode").value = data.zonecode;
                if (roadAddr !== '') {
                    document.getElementById("addr1").value = roadAddr;
                } else if (jibunAddr !== '') {
                    document.getElementById("addr1").value = jibunAddr;
                }
                document.getElementById("addr2").focus();
            }
        }).open();
    }
</script>
</body>
</html>
