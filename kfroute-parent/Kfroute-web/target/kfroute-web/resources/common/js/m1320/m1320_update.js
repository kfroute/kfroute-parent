/**
 * add by zhangyl at 2015-08-03 for m1310_main.jsp
 */
$(function(){
	$('#server_update_tab').dataTable({
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
	$('#server_update_form').validate({
		onkeyup: false,
		errorClass: 'error',
		validClass: 'valid',
		rules: {
			"serverMsg.serverName": { required: true},
			"serverMsg.operator": { required: true},
			"serverMsg.location": { required: true},
			"serverMsg.operSystem": { required: true},
			"serverMsg.sysKernel": { required: true},
			"serverMsg.fileHandles": { required: true},
			"serverMsg.cpuModel": { required: true},
			"serverMsg.cpuFreq": { required: true},
			"serverMsg.centerName": { required: true},
			"serverMsg.bandWidth": { required: true},
			"serverMsg.rate": { required: true},
			"serverMsg.brandName": { required: true}
		},
		submitHandler: function(form) {  //通过之后回调 
			layer.confirm("确定提交吗？", {icon: 3,title:'提示'}, function(index){
				form.submit();//提交表单，如果不写，即便通过表单也不会自动提交 
				layer.load(2);
			});
			 
		}
	});
	
});

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