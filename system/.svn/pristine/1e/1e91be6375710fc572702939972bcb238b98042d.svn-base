$(function(){

	// 협력업체 선택
	$("#companySelect").on('change', function() {
		// console.log($(this).val());

		var _seqCompany = $(this).val();
		// console.log("_seqCompany = " + _seqCompany);
		$("#SeqCompany").val(_seqCompany);

	});
	
	// 등록 버튼
   $("#joinBtn").on('click', function join(){
	   
      if($("#workerId").val().trim() == ''){
         return alert('ID를 입력해주세요.');
      }
      var _joinObj = {};
      _joinObj.SeqCompany = $("#SeqCompany").val();
      _joinObj.WorkerId = $("#workerId").val();
      _joinObj.Password =$("#passWord").val();
      _joinObj.Name =$("#w_name").val();
      _joinObj.Phone =$("#w_phone").val();
      _joinObj.Mail =$("#w_email").val();
      
      var _res = enernet.modules.api.createWorker(_joinObj.SeqCompany, _joinObj.WorkerId, _joinObj.Password, _joinObj.Name, _joinObj.Phone, _joinObj.Mail);
      
      if(_res.seq_worker_new==0){
           return alert('등록 실패');
           
      }else{
    	  alert('등록에 성공하였습니다.');
    	  opener.window.location.reload();
    	  window.close();
         }
      
   });
   
   // 중복확인 버튼
   $("#repetitionBtn").on('click', function repetition(){
	   
      var _joinObj = {};
      _joinObj.WorkerId = $("#workerId").val();
      
      var _res = enernet.modules.api.getWorkerIdCount(_joinObj.WorkerId);
      
      if(_res.count_worker==1){
           return alert('이미 있는 ID입니다. 다른 ID를 사용해주세요.');
           
      }else{
    	  return alert('사용가능한 ID입니다.');
         }
      
   }); //saveAct end	
   
});