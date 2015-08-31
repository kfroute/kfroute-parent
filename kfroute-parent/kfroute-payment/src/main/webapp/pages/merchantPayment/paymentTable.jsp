<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<table id="product_table" class="table table-hover table-bordered table-condensed table-striped">
	<thead>
		<tr>
			<th style="text-align:center">支付编码</th>
			<th style="text-align:center">支付名称类型</th>
			<th style="text-align:center">支付类型图标</th>
			<th style="text-align:center">支付类型说明</th>
			<th style="text-align:center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="paymentEntity" items="${paymentEntityList }">
			<tr>
				<td style="text-align:center">${paymentEntity.paymentCode}</td>
				<td style="text-align:center">${paymentEntity.paymentName}</td>
				<td style="text-align:center"><img style="width:100px;height:30px;" alt="" src="${resourcePath }/image/upload/${paymentEntity.paymentUrl}"/></td>
				<td style="text-align:center">${paymentEntity.paymentDesc}</td>
				<td style="text-align:center"><a onclick="showUpdatePayment('${paymentEntity.id}');" href="javascript:void(0);">编辑</a>&nbsp;<span>|</span>&nbsp;<a onclick="deletePayment('${paymentEntity.id}');" href="javascript:void(0);">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>