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
<title>현장 사진 조회</title>
<jsp:include page="../common/common.jsp"></jsp:include>

<script type="text/javascript">
	var _seqJobCode = "${SEQ_JOB_CODE}";
	var _seqJobCodeList = [];
	
	<c:forEach items="${LIST_JOB_CODE}" var="LIST_JOB_CODE">
	
		var _jobObj = {};
		_jobObj.value = "${LIST_JOB_CODE.job_category} : ( ${LIST_JOB_CODE.job_code} ) ${LIST_JOB_CODE.job_name}";
		_jobObj.seq_job_code = "${LIST_JOB_CODE.seq_job_code}";
	
		_seqJobCodeList.push(_jobObj);
	
	</c:forEach>

</script>

<link href="../resources/vendor/viewer/css/viewer.css" rel="stylesheet">
<script src="../resources/vendor/viewer/js/viewer.js"></script>

<script src="../resources/manager/site/js/photoList.js"></script>

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="../common/sidebar.jsp"></jsp:include>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<jsp:include page="../common/topbar.jsp"></jsp:include>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800"> 현장 사진 조회 </h1>
					</div>

					<!-- contents -->
					<div class="card shadow mb-4">
						
						<div class="card-body">
							
							<div class="">
								<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
									<!-- search -->
									<div class="row">
										<div class="col-sm-12 col-md-12">
											<div class="" style="float:left;width:100%;">
												<div class="" style="float:left;width:100%;text-align: center;">
													<form id="photoSearchForm" name="photoSearchForm" method="get" action="<c:url value='../site/photoList' />">
													
													<label class="" style="padding-top : 0.25rem; padding-bottom : 0.25rem; padding-right : 0.5rem;" for="seq_project"> 공사 </label>
													
													<select style="width:25%;" id="seq_project" name="seq_project" aria-controls="" class="custom-select custom-select-sm">
														<option value="0">전체</option>
														<c:forEach items="${PROJECT_LIST}" var="PROJECT_LIST">
														<option value="${PROJECT_LIST.seq_project}"> ${PROJECT_LIST.project_name}</option>
														</c:forEach>
														
													</select>
													
													<div class="" style="display: inline-block;width:35px;"></div>

													<label class="" style="padding-top : 0.25rem; padding-bottom : 0.25rem; padding-right : 0.5rem;" for="reportCreateDate"> 작성일 </label>
													
													<input class="custom-date custom-date-sm" id="date_work" name="date_work" type="text" placeholder="" style="width:10%;">
													
													<div class="" style="display: inline-block;width:35px;"></div>
													
													<label class="" style="padding-top : 0.25rem; padding-bottom : 0.25rem; padding-right : 0.5rem;" for="seq_job_code"> 공종 코드 </label>
													<input id="seq_job_code" name="seq_job_code" class="custom-input custom-input-sm" style="width:15%;">
												
													<div class="" style="display: inline-block;width:35px;"></div>
													
													<input type="button" id="photoListSearchBtn" class="btn btn-outline-primary btn-sm" value="조회">
													</form>
													
												</div>
												
											</div>	
										</div>
									</div>
									<br>
									
									<c:if test="${fn:length(MMS_LIST) eq 0}">
										<div class="row">
											<div class="col-sm-12">
												<table class="table table-bordered dataTable photoListTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
												
													<thead>
														<tr>
															<td>
																조회된 데이터가 없습니다.			
															</td>
														</tr>
													</thead>
												</table>
												
											</div>
										</div>
									</c:if>
									
									<c:if test="${fn:length(MMS_LIST) ne 0 }">
									<div class="row">
										<div class="col-sm-12">
											<table class="table table-bordered table-hover dataTable ntcListTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
												<thead>
													<tr style="text-align: center;">
														<th class="" style="width: 15%;">공사번호</th>
														<th class="" style="width: 25%;">공사명</th>
														<th class="" style="width: 10%;">구분</th>
														<th class="" style="width: 10%;">코드</th>
														<th class="" style="width: 15%;">작업공종명</th>
														<th class="" style="width: 15%;">작성일</th>
														<th class="" style="width: 10%;">현장 감독자</th>
														
													</tr>
												</thead>
												
												<tbody>
													<c:forEach items="${MMS_LIST}" var="MMS_LIST">
													<tr class="mmsListEvt" style="text-align: center;">
														<td>
															${MMS_LIST.project_id}
															<%-- <form method="post" name="ntcListForm">
																<input type="hidden" name="nowPage" value="${nowPage}" readonly>
																<input type="hidden" name="seq_notice" value="${NOTICE_LIST.seq_notice}" readonly>
																<input type="hidden" name="title" value="${NOTICE_LIST.title}" readonly>
																<input type="hidden" name="notice_type" value="${NOTICE_LIST.notice_type}" readonly>
																
																<textarea rows="" cols="" name="content" style="display:none;" readonly>${NOTICE_LIST.content}</textarea>
																
															</form> --%>
														</td>
														<td>
															${MMS_LIST.project_name}
														</td>
														<td>
															${MMS_LIST.job_category}
														</td>
														<td>
															${MMS_LIST.job_code}
														</td>
														<td>
															${MMS_LIST.job_name}
														</td>
														<td>
															${MMS_LIST.mms_time_created}
														</td>
														<td>
															${MMS_LIST.member_name}
														</td>
													</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									</c:if>
									
									<c:if test="${fn:length(MMS_LIST) ne 0}">
									<div class="row">
										<c:forEach items="${MMS_LIST}" var="MMS_LIST" varStatus="status">
										
											<div class="col-sm-4 col-md-4">
												<div class="card mb-4">
										
													<div class="thumb-outer-frame">
														<div class="thumb-inner-frame">
															
															<span class="thumb-helper"></span><img class="thumb-img thumb-evt" src="${MMS_LIST.url_thumbnail}" data-1="${MMS_LIST.url_image}">
														</div>
													</div>

													<div class="card-body">
														<h5 class="card-title text-primary">${MMS_LIST.project_name } [${MMS_LIST.project_id}]</h5>
														<table class="table table-bordered dataTable photoListTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
														<thead>
															<tr role="row">
																	
																<th class="" style="width: 40px;">구분</th>
																<th class="" style="width: 30px;">코드</th>
																<th class="" style="width: 100px;">작업공종명</th>
																<th class="" style="width: 70px;">작성일 </th>
																<th class="" style="width: 70px;">현장 감독자</th>
															</tr>
															<tr>
															
																<td>
																	${MMS_LIST.job_category}
																</td>
																
																<td>
																	${MMS_LIST.job_code}
																</td>
																
																
																<td>
																	${MMS_LIST.job_name}
																</td>
																
																<td>
																	${MMS_LIST.mms_date_work}
																</td>
																
																<td>
																	${MMS_LIST.member_name}
																</td>
															</tr>
														</thead>
														</table>
													</div>
													
												</div>
											</div>
									
										</c:forEach>
									</div>
									</c:if>
										
									<br />
									<!-- 페이징 -->
									<div class="row">

										<div class="col-sm-12 col-md-12">
											<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
												<ul class="pagination">
													<input type="hidden" id="nowPage" name="nowPage" value="${nowPage}" readonly>
													
													<c:if test="${PAGEUTIL.startPage eq 1}">
													<li class="paginate_button page-item previous" id="dataTable_previous">
														<a class="page-link">
															<
														</a>
													</li>
													</c:if>
													
													<c:if test="${PAGEUTIL.startPage ne 1}">
													<li class="paginate_button page-item previous" id="">
														<a href="../site/photoList?nowPage=${PAGEUTIL.startPage - 1 }&seq_project=${SEQ_PROJECT}&date_work=${DATE_WORK}&seq_job_code=${SEQ_JOB_CODE}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">
															<
														</a>
													</li>			
													</c:if>

													<c:if test="${PAGEUTIL.totalCount eq 0}">
													<li class="paginate_button page-item" id="">
														<a href="../site/photoList?nowPage=1&seq_project=${SEQ_PROJECT}&date_work=${DATE_WORK}&seq_job_code=${SEQ_JOB_CODE}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">
															1
														</a>
													</li>			
													</c:if>
													
													<c:if test="${PAGEUTIL.totalCount ne 0}">
													<!-- 	PAGE	[1][2][3] -->
													<c:forEach var="page" begin="${PAGEUTIL.startPage}" end="${PAGEUTIL.endPage}">
													<li class="paginate_button page-item" id="">
														<a href="../site/photoList?nowPage=${page}&seq_project=${SEQ_PROJECT}&date_work=${DATE_WORK}&seq_job_code=${SEQ_JOB_CODE}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">${page}</a>
													</li>			
													</c:forEach>
													</c:if>
													
													<c:if test="${PAGEUTIL.endPage eq PAGEUTIL.totalPage}">
													<li class="paginate_button page-item next" id="dataTable_previous">
														<a class="page-link">
															>
														</a>
													</li>		
													</c:if>
													
													<c:if test="${PAGEUTIL.endPage ne PAGEUTIL.totalPage}">
													<li class="paginate_button page-item next" id="dataTable_previous">
														<a href="../site/photoList?nowPage=${PAGEUTIL.endPage+1}&seq_project=${SEQ_PROJECT}&date_work=${DATE_WORK}&seq_job_code=${SEQ_JOB_CODE}" class="page-link">
															>
														</a>
													</li>		
													</c:if>

												</ul>
											</div>
										</div>
									</div>
									<!-- 페이징 -->	
								
									<!-- table end -->
									<form name="photoListSearchParamForm">
										<input type="hidden" id="search_seq_project" name="search_seq_project" value="${SEQ_PROJECT}" readonly>
										<input type="hidden" id="search_date_work" name="search_date_work" value="${DATE_WORK}" readonly>
										<input type="hidden" id="search_mms_type" name="search_mms_type" value="${MMS_TYPE}" readonly>
									</form>
									
								</div>
								
							</div>
						
						</div>
						<!-- end card-body -->
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->

			<jsp:include page="../common/footer.jsp"></jsp:include>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<!-- Logout Modal-->
	<%-- util.jsp : logout/scroll top.. --%>
	<jsp:include page="../common/logout.jsp"></jsp:include>

</body>

</html>
