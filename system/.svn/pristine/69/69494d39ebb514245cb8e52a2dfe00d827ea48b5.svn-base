/*!
 * Start Bootstrap - SB Admin 2 v4.0.7 (https://startbootstrap.com/template-overviews/sb-admin-2)
 * Copyright 2013-2019 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-sb-admin-2/blob/master/LICENSE)
 */

/*!
 * Bootstrap v4.3.1 (https://getbootstrap.com/)
 * Copyright 2011-2019 The Bootstrap Authors
 * Copyright 2011-2019 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 */
$(function(){

	  // Toggle the side navigation
	$("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
		$("body").toggleClass("sidebar-toggled");
		$(".sidebar").toggleClass("toggled");
		if ($(".sidebar").hasClass("toggled")) {
			$('.sidebar .collapse').collapse('hide');
		}
		;
	});
	
	

	// Close any open menu accordions when window is resized below 768px
	$(window).resize(function() {
		if ($(window).width() < 768) {
			$('.sidebar .collapse').collapse('hide');
		}
		;
	});

	// Prevent the content wrapper from scrolling when the fixed side navigation hovered over
	$('body.fixed-nav .sidebar').on(
			'mousewheel DOMMouseScroll wheel',
			function(e) {
				if ($(window).width() > 768) {
					var e0 = e.originalEvent, delta = e0.wheelDelta
							|| -e0.detail;
					this.scrollTop += (delta < 0 ? 1 : -1) * 30;
					e.preventDefault();
				}
			});

	// Scroll to top button appear
	$(document).on('scroll', function() {
		var scrollDistance = $(this).scrollTop();
		if (scrollDistance > 100) {
			$('.scroll-to-top').fadeIn();
		} else {
			$('.scroll-to-top').fadeOut();
		}
	});

	// Smooth scrolling using jQuery easing
	$(document).on('click', 'a.scroll-to-top', function(e) {
		var $anchor = $(this);
		$('html, body').stop().animate({
			scrollTop : ($($anchor.attr('href')).offset().top)
		}, 1000, 'easeInOutExpo');
		e.preventDefault();
	});
	
	
	/*	메	뉴	*/
	var _mainMenu = $("#mainMenu").val();
	var _subMenu = $("#subMenu").val();
	
	//console.log(_mainMenu);
	//console.log(_subMenu)
	
	if ($(window).width() > 768) {
		$("li."+_mainMenu +"-"+ _subMenu).addClass('active');	//	main active
		
		//$("#sub-"+ _subMenu).addClass('active');		//	sub active
		
		//$("#main-"+ _mainMenu).addClass('show');		//	main show
		
	}else{
		$("li."+_mainMenu +"-"+ _subMenu).addClass('active');	//	main active
		
		//$("#sub-"+ _subMenu).addClass('active');		//	sub active
		
		//$("#main-"+ _mainMenu).addClass('hide');		//	main hide
	}
	
	
	/*	로 그 아 웃	*/
	$(".logoutBtn").on('click', function(){
	 	$("#logoutModal").modal('show');
	})
	
	/*	로 그 인	*/
	$("#loginBtn").on('click', function(){
		if(enernet.modules.utils.isNullById("userId")){
			
			$("#loginModalLabel").text("아이디를 입력해주시기 바랍니다");
			$("#loginModal").modal('show');
			return $("#userId").focus();
		}
		
		if(enernet.modules.utils.isNullById("userPwd")){
			//alert('비밀번호를 입력해주시기 바랍니다');
			$("#loginModalLabel").text("비밀번호를 입력해주시기 바랍니다");
			$("#loginModal").modal('show');
			return $("#userPwd").focus();
		}
		
		$("#loginForm").submit();
		
	});
	
	$("#userId").on('keyup', function(e){
		if(e.keyCode == 13){
			if(enernet.modules.utils.isNullById("userId")){
				
				$("#loginModalLabel").text("아이디를 입력해주시기 바랍니다");
				$("#loginModal").modal('show');
				return $("#userId").focus();
			}else{
				$("#userPwd").focus();
			}
		}
	});
	
	$("#userPwd").on('keyup', function(e){
		if(e.keyCode == 13){
			if(enernet.modules.utils.isNullById("userPwd")){
				//alert('비밀번호를 입력해주시기 바랍니다');
				$("#loginModalLabel").text("비밀번호를 입력해주시기 바랍니다");
				$("#loginModal").modal('show');
				return $("#userPwd").focus();
			}else{
				$("#loginBtn").click();
			}
		}
	});
	
	if($("#ISLOGIN").val() != ""){
		$("#loginModalLabel").html("로그인에 실패하였습니다. <br> 아이디와 비밀번호를 확인해주시기 바랍니다.");
		$("#loginModal").modal('show');
	}
	
	if(document.getElementById('time-span') != undefined){
		//	타임	설정
		
		////	최초 실행
		getTimeStampHHmm();
		
		setInterval(getTimeStampHHmm,6000);
	}

	function getTimeStampHHmm(){
		$("#time-span").text(enernet.modules.utils.getTimeStampHHmm()); 
	}
	
});




