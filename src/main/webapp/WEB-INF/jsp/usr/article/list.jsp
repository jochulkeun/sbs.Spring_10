<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>

<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js"></script>

</head>
<body>

	<header>
		<a href="/">로고</a>
		<ul>
			<a href="/"><li>홈</li></a>
			<a href="/usr/home/main"><li>홈</li></a>
		</ul>
	</header>

	<h1>List</h1>
	<hr />
	<table border=1>
		<thead>
			<tr>
				<th>번호</th>
				<th>작성날짜</th>
				<th>수정날짜</th>
				<th>제목</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles}">
				<tr>
					<td>${article.id}</td>
					<td>${article.regDate.substring(2,16)}</td>
					<td>${article.updateDate.substring(2,16)}</td>
					<td><a href="../article/detail?id"={article.id}>${article.title}</a></td>
					<td>${article.memberId}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>