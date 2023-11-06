<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/> <%-- 아이디 가져오기 --%>
    <sec:authentication property="authorities" var="authorities"/> <%-- 인증권한 정보 가져오기 --%>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>채팅방</title>
</head>
<body>
<h1>채팅방</h1>
<input type="text" placeholder="보낼 메세지를 입력하세요." class="content">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<input type="hidden" id="roomId" value="${room.roomId}" />
<button type="button" value="전송" class="sendBtn" onclick="sendMsg()">전송</button>
<button type="button" value="방나가기" class="quit" onclick="quit()">방 나가기 </button>
<div>
    <span>메세지</span>
    <div class="msgArea"></div>
</div>

<script>
    var socket = new WebSocket("ws://localhost:8085/ws/chat");
    var roomId = document.getElementById('roomId').value;

    socket.onopen = function (e) {
        console.log('open server!')
        enterRoom(socket);
    };

    socket.onclose = function (e) {
        console.log('disconnet');
    };

    socket.onerror = function (e) {
        console.log(e);
    };

    socket.onmessage = function (e) {
        console.log(e.data);
        let msgArea = document.querySelector('.msgArea');
        let newMsg = document.createElement('div');
        newMsg.innerText = e.data;
        msgArea.append(newMsg);
    };

    function enterRoom(socket) {
        var enterMsg = {
            "type": "ENTER",
            "roomId": roomId,
            "sender": "${principal}",
            "msg": ""
        };
        socket.send(JSON.stringify(enterMsg));
    }

    function sendMsg() {
        let content = document.querySelector('.content').value;
        var talkMsg = {
            "type": "TALK",
            "roomId": roomId,
            "sender": "${principal}",
            "msg": content
        };
        socket.send(JSON.stringify(talkMsg));
    }

    function quit() {
        var quitMsg = {
            "type": "QUIT",
            "roomId": roomId,
            "sender": "${principal}",
            "msg": ""
        };
        socket.send(JSON.stringify(quitMsg));
        socket.close();
        location.href = "/chat/chatList";
    }
</script>
</body>
</html>
