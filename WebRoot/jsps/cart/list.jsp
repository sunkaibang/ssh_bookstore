<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <h1>购物车</h1>
    <c:choose>
    	<c:when test="${empty sessionScope.cart or fn:length(sessionScope.cart.cartItems) eq 0 }">
    		购物车空空如也
    	</c:when>
    	<c:otherwise>
    		<table border="1" width="100%" cellspacing="0" background="black">
		    	<tr>
		    		<td colspan="7" align="right" >
		    		  <a href="${pageContext.request.contextPath }/cart_clearCart">清空购物车</a>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>图片</th>
		    		<th>书名</th>
		    		<th>作者</th>
		    		<th>单价</th>
		    		<th>数量</th>
		    		<th>小计</th>
		    		<th>操作</th>
		    	</tr>
		    	<c:forEach items="${sessionScope.cart.cartItems }" var="cartItem">
		    	<tr>
		    		<td>
		    			<div width="150" height="250"><img width="150" height="250" border="0" alt="图书" src="${cartItem.book.bimage}"></div>
		    		</td>
		    		<td>${cartItem.book.bname }</td>
		    		<td>${cartItem.book.bauther }</td>
		    		<td>${cartItem.book.bprice }</td>
		    		<td>${cartItem.count }</td>
		    		<td>${cartItem.total }</td>
		    		<td><a href="${pageContext.request.contextPath}/cart_deleteCartItem?bid=${cartItem.book.bid}">删除</a></td>
		    	</tr>
		    	</c:forEach>
		    	
		    	<tr>
		    		<td colspan="7" align="right">
		    		合计:${sessionScope.cart.total }元
		    		</td>
		    	</tr>
		    	<tr>
		    		<td colspan="7" align="right">
		    		<a href="${pageContext.request.contextPath}/order_add">一键购买</a>
		    		</td>
		    	</tr>
    		</table>
    	</c:otherwise>
    </c:choose>
    
  </body>
</html>
