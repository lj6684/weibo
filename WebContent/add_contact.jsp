<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="weibo.dao.model.*" %>
<%@ page import="weibo.dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 未来可以改进使用Ajax方式添加关注 -->
<%
String tid = request.getParameter("tid");
User user = (User)session.getAttribute("user");
ContactDAO contactDAO = new ContactDAO();
int res = contactDAO.addContact(user.getUid(), Integer.parseInt(tid));
if(res == 1) {
	request.setAttribute("info", "关注成功");
} else {
	request.setAttribute("errorMsg", "关注失败");
}
%>
<script type="text/javascript">
	history.back();
</script>
</body>
</html>