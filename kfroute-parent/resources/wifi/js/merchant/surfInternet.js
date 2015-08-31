function updateSurf() {
	var theTitle = $('#theTitle').val();
	var copyRight = $('#copyRight').val();
	var jumpHref=$('#jumpHref').val();
	var smsStatus=$("#smsStatus  option:selected").val();
	if(smsStatus=="开启"){
		smsStatus=1;
	}else if(smsStatus=="关闭"){
		smsStatus=0;
	}
	var weiChartStatus=$('#weiChartStatus').val();
	if(weiChartStatus=="开启"){
		weiChartStatus=1;
	}else if(weiChartStatus=="关闭"){
		weiChartStatus=0;
	}
	var weiChartApp=$('#weiChartApp').val();
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/surfInternet!update.do',
		dataType: 'json',
		data    : {
			'theTitle':theTitle,
			'copyRight':copyRight,
			'jumpHref':jumpHref,
			'smsStatus':smsStatus,
			'weiChartStatus':weiChartStatus,
			'weiChartApp':weiChartApp
		},
		success : function(data){
			var result=data.result;
			if(result==-1){
				alert("登录超时，请重新登录");
			}else if(result==1){
				alert("修改成功");
			}else{
				alert("修改失败");
			}
			
			
		},
		error   : function(data){
			alert("修改失败");
		}
	});
}

function findSurf(){
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/surfInternet!add.do',
		dataType: 'json',
		success : function(data){
			var result=data.result;
			if(result==-1){
				alert("登录超时，请重新登录");
				return;
			}
		},
		error   : function(data){
			
		}
	});
}

function uploadImg(seq){
	var fileStr="file"+seq;
	var advertImgName = $('#'+fileStr).val();
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
	    	  'filename_atr': filename_atr,
	    	  'filePath'	: ctx,
	    	  'seq'	: seq
	      },
	      success :showFrontResponse,
	      error:showFrontError,
	      timeout: 30000   //限制请求的时间，当请求大于3秒后，跳出请求
	  };
	$('#uploadSurfImg').attr('action',ctx+'/surfInternet!uploadImg.do');
	jQuery("#uploadSurfImg").ajaxSubmit(options);
	$('#uploadSurfImg').attr('action','');
	
	
}

function showFrontResponse(data){
	var HTML='<img src="'+resourcePath+'/'+data.url+'?d='+Math.random()+'" width="285" height="380" />'+
		'<div class="yes_upload_bottom">'+
		'<a href="javascript:void(0);" class="genghuan">'+
			'<input type="file" name="file'+data.seq+'" id="file'+data.seq+'" onchange="uploadImg('+data.seq+');">'+
			'</a>'+
			'<a href="javascript:void(0);" class="bainji"></a>'+
			'<a href="javascript:void(0);" onclick="deleteImg('+data.seq+');" class="shanchu"><img src="'+resourcePath +'/image/merchant/yichu.png" width="58" height="23" /></a>'+
		'</div>';
	$('#img'+data.seq).css("background","url(none)");
	$('#img'+data.seq).attr("class","yes_upload");
	$('#img'+data.seq).html(HTML);
	
//	修改DIV的元素，并把图片路径换成上传后图片的路径
//	$($('#divClass').val()).css("background","url(none)");
//	$($('#divClass').val()).css("background","url("+resourcePath+'/'+data.url+'?d='+Math.random()+")");

}

function showFrontError(){
	alert("上传失败");
}
	
