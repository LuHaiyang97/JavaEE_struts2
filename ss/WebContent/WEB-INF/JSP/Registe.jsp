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
	<s:form action="Registe.action" method="post" validate="true">
		<s:textfield name="users.userName" label="用户名"/>
		<s:password name="users.userPass" label="密码"/>
		<s:password name="rePass" label="确认密码"/>
		<s:textfield name="users.userPhone" label="手机号"/>
		<s:textfield name="users.userBirthday" label="出生日期"/>
		<s:textfield name="users.userAge" label="年龄"/>
		<s:radio  label="身份" name="users.userPermission" list="#{'teacher':'教师','student':'学生'}" ></s:radio>
		<s:submit value="添加"/>
	</s:form><br>
	<s:a href="SelectUser.action">返回查询页面</s:a><br/>

</body>
</html>