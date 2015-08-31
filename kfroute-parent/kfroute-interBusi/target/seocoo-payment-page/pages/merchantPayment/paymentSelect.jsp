<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<label class="control-label" for="paymentName">支付类型:</label> 
<div class="controls">
	<select id="paymentName" name="paymentName" class="input-medium">
		<c:forEach var="paymentEntity" items="${paymentEntityList }">
			<c:choose>
				<c:when test="${merchantPayment.paymentCode==paymentEntity.paymentCode }">
					<option value="${paymentEntity.paymentCode }" selected>${paymentEntity.paymentName}</option>
				</c:when>
				<c:otherwise>
					<option value="${paymentEntity.paymentCode }">${paymentEntity.paymentName}</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
</div>