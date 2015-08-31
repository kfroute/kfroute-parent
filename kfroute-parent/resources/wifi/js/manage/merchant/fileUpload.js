function fileUploadNow(){
	var fileType=$('#fileType option:selected').val();
	var advertImgName = $('#file').val();
	if (advertImgName == "" || advertImgName == null) {
		alert("请选择文件！");
		return;
	}
	var file_name = advertImgName
			.substring(advertImgName.lastIndexOf("\\") + 1);
	var filename_atr = advertImgName.substring(
			advertImgName.lastIndexOf(".") + 1, advertImgName.length);
	var options = {
	      dataType : "json",
	      async: false,
	      data :{
	    	  'filename_atr': filename_atr,
	    	  'filePath'	: ctx,
	    	  'fileType'	: fileType
	      },
	      success :showFrontResponse,
	      error:showFrontError,
	      timeout: 30000      //限制请求的时间，当请求大于3秒后，跳出请求
	  };
	$('#fileForm').attr('action',ctx+'/fileUpload!uploadFile.do');
	jQuery("#fileForm").ajaxSubmit(options);
	$('#fileForm').attr('action','');
	
	
}

function showFrontResponse(data){
	alert("上传成功");
	$('#file').val('');
	loadTable();
}

function showFrontError(){
	alert("上传失败");
}

$(function(){
	loadTable();
});

function loadTable(){
	var url=ctx+'/fileUpload!findAll.do';
	$('#tableDiv').load(url);
}
