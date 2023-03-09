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

<c:if test="${not empty sessionScope}">
   <script src="${pageContext.request.contextPath}/resources/manager/apt/js/appRequestLGJ.js"></script>
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
                                    <form id="searchForm" name="searchForm" method="post" action="<c:url value='../apt/appRequestLGJ'/>">

                                       <!-- 단지선택 -->
                                       <div style="float: left; padding-right: 15px;">
                                          <div style="float: left;">
                                             <select id="siteSelect" name="siteSelect" class="custom-date-sm selectpicker form-control" 
                                                data-live-search="true" style="width: 100%;">
                                                <option value="select" selected="selected">선택하세요</option>
                                                <c:forEach items="${LIST_SITE}" var="LIST_SITE">
                                                   <option value="${LIST_SITE.seq_site}" label="${LIST_SITE.site_name}"
                                                      ${LIST_SITE.seq_site==SEQSITE ? 'selected' : '' }>${LIST_SITE.site_name} (${LIST_SITE.site_code})</option>
                                                </c:forEach>
                                                   
                                             </select>

                                          </div>
                                       </div>

                                       <div style="float: left; padding-left: 15px;">
                                          <input type="hidden" id="nowPage" name="nowPage" value="${nowPage}" readonly>
                                          <input type="button" id="searchBtn" class="btn btn-outline-primary btn-sm" value="조회">
                                       </div>
                                       
                                    <c:if test="${LIST_HO ne null}">
                                          <div class="page-heading-report-div">
                                             <span class="page-heading-report-helper"></span>
                                             <input type="button" id="acceptSend" class="btn btn-outline-primary btn-sm" value="승인 / 거절 적용하기">
                                          </div>
                                       </c:if>
                                       
                                       
                                       <input type="hidden" readonly id="SeqSite" name="SeqSite" value="${SEQSITE}">
                                       <input type="hidden" readonly id="SiteName" name="SiteName" value="${SITENAME}">
                                       
                                    </form>
                                    
                                 </div>
                                 <hr style="height: 2.5px; background-color: rgba(255, 255, 255, 0.1);">
                                 <c:if test="${LIST_HO ne null}">
                                 <table class="table tablesorter " id="dataTable" style="text-align: center;">
                                    <thead>
                                       <tr>
                                          <th>동</th>
                                          <th>호</th>
                                          <th>세대대표</th>
                                          <th>휴대전화</th>
                                          <th>앱 아이디</th>
                                          <th>가족회원수</th>
                                          <th>승인/거절</th>
                                          <th>승인요청일자</th>
                                          <th>승인처리일자</th>
                                          <th>가입일자</th>
                                       </tr>
                                    </thead>

                                    <tbody class ="tbody">
                                    <c:forEach items="${LIST_HO}" var="LIST_HO" varStatus="status">
                                       <tr class="aptmentEventTr">
                                          <td>${LIST_HO.dong_name}</td>
                                          <td>${LIST_HO.ho_name}</td>
                                          <td>${LIST_HO.request_name}</td>
                                          <td>${LIST_HO.request_phone}</td>
                                          <td>${LIST_HO.member_id}</td>
                                          <td>${LIST_HO.request_state}</td>
                                          <td style="display:none;"><input type="hidden" class="request_state_name" value="${LIST_HO.request_state_name}"  ></td>
                                          <td>
                                          <c:choose> 
												<c:when test="${LIST_HO.request_state == 0}">
													미신청
													<label><input type="hidden" name="acceptState${status.index}" value=""  ></label>
	      										 	<label><input type="hidden" name="acceptState${status.index}" value="" ></label>
												</c:when> 
												<c:when test="${LIST_HO.request_state == 1}">
													<label><input type="radio" name="acceptState${status.index}" value="true"  >승인</label>
	      										 	<label><input type="radio" name="acceptState${status.index}" value="false" >거절</label>
												</c:when> 
												<c:when test="${LIST_HO.request_state == 2}">
													<label><input type="radio" name="acceptState${status.index}" value="true"  checked>승인</label>
	      										 	<label><input type="radio" name="acceptState${status.index}" value="false" > 거절</label>
												</c:when> 
												<c:when test="${LIST_HO.request_state == 3}">
													<label><input type="radio" name="acceptState${status.index}" value="true"  >승인</label>
	      										 	<label><input type="radio" name="acceptState${status.index}" value="false" checked> 거절</label>
												</c:when> 
												<c:when test="${LIST_HO.request_state == 4}">
												가입완료
													<label><input type="hidden" name="acceptState${status.index}" value=""></label>
	      										 	<label><input type="hidden" name="acceptState${status.index}" value=""></label>
												</c:when> 
											</c:choose> 
                                          </td>
                                         
                                          <td>${LIST_HO.time_requested}</td>
                                          <td>${LIST_HO.time_responsed}</td>
                                          <td>${LIST_HO.time_subscribed} <input type="hidden" name="seq_ho" value="${LIST_HO.seq_ho}"></td>
                                         
                                          
                                          
                                          <td class="seq_ho" style="display: none;">
                                           </td>
                                       </tr>
                                 	</c:forEach>
                                 	</tbody>
                              </table>
                              </c:if>
                              </div>

                           </div>
                           
                           <form name="" id="appRequestSend" method="post">
                              <input type="hidden" readonly id="acceptList" name="acceptList">
                              <input type="hidden" readonly id="rejectList" name="rejectList">
                              <input type="hidden" readonly id="SeqSite" name="SeqSite" value= "${SEQSITE}">
                           </form> 
                           
                           
                           
                           
                                 
                           
                           <c:if test="${LIST_HO ne null}">
                           <!-- 페이징 -->
                           <div class="row dataTables_wrapper dt-bootstrap4" style="padding-bottom: 20px;">
                              <div class="col-sm-12 col-md-12">
                                 <div class="dataTables_paginate paging_simple_numbers"
                                    id="dataTable_paginate">
                                    <ul class="pagination">
                                       <c:if test="${PAGEUTIL.startPage eq 1}">
                                          <li class="paginate_button page-item previous" id="dataTable_previous"><a class="page-link"> <
                                          </a></li>
                                       </c:if>

                                       <c:if test="${PAGEUTIL.startPage ne 1}">
                                          <li class="paginate_button page-item previous" id="">
                                             <a href="../apt/appRequestLGJ?nowPage=${PAGEUTIL.startPage - 1 }&SeqSite=${SEQSITE}"
                                             aria-controls="dataTable" data-dt-idx="0" tabindex="0"
                                             class="page-link"> < </a>
                                          </li>
                                       </c:if>

                                       <c:if test="${PAGEUTIL.totalCount eq 0}">
                                          <li class="paginate_button page-item" id=""><a
                                             href="../apt/appRequest?nowPage=1&SeqSite=${SEQSITE}"
                                             aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link"> 1 </a></li>
                                       </c:if>

                                       <c:if test="${PAGEUTIL.totalCount ne 0}">
                                          <!--    PAGE   [1][2][3] -->
                                          <c:forEach var="page" begin="${PAGEUTIL.startPage}" end="${PAGEUTIL.endPage}">
                                             <li class="paginate_button page-item" id=""><a
                                                href="../apt/appRequestLGJ?nowPage=${page}&SeqSite=${SEQSITE}"
                                                aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link"> ${page} </a></li>
                                          </c:forEach>
                                       </c:if>

                                       <c:if test="${PAGEUTIL.endPage eq PAGEUTIL.totalPage}">
                                          <li class="paginate_button page-item next" id="dataTable_previous"><a class="page-link"> >
                                          </a></li>
                                       </c:if>

                                       <c:if test="${PAGEUTIL.endPage ne PAGEUTIL.totalPage}">
                                          <li class="paginate_button page-item next" id="dataTable_previous">
                                             <a href="../apt/appRequestLGJ?nowPage=${PAGEUTIL.endPage+1}&SeqSite=${SEQSITE}" class="page-link"> > </a>
                                          </li>
                                       </c:if>
                                    </ul>
                                 </div>
                              </div>
                           </div>
                           </c:if>
                           <!-- 페이징 -->

                        </div>

                     </div>

                  </div>
                  <!-- 검색, text 끝-->
                  <!-- /.container-fluid -->
               </div>
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