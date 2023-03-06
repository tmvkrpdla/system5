var _ctx = null;

$(function(){
	
	//	데이트 피커
	enernet.modules.evt.makeDateYM("monthFrom");
	enernet.modules.evt.makeDateYM("monthTo");
	
	$("#searchBtn").on('click', function(){
		var _monthFrom = $("#monthFrom").val().replace(/-/g, '');
		var _monthTo = $("#monthTo").val().replace(/-/g, '');

		var sdd = document.getElementById("monthFrom").value;
		var edd = document.getElementById("monthTo").value;
		var ar1 = sdd.split('-');
		var ar2 = edd.split('-');
		var da1 = new Date(ar1[0], ar1[1]);
		var da2 = new Date(ar2[0], ar2[1]);
		var dif = da2 - da1;
		var cDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
		var cMonth = cDay * 30;// 월 만듬
		var cYear = cMonth * 12; // 년 만듬
		
		//console.log("dif = ", dif);
		
		var datelong = parseInt(dif / cMonth);
	
		/*if (sdd && edd) {
			document.getElementById('years').value = parseInt(dif / cYear)
			document.getElementById('months').value = parseInt(dif / cMonth)
			document.getElementById('days').value = parseInt(dif / cDay)
		}*/
		
		if("" == _monthFrom){
			return alert("기간 시작일을 입력해주세요");
		}
		
		if("" == _monthTo){
			return alert("기간 마지막일을 입력해주세요");
		}
		
		if(_monthFrom > _monthTo){
			return alert("기간 시작일이 기간 마지막일보다 큽니다");
		}
		
		if (datelong > 11) {
			return alert("최대 조회 가능 기간은 12개월입니다.")
		}
		
		$("#searchForm").submit();
		
	});
	
	// 단지 선택
	$("#siteSelect").on('change', function() {
		//console.log($(this).val());
		
		var _seqSite = $(this).val();
		$("#SeqSite").val(_seqSite);
		
		if (_seqSite == "0") {
			//console.log("전체입니다.");
			$("#SeqDong").val("0");
			const target = document.getElementById('aptDongSelect');
			target.disabled = true;

		} else {
			//console.log("선택입니다.");
			const target = document.getElementById('aptDongSelect');
			target.disabled = false;
		};

		var _obj = {};
		_obj.SeqSite = _seqSite;

		var _res = enernet.modules.api.getDongListBySite(_obj.SeqSite);
		//console.log("_res = " + _res.list_dong.length);

		$("#aptDongSelect").find('option').remove()
		var _list_dong = _res.list_dong;

		for (var i = 0; i < _list_dong.length; i++) {
			var _tag = "<option value=" + _list_dong[i].seq_dong + ">"
			_tag += _list_dong[i].dong_name;
			_tag += "</option>";

			$("#aptDongSelect").append(_tag);

		}
		
	});	
	
	$("#aptDongSelect").on('change', function(){
		//console.log($(this).val());
		var _seqSite = $("#SeqSite").val();
		//console.log("_seqSite = " + _seqSite);
		
		var _obj = {};
		_obj.SeqDong = $(this).val();
		//console.log("_obj.SeqDong = " + _obj.SeqDong);
		
		var _res = enernet.modules.api.getHoListByDong(_obj.SeqDong);
		//if(_res.ho_list > 1){
			$("#aptHoSelect").find('option').remove()
			
			var _ho_list = _res.list_ho;
			
			for(var i=0; i<_ho_list.length; i++){
				var _tag = "<option value=" + _ho_list[i].seq_ho + ">"
				_tag += _ho_list[i].ho_name;
				_tag += "</option>";
				
				$("#aptHoSelect").append(_tag);

			}
	//	}
		
	});
	
_ctx = document.getElementById('myChart');
	
//	전기
drawChart('myChart', _myChart, 1, 0);

function drawChart(targetId, chartData, type, kind){
	if(kind==0){
		var _y = [
			{
				type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
				display: true,
				position: 'left',
				id: 'y-axis-1',
				ticks: {
    				//suggestedMax: 20,
					fontColor: "#ffffff",
    				fontSize: 14,
    				suggestedMin : 0
    			},
    			scaleLabel: {
    				display: true,
    				labelString: 'kWh',
    				fontSize: 14,
    				fontColor: "#ffffff"
    			},
    			gridLines:{
					color: 'rgba(166, 201, 226, 1)',
					lineWidth:0.3
				}
			}/*, 
			{
				type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
				display: true,
				position: 'right',
				id: 'y-axis-2',
				ticks: {
    				//suggestedMax: 20,
    				fontSize: 14,
    				suggestedMin : 0
    			},
				// 	grid line settings
				gridLines: {
					drawOnChartArea: false, // only want the grid lines for one axis to show up
				},
			}*/];
		
	}
	else{
		var _y = [
			{
				type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
				display: true,
				position: 'left',
				id: 'y-axis-1',
				ticks: {
    				//suggestedMax: 20,
					fontColor: "#ffffff",
    				fontSize: 14,
    				suggestedMin : 0
    			},
    			scaleLabel: {
    				display: true,
    				labelString: 'm³',
    				fontSize: 16
    			}
			}, 
			{
				type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
				display: true,
				position: 'right',
				id: 'y-axis-2',
				ticks: {
    				//suggestedMax: 20,
					fontColor: "#ffffff",
    				fontSize: 14,
    				suggestedMin : 0
    			},
				// 	grid line settings
				gridLines: {
					drawOnChartArea: false, // only want the grid lines for one axis to show up
				},
			}];
		
	}
	
	if(type==2){
		_y = _y.slice(0,1);
	}
	
	var _chart = new Chart( targetId, {
	    type: 'bar',
	    data: {
	        datasets: chartData,
	        labels: _chartLabels
	    },
	    options: {
	    	legend: {
	    		position: 'top'
	    	},
			responsive: true,
			maintainAspectRatio: false,
		    elements: {
	            point:{
	                radius: 2	//	데이터의 점표시
	            },
	            line: {
	            	tension: 0
	            }
	        },
	        tooltips: {
				mode: 'index',
				intersect: true
			},
	    	scales: {
	    		
	    		xAxes: [{
					ticks:{
						fontColor : '#ffffff',
						fontSize : 14
					},
					gridLines:{
						color: 'rgba(166, 201, 226, 1)',
						lineWidth:0.3
					}
				}],
	    		
	    		yAxes: _y
			}
		}
	});
	
}

/*
var _myLineChart = new Chart(_ctx, {
    type: 'line',
    data: {
        datasets: _chartData,
        labels: _chartLabels
    },
    options: {
    	legend: {
    		position: 'right'
    	},
		responsive: true,
		maintainAspectRatio: false,
	    elements: {
            point:{
                radius: 0
            },
            line: {
            	tension: 0
            }
        },
    	scales: {
    		yAxes: [{
    			ticks: {
    				suggestedMax: 20,
    				fontSize: 14
    			},
    			scaleLabel: {
    				display: true,
    				labelString: 'kWh',
    				fontSize: 14
    			}
    		}]
		}
	}
});*/
});