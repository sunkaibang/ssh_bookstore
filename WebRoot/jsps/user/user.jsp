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
    
    <title>书城用户信息</title>
    
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
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/js-libs/my_tools.js"></script>
	<script type="text/javascript">
		onload = function() {
			initDate();
			setDateToSelect();
			loadImgFromInputFile(100, 100);
		};
		
		function setDateToSelect() {
			var birthday = "${sessionScope.session_user.birthday}";
			var date = birthday.split("-");
			var year = date[0];
			var month = date[1];
			var day = date[2];
			
			if (year == "" || month == "" || day == "") {
				return;
			}
			
			var yearNode = document.getElementById("year");
			var monthNode = document.getElementById("month");
			var dayNode = document.getElementById("day");
			var newYear = new Date().getFullYear();
			yearNode.selectedIndex = newYear - parseInt(year) + 1;
			monthNode.selectedIndex = parseInt(month);
			changeDay();
			dayNode.selectedIndex = parseInt(day);
			
		};
	</script>

  </head>
  
  <body>
  	<center>
  	<h2>用户详细信息</h2>
  	<p style="color:red;font-width:900"><s:property value="registerMessage"/></p><br/>
    <form action="${pageContext.request.contextPath }/user_edit" method="post" enctype="multipart/form-data">
    	头像:<img id="img" alt="头像加载失败..." src="${pageContext.request.contextPath }/${sessionScope.session_user.headPicture}"><br/><br/>
    	<input id="img_input" type="file" name="headFile" /> <br /><br/>
    	用户名:<input type="text" name="username" value="${sessionScope.session_user.username }"><br/><br/>
    	密码:<input type="text" name="password" value="${sessionScope.session_user.password }" /><br/><br/>
    	电子邮件:<input type="text" name="email" value="${sessionScope.session_user.email }"/><br/><br/>
	    出生日期:	
			<select id="year" name="birthday">
				<option value="none">请选择</option>
			</select>年
			
			<select id="month" name="birthday">
				<option value="none">请选择</option>
			</select>月
			
			<select id="day" name="birthday">
				<option value="none">请选择</option>
			</select>日 <br/><br/>
    	<input type="submit" value="提交" />
    </form>
    </center>
  </body>
</html>
