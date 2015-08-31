<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>设备批量导入</title>

		<link rel="stylesheet" href="${res}/common/css/style_tab.css"  />
		<link rel="stylesheet" href="${res}/common/css/input_tables.css"  />
		<script src="${res}/common/js/ajaxfileupload.js" ></script>
		<script src="${res}/common/js/m1310/m1310_imp.js" ></script>
 		<script src="${res}/common/css/ajaxfileupload.css" ></script>
	</head>
	<body>
		<form:form method="post" id="imp_form" name ="imp_form" action="${ctx}/m1310/doDeviceImp.do">
			<!-- 设备信息导入模块 -->
			<div id="device_import">
				<table id="device_imp_tab" class="display cell-border input-table" cellspacing="0" width="100%">
					 <thead>
				        <tr style="display:none">
				            <th></th>
				            <th></th>
				           
				        </tr>
				   	 </thead>
				 	 <tfoot>
				        <tr style="display:none">
				            <th></th>
				            <th></th>
				            
				        </tr>
				   	 </tfoot>
					<tbody>
						<tr>
							<th>选择文件</th>
							<td colspan=3>
								<input type="hidden" name="file_name" id="file_name"/>
								<input type="hidden" name="file_type">
		            			<input name="attach_name" type="file"  id="attach_name" size="50" onchange="change()"/>
		            			<input type="button" value="导入" id="data_load" name="data_load" onclick="dataLoad()"/>
		            			<input type="button" value="模板下载" name="muban_download" onclick="mubanDownload()"/>
		            			<input type="button" value="导入详情" style="display:none" id="imp_detail" name="imp_detail" onclick="showImp_detail()"/>
		            			<br/>
		            			<span id="import_msg" style="color:red"></span>
							</td>
						</tr>
						<tr>
							<th>备注</th>
							<td colspan=3>
								<input type="text" id="imp_note" name="imp_note" value="">
							</td>
							
						</tr>
						
					</tbody>
				</table>
			</div>
			<div class="modal-footer" style="position:fixed;width:100%;margin-left:auto;margin-right:auto;bottom:0px;text-align:center;">
				<a href="javascript:void(0)" class="btn" name="submit_btn" onclick="submit();">提交</a>
			</div>
		</form:form>
	</body>
</html>