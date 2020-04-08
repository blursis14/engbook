<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입 완료</title>
<jsp:include page="../top.jsp" flush="false"/>
</head>
<body>
<p>축하합니다,${registerRequest.id }님. 회원가입이 완료되었습니다.</p>
<a href="../main" class="btn btn-success" role="button">메인으로</a>
</body>
</html>