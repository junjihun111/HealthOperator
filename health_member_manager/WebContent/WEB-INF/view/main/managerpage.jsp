<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html;">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0. user-scalable=no," />

<script type="text/javascript" src="/health_member_manager/scripts/jquery.js"></script>
<script src="/health_member_manager/scripts/jquery-ui.js"></script>
<link type="text/css" href="/health_member_manager/css/jquery-ui.css" rel="stylesheet" />
<link href="/health_member_manager/css/base.css" rel="stylesheet" />
<script type="text/javascript" src="/health_member_manager/scripts/function/verify.js"></script>
<%@ include file="/scripts/function/dialog.jsp" %>
<script type="text/javascript">
$(document).ready(function(){

	
	//회원선택 버튼을 누르면 회원번호 옆에 체크박스가 생기도록
 	var i=1;
	$("#memberChoice").on("click", function(){
 			i = i+1;
			
 			if(i%2==0){
 				$("tr td:nth-child(1)").prepend("<input type='checkbox' name='box'>");
 				$("tr th:nth-child(1)").prepend("<input type='checkbox' id='allCheck'>")
 			}
 			
 			else{
 				$("input[id=allCheck]").remove();	
 				$("input[name=box]").remove();		
 			}
 	});
	
	
	//모든전체 체크박스 선택
	$("tr th:nth-child(1)").on("click",function(){
	        if($("#allCheck").prop("checked")){
	            $("input[name=box]").prop("checked",true);
	        }else{
	            $("input[name=box]").prop("checked",false);
	        }
	});
 	
	
	//체크된 회원들을 삭제
 	$("#checkedDelete").click(function(){
 		var no= "";
 		var page ="page="+$("#currentPage").val();
 		
 		$("input[name=box]:checked").each(function(idx){
 			if(idx==$("input[name=box]:checked").length-1){
 				no+="no="+$(this).parent("td").first().text();
 				
 			}
 			else{
 				no+="no="+$(this).parent("td").first().text()+"&";
 			}
 		});
 		var nos=no+"&"+page;
 		
 		 $.ajax({
 			"url":"/health_member_manager/health/deleteByMemberNo.do",
 			"type":"post",
 			"dataType":"json",
 			"data":nos,
 			"success":function(obj){
 				var txt="";
 				$.each(obj,function(){
 					txt = txt+"<tr><td>"
 					+this.memberNo+"<td>"
 					+this.memberName+"<td>"
 					+this.phoneEnd+"<td>"
 					+this.startDay+"<td>"
 					+this.endDay
					+"<td><a href='/health_member_manager/health/shoescage.do?memberNo="+this.memberNo+"'>"
					+this.shoesNo+"</a></td><td><a href='/health_member_manager/health/attendance.do?memberNo="
					+this.memberNo+"&memberName="+this.memberName+"'>출석하기</a>/<a href='/health_member_manager/health/attendanceList.do?memberNo="
					+this.memberNo+"&page=${sessionScope.pageBean.page}'>출석내역보기</a></td><br>";
 				});
 				$("#listTbody").html(txt);
 				$("input[id=allCheck]").remove();
 				alert("회원을 삭제하였습니다.");
 			},
 			"error":function(xhr,msg, msg2){
 				
 			},
 			"beforeSend":function(){
 				if(no==0){
 					alert("회원선택후 삭제해주세요");
 					return false;
 				}
 			}
 		});
 	});
 	
	
	//tbody중에  tr 자식의 두번째 td들을 클릭햇을때 나타나는 이벤트 -> 하나의 회원의 정보를 보여주는 이벤트
 	$("#listTbody").on("click","tr td:nth-child(2)" ,function(){

		 var no ="no="+$(this).prev().text();
		 var page="page="+${sessionScope.pageBean.page};
		 //alert(no);
		 var nopage=no+"&"+page;
	 	$.ajax({
			"url":"/health_member_manager/health/noSearchUpdate.do",
			"data":nopage,
			"dataType":"json",
			"type":"post",
			"success":function(obj){
				//alert(obj);
				$("#memberNo1").html(obj.memberNo);
				$("#memberName1").html(obj.memberName);
				$("#phoneNumber1").html(obj.phoneFront);
				$("#phoneNumber2").html(obj.phoneMiddle);
				$("#phoneNumber3").html(obj.phoneEnd);
				
				$("#startDay1").html(obj.startDay);
				$("#endDay1").html(obj.endDay);
				$("#shoesNo1").html(obj.shoesNo);
				$("#birthday1").html(obj.birthday);
				$("#address1").html(obj.address);
				$("#email1").html(obj.email);
				$("#returnPrice1").html(obj.returnPrice);
				$("#dialog").dialog({modal:true,width:450});
				//alert($("#memberNo").text());
			},
			
			"error":function(xhr, msg, msg2){
				alert("오류발생"+msg2);
			}
		});
	});
 	 	
	
	
	// 하나의 회원 dialog에서 수정버튼을 눌렀을 때 수정할 수 있는 form이 나오는 이벤트
  	$("#updatePage").click(function(){
  	
  		//alert($("#memberNo1").text());
  		$("#memberNo").val($("#memberNo1").text());
  		$("#memberName").val($("#memberName1").text());
  		$("#phoneFront").val($("#phoneNumber1").text());
  		$("#phoneMiddle").val($("#phoneNumber2").text());
  		$("#phoneEnd").val($("#phoneNumber3").text());
  		$("#startDatepicker").val($("#startDay1").text());
  		$("#endDatepicker").val($("#endDay1").text());
  		$("#shoesNo").val($("#shoesNo1").text());
  		$("#birthdayDatepicker").val($("#birthday1").text());
  		$("#address").val($("#address1").text());
  		$("#email").val($("#email1").text());
  		$("#returnPrice").val($("#returnPrice1").text());
  		$("#updateMemberPage").dialog({modal:true,width:450});	
  		
  	});
  	
  	
  	//마우스를 클릭햇을 때 사용중인 사물함의 번호를 보여줌
  	$("#usingNo").on("mousedown",function(){
  		$.ajax({
  			"url":"/health_member_manager/health/usingShooseNo.do",
  			"dataType":"text",
  			"type":"post",
  			"success":function(obj){
  				alert("사용중인 신발장번호 :"+obj);
  			},
  			"error":function(xhr, msg,msg2){
  				alert("오류발생"+msg2)
  			}
  		})
  	});
  	
  	
});

