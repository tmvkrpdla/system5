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
	<title>DCU 현황</title>

<jsp:include page="../common/common.jsp"></jsp:include>

<c:if test="${not empty sessionScope}">
	<script src="${pageContext.request.contextPath}/resources/manager/current/js/addOpenSite.js"></script>
</c:if>

</head>

<body id="page-top" oncontextmenu='return false' onselectstart='return false' ondragstart='return false'>
	<c:if test="${empty sessionScope.ADMIN}">
		<jsp:include page="../common/emptySession.jsp"></jsp:include>		
	</c:if>

	<c:if test="${not empty sessionScope.ADMIN}">
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Content Wrapper -->
		<div class="main-panel">
			
				<!-- Begin Page Content -->
				<div class="card">
					
					<!-- 검색, text -->
						<div class="row">
							<div class="col-xl-12">
								<div class="card shadow mb-4">
									<div class="card-body">
										<div style="width: 100%; display: inline-block;">
											<form id="searchForm" name="searchForm" method="post" action="<c:url value='../current/popTest'/>">
												<div style="width: 100px; float: left;">
													<span style="display: inline-block; height: 21px;"></span><h5 style="display: inline-block;">단지선택</h5>
												</div>

												<div style="width: 200px; float: left;">
													<select id="siteSelect" name="siteSelect" class="custom-select custom-date-sm dropdown-item" style="width: 95%;">
														<option value="0" selected>전체</option>
														<c:forEach items="${LIST_SITE}" var="LIST_SITE">
															<option value="${LIST_SITE.nSeqSite}">${LIST_SITE.siteName}</option>
														</c:forEach>
													</select>
												</div>

												<div style="width: 15%; float: left;">
													<input type="text" name="addSeqSite" id="SeqSite" value=""> 
													<input type="button" id="addSite" class="btn btn-outline-primary btn-sm" value="추가">
												</div>
											</form>
										</div>
										<hr>
										
										<c:if test="${LIST_OPEN_SITE ne null}">
										<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
											<div class="row">
												<div class="col-sm-12">
													<table class="table tablesorter" id="dataTable" style="text-align: center;">
														<thead>
															<tr class="aptMemTableHeaderTr" style="color: powderblue;">
																<th>SeqDcu</th>
																<th>단지</th>
												            	<th>동</th>
												                <th>DCU ID</th>
												                <th>SSH2 Port</th>
												                <th>FEP Port</th>
												                <th>SNMP Port</th>
												                <th>local IP</th>
												                <th>LTE SN</th>
												                <th>외부 IP</th>
												                <th>비고</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${LIST_OPEN_SITE}" var="dcu">
												                <tr>
												                	<td>${dcu.nSeqDcu}</td>
												                    <td>${dcu.siteName}</td>
												                    <td>${dcu.dongName}</td>
												                    <td>${dcu.dcuId}</td>
												                    <td>${dcu.nPortSsh2}</td>
												                    <td>${dcu.nFepTrapPort}</td>
												                    <td>${dcu.nSnmpTrapPort}</td>
												                    <c:choose>
												                    	<c:when test="${dcu.addressInfo ne ''}">
												                    		<td>${dcu.addressInfo}</td>
												                    	</c:when>
												                    	<c:otherwise>
												                    		<td>
												                    			<input type="text">
												                    		</td>
												                    	</c:otherwise>
												                    </c:choose>
												                    <c:choose>
												                    	<c:when test="${dcu.lteSn ne ''}">
												                    		<td>${dcu.lteSn}</td>
												                    	</c:when>
												                    	<c:otherwise>
												                    		<td>
												                    			<input type="text">
												                    		</td>
												                    	</c:otherwise>
												                    </c:choose>
												                    <td>${dcu.ip}</td>
												                    <td>${dcu.comment}</td>
												                </tr>
												            </c:forEach>
														</tbody>
													</table>
												</div>
											</div>
		
										</div>
										</c:if>
							</div>
						</div>
					</div>
				</div>
				<!-- 검색, text 끝-->

			</div>
			<!-- /.container-fluid -->

			<!-- End of Main Content -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	</c:if>	


</body>

</html>
