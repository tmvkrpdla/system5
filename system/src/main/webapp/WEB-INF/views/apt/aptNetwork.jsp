<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>아파트 세대</title>




<c:if test="${not empty sessionScope}">
	<script src="${pageContext.request.contextPath}/resources/manager/apt/js/aptNetwork.js"></script>

</c:if>

</head>

<script>

let LIST_DCU = ${LIST_DCU};
let LIST_SITE3 = ${LIST_SITE3};
let LIST_SITE2 = ${LIST_SITE2};

</script>


<body id="page-top" onload="init('${action}')">
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
						<!-- contents -->
						<!-- 단지 조회, text -->
						<div class="row">
							<div class="col-xl-12">
								<div class="card shadow mb-4 common-border-color">
								
<!-- 								<a href="http://onm.infomark.co.kr/p/h/dstatus/status?pageNo=0&pageSize=10&totalCount=2372&searchWord=0068560">인포마크 링크</a><br/><br/> -->
									
									<div class="card-body">
										<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
											<div style="width: 100%; display: inline-block;" class="col-sm-6-hs">
												<form id="searchForm" name="searchForm" method="post" action="<c:url value='../apt/aptNetwork'/>">
													<!-- 단지선택 -->
													<div style="float: left; padding-right: 15px;">
														<div style="float: left;">
															<select id="siteSelect" name="siteSelect" class="custom-date-sm selectpicker form-control" 
																data-live-search="true" style="width: 100%;">
																<option value="select" selected="selected">선택</option>
																
															</select>
														</div>
													</div>

													<div style="float: left; padding-left: 15px;">
														<input type="hidden" id="nowPage" name="nowPage" value="${nowPage}" readonly>
														<input type="button" id="modBtn" class="btn btn-outline-primary btn-sm" value="수정">
														<input type="button" id="delBtn" class="btn btn-outline-primary btn-sm" value="삭제">
													</div>
													
													<div class="page-heading-report-div">
														<span class="page-heading-report-helper"></span>
														<textarea id="getLteIpInput" class="" ></textarea>
														<input type="button" id="getLteIp" class="btn btn-outline-primary btn-sm" value="아이피 조회">
														<input type="button" id="excelDown" class="btn btn-outline-primary btn-sm" value="엑셀 다운">
														<input type="button" id="addAptNetwork" class="btn btn-outline-primary btn-sm" value="단지 추가">
													</div>
													
													<input type="hidden" readonly id="SeqSite" name="SeqSite" value="${SEQSITE}">
													<input type="hidden" readonly id="SiteName" name="SiteName" value="${SITENAME}">
													
												</form>
												
											</div>
											<hr style="height: 2.5px; background-color: rgba(255, 255, 255, 0.1);">
<%-- 											<c:if test="${LIST_HO ne null}"> --%>
											<table class="table tablesorter" id="dataTable" style="text-align: center;">
												<thead>
													<tr>
														<th style="width:15%;">동</th>
														<th style="width:5%;">DCU_ID</th>
														<th style="width:5%;">SSH2 Port</th>
														<th style="width:4%;">FEP Port</th>
														<th style="width:5%;">SNMP Port</th>
														<th style="width:8%;">Local IP</th>
														<th style="width:8%;">LTE SN</th>
														<th style="width:8%;">LTE IP</th>
														<th style="width:44%;"> 비고 </th>
													</tr>
												</thead>

												<tbody class="listTbody">
											</tbody>
										</table>
<%-- 										</c:if> --%>
										

									</div>
									
									<form name="oldmeterExcelView" id="oldmeterExcelView" method="post">
										<input type="hidden" readonly value='${json}' id="json2" name="json2">
										<input type="hidden" readonly id="SiteName" name="SiteName" value="${SITENAME}">
									</form> 
										
									<form id='addAptNetworkView' method='post'>
										<input type="hidden" value='${LIST_SITE2}' id="LIST_SITE2">
									</form> 
								</div>

							</div>
							</div>
						</div>
						<form action="multi-file" method="post"  enctype="multipart/form-data">
							파일 : <input type="file" name="multiFile" multiple> <br>
							파일 설명 : <input type="text" name="fileContent"><br>
							<input type="submit" value="제출">
						</form>
						<!-- 검색, text 끝-->
					</div>
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

<jsp:include page="../common/common.jsp"></jsp:include>

</html>
