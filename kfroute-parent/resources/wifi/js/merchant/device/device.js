$(function(){
	showDeviceList();	
})

function showDeviceList(){
	var url=ctx + '/device!getDeviceTable.do';
	$('#deviceTable').load(url,function(){
		$('#wholeDevice').text("全部设备(共"+$('#deviceHidden').val()+"个)");
		if($('#deviceHidden').val()>0){
			$("#diy_noadd").hide();
		}	
	});
	
	//$("#code2").draggable();
}

function showAddDevice(){
	var url=ctx + '/device!showAddDevice.do';
	$("#showAdd").load(url);
	$("#showAdd").show();
}

function closeWindow(){
	$("#showAdd").hide();
}

function addDevice(){
	var url=ctx+'/device!addDevice.do';
	$("#addDeviceForm").attr("action",url)
	$("#addDeviceForm").submit();
}

function showDeviceManager(boxMac){
//	var url = ctx + '/device!getDeviceManager.do';
//	var param = {"device.boxMac" : boxMac };
//	$('body').load(url,param);
	window.open(ctx+"/pages/deviceManager/deviceManage.jsp?boxMac="+boxMac,"_self");
}
