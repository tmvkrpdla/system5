/**
 * 2020.04.23
 * v 1.0.3
 */
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

enernet.namespace('enernet.modules.utils');



enernet.modules.utils = function() {
	
	//	private
	//	id : 영문자 또는 숫자 만 가능
	var _expUserId =  /[a-z0-9]$/; 
	
	//	휴대폰 번호 체크
	var _expPhone =  /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;

	var _expPhoneHi = /^\d{3}-\d{3,4}-\d{4}$/;

	var _expTelHi = /^\d{2,3}-\d{3,4}-\d{4}$/;
	
	var _expEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	
	
	//	document 속성 id 가 존재여부를 확인하는 함수	: 존재하면 true
	var _targetIdCheck = function(target){
		
		if(document.getElementById(target) == undefined){
			console.log("target id is undefined : " + target);
			return true;
		}else{
			return false;
		}
	}
	
	//	document 속성 id 의 값을 가져오는 함수
	var _targetIdValue = function(target){
		return document.getElementById(target).value;
	}
	
	///////////	public
	//	휴대폰 번호 01012345678
	var _expPhoneCheck = function(str){
		return _expPhone.test(str);
	}
	
	//	데이터가 있는지 없는지 확인하는 함수		null : true 	not : false
	var _isNull = function(str) {
		if(str == undefined || str ==''){
			return true
		}else{
			if(str.trim() == ''){
				return true;
			}
			return false;
		}
	};//
	
	
	//	document 속성 id를 확인 한 후 값이 널인지 체크하는 함수
	//	null 일경우 true	아니면 false
	var _isNullById = function(target){
		if(!_targetIdCheck(target)){
			return _isNull(_targetIdValue(target));
		}
	}
	
	
	//	정규식 사용자 id체크하는 함수
	//	정규식 규칙에 맞으면 true	아니면 false
	var _expUserIdCheck = function(str){
		return _expUserId.test(str)
	}
	
	//	사용자 id의 정규식 체크하는 함수	
	//	정규식 규칙에 맞으면 true	아니면 false
	var _expUserIdCheckById = function(target){
		if(!_targetIdCheck(target)){
			var targetValue = _targetIdValue(target);
			return _expUserIdCheck(targetValue);
		}
		
	}//	_expUserIdCheckById	
	
	//	정규식 이메일 체크
	var _expEmailCheck = function(str){
		return _expEmail.test(str)
	}
	
	
	//	3자리수마다 컴마 : commaStr(string)
	var _commaStr = function commaStr(obj) {
		
		if(typeof obj == 'number'){
			obj = obj.toString();
		}
		
        var regx = new RegExp(/(-?\d+)(\d{3})/);
        var bExists = obj.indexOf(".", 0);//0번째부터 .을 찾는다.
        var strArr = obj.split('.');
        while (regx.test(strArr[0])) {//문자열에 정규식 특수문자가 포함되어 있는지 체크
            //정수 부분에만 콤마 달기 
            strArr[0] = strArr[0].replace(regx, "$1,$2");//콤마추가하기
        }
        if (bExists > -1) {
            //. 소수점 문자열이 발견되지 않을 경우 -1 반환
            obj = strArr[0] + "." + strArr[1];
        } else { //정수만 있을경우 //소수점 문자열 존재하면 양수 반환 
            obj = strArr[0];
        }
        return obj;//문자열 반환
    }
	
	//	휴대폰번호 하이픈 넣기
	var _phoneFormat = function(str){
		var _temp = str;
		if(typeof _temp == 'number'){
			//console.log(_temp)
			_temp = _temp.toString();
		}
		
		return _temp.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
	}
	
	//	첫번째 자리가 0인지 확인, 2자리 이상일 경우
	var _isFirstZero = function(temp){
		
		var _result = false;
		
		if(typeof temp == 'number'){
			temp = temp.toString();
		}
		
		if(temp.length>=2){
			if(temp.slice(0,1) == '0'){
				_result = true;
			}
		}
		
		
		return _result;
	}
	
	//	이미지 확장자 검색
	var _isImgExt = function(str){
		var _imgArr = ['JPG', 'JPEG', 'BMP', 'PNG', 'GIF']
		var _str = str.slice(str.lastIndexOf('.')+1);
		
		var _result = false;
		/*if(_imgArr.includes(_str.toLocaleUpperCase())){
			_result = true;
		}*/
		
		for( var i=0; i< _imgArr.length; i++){
			if(_str.toLocaleUpperCase().indexOf(_imgArr[i]) > -1){
				//console.log(_str.toLocaleUpperCase().indexOf(_imgArr[i]))
				_result = true;
				break;
			}
		}
		
		
		return _result;
	}
	
	///////////////////////////////////////////
	var _getTimeStampHHmm = function getTimeStampHHmm(){
		var date = new Date();
		var week = ['일', '월', '화', '수', '목', '금', '토'];
		var dayOfWeek = week[new Date(
		            leadingZeros(date.getFullYear(), 4) + '-' +
		            leadingZeros(date.getMonth() + 1, 2) + '-' +
		            leadingZeros(date.getDate(), 2)).getDay()];
	    //년-월-일  시:분:초
		var f_date =
			leadingZeros(date.getFullYear(), 4) + '년 ' +
		    leadingZeros(date.getMonth() + 1, 2) + '월 ' +
		    leadingZeros(date.getDate(), 2) + '일 ' +
		    "(" + dayOfWeek + ") " +
		    leadingZeros(date.getHours(), 2) + ':' +
		    leadingZeros(date.getMinutes(), 2);

		return f_date;
	}
	
	//	return 되는 값 2020년 03월28일 (토) 20:33:43
	var _getTimeStamp = function getTimeStamp() { // 24시간제
		var date = new Date();
		var week = ['일', '월', '화', '수', '목', '금', '토'];
		var dayOfWeek = week[new Date(
		            leadingZeros(date.getFullYear(), 4) + '-' +
		            leadingZeros(date.getMonth() + 1, 2) + '-' +
		            leadingZeros(date.getDate(), 2)).getDay()];
	    //년-월-일  시:분:초
		var f_date =
			leadingZeros(date.getFullYear(), 4) + '년 ' +
		    leadingZeros(date.getMonth() + 1, 2) + '월 ' +
		    leadingZeros(date.getDate(), 2) + '일 ' +
		    "(" + dayOfWeek + ") " +
		    leadingZeros(date.getHours(), 2) + ':' +
		    leadingZeros(date.getMinutes(), 2) + ':' +
		    leadingZeros(date.getSeconds(), 2);

		return f_date;
	}
		//	_getTimeStamp와 함께 쓰임 숫자 두자리 ex) 1이면 01 앞에 0을 붙임
	function leadingZeros(date, digits) {
		var zero = '';
		date = date.toString();

		if (date.length < digits) {
			for (i = 0; i < digits - date.length; i++)
				zero += '0';
		}
		return zero + date;
	}
	
	function _getDate(){
		var _date = new Date();
		return leadingZeros(_date.getFullYear(), 4) + '-' +
        leadingZeros(_date.getMonth() + 1, 2) + '-' +
        leadingZeros(_date.getDate(), 2);
	}
	
	/////////////////////////////////////////////
	
	//////////////////////////////////

	// 특권 메서드가 들어있는 객체를 반환
	return {
		isNull : _isNull,
		isNullById:_isNullById,
		commaStr : _commaStr,
		expUserIdCheck : _expUserIdCheck,
		expUserIdCheckById : _expUserIdCheckById,
		getTimeStamp : _getTimeStamp,
		getTimeStampHHmm : _getTimeStampHHmm,
		phoneFormat : _phoneFormat,
		isFirstZero : _isFirstZero,
		isImgExt : _isImgExt,
		expPhoneCheck : _expPhoneCheck,
		expEmailCheck : _expEmailCheck,
		getDate : _getDate
	};
}();





