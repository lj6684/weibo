<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="weibo.dao.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<META http-equiv="Refresh" content="3; url=space.html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册结果</title>
</head>
<body>
<%
User user = (User)request.getSession().getAttribute("user");
%>
<div align="center">
	<h2><%= user.getName() %>&nbsp;注册成功</h2><br/>
	<h3>请牢记用户名和口令</h3>
	<p/>
	3秒钟后页面将自动跳转，手动跳转请点击&nbsp;<a href="space.html">[这里]</a>
</div>
</body>
</html>