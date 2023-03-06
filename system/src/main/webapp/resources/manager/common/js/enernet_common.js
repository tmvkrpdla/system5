
var enernet = enernet || {};
enernet.namespace = function(ns_string) {
	var parts = ns_string.split('.'), parent = enernet, i;
	if (parts[0] === "enernet") {
		parts = parts.slice(1);
	}
	for (i = 0; i < parts.length; i += 1) {
		if (typeof parent[parts[i]] === "undefined") {
			parent[parts[i]] = {};
		}
		parent = parent[parts[i]];
	}
	return parent;
}
enernet.namespace('enernet.modules.common');

enernet.modules.common = function() {
	
	//	private	error 로그 처리
	function _ajaxCall(_url, _data){
			
		$.ajax({ 
			 
			url : _url, 
			contentType : 'application/json;charset=utf-8;',
			type : 'POST',
			data : JSON.stringify(_data), 
			success : function(data){ 
			}, 
			error: function(e){ 
				//console.log(e) 
				//alert('서버와 실패하였습니다'); 
			} 
		});
	}
	
	//	======================================================================	//
	
	function _apiCallAsync(_url, _data){
		var _returnData = null;
		
		$.ajax({
			url : _url,
			type: 'POST',
			dataType : 'json',
			data : _data,
			async : false,
			beforeSend : function(){
				$('div.wrap-loading').removeClass('display-none');
			},
			success : function(response){
				_returnData = response;
			},
			complete : function(){
				$('.wrap-loading').addClass('display-none');
			},
			error : function(e){
				$('.wrap-loading').addClass('display-none');
				
				var _obj = {}
				_obj.data = _data;
				_obj.status = e.status;
				_obj.statusText = e.statusText;
				_obj.requestUrl = _url;
				
				_ajaxCall("../ajax/error", _obj);
			}
		});
		
		return _returnData;
	}
	
	
	//	form 전송		->	java
	function _ajaxFormPostAsync( requestUrl, formName){
		var _returnData = null;
		
		var _form = $("form[name="+formName+"]")[0];
		
		var _formData = new FormData(_form)
		
		console.log(_formData)
		
		$.ajax({
			url : requestUrl,
			type: 'post',
			processData: false,
            contentType: false,
			data : _formData,
			async : false,
			beforeSend : function(){
				$('div.wrap-loading').removeClass('display-none');
			},
	    	success : function(response){
	    		//console.log(2)
				_returnData = response;
			},
			complete : function(){
				$('.wrap-loading').addClass('display-none');
			},
			error : function(e){
				//console.log(_form)
				alert('서버 전송에 실패하였습니다');
				var _obj = {}
				_obj.status = e.status;
				_obj.statusText = e.statusText;
				_obj.requestUrl = requestUrl;
				_obj.data = {};
				
				var _formNameData = {};
				
				var _inputNameArr = $(_form).find('input[name]');
				for( var i=0; i<_inputNameArr.length; i++){
					var _inputName = _inputNameArr[i].name;
					
					if($("#"+ _inputName).length == 0){
						continue;
					}else{
						var _inputData = $("#"+ _inputName).val();
						_formNameData[_inputName] = _inputData;
					}
				}
				
				_obj.data = _formNameData;
				
	    		_ajaxCall("../ajax/error", _obj);
	    	}
		});
		
		return _returnData;
	}
	
	
	return {
		ajaxFormPostAsync : _ajaxFormPostAsync,
	};
	
}();


var _devMode = true;

// F12 버튼 방지
if(!_devMode){
	$(document).bind('keydown',function(e){
		if (e.keyCode == 123) {
			e.preventDefault();
			e.returnValue = false;
		}
	});
}
 
