<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
<%@ include file="include/header.jsp" %>	
</head>
<body>
<%@ include file="include/menu.jsp" %>
<c:if test="${sessionScope.userid != null}">
<h2>
	${sessionScope.name} ( ${sessionScope.userid} )님의 방문을 환영합니다.
</h2>
</c:if>

<P>  The time on the server is ${serverTime}. </P>

${result}

</body>
</html>
