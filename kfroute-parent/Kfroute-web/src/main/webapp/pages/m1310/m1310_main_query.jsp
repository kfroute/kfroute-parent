<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>设备查询</title>

		<link rel="stylesheet" href="${res}/common/css/style_tab.css"  />
		<link rel="stylesheet" href="${res}/common/css/input_tables.css"  />
		<script src="${res}/common/js/m1310/m1310_query.js" ></script>
		
 
	</head>
	<body>
		
		<!-- 设备信息导入模块 -->
		<div id="device_query">
			<table id="device_query_tab" class="display cell-border input-table" cellspacing="0" width="100%">
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
						<td><input type="text" id="mac" name="mac" value=""></td>
						<th>设备型号</th>
						<td><input type="text" id="modelType" name="modelType" value=""></td> 
					</tr>
					<tr>
						<th>系统初始版本</th>
						<td><input type="text" id="version" name="version" value=""></td>
						<th>芯片型号</th>
						<td><input type="text" id="chipModel" name="chipModel" value=""></td>
					</tr>
					<tr>
						<th>归属类型</th><!-- 代理商类型需要选择代理商 -->
						<td>
							<input type="hidden" id="groupIdHidden" name="groupIdHidden" value="reuest.Constants.CURRENT_USER.groupId" />
							<c:choose>
								<c:when test="${reuest.Constants.CURRENT_USER.groupId==0}">
									<select class="span3" name="belongType" id="belongType" onchange="chooseAgent(this);" style="width:216px;">
										<option selected="selected" value="0">请选择</option>
										<option selected="selected" value="1">自有渠道</option>
										<option value="2">代理商渠道</option>
									</select>
								</c:when>
								<c:otherwise>
									<select class="span3" name="belongType" id="belongType" style="width:216px;">
										<option value="2">代理商渠道</option>
									</select>
								</c:otherwise>  
							</c:choose>
						
						</td>
						<th>归属代理商</th>
						<td>
							<select class="span3" name="groupId" id="groupId" style="width:216px;" disabled>
							</select>
						</td> 
					</tr>
					<tr>
						<th>主频</th>
						<td><input type="text" id="basicFreq" name="basicFreq" value=""></td>
						<th>内存</th>
						<td><input type="text" id="ram" name="ram" value=""></td>
					</tr>
					<tr>
						<th>falsh</th>
						<td><input type="text" id="flash" name="flash" value=""></td>
						<th>品牌名称</th>
						<td><input type="text" id="brandName" name="brandName" value=""></td> 
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