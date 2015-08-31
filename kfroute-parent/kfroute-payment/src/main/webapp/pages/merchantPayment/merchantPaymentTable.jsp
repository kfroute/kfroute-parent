<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<table id="product_table" class="table table-hover table-bordered table-condensed table-striped">
	<thead>
		<tr>
			<th style="text-align:center">商户编码</th>
			<th style="text-align:center">商户名称</th>
			<th style="text-align:center">支付类型</th>
			<th style="text-align:center">支付类型说明</th>
			<th style="text-align:center">支付账户</th>
			<th style="text-align:center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="merchantPayment" items="${merchantPaymentList }">
			<tr>
				<td style="text-align:center">${merchantPayment.merchantCode}</td>
				<td style="text-align:center">${merchantPayment.merchantName}</td>
				<td style="text-align:center">${merchantPayment.paymentName}</td>
				<td style="text-align:center">${merchantPayment.paymentDesc}</td>
				<td style="text-align:center">${merchantPayment.paymentAccount}</td>
				<td style="text-align:center"><a onclick="showUpdateMerchantPayment('${merchantPayment.id}');" href="javascript:void(0);">编辑</a>&nbsp;<span>|</span>&nbsp;<a onclick="deleteMerchantPayment('${merchantPayment.id}');" href="javascript:void(0);">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>