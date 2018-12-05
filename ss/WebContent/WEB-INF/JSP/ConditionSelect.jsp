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
	<s:form action="selByCondition" method="post">
		<h2>选择查询方式</h2>
		<s:radio  label="查询方式" name="users.userPermission" 
		list="{'ID','用户名','年龄','身份'}" ></s:radio>
	</s:form>
</body>
</html>