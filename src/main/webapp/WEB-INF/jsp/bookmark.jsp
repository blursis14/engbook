<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bookmark</title>
<script type="text/javascript" src="webjars/jquery/3.3.1/jquery.min.js"></script>
<jsp:include page="top.jsp" flush="false" />
</head>
<body>

	<div class="container-fluid center-block"
		style="width: 1000px; padding: 15px;">

		<c:if test="${!empty folders }">
			<div class="row">
				<div class="col-sm">
					<ul class="list-group">

						<c:forEach var="folder" items="${folders}">
							<li class="list-group-item">${folder.folder}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</c:if>


		
	</div>

</body>
</html>


























