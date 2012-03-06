<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="weibo.dao.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
User user = (User)session.getAttribute("user");
%>
<table>
	<tr>
		<td width="70px" align="center"><img src="<%=user.getHeadImg() %>" width="50px" height="50px"/></td>
		<td>
			<%=user.getNickName() %><br/>
			<%=user.getLocal() %>
		</td>
	</tr>
</table>
<table>
	<tr>
		<td width="50px" align="center"><a href="ListUserServlet?type=target"><%=user.getFollowCount() %></a></td>
		<td width="50px" align="center"><a href="ListUserServlet?type=fans"><%=user.getFansCount() %></a></td>
		<td width="50px" align="center"><b><%=user.getMessageCount() %></b></td>
	</tr>
	<tr>
		<td align="center">关注</td>
		<td align="center">粉丝</td>
		<td align="center">微博</td>
	</tr>
</table>
<p/>
<table>
	<tr>
		<td height="30px" bgcolor=""><font size="+2">我的首页</font></td>
	</tr>
	<tr>
		<td><a href="#">@提到我的</a></td>
	</tr>
	<tr>
		<td><a href="#">我的评论</a></td>
	</tr>
	<tr>
		<td><a href="#">我的收藏</a></td>
	</tr>
</table>
<p/>
<form action="FindUserServlet" method="post">
<table>
	<tr>
		<td height="30px" bgcolor=""><font size="+2">查找好友</font></td>
	</tr>
	<tr>
		<td>好友昵称</td>
	</tr>
	<tr>
		<td><input type="text" name="nickName"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="查找" /></td>
	</tr>
</table>
</form>
</body>
</html>