$(function(){
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/surfInternet!findPicture.do',
		dataType: 'json',
		success : function(data){
			var pictureList=data.pictureList;
			var img1="img1";
			var img2="img2";
			var img3="img3";
			var img4="img4";
			for(var i=0;i<pictureList.length;i++){
				if(pictureList[i].seq==1){
					img1=pictureList[i].picUrl;
				}else if(pictureList[i].seq==2){
					img2=pictureList[i].picUrl;
				}else if(pictureList[i].seq==3){
					img3=pictureList[i].picUrl;
				}else if(pictureList[i].seq==4){
					img4=pictureList[i].picUrl;
				}
			}
			
			if(img1!="img1"&&img1!=null){
				var HTML='<img src="'+resourcePath+'/'+img1+'?d='+Math.random()+'" width="285" height="380" />'+
				'<div class="yes_upload_bottom">'+
				'<a href="javascript:void(0);" class="genghuan">'+
					'<input type="file" name="file1" id="file1" onchange="uploadImg(1);">'+
					'</a>'+
					'<a href="javascript:void(0);" class="bainji"></a>'+
					'<a href="javascript:void(0);" onclick="deleteImg(1);" class="shanchu"><img src="'+resourcePath +'/image/merchant/yichu.png" width="58" height="23" /></a>'+
				'</div>';
				$('#img1').css("background","url(none)");
				$('#img1').attr("class","yes_upload");
				$('#img1').html(HTML);
			}
			if(img2!="img2"&&img2!=null){
				var HTML='<img src="'+resourcePath+'/'+img2+'?d='+Math.random()+'" width="285" height="380" />'+
				'<div class="yes_upload_bottom">'+
				'<a href="javascript:void(0);" class="genghuan">'+
					'<input type="file" name="file2" id="file2" onchange="uploadImg(2);">'+
					'</a>'+
					'<a href="javascript:void(0);" class="bainji"></a>'+
					'<a href="javascript:void(0);" onclick="deleteImg(2);" class="shanchu"><img src="'+resourcePath +'/image/merchant/yichu.png" width="58" height="23" /></a>'+
				'</div>';
				$('#img2').css("background","url(none)");
				$('#img2').attr("class","yes_upload");
				$('#img2').html(HTML);
			}
			if(img3!="img3"&&img3!=null){
				var HTML='<img src="'+resourcePath+'/'+img3+'?d='+Math.random()+'" width="285" height="380" />'+
				'<div class="yes_upload_bottom">'+
				'<a href="javascript:void(0);" class="genghuan">'+
					'<input type="file" name="file3" id="file3" onchange="uploadImg(3);">'+
					'</a>'+
					'<a href="javascript:void(0);" class="bainji"></a>'+
					'<a href="javascript:void(0);" onclick="deleteImg(3);" class="shanchu"><img src="'+resourcePath +'/image/merchant/yichu.png" width="58" height="23" /></a>'+
				'</div>';
				$('#img3').css("background","url(none)");
				$('#img3').attr("class","yes_upload");
				$('#img3').html(HTML);
			}
			if(img4!="img4"&&img4!=null){
				var HTML='<img src="'+resourcePath+'/'+img4+'?d='+Math.random()+'" width="285" height="380" />'+
				'<div class="yes_upload_bottom">'+
				'<a href="javascript:void(0);" class="genghuan">'+
					'<input type="file" name="file4" id="file4" onchange="uploadImg(4);">'+
					'</a>'+
					'<a href="javascript:void(0);" class="bainji"></a>'+
					'<a href="javascript:void(0);" onclick="deleteImg(4);" class="shanchu"><img src="'+resourcePath +'/image/merchant/yichu.png" width="58" height="23" /></a>'+
				'</div>';
				$('#img4').css("background","url(none)");
				$('#img4').attr("class","yes_upload");
				$('#img4').html(HTML);
			}
			
			
		},
		error   : function(data){
			
		}
	});
	
});

function deleteImg(seq){
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/surfInternet!delete.do',
		data    : {
			'seq':seq
		},
		dataType: 'json',
		success : function(data){
			var result=data.result;
			if(result==-1){
				alert("登录超时，请重新登录");
				return;
			}else if(result==1){
				alert("删除成功");
				var HTML='<div id="img1" class="no_upload">'+
					'<p>点击+上传图片</p>'+
					'<p>建议上传宽高比为3:4尺寸的图片</p>'+
					'<a href="javascript:void(0);" class="no_upload_file">'+
					    '<input class="no_upload_file" type="file" name="file1" id="file1" onchange="uploadImg(1);">'+
					'</a>'+
				'</div>';
				$('#img'+data.seq).html('');
				$('#img'+data.seq).css("background","url(none)");
				$('#img'+data.seq).attr("class","yes_upload");
				$('#img'+data.seq).html(HTML);
				
				
			}
		},
		error   : function(data){
			
		}
	});
	
	
}





