function selectPaymentMode(type){
	$('#paymentPlatform').val(type);
}

function submitData(){
	var orderNumber=$('#orderNumber').val();
	var merchantName=$('#merchantName').val();
	var totalPrice=$('#totalPrice').val();
	var merchantCode=$('#merchantCode').val();
	var paymentPlatform=$('#paymentPlatform').val();
	if(paymentPlatform==""){
		alert("请选择支付方式");
		return;
	}
	$('#postForm').submit();
	
}




