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
<script>
function reload(){
	window.location.reload() ;
}


</script>
<jsp:include page="../common/common.jsp"></jsp:include>

<c:if test="${not empty sessionScope}">
<%-- 	<script src="${pageContext.request.contextPath}/resources/manager/apt/js/aptment.js"></script> --%>
</c:if>
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
			
			<div id="midInfo" style="position: absolute; right: 20%; display: block; padding: 0.5rem 1rem; float: left; font-size: 1rem; color : rgba(255, 255, 255, 0.7); border-top:10px;">
				${ho_info.site_name}   ${ho_info.dong_name} 동 ${ho_info.ho_name}   ${ho_info.mid}
			</div>
			<!-- Topbar -->
			
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
												<form id="searchForm" name="searchForm" method="post" action="<c:url value='../apt/aptment'/>">

													<!-- 단지선택 -->
													<div style="float: left; padding-right: 15px;">
														<div style="float: left;">
															<div style="float:left; font-size: 1rem; color : rgba(255, 255, 255, 0.7);"> 불량 LP 총 개수 : ${total}</div>

														</div>
													</div>
													
													
													<div style="float:right; padding-left: 15px;">
														<input type="hidden" id="nowPage" name="nowPage" value="${nowPage}" readonly>
														<input type="button" id="searchBtn" style="float:right;"class="btn btn-outline-primary btn-sm" value="새로고침" onclick="reload()">
													</div>
													

													<input type="hidden" readonly id="SeqSite" name="SeqSite" value="${SEQSITE}">
													<input type="hidden" readonly id="SeqDong" name="SeqDong" value="${SEQDONG}">
												</form>
												
												
												<form name="meterExcelForm" id="meterExcelForm" method="post">
			                                       <input type="hidden" readonly value="${SEQSITE}" id="SeqSite" name="SeqSite">
			                                    </form> 
												
												
											</div>
											
											<hr style="height: 2.5px; background-color: rgba(255, 255, 255, 0.1);">

											<table class="table tablesorter " id="dataTable" style="text-align: center;">
												<thead>
													<tr>
														<th>LP 시간</th>
														<th>LP 값</th>
													</tr>
												</thead>
												<tbody class="listTbody">
												<c:forEach items="${list_lp}" var="list_lp">
													<tr class="aptmentEventTr" >
														<td>${list_lp.lp_time}</td>
														<td>${list_lp.lp_val}</td>
													</tr>
											</c:forEach>
											</tbody>
										</table>
										</div>

									</div>


					</div>
				</div>
				<!-- End of Main Content -->

				<!-- Footer -->
				<jsp:include page="../common/footer.jsp"></jsp:include>
				<!-- End of Footer -->

			</div>
		</div>

		<jsp:include page="../common/logout.jsp"></jsp:include>
	</c:if>


</body>

</html>
