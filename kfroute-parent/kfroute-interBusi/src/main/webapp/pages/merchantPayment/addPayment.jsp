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
require("js/jquery.form.js");
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">商户账号管理 <span class="divider">/</span></li>
				<li class="active">新增商户</li>
			</ul>
			
			<input id="paymentId" type="hidden" value="${param.id }">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#profile_tab" data-toggle="tab">基本信息</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="profile_tab">
						<input type="hidden" id="id" name="id" value="${param.id }">
						<div class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="paymentCode">支付方式编码:</label> 
								<div class="controls">
									<input type="text" id="paymentCode" name="paymentCode">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="paymentName">支付方式名:</label> 
								<div class="controls">
									<input type="text" id="paymentName" name="paymentName">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="terminalType">终端类型:</label> 
								<div class="controls">
									<select id="terminalType" name="terminalType" class="input-medium">
										<option value="web">web</option>
										<option value="wap">wap</option>
									</select>
								</div>
							</div>
							<div id="selectPayment" class="control-group">
								
							</div>
							<div class="control-group">
								<label class="control-label" for="paymentImg">支付方式图标:</label> 
								<div class="controls">
									<form id="user_form" action="" method="post" name="user_form" enctype="multipart/form-data">
										<img id="logoUrl" name="logoUrl" alt="" src="" style="width:100px;height:30px;">
								        <input onchange="uploadPaymentImgNow();" type="file" name="file" id="file">
								        <input type="hidden" id="paymentUrl" name="paymentUrl"/>
							        </form>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="paymentDesc">支付方式详情:</label> 
								<div class="controls">
									<textarea style="resize:none;width: 800px;height:100px;" id="paymentDesc" name="paymentDesc"></textarea>
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
			
		</div>
	</div>
	<script type="text/javascript">
	require('js/payment/addPayment.js');
	</script>
</body>
</html>