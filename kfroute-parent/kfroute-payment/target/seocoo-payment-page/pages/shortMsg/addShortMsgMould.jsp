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
<title>添加短信模板</title>
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
				<li class="active">新增短信模板</li>
			</ul>
			
			<input id="paymentId" type="hidden" value="${param.id }">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#profile_tab" data-toggle="tab">基本信息</a></li>
				</ul>
				<div class="tab-content">
					<input id="shortMsgId" name="shortMsgId" type="hidden" value="${param.id }"/>
					<div class="tab-pane active" id="profile_tab">
						<input type="hidden" id="id" name="id" value="${param.id }">
						<div class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="msgName">模板名:</label> 
								<div class="controls">
									<input type="text" id="msgName" name="msgName">
								</div>
							</div>
							<div id="showPaymentType" class="control-group">
								<label class="control-label" for="status">使用状态:</label> 
								<div class="controls">
									<select id="status" name="status" class="input-medium">
										<option value="0">未使用</option>
										<option value="1">正在使用</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="shortMsg">短信内容:</label> 
								<div class="controls">
									<textarea style="resize:none;width: 800px;height:100px;" id="shortMsg" name="shortMsg"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-btns">
				<input type="button" class="btn btn-primary" onclick='submitMould();' id="btn_save" value="保存">
				<input type="button" class="btn btn-primary" onclick="history.go(-1);" value="返回">
			</div>
			
		</div>
	</div>
	<script type="text/javascript">
	require('js/payment/shortMsg/addShortMsgMould.js');
	</script>
</body>
</html>