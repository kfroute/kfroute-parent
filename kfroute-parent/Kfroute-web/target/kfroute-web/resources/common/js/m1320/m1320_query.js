/**
 * add by zhangyl at 2015-08-03 for m1310_main.jsp
 */
$(function(){
	$('#server_query_tab').dataTable({
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
	loadGroupMsg();
	
});

//加载扩展模块
layer.config({
    extend: 'extend/layer.ext.js'
});
function loadGroupMsg(){
	$.ajax( {   
        "type": "POST",   
        "url": ctx+"/common/queryServerGroupSelection.do",    
        "dataType": "json",   
        "success": function(data) {   
            //console.log(data);
        	var _option = '<option value="">全部</option>';
        	$('#belongGroup').append(_option);
            for(var i=0;i<data.length;i++){
            	var _map = data[i];
            	var _option = '<option value="'+_map.value+'">'+_map.name+'</option>';
            	$('#belongGroup').append(_option);
            }
           
        },
        "error":function(data){
        	layer.alert("数据获取异常！",{icon: 2,title:'提示'});
        }
	});   
}


function submitQuery(){
	layer.load(2);
	var table = window.parent.table;
	
	var param = {
	    "serverMsg.serverName":$("#serverName").val(),
	    "serverMsg.ipAddress":$("#ipAddress").val(),
	    "serverMsg.operator":$("#operator").val(),
	    "serverMsg.location":$("#location").val(),
	    "serverMsg.bandWidth":$("#bandWidth").val(),
	    "serverMsg.sysStatus":$("#sysStatus").val(),
	    "serverMsg.rate":$("#rate").val(),
	    "serverMsg.belongGroup":$("#belongGroup").val(),
	    "startTimestamp":$("#startTimestamp").val(),
	    "endTimestamp":$("#endTimestamp").val()
	};
	table.settings()[0].ajax.data = param;
	//console.log(table.ajax.url());
	table.ajax.reload();
	

	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭  
	layer.closeAll('loading');
}