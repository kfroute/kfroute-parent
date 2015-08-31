<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>操作成功界面</title>
		<script>
			var retCode = '${retCode}';
			var retMsg = '${retMsg}';
			$(function(){
				if(retCode=='0000'){
					layer.alert("操作成功！",{icon: 6, title:'提示',end:function(){
						parent.window.location.reload();
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭  
					}});
				}else{
					layer.alert("操作失败【${retCode}】！<br/>${retMsg}",{icon: 2, title:'提示',end:function(){
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭  
					}});
				}
			});
			
		
 		</script>
 		
	</head>
	<body>
	</body>
</html>