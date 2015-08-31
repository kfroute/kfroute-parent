function deleteMerchantPayment(id){
	if(confirm("确定删除吗！")){
		$.ajax({
			timeout : 15000,
			async   : true,
			cache   : false,
			type    : 'POST',
			url     : ctx+'/merchantPayment!delete.do',
			dataType: 'json',
			data    : {
				'id': id
			},
			success : function(data){
				flag=true;
				var result=data.result;
				if(result==1){
					loadMerchantPaymentTable();
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

function showUpdateMerchantPayment(id){
	window.open(ctx+"/pages/merchantPayment/addMerchantPayment.jsp?id="+id,"_self");
}


function addMerchant(type){
	window.open(ctx+"/pages/merchantPayment/addMerchantPayment.jsp?id=0","_self");
}


function loadMerchantPaymentTable(){
	var url=ctx+'/merchantPayment!findAll.do';
	$('#merchantPaymentTable').load(url);
}


$(function(){
	loadMerchantPaymentTable();
});
