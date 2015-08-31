$(function(){
	var status=$('#payStatus').val();
	if(status=="success"){
		
	}else if(status=="secrityRisk"){
		$('#successTotalPrice').css('display','none');
		$('#message').html("交易失败");
		$('#successImg').attr('src',resourcePath+'/image/payment/ico-wrong.png');
	}else{
		$('#successTotalPrice').css('display','none');
		$('#message').html("支付失败");
		$('#successImg').attr('src',resourcePath+'/image/payment/ico-wrong1.png');
	}
	
});