let _numbersDong;
let thisIdx;

// let _saveBtnIdx = $("#saveBtnIdx").val();

$(document).ready(
		function() {
			// 동별 세대 정보 + 버튼
			function addDongInfo() {
				var _delHoEvt = document.getElementsByClassName('dongInfoBtn');

				for (var i = 0; i < _delHoEvt.length; i++) {
					_delHoEvt[0].onclick = function() {
						_numbersDong = $("#numbersDong").val();
						// console.log("_numbersDong = " + _numbersDong);

						var _aptName = $("#aptName").val();
						if (_aptName == '') {
							return alert('단지명을 입력해주시기 바랍니다');
						}

						_numbersDong = $("#numbersDong").val();
						if (_numbersDong == '') {
							return alert('동 개수를 입력해주시기 바랍니다');
						}

						var _siteCode = $("#site_code").val();
						if (_siteCode == '') {
							return alert('단지 코드를 입력해주시기 바랍니다');
						}

						var _sitePhone = $("#site_phone").val();
						if (_sitePhone == '') {
							return alert('관리실 번호를 입력해주세요.');
						}
						
						var nohipen = _sitePhone.replace(/-/g, "");
						
						// 주소-좌표 변환 객체를 생성합니다
						var geocoder = new kakao.maps.services.Geocoder();
						
						// 주소로 좌표를 검색합니다
						geocoder.addressSearch($('#addressJibun').val(), function(result, status) {
					
						    // 정상적으로 검색이 완료됐으면 
						     if (status === kakao.maps.services.Status.OK) {
						        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
						        var kakaoDx = new kakao.maps.LatLng(result[0].x);
						        var kakaoDy = new kakao.maps.LatLng(result[0].y);
						        
						        var StringCoords = String(coords);
						        console.log("result = " + JSON.stringify(result[0]));
//						        console.log("StringCoords = " + StringCoords);
						        
						        var coordsStr = StringCoords.split(',');
//						        console.log("coordsStr[0] : " + coordsStr[0]);
//						        console.log("coordsStr[1] : " + coordsStr[1]);
						        
						        var realDx = coordsStr[0].replace('(', "");
						        var realDy = coordsStr[1].replace(')', "");
						        
						        

//																				        result = {
//															"address" : {
//																"address_name" : "강원 원주시 판부면 서곡리 592",
//																"b_code" : "4213038022",
//																"h_code" : "4213038000",
//																"main_address_no" : "592",
//																"mountain_yn" : "N",
//																"region_1depth_name" : "강원",
//																"region_2depth_name" : "원주시",
//																"region_3depth_h_name" : "판부면",
//																"region_3depth_name" : "판부면 서곡리",
//																"sub_address_no" : "",
//																"x" : "127.927890591475",
//																"y" : "37.3119956359267"
//															},
//															"address_name" : "강원 원주시 판부면 서곡리 592",
//															"address_type" : "REGION_ADDR",
//															"road_address" : {
//																"address_name" : "강원 원주시 판부면 남원로 221",
//																"building_name" : "거장2차아파트",
//																"main_building_no" : "221",
//																"region_1depth_name" : "강원",
//																"region_2depth_name" : "원주시",
//																"region_3depth_name" : "판부면 서곡리",
//																"road_name" : "남원로",
//																"sub_building_no" : "",
//																"underground_yn" : "N",
//																"x" : "127.927638742103",
//																"y" : "37.3114940398175",
//																"zone_no" : "26397"
//															},
//															"x" : "127.927890591475",
//															"y" : "37.3119956359267"
//														}
						        
						        console.log('지번주소 : ' + result[0].address.address_name);
						        console.log('도로명 주소 : ' + result[0].road_address.address_name);
						        console.log('좌표2 : ' + coords);
						        console.log('우편번호 : ' + result[0].road_address.zone_no);
						        console.log('법정동코드 : ' + result[0].address.b_code);
						        console.log('행정구역코드 : ' + result[0].address.h_code);
						        
						        
						        $("#addressDoro").val(result[0].road_address.address_name);
						        $("#dx").val(realDx);
						        $("#dy").val(realDy);
						        $("#zone_no").val(result[0].road_address.zone_no);
						        $("#b_code").val(result[0].address.b_code);
						        $("#h_code").val(result[0].address.h_code);
						        
						        // 결과값으로 받은 위치를 마커로 표시합니다
//						        var marker = new kakao.maps.Marker({
//						            map: map,
//						            position: coords
//						        });
					
						        // 인포윈도우로 장소에 대한 설명을 표시합니다
//						        var infowindow = new kakao.maps.InfoWindow({
//						            content: '<div style="width:150px;text-align:center;padding:6px 0;">장소</div>'
//						        });
//						        infowindow.open(map, marker);
//					
//						        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
//						        map.setCenter(coords);
						    } 
						});  
						

						$('#DongInfoBtn').remove();

						getData(_aptName, _siteCode, nohipen);

					}
				}
			};
			addDongInfo();

			// 취소 버튼 예외처리 하기
			$("#cancleBtn").click(function() {
				location.reload();
			});

			// 등록 버튼
			$("#regBtn")
					.click(
							function() {

								// 유효성 검사
								if (_numbersDong == null || _numbersDong == 0) {
									alert("동 개수를 입력해 주세요");
									return;
								}

								let aptInfo = [];
								let aptInfoIdx = [];
								let aptName = document.getElementById('aptName').value;
								let siteCode = document.getElementById('siteCode').value;
								let sitePhone = document.getElementById('sitePhone').value;
								let dongName2 = document.getElementsByName('dongName');
								let str1 = document.getElementsByName('str1');
								
								let Address = document.getElementById('addressDoro').value;
								let AddressJibun = document.getElementById('addressJibun').value;
								let ZipCode = document.getElementById('zone_no').value;
								let dx = document.getElementById('dx').value;
								let dy = document.getElementById('dy').value;
								let RoadCode = document.getElementById('h_code').value;
								let AreaCode = document.getElementById('b_code').value;
								
								//네이버 없는 정보 널처리
								let ReadDay = 1;
								let YearBuilt = 0;
								let MonthBuilt = 0;
								let DayBuilt = 0;
								let Fax = null;
								let Email = null;
								let Area1 = 0;
								let Area2 = 0;
								let ParkingTop = 0;
								let ParkingGround = 0;
								let ParkingUnder = 0;
								let Elev = 0;
								let PassageType = 0;
								let State = 3;
								let Comment = "";
								
								for (let i = 0; i < _numbersDong; i++) {
									aptInfo.push(str1[i].value + ":" + dongName2[i].value);
								}

								aptInfo.sort(function compare(a, b) {
									let aa = a.split(":");	
									let ba = b.split(":");
									if (aa[1] > ba[1])
										return 1;
									if (aa[1] < ba[1])
										return -1;

									return 0;
								});

								// alert("aptName:" + aptName);
								// alert("siteCode:" + siteCode);
								let _res = enernet.modules.api.createSiteFull(
										aptName, siteCode, sitePhone, Address,
										AddressJibun, ZipCode, RoadCode,
										AreaCode, dx, dy, ReadDay, YearBuilt,
										MonthBuilt, DayBuilt, Fax, Email,
										Area1, Area2, ParkingTop,
										ParkingGround, ParkingUnder, Elev,
										PassageType, State, Comment);
								
								let _seq_site = _res.seq_site
								console.log("ZipCode = " + ZipCode);
								console.log("_seq_site = ", _seq_site);
								console.log("siteCode = ", siteCode);
								// alert("_res.seq_site:" + _res.seq_site);
								for (let i = 0; i < _numbersDong; i++) {
									// alert("aptInfo[i].split(':')[1]:" +
									// aptInfo[i].split(":")[1]);
									// alert("aptInfo[i].split(':')[0]:" +
									// aptInfo[i].split(":")[0]);
									let _resDong = enernet.modules.api.addDong(_res.seq_site, aptInfo[i].split(":")[1], aptInfo[i].split(":")[0]); // 동 저장
								}
								alert('등록에 성공하였습니다.');
								
								location.reload();
								//location.href = '../apt/estate';
								
							});
			

		});

