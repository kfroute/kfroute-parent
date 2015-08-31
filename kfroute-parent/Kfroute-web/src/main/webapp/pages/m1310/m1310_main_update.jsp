<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>设备增加</title>

		<link rel="stylesheet" href="${res}/common/css/style_tab.css"  />
		<link rel="stylesheet" href="${res}/common/css/input_tables.css"  />
		<script src="${res}/common/lib/validation/jquery.validate.js" ></script>
        <script src="${res}/common/lib/validation/additional-methods.js" ></script>
        <script src="${res}/common/lib/validation/jquery.metadata.js" ></script>
        <script src="${res}/common/lib/validation/messages_cn.js" ></script>
 		<script src="${res}/common/js/m1310/m1310_update.js" ></script>
 		<style type="text/css">
 			#device_update_form label.error {
 			    color: red;
			    font-style: italic;
				width: auto;
			}
 		</style>
	</head>
	<body>
		<form:form method="post" id="device_update_form"  htmlEscape="true" action="${ctx}/m1310/doUpdateDevice.do">
			<input type="hidden" value="${routerMsg.rIdNo }" id="routerMsg.rIdNo" name="routerMsg.rIdNo">
			<!-- 设备信息修改模块 -->
			<div id="device_update">
				<table id="device_update_tab" class="display cell-border input-table" cellspacing="0" width="100%">
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
							<th>MAC地址</th>
							<td><input type="text" id="mac" name="routerMsg.mac" value="${routerMsg.mac }" readonly/></td>
							<th>设备型号</th>
							<td><input type="text" id="modelType" name="routerMsg.modelType" value="${routerMsg.modelType }"/></td> 
						</tr>
						<tr>
							<th>系统初始版本</th>
							<td><input type="text" id="version" name="routerMsg.version" value="${routerMsg.version }"/></td>
							<th>芯片型号</th>
							<td><input type="text" id="chipModel" name="routerMsg.chipModel" value="${routerMsg.chipModel }"/></td>
						</tr>
						<tr>
							<th>归属类型</th><!-- 代理商类型需要选择代理商 -->
							<td>
								<select class="span3" name="routerMsg.belongType" id="belongType" onchange="chooseAgent(this);" style="width:216px;">
										<option ${routerMsg.belongType==1?"selected='selected'":"" } value="1">自有渠道</option>
										<option ${routerMsg.belongType==2?"selected='selected'":"" } value="2">代理商渠道</option>
								</select>
							</td>
							<th>归属代理商</th>
							<td>
								<input type="hidden" value="${routerMsg.groupId }" id="hiddenGroupId">
								<select class="span3" name="routerMsg.groupId" id="groupId" style="width:216px;" ${routerMsg.belongType==2?"":"disabled"}>
								</select>
							</td> 
						</tr>
						<tr>
							<th>主频</th>
							<td><input type="text" id="basicFreq" name="routerMsg.basicFreq" value="${routerMsg.basicFreq }"/></td>
							<th>内存</th>
							<td><input type="text" id="ram" name="routerMsg.ram" value="${routerMsg.ram }"/></td>
						</tr>
						<tr>
							<th>falsh</th>
							<td><input type="text" id="flash" name="routerMsg.flash" value="${routerMsg.flash }"/></td>
							<th>品牌名称</th>
							<td><input type="text" id="brandName" name="routerMsg.brandName" value="${routerMsg.brandName }"/></td> 
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3"><input type="text" style="width:90%" id="opNote" name="routerMsg.opNote" value="${routerMsg.opNote }"/></td>
							
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