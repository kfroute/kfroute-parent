<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../common.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>添加商户</title>
<script type="text/javascript">
requireCss('third-party/jquery-ui-bs/assets/css/bootstrap.css');

</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">商户账号管理 <span class="divider">/</span></li>
				<li class="active">新增商户</li>
			</ul>
			<form id="user_form">
			<input id="merchantId" type="hidden" value="${param.id }">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#profile_tab" data-toggle="tab">基本信息</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="profile_tab">
						<input type="hidden" id="id" name="id" value="${param.id }">
						<div class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="merchantCode">商户编码:</label> 
								<div class="controls">
									<input type="text" id="merchantCode" name="merchantCode">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="merchantName">商户名:</label> 
								<div class="controls">
									<input type="text" id="merchantName" name="merchantName">
								</div>
							</div>
							<div id="selectPayment" class="control-group">
								
							</div>
							<div class="control-group" style="display:none">
								<label class="control-label" for="paymentAccount">支付账号:</label> 
								<div class="controls">
									<input type="text" id="paymentAccount" name="paymentAccount">
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
			<div class="form-btns">
				<input type="button" class="btn btn-primary" onclick='submitPayment();' id="btn_save" value="保存">
				<input type="button" class="btn btn-primary" onclick="history.go(-1);" value="返回">
			</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	require('js/payment/addMerchantPayment.js');
	</script>
</body>
</html>