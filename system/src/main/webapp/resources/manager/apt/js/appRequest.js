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
	
	
	$("#agreeBtn").click(function() {
		saveBtn();
	});
	

	// 승인버튼
	function saveBtn() {

		var tdArr = new Array(); // 배열선언
		var x = document.getElementsByClassName('selected');
		var tdArrUnSel = new Array(); // 배열선언
		var y = document.getElementsByClassName('unselected');

		for (let i = 0; i < x.length; i++) {
			tdArr.push(x[i].value)
		}
		console.log("tdArr = " + tdArr);

		var str1 = tdArr.join();
		console.log("str1 = " + str1);
		
		for (let j = 0; j < y.length; j++) {
			tdArrUnSel.push(y[j].value)
		}
		console.log("tdArrUnSel = " + tdArrUnSel);
		
		var str2 = tdArrUnSel.join();
		console.log("str2 = " + str2);
		
		//		setAppRequestsAsAccepted, setAppRequestsAsRejected


	}
	
	
	var radioInput = $('.agree');

	$(function () {
	    //filtering cached selector is intentional - won't requery DOM
		radioInput.filter(':checked').closest("input[name='ho_name']").addClass(' selected');
	});
	
	$('.agree').on('change', function() {
		console.log("승인 클릭");
//		radioInput.closest("input[name='ho_name']").removeClass(' selected');
//		$(this).closest("input[name='ho_name']").addClass(' selected');
		$(this).parents('tr.radioEvtTr').find("input[name='ho_name']").addClass(' selected');
		$(this).parents('tr.radioEvtTr').find("input[name='ho_name']").removeClass(' unselected');
		
	});
	
	$('.disAgree').on('change', function() {
		console.log("거절 클릭");
		$(this).parents('tr.radioEvtTr').find("input[name='ho_name']").addClass(' unselected');
		$(this).parents('tr.radioEvtTr').find("input[name='ho_name']").removeClass(' selected');
		
	});
	
	

});
