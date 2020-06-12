<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>물품일괄등록</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
		<form action="/board/insertbatch" method="post">
			<table class="table table-sm">
			
			<thead>

				<tr>
					<th><input type="checkbox"/></th>
					<th>글제목</th>
					<th>글내용</th>
					<th>사용자아이디</th>
					
				
				</tr>

			</thead>
			<tbody>
				<c:forEach var="i" begin="1" end="5" step="1">
					<tr>
					<th><input type="checkbox"/></th>
						<td><input type="text" name="title[]" placeholder="글제목"
							value="테스트중"></td>
						<td><input type="text" name="content[]" placeholder="글내용"
							value="777"></td>
						<td><input type="text" name="brd_id" placeholder="사용자아이디"
							value= "${userid }" readonly></td>

					</tr>
				</c:forEach>
			</tbody>
			</table>
			<input type="submit" class="btn btn-sucess" value="일괄추가" />

		</form>
	</div>
</body>
</html>