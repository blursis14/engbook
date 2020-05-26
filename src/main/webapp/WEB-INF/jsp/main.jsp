<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
<script type="text/javascript" src="webjars/jquery/3.3.1/jquery.min.js"></script>
<jsp:include page="top.jsp" flush="false" />
<script type="text/javascript">
	
	//검색 에이젝스 테스트
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

	//추가버튼눌렀을때 폴더목록 가져오는거
	$(function() {
		$("#addSentence").click(
				function(event) {
					console.log("추가버튼 클릭이벤트 실행");

					$.ajax({
						type : "POST",
						url : "/ffind",
						dataType : "json",
						success : function(data) {
							$.each(data, function(key, value) {
								var addContent = $("<option>" + value.folder
										+ "</option>"); //회원이 가진 폴더들 보여줌
								$("#folder-select").append(addContent);
							})

						},
						error : function(e) {
							var json = e.responseText;
							$('#folderList').html(json);
						}
					})

				});
	});

	//문장추가
	$(function() {
		$("#sentence-form").submit(function() {
			if ($(document.activeElement).attr('type') == 'submit') {

				var sentenceForm = $("form[name=sentence-form]").serialize();

				$.ajax({
					type : "POST",
					url : "/sadd",
					data : sentenceForm,
					success : function() {

					},
					error : function(e) {
						alert('실패');
					}
				})
			}

			else {
				alert("제출하려면 엔터말고 버튼누르셈");
				event.preventDefault();
			}
		});
	});

	function tag_clicked() {

		alert("tag클릭");

	}

	$(function() {

		// alert('시작');

	});

	var dinAndFolder = {} //북마크 추가할 때 필요한-센텐스의 din이랑 선택한 folder

	$(document).on(
			'click',
			'.addBookmark',
			function(e) {

				//북마크추가 버튼 클릭하면 din을 세팅해놓음  
				dinAndFolder["din"] = parseInt($(this).attr('value'), 10);

				if (document.getElementById('login')) { //top.jsp의 코드에서 지금 보이는 게 로그인버튼인지, 로그아웃 버튼인지 알 수 있다. 이에 따라 북마크를 추가하거나 로그인을 요구할 수 있음
					alert('로그인이 필요한 서비스입니다.');
					window.location.replace("http://localhost:8080/login"); //로그인 안했으면 로그인페이지로 이동
				}
				if (document.getElementById('logout')) { //로그인 되어있으면 폴더불러오고,폴더 선택하는 모달 보이기 
					$("#addBookmarkModal").modal('show');

					$.ajax({
						type : "POST",
						url : "/ffind",
						dataType : "json",
						success : function(data) {
							$.each(data,
									function(key, value) {
										var addContent = $("<option>"
												+ value.folder + "</option>"); //폴더가져와서 모달에 폴더선택란에 보이기
										$("#bookmark-folder-select").append(
												addContent);
									})
						},
						error : function(e) {
							var json = e.responseText;
							$('#bookmark-folder-list').html(json);
						}
					})
				}

			})

	//모달에서 폴더선택 후 북마크추가
	$(function() {
		$("#bookmark-folder").submit(
				function() {

					dinAndFolder["folder"] = $(
							"#bookmark-folder-select option:selected").val(); //선택한 폴더 이름을 변수에 저장

					//alert(JSON.stringify(dinAndFolder));

					$.ajax({
						type : "POST",
						url : "/bookmark/add",
						data : JSON.stringify(dinAndFolder),
						contentType : "application/json;charset=UTF-8",
						success : function(data) {
							alert('success');
						},
						error : function(e) {
							alert('실패');
						}
					})
				})
	})
	  var param = {
        page : 0,
        size : 2
      }
	if ($("body").height() < $(window).height()) {

		fireListAjax(); //스크롤바가 없는 처음 화면에서 리스트 가져오기 

	}


	
	$(window).scroll(
			function() {
				if ($(window).scrollTop() + 1 >= $(document).height()
						- $(window).height()) {
					param.page++;//스크롤바 바닥에 닿을 때 마다 센텐스 두 개 씩 더 가져옴 
					fireListAjax();
				}
			});

	

	function fireListAjax() {
		$
				.ajax({
					type : 'POST',
					url : '/list/sentence',
					dataType : 'json',
					data : JSON.stringify(param),
					contentType : "application/json; charset=UTF-8",

					success : function(data) {

						$.each(data,
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
																			value.sentence.regDate))
													.append(
															$(
																	'<ul class="list-group list-group-horizontal">')
																	.append(
																			$(
																					'<div class="ml-auto row mr-1">')
																					.append(
																							$(
																									'<div class="addBookmark">')
																									.attr(
																											'value',
																											value.sentence.din)
																									//din을 알아야 추가하니까 value속성추가
																									.append(
																											$(
																													'<button class="list-group-item list-group-item-primary">')
																													.text(
																															'북마크에 추가')))));

											// 부모 엘리먼트에 append
											$('#cardList').append($ul);
										})

					},
					error : function(e) {
						alert('loadAjax실패');
					}

				})
	}
