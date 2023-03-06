$(function(){
	//	날짜 생성
	makeDatePicker("date_work");
	
	if(_seqJobCode == 0){
		$("#seq_job_code").attr("data-1", _seqJobCode);
		$("#seq_job_code").val("");
	}else{
		$("#seq_job_code").attr("data-1", _seqJobCode);
		
		for(var i=0; i<_seqJobCodeList.length; i++){
			if(_seqJobCodeList[i].seq_job_code == _seqJobCode){
				$("#seq_job_code").val(_seqJobCodeList[i].value);
				break;
			}
		}
		
	}
	
	$("#seq_job_code").on('focus', function(){
		$(this).attr("data-1", 0);
		$(this).val("");
	});
	
	//	조회
	$("#photoListSearchBtn").on('click', function(){
		
		var _seqJobCodeValue = $("#seq_job_code").val();
		if(_seqJobCodeValue == ""){
			$("#seq_job_code").attr("data-1", 0);
		}
		
		var _url = "../site/photoList?";

		_url += "seq_project="+$("#seq_project").val();
		_url += "&seq_job_code="+$("#seq_job_code").attr("data-1");
		_url += "&date_work="+$("#date_work").val();
		
		location.href = _url;
		
	});		
	
	$("#seq_job_code").autocomplete({
	     source: _seqJobCodeList,
	     select: function(event, ui) {

	    	 $("#seq_job_code").attr("data-1",ui.item.seq_job_code);
	   	  
	     }
	});
		
	//
	function makeDatePicker(targetId){
		$("#"+targetId).datepicker({
			changeMonth: true,
			changeYear: true,
			showMonthAfterYear:true,
			dateFormat: 'yy-mm-dd',
			nextText: '다음달',
			prevText: '이전달',
			buttonText: "선택" ,
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
			minDate: "-30Y",
			maxDate: 0
			}).focus(function(){
				$(".ui-datepicker-calendar").show();
			});
			
	}//
	
	var _seqProject = $("#search_seq_project").val();
		
	var _mmsType = $("#search_mms_type").val();
			
	var _dateWork = $("#search_date_work").val();
			
	$("#date_work").val(_dateWork);
	$("#seq_project").val(_seqProject);
	//$("#mms_type").val(_mmsType);
	
	if(document.getElementById("nowPage") != undefined){
		var _pageLink = document.getElementsByClassName('page-link');
		
		if(_pageLink.length != 0){
			var _nowPage = $("#nowPage").val();
			
			for(var i=0; i<_pageLink.length; i++){
				if(_pageLink[i].outerText.replace(/(\s*)/g, "") == _nowPage){
					document.getElementsByClassName('page-link')[i].style.backgroundColor = '#4e73df';
					document.getElementsByClassName('page-link')[i].style.color = 'white';
					document.getElementsByClassName('page-link')[i].style.borderRadius = '5px';
				}
			}
		}
	}//
	
	
	//	이미지 팝업
	//	pop-show
	var _popImgEvt = null;
	
	if(document.getElementsByClassName('thumb-evt').length != 0){
		
		_popImgEvt = document.getElementsByClassName('thumb-evt');
		
		for(var i=0; i<_popImgEvt.length; i++){
	
			_popImgEvt[i].onclick = function(){
				
				var _imgSrc = $(this).attr("data-1");
				
				
				if(!enernet.modules.utils.isImgExt(_imgSrc)){
					return alert('이미지 파일이 없습니다');
				}				
				 
				var image = new Image();
				image.src = _imgSrc;
				
				var viewer = new Viewer(image, 
					{ 
						hidden: function () {
							
							viewer.destroy();
						},
						toolbar: {
							zoomIn: 4,
							zoomOut: 4,
							oneToOne: 4,
							reset: 4,
							prev: 0,
						    play: {
						    	show: 4,
						    	size: 'large'
						    },
						    next: 0,
						    rotateLeft: 4,
						    rotateRight: 4,
						    flipHorizontal: 4,
						    flipVertical: 4,
						},
						navbar : 0,
						tooltip : true,
						viewed : function(evt){
							evt.detail.image.onclick = function(){
								
							}
						}
						
					}
				);
		
				viewer.show();
				
			}
		}
		
	};
	
});
