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
	<title>계량기 현황</title>
	
	<jsp:include page="../common/common.jsp"></jsp:include>
	
	<c:if test="${not empty sessionScope}">
	<script src="${pageContext.request.contextPath}/resources/manager/current/js/meterList.js"></script>
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
			<div class= "content">
				
				<!-- Begin Page Content -->
				<div class="card">
					
						<!-- 검색, text -->
						<div class="row">
							<div class="col-xl-12">
								<div class="card shadow mb-4">
									<div class="card-body">
										<div style="width: 100%; display: inline-block;">
											<form id="searchForm" name="searchForm" method="post" action="<c:url value='../current/meterList'/>">

												<div style="width: 100px; float: left;">
													<span style="display: inline-block; height: 21px;"></span><h5 style="display: inline-block;">단지 선택</h5>
												</div>

												<div style="width: 200px; float: left;">
													<select id="siteSelect" name="siteSelect" class="custom-select custom-date-sm dropdown-item" style="width: 95%;">
														<option value="0" selected>전체</option>
														<c:forEach items="${LIST_SITE}" var="LIST_SITE">
															<option value="${LIST_SITE.seq_site}"
																${LIST_SITE.seq_site==SEQSITE ? 'selected' : '' }>${LIST_SITE.site_name}</option>
														</c:forEach>
													</select>
													<input type="hidden" readonly id="SeqSite" name="SeqSite" value="">
												</div>
												

												<div style="width: 100px; float: left; padding-left: 15px;">
													<span style="display: inline-block; height: 21px;"></span><h5 style="display: inline-block;">아파트 동</h5>
												</div>

												<div style="width: 150px; float: left;">
													<select id="aptDongSelect" name="seq_apt_dong" class="custom-select custom-date-sm dropdown-item" style="width: 120px;">
														<c:forEach items="${LIST_DONG}" var="LIST_DONG">
															<option value="${LIST_DONG.seq_dong}"
																${LIST_DONG.seq_dong==SEQ_APT_DONG ? 'selected' : '' }>${LIST_DONG.dong_name}</option>
														</c:forEach>
													</select>
												</div>

												<div style="width: 15%; float: left;">
													<input type="hidden" readonly id="SeqDong" name="SeqDong" value="">
													<input type="button" id="searchBtn" class="btn btn-outline-primary btn-sm" value="조회">
												</div>

												<div style="width: 50px; float: left;">
													<span style="display: inline-block; height: 21px;"></span><h5 style="display: inline-block;">DCU</h5>
												</div>

												<div style="width: 200px; float: left;">
													<select id="dcuSelect" name="dcuSelect" class="custom-select custom-date-sm dropdown-item" style="width: 95%;">
														<option value="0" selected>전체</option>
														<c:forEach items="${LIST_DCU}" var="LIST_DCU">
															<option value="${LIST_DCU.seq_dcu}"
																${LIST_DCU.seq_dcu==SEQDCU ? 'selected' : '' }>${LIST_DCU.dcu_id}</option>
														</c:forEach>
													</select>
												</div>

												<div style="width: 15%; float: left;">
													<input type="hidden" readonly id="SeqDcu" name="SeqDcu" value="">
													<input type="button" id="searchBtn2" class="btn btn-outline-primary btn-sm" value="조회">
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
														<td width="6%">NID</td>
														<td width="6%">MID</td>
														<td width="6%">모뎀 동반</td>
														<td width="10%">모뎀 MAC</td>
														<td width="13%">단지</td>
														<td width="6%">동</td>
														<td width="5%">호</td>
														<td width="7%">모델명</td>
														<td width="6%">설치자</td>
														<td width="7%">설치사진</td>
														<td width="10%">최근통신</td>
														<td width="6%">기존 MID</td>
														<td width="6%">기존지침</td>
														<td width="6%">시작지침</td>
													</tr>
												</thead>
												
												<tbody>
												<c:forEach items="${LIST_METER}" var="LIST_METER">
													<tr class="">
														<td width="6%">${LIST_METER.dcu_id}</td>
														<td width="6%">${LIST_METER.mid}</td>
														<td width="6%">${LIST_METER.bound_to_modem}</td>
														<td width="10%">${LIST_METER.mac_modem}</td>
														<td width="13%">${LIST_METER.site_name}</td>
														<td width="6%">${LIST_METER.dong_name}</td>
														<td width="5%">${LIST_METER.ho_name}</td>
														<td width="7%">${LIST_METER.meter_model}</td>
														<td width="6%">${LIST_METER.worker_name}</td>
														<td width="7%"></td>
														<td width="10%"></td>
														<td width="6%">${LIST_METER.mid_prev}</td>
														<td width="6%">${LIST_METER.value_prev}</td>
														<td width="6%">${LIST_METER.value_start}</td>
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
														<a href="../current/meterList?nowPage=${PAGEUTIL.startPage - 1 }&SeqSite=${SEQSITE}&SeqDong=${SEQ_APT_DONG}&SeqDcu=${SEQDCU}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">
															<
														</a>
													</li>			
													</c:if>

													<c:if test="${PAGEUTIL.totalCount eq 0}">
													<li class="paginate_button page-item" id="">
														<a href="../current/meterList?nowPage=1&SeqSite=${SEQSITE}&SeqDong=${SEQ_APT_DONG}&SeqDcu=${SEQDCU}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">
															1
														</a>
													</li>			
													</c:if>
													
													<c:if test="${PAGEUTIL.totalCount ne 0}">
													<!-- 	PAGE	[1][2][3] -->
													<c:forEach var="page" begin="${PAGEUTIL.startPage}" end="${PAGEUTIL.endPage}">
													<li class="paginate_button page-item" id="">
														<a href="../current/meterList?nowPage=${page}&SeqSite=${SEQSITE}&SeqDong=${SEQ_APT_DONG}&SeqDcu=${SEQDCU}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">
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
														<a href="../current/meterList?nowPage=${PAGEUTIL.endPage+1}&SeqSite=${SEQSITE}&SeqDong=${SEQ_APT_DONG}&SeqDcu=${SEQDCU}" class="page-link">
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
