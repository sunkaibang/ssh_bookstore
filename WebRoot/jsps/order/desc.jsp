<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'descc.jsp' starting page</title>
    
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
    <h1>当前订单</h1>
    <table border="1" width="100%" cellspacing="0" background="black">
    	<tr bgcolor="gray" bordercolor="gray">
    		<td colspan="6">
    			订单编号:${order.oid }  成交时间:<s:property value='order.date'/>
    			金额<font color="red"><s:property value='order.total'/>元</font>
    		</td>
    	</tr>
    	<s:iterator value="order.orderItems" var="orderItem">
    		<tr>
    			<td><div width="150" height="250" ><img width="150" height="250" border="0" alt="图书" src="<s:property value='#orderItem.book.bimage'/>"/></div></td>
    			<td>书名<s:property value='#orderItem.book.bname'/></td>
    			<td>单价:<s:property value='#orderItem.book.bprice'/>元</td>
    			<td>作者:<s:property value='#orderItem.book.bauther'/></td>
    			<td>数量:<s:property value='#orderItem.count'/></td>
    			<td>小计:<s:property value='#orderItem.total'/></td>
    		</tr>
    	</s:iterator>
    	<br/>
    	<form id="pay" method="post" target="_blank"
    	action="${pageContext.request.contextPath}/order_payByAlipay?oid=<s:property value='order.oid'/>" >
    		收货地址:<input type="text" name="address" size="50" /> <br />
    		
    	</form>
    	<a href="javascript:document.getElementById('pay').submit();">支付宝付款</a>
    </table>
  </body>
</html>
