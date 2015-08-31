/**
 * add by zhangyl at 2015-08-03 for m1310_main.jsp
 */
$(function(){
	$('#device_update_tab').dataTable({
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
	$('#device_update_form').validate({
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
	if($('#belongType').val()=='2'){//代理商
		chooseAgentByValue(2);
	}
});
jQuery.validator.addMethod("mac", function(value, element, params) {   
	regex = /^[A-Z0-9]{12}$/;
	return this.optional(element) || value.match(regex);
}, "mac地址格式有误，例：F0EBD034A618");
jQuery.validator.addMethod("macExists", function(value, element, params) {   
	var flag = false;
	$.ajax({
		   url: ctx+"/m1310/queryDevice.do?routerMsg.mac="+value+"&iDisplayStart=0&iDisplayLength=1&belong_type=0",
		   type: "get",
		   async: false,
		   dataType:"json",
		   success: function(data){
			   if(!data.rows||data.rows.length==0){//mac地址不存在
				   flag =  true;
			   }else{
				   flag =  false;
			   }
		   },
		   error:function(){
			   flag =  false;
		   }
	});
	return flag;
}, "mac地址已经存在");
//加载扩展模块
layer.config({
    extend: 'extend/layer.ext.js'
});
var agent_load_flag=0;//代理商加载标识
function chooseAgentByValue(value){
	if(value=='2'){//代理商渠道
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