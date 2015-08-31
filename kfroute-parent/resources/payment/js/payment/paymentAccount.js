function deletePaymentAccount(id){
	if(confirm("确定删除吗！")){
		$.ajax({
			timeout : 15000,
			async   : true,
			cache   : false,
			type    : 'POST',
			url     : ctx+'/paymentAccount!delete.do',
			dataType: 'json',
			data    : {
				'id': id
			},
			success : function(data){
				flag=true;
				var result=data.result;
				if(result==1){
					loadPaymentAccountTable();
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


function showUpdatePaymentAccount(id){
	window.open(ctx+"/pages/merchantPayment/addPaymentAccount.jsp?id="+id,"_self");
}


function addPaymentAccount(type){
	window.open(ctx+"/pages/merchantPayment/addPaymentAccount.jsp?id=0","_self");
}


function loadPaymentAccountTable(){
	var url=ctx+'/paymentAccount!findAll.do';
	$('#merchantPaymentTable').load(url);
}


$(function(){
	loadPaymentAccountTable();
});

