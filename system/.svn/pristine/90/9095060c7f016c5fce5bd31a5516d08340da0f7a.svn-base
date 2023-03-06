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

	// 단지 선택
	$("#siteSelect").on('change', function() {
		//console.log($(this).val());
		
		var _seqSite = $(this).val();
		$("#SeqSite").val(_seqSite);
		
		if (_seqSite == "0") {
			//console.log("전체입니다.");
			$("#SeqDong").val("0");
			const target = document.getElementById('aptDongSelect');
			target.disabled = true;

		} else {
			//console.log("선택입니다.");
			const target = document.getElementById('aptDongSelect');
			target.disabled = false;
		};

		var _obj = {};
		_obj.SeqSite = _seqSite;

		var _res = enernet.modules.api.getDongListBySite(_obj.SeqSite);
		console.log("_res = " + _res.list_dong.length);

		$("#aptDongSelect").find('option').remove()
		var _list_dong = _res.list_dong;

		for (var i = 0; i < _list_dong.length; i++) {
			var _tag = "<option value=" + _list_dong[i].seq_dong + ">"
			_tag += _list_dong[i].dong_name;
			_tag += "</option>";

			$("#aptDongSelect").append(_tag);

		}
		
		var _res2 = enernet.modules.api.getDcuListBySite(_obj.SeqSite);
		console.log("_res2 = " + _res2.list_dcu.length);
		
		$("#dcuSelect").find('option').remove()
		var _list_dcu = _res2.list_dcu;
		
		var _tag = "<option selected value=" + "0" + ">"
		_tag += "전체";
		_tag += "</option>";
		
		for (var i = 0; i < _list_dcu.length; i++) {
			var _tag2 = "<option value=" + _list_dcu[i].seq_dcu + ">"
			_tag2 += _list_dcu[i].dcu_id;
			_tag2 += "</option>";

			$("#dcuSelect").append(_tag2);

		}
		$("#dcuSelect").append(_tag);
		
	});
	
	// 아파트 동 선택
	$("#aptDongSelect").on('change', function() {
		//console.log($(this).val());

		var _seqDong = $(this).val();
		console.log("_seqDong = " + _seqDong);
		$("#SeqDong").val(_seqDong);


	});
	
	// dcu 선택
	$("#dcuSelect").on('change', function() {
		//console.log($(this).val());

		var _seqDcu = $(this).val();
		console.log("_seqDcu = " + _seqDcu);
		$("#SeqDcu").val(_seqDcu);


	});
	

});

