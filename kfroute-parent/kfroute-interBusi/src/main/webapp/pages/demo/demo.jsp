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

这里是首页
<br/>

	<c:forEach var="demo" items="${demoList }">
	 ${demo.name }<br/>
	</c:forEach>
	<c:forEach var="paymentEntity" items="${paymentEntityList }">
		${paymentEntity.paymentCode}<br/>
	</c:forEach>
	<c:forEach var="merchantPayment" items="${merchantPaymentList }">
		${merchantPayment.merchantCode}<br/>
	</c:forEach>
	<c:forEach var="paymentAccount" items="${paymentAccountList }">
		${paymentAccount.paymentAccount}<br/>
	</c:forEach>
	
	<a href="http://127.0.0.1:8086/wifi/writeBackFile.do">下载图片</a>
	
	
</body>
</html>