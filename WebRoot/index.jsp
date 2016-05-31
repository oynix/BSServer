<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style>
		
		td{
		 border-top:solid 1px #000000;
		 
		 border-left:solid 1px #000000;
		}
		table{
		
		 border-right:solid 1px #000000;
		 border-bottom:solid 1px #000000;
		}
	</style>
	
  </head>
  
  <body>
    <h1>你好，王俊阳。</h1><br>
    <h1>hello </h1><br>
    <h1>world</h1><br>
    <form action="./servlet/UploadFile" method="post">
    	<input type="file" name="file" />
    	<input type="submit" value="upload" />
    </form>
    -------------------------------------------
    <table cellpadding="0" cellspacing="0">
			<tr>
				<td>
					first
				</td>
				<td>
					<form>
						<input value="a" type="hidden"/>
						<input value="b" type="hidden"/>
					</form>
					sjflsjfls
				</td>
			</tr>
			<tr>
				<td>
					first
				</td>
				<td>
					second
				</td>
			</tr>
		</table>
  </body>
</html>
