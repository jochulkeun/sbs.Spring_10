<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시물 리스트</title>
</head>
<body>
	<h1>게시물 리스트</h1>
	<div>
	<%=request.getAttribute("article") %>
	</div>
</body>
</html>