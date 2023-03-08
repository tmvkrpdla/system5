$(function() {
	
	// 전화번호 정규식
	var _site_phone = document.getElementsByClassName('site_phone');
	var _hippenSitePhone = ""
	
		for(i=0; i<_site_phone.length; i++) {
		
		if(_site_phone[i].length == 8) {
			_hippenSitePhone = _site_phone[i].innerText.replace(/ /g, '').replace(/^([0-9]{4})([0-9]{4})$/, '$1-$2');
			_site_phone[i].innerText = _hippenSitePhone;
		} else if(_site_phone[i].length == 12) {
			_hippenSitePhone = _site_phone[i].innerText.replace(/ /g, '').replace(/(^[0-9]{4})([0-9]{4})([0-9]{4})$/, '$1-$2-$3');
			_site_phone[i].innerText = _hippenSitePhone;
		} else {
			_hippenSitePhone = _site_phone[i].innerText.replace(/ /g, '').replace(/(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$/, '$1-$2-$3');
			_site_phone[i].innerText = _hippenSitePhone;
		}
		
//		console.log("_hippenSitePhone = " + _hippenSitePhone);
		
//		var _hippenSitePhone = _site_phone[i].innerText.replace(/ /g, '').replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
		//_site_phone[i].innerText = _hippenSitePhone;
		
	}
	
	
	
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
	
	var dd = document.getElementsByClassName('state');
	var ff = document.getElementsByClassName('radioTd');
	var _agreeLab = document.getElementsByClassName('agreeLab');
	var _disAgreeLab = document.getElementsByClassName('disAgreeLab');
//	console.log(dd[10].value);
	
//	for(let k = 0; k < dd.length; k ++) {
//		
//		if(dd[k].value == "0") {
//			_agreeLab[k].style.visibility = "hidden";
//			_disAgreeLab[k].style.visibility = "hidden";
//		}
//	}
//	
//	
//	for(let k = 0; k < dd.length; k ++) {
//		
//		if(dd[k].value == "4") {
//			_agreeLab[k].style.visibility = "hidden";
//			_disAgreeLab[k].style.visibility = "hidden";
//		}
//	}
	
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
		
		// 컨트롤러로 폼 전송해서 api 해보기
	});
	

	// 승인버튼
	function saveBtn() {

		var tdArr = new Array(); // 배열선언
		var x = document.getElementsByClassName('selected');
		var tdArrUnSel = new Array(); // 배열선언
		var y = document.getElementsByClassName('unselected');
		var comment = document.getElementsByClassName('comment');

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
		
		let _resAccept = enernet.modules.api.setAppRequestsAsAccepted(str1);
		let _resReject = enernet.modules.api.setAppRequestsAsRejected(str2);
		
		//comment
//		for(let j = 0; j < y.length; j++) {
//			
//			let _resComment = enernet.modules.api.setAppRequestComment(y[j].value, comment[j].value);
//		}
		
		console.log("_resAccept = " + JSON.stringify(_resAccept));
		console.log("_resReject = " + JSON.stringify(_resReject));
//		console.log("_resComment = " + JSON.stringify(_resComment));
		alert('등록에 성공하였습니다.');
		location.reload();

	}
	
	
//	var radioInput = $('.agree');
//
//	$(function () {
//	    //filtering cached selector is intentional - won't requery DOM
//		radioInput.filter(':checked').closest("input[name='ho_name']").addClass(' selected');
//	});
	
	
	
//	$('input[class=agree]').dblclick(function (e) {
//	    $('input[class="agree"]:checked').prop('checked', false);
//	});
	
	
	$('.agree').click(function() {
		console.log("승인 클릭2");
//		if($(this).is(":checked") == false ) {
//			console.log("체크 된 애 클릭했음");
//		}
		$(this).parents('tr.radioEvtTr').find("input[name='ho_name']").addClass(' selected');
		$(this).parents('tr.radioEvtTr').find("input[name='ho_name']").removeClass(' unselected');
		
	});
	
	
	
//	$('.agree').on('change', function() {
//		console.log("승인 클릭");
////		radioInput.closest("input[name='ho_name']").removeClass(' selected');
////		$(this).closest("input[name='ho_name']").addClass(' selected');
//		$(this).parents('tr.radioEvtTr').find("input[name='ho_name']").addClass(' selected');
//		$(this).parents('tr.radioEvtTr').find("input[name='ho_name']").removeClass(' unselected');
//		
//	});
	
	$('.disAgree').on('change', function() {
		console.log("거절 클릭");
		$(this).parents('tr.radioEvtTr').find("input[name='ho_name']").addClass(' unselected');
		$(this).parents('tr.radioEvtTr').find("input[name='ho_name']").removeClass(' selected');
		
	});
	
	

});
