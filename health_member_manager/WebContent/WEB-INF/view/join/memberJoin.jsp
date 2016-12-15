
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript"
	src="/health_member_manager/scripts/jquery.js"></script>
<script src="/health_member_manager/scripts/jquery-ui.js"></script>
<link type="text/css" href="/health_member_manager/css/jquery-ui.css"
	rel="stylesheet" />

<style type="text/css">

.errorspans {
font-weight:bold;
color:red;
font-size:12px;
}

table {
	width: 80%;
	border-collapse: collapse;
	text-align: center;
	font-family: 'Trebuchet MS';
}

td, th {
	text-align: center;
	font-size: 10pt;
	border: 1px solid #231EB7;
	height: 30px;
	padding: 5px;
}

th {
	background-color: #5B5AFF;
	color: #ffffff;
	font-family: Georgia;
}

div#dialog {
	width: 400px;
	display: none;
}

article {
	border-bottom: 1px solid black;
	padding: 5px;
	padding-left: 10px;
}

#listTbody tr td:nth-child(2) {
	cursor: pointer;
}
</style>
<script type="text/javascript">

history.pushState(null, null, location.href); 
window.onpopstate = function(event) { 
history.go(1); 
}

$( function() {
    $( "#datepicker4" ).datepicker({
    
    	dateFormat:"yy년 mm월 dd일",
    	onClose: function( selectedDate ) {
			  $("#datepicker5").datepicker( "option", "minDate", selectedDate );
	}

    });
});

$( function() {
    $( "#datepicker5" ).datepicker({
    	dateFormat:"yy년 mm월 dd일",
    	onClose: function( selectedDate ) {
		$("#datepicker4").datepicker( "option", "maxDate", selectedDate );
	}

    });
});

$( function() {
    $( "#datepicker6" ).datepicker({
    
    		changeMonth: true,
	        changeYear: true,
	        yearRange: 'c-150:c+0',
	        dateFormat:"yy년 mm월 dd일"
    });
});

function formCheck(){
	if(document.join.memberName.value.length<2){
		alert("회원이름 2글자 이상");
		document.join.memberName.focus();
		return false;
	}
	
	if(document.join.phoneMiddle.value.length<3){
		alert(" 핸드폰번호 3자 이상");
		document.join.phoneMiddle.focus();
		return false;
	}
	
	if(!(document.join.phoneEnd.value.length==4)){
		alert(" 핸드폰 뒷자리번호는 4자 여야합니다.");
		document.join.phoneEnd.focus();
		return false;
	}
	
	if(document.join.startDay.value.length==0){
		alert("시작일을 입력해주세요");
		document.join.startDay.focus();
		return false;
	}
	
	if(document.join.endDay.value.length==0){
		alert("만료일을 입력해주세요");
		document.join.endDay.focus();
		return false;
	}
	
}




</script>
</head>
<body>
<p align="right"><a href="/health_member_manager/health/healthMemberPaging.do?page=${sessionScope.pageBean.page}"><button>뒤로가기</button></a></p>
<hr>
<div id="joinPage" title="회원가입">
<h1><b>회원가입</b></h1>
<form action="/health_member_manager/health/joinMember.do" method="post" name="join" onsubmit="return formCheck()">
	<table border=1>
			<tr><th>회원이름 :</th><td><input type="text" name="memberName"> <span class="errorspans"><form:errors path="healthMemberForm.memberName"/></span></td></tr>
					<tr><th>회원 연락처 :</th><td><select name="phoneFront">
					<c:forEach items="${sessionScope.codeValue}" var="codevalue">
					<option>${codevalue.codeValue}
					</option>
					</c:forEach>
			</select>-<input type="number" name="phoneMiddle" size="4">-<input type="number" name="phoneEnd" size="4">&nbsp;<span class="errorspans"><form:errors path="healthMemberForm.phoneMiddle"/><form:errors path="healthMemberForm.phoneEnd"/></span></td></tr>
			<tr><th>운동 시작일 :</th><td><input type="text" name="startDay" id="datepicker4" readonly="readonly">&nbsp;<span class="errorspans"><form:errors path="healthMemberForm.startDay"/></span></td></tr>
			<tr><th>운동 만료일 :</th><td><input type="text" name="endDay" id="datepicker5" readonly="readonly">&nbsp;<span class="errorspans"><form:errors path="healthMemberForm.endDay"/></span></td></tr>
			<tr><td colspan="2"><font color=red>---- 여기서 부터는선택항목입니다.-----</font><br></td></tr>
			<tr><th>생년월일 :</th><td><input type="text" name="birthday" id="datepicker6" readonly="readonly">&nbsp;<span class="errorspans"><form:errors path="healthMemberForm.birthday"/></span></td></tr>
			<tr><th>주소 :</th><td><input type="text" name="address"></td></tr>
			<tr><th>이메일 :</th><td><input type="text" name="email"></td></tr>
			<tr><th colspan="2"><font color="white">신발장 등록은 회원가입 후 신발장번호 '0'번 클릭</font><input type="hidden" name="shoesNo" value="0"></th></tr>
			<tr><td colspan="2"><input type="submit" value="가입"><input type="reset" value="다시작성"></td></tr>
	</table>
</form>
		
</div>
</body>
</html>