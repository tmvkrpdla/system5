<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script src="${pageContext.request.contextPath}/resources/manager/vendor/chart.js/js/Chart.js"></script>
<script src="${pageContext.request.contextPath}/resources/manager/vendor/chart.js/js/Chart.color.js"></script>
<script src="${pageContext.request.contextPath}/resources/manager/each/js/aptEachMonth.js"></script>

<!--container start-->
<div class="container1 column board">
	<div class="row pd1_5_lr">
		<!--page_heading & location  -->
		<div class="page_heading">
			<div class="location">
				<button type="button" class="home"
					onclick="location.href = '<c:url value='/dashboard/energyDashboard.do'/>' ">
					<img src="<c:url value="/resources/images/common/ic_home.svg"/>"
						alt="홈">
				</button>
				<a href="<c:url value='/manage/memList.do'/>">세대별 사용량</a> <span>월별
					사용량</span>
			</div>
			<h2 class="title">월별 사용량</h2>
		</div>
	</div>

	<!-- Main Content -->
	<div class="row pd_both">
		<div id="community_wrap" class="tab_container ">
			<!-- contents -->
			<div class="tab_wrap">
				<!-- 상단 우측 영역 start -->
				<div class="top_right_wrap mt-em3">
					<div class="btn_fix">
						<button class="btn normal bgreen down" onclick="excelDown();"
							type="button" title="엑셀 다운받기">엑셀 다운받기</button>
					</div>
				</div>
				<!-- //.end 상단 우측 영역 -->

				<!-- 단지 조회, text -->
				<div class="col-xl-12">
					<div class="card shadow mb-4 common-border-color">
						<div class="card-body">
							<div id="dataTable_wrapper"
								class="dataTables_wrapper dt-bootstrap4">
								<div style="width: 100%; display: inline-block;"
									class="col-sm-6-hs">
									<div style="width: 100px; float: left;">
										<span style="display: inline-block; height: 21px;"></span>기간
										선택
									</div>
									<form id="searchForm" name="searchForm" method="post"
										action="<c:url value='../each/eachMonth.do'/>">

										<div style="width: 200px; float: left;">
											<input class="custom-date custom-date-sm" id="monthFrom"
												name="monthFrom" type="text"
												style="width: 95%; background: #182241; color: #dfdfdf;"
												value="${MONTHFROM}">
										</div>

										<div style="width: 52px; float: left; text-align: center">
											<span style="display: inline-block; height: 21px;"></span>~
										</div>
										<div style="width: 200px; float: left;">
											<input class="custom-date custom-date-sm" id="monthTo"
												name="monthTo" type="text"
												style="width: 95%; background: #182241; color: #dfdfdf;"
												value="${MONTHTO}">
										</div>

										<div
											style="width: 100%; display: inline-block; margin-top: 20px;">
											<div style="width: 100px; float: left;">
												<span style="display: inline-block; height: 21px;"></span>단지
												선택
											</div>

											<div style="width: 300px; float: left;">
												<select id="siteSelect" name="siteSelect"
													class="custom-select custom-date-sm dropdown-item"
													style="width: 95%;">
													<option value="0" selected>전체</option>
													<c:forEach items="${LIST_SITE}" var="LIST_SITE">
														<option value="${LIST_SITE.seq_site}"
															${LIST_SITE.seq_site==SEQSITE ? 'selected' : '' }>${LIST_SITE.site_name}</option>
													</c:forEach>
												</select> <input type="hidden" readonly id="SeqSite" name="SeqSite" value="${SEQSITE}">
											</div>

											<div style="width: 170px; float: left;">
												<select id="aptDongSelect" name="seq_apt_dong"
													class="custom-select custom-date-sm" style="width: 120px;">
													<c:forEach items="${LIST_DONG}" var="LIST_DONG">
														<option value="${LIST_DONG.seq_dong}"
															${LIST_DONG.seq_dong==SEQ_APT_DONG ? 'selected' : '' }>${LIST_DONG.dong_name}</option>
													</c:forEach>
												</select> <span style="padding-left: 10px;">동</span>
											</div>

											<div style="width: 180px; float: left;">
												<select id="aptHoSelect" name="seq_apt_ho"
													class="custom-select custom-date-sm" style="width: 120px;">
													<c:forEach items="${LIST_HO}" var="LIST_HO">
														<option value="${LIST_HO.seq_ho}"
															${LIST_HO.seq_ho==SEQ_APT_HO? 'selected' : ''}>${LIST_HO.ho_name}</option>
													</c:forEach>

												</select> <span style="padding-left: 10px;">호</span>
											</div>

											<div style="width: 15%; float: left;">
												<input type="button" id="searchBtn" class="btn xs blue2" value="조회">
											</div>
										</div>

									</form>
								</div>

							</div>
						</div>
					</div>
				</div>
				<!-- 검색, text 끝-->

				<!-- 차트 -->
				<div class="col-xl-12">
					<div class="card shadow mb-4">
						<div class="card-body">
							<!-- 차트 -->
							<c:if test="${sessionScope.BROWSER ne 'MSIE' }">
								<!-- IE 아닌 경우 -->
								<div style="height: 500px; width: 100%; display: inline-block;">
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
			<!-- /.container-fluid -->
		</div>
		<!-- End of Main Content -->
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
_chartData.usage_elec.borderWidth = 2;
_chartData.usage_elec.backgroundColor = _chartColors[1];
_chartData.usage_elec.borderColor = _chartColors[1];
_chartData.usage_elec.fill = false;
_chartData.usage_elec.type = 'line';
_chartData.usage_elec.order = 1;
_chartData.usage_elec.yAxisID = 'y-axis-1';
_chartData.usage_elec.data = [];


<c:forEach var="LIST_USAGE" items="${LIST_USAGE}">
	
	//_chartLabels.push("${LIST_USAGE.date}");

	<c:if test="${LIST_USAGE.kwh eq null}">
		_chartData.usage_elec.data.push(null);
	</c:if>
	
	<c:if test="${LIST_USAGE.kwh ne null}">
		_chartData.usage_elec.data.push("${LIST_USAGE.kwh}");
	</c:if>
	
	var _unitDate = ${LIST_USAGE.cptr_month};
	
	var _year = String(_unitDate).substring(0,4);
	var _month = String(_unitDate).substring(4,6);
	
	_chartLabels.push(_month.replace(/(^0+)/, "") + "월");

</c:forEach>

_myChart.push(_chartData.usage_elec);

</script>	