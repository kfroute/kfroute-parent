$(function(){
	var id=$('#paymentId').val();
	if(id==0||id==""||id==null){
		
	}else{
		$.ajax({
			timeout : 150000,
			async   : true,
			cache   : false,
			type    : 'POST',
			dataType: 'json',
			data    :{
				'id':id
			},
			url     : ctx+'/paymentEntity!find.do',
			success : function(data){
				var paymentEntity=data.paymentEntity;
				$('#paymentCode').val(paymentEntity.paymentCode);
				$('#paymentName').val(paymentEntity.paymentName);
				$('#logoUrl').attr("src",resourcePath+'/image/upload/'+paymentEntity.paymentUrl);
				$('#paymentDesc').html(paymentEntity.paymentDesc);
			},
			error : function(){
				alert("获取数据失败");
			}
		});
		
		
	}
});


function submitPayment(){
	var paymentCode=$('#paymentCode').val();
	var paymentName=$('#paymentName').val();
	var paymentUrl=$('#paymentUrl').val();
	var paymentDesc=$('#paymentDesc').val();
	if(paymentCode==""||paymentName==""){
		alert("关键信息不可为空");
		return;
	}
	if(paymentUrl==""){
		alert("请上传图片");
		return;
	}
	var id=$('#paymentId').val();
	var type="";
	if(id==0||id==""||id==null){
		type="add";
	}else{
		type="update";
	}
	
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		data    : {
			'id':id,
			'type':type,
			'paymentCode':paymentCode,
			'paymentName':paymentName,
			'paymentUrl':paymentUrl,
			'paymentDesc':paymentDesc
		},
		url     : ctx+'/paymentEntity!add.do',
		dataType: 'json',
		success : function(data){
			var result=data.result;
			if(result==1){
				window.open(ctx+"/pages/merchantPayment/payment.jsp","_self");
			}else if(result==0){
				alert("此支付类型已存在，添加失败");
			}
		},
		error   : function(data){
			alert("操作失败");
		}
	});
	
}


function uploadPaymentImgNow(){
	var paymentCode=$('#paymentCode').val();
	var advertImgName = $('#file').val();
	if (advertImgName == "" || advertImgName == null) {
		alert("请选择文件！");
		return;
	}
	var file_name = advertImgName
			.substring(advertImgName.lastIndexOf("\\") + 1);
	var filename_atr = advertImgName.substring(
			advertImgName.lastIndexOf(".") + 1, advertImgName.length);
	var reg1 = "gif,jpg,jpeg,png,bmp,GIF,JPG,JPEG,PNG,BMP";
	if ((reg1.indexOf(filename_atr) < 0)) {
		alert("请选择gif,jpg,jpeg,png,bmp格式的图片文件！");
		$('#file').val('');
		return;
	}
	var options = {
	      dataType : "json",
	      async: false,
	      data :{
	    	  'paymentCode' : paymentCode,
	    	  'filename_atr': filename_atr,
	    	  'filePath'	: ctx
	      },
	      success :showFrontResponse,
	      error:showFrontError,
	      timeout: 30000      //限制请求的时间，当请求大于3秒后，跳出请求
	  };
	$('#user_form').attr('action',ctx+'/paymentEntity!uploadImg.do');
	jQuery("#user_form").ajaxSubmit(options);
	$('#user_form').attr('action','');
	
}



function showFrontResponse(data){
	var url=data.url;
	$('#paymentUrl').val(data.url);
	$("#logoUrl").attr('src',resourcePath+'/image/upload/'+data.url+'?d='+Math.random()); 
}

function showFrontError(){
	alert("上传失败");
}
