$(function(){
	var boxMac=$('#boxMac').html();
	loadForms(boxMac,0);
	$.ajax({
		timeout : 150000,
		async   : true,
		cache   : false,
		type    : 'POST',
		dataType: 'json',
		data    :{
			'boxMac':boxMac
		},
		url     : ctx+'/device!findBox.do',
		beforeSend: function(){
			$('#mytable').css({ "display": "none" });
		},
		success : function(data){
			$('#mytable').css({ "display": "table" });//这里必须是"table"，而不是"block"，不然依然会出现上述问题
			var result=data.result;
			if(result==1){
				var device=data.device;
				$('#boxNickName').html(device.nickName);
				$('#boxId').val(device.id);
			}else if(result==-1){
				alert("登录超时，请重新登录");
			}else{
				alert("失败");
			}
			
		},
		error : function(){
			alert("失败");
		}
	});
});



function updateDevice(type){
	var id=$('#boxId').val();
	var boxMac=$('#boxMac').html();
	var boxName=$('#boxName').val();
	var boxAddress=$('#boxAddress').val();
	var groupCode=$('#groupCode').val();
	var netMethod=$('#netMethod').val();
	var dns=$('#dnsCode').val();
	var boxWlanIp=$('#boxWlanIp').val();
	var subnetCode=$('#subnetCode').val();		
	if(type==4){
		subnetCode=$('#subnetCode2').val();
	}
	var boxIp=$('#boxIp').val();
	var isPop=$('#isPop').val();
	var netDuration=$('#netDuration').val();
	var ssidPrefix=$('#ssidPrefix').val();
	var ssid=$('#ssid').val();
	var nickName=$('#nickName').val();
	var encryptMethod=$('#encryptMethod').val();
	
	$.ajax({
		timeout : 150000,
		async   : true,
		cache   : false,
		type    : 'POST',
		dataType: 'json',
		data    :{
			'type':type,
			'id':id,
			'boxMac':boxMac,
			'boxName':boxName,
			'boxAddress':boxAddress,
			'groupCode':groupCode,
			'netMethod':netMethod,
			'dns':dns,
			'boxWlanIp':boxWlanIp,
			'subnetCode':subnetCode,
			'boxIp':boxIp,
			'isPop':isPop,
			'netDuration':netDuration,
			'ssidPrefix':ssidPrefix,
			'ssid':ssid,
			'nickName':nickName,
			'encryptMethod':encryptMethod
		},
		url     : ctx+"/device!updateDevice.do",
		success : function(data){
			var result=data.result;
			if(result==1){
				alert("修改成功");
				
			}else if(result==-1){
				alert("登录超时，请重新登录");
			}else{
				alert("失败");
			}
			
		},
		error : function(){
			alert("失败");
		}
	});
	
	
}

function showBasicInformation(){
	
	$('.box').hide();
	$('.box2').hide();
	$('#surfInternet').css('display','none');
	$('#wLan').css('display','none');
	$('#Lan').css('display','none');
	$('#popup').css('display','none');
	$('#onlineTime').css('display','none');
	$('#wLanSet').css('display','none');
	$('#whiteList').css('display','none');
	$('#basicInformation').css('display','block');
}

function showSurfInternet(){
	$('.box').hide();
	$('.box2').hide();
	$('#basicInformation').css('display','none');
	$('#wLan').css('display','none');
	$('#Lan').css('display','none');
	$('#popup').css('display','none');
	$('#onlineTime').css('display','none');
	$('#wLanSet').css('display','none');
	$('#whiteList').css('display','none');
	$('#surfInternet').css('display','block');
}

function showWLan(){
	$('.box').hide();
	$('.box2').hide();
	$('#basicInformation').css('display','none');
	$('#surfInternet').css('display','none');
	$('#Lan').css('display','none');
	$('#popup').css('display','none');
	$('#onlineTime').css('display','none');
	$('#wLanSet').css('display','none');
	$('#whiteList').css('display','none');
	$('#wLan').css('display','block');
}

function showLan(){
	$('.box').hide();
	$('.box2').hide();
	$('#basicInformation').css('display','none');
	$('#surfInternet').css('display','none');
	$('#wLan').css('display','none');
	$('#popup').css('display','none');
	$('#onlineTime').css('display','none');
	$('#wLanSet').css('display','none');
	$('#whiteList').css('display','none');
	$('#Lan').css('display','block');
}

function showPopup(){
	$('.box').hide();
	$('.box2').hide();
	$('#basicInformation').css('display','none');
	$('#surfInternet').css('display','none');
	$('#wLan').css('display','none');
	$('#Lan').css('display','none');
	$('#onlineTime').css('display','none');
	$('#wLanSet').css('display','none');
	$('#whiteList').css('display','none');
	$('#popup').css('display','block');
}

function showOnlineTime(){
	$('.box').hide();
	$('.box2').hide();
	$('#basicInformation').css('display','none');
	$('#surfInternet').css('display','none');
	$('#wLan').css('display','none');
	$('#Lan').css('display','none');
	$('#popup').css('display','none');
	$('#wLanSet').css('display','none');
	$('#whiteList').css('display','none');
	$('#onlineTime').css('display','block');
}

function showWLanSet(){
	$('.box').hide();
	$('.box2').hide();
	$('#basicInformation').css('display','none');
	$('#surfInternet').css('display','none');
	$('#wLan').css('display','none');
	$('#Lan').css('display','none');
	$('#popup').css('display','none');
	$('#onlineTime').css('display','none');
	$('#whiteList').css('display','none');
	$('#wLanSet').css('display','block');
}

function showWhiteList(){
	$('.box').hide();
	$('.box2').hide();
	$('#basicInformation').css('display','none');
	$('#surfInternet').css('display','none');
	$('#wLan').css('display','none');
	$('#Lan').css('display','none');
	$('#popup').css('display','none');
	$('#onlineTime').css('display','none');
	$('#wLanSet').css('display','none');
	$('#whiteList').css('display','block');
}

function loadForms(boxMac,type){
	var url=ctx+'/device!loadForms.do?boxMac='+boxMac;
	$('#allForm').load(url);
}




