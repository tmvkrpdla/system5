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
<title>검침현황</title>
<jsp:include page="../common/common.jsp"></jsp:include>


<style>
.table tr.tbody td {
	color: white !important;
}
</style>


</head>

<body id="page-top">
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
					<div class="">
						<!-- contents -->
						<div class="card shadow mb-4 common-border-color">
							<div class="card-body">
								<div class="table-responsive" style="overflow: hidden;">
									<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
										<div class="row">
											<div class="col-sm-12">
												<span style="color: antiquewhite; margin-left: 3px;" class="totalPer">
													전체 검침율 : <fmt:formatNumber value="${avg}" pattern="0.00"/>%
												</span>
												<span style="color: antiquewhite; margin-left: 20px;" class="totalPer">
													전체 검침세대 : <fmt:formatNumber value="${total2}"/> 세대
												</span>
												<table class="table tablesorter " id="dataTable" style="text-align: center; margin-top: 15px;">
													<thead>
														<tr class="aptMemTableHeaderTr" style="color: powderblue;">
															<td>단지명</td>
															<td>단지코드</td>
															<td>동</td>
															<td>세대</td>
															<td>DCU</td>
															<td>계량기</td>
															<td>검침세대</td>
															<td>가동률(%)</td>
															<td>검침율(%)</td>
														</tr>
													</thead>

													<tbody>
														<c:forEach items="${LIST_SITE}" var="LIST_SITE">
															<tr class="tbody">
																<td>${LIST_SITE.site_name}</td>
																<td>${LIST_SITE.site_code}</td>
																<td>${LIST_SITE.count_dong}</td>
																<td>${LIST_SITE.count_ho}</td>
																<td>${LIST_SITE.count_dcu}</td>
																<td>${LIST_SITE.count_meter}</td>
																<td>${LIST_SITE.count_lp}</td>
																<c:choose>
																	<c:when test="${LIST_SITE.percent_comm ne '0'}">
																		<td>
																			<fmt:formatNumber value="${LIST_SITE.percent_comm}" pattern=".00"/>
																		</td>
																	</c:when>
																	
																	<c:otherwise>
																		<td>0</td>
																	</c:otherwise>
																</c:choose>
																
																<c:choose>
																	<c:when test="${LIST_SITE.percent_lp ne '0'}">
																		<td><fmt:formatNumber value="${LIST_SITE.percent_lp}" pattern=".00"/></td>
																	</c:when>
																	
																	<c:otherwise>
																		<td>0</td>
																	</c:otherwise>
																</c:choose>
															</tr>
														</c:forEach>
													</tbody>
												</table>

											</div>
										</div>

									</div>
								</div>
							</div>
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
	</c:if>

</body>

</html>
