<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>

	<form>
		<input type="hidden" id="mainMenu" value="${mainMenu}">
		<input type="hidden" id="subMenu" value="${subMenu}">
	</form>


<!-- Sidebar -->
<!-- <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion toggled" id="accordionSidebar"> -->
<div class="sidebar">
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion sidenav-dark" id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<%-- <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value='../main/home'/> "> --%>
	<a class="sidebar-brand d-flex align-items-center justify-content-center " href="<c:url value='../apt/estate'/> ">
<!-- 		<div class="sidebar-brand-icon rotate-n-15"> -->
<!-- 			<i class="fas fa-laugh-wink"></i> -->
<!-- 		</div> -->
		<div class="sidebar-brand-text mx-3">
			ENERNET
		</div> 
<!-- 		<img src ="../resources/images/enernet_logo.png" style="width:100px;"> -->
	</a>
	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Nav Item - Home -->
	<%-- <li class="nav-item safety-main" id=''>
		
		<a class="nav-link" href="<c:url value='../proj/list'/> "> 
			<i class="fas fa-fw fa-home"></i> 
			<span> 홈 </span>
		</a>
	</li>
	 --%>
	 
	<li class="nav-item manager-estate">
		<a class="nav-link" href="../apt/estate"> 
			<i class="fas fa-building"></i>
			<span>아파트 단지</span>
		</a>
	</li>
	
	
	<li class="nav-item manager-aptment">
		<a class="nav-link" href="../apt/aptment"> 
			<i class="fas fa-building"></i>
			<span>세대 정보 - 계량기</span>
		</a>
	</li>
	
	<li class="nav-item each-eachtime">
		<a class="nav-link" href="../each/eachTime"> 
			<i class="fas fa-building"></i>
			<span>세대별 사용량 - 시간별</span>
		</a>
	</li>
	
<!-- 	<li class="nav-item each-eachday"> -->
<!-- 		<a class="nav-link" href="../each/eachDay">  -->
<!-- 			<i class="fas fa-building"></i> -->
<!-- 			<span>세대별 사용량 - 날짜별</span> -->
<!-- 		</a> -->
<!-- 	</li> -->
	
<!-- 	<li class="nav-item manager-estate"> -->
<!-- 		<a class="nav-link" href="../each/eachMonth">  -->
<!-- 			<i class="fas fa-building"></i> -->
<!-- 			<span>세대별 사용량 - 월별</span> -->
<!-- 		</a> -->
<!-- 	</li> -->
	
<!-- 	<li class="nav-item manager-estate"> -->
<!-- 		<a class="nav-link" href="../apt/estate">  -->
<!-- 			<i class="fas fa-building"></i> -->
<!-- 			<span>세대간 비교 - 일일</span> -->
<!-- 		</a> -->
<!-- 	</li> -->
	
<!-- 	<li class="nav-item manager-estate"> -->
<!-- 		<a class="nav-link" href="../apt/estate">  -->
<!-- 			<i class="fas fa-building"></i> -->
<!-- 			<span>세대간 비교 - 월간</span> -->
<!-- 		</a> -->
<!-- 	</li> -->

	<li class="nav-item apt-siteReport">
		<a class="nav-link" href="../apt/siteReport"> 
			<i class="fas fa-building"></i>
			<span>검침현황</span>
		</a>
	</li>
	
	<li class="nav-item apt-metering">
		<a class="nav-link" href="../apt/meteringNew"> 
			<i class="fas fa-building"></i>
			<span>단지별 검침값</span>
		</a>
	</li>
	
	<li class="nav-item apt-valueOld">
		<a class="nav-link" href="../apt/valueOld"> 
			<i class="fas fa-cogs"></i>
			<span>철거계량기 지침값</span>
		</a>
	</li>
	
	<li class="nav-item apt-appRequest">
		<a class="nav-link" href="../apt/appRequest"> 
			<i class="fas fa-cogs"></i>
			<span>입주민 앱 승인</span>
		</a>
	</li>
	
	
	<!-- 입주민 앱 승인 LGJ -->
	
<!-- 	<li class="nav-item apt-appRequestLGJ"> -->
<!-- 		<a class="nav-link" href="../apt/appRequestLGJ">  -->
<!-- 			<i class="fas fa-cogs"></i> -->
<!-- 			<span>입주민 앱 승인LGJ</span> -->
<!-- 		</a> -->
<!-- 	</li> -->
	
	
	<li class="nav-item apt-badMeter">
		<a class="nav-link" href="../apt/badMeters"> 
			<i class="fas fa-cogs"></i>
			<span>불량계량기 찾기</span>
		</a>
	</li>
	<li class="nav-item apt-aptNetwork">
		<a class="nav-link" href="../apt/aptNetwork"> 
			<i class="fas fa-cogs"></i>
			<span>네트워크 정리</span>
		</a>
	</li>
	
	
	
	
	<!-- 현장 사진 조회 -->
<!-- 	<li class="nav-item manager-photoList"> -->
<!-- 		<a class="nav-link" href="../site/photoList">  -->
<!-- 			<i class="fas fa-image"></i> -->
<!-- 			<span>현장 사진 조회</span> -->
<!-- 		</a> -->
<!-- 	</li> -->

<!-- 	<li class="nav-item current-mdms"> -->
<!-- 		<a class="nav-link" href="../current/mdmsServer">  -->
<!-- 			<i class="fas fa-server"></i> -->
<!-- 			<span>MDMS 서버</span> -->
<!-- 		</a> -->
<!-- 	</li> -->
	
<!-- 	<li class="nav-item current-dcu"> -->
<!-- 		<a class="nav-link" href="../current/dcuList">  -->
<!-- 			<i class="fas fa-cogs"></i> -->
<!-- 			<span>DCU</span> -->
<!-- 		</a> -->
<!-- 	</li> -->
	
<!-- 	<li class="nav-item current-modem"> -->
<!-- 		<a class="nav-link" href="../current/modemList">  -->
<!-- 			<i class="fas fa-hdd"></i> -->
<!-- 			<span>모뎀</span> -->
<!-- 		</a> -->
<!-- 	</li> -->
	
<!-- 	<li class="nav-item current-meter"> -->
<!-- 		<a class="nav-link" href="../current/meterList">  -->
<!-- 			<i class="fas fa-tachometer-alt"></i> -->
<!-- 			<span>계량기</span> -->
<!-- 		</a> -->
<!-- 	</li> -->
	
<!-- 	<li class="nav-item company-companyList"> -->
<!-- 		<a class="nav-link" href="../company/companyList">  -->
<!-- 			<i class="fas fa-network-wired"></i> -->
<!-- 			<span>협력업체</span> -->
<!-- 		</a> -->
<!-- 	</li> -->
	
<!-- 	<li class="nav-item company-worker"> -->
<!-- 		<a class="nav-link" href="../company/worker">  -->
<!-- 			<i class="fas fa-hard-hat"></i> -->
<!-- 			<span>설치 작업자</span> -->
<!-- 		</a> -->
<!-- 	</li> -->

	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>
</ul>
</div>
<!-- End of Sidebar -->