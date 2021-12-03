<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="titlePage" value="게시물수정" />

<%@ include file="../common/head.jspf"%>
<section class="mt-5">
	<div class="container mx-auto px-3">
		<form class="table-box-type-1" method="post" action="../article/doModify">
		<input type="hidden" name="id" value="${article.id }" />
			<hr />
			<table border=1>
				<colgroup>
					<col width="200">
				</colgroup>

				<tbody>
					<tr>
						<th>번호</th>
						<td><div class="badge badge-primary">${article.id}</td>
					</tr>
					<tr>
						<th>작성날짜</th>
						<td><div class="badge badge-primary">${article.regDateForPrint}</td>
					</tr>
					<tr>
						<th>수정날짜</th>
						<td><div class="badge badge-primary">${article.updateDateForPrint}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><div class="badge badge-primary">${article.extra__writer}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input name="title" class="w-96 input input-bordered"  name="title"
                type="text" type="text" value="${article.title}"/></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea class="w-full input input-bordered" name="body" rows="10">${article.body}</textarea></td>
					</tr>
					<tr>
					<th>수정</th>
					<td>
						 <button type="submit" class="btn btn-outline btn-primary">수정</button>
						<button type="button" class="btn btn-outline btn-primary" onclick="history.back();">뒤로가기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="btns">
			<button class="btn btn-link" type="button" onclick="history.back();">뒤로가기</button>
			<a href="../article/modify?id=${article.id}"class="btn btn-link">게시물 수정</a> 
			<c:if test="${article.extra__actorCanDelete}">
			<a onclick="if ( confirm('게시물을 삭제하시겠습니까?') == false ) { return false; }"href="../article/doDelete?id=${article.id}"class="btn btn-link">게시물 삭제</a>
			</c:if>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>