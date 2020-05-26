<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edit-folder</title>
<script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<jsp:include page="../top.jsp" flush="false" />
<script type="text/javascript">





$(document).on('click','.delete',function(e){ //삭제버튼눌림
	
	var folder={}
	folder["folder"]=$(this).attr('value');//삭제할 폴더이름 저장
	
	//alert(JSON.stringify(folder));
	
	var makeSure=confirm("폴더의 북마크도 함께 삭제됩니다.삭제하시겠습니까?");
	
	if(makeSure){
		
		$.ajax({
			type:"POST",
			url:"/folder/delete",
			data:JSON.stringify(folder),
			contentType:"application/json;charset=UTF-8",
			success:function(data){
				alert('삭제되었습니다.');
				location.reload();
			},
			error:function(e){
				alert('삭제실패');
			}
		})
		
		
	}
	else{
		alert('삭제취소');
	}
	
})


var editFolder={}//수정전 폴더 이름,수정후 폴더 이름  

$(document).on('click','.edit',function(e){ //수정버튼눌림
	  
	  editFolder["old"]=$(this).attr('value');//수정 전 폴더이름 저장
	
	  
	})


$(function(){
	$("#folder-form").submit(function(){
	
		editFolder["new"]=$('#folder').val(); //새로운 폴더이름 폼에서 가져오기
		
		alert(JSON.stringify(editFolder));
		
		$.ajax({
			type:"POST",
			url:"/folder/edit",
			data:JSON.stringify(editFolder),
		  contentType:"application/json;charset=UTF-8",
			success:function(){
				alert('수정되었습니다.');
				location.reload();
			},
			error:function(e){
				alert('실패');
			}
		})
	
	
	})


})



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
																						<button class="btn btn-outline-info" data-toggle="modal" data-target="#editModal">수정</button>
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
		
		<!-- 폴더 수정 모달 -->
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title">폴더명 수정</h4>
		        <button type="button" class="close" data-dismiss="modal" aria-label="close">
		          <span aria-hidden="true">X</span>
		        </button>
		        </div>
		      <div class="modal-body">
		        <form id="folder-form" name="folder-form" method="POST" >
		          <div class="form-group">
		            폴더명<br/>
		            <textarea class="form-control" id="folder" name="folder"></textarea>
		            </div>
		        </div>
		        <div class="modal-footer">
		          <button type="submit" class="btn btn-primary">수정</button>
		          </form>
		          <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
		        </div>
		        </div>
		  </div>
		</div>
</body>
</html>








