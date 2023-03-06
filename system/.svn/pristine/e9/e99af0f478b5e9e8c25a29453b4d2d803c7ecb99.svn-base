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

<link rel="apple-touch-icon" sizes="76x76" href="../resources/black-dashboard-master/assets/img/apple-icon.png">

<title>Home</title>
	
	<jsp:include page="../common/common.jsp"></jsp:include>
	<c:if test="${not empty sessionScope}">
		<script src="${pageContext.request.contextPath}/resources/manager/main/js/mainHome.js"></script>
<%-- 		<script src="${pageContext.request.contextPath}/resources/vendor/chart.js/js/Chart.js"></script>	 --%>
	</c:if>

</head>

<body id="page-top" oncontextmenu='return false' onselectstart='return false' ondragstart='return false'>

	 <%-- session이 없는 경우 --%>
	<c:if test="${empty sessionScope.ADMIN}">
		<jsp:include page="../common/emptySession.jsp"></jsp:include>		
	</c:if>

	<c:if test="${not empty sessionScope.ADMIN}">
	<!-- Page Wrapper -->
		<div id="wrapper">
			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red" -->
				<div class="sidebar-wrapper">
					<jsp:include page="../common/sidebar.jsp"></jsp:include>
				</div>
			</div>
			<!-- End of Sidebar -->

			<!-- Content Wrapper -->
			<div class="main-panel">
				<!-- Topbar -->
				<jsp:include page="../common/topbar.jsp"></jsp:include>
				<!-- End of Topbar -->

				<!-- Main Content -->
				<div class="content">
					<div class="">
						<!-- Page Heading -->

						<!-- <!-- 단지, 세대, mdms dcu, 모뎀, 계량기 -->
						<div class="row">

							<!-- 단지 -->
							<div class="col-xl-3-circle col-md-6 mb-4-hs">
								<div class="card border-left-primary shadow">
									<div class="card-body-hs">

										<div class="row no-gutters align-items-center">
											<div class="col mr-2 font-weight-bold text-primary" style="text-align: center;">
												<h4 style="margin: 0; color: #c9c9c9 !important;">단지</h4>
												<div class="font-weight-bold text-black mb-1 circle"
													id="site" style="font-size: 40px; color: #c9c9c9 !important;">
													<!-- 												<input type="text" readonly id="site" value=""> -->
												</div>
											</div>
										</div>

									</div>
								</div>
							</div>

							<!-- 세대 -->
							<div class="col-xl-3-circle col-md-6 mb-4-hs">
								<div class="card border-left-primary shadow">
									<div class="card-body-hs">

										<div class="row no-gutters align-items-center">
											<div class="col mr-2 font-weight-bold text-primary" style="text-align: center;">
												<h4 style="margin: 0; color: #c9c9c9 !important;">세대</h4>
												<div class="font-weight-bold text-black mb-1 circle"
													id="count_ho" style="font-size: 30px; color: #c9c9c9 !important;"></div>
											</div>
										</div>

									</div>
								</div>
							</div>

							<!-- MDMS 서버 -->
							<div class="col-xl-3-circle col-md-6 mb-4-hs">
								<div class="card border-left-primary shadow">
									<div class="card-body-hs">

										<div class="row no-gutters align-items-center">
											<div class="col mr-2 font-weight-bold text-primary" style="text-align: center;">
												<h4 style="margin: 0; color: #c9c9c9 !important;">MDMS 서버</h4>
												<div class="font-weight-bold text-black mb-1 circle"
													id="count_mdms" style="font-size: 40px; color: #c9c9c9 !important;"></div>
											</div>
										</div>

									</div>
								</div>
							</div>

							<!-- DCU -->
							<div class="col-xl-3-circle col-md-6 mb-4-hs">
								<div class="card border-left-primary shadow">
									<div class="card-body-hs">

										<div class="row no-gutters align-items-center">
											<div class="col mr-2 font-weight-bold text-primary" style="text-align: center;">
												<h4 style="margin: 0; color: #c9c9c9 !important;">DCU</h4>
												<div class="font-weight-bold text-black mb-1 circle"
													id="count_dcu" style="font-size: 40px; color: #c9c9c9 !important;"></div>
											</div>
										</div>

									</div>
								</div>
							</div>

							<!-- 모뎀 -->
							<div class="col-xl-3-circle col-md-6 mb-4-hs">
								<div class="card border-left-primary shadow">
									<div class="card-body-hs">

										<div class="row no-gutters align-items-center">
											<div class="col mr-2 font-weight-bold text-primary" style="text-align: center;">
												<h4 style="margin: 0; color: #c9c9c9 !important;">모뎀</h4>
												<div class="font-weight-bold text-black mb-1 circle"
													id="count_modem" style="font-size: 40px; color: #c9c9c9 !important;"></div>
											</div>
										</div>

									</div>
								</div>
							</div>

							<!-- 계량기 -->
							<div class="col-xl-3-circle col-md-6 mb-4-hs">
								<div class="card border-left-primary shadow">
									<div class="card-body-hs">

										<div class="row no-gutters align-items-center">
											<div class="col mr-2 font-weight-bold text-primary" style="text-align: center;">
												<h4 style="margin: 0; color: #c9c9c9 !important;">계량기</h4>
												<div class="font-weight-bold text-black mb-1 circle" id="count_meter"
													style="font-size: 40px; color: #c9c9c9 !important;"></div>
											</div>
										</div>

									</div>
								</div>
							</div>

						</div>
						<!-- 단지, 세대, mdms dcu, 모뎀, 계량기 끝-->

						<!-- 차트 -->
						<div class="row">
							<div class="col-lg-12">
								<div class="card shadow mb-4-hs">
									<div class="card-body-hs">
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
						<!-- 차트 끝 -->

						<!-- table -->
						<div class="row">
							<div class="col-lg-12">
								<div class="card shadow mb-4-hs">
									<div class="card-body-hs">
										<div class="row">

											<!-- table 1 -->
											<div class="col-lg-6">
												<table
													class="table-hover org-table-bordered org-table-style"
													width="100%" cellspacing="0" role="grid"
													aria-describedby="" style="width: 100%;"
													id="list_usage_table1">
													<tr class="org-table-title">
														<td rowspan='2' width="10%">시간</td>
														<td rowspan='2' width="20%">사용량</td>
														<td colspan='2' width="30%">30일 평균 사용량</td>
														<td rowspan='2' width="20%">사용량 증감</td>
														<td rowspan='2' width="20%">CO<sub>2</sub> 발생
														</td>
													</tr>
													<tr class="org-table-title">
														<td>근무일</td>
														<td>휴무일</td>
													</tr>
												</table>
											</div>
											<!-- table 1 end -->

											<!-- table 2 -->
											<div class="col-lg-6">
												<table
													class="table-hover org-table-bordered org-table-style"
													width="100%" cellspacing="0" role="grid"
													aria-describedby="" style="width: 100%;"
													id="list_usage_table2">
													<tr class="org-table-title">
														<td rowspan='2' width="10%">시간</td>
														<td rowspan='2' width="20%">사용량</td>
														<td colspan='2' width="30%">30일 평균 사용량</td>
														<td rowspan='2' width="20%">사용량 증감</td>
														<td rowspan='2' width="20%">CO<sub>2</sub> 발생
														</td>
													</tr>
													<tr class="org-table-title">
														<td>근무일</td>
														<td>휴무일</td>
													</tr>
												</table>
											</div>
											<!-- table 2 end -->

										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- table 끝 -->

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
