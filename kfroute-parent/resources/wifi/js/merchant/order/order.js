
function closeLoginmodal() {
	$('#lean_overlay').css('display','none');
	var divobj = document.getElementById('loginmodal');
	divobj.style.display = "none";
	$('#fileField').val('');
	$('#username').val('');
}

function lockingoo(orderNumber,merchantCode){
	
	var param = "orderNumber="+orderNumber+"&merchantCode="+merchantCode;
 	var url=ctx + '/orderBase!getOrderItem.do';
	$.ajax({
		url : url,
		type : 'post',
		data : param,
		dataType : 'json',
		success : function(data){
			if(data!==null&&data!==""&&data.length!==0){
				$(".trItem").remove();
				 $("#order").show();	
				$("#orderTable h1").text("订单编号："+data[0].orderNumber);
				var num=0;
				var price =0;
				$.each(data,function(n,obj) { 
					$("#orderTable tr:eq("+1+")").after("<tr class=\"trItem\"><td>"+obj.goodsName+"</td><td>"+obj.num+"</td><td>"+obj.price+"</td></tr>");    
					num = obj.num+num;
					price = price +obj.price*obj.num;
				});  
			    $("#censusTr p:eq(0)").text("数量合计："+ num); 
			    $("#censusTr p:eq(1)").text("应付金额："+ price);
			    $("#order").draggable();
			}else{
				$("#order").hide();
				alert("订单不存在！");
				
			}	
		}
	  	});
	
}


