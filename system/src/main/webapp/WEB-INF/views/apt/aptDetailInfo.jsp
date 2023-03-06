<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>세대 정보</title>
<jsp:include page="../common/common.jsp"></jsp:include>

	<script type="text/javascript">
	
	//	fileupload
	$(function(){
		$("#fileUploadBtn").on('change', function(){
			fileUpload(this)
		});
		
	});
	
    function fileUpload(that) {
    	
    	var curFileCnt = that.files.length;
    	console.log("curFileCnt = ", curFileCnt);
    	
    	var form = new FormData();
    	
    	for (var i = 0; i <that.files.length; i++) {
    		form.append("file", that.files[i]);
    	}	
    	
    	var filesArr = new Array();
    	
		var settings = {
		  "url": "https://egservice.co.kr:18613/api/InsertPhotoListMeter?SeqWorker=${SEQ_ADMIN}&SeqHo=${SEQ_HO}",
		  "method": "POST",
		  "timeout": 0,
		  "processData": false,
		  "mimeType": "multipart/form-data",
		  "contentType": false,
		  "data": form
		};
	
		$.ajax(settings).done(function (response) {
      		
      		console.log(response);
      		alert('사진을 등록하였습니다.');
      		location.reload();
      		
		});
    }  	

	
    </script>	
   
