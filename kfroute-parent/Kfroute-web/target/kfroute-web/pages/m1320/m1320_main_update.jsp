<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=server-width, initial-scale=1.0" />
		<title>设备增加</title>

		<link rel="stylesheet" href="${res}/common/css/style_tab.css"  />
		<link rel="stylesheet" href="${res}/common/css/input_tables.css"  />
		<script src="${res}/common/lib/validation/jquery.validate.js" ></script>
        <script src="${res}/common/lib/validation/additional-methods.js" ></script>
        <script src="${res}/common/lib/validation/jquery.metadata.js" ></script>
        <script src="${res}/common/lib/validation/messages_cn.js" ></script>
 		<script src="${res}/common/js/m1320/m1320_update.js" ></script>
 		<style type="text/css">
 			#server_update_form label.error {
 			    color: red;
			    font-style: italic;
				width: auto;
			}
 		</style>
	</head>
	<body>
		<form:form method="post" id="server_update_form"  htmlEscape="true" action="${ctx}/m1320/doUpdateServer.do">
			<input type="hidden" value="${serverMsg.serverId }" id="serverId" name="serverMsg.serverId">
			<!-- 设备信息修改模块 -->
			<div id="server_update">
				<table id="server_update_tab" class="display cell-border input-table" cellspacing="0" width="100%">
					 <thead>
				        <tr style="display:none">
				            <th></th>
				            <th></th>
				            <th></th>
				            <th></th>
				        </tr>
				   	 </thead>
				 	 <tfoot>
				        <tr style="display:none">
				            <th></th>
				            <th></th>
				            <th></th>
				            <th></th>
				        </tr>
				   	 </tfoot>
					<tbody>
						<tr>
							<th>服务器名称</th>
							<td><input type="text" id="serverName" name="serverMsg.serverName" value="${serverMsg.serverName }"></td>
							<th>IP</th>
							<td><input type="text" id="ipAddress" name="serverMsg.ipAddress" value="${serverMsg.ipAddress }" readonly></td> 
						</tr>
						<tr>
							<th>运营商</th>
							<td><input type="text" id=operator name="serverMsg.operator" value="${serverMsg.operator }"></td>
							<th>位置</th>
							<td><input type="text" id="location" name="serverMsg.location" value="${serverMsg.location }"></td>
						</tr>
						<tr>
							<th>操作系统</th>
							<td><input type="text" id=operSystem name="serverMsg.operSystem" value="${serverMsg.operSystem }"></td>
							<th>内核版本</th>
							<td><input type="text" id="sysKernel" name="serverMsg.sysKernel" value="${serverMsg.sysKernel }"></td>
						</tr>
						<tr>
							<th>文件系统</th>
							<td><input type="text" id=fileHandles name="serverMsg.fileHandles" value="${serverMsg.fileHandles }"></td>
							<th>CPU型号</th>
							<td><input type="text" id="cpuModel" name="serverMsg.cpuModel" value="${serverMsg.cpuModel }"></td>
						</tr>
						<tr>
							<th>主频</th>
							<td><input type="text" id=cpuFreq name="serverMsg.cpuFreq" value="${serverMsg.cpuFreq }"></td>
							<th>机房</th>
							<td><input type="text" id="centerName" name="serverMsg.centerName" value="${serverMsg.centerName }"></td>
						</tr>
						<tr>
							<th>流量限制</th>
							<td>
								<input type="text" id="bandWidth" name="serverMsg.bandWidth" value="${serverMsg.bandWidth }">
							</td>
							<th>状态</th>
							<td>
								<select class="span3" name="serverMsg.sysStatus" id="sysStatus" style="width:216px;" disabled>
									<option value="0">未激活</option>
								</select>
							</td> 
						</tr>
						<tr>
							<th>费用</th>
							<td><input type="text" id="rate" name="serverMsg.rate" value="${serverMsg.rate }"></td>
							<th>系统分组</th>
							<td>
								<input type="hidden" value="${serverMsg.belongGroup }" id="hiddenBelongGroup">
								<select class="span3" name="serverMsg.belongGroup" id="belongGroup" style="width:216px;">
									
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<br/><br/><br/>
			<div class="modal-footer" style="position:fixed;width:100%;margin-left:auto;margin-right:auto;bottom:0px;text-align:center;">
				<input type="submit" value="提交" class="btn" />
			</div>
		</form:form>
	</body>
</html>