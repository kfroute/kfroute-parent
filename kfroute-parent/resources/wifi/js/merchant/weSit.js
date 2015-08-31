$(function(){
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/merchant!find.do',
		dataType: 'json',
		success : function(data){
			var result=data.result;
			if(result==1){
				if(typeof(data.realFilePath)!='undefined'){
					$("#logoImg").attr('src',resourcePath+'/'+data.realFilePath+'?rnd='+Math.random());
				}else{
					$("#logoImg").attr('src',resourcePath+'/image/merchant/logo.jpg');
				}
				$('#logoImgShow').attr('src',data.url);
				getMsg();
			}else if(result==0){
				alert("登录超时，请重新登录");
			}
			
			
		},
		error   : function(data){
		}
	});
});


function logoUpload(){
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
	    	  'filename_atr': filename_atr,
	    	  'filePath'	: ctx
	      },
	      success :showFrontResponse,
	      error:showFrontError,
	      timeout: 30000      //限制请求的时间，当请求大于3秒后，跳出请求
	  };
	$('#uploadImg').attr('action',ctx+'/merchant!uploadImg.do');
	jQuery("#uploadImg").ajaxSubmit(options);
	$('#uploadImg').attr('action','');
	
	
}

function showFrontResponse(data){
	$("#logoImg").attr('src',resourcePath+'/'+data.url+'?d='+Math.random()); 
}

function showFrontError(){
	alert("上传失败");
}

function getMsg(){
	$.ajax({
		timeout : 150000,
		async   : true,
		cache   : false,
		type    : 'POST',
		dataType: 'json',
		url     : ctx+'/merchant!find.do',
		success : function(data){
			$('#shopsName').val(data.shopsName);
			$('#openingTime').val(data.openingTime);
			$('#telephone').val(data.telephone);
			$('#city').val(data.city);
			$('#address').val(data.address);
			$('#introduce').val(data.introduce);
			$('#realFilePath').val(data.realFilePath);
			$('#shopsNameShow').html(data.shopsName);
			$('#openingTimeShow').html(data.openingTime);
			$('#telephoneShow').html(data.telephone);
			$('#addressShow').html(data.address);
			$('#introduceShow').html(data.introduce);
		},
		error : function(){
			
		}
	});
	
}


function updateMsg(){
	var shopsName=$('#shopsName').val();
	var openingTime=$('#openingTime').val();
	var telephone=$('#telephone').val();
	var city=$('#city').val();
	var address=$('#address').val();
	var introduce=$('#introduce').val();
	var realFilePath=$('#realFilePath').val();
	$.ajax({
		timeout : 150000,
		async   : true,
		cache   : false,
		type    : 'POST',
		dataType: 'json',
		url     : ctx+'/merchant!msgUpdate.do',
		data    : {
			'shopsName' : shopsName,
			'openingTime': openingTime,
			'telephone' : telephone,
			'city' : city,
			'address' : address,
			'introduce' : introduce,
			'realFilePath': realFilePath
		},
		success : function(data){
			var result=data.result;
			if(result==-1){
				alert("登录超时，请重新登录");
			}else{
				alert("修改成功");
				$('#shopsName').val(data.shopsName);
				$('#openingTime').val(data.openingTime);
				$('#telephone').val(data.telephone);
				$('#city').val(data.city);
				$('#address').val(data.address);
				$('#introduce').val(data.introduce);
				$('#realFilePath').val(data.realFilePath);
				$('#shopsNameShow').html(data.shopsName);
				$('#openingTimeShow').html(data.openingTime);
				$('#telephoneShow').html(data.telephone);
				$('#addressShow').html(data.address);
				$('#introduceShow').html(data.introduce);
				document.all.ly.style.display='none';
				document.all.Layer2.style.display='none';
				return false;
			}
		},
		error : function(){
			alert("修改失败");
		}
		
	});
	
}

