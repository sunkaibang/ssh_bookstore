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
    
    <title>My JSP 'desc.jsp' starting page</title>
    
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
  	<div>
  		<img width="150" height="250" border="0" alt="图书" src='<s:property value="book.bimage"/>'/>
  	</div>
    	
    <ul>
    	<li>书名:<s:property value="book.bname" /></li>
    	<li>作者:<s:property value="book.bauther" /></li>
    	<li>单价:<s:property value="book.bprice"/>元</li>
    </ul>
    <form id="form" action="${pageContext.request.contextPath}/cart_addCartItem" method="post">
    	<input type="hidden" name="bid" value='<s:property value="book.bid"/>' >
    	<input type="text" size="3" name="cartItem.count" value="1">
    </form>
    <a href="javascript:document.getElementById('form').submit();">购买</a>
  </body>
</html>
