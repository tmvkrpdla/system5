let b1;
let b2

let pIdx = opener.window.$("#modBtnIdx").val();

function delHo() {
	var _delHoEvt = document.getElementsByClassName('delHoBtn');

	for (var i = 0; i < _delHoEvt.length; i++) {
		_delHoEvt[i].onclick = function() {
			//         var _ho = $(this).parents('tr.delTrHoEvt').find('.seq_ho').val();
			var _selDelho = $(this).parents('td.delTdHoEvt').find("input[name='seq_ho']").val();
			console.log("_selDelho = " + _selDelho);

			//         alert($(this).parents('td.delTdHoEvt').find("input[name='seq_ho']")[0].disabled);
			if ($(this).parents('td.delTdHoEvt').find("input[name='seq_ho']")[0].disabled != true) {
				$(this).parents('td.delTdHoEvt').find("input[name='seq_ho']")
						.attr("disabled", true).removeAttr('class');
			} else {
				$(this).parents('td.delTdHoEvt').find("input[name='seq_ho']").attr("disabled", false).addClass('seq_ho');
			}

			//$(this).parents('td.delTdHoEvt').find("input[name='seq_ho']").remove();
			//$(this).parents('td.delTdHoEvt').find("input[name='seq_ho']").removeAttr('class');
		}

	}
};

function all4ex() {
	var tag = "<table id='example-table-1' class='table tablesorter'>";

	var id3 = opener.$(".modCon").val();
	b1 = parseInt(id3); //층수

	var id4 = opener.window.$("._oneFloorHo").val();
	b2 = parseInt(id4); //층당 세대수

	var seqSite = opener.window.$("#seqSite").val();

	for (j = b1; j >= 1; j--) {
		tag += "<tr class='delTrHoEvt'>";
		for (i = 1; i <= b2; i++) {
			if (i < 10) {
				if (i % 10 != 4)
					tag += "<td class='delTdHoEvt'>"
							// +j+"0"+i
							+ "<input style='width:50px;' type='text' name='seq_ho' class='seq_ho' value="
							+ j
							+ "0"
							+ i
							+ ">"
							+ "<input type='button' name='delHoBtn' class='delHoBtn' value='X'>"
							+ "</td>";
			} else {
				if (i % 10 != 4)
					tag += "<td class='delTdHoEvt'>"
							// +j+"0"+i
							+ "<input style='width:50px;' type='text' name='seq_ho' class='seq_ho' value="
							+ j
							+ i
							+ ">"
							+ "<input type='button' name='delHoBtn' class='delHoBtn' value='X'>"
							+ "</td>";
			}
		}
		tag += "</tr>";
	}
	// style='display: none;'

	tag += "</table>";
	area.innerHTML = tag;
}

function only4ex() {
	var tag = "<table id='example-table-1' class='table tablesorter'>";

	var id3 = opener.$(".modCon").val();
	b1 = parseInt(id3); //층수

	var id4 = opener.window.$("._oneFloorHo").val();
	b2 = parseInt(id4); //층당 세대수

	var seqSite = opener.window.$("#seqSite").val();

	for (j = b1; j >= 1; j--) {

		tag += "<tr class='delTrHoEvt'>";
		for (i = 1; i <= b2; i++) {

			if (i < 10) {
				if (i % 10 != 4)
					tag += "<td class='delTdHoEvt'>"
							// +j+"0"+i
							+ "<input style='width:50px;' type='text' name='seq_ho' class='seq_ho' value="
							+ j
							+ "0"
							+ i
							+ ">"
							+ "<input type='button' name='delHoBtn' class='delHoBtn' value='X'>"
							+ "</td>";
			} else {
				tag += "<td class='delTdHoEvt'>"
						// +j+"0"+i
						+ "<input style='width:50px;' type='text' name='seq_ho' class='seq_ho' value="
						+ j
						+ i
						+ ">"
						+ "<input type='button' name='delHoBtn' class='delHoBtn' value='X'>"
						+ "</td>";
			}
		}
		tag += "</tr>";
	}
	// style='display: none;'

	tag += "</table>";
	area.innerHTML = tag;

}

