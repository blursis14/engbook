<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<jsp:include page="top.jsp" flush="false"/>
</head>
<body>
<div class="container-fluid center-block" style="width: 1000px; padding: 15px;">
<form class="form-inline ml-auto" method="post" action="login">
	<input class="form-control mr-sm-2" type="text" name="id" id="id" placeholder="아이디"/><br/>
	<input class="form-control mr-sm-2" type="text" name="password" id="password" placeholder="비밀번호"/></br/>
	<button class="btn btn-success" type="submit">확인</button>
</form>

<div>${exception}</div>
<div>
<a href="/register/form" class="btn btn-outline-info mt-3 ml-1" role="button">회원가입</a>
</div>

</div>
</body>
</html>

















