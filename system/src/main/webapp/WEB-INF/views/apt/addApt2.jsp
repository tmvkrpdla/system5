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
	<title>아파트 단지 등록</title>
	
	<jsp:include page="../common/common.jsp"></jsp:include>
	
 	<script src="${pageContext.request.contextPath}/resources/manager/apt/js/addApt2.js"></script>

<!-- kakao -->
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
	integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx"
	crossorigin="anonymous"></script>

<script>
	// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해야 합니다.
	Kakao.init('30ab8bc333096585dd1a4d6207077085');

	// SDK 초기화 여부를 판단합니다.
// 	console.log(Kakao.isInitialized());
</script>



<style>
/*  th.thDy { */
/*      width: 70% !important; */
/*   } */
</style>

<style>
 
/*             // Fixing width of first */
/*             // column of each row */
/*             td:nth-child(1) { */
/*                 width: 40%; */
/*             } */
 
/* /*             // Fixing width of second */ */
/* /*             // column of each row */ */
/*             td:nth-child(2) { */
/*                 width: 60%; */
/*             } */
        </style>


<!-- kakao API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=30ab8bc333096585dd1a4d6207077085&libraries=services"></script>
</head>

<body>
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
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold">아파트 단지 등록</h6>
						</div> 
						<div class="card-body">
							<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
								<div class="row">
									<div class="col-sm-12">
										<table class="table tablesorter" id="aptTable" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
											<tr>
												<th style="text-align: center;" colspan="6"><h5 style="margin:0;">단지 기본 정보</h5></th>
											</tr>
											<tr>
												<th><h5 style="margin:0;">단지 명</h5></th>
												<td colspan="5">
													<input type="text" class="form-control" id="aptName" style="width: 450px;">
												</td>
											</tr>
											<tr>
												<th><h5 style="margin:0;">동 수</h5></th>
												<td colspan="5">
													<input type="text" class="form-control numbersDong" id="numbersDong" style="width: 80px;">
												</td>
											</tr>	
											<tr>
												<th><h5 style="margin:0;">단지코드</h5></th>
												<td colspan="5">
													<input type="text" class="form-control" id="site_code" style="width: 120px;">
												</td>
											</tr>
											<tr>
												<th><h5 style="margin:0;">관리실 전화</h5></th>
												<td colspan="5">
													<input type="text" class="form-control" id="site_phone" style="width: 160px;">
												</td>
											</tr> 
											<tr>
												<th style="width: 10%;"><h5 style="margin:0;">지번주소</h5></th>
												<td style="width: 30%;">
													<input type="text" class="form-control" id="addressJibun" style="width: 450px;">
												</td>
												<th style="width: 10%; padding-left: 50px;"><h5 style="margin:0;">도로명 주소</h5></th>
												<td style="width: 30%;" colspan="3">
													<input type="text" class="form-control" id="addressDoro" style="width: 450px;">
												</td>
											</tr> 
<!-- 											<tr> -->
												
<!-- 											</tr> -->
											<tr>
												<th style="width: 10%;"><h5 style="margin:0;">dx</h5></th>
												<td style="width: 30%;">
													<input type="text" class="form-control" id="dx" style="width: 160px;">
												</td>
												
												<th class="thDy" style="padding-left: 50px; width: 10%"><h5 style="margin:0;">dy</h5></th>
												<td style="width: 30%;" colspan="3">
													<input type="text" class="form-control" id="dy" style="width: 160px;">
												</td>
												
												
												
											</tr>
											<tr>
												<th style="width: 10%;"><h5 style="margin:0;">우편번호</h5></th>
												<td style="width: 20%;">
													<input type="text" class="form-control" id="zone_no" style="width: 160px;">
												</td>
												<th style="width: 10%; padding-left: 50px;"><h5 style="margin:0;">법정동코드</h5></th>
												<td style="width: 20%;">
													<input type="text" class="form-control" id="b_code" style="width: 160px;">
												</td>
												<th style="width: 10%; padding-left: 50px;"><h5 style="margin:0;">행정구역코드</h5></th>
												<td style="width: 20%;" colspan="">
													<input type="text" class="form-control" id="h_code" style="width: 160px;">
												</td>
												
											</tr>
<!-- 													<tr> -->
<!-- 														<th>완공일</th> -->
<!-- 														<td colspan="2"><input type="text" class="form-control" id="" ></td> -->
<!-- 													</tr> -->
											<tr>
												<th colspan="6" style="text-align: center;">
													<h5 style="margin:0;">동별 세대 정보 입력
														<span id='DongInfoBtn' class='btn btn-outline-primary dongInfoBtn'>
															<i class="fas fa-plus x2"></i></span></h5>
												</th> 
											</tr>
										</table>

										<div style="width: 100%;">
											<div style='padding-bottom: 5px; width: 100%; text-align: right;'>
												<input type="hidden" class="pushTitle-input" id="">
												<input type="hidden" class='pushContent-input' id="">
											
												<input type='text' name='totalSedae' class='form-control-dongInfo totalSedae' id='totalSedae'
													style="visibility: hidden; width: 250px; " value=''>
												<input type="button" value='등록' class='btn btn-outline-success btn-sm' id='regBtn'>
												<input type="button" value='취소' class='btn btn-outline-success btn-sm' id='cancleBtn'>
											</div>
										</div>

										<div style="display: none;" id="area"></div>
										
									</div>
								</div>

							</div>
						</div>
						<!-- end of card-body -->
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

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
