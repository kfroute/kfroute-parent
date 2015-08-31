function loadShortMsgTableTable(){
	var url=ctx+'/shortMsg!findAll.do';
	$('#shortMsgTable').load(url);
}

$(function(){
	loadShortMsgTableTable();
});

function deleteShortMsgMould(id){
	if(confirm("确定删除吗！")){
		$.ajax({
			timeout : 15000,
			async   : true,
			cache   : false,
			type    : 'POST',
			url     : ctx+'/shortMsg!delete.do',
			dataType: 'json',
			data    : {
				'id': id
			},
			success : function(data){
				flag=true;
				var result=data.result;
				if(result==1){
					loadShortMsgTableTable();
				}else{
					alert("删除失败");
				}
			},
			error   : function(data){
				flag=true;
				alert("读取数据失败");
			}
			
		});
	}
}

function showUpdateShortMsgMould(id){
	window.open(ctx+"/pages/shortMsg/addShortMsgMould.jsp?id="+id,"_self");
}


function addShortMsgMould(type){
	window.open(ctx+"/pages/shortMsg/addShortMsgMould.jsp?id=0","_self");
}

function updateStatus(id,status){
	alert("修改状态");
	
}

