$(function() {
	
	var _pageLink = document.getElementsByClassName('page-link');
	
	if(_pageLink.length != 0){
		var _nowPage = $("#nowPage").val();
		
		for(var i=0; i<_pageLink.length; i++){
			if(_pageLink[i].outerText == _nowPage){
				document.getElementsByClassName('page-link')[i].style.backgroundColor = '#5E7FA6';
				document.getElementsByClassName('page-link')[i].style.color = 'white';
				document.getElementsByClassName('page-link')[i].style.borderRadius = '5px';
			}
		}
	}//end of if _pageLink	
	
	$("#addAptBtn").on("click", function(){
		$("#addAptForm").submit();
	});
	
	// 엑셀 다운로드
	$("#reportBtn").on("click", function() {
		
		console.log("레포트인쇄");
		location.href = "../apt/excelDownTest";

	});
	
//	aptNameArray = [];
//	let aptName = document.getElementsByClassName("siteName");
//	alert(aptName.length);
//	for(i = 0; i < aptName.length; i++) {
//		aptNameArray.push(aptName[i].value);
//	}
//	console.log("aptNameArray = ", aptNameArray);

});