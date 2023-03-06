var _ctx = null;

$(function() {
	
	//	데이트 피커
	enernet.modules.evt.makeDateYMD("dateTarget");
	
	//	조회 버튼
	$("#searchBtn").on('click', function() {
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
		    		position: 'top',
	    			labels: {
	    				fontColor: "white" // 라벨 폰트색상 정의
	    			}
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