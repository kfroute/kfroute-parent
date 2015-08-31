function deleteMerchant(id){
	if(confirm("确定删除吗！")){
		$.ajax({
			timeout : 15000,
			async   : true,
			cache   : false,
			type    : 'POST',
			url     : ctx+'/merchant!delete.do',
			dataType: 'json',
			data    : {
				'id': id
			},
			success : function(data){
				flag=true;
				var result=data.result;
				if(result==1){
					loadMerchantTable();
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

function showUpdateMerchant(id){
	window.open(ctx+"/pages/merchantPayment/addMerchant.jsp?id="+id,"_self");
}


function addMerchant(type){
	window.open(ctx+"/pages/merchantPayment/addMerchant.jsp?id=0","_self");
}


function loadMerchantTable(){
	var url=ctx+'/merchant!findAll.do';
	$('#merchantTable').load(url);
}


$(function(){
	loadMerchantTable();
});
