
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="/health_member_manager/css/base.css" rel="stylesheet" />

<script type="text/javascript" src="/health_member_manager/scripts/jquery.js"></script>
<%@ include file="/scripts/function/attendance.jsp" %>
</head>
<body>
	<br>
	<br>
	<hr>
	출석수 : ${requestScope.attendCount }
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<a href="/health_member_manager/health/healthMemberPaging.do?page=${sessionScope.pageBean.page}"><button>뒤로가기</button></a>
	<button id="attendChoice">출석선택</button>
	<button id="checkedDelete">출석내역삭제</button>

	<table border="1">
		<tr>
			<th>회원번호
			<th>회원이름
			<th>출석내역
			<th>환불금액 
			<tbody id="listBody">
			<c:forEach items="${requestScope.attendList}" var="attend">
					<tr>
						<td>${attend.memberNo}</td>
						<td>${attend.memberName}</td>
						<td><fmt:formatDate value="${attend.attendanceDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
				</c:forEach>
				</tbody>
	</table>
</body>
</html>