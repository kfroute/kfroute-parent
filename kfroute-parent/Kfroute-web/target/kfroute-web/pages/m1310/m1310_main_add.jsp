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
 		<script src="${res}/common/js/m1310/m1310_add.js" ></script>
 		<style type="text/css">
 			#device_add_form label.error {
 			    color: red;
			    font-style: italic;
				width: auto;
			}
 		</style>
	</head>
	<body>
		<form:form method="post" id="device_add_form"  htmlEscape="true" action="${ctx}/m1310/addDevice.do">
			<!-- 设备信息增加模块 -->
			<div id="device_add">
				<table id="device_add_tab" class="display cell-border input-table" cellspacing="0" width="100%">
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
							<td><input type="text" id="mac" name="routerMsg.mac" value=""></td>
							<th>设备型号</th>
							<td><input type="text" id="modelType" name="routerMsg.modelType" value=""></td> 
						</tr>
						<tr>
							<th>系统初始版本</th>
							<td><input type="text" id="version" name="routerMsg.version" value=""></td>
							<th>芯片型号</th>
							<td><input type="text" id="chipModel" name="routerMsg.chipModel" value=""></td>
						</tr>
						<tr>
						<th>归属类型</th><!-- 代理商类型需要选择代理商 -->
							<td>
									<select class="span3" name="routerMsg.belongType" id="belongType" onchange="chooseAgent(this);" style="width:216px;">
										<option selected="selected" value="1">自有渠道</option>
										<option value="2">代理商渠道</option>
									</select>
							</td>
							<th>归属代理商</th>
							<td>
								<select class="span3" name="routerMsg.groupId" id="groupId" style="width:216px;" disabled>
								</select>
							</td> 
						</tr>
						<tr>
							<th>主频</th>
							<td><input type="text" id="basicFreq" name="routerMsg.basicFreq" value=""></td>
							<th>内存</th>
							<td><input type="text" id="ram" name="routerMsg.ram" value=""></td>
						</tr>
						<tr>
							<th>falsh</th>
							<td><input type="text" id="flash" name="routerMsg.flash" value=""></td>
							<th>品牌名称</th>
							<td><input type="text" id="brandName" name="routerMsg.brandName" value=""></td> 
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3"><input type="text" style="width:90%" id="opNote" name="routerMsg.opNote" value=""></td>
							
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