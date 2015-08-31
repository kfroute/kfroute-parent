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
requireCss('css/style2.css');
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
		<input type="hidden" name="name" id="name" value="${paymentData.paymentName }">
		<input type="hidden" name="payment.paymentPlatform" id="paymentPlatform"  value="aliPay"/>
	
       <div class="header">
           <img src="${resourcePath }/image/payment/logo.jpg" />
              <p>尚果支付平台</p>  
       </div>
    
        <div class="main_box">
          <div class="left_box">
        <!--订单成功-->
             <div class="order">
                    <img src="${resourcePath }/image/payment/duigou.png" />
                     <p>订单提交成功！请在<span id="t_m" name="t_m" style="color:#E60012;"></span><span id="t_s" name="t_s" style="color:#E60012;"></span>内完成支付。只有在支付成功后餐厅才会收到订单。</p>
             </div> 
          
         <div class="bank_box">
            <div class="pay">
               <p>选择支付方式</p>
            </div>
          
          <div class="order_msg">
            <p>订单信息</p>
              <p> 餐厅名称：拼感觉便当</p>
                <p> 送餐地址：天徽大厦C座</p>
              <p> 联系电话：15665693834</p>
            <p>应付总额：<span>¥ ${paymentData.totalPrice/100 }</span>元</p>
          </div>
          
          <!--银行选择-->
               <div class="box01">
                
                  <ul class="content_tiltle" id="content_tiltle_label">
                   	  <li class="other01"></li>
                       <li class="current"><a href="javascript:void(0);" onclick="selectPaymentMode('aliPay');">支付宝/微信/财付通</a></li>
                      <li class="others"><a href="javascript:void(0);" onclick="selectPaymentMode('icbc');">储蓄卡</a></li>
                      <li class="others"><a href="javascript:void(0);" onclick="selectPaymentMode('icbc');">信用卡</a></li>
                     
                      <li class="other"></li>
                      <li class="other"></li>
                  </ul>  
                <div id="content_tiltle_div">
                 <!--内容3-->
           <div style="display:block; overflow:hidden; margin-top:20px;">
              <ul class="bank-list--xpay" style="overflow:hidden;">
                 <li class="item item left" onclick="selectPaymentMode('aliPay');">
                    <input id="check-alipay" class="radio ui-radio" type="radio" name="paytype1" value="alipay"  checked="checked" />
                    <label for="check-alipay"><img src="${resourcePath }/image/payment/zhifubao.jpg" /></label>
                  </li>
                  <li class="item item" onclick="selectPaymentMode('bank');">
                   <input id="check-tenpay" class="radio ui-radio" type="radio" name="paytype1" value="tenpay"  />
                   <label for="check-tenpay"> <img src="${resourcePath }/image/payment/caifutong.jpg" /></label>
                 </li>
                 <li class="item item" onclick="selectPaymentMode('micro');">
                 <input id="check-wxqrpay" class="radio ui-radio" type="radio" name="paytype1" value="wxqrpay"  />
                  <label for="check-wxqrpay"><img src="${resourcePath }/image/payment/weixin.jpg" /></label>
                 </li>
               </ul>
              </div>
                <!--内容1-->
                  <div class="sub_content_bank" style="display:none"> 
                     <p>&nbsp;网上银行支付</p>
                 <ul class="bank-list">
                  <li class="item ">
        <input id="bank-type-ICBCB2C" class="radio ui-radio" type="radio" name="paytype2" value="alipay-ICBCB2C"  checked="checked"/>
                    <label for="bank-type-ICBCB2C"><img src="${resourcePath }/image/payment/bank_01.jpg" /></label>
                  </li>
                <li class="item ">
                    <input id="bank-type-CMB" class="radio ui-radio" type="radio" name="paytype2" value="alipay-CMB"  />
                    <label for="bank-type-CMB"><img src="${resourcePath }/image/payment/bank_02.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-CCB" class="radio ui-radio" type="radio" name="paytype2" value="alipay-CCB"  />
                    <label for="bank-type-CCB"><img src="${resourcePath }/image/payment/bank_03.jpg" /></label>
                </li>
                <li class="item ">
                   <input id="bank-type-ABC" class="radio ui-radio" type="radio" name="paytype2" value="alipay-ABC"  />
                   <label for="bank-type-ABC"><img src="${resourcePath }/image/payment/bank_04.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-1020" class="radio ui-radio" type="radio" name="paytype2" value="tenpay-1020"  />
                   <label for="bank-type-1020"> <img src="${resourcePath }/image/payment/bank_05.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-BOCB2C" class="radio ui-radio" type="radio" name="paytype2" value="alipay-BOCB2C"  />
                   <label for="bank-type-BOCB2C"><img src="${resourcePath }/image/payment/bank_06.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-CIB" class="radio ui-radio" type="radio" name="paytype2" value="alipay-CIB"  />
                   <label for="bank-type-CIB"><img src="${resourcePath }/image/payment/bank_07.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-1022" class="radio ui-radio" type="radio" name="paytype2" value="tenpay-1022"  />
                    <label for="bank-type-1022"><img src="${resourcePath }/image/payment/bank_08.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-SPDB" class="radio ui-radio" type="radio" name="paytype2" value="alipay-SPDB"  />
                    <label for="bank-type-SPDB"><img src="${resourcePath }/image/payment/bank_09.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-GDB" class="radio ui-radio" type="radio" name="paytype2" value="alipay-GDB"  />
                    <label for="bank-type-GDB"><img src="${resourcePath }/image/payment/bank_10.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-1021" class="radio ui-radio" type="radio" name="paytype2" value="tenpay-1021"  />
                    <label for="bank-type-1021"><img src="${resourcePath }/image/payment/bank_11.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-CMBC" class="radio ui-radio" type="radio" name="paytype2" value="alipay-CMBC"  />
                    <label for="bank-type-CMBC"><img src="${resourcePath }/image/payment/bank_12.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-SPABANK" class="radio ui-radio" type="radio" name="paytype2" value="alipay-SPABANK"  />
                    <label for="bank-type-SPABANK"><img src="${resourcePath }/image/payment/bank_13.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-BJBANK" class="radio ui-radio" type="radio" name="paytype2" value="alipay-BJBANK"  />
                    <label for="bank-type-BJBANK"><img src="${resourcePath }/image/payment/bank_14.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-BJRCB" class="radio ui-radio" type="radio" name="paytype2" value="alipay-BJRCB"  />
                    <label for="bank-type-BJRCB"><img src="${resourcePath }/image/payment/bank_15.jpg" /></label>
                </li>
                <li class="item ">
                   <input id="bank-type-PSBC-DEBIT" class="radio ui-radio" type="radio" name="paytype2" value="alipay-PSBC-DEBIT"  />
                    <label for="bank-type-PSBC-DEBIT"><img src="${resourcePath }/image/payment/bank_16.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-SHRCB" class="radio ui-radio" type="radio" name="paytype2" value="alipay-SHRCB"  />
                    <label for="bank-type-SHRCB"><img src="${resourcePath }/image/payment/bank_17.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-HZCBB2C" class="radio ui-radio" type="radio" name="paytype2" value="alipay-HZCBB2C"  />
                    <label for="bank-type-HZCBB2C"><img src="${resourcePath }/image/payment/bank_18.jpg" /></label>
                </li>
            </ul>
             <p>&nbsp;银联在线支付（支持快捷支付，无需开通网银）</p>
            <ul class="bank-list--upop">
                <li class="item item left">
                    <input id="check-upopdebit" class="radio ui-radio" type="radio" name="paytype" value="upopdebit" />
                    <label for="check-upopdebit"><img src="${resourcePath }/image/payment/yinlian.jpg" /></label>
                </li>
              </ul>
            </div>
            <!--内容2-->
             <div style="display:none" class="sub_content_bank">
              <p>&nbsp;网上银行支付</p>
               <ul class="bank-list">
                  <li class="item ">
                    <input id="bank-type-ICCB2C" class="radio ui-radio" type="radio" name="paytype" value="alipay-ICCB2C"   checked="checked"/>
                    <label for="bank-type-ICCB2C"><img src="${resourcePath }/image/payment/bank_01.jpg" /></label>
                  </li>
                <li class="item ">
                    <input id="bank-type-CB" class="radio ui-radio" type="radio" name="paytype" value="alipay-CMB"  />
                   <label for="bank-type-CB"><img src="${resourcePath }/image/payment/bank_02.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-B" class="radio ui-radio" type="radio" name="paytype" value="alipay-CCB"  />
                   <label for="bank-type-B"> <img src="${resourcePath }/image/payment/bank_03.jpg" /></label>
                </li>
                <li class="item ">
                   <input id="bank-type-AC" class="radio ui-radio" type="radio" name="paytype" value="alipay-ABC"  />
                   <label for="bank-type-AC"><img src="${resourcePath }/image/payment/bank_04.jpg" />
                </li>
                <li class="item ">
                    <input id="bank-type-120" class="radio ui-radio" type="radio" name="paytype" value="tenpay-1020"  />
                   <label for="bank-type-120"><img src="${resourcePath }/image/payment/bank_05.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-BOC2C" class="radio ui-radio" type="radio" name="paytype" value="alipay-BOCB2C"  />
                   <label for="bank-type-BOC2C"><img src="${resourcePath }/image/payment/bank_06.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-CI" class="radio ui-radio" type="radio" name="paytype" value="alipay-CIB"  />
                   <label for="bank-type-CI"><img src="${resourcePath }/image/payment/bank_07.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-102" class="radio ui-radio" type="radio" name="paytype" value="tenpay-1022"  />
                   <label for="bank-type-102"><img src="${resourcePath }/image/payment/bank_08.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-SDB" class="radio ui-radio" type="radio" name="paytype" value="alipay-SPDB"  />
                   <label for="bank-type-SDB"><img src="${resourcePath }/image/payment/bank_09.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-GB" class="radio ui-radio" type="radio" name="paytype" value="alipay-GDB"  />
                   <label for="bank-type-GB"><img src="${resourcePath }/image/payment/bank_10.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-121" class="radio ui-radio" type="radio" name="paytype" value="tenpay-1021"  />
                   <label for="bank-type-121"> <img src="${resourcePath }/image/payment/bank_11.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-CBC" class="radio ui-radio" type="radio" name="paytype" value="alipay-CMBC"  />
                   <label for="bank-type-CBC"> <img src="${resourcePath }/image/payment/bank_12.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-SABANK" class="radio ui-radio" type="radio" name="paytype" value="alipay-SPABANK"  />
                    <label for="bank-type-SABANK"><img src="${resourcePath }/image/payment/bank_13.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-BJANK" class="radio ui-radio" type="radio" name="paytype" value="alipay-BJBANK"  />
                    <label for="bank-type-BJANK"><img src="${resourcePath }/image/payment/bank_14.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-BJCB" class="radio ui-radio" type="radio" name="paytype" value="alipay-BJRCB"  />
                    <label for="bank-type-BJCB"><img src="${resourcePath }/image/payment/bank_15.jpg" /></label>
                </li>
                <li class="item ">
                   <input id="bank-type-PSBC-DBT" class="radio ui-radio" type="radio" name="paytype" value="alipay-PSBC-DEBIT"  />
                    <label for="bank-type-PSBC-DBT"><img src="${resourcePath }/image/payment/bank_16.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-SHCB" class="radio ui-radio" type="radio" name="paytype" value="alipay-SHRCB"  />
                    <label for="bank-type-SHCB"><img src="${resourcePath }/image/payment/bank_17.jpg" /></label>
                </li>
                <li class="item ">
                    <input id="bank-type-HZCB2C" class="radio ui-radio" type="radio" name="paytype" value="alipay-HZCBB2C"  />
                    <label for="bank-type-HZCB2C"><img src="${resourcePath }/image/payment/bank_18.jpg" /></label>
                </li>
            </ul>
              <p>&nbsp;银联在线支付（支持快捷支付，无需开通网银）</p>
             <ul class="bank-list--upop">
                <li class="item item left">
                    <input id="check-upopcredit" class="radio ui-radio" type="radio" name="paytype" value="upopcredit"   />
                     <label for="check-upopcredit"><img src="${resourcePath }/image/payment/yinlian.jpg" /></label>
                </li>
               </ul>
            </div>
            
           
             </div>        
            </div>
          
          <!--支付确认--> 
              <div class="confirm">
                <p>支付<span>¥${paymentData.totalPrice/100 }</span></p>
                 <div class="queren" >
                     <a  href="javascript:void();" class="queren_btn" onclick="submitData();">确认支付</a>
                 </div>
             </div>   
         
          </div>
        </div>
        <!--支付帮助-->
        <div class="right_box">
          <div class="help01">
             <h2>请放心购买</h2>
               <div class="pra1">
               <p>采用网络和第三方平台支付，最大限度保证您支付安全。</p>
                 </div>
               <div class="pra2">
               <p>为了支付便捷，建议windows用户使用IE浏览器支付。</p>
               </div>
          </div>
          
           <div class="help02">
             <h2>支付帮助？</h2>
               <ul>
                <li><a href="javascript:void(0);">没有开通网上银行如何购买？</a></li>
                  <li><a href="javascript:void(0);">没有找到我常用的网上银行？</a></li>
                   <li><a href="javascript:void(0);">什么是地方银行？</a></li>
                     <li><a href="javascript:void(0);">无法跳转到对应的支付页面支付？</a></li>
                       <li><a href="javascript:void(0);">网上银行扣款后，美团订单仍显示<br />&nbsp;&nbsp;&nbsp;“未付款”怎么办？</a></li>
              </ul>
           </div>
        
        </div>
    </div>
    </form>
</body>
<script type="text/javascript">
require('js/payment/index.js');
require('js/payment/toWebPayment.js');
</script>
</html>