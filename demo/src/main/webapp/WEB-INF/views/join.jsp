<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<form action= "/member/join" method="post">
아이디 : <input type="text" name="userid"/> <br/>
패스워드 : <input type="password"  name="password"/> <br/>
이름 : <input type="text" name="username"/> <br/>
연락처 : <input type="text" name="phone" /> <br/>
나이 : <input type="text" name="userage" /> <br/>
성별 : <select name="gender"> 
		<option value="">성별</option>
		<option value="남자">남자</option>
		<option value="여자">여자</option>
		</select>
<input type="submit" value="회원가입"/>
</form>
</body>
</html>