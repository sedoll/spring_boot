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
    <title>채팅 목록</title>
</head>
<body>

<c:choose>
    <c:when test="${not empty principal}">
        <form action="/chat/createRoom" method="post">
            <input type="text" name="name" placeholder="채팅방 이름">
            <button type="submit" >방 만들기</button>
        </form>
        <table>
            <c:forEach items="${roomList}" var="room">
                <tr>
                    <td>
                        <a href="${path}/chat/chatRoom?roomId=${room.roomId}">${room.name}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p>채팅을 하려면 로그인을 하세요</p>
    </c:otherwise>
</c:choose>
</body>
</html>
