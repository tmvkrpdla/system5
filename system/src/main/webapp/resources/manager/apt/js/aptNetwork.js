function createInput(typeName, objName, value, width){
	let input = document.createElement("input");
	input.setAttribute("type", typeName);
	input.setAttribute("name", objName);
	input.setAttribute("autocomplete", "off");
	
	if(width != null){
	input.setAttribute("style", "width:"+width+"%;");
	}
	
	if(value != null){
		input.setAttribute("value", value);

	}
		input.addEventListener('input', function (){
		console.log(input);
	});
		
		
	return input;
}


function makeTable(){
	
}


function init(action){
	
	if (action == "mod") {
		alert("수정 완료");
	}else if(action == "add"){
		alert("추가 완료");
	}else if(action == "del"){
		alert("삭제 완료");
	}
	
	
//	// 엑셀 다운로드
//	$("#excelDown").on("click", function() {
//		$("#oldmeterExcelView").attr("action", "../apt/oldmeterExcelView").submit();
//	});
	
	$("#addAptNetwork").on("click", function() {
		var _outerWidth = window.outerWidth * 0.8;
		var _outerHeight = window.outerHeight * 0.6;
		window.open(
				"addAptNetwork",
				'addAptNetwork',
				'height='
						+ _outerHeight
						+ ', width='
						+ _outerWidth
						+ ', left=20, top=10, scrollbars=1');
	});
	
	
	
	// 단지 선택
	$("#siteSelect").on('change', function() {
		//console.log($(this).val());
		var _seqSite = $(this).val();
		$("#SeqSite").val(_seqSite);
		$("#nowPage").val("");
		
		var target = document.getElementById("siteSelect");
//		alert('선택된 옵션 text 값=' + target.options[target.selectedIndex].text);     // 옵션 text 값
		var _siteName = target.options[target.selectedIndex].text;
		
		$("#SiteName").val(_siteName);
		
	});
	
	//셀렉트 박스
	
let LIST_SITE = LIST_SITE3;
	
	let addSelect = document.getElementById('siteSelect');
	
	for(let i=0 ;i < LIST_SITE.length; i++){
		
		let addOption = document.createElement('OPTION');
		
		addOption.setAttribute('value', LIST_SITE[i].nSeqSite);
		addOption.setAttribute('label',LIST_SITE[i].Name);
		addOption.innerText=LIST_SITE[i].Name + "(" + LIST_SITE[i].SiteCode + ")";
		
		
		addSelect.appendChild(addOption);
	}
	
	
	addSelect.addEventListener('change', function (){
		let listTbody = document.getElementsByClassName('listTbody')[0];
		listTbody.innerText="";
		listTbody.appendChild(createInput('hidden', 'action', 'mod'));
		
		let selectednSeqSite = addSelect.options[addSelect.selectedIndex].value;
		
		let getSelectedInfo= "";
		for(let i = 0 ; i < LIST_SITE.length; i ++ ){
			if(LIST_SITE[i].nSeqSite == selectednSeqSite){
				getSelectedInfo=LIST_SITE[i]
				break;
			}
		}
		listTbody.appendChild(createInput('hidden', 'nSeqSiteH', getSelectedInfo.nSeqSite));
		
		for(let i = 0 ; i < getSelectedInfo.location.length; i ++  ){
			let tr = document.createElement('tr');
			tr.setAttribute('className',"aptmentEventTr");
			
//			tr.setAttribute('value', getSelectedInfo.nSeqSite);
			
			if(i==getSelectedInfo.location.length){
			let tdNseqSite = document.createElement('td');
			tr.appendChild(tdNseqSite);
			}
			
			let tdDong = document.createElement('td');
			tdDong.appendChild(createInput('text', 'location', getSelectedInfo.location[i], 100));
			tdDong.addEventListener( 'keyup',  function(){inputKey(i, null , 'location', 'nPortSsh2')} );
			tr.appendChild(tdDong);
			
			let tdDcuid = document.createElement('td');
			let tdDcuidHidden = createInput('hidden', 'dcuid' , getSelectedInfo.dcuid[i]);
			tdDcuid.innerText = getSelectedInfo.dcuid[i];
			tr.appendChild(tdDcuidHidden);
			tr.appendChild(tdDcuid);
			
			let tdNPortSsh2 = document.createElement('td');
			tdNPortSsh2.appendChild(createInput('text', 'nPortSsh2', getSelectedInfo.nPortSsh2[i] , 80));
			tdNPortSsh2.addEventListener( 'keyup',  function(){inputKey(i, 'location', 'nPortSsh2', 'AddressInfo')});
			
			tr.appendChild(tdNPortSsh2);
			
			let tdNport = document.createElement('td');
			tdNport.setAttribute('name', 'nport');
			tdNport.innerText = getSelectedInfo.nport[i];
			
			tr.appendChild(tdNport);
			
			let tdNPortSnmp = document.createElement('td');
			tdNPortSnmp.setAttribute('name', 'nPortSnmp');
			tdNPortSnmp.innerText = getSelectedInfo.nPortSnmp[i];
			
			tr.appendChild(tdNPortSnmp);
			
			let tdLocalIp = document.createElement('td');
			tdLocalIp.appendChild(createInput('text', 'AddressInfo', getSelectedInfo.AddressInfo[i], 105));
			tdLocalIp.addEventListener( 'keyup',  function(){inputKey(i, 'nPortSsh2', 'AddressInfo', 'LteSn')});
			tr.appendChild(tdLocalIp);
			
			let tdLteSn = document.createElement('td');
			tdLteSn.appendChild(createInput('text', 'LteSn', getSelectedInfo.LteSn[i], 80));
			tdLteSn.addEventListener( 'keyup',  function(){inputKey(i, 'AddressInfo', 'LteSn', 'Comment')});
			tr.appendChild(tdLteSn);
			
			let tdIp = document.createElement('td');
			tdIp.setAttribute('Name', 'ip');
			if(getSelectedInfo.ip[i] == ""){
				tdIp.appendChild(createInput('text', 'ip', getSelectedInfo.ip[i]));
			}else{
				tdIp.innerText = getSelectedInfo.ip[i];
			}
			tr.appendChild(tdIp);
			
			
			let tdComment = document.createElement('td');
			tdComment.appendChild(createInput('text', 'Comment', getSelectedInfo.Comment[i], 100));
			tdComment.addEventListener( 'keyup',  function(){inputKey(i, 'LteSn', 'Comment', null)});

			tr.appendChild(tdComment);
			
			listTbody.appendChild(tr);
		}
		
		$("#modBtn").on("click", function() {
			let addNetworkForm = document.getElementById('addAptNetworkView');
			
			let getChangedInfo = createInput('hidden', 'getChangedInfo'  );
			
			let tbody = document.getElementsByClassName('listTbody')[0];
			
			getChangedInfo.append(tbody);
			
//			console.log(getChangedInfo);
			addNetworkForm.appendChild(getChangedInfo);
			
			$.fn.serializeObject = function() {
				  "use strict"
				  var result = {}
				  var extend = function(i, element) {
				    var node = result[element.name]
				    if ("undefined" !== typeof node && node !== null) {
				      if ($.isArray(node)) {
				        node.push(element.value)
				      } else {
				        result[element.name] = [node, element.value]
				      }
				    } else {
				      result[element.name] = element.value
				    }
				  }

				  $.each(this.serializeArray(), extend)
				  return result
			}
			
			
			let serializeObject =  $('#addAptNetworkView').serializeObject();
			
			console.log(serializeObject);
			
			getChangedInfo.setAttribute('value' , serializeObject);
			
			let addAptNetworkView = document.getElementById("addAptNetworkView");
			addAptNetworkView.appendChild(getChangedInfo);
			$(addAptNetworkView).attr("action", "../apt/addNetworkForm").submit();
			
		});
		
		$("#delBtn").on("click", function() {
			let nSeqSite = document.getElementsByName('nSeqSiteH')[0];
			
			let form = document.getElementById('addAptNetworkView');
			form.appendChild(nSeqSite);
			
			$(addAptNetworkView).attr("action", "../apt/delNetwork").submit();
		});
	});
	
	
	
}


function inputKey(i, pElement, element,  postElement) {
	
	if (window.event.keyCode == 13|| window.event.keyCode == 40) {
		let _oneFloorHo = document.getElementsByName(element);
		_oneFloorHo[i+1].focus();
	}else if(window.event.keyCode == 38) {
		let _oneFloorHo = document.getElementsByName(element);
		_oneFloorHo[i-1].focus();
	}else if(window.event.keyCode == 37) {
		let _oneFloorHo = document.getElementsByName(pElement);
		_oneFloorHo[i].focus();
	}else if(window.event.keyCode == 39) {
		let _oneFloorHo = document.getElementsByName(postElement);
		_oneFloorHo[i].focus();
	}else if(window.event.keyCode == 46) {
		let _oneFloorHo = document.getElementsByName(element);
		_oneFloorHo[i].value = "";
	}
	
};


