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
enernet.namespace('enernet.modules.evt');

enernet.modules.evt = function() {
	//	private
	
	/**
	 * targetId
	 * foucsId
	 * 엔터이벤트 발생하면 다음 인풋을 포커스함
	 */
	function _enterFoucs(targetId, afterFocusId){
		$("#"+targetId).on('keypress', function(e){
			if(e.keyCode == 13){
				$("#"+afterFocusId).focus();
			}	
		})
	};
	
	/**
	 * targetId
	 * btnId
	 * 엔터이벤트 발생하면 다음 버튼 클릭함
	 */
	function _enterBtnClick (targetId, afterBtnId){
		$("#"+targetId).on('keypress', function(e){
			if(e.keyCode == 13){
				$("#"+afterBtnId).click();
			}
		})
	}
		
	function _makeDateYMDFromTo(targetId, from, to){
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
			minDate: new Date(from),
			maxDate: new Date(to),
			}).focus(function(){
				$(".ui-datepicker-calendar").show();
			});
			
	}//
	
	//	데이트 피커 일
	function _makeDateYMD(targetId){
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
	
	//	데이트 피커 일
	function _makeDateYMD_V2(targetId){
		$("#"+targetId).datepicker({
			changeMonth: true,
			changeYear: true,
			showMonthAfterYear:true,
			dateFormat: 'yymmdd',
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
	
	//	데이트 피커 시간
	function _makeDateYMDH(targetId){
		$("#"+targetId).datepicker({
			changeMonth: true,
			changeYear: true,
			showMonthAfterYear:true,
			dateFormat: 'yy-mm-dd-HHmm',
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

	
	//	데이트 피커 월
	function _makeDateYM(targetId){
		$("#"+targetId).datepicker({
			changeMonth: true,
            changeYear: true,
            dateFormat: 'yy-mm',
			nextText: '다음달',
			prevText: '이전달',
			currentText: "오늘",
			closeText : "적용",
			buttonText: "선택" ,
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
			showMonthAfterYear:true,
			showButtonPanel: true,
			minDate: "-30Y",
			maxDate: 0,
			onClose: function(dateText, inst) { 
                $(this).datepicker('setDate', new Date(inst.selectedYear, inst.selectedMonth, 1));
            }
			}).focus(function () {
			    $(".ui-datepicker-calendar").hide();
			});
			
	}//
	
	//	데이트 피커 연도
	function _makeDateY(targetId){
		$("#"+targetId).datepicker({
			minViewMode: 'years',
            changeYear: true,
            dateFormat: 'yy',
			nextText: '다음년',
			prevText: '이전년',
			currentText: "오늘",
			closeText : "적용",
			buttonText: "선택" ,
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			stepMonths: '12',
			showMonthAfterYear:true,
			showButtonPanel: true,
			minDate: "-30Y",
			maxDate: 0,
			onClose: function(dateText, inst) { 
                $(this).datepicker('setDate', new Date(inst.selectedYear, inst.selectedMonth, 1));
            }
			}).focus(function () {
			    $(".ui-datepicker-calendar").hide();
			});
	}	
	
	// 타임피커
	function _makeTimePicker(targetId){
		
		$("#"+targetId).datetimepicker({
			dateFormat:'yy-mm-dd',
			monthNamesShort:[ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
			dayNamesMin:[ '일', '월', '화', '수', '목', '금', '토' ],
			changeMonth:true,
			changeYear:true,
			showMonthAfterYear:true,

			// timepicker 설정
			timeFormat:'HH:mm:ss',
			controlType:'select',
			oneLine:true,
		});
	}
	
	//	=	=	=	=	=	=	=	=	=	=	=
	
	return {
		enterFocus : _enterFoucs,
		enterBtnClick : _enterBtnClick,
		makeDateYMD :_makeDateYMD,
		makeDateYM : _makeDateYM,
		makeDateY : _makeDateY,
		makeDateYMDFromTo : _makeDateYMDFromTo,
		makeDateYMDH : _makeDateYMDH,
		makeDateYMD_V2 : _makeDateYMD_V2,
		makeTimePicker : _makeTimePicker
	};
	
}();