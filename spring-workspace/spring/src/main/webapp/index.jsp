<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Spring</title>
</head>
<body>
	Hello Spring
	
	<%-- main.jsp 페이지로 포워딩 --%>
	<jsp:forward page="WEB-INF/views/main.jsp"></jsp:forward>
</body>
</html>