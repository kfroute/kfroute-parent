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

//定时器，倒计时
//	var interval = 1000; 
//	function ShowCountDown(year,month,day,divname){ 
//		var now = new Date(); 
//		var endDate = new Date(year, month-1, day); 
//		var leftTime=endDate.getTime()-now.getTime(); 
//		var leftsecond = parseInt(leftTime/1000); 
//		//var day1=parseInt(leftsecond/(24*60*60*6)); 
//		var day1=Math.floor(leftsecond/(60*60*24)); 
//		var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
//		var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
//		var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
//		var cc = document.getElementById(divname); 
//		cc.innerHTML = "脚本之家提示距离"+year+"年"+month+"月"+day+"日还有："+day1+"天"+hour+"小时"+minute+"分"+second+"秒"; 
//	} 
//	window.setInterval(function(){ShowCountDown(2010,4,20,'divdown1');}, interval); 
var EndTime= new Date(); //截止时间 前端路上 http://www.51xuediannao.com/qd63/
EndTime.setMinutes(EndTime.getMinutes()+1);
var la;
function getRTime(){
	var NowTime = new Date();
	var t =EndTime.getTime() - NowTime.getTime();
	if(t<=1000){
		window.clearInterval(la);
	}
    /*var d=Math.floor(t/1000/60/60/24);
    t-=d*(1000*60*60*24);
    var h=Math.floor(t/1000/60/60);
    t-=h*60*60*1000;
    var m=Math.floor(t/1000/60);
    t-=m*60*1000;
    var s=Math.floor(t/1000);*/
	
    var m=Math.floor(t/1000/60%60);
    var s=Math.floor(t/1000%60);

    document.getElementById("t_m").innerHTML = m + "分";
    document.getElementById("t_s").innerHTML = s + "秒";
}
la=setInterval(getRTime,1000);


