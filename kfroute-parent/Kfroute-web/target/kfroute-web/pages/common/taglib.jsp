<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="versionBean" class="com.melinkr.micro.entity.Version" />
<c:set var="version" value="${versionBean.version}" scope="request" />
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<%--上传文件URL --%>
<c:set var="res" value="/kfrouteWeb/resources" scope="request"/>
<meta http-equiv="X-UA-Compatible" content="IE=8" >
	<script type="text/javascript">
		var ctx = "${ctx}";
		var res = "${res}";
	</script>
	<script src="${res}/common/js/jquery.min.js" ></script>
	<!-- 解决$.browser 低版本冲突 -->
	<script src="${res}/common/js/jquery-migrate-1.2.1.js"></script>
	<!-- Bootstrap framework -->
	<link rel="stylesheet" href="${res}/common/bootstrap/css/bootstrap.min.css"  />
	<link rel="stylesheet" href="${res}/common/bootstrap/css/bootstrap-responsive.min.css"  />
	<link rel="stylesheet" href="${res}/common/img/splashy/splashy.css"  />
	<link rel="stylesheet" href="${res}/common/lib/jquery-ui/css/Aristo/Aristo.css"  />
	<link rel="stylesheet" href="${res}/common/lib/uniform/Aristo/uniform.aristo.css"  />
	
	<!-- styled form elements -->

	<script src="${res}/common/bootstrap/js/bootstrap.min.js" ></script>
	<!-- datatable -->
	<link rel="stylesheet" type="text/css" href="${res}/common/lib/datatables/media/css/jquery.dataTables.css">
	<link rel="stylesheet" href="${res}/common/css/blue.css"  id="link_theme" />
	<script src="${res}/common/lib/datatables/media/js/jquery.dataTables.min.js" ></script>
    <script src="${res}/common/lib/datatables/jquery.dataTables.sorting.js" ></script>

    
    <!-- layout -->
	<script src="${res}/common/lib/layer/layer.js"></script>
	
	<!-- jquery ui bootstrap -->
	<link type="text/css" href="${res}/common/lib/jquery-ui-bootstrap/css/custom-theme/jquery-ui-1.10.0.custom.css" rel="stylesheet" />

	<link href="${res}/common/lib/jquery-ui-bootstrap/assets/js/google-code-prettify/prettify.css" rel="stylesheet">
	<script src="${res}/common/lib/jquery-ui-bootstrap/assets/js/jquery-ui-1.10.0.custom.min.js" type="text/javascript"></script>
	<script src="${res}/common/lib/jquery-ui-bootstrap/assets/js/google-code-prettify/prettify.js" type="text/javascript"></script>
	<script src="${res}/common/lib/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	
	<!-- datetimepicker -->
	<link href="${res}/common/lib/datepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="${res}/common/lib/datepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${res}/common/lib/datepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>