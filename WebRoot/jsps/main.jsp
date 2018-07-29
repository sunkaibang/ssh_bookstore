<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>书城</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		@import  url("<c:url value='/jsps/css/init.css'/>")
	</style>

  </head>
  
  <body>
  <center>
	<table width="1029" height="694" border="1"  cellspacing="0"  >
	  <tr id="head" style="background-color:#00FFFF;">
	  	<td height="134" colspan="2"><div align="center">
	  		<p><strong style="font-size:30px">BOOKSTORE书城</strong>    </p>
	  	<c:choose>
	  		<c:when test="${empty sessionScope.session_user }">
	  			<p align="justify"><a href="<c:url value='/jsps/user/login.jsp'/>">登录</a> | <a target="_blank" href="<c:url value='/jsps/user/register.jsp'/>">注册</a></p>
	  		</c:when>
	  		<c:otherwise>
	  			<p align="justify">欢迎:<a href="${pageContext.request.contextPath }/jsps/user/user.jsp" target="_blank">${sessionScope.session_user.username }</a> | 
	  			<a href="<c:url value='/jsps/cart/list.jsp'/>" target="body">购物车</a> | 
	  			<a href="${pageContext.request.contextPath}/order_findOrdersByUser" target="body">我的订单</a> |
	  			<a href="${pageContext.request.contextPath }/user_exit">退出</a></p>
	  		</c:otherwise>
	  	</c:choose>
	    </div></td>
	  </tr>
	  <tr>
	    <td width="142" height="558">
	      <iframe src="${pageContext.request.contextPath }/category_findAll" width="142" height="558"  frameborder="0" name="left" ></iframe>
	    </td>
	    <td width="729">
	      <iframe src="<c:url value='/jsps/body.jsp'/>" width="880" height="557" frameborder="0" name="body" ></iframe>
	    </td>
	  </tr>
	</table>
	</center>
  </body>
  
 </html>
