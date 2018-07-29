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
    
    <title>My JSP 'category.jsp' starting page</title>
    
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
  </head>
  
  <body>
  	<h2>高级查询</h2>
    <form action="${pageContext.request.contextPath}/adminBook_categoryFind" method="post">
    	名称:<input type="text" name="book.bname"><br/>
    	作者:<input type="text" name="book.bauther"><br />
    	种类:<select name="category.cid">
    			<option value="-1">---请选择---</option>
   				<s:iterator value="categoryLists" var="category">
   					<option value="<s:property value='#category.cid'/>" >
   						<s:property value='#category.cname'/>
   					</option>
   				</s:iterator>
    			</select><br/>
    	 <input type="submit" value="查找">
    </form>
  </body>
</html>
