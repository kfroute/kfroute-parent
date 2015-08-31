<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../common.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看商户</title>
<script type="text/javascript">
requireCss('third-party/jquery-ui-bs/assets/css/bootstrap.css');
require('js/payment/paymentAccount.js');
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">商户账号管理 <span class="divider">/</span></li>
				<li class="active">查看账号</li>
			</ul>
			<div id="merchantPage">

			<div class="row-fluid toolbar" style="display:none">
        		<input onclick="addPaymentAccount(0);" type="button" class="btn btn-primary" value="新增"/>
        	</div>
        	<div id="merchantPaymentTable">
		        
			</div>
			</div>
		</div>
	</div>

</body>
</html>