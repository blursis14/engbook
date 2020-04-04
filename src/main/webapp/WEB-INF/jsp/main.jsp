<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
<jsp:include page="top.jsp" flush="false"/>
</head>
<body>


<form id="search" name="search" >
단어 검색 <input type="text" name="tag" id="tag" />
<button id="btn" type="submit" >검색</button>
</form>

	<div id="tagList"></div>

 
<script type="text/javascript" src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#search").submit(function(event){
		event.preventDefault();
		fire_ajax_submit();
	});
});
function fire_ajax_submit(){
	
	var search=$("form[name=search]").serialize();
	
	$.ajax({
		type:"POST",
		url:"/tsearch",
		dataType:'json',
		data:search,
		success:function(data){
			$('#tagList').html(data[0].sentence);
			
		},
		error:function(e){
			var json=e.responseText;
			$('#tagList').html(json);
		}
	});
}
</script>
</body>
</html>
























