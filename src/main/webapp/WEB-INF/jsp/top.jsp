<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/bootstrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
	<header>
		<h1>
		<a href="/main">ENGBOOK</a>
		</h1>
	</header>

	<nav class="navbar navbar-expand-sm navbar-light"
		style="background-color: #91cfff;">
		
		<a class="navbar-brand" href="#">Navbar</a>
		
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav navbar-dark">
				<c:if test="${empty authInfo }">
					<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
				</c:if>
				<c:if test="${!empty authInfo }">
					<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
				</c:if>
				<li class="nav-item dropdown">
					 <a class="nav-link dropdown-toggle" href="#"
					data-toggle="dropdown"> Dropdown </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">Link 1</a> <a
							class="dropdown-item" href="#">Link 2</a> <a
							class="dropdown-item" href="#">Link 3</a>
					</div>
				</li>
			</ul>
		</div>
	</nav>
	

</body>
</html>