</head>
<body id="page-top">
	<%-- session이 없는 경우 --%>
	<c:if test="${empty sessionScope.ADMIN}">
		<jsp:include page="../common/emptySession.jsp"></jsp:include>
	</c:if>
	<script src="${pageContext.request.contextPath}/resources/manager/apt/js/aptDetailInfo.js"></script>
	<script src="${pageContext.request.contextPath}/resources/manager/common/js/ckFileUpload.js"></script>
	<link href="<c:url value='/resources/vendor/viewer/css/viewer.css'/>" rel="stylesheet">
 	<script src="<c:url value='/resources/vendor/viewer/js/viewer.js'/>"></script>

	<c:if test="${not empty sessionScope.ADMIN}">
		<!-- Page Wrapper -->
		<div id="wrapper">
			
			<!-- Content Wrapper -->
			<div class="main-panel">
				
				<div class="row">
					
					<!-- 설치정보 -->
					<div class="" style="margin-right: 20px; width: 33%;">
						<div class="card shadow mb-4" style="position: fixed; top: 0; left: 0; right: 0; width: 31%;">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between" style="padding-left: 30px;">
								<input type="hidden" id="seq_admin" value="${sessionScope.ADMIN.seq_admin}" readonly>
								<input type="hidden" id="hoAdmin" value="${HO_INFO.seq_worker}" readonly>
								<input type="hidden" id="is_director" value="${sessionScope.ADMIN.is_director}" readonly>
								<input type="hidden" id="seqHo" value="${HO_INFO.seq_ho}" >
								<h3 class="m-0 font-weight-bold" style="color: white;">${HO_INFO.site_name}
									${HO_INFO.dong_name}동 ${HO_INFO.ho_name}호</h3>
							</div>

							<table class="table tablesorter " id="dataTable" style="text-align: center;">
								<thead>
									<tr>
										<th colspan="2" style="font-size: 17px; color: white;">철거계량기</th>
									</tr>
									<tr>
										<th style="color: white;">MID</th>
										<td> <input type="text" id="prevMid" value="${HO_INFO.mid_prev}"></td> 
									</tr>
									<tr>
										<th style="color: white;">최종지침</th>
										<td> <input type="text" id="oldValue" value="${HO_INFO.meter_value_last}"></td> 
									</tr>
								</thead>
								<thead>
									<tr>
										<th colspan="2" style="font-size: 17px; color: white;">신규계량기</th>
									</tr>
									<tr>
										<th style="color: white;">MID</th>
										<td> <input type="text" id="newMid" value="${HO_INFO.mid}"></td> 
									</tr>
									<tr>
										<th style="color: white;">시작지침</th>
										<td> <input type="text" id="newValue" value="${HO_INFO.meter_value_start}"></td> 
									</tr>
								</thead>
								
								<thead>
									<tr>
									<td style="color: rgba(255, 255, 255, 0.7);">
									
										<c:if test="${HO_INFO.bound_to_modem eq true}">
											<input type="checkbox" id="boundModem" checked="checked"> 모뎀이 부착된 계량기
										</c:if>
										
										<c:if test="${HO_INFO.bound_to_modem ne true}">
											<input type="checkbox" id="boundModem"> 모뎀이 부착된 계량기
										</c:if>
									
									</td>
									</tr>
								</thead>	
							</table>
							
							<div class="row">
								<div class="col-sm-12 col-md-12" style='text-align: center;'>
									<input type="button" id="actSaveBtn" class="btn btn-outline-primary btn-sm workSaveBtn" value="저장">
									<input type="button" id="actDelBtn" class="btn btn-outline-primary btn-sm workDelBtn" value="삭제" >
								</div>
							</div>

							<div style="padding-top: 20px; padding-left: 15px; padding-bottom: 20px;">
							<table class="x-table RB_APT_INFOtable"
								style="width: 450px; position: relative; overflow: visible;" cellspacing="0" id="34" cellpadding="0">
								<tbody class="rows-height-counter" style="font-size: 15px;">
									<tr style="height: 13px;" tridx="0" id="r-0-0">
										<td style="display: none;" id="A1-0-34"></td>
										<td class="fh bw pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="B1-0-34"></td>
										<td class="fh bw pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C1-0-34"></td>
									</tr>

									<tr style="height: 45px;" tridx="2" id="r-2-0">
										<td style="display: none;" id="A3-0-34"></td>
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 10pt; "
											id="B3-0-34">계량기 설치 정보</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); "
											id="C3-0-34"></td>
									</tr>
									<tr style="height: 22px;" tridx="9" id="r-9-0">
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 15pt;"
											id="C10-0-34">- 작업자 : ${HO_INFO.worker_name}
											(${HO_INFO.worker_id})</td>
									</tr>
									<tr style="height: 22px;" tridx="11" id="r-11-0">
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 15pt;"
											id="C12-0-34">- 설치일 : ${HO_INFO.time_meter_installed}</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); "
											id="C2-0-34"></td>
									</tr>

								</tbody>
							</table>
							</div>
							
						</div>

					</div>
					<!-- 설치정보 끝 -->

					<!-- 설치사진 목록 -->
					<div class="" style="width: 60%;">
						<div class="card shadow mb-4">
							<input type="hidden" class="seq_ho" name='seq_ho' value="${HO_INFO.seq_ho}">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between"
								style="padding-top: 20px;">
								<h3 class="m-0 font-weight-bold" style="color: white;">설치사진 목록</h3>
								
								<div class="row">
									<div class="col-sm-12 col-md-12" style="padding-left: 40px;">
										<label for="fileUploadBtn" class="btn btn-outline-primary btn-sm" style="cursor: pointer; color: white;">
										사진등록 &nbsp<i class="fas fa-upload"></i>
										</label>
										<input type="file" name="files" class="real-upload" required multiple id="fileUploadBtn" style="display: none;">
									</div> 
								</div>
							</div>

							<fieldset class="user-info" style="padding-top: ;">
								<div class="dl_table block" style="">
									<c:forEach items="${LIST_IMAGE}" var="LIST_IMAGE">
										<div style="padding-bottom: 15px; border: dotted;">
										<form class="imgForm" name="imgForm" id="imgForm" method='POST' action="<c:url value='../apt/imgDown2'/>">
											<div>
												<input type="text" value="${LIST_IMAGE.url_image}" class="url_image" name="url_image">
												<img class="thumb-img thumb-evt" src="${LIST_IMAGE.url_image}" data-1="${LIST_IMAGE.url_image}">
											</div>
										</form>	
											<table style=" font-size: 15px;">
												<tr class='delTrActEvt' style="color: rgba(255, 255, 255, 0.7);">
													<td style="color: white;">${LIST_IMAGE.worker_name}(${LIST_IMAGE.worker_id})</td>
													<td style="color: white;">${LIST_IMAGE.time_image_added}</td>
													<td><input type="hidden" class="seqImage" value="${LIST_IMAGE.seq_image_meter}"></td>
													<td>
														<input type="button" id="saveImg" class="btn btn-outline-primary btn-sm saveImg" value="저장">
														<input type="button" id="delPict" class="btn btn-outline-primary btn-sm delActBtn" value="삭제">
													</td>
												</tr>
											</table>
										</div>
									</c:forEach>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
				<!-- row end -->
			</div>
			<!-- End of Content Wrapper -->
		</div>
		<!-- End of Page Wrapper -->
		<jsp:include page="../common/logout.jsp"></jsp:include>
	</c:if>
</body>
</html>