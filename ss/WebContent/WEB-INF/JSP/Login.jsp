<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>路海洋 Struts2</title>
</head>
<body>
	<s:form action="Login.action" method="post">
		<s:textfield name="users.userName" label="用户名"/>
		<s:password name="users.userPass" label="密码"/>
		<s:submit value="登录"/>
	</s:form>
</body>
</html>