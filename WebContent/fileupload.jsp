<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="FileUploadServlet" method="post" enctype="multipart/form-data">
	姓名:<input type="text" name="userName" /><br/>
	年龄:<input type="text" name="age"/><br/>
	图片:<input type="file" name="image" /><br/>
	<input type="submit" value="提交"/>
</form>
</body>
</html>