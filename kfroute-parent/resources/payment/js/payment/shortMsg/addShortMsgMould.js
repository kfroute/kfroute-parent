$(function(){
	var id=$('#shortMsgId').val();
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
			url     : ctx+'/shortMsg!find.do',
			success : function(data){
				var result=data.result;
				if(result==1){
					var shortMsg=data.shortMsg;
					$('#msgName').val(shortMsg.msgName);
					$('#shortMsg').val(shortMsg.shortMsg);
					if(shortMsg.status==0){
						var html="<option value='0'>未使用</option><option value='1'>正在使用</option>";
						$('#status').html(html);
					}else if(shortMsg.status==1){
						var html="<option value='1'>正在使用</option><option value='0'>未使用</option>";
						$('#status').html(html);
					}
					
				}else{
					alert("获取失败");
				}
			},
			error : function(){
				alert("获取数据失败");
			}
		});
		
		
	}
});


function submitMould(){
	var id=$('#shortMsgId').val();
	var status=$('#status').val();
	var msgName=$('#msgName').val();
	var shortMsg=$('#shortMsg').val();
	if(msgName==""||msgName==null){
		alert("模板名不可为空");
		return;
	}
	if(shortMsg==""||shortMsg==null){
		alert("短信内容不可为空");
		return;
	}
	$.ajax({
		timeout : 150000,
		async   : true,
		cache   : false,
		type    : 'POST',
		dataType: 'json',
		data    :{
			'id':id,
			'status':status,
			'msgName':msgName,
			'shortMsg':shortMsg
		},
		url     : ctx+'/shortMsg!add.do',
		success : function(data){
			var result=data.result;
			if(result==1){
				window.open(ctx+"/pages/shortMsg/shortMsgMould.jsp","_self");
			}else{
				alert("获取失败");
			}
		},
		error : function(){
			alert("获取数据失败");
		}
	});
	
	
}

