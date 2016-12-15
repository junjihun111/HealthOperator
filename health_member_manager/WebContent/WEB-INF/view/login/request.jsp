<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/health_member_manager/scripts/jquery.js"></script>
<title>Insert title here</title>
<script type="text/javascript"  src="/health_member_manager/scripts/jquery.js"></script>
<script src="/health_member_manager/scripts/jquery-ui.js"></script>
<link type="text/css" href="/health_member_manager/css/jquery-ui.css" rel="stylesheet" />	
<style type="text/css">
.errorspan{
font-weight:bold;
color:#FF2424;
font-size:12px;
}

.errorspans{
font-weight:bold;
color:blue;
font-size:12px;
}


</style>

<script type="text/javascript">

history.pushState(null, null, location.href); 
window.onpopstate = function(event) { 
history.go(1); 
}



function loginCheck(){
	if(document.loginCh.managerId.value.length==0){
		alert("로그인ID란이 비어있습니다 다시 확인해주세요");
		document.idPw.managerId.focus();
		return false;
	}
	
	if(document.loginCh.managerPw.value.length==0){
		alert("패스워드란이 비어있습니다 다시 확인해주세요");
		document.idPw.managerPw.focus();
		return false;
	}
}

</script>
</head>
<body>

<form  action="/health_member_manager/health/manager.do" onsubmit="return loginCheck()" name="loginCh">
<c:if test="${requestScope.failMessage != null }"><span class="errorspans">${requestScope.failMessage }</span></c:if><br>
로그인<br>
아이디:<input type="text" name="managerId">&nbsp;<span class="errorspan"><form:errors path="managerInfo.managerId" /></span><br>
비밀번호 : <input type="password" name="managerPw">&nbsp;<span class="errorspan"><form:errors path="managerInfo.managerPw" /></span><br>
<input type="submit" value="로그인"> &nbsp;
</form>
<a href="/health_member_manager/health/searchIdPwPage.do"><button>ID/비밀번호찾기</button></a>




</body>
</html>