function saveBtn() {
	var seqSite = opener.window.$("#seqSite").val();
	var dongName = opener.window.$(".modDongName").val();
	var str = ""
	var tdArr = new Array(); // 배열선언
	var tr = $("#example-table-1").children();
	var td = tr.children();
	var input = td.find("input[class='seq_ho']");

	let aptName = opener.$("#aptName").val();
	let siteCode = opener.$("#siteCode").val();
	let sitePhone = opener.$("#sitePhone").val();

	input.each(function(i) {
		tdArr.push(input.eq(i).val());
	});

	//console.log("원래 tdArr = ", tdArr);

	tdArr.sort(function compare(a, b) {
		return a - b;
	});
	//console.log("sort 한 tdArr = ", tdArr);

	var str1 = tdArr.join();
	//console.log("str1 = ", str1);

	//   var pReplyBtn = opener.document.getElementsByName("reply")[pIdx];
	//   console.log("pReplyBtn = ", pReplyBtn);

	if (str1 != "") {
		opener.document.getElementsByName("dongName2")[pIdx].value = dongName;
		opener.document.getElementsByName("str1")[pIdx].value = str1;
		opener.document.getElementsByName("saveState")[pIdx].value = "입력 완료";
		
		//pReplyBtn.css("display", "none"); 안됨
		//pReplyBtn.style.display = "none"; 됨
		opener.document.getElementsByName("reply")[pIdx].style.display = "none";
		//$(opener.document).find("input:checked").parents('td.modTd').find("input[name='reply']").css("display", "none"); 됨
		
	      let totalSeDae= opener.document.getElementById('totalSedae');
	      let str1Box = opener.document.getElementsByName('str1');
	      let totalSeDae2 = 0;
	      
	        for(let i = 0; i < str1Box.length; i++ ){
	           if(str1Box[i].value != ""){
	               totalSeDae2 = totalSeDae2 + str1Box[i].value.split(',').length ;
	               totalSeDae.value = "입력된 총 세대수 : " + totalSeDae2;
	              }
	        }
	        
		alert("저장에 성공하였습니다.");

		window.close();
	} else {
		alert("저장에 실패하였습니다.");
	}
}

//창 시작
window.onload = function main(id3, id4) {
	var tag = "<table id='example-table-1' class='table tablesorter'>";

	var id3 = opener.$(".modCon").val();
	b1 = parseInt(id3); //층수

	var id4 = opener.window.$("._oneFloorHo").val();
	b2 = parseInt(id4); //층당 세대수

	var seqSite = opener.window.$("#seqSite").val();

	for (j = b1; j >= 1; j--) {
		tag += "<tr class='delTrHoEvt'>";
		for (i = 1; i <= b2; i++) {

			if (i < 10) {
				tag += "<td class='delTdHoEvt'>"
						// +j+"0"+i
						+ "<input style='width:50px;' type='text' name='seq_ho' class='seq_ho' value="+ j + "0" + i +">"
						+ "<input type='button' name='delHoBtn' class='delHoBtn' value='X'>"
						+ "</td>";
			} else {
				tag += "<td class='delTdHoEvt'>"
						// +j+"0"+i
						+ "<input style='width:50px;' type='text' name='seq_ho' class='seq_ho' value=" + j + i +">"
						+ "<input type='button' name='delHoBtn' class='delHoBtn' value='X'>"
						+ "</td>";
			}
		}
		tag += "</tr>";
	}
	// style='display: none;'

	tag += "</table>";
	area.innerHTML = tag;
	// 미사용 호 삭제
	delHo();

	//$("#actSaveBtn").on('click', function() {
	$("#modSaveBtn").click(function() {

		saveBtn();
	});

	$("#modCancleBtn").click(function() {
		location.reload();
	});

	//모든 4호 제외
	$("#a4ex").click(function() {
		all4ex();
		delHo();
	});
	//4호만 제외
	$("#o4ex").click(function() {
		only4ex();
		delHo();
	});

}