</script>
</head>
<body>
		<div class="container-fluid center-block" style="width: 1000px; padding: 15px;" id="whole">
				<div class="row">
						<form id="search" name="search" class="form-inline">
								<!-- inline여야 간격이 없이 메뉴처럼 나온다. ml-atuo : 우측으로 붙게하기-->
								<input class="form-control mr-sm-2 " type="text" placeholder="단어 검색" name="search-tag" id="search-tag" />
								<button class="btn btn-success" id="btn" type="submit">검색</button>
						</form>
						<c:if test="${!empty authInfo }">
								<!-- 로그인해야 sentence를 추가할 수 있게함 -->
								<a class="btn btn-outline-info ml-2" role="button" id="addSentence" data-toggle="modal" data-target="#addSentenceModal">추가</a>
						</c:if>
						<c:if test="${empty authInfo }">
								<a class="btn btn-outline-info ml-2" href="needLogin" role="button">추가</a>
						</c:if>
						
						<!-- sentence추가 모달 영역 -->
						<div class="modal fade" id="addSentenceModal" tabindex="-1" role="dialog">
								<div class="modal-dialog" role="document">
										<div class="modal-content">
												<div class="modal-header">
														<h4 class="modal-title">문장 추가</h4>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																<span aria-hidden="true">×</span>
														</button>
												</div>
												<div class="modal-body">
														<form id="sentence-form" name="sentence-form" method="POST" action="main">
																<div class="form-group">
																		문장:<br />
																		<textarea class="form-control" id="sentence" name="sentence"></textarea>
																</div>
																<div class="form-group">
																		뜻:<br>
																		<textarea class="form-control" id="mean" name="mean"></textarea>
																</div>
																<div class="form-group">
																		메모:<br />
																		<textarea class="form-control" id="memo" name="memo"></textarea>
																</div>
																<div class="form-group">
																		태그:<br /> <input class="form-control" id="tag" name="tag" placeholder="태그는 띄어쓰기로 구분됩니다" />
																</div>
																<div id="folderList" class="container">
																		폴더선택: <select id="folder-select" class="form-control" name="folder">
																		</select>
																</div>
												</div>
												<div class="modal-footer">
														<button type="submit" class="btn btn-primary">등록</button>
														</form>
														<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
												</div>
										</div>
								</div>
						</div>
						
						<!-- 북마크 추가 폴더선택 모달 -->
						<div class="modal fade" id="addBookmarkModal" tabindex="-1" role="dialog">
								<div class="modal-dialog" role="document">
										<div class="modal-content">
												<div class="modal-header">
														<h4 class="modal-title">북마크 추가</h4>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																<span aria-hidden="true">x</span>
														</button>
												</div>
												<div class="modal-body">
														<form id="bookmark-folder" name="bookmark-folder" method="POST" action="main">
																<div id="bookmark-folder-list" class="container">
																		폴더선택:<select id="bookmark-folder-select" class="form-control" name="folder"></select>
																</div>
												</div>
												<div class="modal-footer">
														<button type="submit" class="btn btn-primary">등록</button>
														</form>
														<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
												</div>
										</div>
								</div>
						</div>
						<div class="container-fluid center-block" style="width: 1000px; padding: 15px;">
								<div class="row">
										<div class="col-sm" id="cardList"></div>
								</div>
						</div>
						</div></div>
</body>
</html>
