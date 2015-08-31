<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=server-width, initial-scale=1.0" />
		<title>服务器端口查看</title>

		<link rel="stylesheet" href="${res}/common/css/style_tab.css"  />
		<link rel="stylesheet" href="${res}/common/css/input_tables.css"  />
 		<script src="${res}/common/js/m1320/m1320_port_view.js" ></script>
 		<style type="text/css">
 			#server_view_form label.error {
 			    color: red;
			    font-style: italic;
				width: auto;
			}
 		</style>
 		<script>
 			var ipAddress = '${param.ipAddress}';
 		</script>
	</head>
	<body>
			<div id="contentwrapper" style="margin-bottom:40px;">
			    <div class="main_content">
			        <div class="row-fluid">
			            <div class="span12"  id="server_port_view">
			            	<h3 class="heading">服务器端口明细列表</h3>
			        		<table id="server_port_view_tab" class="table table-bordered table_vam display" cellspacing="0" width="100%">
			        			<thead>
			        				
			        			</thead>
			        			<tbody>
			        			<!--  
			        				<tr>
			        					<td><input type="checkbox" name="row_sel" class="select_row" /></td>
			        				</tr>
			        			-->
			        			</tbody>
			        		</table>
			        		
			        	</div>
			        </div>
			       
			    </div>
		</div>
		
		<br/><br/><br/><br/>
		<div class="modal-footer" style="position:fixed;width:100%;margin-left:auto;margin-right:auto;bottom:0px;text-align:center;">
			<a href="javascript:history.back()" class="back_link btn btn-small">返回</a>
		</div>
	</body>
</html>