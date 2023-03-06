<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>가정용 스마트 전력 플랫폼</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="title" content="스마트전력플랫폼" />
<meta name="description" content="SMART-EMS" />
<meta name="keyword" content="SMART-EMS" />
<meta name="author" content="SMART- EMS" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,user-scalable=yes" />
    <link rel="stylesheet" href="<c:url value='/resources/css/basic.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/layout.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/contents.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/font.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/jquery-ui.min.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/responsive.css'/>" />
    <script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/resources/js/jquery-ui.min.js'/>"></script>
    <script src="<c:url value='/resources/js/modernizr.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.modal.min.css"/>"/>
    <script src="<c:url value="/resources/js/jquery.modal.min.js"/>"></script>
    <script src="<c:url value='/resources/js/ui_script.js'/>"></script>
    <!--table body scroll plugin  -->
    <script src="<c:url value='/resources/js/underscore-1.12.0.min.js'/>"></script>
    <script src="<c:url value='/resources/js/jquery.scrollTableBody-1.0.0.js'/>"></script>
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  <script src="${pageContext.request.contextPath}/resources/manager/current/js/dcuDetailInfo.js"></script>
  
  <script src="<c:url value='/resources/manager/common/js/enernet_api.js'/>"></script>
  <script src="<c:url value='/resources/manager/common/js/enernet_evt.js'/>"></script>
  <script src="<c:url value='/resources/manager/common/js/enernet_utils.js'/>"></script>
  <link href="<c:url value='/resources/manager/vendor/viewer/css/viewer.css'/>" rel="stylesheet">
<script src="<c:url value='/resources/manager/vendor/viewer/js/viewer.js'/>"></script>
  
</head>
<body>

