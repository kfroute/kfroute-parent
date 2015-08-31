/**
 * add by zhangyl at 2015-08-03 for m1310_main.jsp
 */
$(function(){
	$('#server_view_tab').dataTable({
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
	loadGroupMsg();
	
	
});
function closeFrame(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭  
}
//加载扩展模块
layer.config({
    extend: 'extend/layer.ext.js'
});
function loadGroupMsg(){
	var hiddenBelongGroup = $('#hiddenBelongGroup').val();
	$.ajax( {   
        "type": "POST",   
        "url": ctx+"/common/queryServerGroupSelection.do",    
        "dataType": "json",   
        "success": function(data) {   
            //console.log(data);
            for(var i=0;i<data.length;i++){
            	var _map = data[i];
            	if(_map.value==hiddenBelongGroup){
	            	var _option = '<option selected="selected" value="'+_map.value+'">'+_map.name+'</option>';
	            	$('#belongGroup').append(_option);
            	}else{
            		var _option = '<option value="'+_map.value+'">'+_map.name+'</option>';
                	$('#belongGroup').append(_option);
            	}
            	
            }
           
        },
        "error":function(data){
        	layer.alert("数据获取异常！",{icon: 2,title:'提示'});
        }
	});   
}
function viewPort(ipAddress){
	window.location.href = ctx+'/pages/m1320/m1320_main_port_view.jsp?ipAddress='+ipAddress;

}
function viewMap(longitude,latitude,centerName,operator,cityName){
	window.location.href = ctx+'/pages/m1320/m1320_main_map_view.jsp?longitude='+longitude+"&latitude="+latitude+"&centerName="+centerName+"&operator="+operator+"&cityName="+cityName;

}