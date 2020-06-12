<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	글번호 : 	<input type="text" name="brd_no" value="${obj.brd_no }" placeholder="글번호" readonly /> <br/>
	제목 : <input type="text" name="brd_title" value="${obj.brd_title }" placeholder="글제목" readonly /> <br/>
	내용 : <textarea id="content"  name="brd_content"    placeholder=${obj.brd_content } readonly></textarea> <br/>
	사용자 : <input type="text" name= "brd_id" value="${obj.brd_id }" readonly/><br/>
		 <input type= "file" name= "imgs" /> <br/>
	조회수 	${obj.brd_hit}<br/>
	생성날짜	${obj.brd_date}<br/>
	
	<img src="/board/getimg?no=${obj.brd_no}" width="100px" height="100px" /><br />
	<a href="/board/list"		class="btn btn-secondary">목록</a>
	
</body>
</html>