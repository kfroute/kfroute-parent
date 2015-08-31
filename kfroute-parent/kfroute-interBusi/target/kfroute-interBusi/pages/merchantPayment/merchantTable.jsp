<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<table id="product_table" class="table table-hover table-bordered table-condensed table-striped">
	<thead>
		<tr>
			<th style="text-align:center">商户编码</th>
			<th style="text-align:center">商户名称</th>
			<th style="text-align:center">商户地址</th>
			<th style="text-align:center">所在城市</th>
			<th style="text-align:center">授权状态</th>
			<th style="text-align:center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="merchant" items="${merchantList }">
			<tr>
				<td style="text-align:center">${merchant.merchantCode}</td>
				<td style="text-align:center">${merchant.merchantName}</td>
				<td style="text-align:center">${merchant.merchantAddr}</td>
				<c:forEach var="latn" items="${latnList }">
					<c:if test="${merchant.latnId==latn.aiid }">
						<td style="text-align:center">${latn.name}</td>
					</c:if>
				</c:forEach>
				<c:choose>
					<c:when test="${merchant.status==1}">
						<td style="text-align:center">已授权</td>
					</c:when>
					<c:otherwise>
						<td style="text-align:center">未授权</td>
					</c:otherwise>
				</c:choose>
				<td style="text-align:center"><a onclick="showUpdateMerchant('${merchant.id}');" href="javascript:void(0);">编辑</a>&nbsp;<span>|</span>&nbsp;<a onclick="deleteMerchant('${merchant.id}');" href="javascript:void(0);">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>