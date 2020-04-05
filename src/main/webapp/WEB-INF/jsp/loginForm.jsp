<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm</title>
<jsp:include page="top.jsp" flush="false"/>
</head>
<body>
<form class="form-inline ml-auto" method="post">
	<input class="form-control mr-sm-2" type="text" name="id" id="id" placeholder="아이디"/><br/>
	<input class="form-control mr-sm-2" type="text" name="password" id="password" placeholder="비밀번호"/></br/>
	<button class="btn btn-success" type="submit">확인</button></form>
</body>
</html>