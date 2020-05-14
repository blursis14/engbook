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

//폴더추가
$(function(){
	$("#folder-form").submit(function(){
		if($(document.activeElement).attr('type')=='submit'){
			
			var newFolder=$("form[name=folder-form]").serialize();
			
			$.ajax({
				type:"POST",
				url:"/fadd",
				async:false,
				dataType:'text',
				data:newFolder,
				success:function(data){
					alert("success 호출됨");
					alert((JSON.parse(data)).folder);
				
				},
				error:function(e){
					alert('실패');
				}
				
			})
			
		}
		else{
			alert("엔터말고 추가버튼을 누르세요");
			event.preventDefault();
		}
		
	});
});





</script>





</head>
<body>

	<div class="container-fluid center-block"
		style="width: 1000px; padding: 15px;">

		
			<div class="row">
				<div class="col-sm">
					<ul class="list-group" id="folder-list">

						<c:forEach var="folder" items="${folders}">
							<a class="btn btn-outline-info mb-1" role="button" 
								href="/bookmark/${folder.folder }">
								${folder.folder }</a>
						</c:forEach>
		
						<a class="btn btn-outline-primary mb-1" role="button" id="addFolder"
							data-toggle="modal" data-target="#addFolderModal">폴더 추가</a>
						
						
					</ul>
				</div>
			</div>
	



<!-- 새폴더 모달 -->
<div class="modal fade" id="addFolderModal" tabindex="-1"
	rold="dialog" >
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
			
			<h4 class="modal-title">폴더 추가</h4>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			<span aria-hidden="true">x</span>
			</button></div>
			<div class="modal-body">
				<form id="folder-form" name="folder-form" action="bookmark" >
					<div class="form-group">
						
						<input class="form-control" id="newFolder" name="newFolder"
						placeholder="폴더 이름을 입력하세요"/>
						</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">추가</button>
							</form>
							<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
							</div>
						</div></div>
	</div>





		
	</div>

</body>
</html>


























