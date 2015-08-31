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
							<div id="selectPayment" class="control-group">
								
							</div>
							<div class="control-group">
								<label class="control-label" for="paymentAccount">支付账号:</label> 
								<div class="controls">
									<input type="text" id="paymentAccount" name="paymentAccount">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="relationAccount">关联支付账号:</label> 
								<div class="controls">
									<input type="text" id="relationAccount" name="relationAccount">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="security">秘钥:</label> 
								<div class="controls">
									<input type="text" id="security" name="security">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="pay_partner">合作商户号:</label> 
								<div class="controls">
									<input type="text" id="pay_partner" name="pay_partner">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="pay_paternerKey">商户key:</label> 
								<div class="controls">
									<input type="text" id="pay_paternerKey" name="pay_paternerKey">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="appkey">公众号APPkey:</label> 
								<div class="controls">
									<input type="text" id="appkey" name="appkey">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="appId">微信公众平台appid:</label> 
								<div class="controls">
									<input type="text" id="appId" name="appId">
								</div>
							</div>
							
							<div id="showPaymentType" class="control-group">
								<label class="control-label" for="paymentName">支付方式:</label> 
								<div class="controls">
									<select id="paymentType" name="paymentType" class="input-medium">
										<option value="1">货到付款</option>
										<option value="2">减免费用</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="fee">运费:</label> 
								<div class="controls">
									<input type="text" id="fee" name="fee">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="reduceFee">减免费用:</label> 
								<div class="controls">
									<input type="text" id="reduceFee" name="reduceFee">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="counterFee">手续费:</label> 
								<div class="controls">
									<input type="text" id="counterFee" name="counterFee">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="notifyUrl">支付回执地址:</label> 
								<div class="controls">
									<input type="text" id="notifyUrl" name="notifyUrl">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="callBackUrl">手机跳转地址:</label> 
								<div class="controls">
									<input type="text" id="callBackUrl" name="callBackUrl">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="merchantUrl">回退地址:</label> 
								<div class="controls">
									<input type="text" id="merchantUrl" name="merchantUrl">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="returnUrl">WEB支付跳转地址:</label> 
								<div class="controls">
									<input type="text" id="returnUrl" name="returnUrl">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="errorNotifyUrl">出错通知地址:</label> 
								<div class="controls">
									<input type="text" id="errorNotifyUrl" name="errorNotifyUrl">
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
	require('js/payment/addPaymentAccount.js');
	</script>
</body>
</html>