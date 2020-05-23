<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bookmark-list</title>
<script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<jsp:include page="../top.jsp" flush="false" />
<script type="text/javascript">
	var pin = "${pin}";

	var param = {
		page : 0,
		size : 3,
		folder : "${folder}"//컨트롤러에서 모델앤뷰로 추가한 폴더속성
	}

	if ($("body").height() < $(window).height()) {

		fireListAjax(); //스크롤바가 없는 처음 화면에서 리스트 가져오기 

	}

	$(window).scroll(
			function() {
				if ($(window).scrollTop() + 1 >= $(document).height()
						- $(window).height()) {
					param.page++;//스크롤바 바닥에 닿을 때 마다 북마크 두 개 씩 더 가져옴 
					fireListAjax();
				}
			});

	function fireListAjax() {
		$
				.ajax({
					type : 'POST',
					url : '/bookmark/list',
					dataType : 'json',
					data : JSON.stringify(param),
					contentType : "application/json; charset=UTF-8",

					success : function(data) {
					
						$
								.each(
										data,
										function(key, value) {

											var $ul = $(
													'<ul class="list-group mb-3">')
													.append(
															$(
																	'<li class="list-group-item">')
																	.text(
																			value.sentence.sentence))
													.append(
															$(
																	'<li class="list-group-item">')
																	.text(
																			value.sentence.mean))
													.append(
															$(
																	'<li class="list-group-item">')
																	.text(
																			value.sentence.memo))
													.append(
															$(
																	'<li class="list-group-item">')
																	.text(
																			value.tag))
													.append(
															$(
																	'<li class="list-group-item">')
																	.text(
																			value.sentence.regDate));
											
											if (value.sentence.pin == pin) { //센텐스 작성자인지, 단순 북마크 추가자인지 비교 후 작성자면 삭제&외웠어요 버튼, 추가자면 외웠어요버튼만 보이기

												$ul
														.append($(
																'<li class="list-group-item">')
																.append(
																		$(
																				'<div class="row">')
																				.append(
																						$(
																								'<div class="col-md-8">')
																								.append(
																										$(
																												'<div class="ml-auto row mr-2">')
																												.append(
																														$(
																																'<div class="delete">')
																																.attr(
																																		'value',
																																		value.sentence.din)
																																.append(
																																		$(
																																				'<a class="btn btn-outline-info mr-1" role="button">')
																																				.text(
																																						'삭제')))
																												.append(
																														$(
																																'<div class="pass">')
																																.attr(
																																		'value',
																																		value.sentence.din)
																																.append(
																																		$(
																																				'<a class="btn btn-outline-info mr-1" role="button">')
																																				.text(
																																						'외웠어요')))
																																						))));

											}else{
												  $ul
						                            .append($(
						                                '<li class="list-group-item">')
						                                .append(
						                                    $(
						                                        '<div class="row">')
						                                        .append(
						                                            $(
						                                                '<div class="col-md-8">')
						                                                .append(
						                                                    $(
						                                                        '<div class="ml-auto row mr-2">')
						                                                        .append(
						                                                            $(
						                                                                '<div class="pass">')
						                                                                .attr(
						                                                                    'value',
						                                                                    value.sentence.din)
						                                                                .append(
						                                                                    $(
						                                                                        '<a class="btn btn-outline-info mr-1" role="button">')
						                                                                        .text(
						                                                                            '외웠어요')))
						                                                ))));
											}
											
											

											// 부모 엘리먼트에 append
											$('#bookmark-list').append($ul);
										})

					},
					error : function(e) {
						alert('loadAjax실패');
					}

				})
	}

	//삭제 버튼 눌림
	$(document).on('click', '.delete', function(e) {

		var data = {}
		data["din"] = parseInt($(this).attr('value'), 10); //value=디비의 din컬럼
		 
		$.ajax({
			type : "POST",
			url : "/bookmark/delete",
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
		<div class="container-fluid center-block" style="width: 1000px; padding: 15px;" id="root">
				<div class="row">
						<div class="col-sm">
								<ul class="list-group" id="bookmark-list">
								</ul>
						</div>
				</div>
		</div>
</body>
</html>
