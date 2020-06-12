<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판2</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />

</head>

<body>

	<div class="container">
		<form action="/admin/item" method="post">
			<a href= "/admin/iteminsert" class="btn btn-sucecess">일괄추가</a>
			<input type="submit" name="btn" class="btn btn-success" value="일괄수정" /> 
			<input type="submit" name="btn" class="btn btn-success" value="일괄삭제" />
			<table class="table table-sm">
				<thead>

					<tr>
						<th><input type="checkbox" /></th>
						<th>아이템 번호</th>
						<th>상품명</th>
						<th>상품가격</th>
						<th>상품재고</th>
						<th>상품설명</th>
						<th>상품등록날짜</th>

					</tr>

				</thead>
				<tbody>
					<c:forEach var="tmp" items="${list}">
						<tr>
							<th><input type="checkbox" name="chk[]"
								value="${tmp.itemno}" /></th>
							<td>${tmp.itemno}</td>
							<td>${tmp.itemname}</td>
							<td>${tmp.itemprice}</td>
							<td>${tmp.itemqty}</td>
							<td>${tmp.itemdes}</td>
							<td>${tmp.itemdate}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>