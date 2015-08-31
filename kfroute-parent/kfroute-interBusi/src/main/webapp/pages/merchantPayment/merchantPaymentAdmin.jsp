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

<title>商家支付账户管理</title>
<script type="text/javascript">
	requireCss('third-party/jquery-ui-bs/assets/css/bootstrap.css')
	require('js/payment/paymentAdmin.js');
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">商户支付账户管理 <span class="divider">/</span></li>
				<li class="active">修改支付权限</li>
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
								<label class="control-label" for="paymentName">选择商户:</label> 
								<div class="controls">
									<input type="checkbox" name="allMerchant" id="allMerchant" value="allMerchant" onclick="checkAllMerchant();"/>全选&nbsp;&nbsp;<br/>
									<input type="checkbox" name="merchantCode" value="alipay" />栖巢咖啡&nbsp;&nbsp;<br/>
									<input type="checkbox" name="merchantCode" value="weixinzhifu" />星巴克咖啡&nbsp;&nbsp;<br/>
									<input type="checkbox" name="merchantCode" value="yizhifu">麦当劳&nbsp;&nbsp;<br/>
									<input type="checkbox" name="merchantCode" value="icbc">肯德基&nbsp;&nbsp;<br/>
									<input type="checkbox" name="merchantCode" value="abc">必胜客&nbsp;&nbsp;<br/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="paymentName">支付方式:</label> 
								<div class="controls">
									<input type="checkbox" name="allPayment" id="allPayment" value="allPayment" onclick="checkAllPayment();"/>全选&nbsp;&nbsp;<br/>
									<input type="checkbox" name="paymentName" value="alipay" />支付宝&nbsp;&nbsp;<br/>
									<input type="checkbox" name="paymentName" value="weixinzhifu" />微信支付&nbsp;&nbsp;<br/>
									<input type="checkbox" name="paymentName" value="yizhifu">翼支付&nbsp;&nbsp;<br/>
									<input type="checkbox" name="paymentName" value="icbc">中国工商银行&nbsp;&nbsp;<br/>
									<input type="checkbox" name="paymentName" value="abc">中国农业银行&nbsp;&nbsp;<br/>
									<input type="checkbox" name="paymentName" value="boc">中国银行&nbsp;&nbsp;<br/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-btns">
				<input type="button" class="btn btn-primary" onclick='submitMerchant();' id="btn_save" value="保存">
				<input type="button" class="btn btn-primary" onclick="history.go(-1);" value="返回">
			</div>
			</form>
		</div>
	</div>

	
</body>
</html>