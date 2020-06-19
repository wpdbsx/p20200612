<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri= "http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
	<h1>인덱스 페이지 입니다.</h1>

	<c:if test="${sessionScope.SESSION_ID eq null }">
		<a href="/member/login">로그인</a>
		<a href="/member/join">회원가입</a>
	</c:if>
	<c:if test="${sessionScope.SESSION_ID ne null }">
		<a href="/board/insert">게시판 글쓰기</a>x
		<a href="/member/logout">로그아웃</a>
		<a href="/member/edit">회원정보수정</a>
	</c:if>
	

</body>
</html>