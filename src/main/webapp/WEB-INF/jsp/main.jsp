<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
<jsp:include page="top.jsp" flush="false" />
<script type="text/javascript" src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#search").submit(function(event) {
			event.preventDefault();
			fire_ajax_submit();
		});
	});
	function fire_ajax_submit() {

		var search = $("form[name=search]").serialize();

		$.ajax({
			type : "POST",
			url : "/tsearch",
			dataType : 'json',
			data : search,
			success : function(data) {
				$('#tagList').html(data[0].sentence);

			},
			error : function(e) {
				var json = e.responseText;
				$('#tagList').html(json);
			}
		});
	}

	$(function() {
		$("#addSentence").click(function(event) {
			console.log("추가버튼 클릭이벤트 실행");
		});
	});
</script>

</head>
<body>
	<div class="container-fluid center-block"
		style="width: 1000px; padding: 15px;">

		<div class="row">
			<form id="search" name="search" class="form-inline">
				<!-- inline여야 간격이 없이 메뉴처럼 나온다. ml-atuo : 우측으로 붙게하기-->
				<input class="form-control mr-sm-2" type="text" placeholder="단어 검색"
					name="tag" id="tag" />
				<button class="btn btn-success" id="btn" type="submit">검색</button>
			</form>
			<a id="addSentence" class="btn btn-outline-info ml-2"
				href="/addSentence" role="button">추가</a>

		<div class="card">
			<div id="tagList" class="card-body"></div>
		</div>

	</div>
	</div>
	


</body>
</html>
























