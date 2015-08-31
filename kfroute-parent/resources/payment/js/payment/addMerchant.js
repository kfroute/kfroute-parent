var merchant;
$(function(){
	var id=$('#merchantId').val();
	loadProvince();
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
			url     : ctx+'/merchant!find.do',
			success : function(data){
				var result=data.result;
				if(result==1){
					merchant=data.merchant;
					loadLatn2(merchant.provinceId);
					$('#merchantCode').val(merchant.merchantCode);
					$('#merchantName').val(merchant.merchantName);
					$('#merchantAddr').val(merchant.merchantAddr);
					$('#linkInfo').val(merchant.linkInfo);
					$('#manage').val(merchant.manage);
					$('#status').val(merchant.status);
					window.setTimeout(changeCity(merchant),5000);
					//$('#provinceId').val(merchant.provinceId);
					//$('#latnInfo').val(merchant.latnInfo);
					
				}else{
					alert("获取数据失败，请检查网络连接");
				}
				
			},
			error : function(){
				alert("获取数据失败");
			}
		});
		
		
	}
});

window.setTimeout(changeCity,1000);

function changeCity(){
	if(merchant!=null||merchant!=undefined){
		$('#provinceId').val(merchant.provinceId);
		$('#latnInfo').val(merchant.latnId);
	}
}

function loadProvince(){
	var url=ctx+'/latn!findAll.do';
	$('#province').load(url);
	loadLatn();
}

function loadLatn(){
	var aiid=$('#provinceId option:selected').val();
	if(aiid==""||aiid==null||aiid==undefined){
		aiid=-2;
	}
	var url=ctx+'/latn!findList.do';
	$('#latn').load(url,{"aiid":aiid});
}

function loadLatn2(aiid){
	var url=ctx+'/latn!findList.do';
	$('#latn').load(url,{"aiid":aiid});
}


function submitMerchant(){
	var merchantCode=$('#merchantCode').val();
	var merchantName=$('#merchantName').val();
	var merchantAddr=$('#merchantAddr').val();
	var linkInfo=$('#linkInfo').val();
	var manage=$('#manage').val();
	var provinceId=$('#provinceId option:selected').val();
	var latnInfo=$('#latnInfo option:selected').val();
	var status=$('#status').val();
	
	var id=$('#merchantId').val();
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
			'merchantCode':merchantCode,
			'merchantName':merchantName,
			'merchantAddr':merchantAddr,
			'linkInfo':linkInfo,
			'manage':manage,
			'provinceId':provinceId,
			'latnInfo':latnInfo,
			'status':status
			
		},
		url     : ctx+'/merchant!add.do',
		dataType: 'json',
		success : function(data){
			var result=data.result;
			if(result==1){
				window.open(ctx+"/pages/merchantPayment/merchant.jsp","_self");
			}else if(result==0){
				alert("商户编码已存在，添加失败");
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
