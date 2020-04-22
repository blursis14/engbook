<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
	
	
//추가잘되었을때
	$(function(){
		$("#sentence-form").submit(function () {
			  if ($(document.activeElement).attr('type') == 'submit'){
				  alert("추가되었습니다");
				  
				  var sentenceForm=$("form[name=sentence-form]").serialize();
				  
				  $.ajax({
					  type:"POST",
					  url:"/sadd",
					  data:sentenceForm,
					  success:function(){
						  alert('추가성공');
					  },
					  error:function(e){
						  alert('실패');
					  }
				  })
			  }
			    
			  else {
				  alert("제출하려면 엔터말고 버튼누르셈");  
				  event.preventDefault();}
			});
	});
	

	
	
//추가버튼눌렀을때 회원 폴더가져오는거
	$(function() {
		$("#addSentence").click(function(event) {
			console.log("추가버튼 클릭이벤트 실행"); 
			
			$.ajax({
				type:"POST",
				url:"/ffind",
				dataType:"json",
				success:function(data){
					$.each(data,function(key,value){
						var addContent=$("<option>"+value.folder+"</option>"); //회원이 가진 폴더들 보여줌
						$("#folder-select").append(addContent);
					})
					
				},
				error:function(e){
					var json=e.responseText;
					$('#folderList').html(json);
				}
			})
			
			
		});
	});
	

	
	function tag_clicked(){
		
		alert("tag클릭");
	
	}
	
	
	$(function(){
  
   // alert('시작');
    
});
	
	var param={
			page:0,
			size:2
	}
	
	if($("body").height()<$(window).height()){
		
		//alert("스크롤바음슴");
		fireListAjax();
	
	}
	
	$(window).scroll(function(){
		if($(window).scrollTop()+1>=$(document).height()-$(window).height()){
			param.page++;
			fireListAjax();
		}
	});
	
	function fireListAjax(){
		$.ajax({
	         type:'POST',
	         url:'/list/sentence',
	         dataType:'json',
	         data:JSON.stringify(param),
	         contentType:"application/json; charset=UTF-8",
	         success:function(data){
	        	 
	        	 //alert(data[0].sentence);
	        	 
	        	 $.each(data,function(key,value){
	        		 var $ul = $('<ul class="list-group mb-3">')
                     .append($('<li class="list-group-item">').text(value.sentence))
                     .append($('<li class="list-group-item">').text(value.mean))
                     .append($('<li class="list-group-item">').text(value.memo))
                     .append($('<li class="list-group-item">').text(value.regDate));
                     //.append($('<li class="list-group-item">').text(value.tag));                  
     
     // 부모 엘리먼트에 append
     $('#cardList').append($ul);
	        	 })
	        	 
	        	 
	         },
	         error:function(e){
	             alert('loadAjax실패');
	         }
	     
	     })
	}
</script>



</head>
<body>
	<div class="container-fluid center-block"
		style="width: 1000px; padding: 15px;"  id="whole">

		<div class="row">
			<form id="search" name="search" class="form-inline">
				<!-- inline여야 간격이 없이 메뉴처럼 나온다. ml-atuo : 우측으로 붙게하기-->
				<input class="form-control mr-sm-2 " type="text" placeholder="단어 검색"
					name="search-tag" id="search-tag" />
				<button class="btn btn-success" id="btn" type="submit">검색</button>
			</form>
			<c:if test="${!empty authInfo }">
				<!-- 로그인해야 추가할수있게 -->
				<!-- <button type="button" id="addSentence"
				class="btn btn-outline-info ml-2" data-toggle="modal"
				data-target="#addSentenceModal">추가</button> -->

				<a class="btn btn-outline-info ml-2" role="button" id="addSentence"
					data-toggle="modal" data-target="#addSentenceModal">추가</a>
			</c:if>

			<c:if test="${empty authInfo }">
				<a class="btn btn-outline-info ml-2" href="needLogin" role="button">추가</a>
			</c:if>
			<!-- 모달버튼 -->

			<!-- 모달 영역 -->
			<div class="modal fade" id="addSentenceModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">

							<h4 class="modal-title" id="myModalLabel">문장 추가</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">




								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">

							<form id="sentence-form" name="sentence-form" method="POST"
								action="main">
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
									태그:<br /> <input class="form-control" id="tag" name="tag"
										placeholder="태그는 띄어쓰기로 구분됩니다" />
								</div>
								<div id="folderList" class="container">
									폴더선택: <select id="folder-select" class="form-control"
										name="folder">

									</select>

								</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">등록</button>
							</form>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>



			<div class="container-fluid center-block"
				style="width: 1000px; padding: 15px;">
				<div class="row">
					<div class="col-sm" id="cardList">
						<ul class="list-group">
							<li class="list-group-item">문장</li>
							<li class="list-group-item">뜻</li>
							<li class="list-group-item">메모</li>
							<li class="list-group-item">태그</li>
							<li class="list-group-item">등록일</li>
							<ul class="list-group list-group-horizontal">
								<button class="list-group-item list-group-item-action">수정</button>
								<button class="list-group-item list-group-item-action">삭제</button>
							</ul>
						</ul>
					</div>



				</div>
			</div>


		</div>
	</div>



</body>
</html>
























