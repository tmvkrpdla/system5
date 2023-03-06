<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>


<div id="wrapper" style="text-align: center;">
	<div id="content-wrapper" class="d-flex flex-column">

		<!-- Main Content -->
		<div id="content">
			<div class="container-fluid">

					<!-- Page Heading -->
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">
							
						</h1>
					</div>
					
					<!-- contents -->
					<div class="row">
						<div class="col-lg-12 mb-12">
							<p>페이지를 보려면 로그인이 필요합니다.</p>
							<p>
								<a href="<c:url value='/'/>">로그인</a>
							</p>
						</div>
					</div>
			</div>
			
		</div>
	</div>
</div>
