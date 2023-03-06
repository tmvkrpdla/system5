$(function(){
	
	//	정보 가져오기
	function getInfo(){
		var _getInfo = enernet.modules.api.getAmiInfo();
		console.log("_getInfo = " + _getInfo);
		
		$("#site").text(_getInfo.count_site);
		$("#count_ho").text(_getInfo.count_ho);
		$("#count_mdms").text(_getInfo.count_mdms);
		$("#count_dcu").text(_getInfo.count_dcu);
		$("#count_modem").text(_getInfo.count_modem);
		$("#count_meter").text(_getInfo.count_meter);
		
	}
	getInfo()

//	//	차트
//	function drawChart(){
//		
//		// 차트 데이터 생성
//		var _list_usage = _apiData.list_usage;
//		
//			
//		var	_bar_data = [];
//		var	_line_data_workday = [];
//		var _line_data_holiday = [];
//		var	_chart_label = [];
//		
//		
//		
//		for(var i=0; i<_list_usage.length; i++){
//			_bar_data.push(_list_usage[i].usage);
//			
//			//	근무일이면
//			_line_data_workday.push(_list_usage[i].usage_avg_workday);
//			//	휴무일이면
//			_line_data_holiday.push(_list_usage[i].usage_avg_holiday);
//			
//			
////			_chart_label.push(_list_usage[i].hhmm);
//			
//			//	라벨
//			if( _list_usage[i].hhmm.indexOf(":15")>0 || _list_usage[i].hhmm.indexOf(":30")>0 || _list_usage[i].hhmm.indexOf(":45")>0 ){
//				_chart_label.push("");	//	15분, 30분, 45분
//				
//			}else{
//				if(_list_usage[i].hhmm.indexOf("00:00")>0){
//					_chart_label.push("");	//	00:00 분
//				}else{
//					var _hhmm = _list_usage[i].hhmm.replace(":00", "");
//					
//					_chart_label.push(_hhmm + "시");
//				}
//				
//			}
//			
//		}
//		
//		
//		_ctx = document.getElementById('myChart');
//		
//		_mixedChart = new Chart(_ctx, {
//		    type: 'bar',
//		    data: {
//		        datasets: [
//		        	{
//			            label: '오늘 사용량',
//			            data: _bar_data,	//	bar chart
//			            borderWidth: 2,
//			            backgroundColor: 'rgba(255, 0, 0, 0.5)',
//			            // this dataset is drawn below
//			            order: 1
//			        }, 
//			        {
//			            label: '30일 평균(근무일)',
//			            data: _line_data_workday,	//	line chart
//			            type: 'line',
//			            borderWidth: 3,
//			            borderColor: '#7F7EFF',
//			            backgroundColor: '#7F7EFF',
//			            fill: false,
//			            order: 2
//			        },
//			        {
//			            label: '30일 평균(휴무일)',
//			            data: _line_data_holiday,	//	line chart
//			            type: 'line',
//			            borderWidth: 3,
//			            borderColor: '#8EC7D0',
//			            backgroundColor: '#8EC7D0',
//			            fill: false,
//			            order: 3
//			        }
//			        ],
//		        labels: _chart_label //['January', 'February', 'March', 'April']
//		    },
//		    options: {
//		    	scales:{
//		    		xAxes: [{
//		    			gridLines: {
//		                    display:false
//		                },
//		    			ticks: {
//		    				autoSkip: false,
//		    				maxRotation: 0,
//		    				fontSize: 12
//		    			}
//		    		}],
//		    		yAxes: [{
//		    			ticks: {
//		    				suggestedMax: 20,
//		    				fontSize: 14
//		    			},
//		    			scaleLabel: {
//		    				display: true,
//		    				labelString: 'kWh',
//		    				fontSize: 14
//		    			}
//		    		}]
//		    	},
//				responsive: true,
//				maintainAspectRatio: false,
//			    elements: {
//		            point:{
//		                radius: 0
//		            },
//		            line: {
//		            	tension: 0
//		            }
//		        }
//			}
//		});
//	}//	차트 끝
//	
//	
//	//	30일 평균 사용량 테이블
//	function getListUsageTable(){
//		
//		var _arr1 = _apiData.list_usage.slice(0,48);
//		var _arr2 = _apiData.list_usage.slice(48);
//		
//		var _listUsageTable1 = createTag(_arr1);
//		var _listUsageTable2 = createTag(_arr2);
//		
//		$("#list_usage_table1").append(_listUsageTable1);
//		$("#list_usage_table2").append(_listUsageTable2);
//	}
//
//	function createTag(arr){
//		var _tag = null;
//		
//		for(var i=0; i<arr.length; i++){
//			_tag += "<tr class='list_usage_tr'>";
//			_tag += "<td>" + arr[i].hhmm + "</td>";	//	시간
//			
//			if(arr[i].usage == null){
//				_tag += "<td>" + "-" + "</td>";	//	사용량
//			}else{
//				_tag += "<td>" +  enernet.modules.utils.commaStr(arr[i].usage.toFixed(2)) + "</td>";	//	사용량	
//			}
//			_tag += "<td>" +  enernet.modules.utils.commaStr(arr[i].usage_avg_workday.toFixed(2)) + "</td>";	//	근무일
//			
//			_tag += "<td>" +  enernet.modules.utils.commaStr(arr[i].usage_avg_holiday.toFixed(2)) + "</td>";		//	휴무일
//			
//			
//			if(arr[i].delta_usage == null){
//				_tag += "<td>" + "-" + "</td>";
//			}else{
//				if(arr[i].delta_usage>0){
//					_tag += "<td>" + "+" + enernet.modules.utils.commaStr(arr[i].delta_usage.toFixed(2)) + "</td>";
//				}else{
//					_tag += "<td>"  + enernet.modules.utils.commaStr(arr[i].delta_usage.toFixed(2)) + "</td>";
//				}
//			}
//			
//			
//			if(arr[i].delta_carbon == null){
//				_tag += "<td>" + "-" + "</td>";
//			}else{
//				
//				if(arr[i].delta_carbon>0){
//					_tag += "<td>" +  "+" + enernet.modules.utils.commaStr(arr[i].delta_carbon.toFixed(2)) + "</td>";
//				}else{
//					_tag += "<td>" +  enernet.modules.utils.commaStr(arr[i].delta_carbon.toFixed(2)) + "</td>";
//				}
//				
//			}
//			
//			
//			_tag += "</tr>";
//		}
//		
//		return _tag;
//	}
	
});