<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="weibo.dao.model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="MessageServlet?act=pub" method="post">
	<table width="400px">
		<tr>
			<td><img src="img/new.png" width="220px" height="22px"></td>
		</tr>
		<tr>
			<td><textarea rows="6" cols="60" name="content"></textarea></td>
		</tr>
		<tr>
			<td align="right"><input type="submit" value="发布"/>&nbsp;&nbsp;<input type="reset" value="重写"/></td>
		</tr>
	</table>
</form>
<hr/>
<%
User user = (User)session.getAttribute("user");
List<Message> messages = (List<Message>)request.getAttribute("messages");

String info = (String)request.getAttribute("info");
if(info != null) {
	%><div style="background-color:yellow"><%=info %></div><%
}
String errorMsg = (String)request.getAttribute("errorMsg");
if(errorMsg != null) {
	%><div style="background-color:red"><%=errorMsg %></div><%
}
%>
<table width="600px">
<%
for(Message message : messages) {
%>
	<tr>
		<td valign="top" width="70px"><img src="<%=message.getAuthorImg() %>" width="50px" height="50px"></td>
		<td><font color="#6EAFD5"><%=message.getAuthorName() %></font>:&nbsp;<%=message.getContent() %></td>		
	</tr>
	<tr>
		<td colspan="2" align="right">
		<span align="left"><font color="999999" size="-2"><%=message.getCreateTime().substring(0, 19) %></font></span>
		<span align="right"><a href="#">转发</a>&nbsp;&nbsp;<a href="#">收藏</a>&nbsp;&nbsp;<a href="#">评论</a></span>
		</td>
	</tr>
	<tr>
		<td colspan="2" height="25px"><hr/></td>
	</tr>
<%
}
%>
</table>
</body>
</html>