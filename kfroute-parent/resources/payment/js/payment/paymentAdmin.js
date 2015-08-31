
function checkAllMerchant(){
	if($('#allMerchant').is(':checked')){
		var obj=document.getElementsByName('merchantCode');
		for(var i=0;i<obj.length;i++){
			obj[i].checked=true;
		}
		return false;
	}else{
		$('[name="merchantCode"]').removeAttr("checked");
		return false;
	}
	
}


function checkAllPayment(){
	if($('#allPayment').is(':checked')){
		var obj=document.getElementsByName('paymentName');
		for(var i=0;i<obj.length;i++){
			obj[i].checked=true;
		}
		return false;
	}else{
		$('[name="paymentName"]').removeAttr("checked");
		return false;
	}
	
}