</script>
</head>
<body>
	<br>
	<br>
	<br>
	<a href="/health_member_manager/health/memberJoin.do"><button id="join">회원가입</button></a>
	<a href="/health_member_manager/health/healthMemberPaging.do?page=${sessionScope.pageBean.page}"><button id="listBtn">회원 조회</button></a>
	
	<form action ="/health_member_manager/health/nameSearch.do?" method="get" style="float:right;margin:0;">
	<input type="text" name="name">
	<input type="submit" value="이름조회">
	</form>			
	
	<form action="/health_member_manager/health/noSearch.do" method="get" style="float:right;margin:0;">
	<input type="text" name="searchNo">
	<input type="submit" value="No조회">
	</form>
	
	<button id="memberChoice">회원선택</button>
	<button id="checkedDelete">회원삭제</button>
	<a href="/health_member_manager/health/endedMember.do?page=${sessionScope.pageBean.page }"><button>만료된 회원 조회</button></a>
	<a href="/health_member_manager/health/endedShoeNo.do?page=${sessionScope.pageBean.page }"><button>만료된 신발장 조회</button></a>
	
	<input type="hidden" value="${sessionScope.pageBean.page}" id="currentPage">
	<hr>
	<br>
	<table border='1' id="HealthMemberTB">
		<thead>
			<tr>
				<th id="memberNumber">회원번호</th>
				<th>이름</th>
				<th>핸드폰뒷자리</th>
				<th>시작일</th>
				<th>만료일</th>
				<th>신발장번호</th>
				<th>출석체크</th>
			</tr>
		</thead>
		<tbody id="listTbody">
			<c:forEach items="${sessionScope.healthMemberList}" var="list">
				<tr>
					<td>${list.memberNo}</td>
					<td>${list.memberName}</td>
					<td>${list.phoneEnd}</td>
					<td><fmt:formatDate value="${list.startDay}" type="date" pattern="yyyy년 MM월 dd일 " /></td>
					<td><fmt:formatDate value="${list.endDay}" type="date" pattern="yyyy년 MM월 dd일" /></td>
					<td><a href="/health_member_manager/health/shoescage.do?name=${sessionScope.name}&memberNo=${list.memberNo}&memberName=${list.memberName}&page=${sessionScope.pageBean.page}">${list.shoesNo}</a></td>
					<td><a href="/health_member_manager/health/attendance.do?memberNo=${list.memberNo}&memberName=${list.memberName}">출석하기</a>
						/ <a href='/health_member_manager/health/attendanceList.do?memberNo=${list.memberNo}&page=${sessionScope.pageBean.page}'>출석내역보기</a></td>
				</tr>
				</c:forEach>
		</tbody>
		<tfoot>
		</tfoot>
	</table>

