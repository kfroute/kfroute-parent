$(function(){
	getBoxList();
})

function selectedAll(){
	if($("#allSelected").attr("checked")=="checked"){
		$(".device_td").find("input").attr("checked","checked");
	}else{
		$(".device_td").find("input").attr("checked",false);
	}
}

function slideUp(){
	$("#release_cont_cont").slideUp(500);	
}

function slideDown(){
	$("#release_cont_cont").slideDown(500);
}

function syncStatus(){
	var i=0;
	var boxMacs = new Array();
	$(".device_td").find("input").each(function(){		      
		if($(this).attr("checked")=="checked"){
			boxMacs[i] = $(this).val();
		    i++;
		}
	});
	if(i==0){
		alert("未选择任何设备！");
		return;
	}
	var param = "boxMacs="+boxMacs;
	var url=ctx + '/release!saveStatues.do';
	$.ajax({
		url : url,
		type : 'post',
		data : param,
		dataType : 'json',
		success : function(){
			alert("同步成功！");
			$("#device").hide();
		}
	});
}

function getBoxList(){
	var url=ctx + '/release!queryDeviceList.do';
	$('#boxUl').load(url,function(){
		$("#boxP").text("正在使用这个站点的设备（"+$("#boxHidden").val()+"）个")
		if($("#boxHidden").val()>0){
			$("#diy_noadd").hide();
		}else{
			$("#diy_noadd").show();
		}
	});
}

function singleBox(){
	var url=ctx + '/release!querySingleBox.do';
	$("#device").load(url,function(){

		if($("#singleBoxHidden").val()>0){
			$("#no_device").hide();
		}else{
			$("#no_device").show();
		}
	});	
	$("#device").show();
	$("#device").draggable();
}