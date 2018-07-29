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
		@import  url("<c:url value='/adminjsps/css/background.css'/>")
	</style>
	<style type="text/css">
		@import  url("<c:url value='/jsps/css/init.css'/>")
	</style>
	

  </head>
  
  <body>
    <h2>分类列表</h2>
    <table  border="1" cellpadding="0" cellspacing="0" width="400">
     	<tr id="th" bordercolor="rgb(78,78,78)">
     		<th>分类名称</th>
     		<th>操作</th>
     	</tr> 
     	<s:iterator value="categoryList" var="category">
     		<tr id="th" bordercolor="rgb(78,78,78)">
     			<td><s:property value='#category.cname'/></td>
     			<td>
     				<a onclick="return confirm('您真要删除吗?')" 
     				href="${pageContext.request.contextPath}/adminCategory_delCategory?cid=<s:property value='#category.cid' />">删除</a>
     				||
     				<a href="${pageContext.request.contextPath}/adminCategory_modCategoryAfter?cid=<s:property value='#category.cid' />">修改</a>
     			</td>
     		</tr>
     	</s:iterator>
    </table>
  </body>
</html>
