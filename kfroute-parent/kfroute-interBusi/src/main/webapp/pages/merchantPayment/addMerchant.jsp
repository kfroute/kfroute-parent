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
				<li class="active">商户管理 <span class="divider">/</span></li>
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
							<div class="control-group">
								<label class="control-label" for="merchantAddr">商户地址:</label> 
								<div class="controls">
									<input type="text" id="merchantAddr" name="merchantAddr">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="linkInfo">联系方式:</label> 
								<div class="controls">
									<input type="text" id="linkInfo" name="linkInfo">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="manage">管理员:</label> 
								<div class="controls">
									<input type="text" id="manage" name="manage">
								</div>
							</div>
							<div class="control-group" id="city">
								<label class="control-label" for="latnInfo">所在城市:</label> 
								<div id="province" style= "display:inline ">
									
								</div>
								<div id="latn" style= "display:inline ">
									
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="status">授权状态:</label> 
								<select id="status">
									<option value="0">未授权</option>
									<option value="1">已授权</option>
								</select>
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
	<script type="text/javascript">
	require('js/payment/addMerchant.js');
	</script>
</body>
</html>