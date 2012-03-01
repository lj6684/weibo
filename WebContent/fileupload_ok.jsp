<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String userName = (String)request.getAttribute("userName");
String age = (String)request.getAttribute("age");
String imgName = (String)request.getAttribute("imgName");
%>
<h1><%=userName %></h1><br/>
<h2><%=age %></h2><br/>
<img src="upload/<%=imgName %>"/>
</body>
</html>