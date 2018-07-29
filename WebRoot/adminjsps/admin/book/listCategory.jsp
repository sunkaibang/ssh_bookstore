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
    
    <title>My JSP 'listCategory.jsp' starting page</title>
    
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
	<style type="text/css">
	
		span{
			float:left;
			width:180px;
			height:280px;
		}
		#a{
			clear:both;
			
		}
	</style>

  </head>
  
  <body>
  	<s:if test="pageBean.list.size()==0">
  		没有内容!!!
  	</s:if>
  	<s:else>
	  	<s:iterator value="pageBean.list" var="book">
	  		<span id="book">
	    	    <a href='${pageContext.request.contextPath }/adminBook_findByBid?book.bid=<s:property value="#book.bid"/>'>
	    	    	<img width="150" height="250" border="0" alt="图书" src='<s:property value="#book.bimage"/>'>
	    	    </a>
	    		
	    		<br/>
	    		<a href='${pageContext.request.contextPath }/adminBook_findByBid?book.bid=<s:property value="#book.bid"/>'>
	    			<s:property value='#book.bname'/>
	    		</a>
	    	</span>
	  	</s:iterator>
	  	<p id="a">
	  		<br />
	  		
	    	当前页<s:property value='pageBean.currentPage'/>/总页数<s:property value='pageBean.totalPage'/>
	    	<a href="${pageContext.request.contextPath}/adminBook_categoryFind?<s:property value='pageBean.url'/>pageBean.currentPage=1">首页 </a> 
	    	<s:if test="pageBean.currentPage > 1">
	    		<a href="${pageContext.request.contextPath}/adminBook_categoryFind?<s:property value='pageBean.url'/>pageBean.currentPage=<s:property value='pageBean.currentPage-1'/>">上一页  </a>
	    	</s:if>
	    	
	    	
	    	<s:if test="pageBean.totalPage < 3">
	    		<s:set name="begin" value="1"/>
	    		<s:set name="end" value="pageBean.totalPage"/>
	    	</s:if>
	    	<s:else>
	    		<s:set name="begin" value="pageBean.currentPage - 1"/>
	    		<s:set name="end" value="pageBean.currentPage + 1"/>
	    		
	    		<s:if test="#begin < 1">
	    			<s:set name="begin" value="1"/>
	    			<s:set name="end" value="3"/>
	    		</s:if>
	    		<s:if test="#end > pageBean.totalPage">
	    			<s:set name="begin" value="pageBean.totalPage - 2"/>
	    			<s:set name="end" value="pageBean.totalPage"/>
	    		</s:if>
	    	</s:else>
	    	
	    	
	    	<s:iterator var="i" begin="begin" end="end">
	    		<s:if test="#i == pageBean.currentPage">
	    			[<s:property value="i"/>]  
	    		</s:if>
	    		<s:else>
	    			<a href="${pageContext.request.contextPath}/adminBook_categoryFind?<s:property value='pageBean.url'/>pageBean.currentPage=<s:property value='#i'/>">
	    				[<s:property value="i"/>]  
	    			</a>
	    		</s:else>
	    	</s:iterator>
	    	
	    	<s:if test="pageBean.currentPage < pageBean.totalPage">
	    		<a href="${pageContext.request.contextPath}/adminBook_categoryFind?<s:property value='pageBean.url'/>pageBean.currentPage=<s:property value='pageBean.currentPage+1'/>">下一页  </a>
	    	</s:if>
	    	<a href="${pageContext.request.contextPath}/adminBook_categoryFind?<s:property value='pageBean.url'/>pageBean.currentPage=<s:property value='pageBean.totalPage'/>">
	    		尾页  
	    	</a>
	    </p>
	 </s:else>
  </body>
</html>
