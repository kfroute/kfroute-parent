<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../common.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

这里是首页
<br/>
	<c:forEach var="demo" items="${demoList }">
	 ${demo.name }<br/>
	</c:forEach>
	<c:forEach var="systemConfig" items="${systemConfigList}">
		${systemConfig.propertyKey}<br/>
	</c:forEach>
	<c:forEach var="seocooService" items="${seocooServiceList }">
		${seocooService.id }<br/>
		${seocooService.status }<br/>
		${seocooService.serviceDesc }<br>
	</c:forEach>
	
	<a href="${ctx }/systemConfig.do">查看系统配置</a>
	<a href="${ctx }/seocooService.do">查看数据</a>
	
	
</body>
</html>