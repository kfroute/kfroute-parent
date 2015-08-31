<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../common.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="keywords" content="">
<title>toPay</title>
<script type="text/javascript">
requireCss('css/style.css');
</script>
</head>
<body style="padding:0;">
<form name="postForm" id="postForm" method="post" action="${ctx}/pay/payment!topay.do">
<!-- 	<c:forEach items="${params}" var="mymap">
		<c:if  test="${mymap.key=='orderNumber'}">
			<c:out value="${mymap.value }"></c:out>
		</c:if>
	</c:forEach>-->
		<input type="hidden" name="payment.orderNumber" id="orderNumber"  value="${paymentData.orderNumber }"/>
		<input type="hidden" name="merchantName" id="merchantName"  value="${paymentData.merchantName }"/>
		<input type="hidden" name="payment.totalPrice" id="totalPrice"  value="${paymentData.totalPrice }"/>
		<input type="hidden" name="payment.merchantCode" id="merchantCode"  value="${paymentData.merchantCode }"/>
		<input type="hidden" name="name" id="name" value="${paymentData.paymentName }"/>
		<input type="hidden" name="payment.platformOrderNumber" id="payment.platformOrderNumber" value="${paymentData.platformOrderNumber }" />
		<input type="hidden" name="payment.paymentPlatform" id="paymentPlatform"  value=""/>
<!--标题-->
<div style=" overflow:hidden; font-family:'幼圆'">
    <div class="common_head">
        <a href="javascript:void(0);" class="backbtn"></a>
        <img style="float:left;" src="${resourcePath }/image/payment/shang.png" />
        <div style=" height:40px; line-height:100%; float:left;margin-top:10px;">支付订单 </div> 
    </div>

    <!-- <div class="time">
       <div style="width:250px; background-image:url(${resourcePath }/image/payment/colock_03.png); background-position:left center; background-repeat:no-repeat;"></div> 
    </div> -->
     
     
<div class="order">
	<p >&nbsp;&nbsp;订单号：<span id="orderNumber" style="color:#E60012;">${paymentData.orderNumber }</span></p>
	<p >&nbsp;&nbsp;订单总价：<span id="orderPrice" style="color:#E60012;">${paymentData.totalPrice/100 }</span><span style="color:#E60012;">元</span></p>
	<div style="background-image:url(${resourcePath }/image/payment/bg07_04.png); background-repeat:repeat-x; background-position:bottom center;width:100%;height:20px;">
	</div>
</div>
   
   
    <!-- <div class="account">
      <p class="pra2">&nbsp;&nbsp;账户余额:&nbsp;<span id="balance">0</span><span>元</span></p>
    </div> -->
            
           <div style=" width:100%; height:10px; background-color:#f0f0f0;"></div>
          
     <div class="account">
     	<p class="pra2">&nbsp;&nbsp;还需支付:&nbsp;<span id="totalPrice">${paymentData.totalPrice/100 }</span><span>元</span></p>
     </div>

<!--购买方式-->
    <div class="choosebank wp96" style="background:#fff;">
    	<input type="hidden" id="paymentMode" name="paymentMode" value=""/>
          <ul >
            <li class="bank_normal" onclick="selectPaymentMode('aliPayWap');">
             <!--实线--><div class="mt5 mb5" style="border-bottom:1px solid  #f3f3f3"></div>
                <div class="wp80">
                    <div class="zhifubao"><a  href="javascript:void();"></a></div>
                    <div>
                      <h3 style="margin-left:15%; line-height:200%;font-size:16px;">支付宝WAP支付</h3>
                      <p style="margin-left:15%; line-height:150%;">推荐有支付宝帐号的用户使用</p>
                    </div>
                </div>
                <div class="clear"></div>
                <!--实线--><div class="mt5 mb5" style="border-bottom:1px solid #f3f3f3"></div>
            </li>
            <li class="bank_normal" onclick="selectPaymentMode('bank');">
                <div class="wp80">
                    <div class="yinhang"><a  href="javascript:void();"></a></div>
                    <div>
                      <h3 style="margin-left:15%; line-height:200%;font-size:16px;">银行卡支付</h3>
                      <p style="margin-left:15%; line-height:150%;">支持储蓄卡信用卡，无需开通网银</p>
                  </div>
                </div>
            </li>
            <li class="bank_normal"></li>
            <li class="bank_normal">
              <div class="clear"></div>
              <!--实线--><div class="mt5 mb5" style="border-bottom:1px solid  #f3f3f3"></div>
          </li>
            <li class="bank_normal" onclick="selectPaymentMode('micro');">
                <div class="wp80">
                    <div class="weixin"><a  href="javascript:void();"></a></div>
                    <div>
                      <h3 style="margin-left:15%; line-height:200%; font-size:16px;">微信支付</h3>
                      <p style="margin-left:15%; line-height:150%;">推荐安装微信5.0以上版本的使用 </p>
                  </div>
                </div>
                <div class="clear"></div>
                <!--实线--><div class="mt5 mb5" style="border-bottom:1px solid  #f3f3f3"></div>
            </li> 
        </ul>
        <div class="clear"></div>
    </div>
  </div>
<!--支付按钮-->
<div class="mt10" >
  <a  href="javascript:void();" onclick="submitData();" class="queren_btn" >确认支付</a>
</div>
</form>
<!--请将以下js放在页尾引用-->
<script type="text/javascript">
require('js/jquery1.8.3.js');
require('js/payment/toPayment.js');
</script>
<script type="text/javascript">
$(".choosebank li").click(function(){
	$(this).addClass("bank_active").siblings().removeClass("bank_active");
});
</script>
	
	
</body>
</html>