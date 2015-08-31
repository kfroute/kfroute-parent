<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"
	scope="request" />

<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录-尚果科技</title>
<meta name="keywords" content="用户登录,尚果科技" />
<meta name="Description" content="尚果科技" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<jsp:include page="../../common.jsp"></jsp:include>
<!-- 引用外部库 -->
<script type="text/javascript">
require('js/merchant/jquery.leanModal.min.js');
require('js/merchant/top.js');
require('js/merchant/diy.js');
require('js/ajaxfileupload.js');
require("js/jquery.form.js");
requireCss('css/merchant/css.css');
requireCss('css/merchant/main_min.css');
</script>
<style type="text/css">
.nobg {
	background: #FFF;
}
</style>
<script type="text/javascript">
			var service = '${param.service}';
			var href = window.location.href;
			if(window.top != window){
				window.parent.location.href = href;
			}
			$(function(){
				 $(".input_tank").focus(function(){//聚焦
					 $(this).addClass("nobg");//添加nobg样式
				 })
					
				 $(".input_tank").blur(function(){//失焦
				 	var tankvalue=$(".input_tank").val();//取input的value值
				 	if(tankvalue!=null&&tankvalue!=''){//判断value值为不为空
						return;//直接返回
					}
					 $(this).removeClass("nobg");//为空则将nobg样式清除
					
				 })
			})
		</script>
</head>

<header role="banner">
<div id="header_con">
<div id="mer_name"><a href="#">尚果科技</a></div>
<div id="mer_name_left">

<ul id="mobanwang_com" class="first-menu2">

	<li><a href="#" target="_self">首页</a></li>
</ul>
</div>

<div class="cls"></div>
</div>
</header>

<div id="main_login">
<div id="login">
<div id="login_top">
<p>登录尚果科技运营平台</p>
</div>
<div id="login_con">

<form name="form" id="loginForm" method="post" action="${ctx }" target="_top">
<table width="335" height="230" border="0" cellspacing="0"
	cellpadding="0">
	<tr>
		<td><span>账号：</span></td>
		<td><input id="loginName" name="loginName" class="username" value="请输入用户名" size="20" placeholder="请输入您的邮箱"
									onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                             		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
                             		onFocus="if (value =='请输入您的邮箱'){value =''}"
                             		onBlur="if (value ==''){value='请输入您的邮箱'}"></td>
	</tr>
	<tr>
		<td><span>密码：</span></td>
		<td><input name="password" type="password" class="password" placeholder="请输入您的密码"
			value="" size="20" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                             		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
                            		 onFocus="if (value =='请输入您的密码'){value =''}"
                            		 onBlur="if (value ==''){value='请输入您的密码'}"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input onclick="doLogin();" type="button"  class="land_btn" value=""></td>
	</tr>
			<div class="wrong-tip-box" id="id_tips_login_email">
				<c:if test="${not empty l_message}">
		           <strong>错误:</strong>${l_message }</p>
		      	</c:if> 
			</div>
</table>
</form>
</div>
</div>
</div>

<footer>
<div id="footer">
<p>广告投放热线：0551-62698780 / 0551-62389988</p>
<p>公司地址：安徽省合肥市庐阳区市府广场天徽大厦19F</p>
<p>全国统一服务热线：4006-800-890</p>
<p>copyright 2012-2013 尚果科技 All Right Reserved</p>
</div>
</footer>
<!--<iframe runat="server" src="bottom.html" width="1140" height="248" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>-->
</body>

<script type="text/javascript">
		$(document).ready(function() {
			$("#loginName").focus();
			$("input").keypress(function(event){
				if(event.which == 13){
					doLogin();
				}
			});
		});
		function doLogin(){
			$("#loginForm").submit();
		}
		
	</script>
</body>

</html>

</html>