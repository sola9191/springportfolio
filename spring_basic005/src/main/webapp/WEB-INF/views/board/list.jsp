<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false"%>

<jsp:include   page="../inc/header.jsp" />

<!-- Main content -->
<script>
if ("${insert}" == "success") {alert("글쓰기에 성공했습니다.");} 
if ("${insert}" == "fail") {alert("글쓰기에 실패했습니다. 관리자에게 문의해주세요.");} 
if ("${delete}" == "success") {alert("글삭제에 성공했습니다.");} 
if ("${delete}" == "fail") {alert("글삭제에 실패했습니다. 비밀번호를 확인해주세요.");} 

$(function(){
	 $("#search").on("keyup", function(){ 
		 $(".sresult tbody").html();
		var btitle =  $("#search").val();
		if(btitle=="") { $("#search").focus();}
		else{
		$.ajax({
			url: "${pageContext.request.contextPath}/board/search",
			type: "get",
			dataType:"json",
			data: {"btitle" : btitle},
			success:function(data){
				var list = data.list;
				console.log(list);
			
				$(".sresult tbody").empty();
				
				for(var i = 0; i<list.length ; i++){
						
					
					var tr = $("<tr>");
					var td1 = $("<td>").html(i+1);
					var td2 = $("<td>");
					var a = $("<a href='${pageContext.request.contextPath}/board/board_detail?bno="+list[i].bno+"'>").html(list[i].btitle);
					var td3 = $("<td>").html(list[i].bname);
					var td4 = $("<td>").html(list[i].bdate);
					var td5 = $("<td>").html(list[i].bhit);
					td2.append(a);
					tr.append(td1).append(td2).append(td3).append(td4).append(td5);
					$(".sresult tbody").append(tr);				
				}			
				
			}, error: function(xhr, textStatus, errorThrown){
				$(".sresult tbody").html(textStatus + "(HTTP- " +xhr.status+"/" +errorThrown);
				
			}
		});	
	}
	});	
	
});


</script>
<!-- Main content -->

<section class="container"  style="margin-top:15%">
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>BNO</th>
				<th>TITLE</th>
				<th>WRITER</th>
				<th>DATE</th>
				<th>HIT</th>
			</tr>
		</thead>
		<tbody>		
		<c:forEach items="${list}" var="boardVO"  varStatus="status" >
			<tr><td>${list.size()-status.index}</td>
			<td><a href="<%=request.getContextPath()%>/board/board_detail?bno=${boardVO.bno}">${boardVO.btitle}</a></td>
			<td>${boardVO.bname}</td>
			<td>${boardVO.bdate}</td>
			<td>${boardVO.bhit}</td></tr>			 
		</c:forEach>	
			<tr  class="text-right">
				<td  colspan="5">
				
					<a href='<%=request.getContextPath()%>/board/board_write' class="btn btn-default"> 글쓰기</a>
				</td>
			</tr>	
		</tbody>			
	</table>
</section>

<div class="container"  style="margin-top:5%">
	<label for="search">SEARCH - Spring Ajax 기능 사용</label>
	<input type="search" id="search" name="search" class="form-control" placeholder="조회를 원하는 글제목을 입력해주세요">
	
	<div class="sresult">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>BNO</th>
					<th>TITLE</th>
					<th>WRITER</th>
					<th>DATE</th>
					<th>HIT</th>
				</tr>
			</thead>
			<tbody>		
		</tbody>
		</table>
	</div>
</div>


<%@include file="../inc/footer.jsp"%>
