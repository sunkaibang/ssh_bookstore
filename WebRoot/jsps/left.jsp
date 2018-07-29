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
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<c:forEach items="${categoryList }" var="category">
  		<div align="center">
  			<a href="<c:url value='/BookServlet?method=findByCategory&cid=${category.cid }'/>" target="body">${category.cname }分类</a>
  		</div>
  	</c:forEach>
	-->
	<style type="text/css">
		@import url("<c:url value='/jsps/css/background.css'/>")
	</style>
	<style type="text/css">
		@import  url("<c:url value='/jsps/css/init.css'/>")
	</style>

  </head>
  
  <body>
  	<div align="center">
  		<a href="${pageContext.request.contextPath }/category_findAll">全部分类</a>
  	</div>
  	
  	
  	<s:iterator value="categoryList" var="category">
  		<div align="center">
  			<a href="${pageContext.request.contextPath }/book_findByCategory?cid=<s:property value="#category.cid"/>" target="body">
  				<s:property value="#category.cname"/>
  			</a>
  		</div>
  	</s:iterator>
  	
  </body>
</html>
