function closeLoginmodal() {
	$('#lean_overlay').css('display','none');
	var divobj = document.getElementById('loginmodal');
	divobj.style.display = "none";
	$('#fileField').val('');
	$('#username').val('');
}

$(function(){
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/diySite!count.do',
		dataType: 'json',
		data    : {
			
		},
		success : function(data){
			var result=data.result;
			if(result=="1"){
				$("#diy_noadd").css('display','none');
			}
			loadTable();
		},
		error   : function(data){
			alert("读取数据失败");
		}
		
	});
	
});

function loadTable(){
	var url=ctx+"/diySite!findAll.do";
	$('#diy_td').load(url);
}


function addDiySite(){
	var siteName=$('#username').val();
	var filePath=$('#realFilePath').val();
	if(siteName==null||siteName==""){
		alert("请输入站点名称");
		return;
	}
	if(filePath==null||filePath==""){
		alert("请上传文件");
		return;
	}

	
//	var options = {
//      dataType : "json",
//      async: false,
//      data:{
//    	'siteName' : siteName,
//	    'filePath' : filePath
//      },
//      success :showFrontResponse,
//      error:showFrontError,
//      timeout: 30000      //限制请求的时间，当请求大于3秒后，跳出请求
//	};
//	$('#newDiySite').attr('action',ctx+'/diySite!insert.do');
//	jQuery("#newDiySite").ajaxSubmit(options);
//	$('#newDiySite').attr('action','');
	
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/diySite!insert.do',
		dataType: 'json',
		data    : {
			'siteName' : siteName,
			'filePath' : filePath
		},
		success : function(data){
			var result=data.result;
			if(result=="1"){
				alert("创建成功");
				loadTable();
				$("#diy_noadd").css('display','none');
				$('#lean_overlay').css('display','none');
				$("#loginmodal").css('display','none');
			}else{
				alert("创建失败");
			}
		},
		error   : function(data){
			alert("创建失败");
		}
		
	});
	
//	$.ajax({
//	type: "post",
//    url: ctx+'/diySite!insert.do',
//    data: {
//    	'siteName' : siteName,
//    	'filePath' : filePath
//    },
//    dataType: "json",
//    success: function(data){
//    	alert('success');
//    	alert(data.result);
//    }
//});
	
	
}

function uploadTar() {
	
	var _front_img;
	//点击取消的时候 不做处理
	var advertImgName = document.getElementById("file").value;
	if (advertImgName == "" || advertImgName == null) {
		alert("请选择文件！");
		return;
	}
	var file_name = advertImgName
			.substring(advertImgName.lastIndexOf("\\") + 1);
	var filename_atr = advertImgName.substring(
			advertImgName.lastIndexOf(".") + 1, advertImgName.length);
	var reg1 = "tar";
	if ((reg1.indexOf(filename_atr) < 0)) {
		alert("请选择tar格式压缩文件！");
		$('#fileField').val('');
		return;
	}
	var options = {
	      dataType : "json",
	      async: false,
	      success :showFrontResponse,
	      error:showFrontError,
	      timeout: 30000      //限制请求的时间，当请求大于3秒后，跳出请求
	  };
	$('#newDiySite').attr('action',ctx+'/diySite!uploadTar.do');
	jQuery("#newDiySite").ajaxSubmit(options);
	$('#newDiySite').attr('action','');
}


var showFrontResponse = function(data){
	var flag=data.result;
	 if(flag==1){
		 alert("上传成功");
		 $('#realFilePath').val(data.url)
	 }else if(flag==4){
			alert('上传失败，失败原因：文件格式不正确!');
	 }else if(flag==5){
		 	alert('上传失败，失败原因：文件不能超过2M!');
	 }else{
		 	alert('上传失败，请您稍后重试!');
	 }

}
var showFrontError=function(data){
	alert("上传失败");
}


