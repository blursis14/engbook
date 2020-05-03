<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bookmark</title>
<script type="text/javascript" src="webjars/jquery/3.3.1/jquery.min.js"></script>
<jsp:include page="../top.jsp" flush="false" />

<script type="text/javascript">

</script>





</head>
<body>



<div class="container-fluid center-block"
		style="width: 1000px; padding: 15px;">

		<c:if test="${!empty bookmarks }">
			<div class="row">
				<div class="col-sm">
					<ul class="list-group" id="bookmark-list">

						<c:forEach var="bookmark" items="${bookmarks}">
							<ul>
							<li>${bookmark.sentence }</li>
							<li>${bookmark.mean }</li>
							</ul>
						</c:forEach>
						
					</ul>
				</div>
			</div>
		</c:if>


		
	</div>




</body>
</html>
