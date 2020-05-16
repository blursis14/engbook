<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="/WEB-INF/jsp/bootstrap.jsp"%>


<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>

	<div class="container-fluid center-block"
		style="width: 1000px; padding: 15px;">
		<header>
			<h1>
				<a href="/main">ENGBOOK</a>
			</h1>
		</header>
		<nav class="navbar navbar-expand-sm navbar-light"
			style="background-color: #91cfff;">


			<div id="member">
				<ul class="navbar-nav navbar-dark">
					<c:if test="${empty authInfo }">
						<li class="nav-item"><a class="nav-link" id="login" href="/login">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/needLogin">북마크</a></li>
					</c:if>
					<c:if test="${!empty authInfo }">
						<li class="nav-item"><a class="nav-link" id="logout" href="/logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link" href="/bookmark">북마크</a></li>
					</c:if>
					
						
				</ul>
				
			</div>
		</nav>
	</div>



</body>
</html>