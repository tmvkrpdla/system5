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
	<title>아파트 단지 등록</title>
	
	<jsp:include page="../common/common.jsp"></jsp:include>
	
 	<script src="${pageContext.request.contextPath}/resources/manager/apt/js/addApt.js"></script> 
	
	<script type="text/javascript">
	</script>
	
	<script>
	/*
    function main(a1, a2) {
      var tag = "<table border='1'>";
      var b1 = parseInt(a1);
     // console.log("b1 = " + b1);
      var b2 = parseInt(a2);
     // console.log("b2 = " + b2);
      for (j = 1; j <= b1; j++) {
        tag += "<tr>";
        for (i = 1; i <= b2; i++) {
          tag += "<td>"
          	+j+"0"+i
          	+ "<input type='button' value='X'>" 
          	+ "</td>";
        }
        tag += "</tr>";
      }
      tag += "</table>";
      area.innerHTML = tag;
    }
	*/
    
  </script>
	
	
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
								<div class="">
									<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">

										<div class="row">
											<div class="col-sm-12">
												<div style="width: 100%;"></div>

												<table class="table tablesorter" id="aptTable" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">

													<tr>
														<th style="text-align: center;" colspan="3"><h5 style="margin:0;">단지 기본 정보</h5></th>
													</tr>
													<tr>
														<th><h5 style="margin:0;">단지 명</h5></th>
														<td colspan="2"><input type="text" class="form-control" id="aptName"></td>
													</tr>
<!-- 													<tr> -->
<!-- 														<th>시작 동 </th> -->
<!-- 														<td><input type="text" class="" id="starDongNumber"></td> -->
<!-- 													</tr>	 -->
													<tr>
														<th><h5 style="margin:0;">동 개수</h5></th>
														<td colspan="2"><input type="text" class="form-control numbersDong" id="numbersDong" style="width: 10%;"></td>
													</tr>	
													<tr>
														<th><h5 style="margin:0;">단지코드</h5></th>
														<td colspan="2"><input type="text" class="form-control" id="site_code"></td>
													</tr>
													<tr>
														<th><h5 style="margin:0;">관리실 전화</h5></th>
														<td colspan="2"><input type="text" class="form-control" id="site_phone"></td>
 													</tr> 
<!-- 													<tr> -->
<!-- 														<th>완공일</th> -->
<!-- 														<td colspan="2"><input type="text" class="form-control" id="" ></td> -->
<!-- 													</tr> -->
													<tr>
														<th colspan="3" style="text-align: center;" colspan="2"><h5 style="margin:0;">동별 세대 정보 입력
															<span id='DongInfoBtn' class='btn btn-outline-primary dongInfoBtn'>
																<i class="fas fa-plus x2"></i></span></h5>
														</th> 
													</tr>
												</table>

												<div style="width: 100%;">
													<div style='padding-bottom: 5px; width: 100%; text-align: right;'>
														<input type="hidden" class="pushTitle-input" id="">
														<input type="hidden" class='pushContent-input' id="">
													
														<input type='text' name='totalSedae' class='seqSite form-control-dongInfo totalSedae' id='totalSedae' value=''>
														<input type="button" value='등록' class='btn btn-outline-success btn-sm' id='regBtn'>
														<input type="button" value='취소' class='btn btn-outline-success btn-sm' id='cancleBtn'>
														<input type='hidden' name='seqSite' class='seqSite form-control-dongInfo' id='seqSite' style='width: 10%;' value=''>
													</div>
												</div>
												
<!-- 												행(row) : <input type="number" id="txt1"><br/> -->
<!--  												열(col) : <input type="number" id="txt2"> -->
<!--  												<button onclick="main(txt1.value, txt2.value)">enter</button> -->
<!--  												<hr/> -->
<!--  												<div id="area"></div> -->

											<div id="area"></div>

											</div>
										</div>
										
										<input type="hidden" name="siteName" class="siteName" value="${SITENAME}">
										
<!-- 										<input type="text" name="test" class="test" value=""> -->
										
										
										

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
