<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="weibo.dao.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="info.jsp">返回</a>
<p/>
<%
String info = (String)request.getAttribute("info");
if(info != null) {
	%><div style="background-color:yellow"><%=info %></div><%
}
String errorMsg = (String)request.getAttribute("errorMsg");
if(errorMsg != null) {
	%><div style="background-color:red"><%=errorMsg %></div><%
}

List<User> users = (List<User>)request.getAttribute("users");

if(users.size() == 0) {
	%><h2>用户不存在 <a href="space.html">[返回]</a></h2><%
} else {
%>
<table>
<%
	for(User user : users) {
%>
		
	<tr>
		<td width="70px"><img src="<%=user.getHeadImg() %>"/></td>
		<td align="left">
		<font size="+2"><%=user.getNickName() %></font><br/>
		来自:<%=user.getLocal() %><br/>
		微博:<%=user.getMessageCount() %>
		&nbsp;&nbsp;关注:<%=user.getFollowCount() %>
		&nbsp;&nbsp;粉丝:<%=user.getFansCount() %>
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" value="+加关注" onclick="window.location='add_contact.jsp?tid=<%=user.getUid()%>'"/></td>
	</tr>
	<tr>
		<td colspan="2"><hr/></td>
	</tr>
<%
	} 
}
%>
</table>
</body>
</html>