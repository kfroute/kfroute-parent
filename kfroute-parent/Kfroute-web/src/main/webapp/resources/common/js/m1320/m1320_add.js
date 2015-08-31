/**
 * add by zhangyl at 2015-08-22 for m1320_main_add.jsp
 */
$(function(){
	$('#server_add_tab').dataTable({
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
	$('#server_add_form').validate({
		onkeyup: false,
		errorClass: 'error',
		validClass: 'valid',
		rules: {
			"serverMsg.ipAddress": { required: true,ip:true,ipExists:true},
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
jQuery.validator.addMethod("ip", function(value, element, params) {   
	regex = /^((([1-9]?|1\d)\d|2([0-4]\d|5[0-5]))\.){3}(([1-9]?|1\d)\d|2([0-4]\d|5[0-5]))$/;
	return this.optional(element) || value.match(regex);
}, "ip地址格式有误，例：192.168.1.1");
jQuery.validator.addMethod("ipExists", function(value, element, params) {   
	var flag = false;
	$.ajax({
		   url: ctx+"/m1320/queryServer.do?serverMsg.ipAddress="+value+"&iDisplayStart=0&iDisplayLength=1&belong_type=0",
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
}, "服务器IP地址已经存在");
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