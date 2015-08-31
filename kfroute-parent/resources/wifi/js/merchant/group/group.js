$(function(){
	$("#tianjia").css("display","none");
	
	getGroupList();
})

function showEdit(groupName,comments,groupCode){
	saveFlag=false;
	$(".tianjia_top h1").text("编辑设备群组");
	$("#groupButton").text("保存修改");
	$("#groupButton").attr("onclick","saveEdit()");
	$("#groupName").val(groupName);
	$("#groupComments").val(comments);
	$("#groupCode").val(groupCode);
	$("#tianjia").show();
}

var saveFlag=false;
function saveEdit(){
	var groupName = $("#groupName").val();
	var comments = $("#groupComments").val();
	var groupCode = $("#groupCode").val();
	var param = "group.groupName="+groupName+"&group.comments="+comments+"&group.groupCode="+groupCode;
	var url=ctx + '/device!saveEdit.do';
 	if(saveFlag==false){
 		saveFlag = true;
 		$.ajax({
 	 		url : url,
 	 		type : 'post',
 	 		data : param,
 	 		dataType : 'json',
 	 		success : function(flag){
 	 			if(flag==true){
 	 				alert("修改成功！");
 	 				saveFlag = false;
 	 				$("#tianjia").css("display","none");
 	 				window.location.reload();
 	 			}
 	 		}
 	 	});
 	}else{
 		return;
 	}
}

function getGroupList(){
	var url=ctx + '/device!getGroupList.do';
	$('#groupList').load(url,function(){
		$("#diy_td").show();
		if($('#groupHidden').val()>0){
			$("#diy_noadd").hide();
		}	
	});
}
function showDelete(groupCode){
	deleteFlag = false;
	$("#code2").show();
	$("#deleteHidden").val(groupCode);
}

function closeDelete(){
	$("#code2").hide();
}

var deleteFlag = false;
function deleteGroup(){
	var groupCode = $("#deleteHidden").val();
	var param = "group.groupCode="+groupCode;
 	var url=ctx + '/device!deleteGroup.do';
 	if(deleteFlag==false){
 		deleteFlag = true;
 		$.ajax({
 	 		url : url,
 	 		type : 'post',
 	 		data : param,
 	 		dataType : 'json',
 	 		success : function(flag){
 	 			if(flag==true){
 	 				alert("删除成功！");
 	 				deleteFlag = false;
 	 				$("#code2").hide();
 	 				window.location.reload();
 	 			}
 	 		}
 	 	});
 	}else{
 		return;
 	}
}


function showAddGroup(){
	
	$(".tianjia_top h1").text("新建设备群组");
	$("#groupButton").text("新建群组");
	$("#groupButton").attr("onclick","newGroup()");
	$("#tianjia").show();
	totalFlag = false;
}

function closeWindow(){
	$("#tianjia").css("display","none");
}


var totalFlag = false;
function newGroup(){
	var name = $("#groupName").val();
	var comments = $("#groupComments").val();
	var param = "group.groupName="+name+"&group.comments="+comments;
 	var url=ctx + '/device!saveGroup.do';
 	if(totalFlag==false){
 		totalFlag = true;
 		$.ajax({
 	 		url : url,
 	 		type : 'post',
 	 		data : param,
 	 		dataType : 'json',
 	 		success : function(flag){
 	 			if(flag==true){
 	 				alert("添加成功！");
 	 				totalFlag = false;
 	 				$("#tianjia").css("display","none");
 	 				window.location.reload();
 	 			}
 	 		}
 	 	});
 	}else{
 		return;
 	}
 	
}