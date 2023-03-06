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
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>아파트 단지</title>

<jsp:include page="../common/common.jsp"></jsp:include>

<c:if test="${not empty sessionScope}">
		
</c:if>

<script src="${pageContext.request.contextPath}/resources/vendor/chart.js/js/Chart.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/chart.js/js/Chart.color.js"></script>
<script src="${pageContext.request.contextPath}/resources/manager/each/js/aptEachTime.js"></script>

</head>

<body id="page-top" oncontextmenu='return false' onselectstart='return false' ondragstart='return false'>

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
				<div class="">
				<!-- contents -->
				<div class="card shadow mb-4 common-border-color">
					<div class="card-body">
						<!-- contents -->
						<div class="tab_wrap">

							<!-- contents -->
							<!-- 단지 조회, text -->
							<div class="col-xl-12">
										<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
											<div style="width: 100%; display: inline-block;" class="col-sm-6-hs">

												<div style="width: 100px; float: left; color: white;">
													<span style="display: inline-block; height: 21px;"></span>날짜 선택
												</div>

												<form id="searchForm" name="searchForm" method="post" action="<c:url value='../each/eachTime'/>">
													<div style="width: 200px; float: left;">
														<input class="custom-date custom-date-sm" id="dateTarget" name="dateTarget" type="text"
														style="background: #182241; color: #dfdfdf; border: 1px solid #485998;" value="${DATETARGET}">
													</div>
											</div>
											
											<div style="width: 100%; display: inline-block; color: white;">

												<div style="float: left; padding-right: 15px;">
													<select id="siteSelect" name="siteSelect" class="custom-date-sm selectpicker form-control" 
																data-live-search="true" style="width: 100%;">
														<c:forEach items="${LIST_SITE}" var="LIST_SITE">
															<option value="${LIST_SITE.seq_site}"
																${LIST_SITE.seq_site==SEQSITE ? 'selected' : '' }>${LIST_SITE.site_name}</option>
														</c:forEach>
													</select> <input type="hidden" readonly id="SeqSite" name="SeqSite"
														value="${SEQSITE}">
												</div>


												<!-- 동 호 선택  -->
												<div style="width: 130px; float: left;">
													<select id="aptDongSelect" name="seq_apt_dong" class="custom-select custom-date-sm"
													style="border-width: 2px;
																	border: none;
																	position: relative;
																	margin: 4px 1px;
																	border-radius: 0.4285rem;
																	cursor: pointer; 
																	background: #344675; 
																	background-color: #344675; 
																	transition: all 0.15s ease;
																	box-shadow: none;
																	color: #ffffff;
																	width: 80px;">
														<c:forEach items="${LIST_DONG}" var="LIST_DONG">
															<option value="${LIST_DONG.seq_dong}"
																${LIST_DONG.seq_dong==SEQ_APT_DONG ? 'selected' : '' }>
																${LIST_DONG.dong_name}</option>
														</c:forEach>
													</select> <span style="padding-left: 1px; padding-top: 10px;">동</span>
												</div>

												<div style="width: 120px; float: left;">
													<select id="aptHoSelect" name="seq_apt_ho" class="custom-select custom-date-sm"
														style="border-width: 2px;
																	border: none;
																	position: relative;
																	margin: 4px 1px;
																	border-radius: 0.4285rem;
																	cursor: pointer; 
																	background: #344675; 
																	background-color: #344675; 
																	transition: all 0.15s ease;
																	box-shadow: none;
																	color: #ffffff;
																	width: 80px;">
														<c:forEach items="${LIST_HO}" var="LIST_HO">
															<option value="${LIST_HO.seq_ho}"
																${LIST_HO.seq_ho==SEQ_APT_HO? 'selected' : ''}>${LIST_HO.ho_name}</option>
														</c:forEach>
													</select> <span style="padding-left: 1px; padding-top: 10px;">호</span>
												</div>

												<div style="width: 15%; float: left;">
													<input type="button" id="searchBtn" class="btn btn-outline-primary btn-sm" value="조회">
												</div>
												</form>
											</div>

											<div class="col-xl-12">
												<div class="card shadow mb-4">
													<div class="card-body">
														<!-- 차트 -->
														<c:if test="${sessionScope.BROWSER ne 'MSIE' }">
															<!-- IE 아닌 경우 -->
															<div
																style="height: 500px; width: 100%; display: inline-block;">
																<canvas id="myChart"
																	style="height: 500px; width: 100%; display: unset;"></canvas>
															</div>
														</c:if>

														<c:if test="${sessionScope.BROWSER eq 'MSIE' }">
															<!-- IE 인 경우 -->
															<div style="height: 400px;">
																<canvas id="myChart" style=""></canvas>
															</div>
														</c:if>

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
				</div>
				<!-- End of Content Wrapper -->

			</div>

			<jsp:include page="../common/footer.jsp"></jsp:include>

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<script type="text/javascript">

var _chartLabels = [];

var _chartData = {};

//전기
var _myChart = [];
_chartData.usage_elec = {};
_chartData.usage_elec.label = '전기 사용량';
// _chartData.usage_elec.label.fontColor = _chartColors[1];
_chartData.usage_elec.borderWidth = 2;
_chartData.usage_elec.backgroundColor = _chartColors[1];
_chartData.usage_elec.borderColor = _chartColors[1];
_chartData.usage_elec.fill = false;
_chartData.usage_elec.type = 'line';
_chartData.usage_elec.order = 1;
_chartData.usage_elec.yAxisID = 'y-axis-1';
_chartData.usage_elec.data = [];

<c:forEach var="LIST_USAGE" items="${LIST_USAGE}">
_chartLabels.push("${LIST_USAGE.unit}" + '시');

	<c:if test="${LIST_USAGE.kwh_curr eq null}">
	_chartData.usage_elec.data.push(null);
	</c:if>

	<c:if test="${LIST_USAGE.kwh_curr ne null}">
	_chartData.usage_elec.data.push("${LIST_USAGE.kwh_curr}");
	</c:if>

</c:forEach>

_myChart.push(_chartData.usage_elec);
</script>


</body>

</html>