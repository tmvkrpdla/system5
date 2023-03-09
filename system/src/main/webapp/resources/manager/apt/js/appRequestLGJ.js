

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
      
      var target = document.getElementById("siteSelect");
//      alert('선택된 옵션 text 값=' + target.options[target.selectedIndex].text);     // 옵션 text 값
      var _siteName = target.options[target.selectedIndex].text;
      
      $("#SiteName").val(_siteName);
      
   });
//   
//   let listTbody = document.getElementByClassName('tbody')[0];
//   listTbody.innerText="";
//   let getSelectedInfo= "";
   
   
//   tbody.appendChild();
   
   // 조회 버튼
   $("#searchBtn").on('click', function() {
      $("#searchForm").submit();
   });
   
   
   
   
   $("#acceptSend").on("click", function() {
	   let request_state = document.getElementsByClassName('request_state_name');
	   
	   let acceptList = [];
	   let rejectList = [];
	   
	   
	   for(let i = 0; i < request_state.length ; i++){
		   if(request_state[i].value == "승인대기"){
			   if(document.getElementsByName("acceptState"+i)[0].checked){
//				   alert("승인대기이면서 승인체크");
				   acceptList.push(document.getElementsByName("seq_ho")[i].value);
			   	}else if(document.getElementsByName("acceptState"+i)[1].checked){
//				   alert("승인대기이면서 거절체크");
				   rejectList.push(document.getElementsByName("seq_ho")[i].value);
				    }
		   		}else if(request_state[i].value == "승인됨"
			   && document.getElementsByName("acceptState"+i)[1].checked){
//			   		alert("승인됨 이면서 거절");
				   
					   rejectList.push(document.getElementsByName("seq_ho")[i].value);
		   		}else if(request_state[i].value == "거절됨"
			   && document.getElementsByName("acceptState"+i)[0].checked){
//			   		alert("거절됨 이면서 승인");
			   		acceptList.push(document.getElementsByName("seq_ho")[i].value);
		   }
	   }
	   
	   document.getElementById("acceptList").value = acceptList;
	   document.getElementById("rejectList").value = rejectList;
	   
	   
	   if (acceptList.length != 0 || rejectList.length != 0 ){
	      $("#appRequestSend").attr("action", "../apt/appRequestSendLGJ").submit();
	   }
	   });
   
   

});

function cancleChoose(i){
	document.getElementsByName("acceptState"+i)[0].checked = false;
	document.getElementsByName("acceptState"+i)[1].checked = false;
}