<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>设备修改成功界面</title>
		<script>
			var retCode = '${retCode}';
			var retMsg = '${retMsg}';
			var routeMsg = '${routerMsg}';
			$(function(){
				if(retCode=='0000'){
					layer.alert("操作完成！",{icon: 6, title:'提示',end:function(){
						//parent.window.location.reload();
						var rowObj = $('#check_${routerMsg.rIdNo}',window.parent.document).parent().parent();
						var cols = rowObj.children();
						cols.eq(3).html('${routerMsg.modelType}');//型号
						cols.eq(4).html('${routerMsg.version}');//版本
						cols.eq(5).html('${routerMsg.belongType==2?"代理商渠道":"自有渠道"}');//归属类型
						cols.eq(6).html('${(routerMsg.groupName==null||routerMsg.groupName=="")?"自有":(routerMsg.groupName)}');//归属
						cols.eq(7).html('${routerMsg.chipModel}');//芯片型号
						//cols.eq(8).html('${routerMsg.basicFreq}');//主频
						cols.eq(8).html('${routerMsg.ram}');//ram
						//cols.eq(10).html('${routerMsg.flash}');//flash
						cols.eq(9).html('${routerMsg.brandName}');//品牌
						cols.eq(10).html('${routerMsg.opNote}');//操作日志
						cols.eq(11).html('${routerMsg.updateTimestamp}');//操作时间
						
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭  
					}});
				}else{
					layer.alert("操作完成！<br/>${retMsg}",{icon: 0, title:'提示',end:function(){
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