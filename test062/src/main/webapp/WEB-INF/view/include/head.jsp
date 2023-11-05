<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path2" value="${pageContext.request.contextPath}" />

<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<link rel="stylesheet" href="${path2}/resource/css/pure-min.css">
<link rel="stylesheet" href="${path2}/resource/css/grids-responsive-min.css">
<link rel="stylesheet" href="${path2}/resource/css/styles.css">
<link rel="stylesheet" href="${path2}/resource/css/datatables.css">

<%-- j쿼리 --%>
<script src="https://code.jquery.com/jquery-latest.js"></script>

<!-- datatables -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css">
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
