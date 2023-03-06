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
<script>
function reload(){
	window.location.reload() ;
}
function popLP(Mid){
	let _outerWidth = window.outerWidth * 0.3;
	let _outerHeight = window.outerHeight * 0.6;
	let popupX = (document.body.offsetWidth / 2) ;
	let popupY= (window.screen.height / 2)  -550;
	let form = document.createElement('form');
	let formInput = document.createElement('input');
	formInput.setAttribute('name', "Mid");
	formInput.value = Mid;
	
	window.open(
			"",
			'getLpListByMid',
			'height='
					+ _outerHeight
					+ ', width='
					+ _outerWidth
					+ ', left='+popupX+', top='+ popupY +', scrollbars=1'
	);
	form.action = "getLpListByMid";
	form.target = 'getLpListByMid';
	form.appendChild(formInput);
	document.body.appendChild(form);
	form.submit();
	
}


$(function() {

$("#excelDown").on("click", function() {
	$("#excelDownBadMeter").attr("action", "../apt/excelDownBadMeter").submit();
});

});



</script>


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
											 <div style="position: relative; left: 30px; top: 40px; font-size: 1rem; color : rgba(255, 255, 255, 0.7);"> 불량 계량기 총 개수 : ${total}</div>
												<form id="searchForm" name="searchForm" method="post" action="<c:url value='../apt/aptment'/>">
													
													<div style="float: right; padding-left: 15px;">
														<input type="button" class="btn btn-outline-primary btn-sm" value="새로고침" onclick="reload()">
														<input type="button" id="excelDown" class="btn btn-outline-primary btn-sm" value="엑셀 다운">
													</div>
													
												</form>
												
												<form id="excelDownBadMeter" name="excelDownBadMeter" method="post" >
			                                       <input type="hidden"  value='${json}' name="json2">
			                                    </form> 
												
											</div>
											
											<hr style="height: 2.5px; background-color: rgba(255, 255, 255, 0.1);">

											<table class="table tablesorter " id="dataTable" style="text-align: center;">
												<thead>
													<tr>
														<th>단지</th>
														<th>동</th>
														<th>호</th>
														<th>MID</th>
														<th>이상발생횟수</th>
													</tr>
												</thead>
												<tbody class="listTbody">
												<c:forEach items="${list_bad_meter}" var="list_bad_meter">
													<tr class="aptmentEventTr" onclick="popLP(${list_bad_meter.Mid})">
														<td>${list_bad_meter.site_name}</td>
														<td>${list_bad_meter.dong_name}</td>
														<td>${list_bad_meter.ho_name}</td>
														<td>${list_bad_meter.Mid}</td>
														<td>${list_bad_meter.count_minus}<input type="hidden"  name="seqSite" value="${list_bad_meter.seq_site}"></td>
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
