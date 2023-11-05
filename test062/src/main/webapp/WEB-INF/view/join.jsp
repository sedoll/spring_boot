<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path0" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="./include/head.jsp"/>
    <title>회원 가입</title>
</head>
<body>
<jsp:include page="./include/header.jsp"/>
<div class="banner"></div>
<div class="l-content" style="width:1280px;margin:20px auto;">
    <h2>회원 가입</h2>
    <form class="pure-form pure-form-aligned" action="${path0}/common/joinPro" method="post" onsubmit="return joinConfirm(this)">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <fieldset>
            <legend>회원 가입 정보를 입력하시기 바랍니다.</legend>
            <div class="pure-control-group">
                <label for="id">Id</label>
                <input type="text" name="name" id="id" placeholder="Id" minlength="4" maxlength="16" placeholder="id"
                       pattern="^[a-z0-9]*$" required/>
                <button type="button" id="idCheckBtn" class="pure-button pure-button-primary">아이디 중복 체크</button>
                <input type="hidden" name="idCheck" id="idCheck" value="false">
                <div id="msg"></div>
            </div>
            <div class="pure-control-group">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" placeholder="Password"
                       pattern="^\d{4,}(?:[a-z@^*]*)?$" required/>
            </div>
            <div class="pure-control-group">
                <label for="password2">Password Confirm</label>
                <input type="password" name="password2" id="password2" placeholder="Password Confirm"
                       pattern="^\d{4,}(?:[a-z@^*]*)?$" required/>
            </div>
            <div class="pure-control-group">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" placeholder="Username"
                       minlength="2" maxlength="50" placeholder="username" pattern="^[가-힣]+$" required/>
            </div>
            <div class="pure-control-group">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" placeholder="Email"
                       pattern="^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}" maxlength="100" required/>
                <button type="button" id="emailCheckBtn" class="pure-button pure-button-primary" >이메일 중복 체크</button>
                <input type="hidden" name="emailCheck" id="emailCheck" value="false">
                <div id="msg2"></div>
            </div>
            <div class="pure-control-group">
                <label for="address">Address</label>
                <input type="text" name="address" id="address" placeholder="Address"
                       pattern="^[가-힣a-zA-Z0-9\s]*$" maxlength="280" required/>
            </div>
            <div class="pure-control-group">
                <label for="tel">Tel</label>
                <input type="tel" name="tel" id="tel" placeholder="Tel"
                       placeholder="010(02,0505)-0000-0000" pattern="\d{2,4}-\d{3,4}-\d{4}" maxlength="30" required/>
            </div>
            <div class="pure-controls">
                <button type="submit" class="pure-button pure-button-primary">JOIN</button>
            </div>
        </fieldset>
    </form>
    <script>
    $(document).ready(function(){
        $("#idCheckBtn").click(function(){
            if($("#id").val()==""){
                alert("아이디를 입력하지 않으셨습니다.");
                $("#id").focus();
                return;
            }
            var test = { name : $("#id").val() }; //전송되어질 데이터를 객체로 묶음
            $.ajax({
                url:"${path1 }/common/idCheck",	//아이디가 전송되어질 곳
                type:"post",		//전송방식
                data:JSON.stringify(test),
                dataType:"json",
                contentType: "application/json",
                success:function(result){
                    console.log(result);
                    var idChk = result;	//true 또는 false를 받음
                    if(idChk==false){	//사용할 수 없는 아이디
                        $("#idCheck").val("false");
                        $("#msg").html("<strong style='color:red'>기존에 사용되고 있는 아이디 입니다. 다시 입력하시기 바랍니다.</strong>");
                        $("#id").focus();
                    } else if(idChk==true){	//사용 가능한 아이디
                        $("#idCheck").val("true");
                        $("#msg").html("<strong style='color:blue'>사용가능한 아이디 입니다.</strong>");
                    } else if(idChk==""){
                        $("#msg").html("<strong>아이디가 확인되지 않았습니다. 다시 시도해주시기 바랍니다.</strong>");
                    }
                }
            });
        });
        $("#emailCheckBtn").click(function(){
            if($("#email").val()==""){
                alert("이메일을 입력하지 않으셨습니다.");
                $("#email").focus();
                return;
            }
            var params = {	email : $("#email").val()	} //전송되어질 데이터를 객체로 묶음
            $.ajax({
                url:"${path1 }/common/emailCheck",	//아이디가 전송되어질 곳
                type:"post",		//전송방식
                dataType:"json",	//데이터 반환 방식
                data:params,		//전송방식이 post인 경우 객체로 묶어서 전송
                success:function(result){
                    console.log(result);
                    var emailChk = result;	//true 또는 false를 받음
                    if(emailChk==false){	//사용할 수 없는 이메일
                        $("#emailCheck").val("false");
                        $("#msg2").html("<strong style='color:red'>기존에 사용되고 있는 이메일 입니다. 다시 입력하시기 바랍니다.</strong>");
                        $("#email").focus();
                    } else if(emailChk==true){	//사용 가능한 이메일
                        $("#emailCheck").val("true");
                        $("#msg2").html("<strong style='color:blue'>사용가능한 이메일입니다.</strong>");
                    } else if(emailChk==""){
                        $("#msg2").html("<strong>이메일이 확인되지 않았습니다. 다시 시도해주시기 바랍니다.</strong>");
                    }
                }
            });
        });
        function joinConfirm(f){
            if(f.password.value!=f.password2.value){
                alert("비밀번호와 비밀번호 확인이 서로 다릅니다.");
                f.password.focus();
                return false;
            }
            if(f.idCheck.value!="true"){
                alert("아이디 중복 체크를 하지 않으셨습니다.");
                return false;
            }
            if(f.emailCheck.value!="true"){
                alert("이메일 중복 체크를 하지 않으셨습니다.");
                return false;
            }
        }
    });
    </script>
</div>
<div class="footer l-box">
    <p>
        <a href="#">Try now</a> for 14 days. No credit card required. Header image courtesy of <a href="http://unsplash.com/">Unsplash</a>.
    </p>
</div>
</body>
</html>