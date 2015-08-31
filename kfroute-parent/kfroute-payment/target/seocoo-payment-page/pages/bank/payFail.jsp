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
<title>payFail</title>
<script type="text/javascript">
requireCss('css/style.css');
</script>
</head>
<body style="padding:0;">

<!--标题-->
<div style="overflow:hidden; font-family:'幼圆'">
    <div class="common_head">
        <a href="javascript:void(0);" class="backbtn"></a>
        <img style="float:left;" src="${resourcePath }/image/payment/shang.png" />
        <div style=" height:40px; line-height:100%; float:left;margin-top:10px;">尚果收银台 </div> 
	</div>

     <!--成功信息-->
    <div class="orderok_box">
     <div class="success"><img id="successImg" class="img_box" src="${resourcePath }/image/payment/ico-wrong.png" width="48" height="48" /><p id="message">支付失败！</p></div>
     <div class="order_num" id="successOrderNumber">订单号为：<span class="wapred" id="orderNumber">${orderNumber }</span></div>
    </div>

<!--支付按钮-->
     <div class="mt10" >
           <a  href="javascript:void();" class="queren_btn" >返回</a>
     </div>
</div>
</body>
<script type="text/javascript">
//require('js/payment/paySuccess.js');
</script>
</html>