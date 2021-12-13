<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="titlePage" value="게시물작성" />

<%@ include file="../common/head.jspf"%>
<section class="mt-5">
	<div class="container mx-auto px-3">
		<form class="table-box-type-1" method="post" action="../article/doWrite">
		<input type="hidden" name="id" value="${article.id }" />
			<hr />
			<table border=1>
				<colgroup>
					<col width="200">
				</colgroup>

				<tbody>
					<tr>
						<th>작성자</th>
						<td><div>${rq.loginedMember.nickname}</td>
					</tr>
					<tr>
						<th>게시판</th>
						<td>
						<select class="select select-bordered" name="boardId">
						<option select disabled>게시판을 선택해 주세요</option>
						<option value="1">공지사항</option>
						<option value="2">자유 1</option>
						</select>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input required="required" name="title" class="w-96 input input-bordered"  name="title"
                type="text"/></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea required="required" class="w-full textarea textarea-bordered" name="body" rows="10"></textarea></td>
					</tr>
					<tr>
					<th>수정</th>
					<td>
						 <button type="submit" class="btn btn-outline">작성</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="btns mt-3">
			<button class="btn btn-outline" type="button" onclick="history.back();">뒤로가기</button>
			<a href="../article/modify?id=${article.id}"class="btn btn-outline">게시물 수정</a> 
			<c:if test="${article.extra__actorCanDelete}">
			<a onclick="if ( confirm('게시물을 삭제하시겠습니까?') == false ) { return false; }"href="../article/doDelete?id=${article.id}"class="btn btn-link">게시물 삭제</a>
			</c:if>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>