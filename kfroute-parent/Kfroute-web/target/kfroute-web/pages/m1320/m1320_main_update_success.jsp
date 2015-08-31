<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>服务器修改成功界面</title>
		<script>
			var retCode = '${retCode}';
			var retMsg = '${retMsg}';
			var serverMsg = '${serverMsg}';
			console.log(serverMsg);
			$(function(){
				if(retCode=='0000'){
					layer.alert("操作完成！",{icon: 6, title:'提示',end:function(){
						//parent.window.location.reload();
						var rowObj = $('#${serverMsg.serverId}',window.parent.document);
						var cols = rowObj.children();
						cols.eq(2).html('${serverMsg.serverName}');//名称
						//cols.eq(4).html('${serverMsg.operator}');//运营商
						cols.eq(4).html('${serverMsg.location}');//位置
						cols.eq(5).html('${serverMsg.bandWidth}');//流量限制
						cols.eq(6).html('${serverMsg.rate}');//费用
						//cols.eq(10).html('${routerMsg.flash}');//flash
						if(${serverMsg.sysStatus}==0){
							cols.eq(8).html('未激活');//状态
						}else if(${serverMsg.sysStatus}==1){
							cols.eq(8).html('已激活');//状态
						}else{
							cols.eq(8).html('锁定');//状态
						}
						
						cols.eq(9).html('${serverMsg.belongGroupName}');//系统分组
						cols.eq(10).html('${serverMsg.updateTimestamp}');//操作时间
						
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