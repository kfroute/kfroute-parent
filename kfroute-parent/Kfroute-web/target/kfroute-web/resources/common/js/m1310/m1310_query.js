/**
 * add by zhangyl at 2015-08-03 for m1310_main.jsp
 */
$(function(){
	$('#device_query_tab').dataTable({
		"bPaginate":false,
		"bInfo":false,
		"searching":false,
		"bSort": false,
		"ordering":false,
		"bRetrieve":true,
		"aoColumnDefs": [//各列的一些个性显示信息
		 				{
		 					sDefaultContent: '',
		 					aTargets: [ '_all' ]
		 				}
		 			],
	});
	$(".input-table tbody tr th").addClass("sorting_1");
	$(".form_datetime").datetimepicker({
		language:  'zh-CN',
        format: "yyyy-mm-dd",
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        pickerPosition: "top-left",
        forceParse: 0
    });
	if($('#groupIdHidden').val()||groupIdHidden.val()!='0'){
		chooseAgentByValue('2');
	}
	
});

//加载扩展模块
layer.config({
    extend: 'extend/layer.ext.js'
});
var agent_load_flag=0;//代理商加载标识
function chooseAgent(obj){
	if(obj.value=='2'){//代理商渠道
		if(agent_load_flag==1){//已经加载
			$('#groupId').removeAttr('disabled');
		}else{
			$.ajax( {   
		        "type": "POST",   
		        "url": ctx+"/common/queryAgentSelection.do",    
		        "dataType": "json",   
		        "success": function(data) {   
		            //console.log(data);
		        	var _option = '<option value="">全部</option>';
	            	$('#groupId').append(_option);
		            for(var i=0;i<data.length;i++){
		            	var _map = data[i];
		            	var _option = '<option value="'+_map.value+'">'+_map.name+'</option>';
		            	$('#groupId').append(_option);
		            }
		            agent_load_flag=1;
		            $('#groupId').removeAttr('disabled');
		        },
		        "error":function(data){
		        	layer.alert("数据获取异常！",{icon: 2,title:'提示'});
		        	 $('#groupId').attr('disabled','disabled');
		        }
			});   
		}
		
	}else{
		$('#groupId').attr('disabled','disabled');
	}
}
function chooseAgentByValue(value){
	if(value=='2'){//代理商渠道
		if(agent_load_flag==1){//已经加载
			$('#groupId').removeAttr('disabled');
		}else{
			$.ajax( {   
		        "type": "POST",   
		        "url": ctx+"/common/queryAgentSelection.do",    
		        "dataType": "json",   
		        "success": function(data) {   
		            //console.log(data);
		        	var _option = '<option value="">全部</option>';
	            	$('#groupId').append(_option);
		            for(var i=0;i<data.length;i++){
		            	var _map = data[i];
		            	var _option = '<option value="'+_map.value+'">'+_map.name+'</option>';
		            	$('#groupId').append(_option);
		            }
		            agent_load_flag=1;
		            $('#groupId').removeAttr('disabled');
		        },
		        "error":function(data){
		        	layer.alert("数据获取异常！",{icon: 2,title:'提示'});
		        	 $('#groupId').attr('disabled','disabled');
		        }
			});   
		}
		
	}else{
		$('#groupId').attr('disabled','disabled');
	}
}
function submitQuery(){
	layer.load(2);
	var table = window.parent.table;
	var groupId;
	if(!$("#groupId").attr('disabled')){
		groupId = $("#groupId").val();
	}
	var param = {
	    "routerMsg.mac":$("#mac").val(),
	    "routerMsg.modelType":$("#modelType").val(),
	    "routerMsg.version":$("#version").val(),
	    "routerMsg.chipModel":$("#chipModel").val(),
	    "routerMsg.belongType":$("#belongType").val(),
	    "routerMsg.groupId":groupId,
	    "routerMsg.basicFreq":$("#basicFreq").val(),
	    "routerMsg.ram":$("#ram").val(),
	    "routerMsg.flash":$("#flash").val(),
	    "routerMsg.brandName":$("#brandName").val(),
	    "startTimestamp":$("#startTimestamp").val(),
	    "endTimestamp":$("#endTimestamp").val()
	};
	table.settings()[0].ajax.data = param;
	//console.log(table.ajax.url());
	//console.log(table.settings()[0].ajax.data);
	table.ajax.reload();
	

	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭  
	layer.closeAll('loading');
}