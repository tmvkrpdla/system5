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
								for (let i = 0; i < _numbersDong; i++) {aptInfo.push(str1[i].value + ":" + dongName2[i].value);
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
								let _res = enernet.modules.api.createSite(aptName, siteCode, sitePhone);
								let _seq_site = _res.seq_site
								console.log("_seq_site = ", _seq_site);
								
//								$.ajax({
//									type : "POST",
//									url : "./insertDx",
//									data: {
//										_seq_site : _seq_site
//									},
//									success : function(data){
//										
//									    },
//									    error : function(){
//									        alert("에러")		
//									      }
//								});
								
								
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
								
//								let aptNameArray = [];
//								let aptNameForIndex = document.getElementsByClassName("siteName");
								//aptNameArray = aptNameForIndex.split('');
								//console.log("aptNameArray = ", aptNameArray);
								//console.log("aptNameArray.length = ", aptNameArray.length);
								
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

function parseArray(idx){
	
	let inputCopy = document.getElementsByName('inputCopy');
	
	let parseData = JSON.parse(inputCopy[idx].value);
	console.log(parseData);
	
	let dongName;
		dongName = parseData.landPriceFloors[i].landPrices[0].dongNm;
	
	console.log("dongNameAry : " + dongName);
	
	let hoNameAry = [];
	for(let i = 0; i< parseData.landPriceFloors.length; i++ ){
		for(let j = 0; j < parseData.landPriceFloors[i].landPrices.length; j++ ){
			hoNameAry.push(parseData.landPriceFloors[i].landPrices[j].hoNm);
		}
	}
		
	hoNameAry.sort(function (a, b) {
		  return a - b;
		});
		console.log("hoNmAry : " + hoNameAry);
		
	var tdArr = new Array(); // 배열선언
	let str1Box = document.getElementsByName('str1');
	let dongNameBox = document.getElementsByName('dongName');
	let _saveState = document.getElementsByName('saveState');
	// console.log("str1 = " + str1);

	dongNameBox[idx].value = dongName;
	str1Box[idx].value = hoNameAry;
	_saveState[idx].value = "네이버입력완료"
	
	
	
	// lgj
	let totalSeDae= document.getElementById('totalSedae');
      let totalSeDae2 = 0;
      for(let i = 0; i < str1Box.length; i++ ){
         if(str1Box[i].value != ""){
           // alert(str1Box[i].value);
            totalSeDae2 = totalSeDae2 + str1Box[i].value.split(',').length ;
            totalSeDae.value = "입력된 총 세대수 : " + totalSeDae2;
           }
      }
	
		
	
	
}



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
					+ "</form>" + "</td>";
			_tag += "<td class='modTd' style='vertical-align:middle'>"
					+ "<input type=button class='btn btn-outline-success btn-sm saveDongBtn' name='reply' id='saveDongBtn' value='입력'>"
					+ "<input type=text class='btn btn-outline-success btn-sm' name='inputCopy'>"
					+ "<input type=button class='btn btn-outline-success btn-sm' name='inputBtn' onclick='parseArray("+ i +")' value='입력2'>"
					+ "<input type='radio' name='modRadio' class='modRadio' style='display:none;'>"
					+ "<input type='button' class='btn btn-outline-success btn-sm modDongBtn' name= 'modReply' style='margin-left: 5px;' value='수정'>"
					+ "<input type='text' class='btn btn-outline-success btn-sm saveBtn' name= 'saveState' style='margin-left: 5px; width: 90px;' value='입력전'>"
					+ "<input type='hidden' class='btn btn-outline-success btn-sm dongName2' name= 'dongName2' style='margin-left: 5px;'>"
					+ "<input type='text' class='btn btn-outline-success btn-sm str1' name= 'str1' style='margin-left: 5px;' value=''>"
					+ "<input type=hidden id='aptName' value='"+_aptName+"'>" 
					+ "<input type=hidden id='siteCode' value='"+ _siteCode+"'>"
					+ "<input type=hidden id='sitePhone' value='"+_sitePhone+"'>"
					+ "<input type='button' class='btn btn-sm delDongBtn' name= 'delDongBtn' style='margin-left: 5px; background: crimson;' value='삭제'>";
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
		
		// 저장버튼
		function saveDong() {
			var _trSave = document.getElementsByClassName('saveDongBtn');
			for (let i = 0; i < _trSave.length; i++) {

				_trSave[i].onclick = function(e) {
					//console.log("i 클릭 = ", i);
					if ($(e.target).hasClass('saveDongBtn')) {

						if (document.getElementsByName('matrixHo').length != 0) {
							$(".matrixHo").remove();
						}

						if (document.getElementsByName('matrixFloor').length != 0) {
							$(".matrixFloor").remove();
						}

						if (document.getElementsByName('matrixFloor').length != 0) {
							$(".matrixFloor").remove();
						}

						if (document.getElementsByName('saveBtnIdx').length != 0) {
							$(".saveBtnIdx").remove();
						}

						thisIdx = i;
						console.log("thisIdx1 = ", thisIdx);

						console.log("thisIdx2 = ", thisIdx);

						var _seqSite = $(".seqSite").val();
						var _dongModForm = $(this).parents('tr.saveDongEvt')
								.find('form')[0];

						var _dongName = $(this).parents('tr.saveDongEvt').find("input[name='dongName']").val();
						if (_dongName == '') {
							return alert('동 이름을 입력해주시기 바랍니다.');
						}

						var _oneFloorHo = $(_dongModForm).find(
								"input[name='oneFloorHo']").val();
						if (_oneFloorHo == '') {
							return alert('층당 세대 수를 입력해주시기 바랍니다.');
						}

						var _floor = $(_dongModForm)
								.find("input[name='floor']").val();
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
						_saveState[_saveBtnIdx].value = "입력완료"
						$(this).closest("input[name='reply']").toggle();
						
						
						
						// lgj
						let totalSeDae= document.getElementById('totalSedae');
		                  let totalSeDae2 = 0;
		                  for(let i = 0; i < str1Box.length; i++ ){
		                     if(str1Box[i].value != ""){
		                       // alert(str1Box[i].value);
		                        totalSeDae2 = totalSeDae2 + str1Box[i].value.split(',').length ;
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
							var _dongName = $(this).find(
									"input[name='dongName']").val();
							if (_dongName == '') {
								return alert('동 이름을 입력해주시기 바랍니다.');
							}
							var _oneFloorHo = $(_dongModForm).find(
									"input[name='oneFloorHo']").val();
							if (_oneFloorHo == '') {
								return alert('층당 세대 수를 입력해주시기 바랍니다.');
							}
							var _modCon = $(_dongModForm).find(
									"input[name='floor']").val();
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
							$(this).find("input[name='modRadio']").prop(
									"checked", true);
							// $("input:checkbox[name='NAME명']").prop("checked",
							// false);

						} // if 끝

					} // onclick = function(e) 끝
				} // for 문 끝
			} // if 끝

		}
		; // modAct 끝
		
		
		
		modDong();
		saveDong();

	} catch (e) {
		console.log('error');
		console.log(e);
	} finally {
		$('.wrap-loading').addClass('display-none');
	}

} // getData 끝
