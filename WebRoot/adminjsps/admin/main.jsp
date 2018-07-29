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
    
    <title>书城管理页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table width="1495" height="714" border="1" cellspacing="0" cellpadding="0">
	<tr style="background-color:#808080;">
    	<td height="137" colspan="2"><div align="center">
    	  <p style="font-size:24px"><strong>BOOKSTORE书城管理</strong></p>
    	  <p align="left">&nbsp;</p>
    	  <p align="left">你好：管理员    	</p>
    	</div></td>
    </tr>
    <tr>
		<td width="160" align="left" valign="top">
			<iframe name="left" src="<c:url value='/adminjsps/admin/left.jsp'/>" height="550"  width="100%" frameborder="0" ></iframe>
		</td>
        <td width="1304" align="left" valign="top">
        	<iframe name="body" src="<c:url value='/adminjsps/admin/body.jsp'/>" height="550"  width="100%" frameborder="0"></iframe>
        </td>
    </tr>
</table>
  </body>
</html>
