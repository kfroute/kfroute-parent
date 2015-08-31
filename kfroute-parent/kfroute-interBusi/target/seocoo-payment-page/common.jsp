<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<c:set var="ctx" value="${pageContext.request.contextPath}"  scope="request"/>
<c:set var="resourcePath" value="${applicationScope.resourcePath}"  scope="request"/>
<script type="text/javascript">
	var ctx = "${ctx}";	//上下文路径
	var resourcePath = "${resourcePath}";	
	document.writeln('<script type="text/javascript" src="'+resourcePath+'/js/global.js?d=' + new Date().getTime() + '" ><\/script>');
</script>


<script type="text/javascript">
requireCss('third-party/jquery-ui-bs/assets/css/bootstrap-responsive.css');
requireCss('third-party/jquery-ui-bs/css/custom-theme/jquery-ui-1.10.0.custom.css');
requireCss('third-party/jquery-ui-bs/css/custom-theme/jquery.ui.1.10.0.ie.css');
requireCss('third-party/jquery-validation/1.10.0/validate.css');
requireCss('third-party/dataTables/jquery.dataTables.bs.css');
requireCss('third-party/jquery-ui-multiselect/jquery.multiselect.css');
requireCss('third-party/jquery-loadmask-0.4/jquery.loadmask.css');
requireCss('third-party/uploadify/uploadify.css');
requireCss('third-party/My97DatePicker/skin/WdatePicker.css');
requireCss('third-party/css/common.css');

require('third-party/jquery-ui-bs/js/jquery-1.8.3.min.js');
require('third-party/jquery-ui-bs/js/jquery-ui-1.10.0.custom.min.js');
require('third-party/jquery-ui-bs/assets/js/bootstrap.min.js');
require('third-party/jquery-validation/1.10.0/jquery.validate.min.js');
require('third-party/jquery-validation/1.10.0/messages_bs_zh.js');
require('third-party/dataTables/jquery.dataTables.js');
require('third-party/js/jquery.blockUI.js');
require('third-party/jquery-ui-multiselect/jquery.multiselect.js');
require('third-party/jquery-loadmask-0.4/jquery.loadmask.min.js');
require('third-party/uploadify/jquery.uploadify.min.js');
require('third-party/My97DatePicker/WdatePicker.js');
require('third-party/js/jquery.cookie.js');
require('third-party/js/cookie.js');
require('third-party/js/common.js');
require('third-party/js/jquery.callback.js');
require('third-party/js/ajaxfileupload.js');
require('third-party/js/additional-methods.js');
require('third-party/js/form.js');
</script>