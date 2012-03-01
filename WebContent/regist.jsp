<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
</head>
<body>
<%
String errorMsg = (String)request.getAttribute("errorMsg");
if(errorMsg != null) {
	%><div style="width:300px; background-color:red;"><%=errorMsg %></div><%
}
%>
<form action="RegistServlet" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td>用户名(电子邮件地址)</td>
		<td><input type="text" name="userName" />&nbsp;<font color="red">*</font></td>
	</tr>
	<tr>
		<td>昵称</td>
		<td><input type="text" name="nickName" />&nbsp;<font color="red">*</font></td>
	</tr>
	<tr>
		<td>来自</td>
		<td><input type="text" name="local" />&nbsp;<font color="red">*</font></td>
	</tr>
	<tr>
		<td>请输入口令</td>
		<td><input type="password" name="password" />&nbsp;<font color="red">*</font></td>
	</tr>
	<tr>
		<td>再次输入口令</td>
		<td><input type="password" name="repassword" />&nbsp;<font color="red">*</font></td>
	</tr>
	<tr>
		<td>上传头像</td>
		<td><input type="file" name="headImg" />&nbsp;<font color="red">*</font></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="注册"/>&nbsp;&nbsp;<input type="reset" value="重置"/></td>
	</tr>
</table>
</form>
</body>
</html>