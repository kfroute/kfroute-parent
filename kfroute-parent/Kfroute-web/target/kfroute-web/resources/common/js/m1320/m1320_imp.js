/**
 * add by zhangyl at 2015-08-03 for m1320_main.jsp
 */
var imp_form;
var imp_success = false;
var fail_num=0;
$(function(){
	$('#server_imp_tab').dataTable({
		"bPaginate":false,
		"bInfo":false,
		"searching":false,
		"bSort": false,
		"ordering":false,
		"bRetrieve":true
	});
	$(".input-table tbody tr th").addClass("sorting_1");
	imp_uniform.init();
	imp_form = document.forms['imp_form'];
});

//加载扩展模块
layer.config({
    extend: 'extend/layer.ext.js'
});
//* uniform
imp_uniform = {
	init: function() {
        $(".uni_style").uniform();
    }
};

/**
 * 重新选择文件
 */
function change()
{	
	var attachTemp = imp_form.attach_name.value;
	var file_name = attachTemp.substring(attachTemp.lastIndexOf("\\")+1,attachTemp.lastIndexOf("."));
	
	imp_form.file_name.value=file_name;
}
function dataLoad(){//加载数据到数据库，此时数据仅存放在临时表中，用户提交后才会更新到审批表等待审批
	
	if(imp_form.data_load.value=="重新导入"){
		layer.confirm('导入文件将删除，确定？', {icon: 3,title:'提示'}, function(index){
			layer.close(index);
			imp_form.data_load.value="导入";
			$('#import_msg').html("");
			imp_success = false;
			
			imp_form.attach_name.disabled=false;
			$.post(ctx+"/m1320/m1320_delUploadFile.do",function(data){
				if(data==1){
					$('#imp_detail').css("display","none");
					commit_flag=false;
					layer.alert("导入文件删除成功，请重新导入！",{icon: 6});
				}
			},"text");
		});
		return;
		
	}
	//alert(cust_mgr_tree.value_id);
	var attachTemp = imp_form.attach_name.value;
	if(attachTemp==""||attachTemp==null){
		layer.alert("请上传文件！",{icon: 5,title:'提示'});
		return;	
	}
	var note= imp_form.imp_note.value;

	var file_name = attachTemp.substring(attachTemp.lastIndexOf("\\")+1,attachTemp.lastIndexOf("."));
	var filename_atr = attachTemp.substring(attachTemp.lastIndexOf("."),attachTemp.length);
	var reg1 = ".xls";
	var reg2 = ".xlsx";
	if((reg1.indexOf(filename_atr)<0) && (reg2.indexOf(filename_atr)<0)){
		layer.alert("请导入excel格式文件！",{icon: 5,title:'提示'});	
		return;
	}
	imp_form.file_type.value =filename_atr;
	layer.confirm('确定导入吗？', {icon: 3,title:'提示'}, function(index){
	    layer.close(index);
	    $("#data_load").disabled=true;
		imp_success=true;
		$.ajaxFileUpload({
			url:ctx+"/m1320/m1320_uploadAndCheck.do",//服务器端程序
			secureuri:false,
			fileElementId:'attach_name',//input框的ID
			dataType: 'json',//返回数据类型
			beforeSend:function(){//上传前需要处理的工作，如显示Loading...
				layer.load(2);
			},
			success: function (data, status){//上传成功
				layer.closeAll('loading');
				if(data.retCode!='0000'){//上传异常，直接返回，不做任何操作
					layer.alert("操作失败【"+data.retMsg+"】！",{icon: 2, title:'提示',end:function(){
						imp_success = false;
						commit_flag=false;
						imp_form.attach_name.disabled=false;
						$('#imp_detail').css("display","none");
					}});
					
				}else{
					var retMsg = data.retMsg;
					imp_success = true;
					imp_form.data_load.value="重新导入";	//导入按钮值变为重新导入
					imp_form.attach_name.disabled="true";//文件选择失效
					layer.alert("导入成功:"+retMsg.success+",导入失败:"+retMsg.fail+",明细请点击导入详情按钮！",{icon: 1, title:'提示'});
					$('#import_msg').html("*导入成功:"+retMsg.success+",导入失败:"+retMsg.fail+",明细请点击导入详情按钮！");
					$('#imp_detail').css("display","inline");
					fail_num = retMsg.fail;
					commit_flag=true;
				} 
			},
			error:function(){
				layer.alert("操作失败,系统调用返回异常！",{icon: 2, title:'提示'});
				layer.closeAll('loading');
			}
		});
	});
		
}
function mubanDownload(){//下载模板到本地
	 this.location = ctx+"/m1320/m1320_downloadModel.do?filename=server_imp_model.xlsx";
		//$.post("c_credit_change_upload.jsp?callMethod=downLoadModel");	
}
function showImp_detail(){
	if(!imp_success){
		layer.alert("请先导入文件！",{icon: 5,title:'提示'});	
		return;
		
	}
	this.location = ctx+"/m1320/m1320_downloadUploadDetail.do";
}
function submit(){
	if(!commit_flag)
		return;
	var note= imp_form.imp_note.value;
	if(note==""||note==null){
			layer.alert("请填写备注！",{icon: 5,title:'提示'});	
			return;	
	 }

	$(imp_form.submit_btn).attr("disabled","true");	
	var confirmVal = "";
	if(fail_num>0)
		confirmVal+=fail_num+"条失败记录将忽略，";
	confirmVal+='确定提交导入文件？';
	layer.confirm(confirmVal, {icon: 3,title:'提示'}, function(index){
		layer.close(index);
		imp_form.submit();
		layer.load(2);
	});
	$(imp_form.submit_btn).removeAttr("disabled");	

}