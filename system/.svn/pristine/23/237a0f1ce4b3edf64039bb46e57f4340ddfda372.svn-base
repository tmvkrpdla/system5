<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>설치 작업자</title>
	
	<jsp:include page="../common/common.jsp"></jsp:include>
	
	<c:if test="${not empty sessionScope}">
	<script src="${pageContext.request.contextPath}/resources/manager/company/js/companyWorker.js"></script>
	</c:if>
	
</head>

<body id="page-top" oncontextmenu='return false' onselectstart='return false' ondragstart='return false'>
	<c:if test="${empty sessionScope.ADMIN}">
		<jsp:include page="../common/emptySession.jsp"></jsp:include>		
	</c:if>

	<c:if test="${not empty sessionScope.ADMIN}">
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="../common/sidebar.jsp"></jsp:include>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div class="main-panel">
		<!-- Topbar -->
		<jsp:include page="../common/topbar.jsp"></jsp:include>
		<!-- End of Topbar -->		

			<!-- Main Content -->
			<div class="content">
			
				<!-- Begin Page Content -->
				<div class="card">
					
					<!-- 검색, text -->
						<div class="row">
							<div class="col-xl-12">
								<div class="card shadow mb-4">
									<div class="card-body">
										<div style="width: 100%; display: inline-block;">
											<form id="searchForm" name="searchForm" method="post" action="<c:url value='../company/worker'/>">

												<div style="width: 100px; float: left;">
													<span style="display: inline-block; height: 21px;"></span><h5 style="display: inline-block;">협력 업체</h5>
												</div>
												
												<div style="width: 200px; float: left;">
													<select id="companySelect" name="companySelect" class="custom-select custom-date-sm dropdown-item" style="width: 95%;">
														<option value="0" selected>전체</option>
														<c:forEach items="${LIST_COMPANY}" var="LIST_COMPANY">
															<option value="${LIST_COMPANY.seq_company}"
																${LIST_COMPANY.seq_company==SEQCOMPANY ? 'selected' : '' }>${LIST_COMPANY.company_name}</option>
														</c:forEach>
													</select>
												</div>

												<div style="width: 15%; float: left;">
													<input type="hidden" readonly id="SeqCompany" name="SeqCompany" value="">
													<input type="button" id="searchBtn" class="btn btn-outline-primary btn-sm" value="조회">
												</div>
												
												<div style="width: 3%; float: right;">
													<input type="button" id="addWorkerBtn" class="btn btn-outline-primary btn-sm" value="추가">
												</div>

											</form>
										</div>
										<hr>
										
										<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
								
								<input type="hidden" id="nowPage" name="nowPage" value="${nowPage}" readonly>
								
									<div class="row">
										<div class="col-sm-12">
										
											<table class="table tablesorter" id="dataTable" style="text-align: center;">
												<thead>
													<tr class="aptMemTableHeaderTr" style="color: powderblue;">
														<td rowspan='2' width="8%">이름</td>
														<td rowspan='2' width="8%">ID</td>
														<td rowspan='2' width="10%">전화</td>
														<td rowspan='2' width="12%">이메일</td>
														<td rowspan='2' width="8%">소속사</td>
														<td rowspan='2' width="8%">등록일</td>
														<td rowspan='2' width="8%">최근접속</td>
														<td rowspan='2' width="10%">최근단지</td>
														<td colspan='4' width="28%">작업내용</td>
													</tr>
													<tr class="aptMemTableHeaderTr" style="color: powderblue;">
														<td width="7%">DCU</td>
														<td width="7%">모뎀</td>
														<td width="7%">계량기</td>
														<td width="7%">설치사진</td>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${LIST_WORKER}" var="LIST_WORKER">
														<tr>
															<td width="8%">${LIST_WORKER.worker_name}</td>
															<td width="8%">${LIST_WORKER.worker_id}</td>
															<td width="10%">${LIST_WORKER.worker_phone}</td>
															<td width="12%">${LIST_WORKER.worker_email}</td>
															<td width="8%">${LIST_WORKER.company_name}</td>
															<td width="8%">${LIST_WORKER.time_created}</td>
															<td width="8%">${LIST_WORKER.time_last_login}</td>
															<td width="10%">${LIST_WORKER.site_name_last}</td>
															<td width="7%">${LIST_WORKER.count_dcu}</td>
															<td width="7%">${LIST_WORKER.count_modem}</td>
															<td width="7%">${LIST_WORKER.count_meter}</td>
															<td width="7%"></td>
														</tr>
													</c:forEach>
												</tbody>

											</table>
										</div>
									</div>
									
									<!-- 페이징 -->
									<div class="row" style="margin-top: 20px;">
										<div class="col-sm-12 col-md-12">
											<div class="dataTables_paginate paging_simple_numbers"
												id="dataTable_paginate">
												<ul class="pagination">
												<c:if test="${PAGEUTIL.startPage eq 1}">
													<li class="paginate_button page-item previous" id="dataTable_previous">
														<a class="page-link">
															<
														</a>
													</li>
													</c:if>
													
													<c:if test="${PAGEUTIL.startPage ne 1}">
													<li class="paginate_button page-item previous" id="">
														<a href="../company/worker?nowPage=${PAGEUTIL.startPage - 1 }&SeqCompany=${SEQCOMPANY}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">
															<
														</a>
													</li>			
													</c:if>

													<c:if test="${PAGEUTIL.totalCount eq 0}">
													<li class="paginate_button page-item" id="">
														<a href="../company/worker?nowPage=1&SeqCompany=${SEQCOMPANY}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">
															1
														</a>
													</li>			
													</c:if>
													
													<c:if test="${PAGEUTIL.totalCount ne 0}">
													<!-- 	PAGE	[1][2][3] -->
													<c:forEach var="page" begin="${PAGEUTIL.startPage}" end="${PAGEUTIL.endPage}">
													<li class="paginate_button page-item" id="">
														<a href="../company/worker?nowPage=${page}&SeqCompany=${SEQCOMPANY}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">
															${page}
														</a>
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
														<a href="../company/worker?nowPage=${PAGEUTIL.endPage+1}&SeqCompany=${SEQCOMPANY}" class="page-link">
															>
														</a>
													</li>		
													</c:if>
												</ul>
											</div>
										</div>
									</div>
									<!-- 페이징 -->

								</div>
										
										

									</div>
								</div>
							</div>
						</div>
						<!-- 검색, text 끝-->

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
	</c:if>	


</body>

</html>
