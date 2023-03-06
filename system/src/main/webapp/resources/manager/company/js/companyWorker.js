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

	// 추가 버튼
	$("#addWorkerBtn").click(
			function() {
				
				var form = document.createElement("form");
				form.setAttribute("id", "popForm")
				form.setAttribute("name", "popForm")
				form.setAttribute("charset", "UTF-8");
				form.setAttribute("method", "post");
				form.setAttribute("action", "./addWorker");
				document.body.appendChild(form);

				var _popForm = document.popForm;

				var _h = window.outerHeight * 0.9;
				var _w = window.outerWidth * 0.9;
				var _op = "scrollbars=yes,resizable=yes,top=50,left=50";
				_op += ',width=' + _w;
				_op += ',height=' + _h;

				var _outerWidth = window.outerWidth * 0.5;
				var _outerHeight = window.outerHeight * 0.6;

				window.open("", 'popForm', 'height=' + _outerHeight
						+ ', width=' + _outerWidth
						+ ', left=20, top=10, scrollbars=1');
				_popForm.target = 'popForm';
				_popForm.submit();

			});
	
	// 협력업체 선택
	$("#companySelect").on('change', function() {
		//console.log($(this).val());

		var _seqCompany = $(this).val();
		//console.log("_seqCompany = " + _seqCompany);
		$("#SeqCompany").val(_seqCompany);

	});
	

});

