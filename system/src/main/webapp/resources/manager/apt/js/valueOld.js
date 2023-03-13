
$(function() {
    
	// 엑셀 다운로드
	   $("#excelDown").on("click", function() {
	      $("#oldmeterExcelView").attr("action", "../apt/oldmeterExcelView").submit();
	   });
	   
	   $("#excelDownA").on("click", function() {
	      $("#oldmeterExcelViewA").attr("action", "../apt/oldmeterExcelViewA").submit();
	   });
	   
	   //계량기 사진 다운 테스트
	   $("#imageDown").on("click", function() {
		      $("#meterImageDown").attr("action", "../apt/meterImageDown").submit();
		   });

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
      
      $("#nowPage").val("");
      
      var target = document.getElementById("siteSelect");
//      alert('선택된 옵션 text 값=' + target.options[target.selectedIndex].text);     // 옵션 text 값
      var _siteName = target.options[target.selectedIndex].text;
      
      $("#SiteName").val(_siteName);
      $("#SiteName2").val(_siteName);
      
   });
   
   // 조회 버튼
   $("#searchBtn").on('click', function() {
      $("#searchForm").submit();
   });
   
   
   // 아파트 상세정보 (클릭 이벤트)
   function aptDetailInfo() {
      
      const delta = 6;
      let startX;
      let startY;
      
      document.addEventListener('mousedown', function (event) {
         startX = event.pageX;
         startY = event.pageY;
      });
      
      document.addEventListener('mouseup', function (event) {
         const diffX = Math.abs(event.pageX - startX);
         const diffY = Math.abs(event.pageY - startY);
         
      
         var _AptDetailtr = document.getElementsByClassName('aptmentEventTr');
         if (_AptDetailtr.length != 0) {
   
            for (var i = 0; i < _AptDetailtr.length; i++) {
               _AptDetailtr[i].onclick = function(e) {
                  if (diffX < delta && diffY < delta) {
                     //alert("클릭");
                     $(this).find('form').attr('method', 'post').attr('action', '../apt/aptDetailInfo.do');
                     var _aptDetailForm = $(this).find('form')[0];
                     var _ho = $(_aptDetailForm).find("input[name='seq_ho']").val();
   
                     var _outerWidth = window.outerWidth * 0.7;
                     var _outerHeight = window.outerHeight * 0.8;
   
                     window.open("", '_aptDetailForm', 'height=' + _outerHeight + ', width=' + _outerWidth + ', left=20, top=10, scrollbars=1');
                     _aptDetailForm.target = '_aptDetailForm';
                     _aptDetailForm.submit();
                  }
               }
            }
         }
      });
   };
   aptDetailInfo();

});