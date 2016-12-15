
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
history.pushState(null, null, location.href); 
window.onpopstate = function(event) { 
history.go(1); 
}	

$(document).ready(function() {
	
	
	var i=1;
	$("#attendChoice").on("click", function(){
		i=i+1;
		if(i%2==0){
		$("tr td:nth-child(1)").prepend("<input type='checkbox'  name='box'>");	
		}
		else{
			$("input[name=box]").remove();
		}
		
	});
	
	$("#checkedDelete").click(function() {
		var ids = "";
		var dates = "";
		//alert($("input[name=box]:checked").length);
		$("input[name=box]:checked").each(function(idx) {
			if(idx==$("input[name=box]:checked").length-1){
				 //ids+="id="+$(this).parent().text();
				 dates+="attendanceDates="+$(this).parent().nextAll(":eq(1)").text();
			}else{
				//ids+="id="+$(this).parent().text()+"&";
				 dates+="attendanceDates="+$(this).parent().nextAll(":eq(1)").text()+"&";
			}
			ids="id="+$(this).parent().text();
		});
		var queryString = ids+"&"+dates;

		  $.ajax({
			 "url":"/health_member_manager/health/deleteByAttend.do",
			 "data":queryString,
			 "dataType":"json",
			"type":"post",
			"success":function(obj){
				var txt="";
				$.each(obj,function(){
					txt= txt+"<tr><td>"
					+this.memberNo+"</td><td>"
					+this.memberName+"</td><td>"
					+this.attendanceDate+"</td><td></td>"
				})
				$("#listBody").html(txt);
			},
			error:function(xhr, msg, msg2){
				alert("오류발생"+msg2);
			},
 			"beforeSend":function(){
 				if(dates==0){
 					alert("삭제할 출석내역을 선택한 후 삭제 해주세요");
 					return false;
 				}
 			}
		 }) 
	});
	
});

</script>