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
	<s:if test="#session.user.userPermission=='admin'">
		<s:a href="SelectUser.action">查询用户信息</s:a><br/>
	</s:if>
	<s:if test="#session.user.userPermission==null">
		<h2>还未登录，请登录！</h2>
	</s:if>
	<s:a href="toLogin">返回登录页面</s:a><br/>
	<s:if test="#session.user.userPermission!=null">
		<a href="toUpdate?users.UserId=<s:property value="#session.user.userID"/>">修改个人信息</a>
		<a href="logout.action">注销登录</a>
	</s:if>

</body>
</html>