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
   
   // 단지 선택
   $("#siteSelect").on('change', function() {
      //console.log($(this).val());
      var _seqSite = $(this).val();
      $("#SeqSite").val(_seqSite);
      $("#nowPage").val("");
      $("#SeqDong").val("선택");

      var _obj = {};
      _obj.SeqSite = _seqSite;

      var _res = enernet.modules.api.getDongListBySite(_obj.SeqSite);

      $("#aptDongSelect").find('option').remove()
      var _list_dong = _res.list_dong;
      
      var _tag = "<option selected value=" + "선택" + ">"
      _tag += "선택";
      _tag += "</option>";
      $("#aptDongSelect").append(_tag);

      for (var i = 0; i < _list_dong.length; i++) {
         var _tag2 = "<option value=" + _list_dong[i].seq_dong + ">"
         _tag2 += _list_dong[i].dong_name;
         _tag2 += "</option>";
         
         $("#aptDongSelect").append(_tag2);
      }
      
   });
   
   $("#aptDongSelect").on('change', function(){
      //console.log($(this).val());
      var _seqDong = $(this).val();
      $("#SeqDong").val(_seqDong);
      $("#nowPage").val("");
      
   });
   
   //   조회 버튼
   $("#searchBtn").on('click', function() {
      
      if($("#siteSelect").val() == "선택" ) {
         return alert("단지를 선택해주세요.");
      }
      
      
      if($("#SeqDong").val() == "선택" ) {
         return alert("동을 선택해주세요.");
      }
      
      $("#searchForm").submit();
   //location.href = "../apt/aptment?SeqSite="+_seqSite.val();
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