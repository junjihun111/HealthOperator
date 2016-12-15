<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

history.pushState(null, null, location.href); 
window.onpopstate = function(event) { 
history.go(1); 
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/health_member_manager/health/joinshoes.do" method="post">
회원번호:
신발장번호:<input type="text" name="shoeNumber">
신발장시작일:<input type="text" name="startDate">
신발장만료일:<input type="text" name="endedDate">
</form>
</body>
</html>