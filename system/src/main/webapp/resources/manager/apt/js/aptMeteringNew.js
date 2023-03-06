$(function() {

	// 엑셀 다운로드
	$("#excelDown").on("click", function() {
		$("#meterExcelForm").attr("action", "../apt/excelDownNew").submit();
	});
	
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
	
	//	조회 버튼
	$("#searchBtn").on('click', function() {
		var _siteSelect = document.getElementById("siteSelect").value;

		if (_siteSelect == "select") {
			alert("단지를 선택해주세요.");
		} else{
	         $("#searchForm").submit();
	      }

	});
	

});
