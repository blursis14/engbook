<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bookmark-list</title>
<script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<jsp:include page="../top.jsp" flush="false" />

<script type="text/javascript">
	//삭제 버튼 눌림
	$(document).on('click', '.delete', function(e) {

		var data = {}
		data["din"] = parseInt($(this).attr('value'), 10); //value=디비의 din컬럼

		$.ajax({
			type : "POST",
			url : "/bookmark/delete",
			//async:false,
			data : JSON.stringify(data),
			contentType : "application/json;charset=UTF-8",
			success : function(data) {
				location.reload(); //새로고침
			},
			error : function(e) {
				alert('실패');
			}
		})

	});

	//외웠어요 눌림
	$(document).on('click', '.pass', function(e) {

		var data = {}
		data["din"] = parseInt($(this).attr('value'), 10); //value=디비의 din컬럼

		$.ajax({
			type : "POST",
			url : "/bookmark/pass",
			data : JSON.stringify(data),
			contentType : "application/json;charset=UTF-8",
			success : function(data) {
				location.reload(); //새로고침
			},
			error : function(e) {
				alert('실패');
			}
		})

	});
</script>


</head>
<body>




	<div class="container-fluid center-block"
		style="width: 1000px; padding: 15px;" id="root">


		<c:if test="${!empty bookmarks }">
			<div class="row">
				<div class="col-sm">
					<ul class="list-group" id="bookmark-list">

						<c:forEach var="bookmark" items="${bookmarks}">
							<ul class="list-group mb-3">
								<li class="list-group-item">${bookmark.sentence.sentence }</li>
								<li class="list-group-item">${bookmark.sentence.mean }</li>

								<li class="list-group-item">${bookmark.sentence.memo }</li>

								<li class="list-group-item">${bookmark.tag }</li>
								<li class="list-group-item">${bookmark.sentence.regDate }</li>

								<li class="list-group-item">
									<div class="row">
										<div class="col-md-8"></div>
										<div class="ml-auto row mr-2">
										<c:if test="${pin==bookmark.sentence.pin }">
											<div class="delete" value="${bookmark.sentence.din }">
												<a class="btn btn-outline-info mr-1" role="button">삭제</a>
											</div>
										</c:if>
											<div class="pass" value="${bookmark.sentence.din }">
												<a class="btn btn-outline-info" role="button">외웠어요</a>
											</div>
										</div>
									</div>

								</li>


							</ul>
						</c:forEach>

					</ul>
				</div>
			</div>
		</c:if>



	</div>




</body>
</html>
