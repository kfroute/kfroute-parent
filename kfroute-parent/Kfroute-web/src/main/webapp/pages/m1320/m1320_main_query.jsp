<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="server=device-width, initial-scale=1.0" />
		<title>服务器查询</title>

		<link rel="stylesheet" href="${res}/common/css/style_tab.css"  />
		<link rel="stylesheet" href="${res}/common/css/input_tables.css"  />
		<script src="${res}/common/js/m1320/m1320_query.js" ></script>
		
 
	</head>
	<body>
		
		<!-- 服务器信息查询模块 -->
		<div id="server_query">
			<table id="server_query_tab" class="display cell-border input-table" cellspacing="0" width="100%">
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
						<td><input type="text" id="serverName" name="serverName" value=""></td>
						<th>IP</th>
						<td><input type="text" id="ipAddress" name="ipAddress" value=""></td> 
					</tr>
					<tr>
						<th>运营商</th>
						<td><input type="text" id=operator name="operator" value=""></td>
						<th>位置</th>
						<td><input type="text" id="location" name="location" value=""></td>
					</tr>
					<tr>
						<th>流量限制</th>
						<td>
							<input type="text" id="bandWidth" name="bandWidth" value="">
						</td>
						<th>状态</th>
						<td>
							<select class="span3" name="sysStatus" id="sysStatus" style="width:216px;">
								<option selected="selected" value="">请选择</option>
								<option value="1">已激活</option>
								<option value="0">未激活</option>
							</select>
						</td> 
					</tr>
					<tr>
						<th>费用</th>
						<td><input type="text" id="rate" name="rate" value=""></td>
						<th>系统分组</th>
						<td>
							<select class="span3" name="belongGroup" id="belongGroup" style="width:216px;">
								
							</select>
						</td>
					</tr>
					<tr>
						<th>开始时间</th>
						<td>
							<div class="input-append date form_datetime" >
								<input size="16" type="text" id="startTimestamp" name="startTimestamp" style="width:153px;" name="beginTimestamp" value="" readonly>
								<span class="add-on"><i class="icon-remove"></i></span>
	    						<span class="add-on"><i class="icon-th"></i></span>
	    					</div>
						</td>
						<th>结束时间</th>
						<td>
							<div class="input-append date form_datetime">
								<input size="16" type="text" id="endTimestamp" name="endTimestamp" style="width:153px;" name="endTimestamp" value="" readonly>
								<span class="add-on"><i class="icon-remove"></i></span>
	    						<span class="add-on"><i class="icon-th"></i></span>
	    					</div>
						</td> 
					</tr>
				</tbody>
			</table>
		</div>
		<br/><br/><br/>
		<div class="modal-footer" style="position:fixed;width:100%;margin-left:auto;margin-right:auto;bottom:0px;text-align:center;">
			<a href="javascript:void(0)" class="btn" name="submit_btn" onclick="submitQuery();">查询</a>
		</div>
		
	</body>
</html>