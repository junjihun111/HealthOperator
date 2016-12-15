
<script type="text/javascript">
$(document).ready(function(){
<c:if test="${requestScope.shoescage!=null}">
	$("#shoescageInfo").dialog({modal:true,width:450});
</c:if>

<c:if test="${empty requestScope.shoescage}">
<c:if test="${requestScope.memberNo!=null}">
$("#shoesCageRegisterPage").dialog({modal:true,width:450});
</c:if>
</c:if> 

/*만료된 신발장의 조회 endedShoeCageList가 null이 아니라면 dialog를 통해 보여줘라  */
<c:if test="${requestScope.endedShoeCageList != null}">
$("#endedShoeNoDialog").dialog({modal:true,width:500, 
		 buttons: {
		         Cancel: function(){$( this ).dialog( "close" );}
		       }
});
</c:if>

/* 에러가 났을 때 다시보여줄 Dialog  */
<c:if test="${requestScope.errors!=null}">
	$("#shoesCageRegisterPage").dialog({modal:true,width:450});
</c:if>

<c:if test="${requestScope.DulplicateMessage != null}">
	$("#shoesCageRegisterPage").dialog({modal:true,width:450});
</c:if>

<c:if test="${requestScope.attMessage!=null}">
alert("${requestScope.attMessage}");
</c:if>

});
</script>