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
	<script src="${pageContext.request.contextPath}/resources/manager/apt/js/appRequest_test.js"></script>
	
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
												<form id="searchForm" name="searchForm" method="post" action="<c:url value='../apt/appRequest_test'/>">
													<input type="hidden" readonly id="SeqSite" name="SeqSite" value="${SEQSITE}">
													<input type="hidden" id="nowPage" name="nowPage" value="${nowPage}" readonly>
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
													</div>
													
													<div class="page-heading-report-div">
														<span class="page-heading-report-helper"></span>
													</div>
													
												</form>
												
											</div>
											<c:if test="${LIST_HO ne null}">
											<hr style="height: 2.5px; background-color: rgba(255, 255, 255, 0.1);">
											
											<input type="button" id="agreeBtn" class="btn btn-sm" 
											value="승인 및 거절 적용하기" style="float: right;">
											
											<table class="table tablesorter dataTable " id="dataTable" style="text-align: center;">
													<thead>
														<tr class="aptMemTableHeaderTr" style="color: powderblue;">
															<td>동</td>
															<td>호</td>
															<td>세대대표</td>
															<td>휴대전화</td>
															<td>앱 ID</td>
															<td>가족회원수</td>
															<td style="display: none;">상태</td>
															<td>승인 / 거절</td>
<!-- 															<td>거절사유</td> -->
															<td>요청일자</td>
															<td>처리일자</td>
															<td>가입일자</td>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${LIST_HO}" var="LIST_HO" varStatus="status">
														<tr class="radioEvtTr">
															<td style="color: azure !important;">${LIST_HO.dong_name}</td>
															<td class="td_ho_name" style="color: azure !important;">${LIST_HO.ho_name}
																<input type="hidden" class="" name="ho_name" value="${LIST_HO.seq_ho}">
															</td>
															<td class="radioEvent" style="color: azure !important;">${LIST_HO.request_name}</td>
			                                                <td class="site_phone" style="color: azure !important;">${LIST_HO.request_phone}</td>
		                                                    <td style="color: azure !important;">${LIST_HO.member_id}</td>
	                                                      	<td style="color: azure !important;">${LIST_HO.count_family_member}</td>
															<td style="color: azure !important; display: none;">${LIST_HO.request_state_name}
																<input class="state" type="hidden" value="${LIST_HO.request_state}">
															</td>
															<c:choose>
																<c:when test="${LIST_HO.request_state eq '2'}">
																	<td class="radioTd" style="color: azure !important;">
																		<label class="agreeLab"><input type="radio" checked="checked" class="agree" name="agree_${status.count}" value="2"> 승인</label>
		    		 													<label class="disAgreeLab"><input type="radio" class="disAgree" name="agree_${status.count}" value="3"> 거절</label>
																	</td>
																</c:when>
																<c:when test="${LIST_HO.request_state eq '4'}">
																	<td style="color: azure !important;">가입완료</td>
																</c:when>
																<c:when test="${LIST_HO.request_state eq '0'}">
																	<td style="color: azure !important;">미신청</td>
																</c:when>
																<c:when test="${LIST_HO.request_state eq '3'}">
																	<td class="radioTd" style="color: azure !important;">
																		<label class="agreeLab"><input type="radio" class="agree" name="agree_${status.count}" value="2"> 승인</label>
		    		 													<label class="disAgreeLab"><input type="radio" checked="checked" class="disAgree" name="agree_${status.count}" value="3"> 거절</label>
																	</td>
																</c:when>
																<c:otherwise>
																	<td class="radioTd" style="color: azure !important;">
																		<label class="agreeLab"><input type="radio" class="agree" name="agree_${status.count}" value="2"> 승인</label>
		    		 													<label class="disAgreeLab"><input type="radio" class="disAgree" name="agree_${status.count}" value="3"> 거절</label>
																	</td>
																</c:otherwise>
															</c:choose>
															
<%-- 															<c:choose> --%>
<%-- 																<c:when test="${LIST_HO.request_comment ne ''}"> --%>
<%-- 																	<td class="content" style="color: azure !important;">${LIST_HO.request_comment}</td> --%>
<%-- 																</c:when> --%>
<%-- 																<c:otherwise> --%>
<!-- 																	<td> -->
<!-- 																		<input class="comment" type="text"> -->
<!-- 																	</td> -->
<%-- 																</c:otherwise> --%>
<%-- 															</c:choose> --%>
															
															<td class="time_requested" style="color: azure !important;">${LIST_HO.time_requested}</td>
															<td class="time_responsed" style="color: azure !important;">${LIST_HO.time_responsed}</td>
															<td class="time_subscribed" style="color: azure !important;">${LIST_HO.time_subscribed}</td>
														</tr>
													</c:forEach>
													</tbody>
												</table>
												</c:if>

											</div>
										</div>
										
										<!-- 페이징 -->
										<c:if test="${LIST_HO ne null}">
											<div class="row">
												<div class="col-sm-12 col-md-12" style="display: flex; justify-content : center;">
													<div class="dataTables_paginate paging_simple_numbers"
														id="dataTable_paginate">
														<ul class="pagination">
															<c:if test="${PAGEUTIL.startPage eq 1}">
																<li class="paginate_button page-item previous"
																	id="dataTable_previous"><a class="page-link"> <
																</a></li>
															</c:if>
						
															<c:if test="${PAGEUTIL.startPage ne 1}">
																<li class="paginate_button page-item previous" id="">
																	<a href="../apt/appRequest?nowPage=${PAGEUTIL.startPage - 1 }&SeqSite=${SEQSITE}"
																	aria-controls="dataTable" data-dt-idx="0" tabindex="0"
																	class="page-link"> < </a>
																</li>
															</c:if>
						
															<c:if test="${PAGEUTIL.totalCount eq 0}">
																<li class="paginate_button page-item" id="">
																	<a href="../apt/appRequest?nowPage=1&SeqSite=${SEQSITE}" aria-controls="dataTable"
																	data-dt-idx="0" tabindex="0" class="page-link"> 1 </a></li>
															</c:if>
						
															<c:if test="${PAGEUTIL.totalCount ne 0}">
																<!-- 	PAGE	[1][2][3] -->
																<c:forEach var="page" begin="${PAGEUTIL.startPage}"
																	end="${PAGEUTIL.endPage}">
																	<li class="paginate_button page-item" id=""><a
																		href="../apt/appRequest?nowPage=${page}&SeqSite=${SEQSITE}"
																		aria-controls="dataTable" data-dt-idx="0" tabindex="0"
																		class="page-link"> ${page} </a></li>
																</c:forEach>
															</c:if>
						
															<c:if test="${PAGEUTIL.endPage eq PAGEUTIL.totalPage}">
																<li class="paginate_button page-item next"
																	id="dataTable_previous"><a class="page-link"> >
																</a></li>
															</c:if>
						
															<c:if test="${PAGEUTIL.endPage ne PAGEUTIL.totalPage}">
																<li class="paginate_button page-item next"
																	id="dataTable_previous"><a href="../apt/appRequest?nowPage=${PAGEUTIL.endPage+1}&SeqSite=${SEQSITE}" class="page-link"> > </a></li>
															</c:if>
														</ul>
													</div>
												</div>
											</div>
										</c:if>
										<!-- 페이징 -->
								</div>

							</div>

						</div>
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
