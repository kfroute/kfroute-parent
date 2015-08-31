var table_log;

$(function(){	
	
	table_log = $("#log_table").myDataTable({
		sAjaxSource:ctx+"/updateLog!queryLogList.do",
		paramSelector:"#merchantName,#boxMac,#status",
		aoColumns : [   
                     {},
                     {},
                     {},
                     {},
                     {},
                     {}
				]
	});
	
	$("#query_btn").on("click",function(){
		table_log.fnPageChange("first",true);
	});
});


function addMerchant(type){
	window.open(ctx+"/pages/merchant/editUser.jsp?id="+type,"_self");
}

function deleteMerchant(id){
	if(confirm("确定删除吗？")){
		$.ajax({
			timeout : 150000,
			async   : true,
			cache   : false,
			type    : 'POST',
			dataType: 'json',
			data    :{
				'id':id
			},
			url     : ctx+'/merchant!delete.do',
			success : function(data){
				var result=data.result;
				if(result==1){
					alert("删除成功");
				}else{
					alert("删除失败");
				}
				
			},
			error : function(){
				alert("删除失败");
			}
		});
	}
}

