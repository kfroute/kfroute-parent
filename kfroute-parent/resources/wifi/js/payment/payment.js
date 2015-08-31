function loadPaymentTable(){
	var url=ctx+'/paymentEntity!findTheAll.do';
	$('#paymentTable').load(url);
}

$(function(){
	loadPaymentTable();
});


function deletePayment(id){
	if(confirm("确定删除吗！")){
		$.ajax({
			timeout : 15000,
			async   : true,
			cache   : false,
			type    : 'POST',
			url     : ctx+'/paymentEntity!delete.do',
			dataType: 'json',
			data    : {
				'id': id
			},
			success : function(data){
				var result=data.result;
				if(result==1){
					loadPaymentTable();
				}else{
					alert("删除失败");
				}
			},
			error   : function(data){
				alert("读取数据失败");
			}
			
		});
	}
}


function showUpdatePayment(id){
	window.open(ctx+"/pages/merchantPayment/addPayment.jsp?id="+id,"_self");
}


function addPayment(type){
	window.open(ctx+"/pages/merchantPayment/addPayment.jsp?id=0","_self");
}
