<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<table id="product_table" class="table table-hover table-bordered table-condensed table-striped">
	<thead>
		<tr>
			<th style="text-align:center">支付账号</th>
			<th style="text-align:center">关联支付账号</th>
			<th style="text-align:center">支付方式</th>
			<th style="text-align:center">支付渠道</th>
			<th style="text-align:center">所属商户</th>
			<th style="text-align:center">支付后跳转链接</th>
			<th style="text-align:center">回退链接</th>
			<th style="text-align:center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="paymentAccounts" items="${paymentAccountList }">
			<tr>
				<td style="text-align:center">${paymentAccounts.paymentAccount }</td>
				<td style="text-align:center">${paymentAccounts.relationAccount }</td>
				<c:choose>
					<c:when test="${paymentAccounts.paymentType==1 }">
						<td style="text-align:center">货到付款</td>
					</c:when>
					<c:otherwise>
						<td style="text-align:center">减免费用</td>
					</c:otherwise>
				</c:choose>
				<td style="text-align:center">${paymentAccounts.bankName}</td>
				<td style="text-align:center">${paymentAccounts.merchantName}</td>
				<td style="text-align:center">${paymentAccounts.callBackUrl}</td>
				<td style="text-align:center">${paymentAccounts.merchantUrl}</td>
				<td style="text-align:center"><a onclick="showUpdatePaymentAccount('${paymentAccounts.id}');" href="javascript:void(0);">编辑</a>&nbsp;<span>|</span>&nbsp;<a onclick="deletePaymentAccount('${paymentAccounts.id}');" href="javascript:void(0);">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>