function loadActivity(){
	$.ajax({
		timeout : 150000,
		async   : true,
		cache   : false,
		type    : 'POST',
		dataType: 'json',
		url     : ctx+'/activity!find.do',
		success : function(data){
			var result=data.result;
			if(result==-1){
				alert("登录超时，请重新登录");
			}else if(result==1){
				var obj=data.obj;
				if(obj.discount==100){
					$('#uniteDiscount').html('不打折');
				}else{
					var str=obj.discount+'折';
					$('#uniteDiscount').html(str);
				}
				$('#newActivity').text(obj.activityDesc);
				$('#discount').val(obj.discount);
				if(obj.isReduce==1){
					$('#isReduce').attr("checked","checked");
					if(obj.reduceType==0){
						$('#reduceScheme').html('不满减满返');
					}else if(obj.reduceType==1){
						$('#reduceScheme').html('满'+obj.reduceTotal+'元，减'+obj.reduceAmount+'元');
					}else if(obj.reduceType==2){
						$('#reduceScheme').html('满'+obj.reduceTotal+'元，返'+obj.reduceAmount+'元现金券');
					}
				}else if(obj.isReduce==0){
					$('#reduceScheme').html('不满减满返');
				}
				if(obj.reduceType==0){
					$('#reduceType0').attr("selected",true);
				}else if(obj.reduceType==1){
					$('#reduceType1').attr("selected",true);
				}else if(obj.reduceType==2){
					$('#reduceType2').attr("selected",true);
				}
				$('#activityDesc').val(obj.activityDesc);
				if(obj.picUrl!=null&&obj.picUrl!=""){
					$("#activityImg").attr('src',resourcePath+'/'+obj.picUrl+'?d='+Math.random());
				}
				
			}else if(result==0){
				
			}
			
		},
		error : function(){
			
		}
	});
	
}


function updateActivity(){
	var discount=$('#discount').val();
	var isReduce=0;
	if ($("#isReduce").get(0).checked){
	    isReduce=1;
	}
	var reduceTotal=$('#reduceTotal').val();
	var reduceType=$('#reduceType option:selected').text();
	var reduceAmount=$('#reduceAmount').val();
	var activityDesc=$('#activityDesc').val();
	$.ajax({
		timeout : 150000,
		async   : true,
		cache   : false,
		type    : 'POST',
		data    : {
			'discount':discount,
			'isReduce':isReduce,
			'reduceTotal':reduceTotal,
			'reduceType':reduceType,
			'reduceAmount':reduceAmount,
			'activityDesc':activityDesc
		},
		dataType: 'json',
		url     : ctx+'/activity!update.do',
		success : function(data){
			var result=data.result;
			if(result==-1){
				alert("登录超时，请重新登录");
			}else if(result==1){
				var obj=data.obj;
				if(obj.discount==100){
					$('#uniteDiscount').html('不打折');
				}else{
					var str=obj.discount+'折';
					$('#uniteDiscount').html(str);
				}
				$('#newActivity').text(obj.activityDesc);
				$('#discount').val(obj.discount);
				if(obj.isReduce==1){
					$('#isReduce').attr("checked","checked");
					if(obj.reduceType==0){
						$('#reduceScheme').html('不满减满返');
					}else if(obj.reduceType==1){
						$('#reduceScheme').html('满'+obj.reduceTotal+'元，减'+obj.reduceAmount+'元');
					}else if(obj.reduceType==2){
						$('#reduceScheme').html('满'+obj.reduceTotal+'元，返'+obj.reduceAmount+'元现金券');
					}
				}else if(obj.isReduce==0){
					$('#reduceScheme').html('不满减满返');
				}
				if(obj.reduceType==0){
					$('#reduceType0').attr("selected",true);
				}else if(obj.reduceType==1){
					$('#reduceType1').attr("selected",true);
				}else if(obj.reduceType==2){
					$('#reduceType2').attr("selected",true);
				}
				$('#activityDesc').val(obj.activityDesc);
				document.all.ly.style.display='none';
				document.all.promotion.style.display='none';
				return false;
			}
			
		},
		error : function(){
			alert("获取信息失败");
		}
	});
	
	
}

function uploadActivityImgNow(){
	var advertImgName = $('#file1').val();
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
		$('#file1').val('');
		return;
	}
	var options = {
	      dataType : "json",
	      async: false,
	      data :{
	    	  'filename_atr': filename_atr,
	    	  'filePath'	: ctx
	      },
	      success :showFrontResponse1,
	      error:showFrontError,
	      timeout: 30000      //限制请求的时间，当请求大于3秒后，跳出请求
	  };
	$('#uploadActivityImg').attr('action',ctx+'/activity!uploadImg.do');
	jQuery("#uploadActivityImg").ajaxSubmit(options);
	$('#uploadActivityImg').attr('action','');
}

function showFrontResponse1(data){
	$("#activityImg").attr('src',resourcePath+'/'+data.url+'?d='+Math.random());
}




