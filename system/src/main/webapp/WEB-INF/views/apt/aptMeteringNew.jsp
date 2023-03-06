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
<jsp:include page="../common/common.jsp"></jsp:include>
<c:if test="${not empty sessionScope}">
	<script src="${pageContext.request.contextPath}/resources/manager/apt/js/aptMeteringNew.js"></script>
	
	<style type="text/css">
	.modal_wrap{
        display: none;
        width: 500px;
        height: 500px;
        position: absolute;
        top:500px;
        left: 50%;
        margin: -250px 0 0 -250px;
        z-index: 2;
    }
    .black_bg{
        display: none;
        position: absolute;
        content: "";
        
        width: 100%;
        height: 100%;
        background-color:rgba(0, 0,0, 0.5);
        top:0;
        left: 0;
        z-index: 1;
    }
    .modal_close{
        width: 26px;
        height: 26px;
        position: absolute;
        top: -30px;
        right: 0;
    }
    .modal_close> a{
        display: block;
        width: 100%;
        height: 100%;
        background:url(https://img.icons8.com/metro/26/000000/close-window.png);
        text-indent: -9999px;
    }
	
/* 	loading icon */
	
 	.loader { 
    position: absolute;
    left: 50%;
    top: 30%;
    z-index: 1;
    width: 150px;
    height: 150px;
    margin: -75px 0 0 -75px;
    border: 30px solid #f3f3f3;
    border-radius: 50%;
    border-top: 30px solid #485998;
    width: 200px;
    height: 200px;
    -webkit-animation: spin 2s linear infinite;
    animation: spin 2s linear infinite;
 } 


@-webkit-keyframes spin {
    0% { -webkit-transform: rotate(0deg); }
    100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}

/*  loading phrase */
 	.loader2 { 
    position: absolute;
    left: -5%;
    top: 80%;
    z-index: 1;
    width: 700px;
    height: 120px;
    font-size: 45px;
    font-weight: 600;
    color: lightsteelblue;
    text-align: center;
 } 

</style>
	
</c:if>
</head>
<body id="page-top">
	<c:if test="${empty sessionScope.ADMIN}">
		<jsp:include page="../common/emptySession.jsp"></jsp:include>
	</c:if>

	<c:if test="${not empty sessionScope.ADMIN}">
		<!-- Page Wrapper -->
		<div id="wrapper">
		
			<div class="black_bg"></div>
					<div class="modal_wrap">
						<div>
							<div class="loader"></div>
							<div class="loader2">
								<p style="font-size: 45px; font-weight: 600; color: lightsteelblue; text-align: center;">데이터를 불러오는 중입니다.</p>
								<p style="font-size: 45px; font-weight: 600;  color: lightsteelblue; text-align: center;">잠시만 기다려주세요.</p>
							</div>
						</div>
			   		</div>

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

									<div class="card-body">
										<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
											<div style="width: 100%; display: inline-block;" class="col-sm-6-hs">
												<form id="searchForm" name="searchForm" method="post" action="<c:url value='../apt/meteringNew'/>">

													<!-- 단지선택 -->
													<div style="float: left; padding-right: 15px;">
														<div style="float: left;">
															<select id="siteSelect" name="siteSelect" class="custom-date-sm selectpicker form-control" 
																data-live-search="true" style="width: 100%;">
																<option value="select" selected="selected">선택하세요</option>
																<c:forEach items="${LIST_SITE}" var="LIST_SITE">
																	<option value="${LIST_SITE.seq_site}" label="${LIST_SITE.site_name}"
																		${LIST_SITE.seq_site==SEQSITE ? 'selected' : '' }>${LIST_SITE.site_name} (${LIST_SITE.site_code})</option>
																</c:forEach>
																	
															</select>

														</div>
													</div>

													<div style="float: left; padding-left: 15px;">
														<input type="button" id="searchBtn" class="btn btn-outline-primary btn-sm" value="조회">
														<input type="button" id="specialCancle" class="btn-info btn-sm" value="특정시간 검침값 조회 ➞"
															style="margin-left: 50px;" onclick="location.href='../apt/metering'">
													</div>
													
													<div class="page-heading-report-div">
														<span class="page-heading-report-helper"></span>
														<c:if test="${LIST_FAP ne null}">
															<input type="button" id="excelDown" class="btn btn-outline-primary btn-sm" value="엑셀로 저장">
														</c:if>	
													</div>
													<input type="hidden" readonly id="SeqSite" name="SeqSite" value="${SEQSITE}">
													<input type="hidden" readonly id="SiteName" name="SiteName" value="${SITENAME}">
													
												</form>
												
												<form name="meterExcelForm" id="meterExcelForm" method="post">
													<input type="hidden" readonly value="${SEQSITE}" id="SeqSite" name="SeqSite">
													<input type="hidden" readonly id="SiteName" name="SiteName" value="${SITENAME}">
			                                       	<input type="hidden" readonly value='${json}' id="json2" name="json2">
												</form>	
											</div>
											<c:if test="${LIST_FAP ne null}">
											<hr style="height: 2.5px; background-color: rgba(255, 255, 255, 0.1);">
											<!-- 검침세대 / 전체세대 -->
											<div style="color: azure !important; margin-left: 10px; margin-bottom: 10px;">
												미검침세대: ${EMPTY} / 전체세대: ${TOTAL}
											</div>
											
											<table class="table tablesorter " id="dataTable"
													style="text-align: center;">
													<thead>
														<tr class="aptMemTableHeaderTr" style="color: powderblue;">
															<td>동</td>
															<td>호</td>
															<td>계량기 ID</td>
															<td>검침값</td>
															<td>검침시간</td>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${LIST_FAP}" var="LIST_FAP">
															<tr>
																<td style="color: azure !important;">${LIST_FAP.dong_name}</td>
																<td style="color: azure !important;">${LIST_FAP.ho_name}</td>
																<td style="color: azure !important;">${LIST_FAP.meter_id}</td>
																<c:choose>
																	<c:when test="${LIST_FAP.fap ne null}">
				                                                   	<td style="color: azure !important;">
				                                                   		<fmt:formatNumber value="${LIST_FAP.fap}" pattern="#,###"/>
				                                                    </td>
				                                                   </c:when>
				                                                   <c:otherwise>
				                                                      <td style="color: azure !important;">-</td>
				                                                   </c:otherwise>
                                            			  	  </c:choose>
                                            			  	  
                                            			  	  <c:choose>
                                            			  	  	<c:when test="${LIST_FAP.time_lp ne null}">
			                                                      <td style="color: azure !important;">${LIST_FAP.time_lp}</td>
			                                                   </c:when>
			                                                   <c:otherwise>
			                                                      <td style="color: azure !important;">-</td>
			                                                   </c:otherwise>
			                                                </c:choose>
																
															</tr>
														</c:forEach>
													</tbody>
												</table>
												</c:if>

										</div>

									</div>

								</div>

							</div>

						</div>
						<!-- 검색, text 끝-->
						<!-- /.container-fluid -->
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

</html>