<%-- 회원정보를 뿌려주는 form --%>
	<div id="dialog" title="회원 정보" style="display: none;">
		<form id="test" method="post" action="/health_member_manager/health/updateMemberPage.do">
			<table border=1>
				<tr><th>회원번호</th><td id="memberNo1"></td></tr>
				<tr><th>회원이름</th><td id="memberName1"></td></tr>
				<tr><th>핸드폰번호</th><td><span id="phoneNumber1"></span>- <span id="phoneNumber2"></span>-<span id="phoneNumber3"></span></td></tr>
				<tr><th>시작일</th><td id="startDay1"></td></tr>
				<tr><th>만료일</th><td id="endDay1"></td></tr>
				<tr><th>신발장번호</th><td id="shoesNo1"></td></tr>
				<tr><th>생년월일</th><td id="birthday1"></td></tr>
				<tr><th>주소</th><td id="address1"></td></tr>
				<tr><th>이메일</th><td id=email1></td></tr>
				<tr><th>환불금액</th><td id="returnPrice1"></td></tr>
			</table>
		</form>
		<center><button id="updatePage">수정</button></center>
	</div>



<%-- 한명의 회원정보가 나왔을 때, 수정버튼을 누르면 나오는 폼 --%>  
	<div id=updateMemberPage title="회원 수정 " style="display: none;">
		<form action="/health_member_manager/health/updateTransfer.do" method="post" name="update" onsubmit="return updateFormCheck()">
			<input type="hidden" value="${sessionScope.pageBean.page}" name="page">
			<h2>회원정보수정</h2>
			<input type="hidden" id="memberNo" name="memberNo"><br>
			회원이름 :<input type="text" id="memberName" name="memberName" size="4"><span class="errorspans"><form:errors path="healthMemberForm.memberName"/></span><br>
			핸드폰번호<select id="phoneFront" name="phoneFront">
				<c:forEach items="${sessionScope.codeValue}" var="codevalue">
					<option>${codevalue.codeValue}</option>
				</c:forEach>

			</select> <input type="text" id="phoneMiddle" name="phoneMiddle" size="4">-<input type="text" id="phoneEnd" name="phoneEnd" size="4" onblur="return formCheck()"><br>
			<span class="errorspans"><form:errors path="healthMemberForm.phoneMiddle"/></span><br>
			<span class="errorspans"><form:errors path="healthMemberForm.phoneEnd"/></span><br>
			시작일 :<input type="text" id="startDatepicker" name="startDay" readonly="readonly" ><span class="errorspans"><form:errors path="healthMemberForm.startDay"/></span><br>
			만료일 :<input type="text" id="endDatepicker" name="endDay"  readonly="readonly"><span class="errorspans"><form:errors path="healthMemberForm.endDay"/></span><br>
			생년월일 :<input type="text" id="birthdayDatepicker" name="birthday" readonly="readonly"><span class="errorspans"><form:errors path="healthMemberForm.birthday"/></span><br>
			주소:<input type="text" id="address" name="address"><br>
			이메일:<input type="text" id="email" name="email"><br>
			환불금액:<input type="text" id="returnPrice" name="returnPrice"><br>
			<br> <br>
			<center><input type="submit" value="수정완료" id="updateSuccess"><input type="reset" value="다시쓰기"></center>
		</form>
	</div>
	



	<div id="shoescageInfo" title="신발장 번호" style="display: none">
		회원번호 : ${requestScope.shoescage.memberNo}<br> 
		회원 신발장 번호 : ${requestScope.shoescage.shoesNo}<br> 
		신발장시작일 : <fmt:formatDate value="${requestScope.shoescage.startDay}" pattern="yyyy년 MM월 dd일" /><br> 
		신발장 만료일 :<fmt:formatDate value="${requestScope.shoescage.endDay}" pattern="yyyy년 MM월 dd일" /><br> 
		<a href="/health_member_manager/health/ShoesCageStop.do?name=${requestScope.name}&page=${sessionScope.pageBean.page}&memberNo=${requestScope.shoescage.memberNo}"><button>이용중지</button></a>
	</div>
		<div id="shoescageInfo2" title="신발장 번호" style="display: none">
		회원번호 : ${requestScope.noForSearch.memberNo}<br> 회원 신발장 번호 :
		${requestScope.noForSearch.shoesNo}<br> 신발장시작일 :
		<fmt:formatDate value="${requestScope.noForSearch.startDay}"
			pattern="yyyy년 MM월 dd일" />
		<br> 신발장 만료일 :
		<fmt:formatDate value="${requestScope.noForSearch.endDay}"
			pattern="yyyy년 MM월 dd일" />
		<br> <a
			href="/health_member_manager/health/ShoesCageStop.do?page=${sessionScope.pageBean.page}&memberNo=${requestScope.noForSearch.memberNo}"><button>이용중지</button></a>
	</div>

	
		<div id="shoescageInfoForNo" title="신발장 번호" style="display: none">
		회원번호 : ${requestScope.shoescageForNo.memberNo}<br> 회원 신발장 번호 :
		${requestScope.shoescageForNo.shoesNo}<br> 신발장시작일 :
		<fmt:formatDate value="${requestScope.shoescageForNo.startDay}"
			pattern="yyyy년 MM월 dd일" />
		<br> 신발장 만료일 :
		<fmt:formatDate value="${requestScope.shoescageForNo.endDay}"
			pattern="yyyy년 MM월 dd일" />
		<br> <a
			href="/health_member_manager/health/ShoesCageStop.do?page=${sessionScope.pageBean.page}&memberNo=${requestScope.shoescageForNo.memberNo}"><button>이용중지</button></a>
	</div>
	
	
	<div id="shoesCageRegisterPage" title="신발장등록" style="display: none">
		<b2>신발장등록</b2>
		<br>
		<form action="/health_member_manager/health/shoesRegister.do" method="get" class="f_register" name="shoesCage" onsubmit="return shoesCageCheck()">
			<input type="hidden" value="${requestScope.name }" name="name">
			<input type="hidden" value="${sessionScope.pageBean.page}" name="page">
			회원번호 :${requestScope.memberNo}<input type="hidden" name="memberNo" value="${requestScope.memberNo}"><br>
			신발장번호:<input type="text" name="shoesNo" id="usingNo" size="4">
			<c:if test="${requestScope.DulplicateMessage!= null }">
				<span class="errorspans">${requestScope.DulplicateMessage }</span>
			</c:if><br>
			시작일 :<input type="text" name="startDay" id="startDay"  readonly="readonly"><span class="errorspans"><form:errors path="shoesCageForm.startDay"/></span><br>
			만료일:<input type="text" name="endDay" id="endDay"  readonly="readonly"><span class="errorspans"><form:errors path="shoesCageForm.endDay"/></span><br> 
				<input type="submit" value="등록완료">
		</form>
	</div>

	<div id="endedShoeNoDialog" title="만료된 신발장 조회" style="display: none">
		<form action="/health_member_manager/health/deleteEndedShoesCage.do">
		<c:if test="${requestScope.endedShoesCageErrorMessage != null }">
			<span class="errorspans">${requestScope.endedShoesCageErrorMessage}</span>
		</c:if>
		<table>
			<thead>
				<tr>
					<th>회원번호</th>
					<th>신발장번호</th>
					<th>시작일</th>
					<th>만료일</th>
				</tr>
			</thead>
			 <c:forEach items="${requestScope.endedShoeCageList }" var="endedlist">
			<tr>
			<td>
			<input type="checkbox" name="memberNo" value="${endedlist.memberNo}">${endedlist.memberNo}
			</td>
			<td>${endedlist.shoesNo}</td>
			<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${endedlist.startDay }"/></td>
			<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${endedlist.endDay }"/></td>
			</tr>
			</c:forEach>
			</table><br>
			<div align="right"><input type="submit" value="삭제"></div>
		</form>
	</div>

