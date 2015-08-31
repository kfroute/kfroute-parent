<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../common.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	
<form name="postForm" id="postForm" method="post" action="${ctx}/pay/payment!topay.do">
orderNumber:<input name="payment.orderNumber" id="payment.orderNumber"  value="${data.orderNumber }"/><br/><br/>
merchantName:<input name="merchantName" id="merchantName"  value="${data.merchantName }"/><br/>
	<br/>
price:<input name="payment.totalPrice" id="price"  value="${data.price }"/><br/>
merchantCode:<input name="payment.merchantCode" id="merchantCode"  value="${data.merchantCode }"/><br/>
<input name="payment.paymentPlatform" id="payment.paymentPlatform"  value="aliPayWap"/>

	<br/>
<input  type="submit"/>

</form>
	
	
	
</body>
</html>