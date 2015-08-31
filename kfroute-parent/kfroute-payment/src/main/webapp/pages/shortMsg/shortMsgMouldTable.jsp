<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<table id="product_table" class="table table-hover table-bordered table-condensed table-striped">
	<thead>
		<tr>
			<th style="text-align:center">短信模板名</th>
			<th style="text-align:center">短信内容</th>
			<th style="text-align:center">创建时间</th>
			<th style="text-align:center">最后一次修改时间</th>
			<th style="text-align:center">使用状态</th>
			<th style="text-align:center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="shortMsg1" items="${shortMsgList }">
			<tr>
				<td style="text-align:center">${shortMsg1.msgName}</td>
				<td style="text-align:center">${shortMsg1.shortMsg}</td>
				<td style="text-align:center"><fmt:formatDate value= "${shortMsg1.creatDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td style="text-align:center"><fmt:formatDate value= "${shortMsg1.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td style="text-align:center">
				<c:choose>
					<c:when test="${shortMsg1.status==1}">
						正在使用
					</c:when>
					<c:otherwise>
						未使用
					</c:otherwise>
				</c:choose>
				</td>
				<td style="text-align:center"><a onclick="showUpdateShortMsgMould('${shortMsg1.id}');" href="javascript:void(0);">编辑</a>&nbsp;<span>|</span>&nbsp;<a onclick="deleteShortMsgMould('${shortMsg1.id}');" href="javascript:void(0);">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>