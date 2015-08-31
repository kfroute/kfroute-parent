
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
	var option = {
		    tooltip : {
		        trigger: 'axis'
		    },
		    calculable : false,//决定是否允许拖动，这里不允许
		    legend: {
		        data:['直接访问','百度','谷歌','必应','其他']
		    },
		    xAxis : [
		        {
		            type : 'category',
		            splitLine : {show : false},
		            data : ['周一','周二','周三','周四','周五','周六','周日']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            splitArea : {show : true}
		        }
		    ],
		    series : [
		        {
		            name:'直接访问',
		            type:'bar',
		            barWidth : 30,
		            data:[320, 332, 301, 334, 390, 330, 320]
		        },
		        {
		            name:'搜索引擎细分',
		            type:'pie',
		            tooltip : {
		                trigger: 'item',
		                formatter: '{a} <br/>{b} : {c} ({d}%)'
		            },
		            center: [660,130],
		            radius : [0, 50],
		            itemStyle :　{
		                normal : {
		                    labelLine : {
		                        length : 20
		                    }
		                }
		            },
		            data:[
		                {value:1048, name:'百度'},
		                {value:251, name:'谷歌'},
		                {value:147, name:'必应'},
		                {value:102, name:'其他'}
		            ]
		        }
		    ]
		};
	return option;
}


function getOption2(dayStrList,dayCountList,dayStr){
	var option = {
		    tooltip : {
		        trigger: 'axis'
		    },
		    calculable : false,//决定是否允许拖动，这里不允许
		    legend: {
		        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎','百度','谷歌','必应','其他']
		    },
		    xAxis : [
		        {
		            type : 'category',
		            splitLine : {show : false},
		            data : ['周一','周二','周三','周四','周五','周六','周日']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            position: 'right',
		            splitArea : {show : true}
		        }
		    ],
		    series : [
		        {
		            name:'直接访问',
		            type:'bar',
		            data:[320, 332, 301, 334, 390, 330, 320]
		        },
		        {
		            name:'邮件营销',
		            type:'bar',
		            tooltip : {trigger: 'item'},
		            stack: '广告',
		            data:[120, 132, 101, 134, 90, 230, 210]
		        },
		        {
		            name:'联盟广告',
		            type:'bar',
		            tooltip : {trigger: 'item'},
		            stack: '广告',
		            data:[220, 182, 191, 234, 290, 330, 310]
		        },
		        {
		            name:'视频广告',
		            type:'bar',
		            tooltip : {trigger: 'item'},
		            stack: '广告',
		            data:[150, 232, 201, 154, 190, 330, 410]
		        },
		        {
		            name:'搜索引擎',
		            type:'line',
		            data:[862, 1018, 964, 1026, 1679, 1600, 1570]
		        },
		        {
		            name:'搜索引擎细分',
		            type:'pie',
		            tooltip : {
		                trigger: 'item',
		                formatter: '{a} <br/>{b} : {c} ({d}%)'
		            },
		            center: [160,130],
		            radius : [0, 50],
		            itemStyle :　{
		                normal : {
		                    labelLine : {
		                        length : 20
		                    }
		                }
		            },
		            data:[
		                {value:1048, name:'百度'},
		                {value:251, name:'谷歌'},
		                {value:147, name:'必应'},
		                {value:102, name:'其他'}
		            ]
		        }
		    ]
		};
	return option;
}
