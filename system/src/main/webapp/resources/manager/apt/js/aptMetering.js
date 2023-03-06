$(function() {

	// 엑셀 다운로드
	$("#excelDown").on("click", function() {
		$("#meterExcelForm").attr("action", "../apt/excelDownTest").submit();
	});
	
	enernet.modules.evt.makeDateYMD("To");
	
	// 단지 선택
	$("#siteSelect").on('change', function() {
		//console.log($(this).val());
		var _seqSite = $(this).val();
		$("#SeqSite").val(_seqSite);
		
		var target = document.getElementById("siteSelect");
		//alert('선택된 옵션 text 값=' + target.options[target.selectedIndex].text);	// 옵션 text 값
		var _siteName = target.options[target.selectedIndex].text;
		$("#SiteName").val(_siteName);

	});
	
	// 종료날짜 선택
	$("#To").on('change', function() {
		//console.log($(this).val());
		var _To = $(this).val();
		$("#to_yymmdd").val(_To);

	});
	
	// 종료시간 선택
	$("#to_selecthhmm").on('change', function() {
		//console.log($(this).val());
		var _tohhmm = $(this).val();
		$("#to_hhmm").val(_tohhmm);

	});
	
	//	조회 버튼
	$("#searchBtn").on('click', function() {
		var _siteSelect = document.getElementById("siteSelect").value;

		if (_siteSelect == "select") {
			alert("단지를 선택해주세요.");
			return;
		}

		let today = new Date();
		let hours = today.getHours();
		//alert("hours = " + hours);
		let presentHours = hours + "00";
		//alert("presentHours = " + presentHours);

		if($("#to_hhmm").val() > presentHours) {
	         alert("현재시각 이후의 시간은 조회하실 수 없습니다.");
	         
	         document.querySelector('.modal_wrap').style.display ='none';
	         document.querySelector('.black_bg').style.display ='none';
	         
	      } else{
	    	  document.querySelector('.modal_wrap').style.display ='block';
	          document.querySelector('.black_bg').style.display ='block';
	         $("#searchForm").submit();
	        
	      }

	});
	

});

