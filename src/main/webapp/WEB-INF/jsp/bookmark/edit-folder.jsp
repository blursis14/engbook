<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edit-folder</title>
<script type="text/javascript" src="webjars/jquery/3.3.1/jquery.min.js"></script>
<jsp:include page="../top.jsp" flush="false" />
<script type="text/javascript">



</script>
</head>
<body>
		<div class="container-fluid center-block" style="width: 1000px; padding: 15px;">
				<div class="row">
						<div class="col-sm">
								<ul class="list-group" id="folder-list">
										<c:forEach var="folder" items="${folders}">
												<li class="list-group-item">
														<div class="row">
																<div class="col-md-8">
																		<div class="ml-auto row mr-2">
																				<h4 class="col-md-6 text-info">${folder.folder}</h4>
																				<div class="delete" value="${folder.folder}">
																						<button class="btn btn-outline-info">삭제</button>
																				</div>
																				<div class="edit" value="${folder.folder}">
																						<button class="btn btn-outline-info">수정</button>
																				</div>
																		</div>
																</div>
														</div>
												</li>
										</c:forEach>
								</ul>
						</div>
				</div>
		</div>
</body>
</html>
