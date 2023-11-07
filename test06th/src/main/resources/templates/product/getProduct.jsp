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
    <jsp:include page="../include/head.html"/>
    <title>게시판 상세</title>
</head>
<body>
<jsp:include page="../include/header.html"/>
<div class="container" style="width:1280px;margin:20px auto;">
    <div class="row content">
        <div class="col-sm-2 sidenav">
<%--            <p><a href="#">Link</a></p>--%>
<%--            <p><a href="#">Link</a></p>--%>
<%--            <p><a href="#">Link</a></p>--%>
        </div>
        <div class="col-sm-8 text-left">
            <h2 style="text-align: center;">게시판 상세</h2>
            <table class="table">
                <tbody>
                    <tr>
                        <th>번호</th>
                        <td>${board.id}</td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>${board.name}</td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td>${board.title}</td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>${board.content}</td>
                    </tr>
                    <tr>
                        <th>조회수</th>
                        <td>${board.cnt}</td>
                    </tr>
                    <tr>
                        <th>게시일</th>
                        <td>${board.resdate}</td>
                    </tr>
                    <tr>
                        <th>비고</th>
                        <td>
                            <c:if test="${principal eq board.name or authorities eq '[ADMIN]'}">
                                <a href="${path0}/board/boardUpdate?id=${board.id}">수정</a>
                                <a href="${path0}/board/boardDelete?id=${board.id}">삭제</a>
                            </c:if>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table class="table" id="myTable">
                <thead>
                <tr>
                    <th class="item1">작성자</th>
                    <th class="item2">댓글</th>
                    <th class="item3">작성일</th>
                    <th class="item4">비고</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="lev" items="${comment }">
                    <tr>
                        <td class="item1">${lev.name}</td>
                        <td class="item2">${lev.content}</td>
                        <td class="item3">${lev.resdate}</td>
                        <td class="item4">
                            <c:if test="${principal eq lev.name or authorities eq '[ADMIN]'}">
                                <a href="${path0}/board/comUpdate?id=${lev.id}" class="button is-link is-small is-outlined is-rounded ">수정</a>
                                <a href="${path0}/board/comDelete?id=${lev.id}" class="button is-danger is-small is-outlined is-rounded">삭제</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <form action="${path0}/board/commentInsert" id="login_frm" class="frm" method="post" onsubmit="return comment(this)">
                <table class="tb3">
                    <tbody>
                    <tr>
                        <c:if test="${not empty principal}">
                            <th class="has-text-centered">${principal}</th>
                            <th><textarea name="content" id="content" cols="100" rows="5" placeholder="댓글 작성" maxlength="250" required ></textarea></th>
                            <th>
                                <input type="hidden" name="name" value="${principal}">
                                <input type="hidden" name="par" value="${board.id}">
                                <input type="submit" value="글쓰기" class="inbtn" id="ans_btn">
                                <input type="hidden" id="ck" value="no">
                                <button id="contBtn" type="button">내용 검사</button>
                                <div id="error-message"></div>
                            </th>
                        </c:if>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <div class="col-sm-2 sidenav">
<%--            <div class="well">--%>
<%--                <p>ADS</p>--%>
<%--            </div>--%>
<%--            <div class="well">--%>
<%--                <p>ADS</p>--%>
<%--            </div>--%>
        </div>
    </div>
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

    $(document).ready( function () {
        $('#myTable').DataTable({
            // sorting 화살표 제거
            "targets": 'no-sort',
            "bSort": false,
            "destroy": true,

            // 2번째 컬럼을 기준으로 내림차순 정렬
            order: [[2, 'desc']],
            pageLength : 5,
            searching: false, //검색 제거
            lengthChange: false, // show entries 제거
            info: false,

            language: {
                emptyTable: '작성된 댓글(이)가 없습니다.'
            }
        });
        $('#myTable').css({
            'border':'none',
        });
    } );

    function comment(f) {
        if(f.ck.value == "no"){
            alert("게시글 내용을 검사해주세요");
            f.content.focus();
            return false;
        }
    }

</script>
<div class="footer l-box">
    <p>
        <a href="#">Try now</a> for 14 days. No credit card required. Header image courtesy of <a href="http://unsplash.com/">Unsplash</a>.
    </p>
</div>
</body>
</html>