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
<table>
<%
	List<User> userList = (List<User>)session.getAttribute("userList");
	for(User user : userList) {
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
		<td colspan="2"><hr/></td>
	</tr>
<%
	}
%>
</table>
</body>
</html>