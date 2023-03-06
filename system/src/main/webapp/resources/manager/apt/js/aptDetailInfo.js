$(function() {
	
	// 작업결과 저장버튼 권한
	if($("#is_director").val() == "1" || $("#seq_admin").val() == $("#hoAdmin").val() || $("#hoAdmin").val() =="0"){
		document.getElementsByClassName("workSaveBtn")[0].style.display = "inline";
	}
//	if($("#seq_admin").val() == $("#hoAdmin").val()) {
//		console.log("로그인자와 설치작업자가 서로 같음");
//		document.getElementsByClassName("workSaveBtn")[0].style.display = "inline";
//		
//	}
//	if($("#hoAdmin").val() =="0"){
//		console.log("아직 작업결과 미등록 세대");
//		document.getElementsByClassName("workSaveBtn")[0].style.display = "inline";
//	}
	else {
		console.log("로그인자와 설치작업자가 다르며 관리자도 아니며 미설치 세대도 아님");
		document.getElementsByClassName("workSaveBtn")[0].style.display = "none";
		
	}
	
	// 작업결과 삭제버튼 권한
	if($("#is_director").val() == "1") {
		console.log("관리자 권한");
		document.getElementsByClassName("workDelBtn")[0].style.display = "inline";
	} else{
		document.getElementsByClassName("workDelBtn")[0].style.display = "none";
	}
	
	// 사진삭제 버튼 권한
	for (var i = 0; i < $(".delActBtn").length; i++) {
		if($("#is_director").val() == "1") {
			console.log("관리자 권한");
			document.getElementsByClassName("delActBtn")[i].style.display = "inline";
		} else{
			document.getElementsByClassName("delActBtn")[i].style.display = "none";
		}
	}
	
	// 사진저장버튼 
	$("#saveImg").on("click", function(){
		$("#imgForm").submit();
	});
	
	
	
	
	// 저장 버튼
	$("#actSaveBtn").on('click', function saveWork(){
	   
      if($("#prevMid").val().trim() == ''){
         return alert('철거계량기 MID를 입력해주세요.');
      }
      if($("#oldValue").val().trim() == ''){
          return alert('철거계량기 최종지침을 입력해주세요.');
       }
      if($("#newMid").val().trim() == ''){
          return alert('신규계량기 MID를 입력해주세요.');
       }
      if($("#newValue").val().trim() == ''){
          return alert('신규계량기 MID를 입력해주세요.');
       }
      if($("#newMid").val().length != 11){
          return alert('신규계량기 MID를 숫자 11자리로 입력해주세요.');
       }
      
      
//      var substring = "901900";
//      var stringVal = $("#newMid").val();
//      console.log("stringVal = " + stringVal);
//      
//      if(stringVal.indexOf(substring) == -1){
//    	  console.log("계량기 번호 입력 오류");
//    	  return alert('신규계량기 MID를 옳바르게 입력해주세요. ex)901900....');
//      }
      
      var _check = null;
      
      if($('input:checkbox[id="boundModem"]').is(":checked") == true) {
    	  _check = true;
      } else {
    	  _check = false;
      }
      //console.log("_check = ", _check);
      
      var _actObj = {};
      _actObj.SeqHo = $("#seqHo").val();
      _actObj.SeqWorker = $("#seq_admin").val();
      _actObj.Mid = $("#newMid").val();
      _actObj.ValueStart = $("#newValue").val();
      _actObj.MidPrev = $("#prevMid").val();
      _actObj.ValueLastPrev = $("#oldValue").val(); 
      _actObj.BoundToModem = _check;
      console.log("_actObj = ", _actObj);
      
      var _resact = enernet.modules.api.installMeter(_actObj.SeqHo, _actObj.SeqWorker, _actObj.Mid, _actObj.ValueStart, _actObj.MidPrev, _actObj.ValueLastPrev, _actObj.BoundToModem);
      console.log("_resact = ", _resact);
      
      if(_resact.result_code == -1){
           return alert('등록실패 (미등록 계량기입니다.)');
      }
      if(_resact.result_code == -2){
    	  return alert('등록실패 (계량기 MID 중복 오류)');
      }
      if(_resact.result_code == -3){
    	  return alert('등록실패 (다른 작업자의 작업 결과는 수정할 수 없습니다.)');
      } else{
    	  alert('저장에 성공하였습니다.');
    	  location.reload();
      }
   
   }); //saveAct end
	
	
	

		function removeCheck() {
		if (confirm("정말 삭제하시겠습니까??") == true) { // 확인
			document.removefrm.submit();
		} else { // 취소
			return false;
		}
	}
	
	// 계량기 등록 삭제
	$("#actDelBtn").on('click', function delWork(){
		
		if (confirm("정말 삭제하시겠습니까?") == true) { // 확인
			
			var _actObj = {};
			_actObj.SeqWorker = $("#seq_admin").val();
			_actObj.SeqHo = $("#seqHo").val();
			
			var _resact = enernet.modules.api.uninstallMeter(_actObj.SeqWorker, _actObj.SeqHo);
			console.log("_resact = ", _resact);
			
			if(_resact.result_code == -1) {
				return alert('삭제 실패 (본사에 문의하세요.)');
			} else{
				alert('삭제하였습니다.');
				location.reload();
			}
		} else{
			return false;
			}
	});
	
	// 계량기 이미지 삭제
	function delAct() {
		var _delActEvt = document.getElementsByClassName('delActBtn');
		
		var _actObj = {};
		_actObj.SeqWorker = $("#seq_admin").val();

		for (var i = 0; i < _delActEvt.length; i++) {
			_delActEvt[i].onclick = function() {
				var _image = $(this).parents('tr.delTrActEvt').find('.seqImage').val();
				var _resDelAct = enernet.modules.api.deleteImageMeter(_actObj.SeqWorker, _image);
				
				if(_resDelAct.result_code == 1) {
					console.log("_resDelAct = ", _resDelAct.result_code);
					alert('사진을 삭제하였습니다.');
					location.reload();
				} else{
					alert('사진 삭제 실패 (삭제에 필요한 권한을 확인하세요.)');
				}
				
			} // .onclick = function() 끝
		} // for 끝
	}; // delAct 끝
	delAct();
	
	//	이미지 팝업
	//	pop-show
	var _popImgEvt = null;
	
	if(document.getElementsByClassName('thumb-evt').length != 0){
		console.log("이미지 0 아님");
		
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