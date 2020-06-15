<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form action="/admin/member" method="post">
			<input type="submit" name="btn" class="btn btn-success" value="일괄수정" /> 
			<input type="submit" name="btn" class="btn btn-success" value="일괄삭제" />
			<table class="table table-sm">
				<thead>

					<tr>
						<th><input type="checkbox" /></th>
						<th>사용자아이디  </th>
						<th>비밀번호  </th>
						<th>사용자명  </th>
						<th>폰  </th>
						<th>나이  </th>
						<th>가입날짜   </th>

					</tr>

				</thead>
				<tbody>
					<c:forEach var="tmp" items="${lista}">
						<tr>
							<th><input type="checkbox" name="chk[]"
								value="${tmp.userid}" /></th>
							<td>${tmp.userid}</td>
							<td>${tmp.password}</td>
							<td>${tmp.username}</td>
							<td>${tmp.phone}</td>
							<td>${tmp.userage}</td>
							<td>${tmp.joindate} a</td>
 
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>