<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
<form action= "/board/insert" method="post" enctype="multipart/form-data">
제목 : <input type="text" name="brd_title" placeholder="글제목"/> <br/>
내용 : <textarea id="content"  name="brd_content" placeholder= "글내용"></textarea> <br/>
사용자 : <input type="text" name= "brd_id" value="${userid }" readonly/><br/>
	 <input type= "file" name= "imgs"/> <br/>
	<input type="submit" value="입력"/>
</form>
</body>
</html>