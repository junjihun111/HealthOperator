history.pushState(null, null, location.href); 
window.onpopstate = function(event) { 
history.go(1); 
}

$( function() {
    $( "#startDatepicker" ).datepicker({
    	dateFormat:"yy년 mm월 dd일",
    	onClose: function( selectedDate ) {
    			  $("#endDatepicker").datepicker( "option", "minDate", selectedDate );
    	}

    });
});
  
  $( function() {
	    $( "#endDatepicker" ).datepicker({
	    	dateFormat:"yy년 mm월 dd일",
	    	onClose: function( selectedDate ) {
  			  $("#startDatepicker").datepicker( "option", "maxDate", selectedDate );
  	}
	    });
	});
  
  $( function() {
	    $( "#birthdayDatepicker" ).datepicker({
	    	
	    		changeMonth: true,
		        changeYear: true,
		        yearRange: 'c-150:c+0',
		        dateFormat:"yy년 mm월 dd일"
	    });
	});
  
  $( function() {
	    $( "#startDatepicker2" ).datepicker({
	    	dateFormat:"yy년 mm월 dd일"
	    });
	});
	  
	  $( function() {
		    $( "#endDatepicker2" ).datepicker({
		    	dateFormat:"yy년 mm월 dd일"
		    });
		});
	  
	  $( function() {
		    $( "#birthdayDatepicker2" ).datepicker({
		    	
		    		changeMonth: true,
			        changeYear: true,
			        yearRange: 'c-150:c+0',
			        dateFormat:"yy년 mm월 dd일"
		    });
		});
  

	  $( function() {
		    $( "#startDay" ).datepicker({
		    	dateFormat:"yy년 mm월 dd일",
		    	onClose: function( selectedDate ) {
	    			  $("#endDay").datepicker( "option", "minDate", selectedDate );
	    	}
		    });
		});
	  $( function() {
		    $( "#endDay" ).datepicker({
		    	dateFormat:"yy년 mm월 dd일",
		      	onClose: function( selectedDate ) {
	    			  $("#endDay").datepicker( "option", "minDate", selectedDate );
	    	}
		    });
		});
	  


 	  function updateFormCheck(){
			if(document.update.memberName.value.length<2){
				alert("회원이름 2글자 이상");
				document.update.memberName.focus();
				return false;
			}
			if(document.update.phoneMiddle.value.length<3){
				alert(" 핸드폰번호 3자 이상");
				document.update.phoneMiddle.focus();
				return false;
			}
			if(!(document.update.phoneEnd.value.length==4)){
				alert(" 핸드폰번호 뒷자리 4자리이어야 합니다.");
				document.update.phoneEnd.focus();
				return false;
			}
			
			if((document.update.startDay.value.length==0)){
				alert(" 시작날짜를 달력에서 선택하세요");
				document.update.startDay.focus();
				return false;
			}
			
			if((document.update.endDay.value.length==0)){
				alert("만료날짜를 달력에서 선택하세요");
				document.update.endDay.focus();
				return false;
			}

		};
	  
		
	  function shoesCageCheck(){
			if(document.shoesCage.shoesNo.value==0){
				alert("신발장번호 값이 0또는 없으므로 등록 할수 없습니다.");
				document.shoesCage.shoesNo.focus();
				return false;
			}  
	  
			if(document.shoesCage.shoesNo.value>100){
				alert("신발장번호 100번 이하로 등록");
				document.shoesCage.shoesNo.focus();
				return false;
			}
			
			if(document.shoesCage.startDay.value.length==0){
				alert("시작 날짜를 달력에서 입력받으세요");
				document.shoesCage.startDay.focus();
				return false;
			}
			
			if(document.shoesCage.endDay.value.length==0){
				alert("만료 날짜를 달력에서 입력받으세요");
				document.shoesCage.endDay.focus();
				return false;
			}
		};
		
		

		