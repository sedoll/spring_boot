<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path0" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../include/head.jsp"/>
    <title>게시판 글작성</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="banner"></div>
<div class="container" style="width:1280px;margin:20px auto;">
    <h2 style="text-align: center;">게시판 글작성</h2>
    <form action="${path0}/board/boardInsert" method="post" style="width:600px;margin:15px auto;"
          onsubmit="return textCheck(this)">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <legend>게시판 글 작성</legend>
            <div class="form-group">
                <label for="id">Id</label>
                <input type="text" class="form-control bg-secondary text-white" name="name" id="id" value="${name}" minlength="4" maxlength="16"
                       pattern="^[a-z0-9]*$" readonly/>
            </div>

            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" name="title" id="title" placeholder="Title" minlength="1" maxlength="30"
                       pattern="[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ_!?@,.^~\s]*$" title="영문, 숫자, 밑줄, 공백, 한글만 입력할 수 있습니다." required/>
            </div>

            <div class="form-group">
                <label for="content">Content</label>
                <textarea name="content" class="form-control" id="content" cols="30" rows="10" maxlength="900" required></textarea>
                <input type="hidden" id="ck" value="no">
                <div id="error-message"></div>
            </div>
            <div class="form-group">
                <button id="contBtn" type="button" class="btn btn-secondary">내용 검사</button>
                <input type="submit" class="btn btn-primary" value="작성">
                <input type="reset" class="btn btn-danger" value="초기화">
            </div>
    </form>
</div>
<div class="footer l-box">
    <p>
        <a href="#">Try now</a> for 14 days. No credit card required. Header image courtesy of <a href="http://unsplash.com/">Unsplash</a>.
    </p>
</div>
<script>
    $(document).ready(function() {
        $("#contBtn").click(function () {
            const textarea = $("#content");
            const errorMessage = $("#error-message");
            const ck_content = $("#ck");

            const inputText = textarea.val();
            const pattern = /^[a-zA-Z0-9_\s\n가-힣ㄱ-ㅎㅏ-ㅣ!?,.@^~]*$/;

            if (pattern.test(inputText)) {
                errorMessage.text("내용이 제대로 작성되었습니다.");
                ck_content.val("yes");
                // 여기에서 다른 작업 수행
            } else {
                errorMessage.text("영문, 숫자, 밑줄, 공백, 한글만 입력할 수 있습니다.");
                ck_content.val("no");
            }
        });
    });

    function textCheck(f) {
        if(f.ck.value == "no"){
            alert("게시글 내용을 검사해주세요");
            f.content.focus();
            return false;
        }
    }
</script>
</body>
</html>