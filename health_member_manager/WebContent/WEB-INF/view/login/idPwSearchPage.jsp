
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">

history.pushState(null, null, location.href); 
window.onpopstate = function(event) { 
history.go(1); 
}
</script>
<meta charset="UTF-8">
<title></title>
<style type="text/css">

.errorspans {
font-weight:bold;
color:red;
font-size:12px;
}

.errorspan {
font-weight:bold;
color:blue;
font-size:12px;
}

</style>



<script type="text/javascript">

function formCheck(){
	if(document.idPw.managerEmail.value.length==0){
		alert("이메일란이 비어있습니다 다시 확인해주세요");
		document.idPw.managerEmail.focus();
		return false;<%-- 이조건이면 전송되는 것을 막아줘야 하기때문에 false --%>
	}
	
	if(document.idPw.serialNum.value.length==0){
		alert("주민번호 뒷자리가 비어있습니다 다시 확인해주세요");
		document.idPw.serialNum.focus();
		return false;
	}
}



</script>

</head>


<body>
<div id="idpwsearchPage">
<form name="idPw" action="/health_member_manager/health/searchIdPw.do" method="post" onsubmit="return formCheck()">
ID/PW찾기<br>
<c:if test="${requestScope.failMessage!=null }">
<span class="errorspan">${requestScope.failMessage }</span>
</c:if><br>
이메일 주소 : <input type="text" name="managerEmail"><span class="errorspans"><form:errors path="managerInfo.managerEmail"/></span><br>
주민번호 뒷자리 네자리: <input type="text" name="serialNum"><span class="errorspans"><form:errors path="managerInfo.serialNum"/></span><br>
<input type="submit" value="찾기">
</form>

<c:if test="${requestScope.successMessage!=null}">
성공하였습니다.<br>
${requestScope.successMessage }
</c:if>

</div>
</body>
</html>