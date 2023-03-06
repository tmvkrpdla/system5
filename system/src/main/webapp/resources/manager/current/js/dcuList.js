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

	//	조회 버튼
	$("#searchBtn").on('click', function() {
		$("#searchForm").submit();
		//	location.href = "../apt/aptment?SeqSite="+_seqSite.val();
	});

	//	조회 버튼2
	$("#searchBtn2").on('click', function() {
		$("#searchForm").submit();

	});

	// 서버 선택
	$("#mdmsSelect").on('change', function() {
		//console.log($(this).val());

		var _seqMdms = $(this).val();
		//console.log("_seqMdms = " + _seqMdms);
		$("#SeqMdms").val(_seqMdms);

		if (_seqMdms == "0") {
			$("#SeqSite").val("0");
			const target = document.getElementById('siteSelect');
			target.disabled = true;

		} else {
			const target = document.getElementById('siteSelect');
			target.disabled = false;
		};
		
		var _obj = {};
		_obj.SeqMdms = _seqMdms;
		
		var _res = enernet.modules.api.getSiteListByMdms(_obj.SeqMdms);
		//console.log("_res = " + _res.list_site.length);
		
		//if(_res.list_site.length > 0){
			$("#siteSelect").find('option').remove()
			var _list_site = _res.list_site;
			
			var _tag2 = "<option selected value=" + "0" + ">"
				_tag2 += "전체";
				_tag2 += "</option>";
			
			for(var i=0; i<_list_site.length; i++){
				var _tag = "<option value=" + _list_site[i].seq_site + ">"
				_tag += _list_site[i].site_name;
				_tag += "</option>";
				
				$("#siteSelect").append(_tag);

			}
			$("#siteSelect").append(_tag2);
		//}
	});

	// 단지 선택
	$("#siteSelect").on('change', function() {
		//console.log($(this).val());

		var _seqSite = $(this).val();
		$("#SeqSite").val(_seqSite);

	});
	
	
	// DCU 상세정보(클릭 이벤트)
	function dcuDetailInfo() {
		var _DcuDetailTr = document.getElementsByClassName('dcuDetailTr');
		if (_DcuDetailTr.length != 0) {
			for (var i = 0; i < _DcuDetailTr.length; i++) {
				_DcuDetailTr[i].onclick = function(e) {
					
					$(this).find('form').attr('method', 'post').attr('action', '../current/dcuDetailInfo');
					var _dcuDetailForm = $(this).find('form')[0];
					//console.log("_dcuDetailForm = " + _dcuDetailForm);
					
					var _outerWidth = window.outerWidth * 0.6;
					var _outerHeight = window.outerHeight * 0.8;

					window.open("", '_dcuDetailForm', 'height=' + _outerHeight + ', width=' + _outerWidth + ', left=20, top=10, scrollbars=1');
					
					_dcuDetailForm.target = '_dcuDetailForm';
					_dcuDetailForm.submit();

				}

			}

		}
	};
	
	dcuDetailInfo();
	
	
	

});

