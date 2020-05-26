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
