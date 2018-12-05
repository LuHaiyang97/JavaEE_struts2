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
	<s:form action="update.action" method="post" validate="true">
		<s:textfield name="users.userId" label="ID" readonly="true" />
		<s:textfield name="users.userName" label="用户名" />
		<s:textfield name="users.userPass" label="密码"/>
		<s:textfield name="users.userPhone" label="手机号"></s:textfield>
		<s:submit value="修改"></s:submit>
		<s:if test="#session.user.userPermission=='admin'">
			<s:a href="SelectUser.action">返回查询页面</s:a><br/>
		</s:if>
		<s:a href="index">返回首页</s:a>
	</s:form>

</body>
</html>