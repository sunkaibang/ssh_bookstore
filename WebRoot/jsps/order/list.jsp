<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		@import url("<c:url value='/jsps/css/background.css'/>")
	</style>
	<style type="text/css">
		@import  url("<c:url value='/jsps/css/init.css'/>")
	</style>

  </head>
  
  <body>
    <h1>我的订单</h1>
    <table border="1" width="100%“ cellspacing="0" background="black">
    	<s:iterator value="orderList" var="order">
	    	<tr bgcolor="gray" bordercolor="gray">
	    		<td colspan="6">
	    			订单编号:<s:property value="#order.oid"/> 成交时间:<s:property value="#order.date"/> 金额:<font color="red"><s:property value="#order.total"/></font>
	    			
	    			<s:if test="#order.state==1">
	    				<a href="${pageContext.request.contextPath}/order_goPay?oid=<s:property value='#order.oid'/>">去付款</a>
	    			</s:if>
	    			<s:elseif test="#order.state==2">
	    				等待发货
	    			</s:elseif>
	    			<s:elseif test="#order.state==3">
	    				<a href="${pageContext.request.contextPath}/order_confirm?oid=<s:property value='#order.oid'/>">确认收货</a>
	    			</s:elseif>
	    			<s:elseif test="#order.state==4">
	    				交易成功
	    			</s:elseif>
	    		</td>
	    	</tr>
	    	<s:iterator value="#order.orderItems" var="orderItem">
	    		<tr bordercolor="gray" align="center">
	    			<td width="15%">
	    				<div width="150" height="250">
	    					<img width="150" height="250" border="0" alt="图书" src="<s:property value='#orderItem.book.bimage'/>">
	    				</div>
	    			</td>
	    			<td>书名:<s:property value='#orderItem.book.bname'/></td>
	    			<td>单价:<s:property value='#orderItem.book.bprice'/>元</td>
	    			<td>作者:<s:property value='#orderItem.book.bauther'/></td>
	    			<td>数量:<s:property value='#orderItem.count'/></td>
	    			<td>小计:<s:property value='#orderItem.total'/></td>
	    		</tr>
	    	</s:iterator> 
    	</s:iterator>
    </table>
  </body>
</html>
