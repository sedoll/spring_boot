<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path2" value="${pageContext.request.contextPath}" />

<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="${path2}/resource/css/pure-min.css">
<link rel="stylesheet" href="${path2}/resource/css/grids-responsive-min.css">
<link rel="stylesheet" href="${path2}/resource/css/styles.css">
