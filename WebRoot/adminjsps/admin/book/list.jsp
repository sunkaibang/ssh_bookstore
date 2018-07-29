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
		div{
			float:left;
			width:180px;
			height:280px;
		}
	</style>
	
	<style type="text/css">
		@import  url("<c:url value='/adminjsps/css/background.css'/>")
	</style>
	
	<style type="text/css">
		@import  url("<c:url value='/jsps/css/init.css'/>")
	</style>

  </head>
  
  <body>
    <s:iterator value="listBooks" var="book">
    	<div >
    	    <center>
	    	    <a href='${pageContext.request.contextPath }/adminBook_findByBid?book.bid=<s:property value="#book.bid"/>'>
	    	    	<img width="150" height="250" border="0" alt="图书" src='<s:property value="#book.bimage"/>'>
	    	    </a>
	    		
	    		<br/>
	    		<a href='${pageContext.request.contextPath }/adminBook_findByBid?book.bid=<s:property value="#book.bid"/>'>
	    			<s:property value='#book.bname'/>
	    		</a>
    		</center>
    	</div>
    </s:iterator>
  </body>
</html>
