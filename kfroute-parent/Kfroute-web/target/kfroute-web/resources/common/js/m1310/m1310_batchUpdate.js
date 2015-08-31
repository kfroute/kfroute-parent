/**
 * add by zhangyl at 2015-08-03 for m1310_main.jsp
 */
$(function(){
	$('#device_batchUpdate_tab').dataTable({
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
	$('#device_batchUpdate_form').validate({
		onkeyup: false,
		errorClass: 'error',
		validClass: 'valid',
		rules: {
			"routerMsg.modelType": { required: true},
			"routerMsg.version": { required: true},
			"routerMsg.chipModel": { required: true},
			"routerMsg.basicFreq": { required: true},
			"routerMsg.ram": { required: true},
			"routerMsg.flash": { required: true},
			"routerMsg.brandName": { required: true}
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
var agent_load_flag=0;//代理商加载标识

function chooseAgent(obj){
	if(obj.value=='2'){//代理商渠道
		if(agent_load_flag==1){//已经加载
			$('#groupId').removeAttr('disabled');
		}else{
			var hiddenGroupId = $('#hiddenGroupId').val();
			$.ajax( {   
		        "type": "POST",   
		        "url": ctx+"/common/queryAgentSelection.do",    
		        "dataType": "json",   
		        "success": function(data) {   
		            //console.log(data);
		            for(var i=0;i<data.length;i++){
		            	var _map = data[i];
		            	if(_map.value==hiddenGroupId){
		            		var _option = '<option selected="selected" value="'+_map.value+'">'+_map.name+'</option>';
			            	$('#groupId').append(_option);
		            	}else{
		            		var _option = '<option value="'+_map.value+'">'+_map.name+'</option>';
			            	$('#groupId').append(_option);
		            	}
		            	
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