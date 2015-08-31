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
 		<script src="${res}/common/js/m1320/m1320_view.js" ></script>
 		<style type="text/css">
 			#server_view_form label.error {
 			    color: red;
			    font-style: italic;
				width: auto;
			}
 		</style>
	</head>
	<body>
			<!-- 设备信息修改模块 -->
			<div id="server_view">
				<table id="server_view_tab" class="display cell-border input-table" cellspacing="0" width="100%">
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
							<td><input type="text" id="serverName" name="serverMsg.serverName" value="${serverMsg.serverName }" readonly></td>
							<th>IP</th>
							<td><input type="text" id="ipAddress" name="serverMsg.ipAddress" value="${serverMsg.ipAddress }" readonly></td> 
						</tr>
						<tr>
							<th>运营商</th>
							<td><input type="text" id=operator name="serverMsg.operator" value="${serverMsg.operator }" readonly></td>
							<th>位置</th>
							<td><input type="text" id="location" name="serverMsg.location" value="${serverMsg.location }" readonly></td>
						</tr>
						<tr>
							<th>操作系统</th>
							<td><input type="text" id=operSystem name="serverMsg.operSystem" value="${serverMsg.operSystem }" readonly></td>
							<th>内核版本</th>
							<td><input type="text" id="sysKernel" name="serverMsg.sysKernel" value="${serverMsg.sysKernel }" readonly></td>
						</tr>
						<tr>
							<th>文件系统</th>
							<td><input type="text" id=fileHandles name="serverMsg.fileHandles" value="${serverMsg.fileHandles }" readonly></td>
							<th>CPU型号</th>
							<td><input type="text" id="cpuModel" name="serverMsg.cpuModel" value="${serverMsg.cpuModel }" readonly></td>
						</tr>
						<tr>
							<th>主频</th>
							<td><input type="text" id=cpuFreq name="serverMsg.cpuFreq" value="${serverMsg.cpuFreq }" readonly></td>
							<th>机房</th>
							<td><input type="text" id="centerName" name="serverMsg.centerName" value="${serverMsg.centerName }" readonly></td>
						</tr>
						<tr>
							<th>经度</th>
							<td><input type="text" id=longitude name="serverMsg.longitude" value="${serverMsg.longitude }" readonly></td>
							<th>纬度</th>
							<td><input type="text" id="latitude" name="serverMsg.latitude" value="${serverMsg.latitude }" readonly></td>
						</tr>
						<tr>
							<th>创建人</th>
							<td><input type="text" id=createBy name='serverMsg.createBy" value="${serverMsg.createBy==""?"管理员批量导入":(serverMsg.createBy) }' readonly></td>
							<th>创建时间</th>
							<td><input type="text" id="createTimestamp" name="serverMsg.createTimestamp" value="${serverMsg.createTimestamp }" readonly></td>
						</tr>
						<tr>
							<th>修改人</th>
							<td><input type="text" id=updateBy name="serverMsg.updateBy" value="${serverMsg.updateBy }" readonly></td>
							<th>修改时间</th>
							<td><input type="text" id="updateTimestamp" name="serverMsg.updateTimestamp" value="${serverMsg.updateTimestamp }" readonly></td>
						</tr>
						
						<tr>
							<th>流量限制</th>
							<td>
								<input type="text" id="bandWidth" name="serverMsg.bandWidth" value="${serverMsg.bandWidth }" readonly>
							</td>
							<th>状态</th>
							<td>
								<select class="span3" name="serverMsg.sysStatus" id="sysStatus" style="width:216px;" disabled>
									<option ${serverMsg.sysStatus==0?"selected='selected'":"" } value="0">未激活</option>
									<option ${serverMsg.sysStatus==1?"selected='selected'":"" } value="1">已激活</option>
									<option ${serverMsg.sysStatus==9?"selected='selected'":"" } value="1">锁定</option>
								</select>
							</td> 
						</tr>
						<tr>
							<th>费用</th>
							<td><input type="text" id="rate" name="serverMsg.rate" value="${serverMsg.rate }" readonly></td>
							<th>系统分组</th>
							<td>
								<input type="hidden" value="${serverMsg.belongGroup }" id="hiddenBelongGroup">
								<select class="span3" name="serverMsg.belongGroup" id="belongGroup" style="width:216px;" readonly>
									
								</select>
							</td>
						</tr>
						<tr>
							<th>地图查看</th>
							<td><input type="button" id="viewMap" name="viewMap" value="地图显示"  onclick="viewMap('${serverMsg.longitude }','${serverMsg.latitude }','${serverMsg.centerName }','${serverMsg.operator }','${serverMsg.cityName }')"></td>
							<th>端口列表</th>
							<td>
								<input type="button" id="viewMap" name="viewMap" value="查看" onclick="viewPort('${serverMsg.ipAddress }')">
							</td>
						</tr>
						<tr>
							<th>操作备注</th>
							<td><input type="text" id=opNote name="serverMsg.opNote" value="${serverMsg.opNote }" readonly></td>
							<th>系统备注</th>
							<td><input type="text" id="note" name="serverMsg.note" value="${serverMsg.note }" readonly></td>
						</tr>
					</tbody>
				</table>
			</div>
			<br/><br/><br/>
			<div class="modal-footer" style="position:fixed;width:100%;margin-left:auto;margin-right:auto;bottom:0px;text-align:center;">
				<input type="submit" value="关闭" class="btn" onclick="closeFrame();" />
			</div>
	</body>
</html>