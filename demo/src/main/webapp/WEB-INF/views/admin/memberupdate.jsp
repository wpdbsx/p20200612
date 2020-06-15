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
		<form action="/admin/memberupdate" method="post">

			<input type="submit" name="btn" class="btn btn-success" value="일괄수정" />

			<table class="table table-sm">
				<thead>

					<tr>

						<th>사용자아이디</th>
						<th>비밀번호</th>
						<th>사용자명</th>
						<th>폰</th>
						<th>나이</th>
						<th>가입날짜</th>

					</tr>

				</thead>
				<tbody>
					<c:forEach var="tmp" items="${list}">
						<tr>
							<td>${tmp.userid}<input type="hidden" name="id[]" value="${tmp.userid}"></td>
							<td><input type="text" name= "password[]" value="${tmp.password}"/></td>
							<td><input type="text" name= "name[]" value="${tmp.username }"/></td>
							<td><input type="text" name= "phone[]" value="${tmp.phone}" /></td>
							<td><input type="text" name= "age[]" value="${tmp.userage}" /></td>
							<td>${tmp.joindate}"</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>