
function laodTable(){
	var url=ctx + '/tables!getTableList.do';
	$('#tabal_room').load(url);
	//$("#code2").draggable();
}


function loadQrCodes(){
	var url=ctx + '/tables!getQrList.do';
	$('#tr_code').load(url);
}

var tableList;
var index = 0;
var lengthIndex = 0;
function initQrcode(){
	var url=ctx + '/tables!queryTableList.do';
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		success : function(data){
			tableList = data.tableList;
			for(var i=0;i<2;i++){
				$('#tr_code ul').append("<li><div style=\"align:center; margin-top:10px\" id=\"tr_code_cont\">"+
						"<div style=\"align:center;margin-left:24.7%;margin-top:10%\" id=\"tr_code_cont_"+i+"\" class=\"allQrcode\" content=\""+tableList[i].qrCodeUrl+"\" >"+
						"</div><h1>"+tableList[i].tableName+"</h1><p>"+tableList[i].qrCodeUrl+"</p></div></li>")
			}
			$('.allQrcode').each(function(){
				var content=$(this).attr("content");
				$(this).qrcode({
					render: "table", //table
					width: 200, //宽度
					height:200, //高度
					text: content //内容
				});
			})
			index=1;
			lengthIndex = 1;
		}
	});	
}

function crtQrcode(){
	lengthIndex = 0;
	index = 0;
	tableList = 0;
	initQrcode();
	var $wd = $(window),
	    $img = $('img'),
	    imgTop,          //图片距离顶部高度
	    scTop,             //滚动条高度
	    wH;
	wH = $wd.height();  //获得可视浏览器的高度
	$wd.scroll( function() {
		scTop = $wd.scrollTop();  //获取滚动条到顶部的垂直高度	
		if(scTop>50+lengthIndex*100){
			for(var i=index+1;i<=index+2;i++){
				if(i>tableList.length-1){
					index++;
					return;
				}
				$('#tr_code ul').append("<li><div style=\"align:center; margin-top:10px\" id=\"tr_code_cont\">"+
						"<div style=\"align:center;margin-left:24.7%;margin-top:10%\" id=\"tr_code_cont_"+i+"\" class=\"allQrcode\" content=\""+tableList[i].qrCodeUrl+"\" >"+
						"</div><h1>"+tableList[i].tableName+"</h1><p>"+tableList[i].qrCodeUrl+"</p></div></li>")
			    var content = $('#tr_code_cont_'+i).attr("content");
				$('#tr_code_cont_'+i).qrcode({
					render: "table", //table
					width: 200, //宽度
					height:200, //高度
					text: content //内容
				});
			}
			index = index+2;
			lengthIndex = lengthIndex +2;
		}
	});
	
	
		/*$('.allQrcode').each(function(){
			imgTop =  $(this).offset().top;
			if(imgTop - wH < scTop &&     //图片必须出现在窗口底部上面
	                imgTop - wH > 0 &&        //排除首页图片
	                $(this).attr('src') != $(this).data('url')){          //排除已经加载过的图片
				var content=$(this).attr("content");
				$(this).qrcode({
					render: "table", //table
					width: 256, //宽度
					height:256, //高度
					text: content //内容
				});
	          }
		  });*/

}

var editFlag = false;
var deleteFlag = false;
function showTable(id,name){
	editFlag = false;
	deleteFlag = false;
	$("#showTable").val(name);
	$("#code2 h1").text("桌号编辑");
	$("#deleteButton").show();
	$("#code2").show();
	$(".code2_cont tr:eq(1) a:eq(0)").unbind().bind("click",function(){
        var tableName = $("#showTable").val();
        var param = "id="+id+"&tableName="+tableName;
    	var url=ctx + '/tables!updateTable.do';
    	if(editFlag == false){
    		editFlag = true;
    		$.ajax({
        		url : url,
        		type : 'post',
        		data : param,
        		dataType : 'json',
        		success : function(flag){
        			editFlag = false;
        			if(flag==false){
        				document.all.code2.style.display='none';
        				//$('#code2').hide();
        				alert("当前未登录，请先登录！")
        			}else if(flag==2){
        				alert("该桌号已存在！请重新设置！")
        			}else{
        				$('#code2').hide();
    					laodTable();
        			}
        		}
        	});
    	}else{
    		return;
    	}
    	
	});
	$(".code2_cont tr:eq(1) a:eq(1)").unbind().bind("click",function(){
	    var param = "id="+id;
    	var url=ctx + '/tables!deleteTable.do';
    	if(deleteFlag == false){
    		deleteFlag = true;
    		$.ajax({
        		url : url,
        		type : 'post',
        		data : param,
        		dataType : 'json',
        		success : function(flag){
        			deleteFlag = false;
        			if(flag=="false"){
        				$('#code2').hide();
        				alert("当前未登录，请先登录！")
        			}else{
        				$('#code2').hide();
    					laodTable();
        			}
    			}
        	})
    	}else{
    		return;
    	}
    	
	})
}

var addFlag = false;
function addTable(){
	addFlag = false;
	$("#code2 h1").text("添加桌号");
	$(".code2_cont tr:eq(1) a:eq(1)").hide();
	$("#code2").show();
	var url=ctx + '/tables!getMaxNumber.do';
	var maxNum;	
	$.ajax({
		url : url,
		type : 'get',
		dataType : 'json',
		success : function(flag){
			if(flag=="false"){
				alert("当前未登录！请先登录！")
			}else{
				maxNum=flag+1;
				$("#showTable").val(maxNum+"号桌");
			}
		}
	})	
	
	$(".code2_cont tr:eq(1) a:eq(0)").unbind().bind("click",function(){
		var tableName = $("#showTable").val();
	    var param = "tableName="+tableName;
    	var url=ctx + '/tables!addTable.do';
    	if(addFlag == false){
    		addFlag = true;
    		$.ajax({
        		url : url,
        		type : 'post',
        		data : param,
        		dataType : 'json',
        		success : function(data){
        			addFlag == false;
        			if(data.flag=="false"){    				
        				alert("当前未登录，请先登录！");
        				$('#code2').hide();
        			}else if(data.flag==2){
        				alert("该桌号已存在！请重新添加！");
        			}else{
        				$('.table_seletor').hide();
    					laodTable();
        			}
    			}
        	})
    	}else{
    		return;
    	}
    	
	})
}
