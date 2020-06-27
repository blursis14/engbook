<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>
<jsp:include page="../top.jsp" flush="false" />
</head>
<body>
	<div class="container-fluid center-block"
		style="width: 1000px; padding: 15px;">
		<form:form modelAttribute="registerRequest" method="post"
			id="register">
			<p>
				<label>아이디<br> <form:input path="id" /> <form:errors path="id" />
				</label>
			</p>
			<p>
				<label>이메일<br> <form:input path="email" /> <form:errors path="email" />
				</label>
			</p>

			<p>
				<label>비밀번호<br> <form:password path="password"/> <form:errors path="password" />
				</label>
			</p>
			<p>
				<label>비밀번호확인<br> <form:password path="confirmPassword"/> <form:errors path="confirmPassword" />
				</label>
			</p>
			<p>${isPasswordMatch }</p>
			<button class="btn btn-success" type="submit">확인</button>
		</form:form>

	</div>

</body>
</html>




























