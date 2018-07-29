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
    
    <title>My JSP 'add.jsp' starting page</title>
    
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
	
	<script type="text/javascript">
		/* var isSubmit="false";
		
		$(function(){
			$("#bname").keyup(function(){
				$.post("${pageContext.request.contextPath }/AjaxServlet?method=bnameAjax",{
					bname:$("#bname").val()
				},function(date){
					isSubmit=date;
					if(date=="true"){
						$("#bnameInfo").html("");
					}else{
						$("#bnameInfo").html("书名已经存在");
					}
				}
				)
			});
		});
		
		function mySubmit(){
				if(isSubmit=="true"){
					document.getElementById("form").submit();
				}else{
					alert("添加失败");
				}
			} */
		$(function(){
			var selected = "<s:property value='book.category.cid'/>";
			var selectNode = document.getElementById("cidIndex");
			for (var i = 0; i < selectNode.length; i++) {
				if (selectNode[i].value == selected) {
					selectNode.selectedIndex = i;
				}
			}
		});
	</script>

  </head>
  
  <body>
    <h2>添加图书</h2>
    <p style="font-weight:900;color:red"><s:property value='msg'/></p>
    <form id="form" action='${pageContext.request.contextPath}/adminBook_addBook' method="post" enctype="multipart/form-data">
    	图书名称:<input id="bname" type="text" name="book.bname" value="<s:property value='book.bname'/>"><span id="bnameInfo"></span><br/>
    	图书图片:<input type="file" name="bookImage"><br/>
    	图书单价:<input type="text" name="book.bprice" value="<s:property value='book.bprice'/>"><br/>
    	图书作者:<input type="text" name="book.bauther" value="<s:property value='book.bauther'/>"><br/>
    	图书分类:<select name="category.cid" id="cidIndex">
    				<s:iterator value="categoryLists" var="category">
    					<option value="<s:property value='#category.cid'/>" >
    						<s:property value='#category.cname'/>
    					</option>
    				</s:iterator>
    			</select><br/>
    			<input type="submit" value="提交">
    </form>
  </body>
</html>
