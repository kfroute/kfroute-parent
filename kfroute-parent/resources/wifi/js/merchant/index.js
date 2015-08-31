
var option1={};
var option2={};
$(function(){
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/report!getReport.do',
		dataType: 'json',
		success : function(data){
			var result=data.result;
			if(result==1){
				var dayStrList=data.dayStrList;
				var dayCountList=data.dayCountList;
				var monthStrList=data.monthStrList;
				var monthCountList=data.monthCountList;
				option1=getOption1(dayStrList,dayCountList,data.report.dayStr);
				option2=getOption2(monthStrList,monthCountList,data.report.monthStr);
				require.config({
				    paths: {
				        echarts: '${resourcePath }/js/merchant/echarts'
				    }
				});
				require(
				    [
				        'echarts',
				        'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
				    ],DrawEChart);
				
				
			}else if(result==-1){
				alert("登录超时，请重新登录");
			}
			
			
		},
		error   : function(data){
			alert("获取数据失败");
		}
	});
	setInterval(changeShape,10);
});

function changeShape(){
	if($("#welcomeBox").height()>100&&$(".main_index_top").height()<200){
		$("#welcomeBox span").before("<br>");
		$(".main_index_top").css("height","200px");
	}
}

//渲染ECharts图表
function DrawEChart(ec) {
	//图表渲染的容器对象
	var chartContainer1 = document.getElementById("main1");
	//加载图表
	var myChart1 = ec.init(chartContainer1);
	myChart1.setOption(option1);
	var chartContainer2 = document.getElementById("main2");
	//加载图表
	var myChart2 = ec.init(chartContainer2);
	myChart2.setOption(option2);
}


function getOption1(dayStrList,dayCountList,dayStr){
	var option={
		title : {
	        text: dayStr+'日',
	        subtext: '一天各时间段访问量统计表'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['访问量']
	    },
	    
	    calculable : false,//决定是否允许拖动，这里不允许
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : dayStrList
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} 人/次',
	                textStyle:{
	                	color:"black"
	                }
	            },
	            splitArea : {show : true}
	        }
	    ],
	    series : [
	        {
	            name:'访问量',
	            type:'line',
	            itemStyle: {
	                normal: {
	                	color : 'rgba(255,51,0,255)',
	                    lineStyle: {
	                        shadowColor : 'rgba(255,51,0,0.4)',
	                        shadowBlur: 5,
	                        shadowOffsetX: 3,
	                        shadowOffsetY: 3
	                    }
	                }
	            },
	            data:dayCountList,
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        }
	    ]
	};
	return option;
}

function getOption2(dayStrList,dayCountList,dayStr){
	var option={
		title : {
	        text: dayStr+'月',
	        subtext: '一个月中每日访问量统计表'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['访问量']
	    },
	    
	    calculable : false,//决定是否允许拖动，这里不允许
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : dayStrList
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} 人/次'
	            },
	            splitArea : {show : true}
	        }
	    ],
	    series : [
	        {
	            name:'访问量',
	            type:'line',
	            itemStyle: {
	                normal: {
	                	color : 'rgba(255,51,0,255)',
	                    lineStyle: {
	                        shadowColor : 'rgba(255,51,0,0.4)',
	                        shadowBlur: 5,
	                        shadowOffsetX: 3,
	                        shadowOffsetY: 3
	                    }
	                }
	            },
	            data:dayCountList,
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        }
	    ]
	};
	return option;
}
