<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"  class="error_page">
    <%@include file="/pages/common/taglib.jsp"%>
   <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Error Page</title>
		<!-- Bootstrap framework -->
            <link rel="stylesheet" href="${res}/common/bootstrap/css/bootstrap.min.css"  />
            <link rel="stylesheet" href="${res}/common/bootstrap/css/bootstrap-responsive.min.css"  />
		<!-- main styles -->
            <link rel="stylesheet" href="${res}/common/css/style.css"  />
	</head>
	<body>

		<div class="error_box">
			<h1>操作异常【${retCode}】</h1>
			<p>${retMsg }。.</p>
			<a href="javascript:history.back()" class="back_link btn btn-small">返回</a>
		</div>

	
		
	</body>
</html>