<!-- Page Wrapper -->
<div id="wrapper">
	<!-- Content Wrapper -->
	<div class="main-panel">
		<!-- Main Content -->
		<div class="">
			<!-- Begin Page Content /.container-fluid-->
			<div class="">
				<!-- blank -->
				<div class="row" style='height: 30px'>
					<div class="col-xl-12"></div>
				</div>

				<!-- 동 정보 입력 시작 -->
				<div class="row">
					<div class="col-xl-12">
						<div class="card shadow mb-4">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between" style="padding-left: 15pt;">
								<h3 class="m-0 font-weight-bold text-primary" style="font-weight: 800 !important;">NID : ${DCU_INFO.dcu_id}</h3>
							</div>

							<table class="x-table RB_APT_INFOtable"
								style="width: 475px; position: relative; overflow: visible;" cellspacing="0" id="34" cellpadding="0">
								<colgroup>
									<col class="RB_APT_INFOcol" style="width: 179px;" col="1" min-col-width="135px" c-w="179">
									<col class="RB_APT_INFOcol" style="width: 227px;" col="2" min-col-width="66px" c-w="227">
								</colgroup>
								<tbody class="rows-height-counter" style="font-size: 15px;">
									<tr style="height: 13px;" tridx="0" id="r-0-0">
										<td style="display: none;" id="A1-0-34"></td>
										<td class="fh bw pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="B1-0-34"></td>
										<td class="fh bw pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C1-0-34"></td>
									</tr>
									<tr style="height: 22px;" tridx="1" id="r-1-0">
										<td style="display: none;" id="A2-0-34"></td>
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 10pt; font-family: 'Malgun Gothic';"
											id="B2-0-34">MDMS ID : ${DCU_INFO.mdms_id}</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C2-0-34"></td>
									</tr>
									<tr style="height: 45px;" tridx="2" id="r-2-0">
										<td style="display: none;" id="A3-0-34"></td>
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 10pt; font-family: 'Malgun Gothic';"
											id="B3-0-34">네트워크 정보 </td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C3-0-34"></td>
									</tr>
									<tr style="height: 22px;" tridx="9" id="r-9-0">
										<td class="fh bw f10-0 b0" style="color: rgb(255, 255, 255); padding-left: 15pt; font-family: 'Malgun Gothic';"
											id="C10-0-34">- DCU IP : ${DCU_INFO.ip_dcu}</td>
									</tr>
									<tr style="height: 22px;" tridx="10" id="r-10-0">
										<td class="fh bw f10-0 pl2 b0"
										 style="color: rgb(255, 255, 255); width:100%; padding-left: 15pt; font-family: 'Malgun Gothic';"
											id="C11-0-34">- TRAP IP : ${DCU_INFO.ip_trap}</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C2-0-34"></td>	
									</tr>
									<tr style="height: 22px;" tridx="11" id="r-11-0">
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 15pt; font-family: 'Malgun Gothic';"
											id="C12-0-34">- FEP port : ${DCU_INFO.port_fep}</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C2-0-34"></td>	
									</tr>
									
									<tr style="height: 22px;" tridx="11" id="r-11-0">
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 15pt; font-family: 'Malgun Gothic';"
											id="C12-0-34">- FEP trap port : ${DCU_INFO.port_trap_fep}</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C2-0-34"></td>	
									</tr>
									
									<tr style="height: 22px;" tridx="11" id="r-11-0">
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 15pt; font-family: 'Malgun Gothic';"
											id="C12-0-34">- SNMP trap port : ${DCU_INFO.port_trap_snmp}</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C2-0-34"></td>	
									</tr>
									
									<tr style="height: 45px;" tridx="2" id="r-2-0">
										<td style="display: none;" id="A3-0-34"></td>
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 10pt; font-family: 'Malgun Gothic';"
											id="B3-0-34">설치 정보 </td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C3-0-34"></td>
									</tr>
									<tr style="height: 22px;" tridx="9" id="r-9-0">
										<td class="fh bw f10-0 b0" style="color: rgb(255, 255, 255); padding-left: 15pt; font-family: 'Malgun Gothic';"
											id="C10-0-34">- 작업자 : ${DCU_INFO.worker_name} (${DCU_INFO.worker_id})</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C2-0-34"></td>		
									</tr>
									<tr style="height: 22px;" tridx="10" id="r-10-0">
										<td class="fh bw f10-0 pl2 b0"
										 style="color: rgb(255, 255, 255); width:100%; padding-left: 15pt; font-family: 'Malgun Gothic';"
											id="C11-0-34">- 소속사 : ${DCU_INFO.company_name}</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C2-0-34"></td>	
									</tr>
									<tr style="height: 22px;" tridx="11" id="r-11-0">
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 15pt; font-family: 'Malgun Gothic';"
											id="C12-0-34">- 설치일 : ${DCU_INFO.time_dcu_installed}</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C2-0-34"></td>	
									</tr>
									<tr style="height: 22px;" tridx="11" id="r-11-0">
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 15pt; font-family: 'Malgun Gothic';"
											id="C12-0-34">- 설치 장소 : ${DCU_INFO.dcu_location}</td>
										<td class="fh bw f10-0 pl2 b0"
											style="color: rgb(255, 255, 255); font-family: 'Malgun Gothic';"
											id="C2-0-34"></td>	
									</tr>
									<tr style="height: 22px;" tridx="11" id="r-11-0">
										<td class="fh bw f10-0 b0"
											style="color: rgb(255, 255, 255); padding-left: 15pt; font-family: 'Malgun Gothic';"
											id="C12-0-34">- 최근 통신 : ${DCU_INFO.time_last_comm}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="col-xl-12">
						<div class="card shadow mb-4">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h3 class="m-0 font-weight-bold text-primary">설치사진 목록</h3>
							</div>

							<fieldset class="user-info">
								<div class="dl_table block">
									<c:forEach items="${LIST_IMAGE}" var="LIST_IMAGE">
										<div>
											<img class="thumb-img thumb-evt" src="${LIST_IMAGE.url_dcu_thumb}" data-1="${LIST_IMAGE.url_dcu_image}">
										</div>
										<table style="margin-top: 5px; margin-bottom: 15px; font-size: 15px; font-family: 'Malgun Gothic';"">
											<tr>
												<td>${LIST_IMAGE.worker_name} (${LIST_IMAGE.worker_id})</td>
												<td style="padding-left: 15px;">${LIST_IMAGE.time_image_added}</td>
											</tr>
										</table>
									</c:forEach>

								</div>
							</fieldset>
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

</body>
</html>