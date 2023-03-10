<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<!-- jquery 사용 -->
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- kakao -->
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
	integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx"
	crossorigin="anonymous"></script>

<script>
	// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해야 합니다.
	Kakao.init('30ab8bc333096585dd1a4d6207077085');

	// SDK 초기화 여부를 판단합니다.
	console.log(Kakao.isInitialized());
</script>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<table>
			<tr>
				<td>주소</td>
				<td><input type="text" id="address"></td>
				<td><button type="button" id="searchBtn">검색</button></td>
			</tr>
			<tr>
				<td>상세 주소</td>
				<td><input type="text" name="detailAddress2"></td>
				<td></td>
			</tr>
		</table>
		<div id="map" style="width:100%;height:350px;"></div>
   
	<!-- kakao API -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=30ab8bc333096585dd1a4d6207077085&libraries=services"></script>
	<script>
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
	
	
	$('#searchBtn').click(function(){
		// 버튼을 click했을때
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch($('#address').val(), function(result, status) {
	
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		        
// 		        console.log('그런 너를 마주칠까 ' + result[0].address.address_name + '을 못가');
		        console.log('지번주소 : ' + result[0].address.address_name);
		        console.log('도로명 주소 : ' + result[0].address_name);
		        console.log('행정구역 코드 : ' + result[0].code);
		        console.log('좌표2 : ' + coords);
		        
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
	
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">장소</div>'
		        });
		        infowindow.open(map, marker);
	
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});  
	});
	  
	</script>
</body>
</html>