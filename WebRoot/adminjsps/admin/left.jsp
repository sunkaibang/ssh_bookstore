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
	-->
	<style type="text/css">
			table ul{
				list-style: none;
				padding-left:45px;
				margin: 0px;
				color:#6495ED ;
			}
			table{
				border: 1px solid sandybrown;
				border-collapse:collapse;
				width:150px;
			}
			table td{
				border: 1px solid darkgoldenrod;
				background-color: #00CED1;
			}
			table tr td a:link,table tr td a:visited{
				text-decoration: none;
				color: coral;
			}
			
			table tr td a:hover,table tr td a:active{
				color:#000000;
			}
			table tr td a{
			}
			
			.open{
				display: block;
			}
			
			.close{
				display: none;
			}
			
        </style>
        <style type="text/css">
			@import  url("<c:url value='/adminjsps/css/background.css'/>")
		</style>

  </head>
  
  <body>
    <script type="text/javascript">
			var isReject=false;
			function list(nd){
				var otd=nd.parentNode;
				var oul=otd.getElementsByTagName("ul")[0];
				var otableNode=document.getElementById("goodList");
				var collsUlNode=otableNode.getElementsByTagName("ul");
				if(isReject==true){
					for(var i=0;i<collsUlNode.length;i++){
						if(collsUlNode[i]==oul)
						{
							if(collsUlNode[i].className=="close")
								collsUlNode[i].className="open";
							else
								collsUlNode[i].className="close";
						}
						else
							collsUlNode[i].className="close";
					}
				}else{
					for(var i=0;i<collsUlNode.length;i++){
						if(collsUlNode[i]==oul)
						{
							if(collsUlNode[i].className=="close")
								collsUlNode[i].className="open";
							else
								collsUlNode[i].className="close";
						}
					}	
				}
			}
		</script>
		
		 
		<table id="goodList" >
			<tr>
				<td>
					<a href="javascript:void(0)" onclick="list(this)" >分类管理</a>
					<ul class="close" style="background-color: #D8BFD8;">
						<li><a href="${pageContext.request.contextPath}/adminCategory_findAll" target="body">查看分类</a></li>
						<li><a href="${pageContext.request.contextPath}/adminjsps/admin/category/add.jsp" target="body">添加分类</a></li>
					</ul>
					
				</td>
			</tr>
			
			<tr>
				<td>
					<a href="javascript:void(0)" onclick="list(this)" >图书管理</a>
					<ul class="close" style="background-color: #D8BFD8;">
						<li><a href='${pageContext.request.contextPath}/adminBook_findAll' target="body">所有图书</a></li>
						<li><a href='${pageContext.request.contextPath}/adminBook_addBookAfter'  target="body">添加图书</a></li>
						<li><a href='${pageContext.request.contextPath}/adminBook_categoryFindFirst' target="body">高级查询</a></li>
					</ul>
					
				</td>
			</tr>
			
			<tr>
				<td>
					<a href="javascript:void(0)" onclick="list(this)" >订单管理</a>
					<ul class="close" style="background-color: #D8BFD8;">
						<li><a href="${pageContext.request.contextPath}/adminOrder_findAllOrder" target="body">所有订单</a></li>
						<li><a href="${pageContext.request.contextPath}/adminOrder_findOrderByState?state=1" target="body">未付款订单</a></li>
						<li><a href="${pageContext.request.contextPath}/adminOrder_findOrderByState?state=2" target="body">未发货订单</a></li>
						<li><a href="${pageContext.request.contextPath}/adminOrder_findOrderByState?state=3" target="body">未确认订单</a></li>
						<li><a href="${pageContext.request.contextPath}/adminOrder_findOrderByState?state=4" target="body">交易成功订单</a></li>
					</ul>
					
				</td>
			</tr>
			
		</table>
  </body>
</html>
