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
   <script src="${pageContext.request.contextPath}/resources/manager/apt/js/aptment.js"></script>
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
                                    <form id="searchForm" name="searchForm" method="post" action="<c:url value='../apt/aptment'/>">

                                       <!-- 단지선택 -->
                                       <div style="float: left; padding-right: 15px;">
                                          <div style="float: left;">
                                             <select id="siteSelect" name="seq_apt_dong" class="custom-date-sm selectpicker form-control" 
                                                data-live-search="true" style="width: 100%;">
                                                <option selected value="선택">선택</option>
                                                <c:forEach items="${LIST_SITE}" var="LIST_SITE">
                                                   <option value="${LIST_SITE.seq_site}" label="${LIST_SITE.site_name}"
                                                      ${LIST_SITE.seq_site==SEQSITE ? 'selected' : '' }>${LIST_SITE.site_name} (${LIST_SITE.site_code})</option>
                                                </c:forEach>
                                                   
                                             </select>

                                          </div>
                                       </div>
                                       
                                       <!-- 동 호 선택  -->
                                       <div style="display: inline-flex; float: left;">
                                          <select id="aptDongSelect" name="seq_apt_dong" class="custom-select custom-date-sm"
                                             style=" border-width: 2px;
                                                   border: none;
                                                   position: relative;
                                                   margin: 4px 1px;
                                                   border-radius: 0.4285rem;
                                                   cursor: pointer; 
                                                   background: #344675; 
                                                   background-color: #344675; 
                                                   transition: all 0.15s ease;
                                                   box-shadow: none;
                                                   color: #ffffff;">
                                             <option selected value="선택">선택</option>
                                             <c:forEach items="${LIST_DONG}" var="LIST_DONG">
                                                <option value="${LIST_DONG.seq_dong}"
                                                   ${LIST_DONG.seq_dong==SEQDONG ? 'selected' : '' }>
                                                   ${LIST_DONG.dong_name}</option>
                                             </c:forEach>
                                          </select> <span style="padding-left: 8px; color: #ffffffcc; padding-top: 10px;">동</span>
                                       </div>

                                       <div style="float: left; padding-left: 15px;">
                                          <input type="hidden" id="nowPage" name="nowPage" value="${nowPage}" readonly>
                                          <input type="button" id="searchBtn" class="btn btn-outline-primary btn-sm" value="조회">
                                       </div>

                                       <input type="hidden" readonly id="SeqSite" name="SeqSite" value="${SEQSITE}">
                                       <input type="hidden" readonly id="SeqDong" name="SeqDong" value="${SEQDONG}">
                                    </form>
                                    
                                    
                                    <form name="meterExcelForm" id="meterExcelForm" method="post">
                                                <input type="hidden" readonly value="${SEQSITE}" id="SeqSite" name="SeqSite">
                                             </form> 
                                    
                                 </div>
                                 
                                 <hr style="height: 2.5px; background-color: rgba(255, 255, 255, 0.1);">
                                 
                                 <c:if test="${LIST_DONG_HO ne null}">
                                    <table class="table tablesorter " id="dataTable" style="text-align: center;">
                                       <thead>
                                          <tr>
                                             <th rowspan='2' width="7%">동</th>
                                             <th rowspan='2' width="7%">호</th>
                                             <th colspan='2' width="20%">신규 계량기</th>
                                             <th colspan='2' width="20%">철거 계량기</th>
                                             <th colspan='3' width="20%">설치정보</th>
                                             <th style="display: none;" rowspan='2' width="12%"></th>
                                          </tr>
                                          <tr class="dataTable" style="text-align: center;">
                                             <th width="10%">MID</th>
                                             <th width="10%">시작지침</th>
                                             <th width="10%">MID</th>
                                             <th width="10%">최종지침</th>
                                             <th width="10%">작업자</th>
                                             <th width="10%">설치시간</th>
                                             <th width="10%">설치사진 수</th>
                                          </tr>
                                       </thead>
   
                                       <tbody>
                                       <c:forEach items="${LIST_DONG_HO}" var="LIST_DONG_HO">
                                          <tr class="aptmentEventTr">
                                             <td>${LIST_DONG_HO.dong_name}</td>
                                             <td>${LIST_DONG_HO.ho_name}</td>
                                             
                                             <c:if test="${LIST_DONG_HO.bound_to_modem eq true}">
                                                <td style="color: green !important;">${LIST_DONG_HO.mid_new}</td>
                                             </c:if>
                                             
                                             <c:if test="${LIST_DONG_HO.bound_to_modem ne true}">
                                                <td>${LIST_DONG_HO.mid_new}</td>
                                             </c:if>
                                             
                                             <td>${LIST_DONG_HO.value_start}</td>
                                             <td>${LIST_DONG_HO.mid_old}</td>
                                             <td>${LIST_DONG_HO.value_last}</td>
                                             <td>${LIST_DONG_HO.worker_name}</td>
                                             <td>${LIST_DONG_HO.time_installed}</td>
                                             <td>${LIST_DONG_HO.count_image}</td>
                                             <td class="seq_ho" style="display: none;">
      <%--                                           <form id="aptDetailInfo" class="aptDetailInfo" name="aptDetailInfo" method="post" --%>
      <%--                                              action="<c:url value='../apt/aptDetailInfo.do'/>"> --%>
      <%--                                              <input type="hidden" class="seq_ho" name='seq_ho' value="${LIST_DONG_HO.seq_ho}"> --%>
      <%--                                           </form> --%>
                                                <form name="aptDetailInfo">
                                                   <input type="hidden" class="seq_ho" name='seq_ho' value="${LIST_DONG_HO.seq_ho}">
                                                </form>
                                             </td>
                                          </tr>
                                    </c:forEach>
                                    </tbody>
                                 </table>
                              </c:if>
                              </div>

                           </div>

                           <!-- 페이징 -->
                           <c:if test="${LIST_DONG_HO ne null}">
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
                                                <a href="../apt/aptment?nowPage=${PAGEUTIL.startPage - 1 }&SeqSite=${SEQSITE}&SeqDong=${SEQDONG}"
                                                aria-controls="dataTable" data-dt-idx="0" tabindex="0"
                                                class="page-link"> < </a>
                                             </li>
                                          </c:if>
   
                                          <c:if test="${PAGEUTIL.totalCount eq 0}">
                                             <li class="paginate_button page-item" id=""><a
                                                href="../apt/aptment?nowPage=1&SeqSite=${SEQSITE}&SeqDong=${SEQDONG}"
                                                aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link"> 1 </a></li>
                                          </c:if>
   
                                          <c:if test="${PAGEUTIL.totalCount ne 0}">
                                             <!--    PAGE   [1][2][3] -->
                                             <c:forEach var="page" begin="${PAGEUTIL.startPage}" end="${PAGEUTIL.endPage}">
                                                <li class="paginate_button page-item" id=""><a
                                                   href="../apt/aptment?nowPage=${page}&SeqSite=${SEQSITE}&SeqDong=${SEQDONG}"
                                                   aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link"> ${page} </a></li>
                                             </c:forEach>
                                          </c:if>
   
                                          <c:if test="${PAGEUTIL.endPage eq PAGEUTIL.totalPage}">
                                             <li class="paginate_button page-item next" id="dataTable_previous"><a class="page-link"> >
                                             </a></li>
                                          </c:if>
   
                                          <c:if test="${PAGEUTIL.endPage ne PAGEUTIL.totalPage}">
                                             <li class="paginate_button page-item next" id="dataTable_previous">
                                             <a href="../apt/aptment?nowPage=${PAGEUTIL.endPage+1}&SeqSite=${SEQSITE}&SeqDong=${SEQDONG}"
                                                class="page-link"> > </a></li>
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