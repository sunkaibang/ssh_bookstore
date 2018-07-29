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
    
    <title>My JSP 'desc.jsp' starting page</title>
    
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
	<script type="text/javascript" src="${pageContext.request.contextPath }/js-libs/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js-libs/my_tools.js"></script>
	<script type="text/javascript">
		function setMethod(method){
			var ele=document.getElementsByTagName("form")[0];
			if (method == "modify") {
				ele.action = "${pageContext.request.contextPath}/adminBook_modifyBook";
			} else if (method == "delete") {
				ele.action = "${pageContext.request.contextPath}/adminBook_deleteBook"
			}
		}
		
		onload = function() {
			// 加载修改图片
			loadImgFromInputFile(150,250);
			
			// 选择书的种类
			var selected = "<s:property value='book.category.cid'/>";
			var selectNode = document.getElementById("cidIndex");
			for (var i = 0; i < selectNode.length; i++) {
				if (selectNode[i].value == selected) {
					selectNode.selectedIndex = i;
				}
			}
			
		}
	</script>

  </head>
  
  <body>
    <div>
  		<img id="img" width="150" height="250" border="0" alt="图书" src='<s:property value="book.bimage"/>'/>
  	</div>
    <br/>
    <form  method="post" enctype="multipart/form-data">
    	<input type="hidden" name="book.bid" value='<s:property value="book.bid"/>'/>
    	<input type="hidden" name="book.bimage" value='<s:property value="book.bimage"/>'>
    	图书书名:<input type="text" name="book.bname" value='<s:property value="book.bname"/>'><br/>
    	图书图片:<input id="img_input" type="file" name="bookImage" /> <br />
    	图书作者:<input type="text" name="book.bauther" value='<s:property value="book.bauther"/>'><br/>
    	图书单价:<input type="text" name="book.bprice" value='<s:property value="book.bprice"/>'><br/>
    	图书分类:<select style="width:150px;height:20px;" name="category.cid" id="cidIndex">
    				<s:iterator value="categoryLists" var="category">
    					<option value='<s:property value="#category.cid"/>' >
    						<s:property value="#category.cname"/>
    					</option>
    				</s:iterator>
    			</select><br/>
    	<input type="submit" value="删除" onclick="setMethod('delete')"/>
    	<input type="submit" value="修改" onclick="setMethod('modify') "/>
    </form>
    <p style="font-weight:900;color:red"><s:property value="msg"/></p>
  </body>
</html>
