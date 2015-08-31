<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=server-width, initial-scale=1.0" />
		<title>服务器管理</title>

		<link rel="stylesheet" href="${res}/common/css/style_tab.css"  />
		<link rel="stylesheet" href="${res}/common/css/input_tables.css"  />
		<script src="${res}/common/js/m1320/main.js" ></script>
 
	</head>
	<body>
		<div id="contentwrapper">
			    <div class="main_content">
			        <div class="row-fluid">
			            <div class="span12">
			                <h3 class="heading">服务器明细列表</h3>
			        		<table class="table table-bordered table_vam display" id="server_detail">
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
			        <!-- hide elements-->
			        <div class="hide">
			            
			            <!-- actions for datatables -->
			            <div class="server_detail_actions">
			                <div class="btn-group">
			                    <button data-toggle="dropdown" class="btn dropdown-toggle"><i class="splashy-sprocket_light"></i> 操作 <span class="caret"></span></button>
			                    <ul class="dropdown-menu">
			                   		<li><a href="javascript:void(0)" onclick="showQueryFrame();"><i class="splashy-zoom"></i> 查询</a></li>
			                        <li><a href="javascript:void(0)" onclick="showAddFrame();"><i class="icon-plus"></i> 增加</a></li>
			                         <li><a href="javascript:void(0)" onclick="showBatchActiveFrame();"><i class="icon-plane"></i> 批量激活</a></li>
			                        <li><a href="javascript:void(0)" onclick="showImpFrame();"><i class="icon-adt_atach"></i> 导入</a></li>
			                    </ul>
			                </div>
			            </div>
			            
			            <!-- confirmation box -->
			            <div id="confirm_dialog" class="cbox_content">
			                <div class="sepH_c tac"><strong>确定删除所选记录吗?</strong></div>
			                <div class="tac">
			                    <a href="#" class="btn btn-gebo confirm_yes">是</a>
			                    <a href="#" class="btn confirm_no">否</a>
			                </div>
			            </div>
			            
			        </div>           
			    </div>
		</div>
		
		
	</body>
</html>