<div id="pagingLayer" >
	<!--첫페이지로 이동  -->
	<a href="/health_member_manager/health/healthMemberPaging.do?page=1">
		◁&nbsp;&nbsp; </a>


	<!--이전 페이지 그룹이 있다면, 페이지 그룹으로 이동  -->
	<c:choose>
		<c:when test="${sessionScope.pageBean.previousPageGroup }">
			<a
				href="/health_member_manager/health/healthMemberPaging.do?page=${sessionScope.pageBean.beginPage-1 }">
				◀ </a>	
 		&nbsp;&nbsp;
 	</c:when>
		<c:otherwise>
		◀&nbsp;&nbsp;
		
 	</c:otherwise>
	</c:choose>

	<c:forEach begin="${sessionScope.pageBean.beginPage }" end="${sessionScope.pageBean.endPage }" var="p">
		<c:choose>
			<c:when test="${p != sessionScope.pageBean.page }">
				<a href="/health_member_manager/health/healthMemberPaging.do?page=${p }">
					${p } </a>
			&nbsp;&nbsp;
		</c:when>
			<c:otherwise>
			[${p }]&nbsp;&nbsp;
		</c:otherwise>
		</c:choose>
	</c:forEach>


	<c:choose>
		<c:when test="${sessionScope.pageBean.nextPageGroup }">
			<a
				href="/health_member_manager/health/healthMemberPaging.do?page=${sessionScope.pageBean.endPage+1 }">
				▶&nbsp;&nbsp; </a>
		</c:when>
		<c:otherwise>
		▶&nbsp;&nbsp;
	</c:otherwise>
	</c:choose>


	<!--마지막 페이지로 이동  -->
	<a
		href="/health_member_manager/health/healthMemberPaging.do?page=${sessionScope.pageBean.totalPage}">
		▷ </a>
</div>

</body>
</html>