<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orderDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		@import  url("<c:url value='/adminjsps/css/background.css'/>")
	</style>
	<style type="text/css">
		@import  url("<c:url value='/jsps/css/init.css'/>")
	</style>
  </head>
  
  <body>
  	<h2>订单修改</h2>
  	<h4 font='red'><s:property value='msg'/></h4>
  	<form action="${pageContext.request.contextPath}/adminOrder_updateOrder?oid=<s:property value='order.oid'/>	" method="post">
  		订单编号:<s:property value='order.oid'/>	<br/>
    	订单时间:<s:property value='order.date'/>	<br/>
    	订单总价:<s:property value='order.total'/>	<br/>
    	订单地址:<input name="order.adress" value="<s:property value='order.adress'/>">	<br/>
    	订单状态:<select id="orderState" name="order.state">	
    	 	<option value="1">未付款</option>
    	 	<option value="2">未发货</option>
    	 	<option value="3">未确认</option>
    	 	<option value="4">交易完成</option>
    	</select>  <br/>
    	<input type="submit" value="修改"></input>
  	</form>
  </body>
  <script type="text/javascript">
  	function loadState() {
  		var state = "<s:property value='order.state'/>";
  		var orderStateNode = document.getElementById("orderState");
  		orderStateNode.selectedIndex = state - 1;
  	}
  	loadState();
  </script>
</html>
