<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
</head>
<body>
<form action="LoginServlet" method="post">
<%
String errorMsg = (String)request.getAttribute("errorMsg");
if(errorMsg != null) {
	%><div style="width:300px; background-color:red;"><%=errorMsg %></div><%
}
%>
<table>
	<tr>
		<td>用户名</td>
		<td><input type="text" name="userName" /></td>
	</tr>
	<tr>
		<td>密码</td>
		<td><input type="password" name="password" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="登陆"/>&nbsp;&nbsp;<a href="regist.jsp">新用户注册</a></td>
	</tr>
</table>
</form>

<script type="text/javascript">
function regist() {
	window.location = "regist.jsp";
}
</script>
</body>
</html>