<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统一接口平台</title>
<jsp:include page="common.jsp" />
<!-- 引用外部库 -->
<script type="text/javascript">
require('js/bootstrap/bootstrap.min.js');
require('js/index.js');
require('js/json2.js');
requireCss('css/bootstrap/bootstrap.css');
requireCss('css/index/index.css');
</script>
<script type="text/javascript">
	var loginName = '<shiro:principal property="loginName"/>';
	function iFrameHeight() {
		var ifm = document.getElementById("main_frame");
		var subWeb = document.frames ? document.frames["main_frame"].document : ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			if(subWeb.body.scrollHeight < 400){
				ifm.height = 562;
			}else{
				ifm.height = subWeb.body.scrollHeight;	
			}
		}
	}
</script>
</head>
<body>
<div class="navbar">
	<div class="navbar-inner">
		<a class="brand" id="main" href="${ctx }/" target="_top"><span>微商运营平台</span></a>
		<shiro:user>
			<ul class="nav pull-right">
				<li id="btn-user" class="dropdown"><a href="#"
					role="button" class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-user icon-white"></i> <shiro:principal property="name" />，您好！<i class="caret"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a id="auth_link" href="${ctx }/" target="_top"><i class="icon-home"></i> 统一运营</a></li>
						<li><a id="pwd_link" href="${ctx }/user/changePwd" target="main_frame"><i class="icon-lock"></i> 修改密码</a></li>
						<li class="divider"></li>
						<li><a href="${ctx }/logout" target="_top"><i class="icon-trash"></i> 注销</a></li>
					</ul></li>
			</ul>
		</shiro:user>
	</div>
</div>
<div class="sidebar-nav">
	<div class="demo-menu hide">
	<a href="#menu_{0}" class="nav-header collapsed" data-toggle="collapse">{1}</a>
	<ul id="menu_{0}" class="nav nav-list collapse">
	</ul>
</div>

</div>
<div class="content">
	<div class="split left">
		<div class="caret"></div>
	</div>
	<ul class="breadcrumb">
		<li class='active'>&nbsp;</li>
	</ul>
	<div class="iframe-wrap">
		<iframe id="main_frame" name="main_frame" frameborder="0" width="100%" height="100%" ></iframe>
	</div>
</div>

<div class="footer">
	 &nbsp;v1.0beta &nbsp;@版权所有&nbsp; 
</div>
</body>

</html>