function goNextDongName(i) {
	if (window.event.keyCode == 13) {
		let _dongNameForFocus = document.getElementsByName('dongName');
		_dongNameForFocus[i+1].focus();
	}
	
};

function goNextFloor(i) {
	if (window.event.keyCode == 13) {
		let _oneFloorHo = document.getElementsByName('oneFloorHo');
		_oneFloorHo[i+1].focus();
	}
	
};



function getData(_aptName, _siteCode, _sitePhone) {
	$('div.wrap-loading').removeClass('display-none');
	try {
		_numbersDong = $("#numbersDong").val();
		for (var i = 0; i < _numbersDong; i++) {

			var _tag = "<tr class='saveDongEvt modDongEvt'>";
			_tag += "<td style='vertical-align:middle'>"
					+ '동 이름 : '
					+ "<input type='text' onkeyup='goNextDongName("+ i +")' name='dongName' class='dongName form-control' style='width: 30%; display: inline;'>";
			+"</td>";
			_tag += "<td style='vertical-align:middle'>"
					+ "<form name='dongModForm' class='dongModForm' method='post' action='popDongMod' target='popDongModForm'>"
					+ '층당 세대 수 : '
					+ "<input type='text' name='oneFloorHo' class='oneFloorHo form-control' style='margin-right: 10px; width: 10%; display: inline;'>"
					// + '<br>'
					+ '층 수 : '
					+ "<input type='text' onkeyup='goNextFloor("+ i +")' name='floor' class='floor form-control' style='width: 10%; display: inline;'>"
					+ '네이버 정보 복사 : '
					+ "<input type='text' name='naverInfo' class='naverInfo form-control' style='width: 10%; display: inline;'>"
					+ "</form>" + "</td>";
			_tag += "<td class='modTd' style='vertical-align:middle'>"
					+ "<input type=button class='btn btn-outline-success btn-sm saveDongBtn' name='reply' id='saveDongBtn' value='입력'>"
					+ "<input type=button class='btn btn-outline-success btn-sm naverReplyBtn' name='naverReply' id='naverReplyBtn' value='네이버 입력'>"
					+ "<input type='radio' name='modRadio' class='modRadio' style='display:none;'>"
					+ "<input type='button' class='btn btn-outline-success btn-sm modDongBtn' name= 'modReply' style='margin-left: 5px;' value='수정'>"
					+ "<input type='text' class='btn btn-outline-success btn-sm saveBtn' name= 'saveState' style='margin-left: 5px; width: 90px;' value='입력전'>"
					+ "<input type='hidden' class='btn btn-outline-success btn-sm dongName2' name= 'dongName2' style='margin-left: 5px;'>"
					+ "<input type='text' class='btn btn-outline-success btn-sm str1' readonly='readonly' name= 'str1' style='margin-left: 5px;' value=''>"
					+ "<input type='text' class='btn btn-outline-success btn-sm str2' readonly='readonly' name= 'str2' style='margin-left: 5px;' value=''>"
					+ "<input type=hidden id='aptName' value='"+_aptName+"'>" 
					+ "<input type=hidden id='siteCode' value='"+ _siteCode+"'>"
					+ "<input type=hidden id='sitePhone' value='"+_sitePhone+"'>"
//					+ "<input type='button' class='btn btn-sm delDongBtn' name= 'delDongBtn' style='margin-left: 5px; background: crimson;' value='삭제'>";
			_tag += "</td>";
			_tag += "</tr>";

			$('#aptTable').append(_tag);

		}

		// 삭제버튼
		function delDong() {
			var _trDelDong = document.getElementsByClassName('delDongBtn');
			for (let i = 0; i < _trDelDong.length; i++) {

				_trDelDong[i].onclick = function() {
					alert("삭제");
					$(this).parents('tr.saveDongEvt').remove();
				}
			}
		};
		delDong();
		
		// 입력버튼
		function saveDong() {
			var _trSave = document.getElementsByClassName('saveDongBtn');
			for (let i = 0; i < _trSave.length; i++) {

				_trSave[i].onclick = function(e) {
					if ($(e.target).hasClass('saveDongBtn')) {

						if (document.getElementsByName('matrixHo').length != 0) {
							$(".matrixHo").remove();
						}

						if (document.getElementsByName('matrixFloor').length != 0) {
							$(".matrixFloor").remove();
						}

						if (document.getElementsByName('saveBtnIdx').length != 0) {
							$(".saveBtnIdx").remove();
						}

						thisIdx = i;
						console.log("thisIdx = ", thisIdx);

						var _seqSite = $(".seqSite").val();
						var _dongModForm = $(this).parents('tr.saveDongEvt').find('form')[0];

						var _dongName = $(this).parents('tr.saveDongEvt').find("input[name='dongName']").val();
						if (_dongName == '') {
							return alert('동 이름을 입력해주시기 바랍니다.');
						}

						var _oneFloorHo = $(_dongModForm).find("input[name='oneFloorHo']").val();
						if (_oneFloorHo == '') {
							return alert('층당 세대 수를 입력해주시기 바랍니다.');
						}

						var _floor = $(_dongModForm).find("input[name='floor']").val();
						if (_floor == '') {
							return alert('층수를 입력해주시기 바랍니다.');
						}

						var matrixHo = document.createElement("input");
						matrixHo.setAttribute("id", "matrixHo");
						matrixHo.setAttribute("class", "matrixHo");
						matrixHo.setAttribute("name", "matrixHo");
						matrixHo.setAttribute("type", "hidden");
						matrixHo.setAttribute("value", _oneFloorHo);
						document.body.appendChild(matrixHo);

						var matrixFloor = document.createElement("input");
						matrixFloor.setAttribute("id", "matrixFloor");
						matrixFloor.setAttribute("class", "matrixFloor");
						matrixFloor.setAttribute("name", "matrixFloor");
						matrixFloor.setAttribute("type", "hidden");
						matrixFloor.setAttribute("value", _floor);
						document.body.appendChild(matrixFloor);

						var saveBtnIdx = document.createElement("input");
						saveBtnIdx.setAttribute("id", "saveBtnIdx");
						saveBtnIdx.setAttribute("class", "saveBtnIdx");
						saveBtnIdx.setAttribute("name", "saveBtnIdx");
						saveBtnIdx.setAttribute("type", "hidden");
						saveBtnIdx.setAttribute("value", thisIdx);
						document.body.appendChild(saveBtnIdx);

						var tag = "<table id='example-table-1' border='1' style='display: none;'>";
						// var tag = "<table id='example-table-1' border='1'>";
						var id3 = $(".matrixFloor").val();
						var b1 = parseInt(id3);
						var id4 = $(".matrixHo").val();
						var b2 = parseInt(id4);

						for (j = 1; j <= b1; j++) {
							tag += "<tr class='delTrHoEvt'>";
							for (let i = 1; i <= b2; i++) {

								if (i < 10) {
									tag += "<td class='delTdHoEvt'>"
											// +j+"0"+i
											+ "<input style='width:50px;' type='text' name='seq_ho' class='seq_ho' value="
											+ j + "0" + i + ">" + "</td>";
								} else {
									tag += "<td class='delTdHoEvt'>"
											// +j+"0"+i
											+ "<input style='width:50px;' type='text' name='seq_ho' class='seq_ho' value="
											+ j + i + ">" + "</td>";
								}
							}
							tag += "</tr>";
						}
						tag += "</table>";
						area.innerHTML = tag;

						var tdArr = new Array(); // 배열선언
						var tr = $("#example-table-1").children();
						var td = tr.children();
						var input = td.find("input[class='seq_ho']");
						let dongName2Box = document.getElementsByName('dongName2');
						let str1Box = document.getElementsByName('str1');
						let _saveState = document.getElementsByName('saveState');
						let _saveBtnIdx = $(".saveBtnIdx").val();

						input.each(function(i) {
							tdArr.push(input.eq(i).val());
						});
						// console.log("배열에 담긴 값 : " + tdArr);

						var str1 = tdArr.join();
						// console.log("str1 = " + str1);

						dongName2Box[_saveBtnIdx].value = _dongName;
						str1Box[_saveBtnIdx].value = str1;
//						_saveState[_saveBtnIdx].value = "입력완료"
//						$(this).closest("input[name='reply']").toggle();

						// lgj
						let totalSeDae = document.getElementById('totalSedae');
						let totalSeDae2 = 0;
						for (let i = 0; i < str1Box.length; i++) {
							if (str1Box[i].value != "") {
								// alert(str1Box[i].value);
								totalSeDae2 = totalSeDae2 + str1Box[i].value.split(',').length;
								totalSeDae.value = "입력된 총 세대수 : " + totalSeDae2;
							}
						}
						
					}
				}
			} // if e.target end

		}; // saveDong end

		// 수정버튼
		function modDong() {
			var _trMod = document.getElementsByClassName('modDongEvt');

			if (_trMod.length != 0) {

				for (let i = 0; i < _trMod.length; i++) {
					_trMod[i].onclick = function(e) {

						if (document.getElementsByName('_oneFloorHo').length != 0) {
							$("._oneFloorHo").remove();
						}

						if (document.getElementsByName('modCon').length != 0) {
							$(".modCon").remove();
						}

						if (document.getElementsByName('modDongName').length != 0) {
							$(".modDongName").remove();
						}
						if (document.getElementsByName('modBtnIdx').length != 0) {
							$(".modBtnIdx").remove();
						}
						if ($(e.target).hasClass('modDongBtn')) {

							var _dongModForm = $(this).find('form')[0];
							var _dongName = $(this).find("input[name='dongName']").val();
							if (_dongName == '') {
								return alert('동 이름을 입력해주시기 바랍니다.');
							}
							var _oneFloorHo = $(_dongModForm).find("input[name='oneFloorHo']").val();
							if (_oneFloorHo == '') {
								return alert('층당 세대 수를 입력해주시기 바랍니다.');
							}
							var _modCon = $(_dongModForm).find("input[name='floor']").val();
							if (_modCon == '') {
								return alert('층수를 입력해주시기 바랍니다.');
							}

							thisIdx = i;

							let modBtnIdx = document.createElement("input");
							modBtnIdx.setAttribute("id", "modBtnIdx");
							modBtnIdx.setAttribute("class", "modBtnIdx");
							modBtnIdx.setAttribute("name", "modBtnIdx");
							modBtnIdx.setAttribute("type", "hidden");
							modBtnIdx.setAttribute("value", thisIdx);
							document.body.appendChild(modBtnIdx);

							var modinput = document.createElement("input");
							modinput.setAttribute("id", "_oneFloorHo");
							modinput.setAttribute("class", "_oneFloorHo");
							modinput.setAttribute("name", "_oneFloorHo");
							modinput.setAttribute("type", "hidden");
							modinput.setAttribute("value", _oneFloorHo);
							document.body.appendChild(modinput);

							var modConinput = document.createElement("input");
							modConinput.setAttribute("id", "modCon");
							modConinput.setAttribute("class", "modCon");
							modConinput.setAttribute("name", "modCon");
							modConinput.setAttribute("type", "hidden");
							modConinput.setAttribute("value", _modCon);
							document.body.appendChild(modConinput);

							var dongNameinput = document.createElement("input");
							dongNameinput.setAttribute("id", "modDongName");
							dongNameinput.setAttribute("class", "modDongName");
							dongNameinput.setAttribute("name", "modDongName");
							dongNameinput.setAttribute("type", "hidden");
							dongNameinput.setAttribute("value", _dongName);
							document.body.appendChild(dongNameinput);

							var _outerWidth = window.outerWidth * 0.8;
							var _outerHeight = window.outerHeight * 0.6;
							window.open(
											"popDongMod",
											'popDongModForm',
											'height='
													+ _outerHeight
													+ ', width='
													+ _outerWidth
													+ ', left=20, top=10, scrollbars=1');
							// _actModForm.target = 'popActModForm';
							_dongModForm.submit();

							// $(this).find("input[name='modReply']").toggle();
							$(this).find("input[name='modRadio']").prop("checked", true);
							// $("input:checkbox[name='NAME명']").prop("checked",
							// false);

						} // if 끝

					} // onclick = function(e) 끝
				} // for 문 끝
			} // if 끝

		}; // modAct 끝
		
		
		// 네이버입력버튼
		function naverReply() {
			var _trNaverSave = document.getElementsByClassName('naverReplyBtn');
			for (let i = 0; i < _trNaverSave.length; i++) {
				
				_trNaverSave[i].onclick = function(e) {
					
					if ($(e.target).hasClass('naverReplyBtn')) {
					
						if (document.getElementsByName('nowNaverInfo').length != 0) {
							$(".nowNaverInfo").remove();
						}
						
						if (document.getElementsByName('saveBtnIdx').length != 0) {
							$(".saveBtnIdx").remove();
						}
						
						thisIdx = i;

						var _dongModForm = $(this).parents('tr.saveDongEvt').find('form')[0];

						var _dongName = $(this).parents('tr.saveDongEvt').find("input[name='dongName']").val();
						if (_dongName == '') {
							return alert('동 이름을 입력해주시기 바랍니다.');
						}
					
						var _naverInfo = $(_dongModForm).find("input[name='naverInfo']").val();
					
						if (_naverInfo == '') {
							return alert('정보를 입력하세요.');
						}
						
						var nowNaverInfo = document.createElement("input");
						nowNaverInfo.setAttribute("id", "nowNaverInfo");
						nowNaverInfo.setAttribute("class", "nowNaverInfo");
						nowNaverInfo.setAttribute("name", "nowNaverInfo");
						nowNaverInfo.setAttribute("type", "hidden");
						nowNaverInfo.setAttribute("value", _naverInfo);
						document.body.appendChild(nowNaverInfo);
						
						var saveBtnIdx = document.createElement("input");
						saveBtnIdx.setAttribute("id", "saveBtnIdx");
						saveBtnIdx.setAttribute("class", "saveBtnIdx");
						saveBtnIdx.setAttribute("name", "saveBtnIdx");
						saveBtnIdx.setAttribute("type", "hidden");
						saveBtnIdx.setAttribute("value", thisIdx);
						document.body.appendChild(saveBtnIdx);
						
						var _nowNaverInfo = $(".nowNaverInfo").val();
						
						var obj = JSON.parse(_nowNaverInfo);
						alert("층수 : " + obj.length);
						
						var tdArr = new Array(); // 배열선언
						
						for(let i=0; i<obj.length; i++) {
							
							var _pyeongHoList = obj[i].pyeongHoList;
//							alert("_pyeongHoList = " + _pyeongHoList);
							
							for(let j=0; j<_pyeongHoList.length; j++) {
								if(obj[i].pyeongHoList[j].hoName != null) {
									tdArr.push(obj[i].pyeongHoList[j].hoName);
								}
							}
							
						}
						tdArr.sort(function compare(a, b) {
							return a - b;
						});
						
						let dongName2Box = document.getElementsByName('dongName2');
						let str1Box = document.getElementsByName('str1');
						let str2Box = document.getElementsByName('str2');
						
						let _saveState = document.getElementsByName('saveState');
						let _saveBtnIdx = $(".saveBtnIdx").val();

						var str1 = tdArr.join();

						dongName2Box[_saveBtnIdx].value = _dongName;
						str1Box[_saveBtnIdx].value = str1;
						str2Box[_saveBtnIdx].value = str1Box[_saveBtnIdx].value.split(',').length + "세대";
						_saveState[_saveBtnIdx].value = "입력완료"
//						$(this).closest("input[name='reply']").toggle();
						
						// lgj
						let totalSeDae = document.getElementById('totalSedae');
						let totalSeDae2 = 0;
						for (let i = 0; i < str1Box.length; i++) {
							if (str1Box[i].value != "") {
								totalSeDae2 = totalSeDae2 + str1Box[i].value.split(',').length;
								totalSeDae.value = "입력된 총 세대수 : " + totalSeDae2;
							}
						}
					}
				}
				
			} // if e.target end

		}; // saveDong2 end
		
		naverReply();
		modDong();
		saveDong();
		

	} catch (e) {
		console.log('error');
		console.log(e);
	} finally {
		$('.wrap-loading').addClass('display-none');
	}

} // getData 끝
