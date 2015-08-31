$(function(){
	var id=$('#merchantId').val();
	if(id==0||id==""||id==null){
		loadPaymentEntitySelect(id);
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
			url     : ctx+'/merchantPayment!find.do',
			success : function(data){
				var result=data.result;
				var merchantPayment=data.merchantPayment;
				$('#merchantCode').val(merchantPayment.merchantCode);
				$('#merchantName').val(merchantPayment.merchantName);
				$('#paymentAccount').val(merchantPayment.paymentAccount);
				var paymentAccount=data.paymentAccount;
				$('#relationAccount').val(paymentAccount.relationAccount);
				$('#security').val(paymentAccount.security);
				if(paymentAccount.paymentType==1){
					var HTML='<option value="1" selected>货到付款</option>'+
						'<option value="2">减免费用</option>';
					$('#paymentType').html(HTML);
				}else if(paymentAccount.paymentType==2){
					var HTML='<option value="1" >货到付款</option>'+
						'<option value="2" selected>减免费用</option>';
					$('#paymentType').html(HTML);
				}
				$('#paymentName').html(paymentAccount.paymentName);
				$('#fee').val(paymentAccount.fee);
				$('#reduceFee').val(paymentAccount.reduceFee);
				$('#counterFee').val(paymentAccount.counterFee);
				$('#notifyUrl').val(paymentAccount.notifyUrl);
				$('#callBackUrl').val(paymentAccount.callBackUrl);
				$('#merchantUrl').val(paymentAccount.merchantUrl);
				document.getElementById('paymentAccount').readOnly=true;
				var HTML='<label class="control-label" for="paymentName">支付类型:</label>'+
					'<div class="controls"><select id="paymentName" name="paymentName" class="input-medium">'+
					'<option value="'+paymentAccount.bankCode+'" selected>'+paymentAccount.bankName+'</option>'+
					'</select></div>';
				$('#selectPayment').html(HTML);
			},
			error : function(){
				alert("获取数据失败");
			}
		});
		
		
	}
});


function loadPaymentEntitySelect(id){
	var url=ctx+'/paymentEntity!findAll.do?id='+id;
	$('#selectPayment').load(url);
}



function submitPayment(){
	var merchantCode=$('#merchantCode').val();
	var merchantName=$('#merchantName').val();
	var paymentCode=$('#paymentName option:selected').val();
	var paymentAccount=$('#paymentAccount').val();
	if(merchantCode==null||merchantCode==""){
		alert("商户编码不可为空");
		return;
	}
	if(merchantName==null||merchantName==""){
		alert("商户名不可为空");
		return;
	}
	if(paymentCode==null||paymentCode==""){
		alert("支付类型不可为空");
		return;
	}
	if(paymentAccount==null||paymentAccount==""){
		alert("支付账户不可为空");
		return;
	}
	var relationAccount=$('#relationAccount').val();
	var security=$('#security').val();
	var paymentType=$('#paymentType').val();
	var fee=$('#fee').val();
	var reduceFee=$('#reduceFee').val();
	var counterFee=$('#counterFee').val();
	var notifyUrl=$('#notifyUrl').val();
	var callBackUrl=$('#callBackUrl').val();
	var merchantUrl=$('#merchantUrl').val();
	if(paymentType==1&&(fee==""||fee==null)){
		alert("使用货到付款方式需要填写运费");
		return;
	}
	if(paymentType==2&&(reduceFee==""||reduceFee==null)){
		alert("使用减免费用方式需要填写减免的数额");
		return;
	}
	
	var id=$('#merchantId').val();
	var type="";
	if(id==0||id==""||id==null){
		type="add";
	}else{
		type="update";
	}
	
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		data    : {
			'merchantCode':merchantCode,
			'merchantName':merchantName,
			'paymentCode':paymentCode,
			'paymentAccount':paymentAccount,
			'type':type,
			'id':id,
			'relationAccount':relationAccount,
			'security':security,
			'paymentType':paymentType,
			'fee':fee,
			'reduceFee':reduceFee,
			'counterFee':counterFee,
			'notifyUrl':notifyUrl,
			'callBackUrl':callBackUrl,
			'merchantUrl':merchantUrl
		},
		url     : ctx+'/merchantPayment!add.do',
		dataType: 'json',
		success : function(data){
			var result=data.result;
			if(result==1){
				alert("操作成功");
			}else if(result==0){
				alert("登录超时，请重新登录");
			}else if(result==-1){
				alert("此商户已经有此类账户存在或支付账户已经存在，请修改后重新提交");
			}
		},
		error   : function(data){
			alert("操作失败");
		}
	});
	
}

