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
    
    <title>书城注册</title>
    
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
  	<center>
    <h2>书城注册</h2>
    <p style="color:red;font-width:900"><s:property value="registerMessage"/></p><br/>
    <form action="${pageContext.request.contextPath }/user_register.action" method="post" >
    	用户名:<input type="text" name="username" value='<s:property value="user.username"/>'>
    	<span style="color:red;font-width:900">${errors.username }</span>
    	<br/><br/>
    	密码:<input type="password" name="password" value=""/>
    	<span style="color:red;font-width:900">${errors.password }</span>
    	<br/><br/>
    	邮箱:<input type="text" name="email"><br/><br/>
    	<input type="submit" value="注册">
    </form>
    </center>
  </body>
</html>
