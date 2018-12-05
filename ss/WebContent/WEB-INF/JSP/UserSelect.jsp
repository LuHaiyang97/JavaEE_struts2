<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>路海洋 Struts2</title>
<script type="text/javascript">
	function del() {
		var msg = "您真的确定要删除吗？\n\n请确认！";
		if (confirm(msg)==true){
		return true;
		}else{
		return false;
		}
	}
	function show(tag) {
		 var a=document.getElementById(tag);
		 a.style.display="block";
	}
</script>
</head>
<body>
	<table align="center" border="2" width="600px">
		<tr>
			<td>序号</td>
			<td>ID</td>
			<td>用户名</td>
			<td>密码</td>
			<td>手机号</td>
			<td>年龄</td>
			<td>身份</td>
			<td>删除</td>
			<td>修改</td>
		</tr>
		<s:iterator value="list">
			<tr>
				<td><s:property value="rowNum"/></td>
				<td><s:property value="userId"/></td>
				<td><s:property value="userName"/></td>
				<td><s:property value="userPass"/></td>
				<td><s:property value="userPhone"/></td>
				<td><s:property value="userAge"/></td>
				<td><s:property value="userPermission"/></td>
				<td><a href="Delete.action?users.UserId=<s:property value="userId" />" 
				    onclick="javascript:return del();" >删除</a></td>	
				<td><input type="button" value="修改" 
				onclick="javascript:location.replace
				('toUpdate?users.UserId=<s:property value="userId" />')"></td>			
			</tr>
		</s:iterator>
		<tr>
		  <td colspan="3" align="center">
            <s:if test="#session.user.userPermission!=null">
              <a href="logout.action">注销登录</a>
            </s:if>
            <s:else><a href="logout.action">返回登录页面</a></s:else>
          </td>
          <td colspan="3" align="center">
            <s:if test="#session.user.userPermission=='admin'">
              <s:a href="toRegiste.action">添加用户</s:a>
            </s:if>
          </td>
          <td colspan="3" align="center">
          <s:if test="#session.user.userPermission!=null">
            <s:a href="index">返回首页</s:a>
          </s:if>
		  </td>
		</tr>
	</table>	
	<div style="margin-left:40%;">
	  <s:form action="selUserByCondition.action">
		选择查询方式<br>
		<input type="radio" name="queryObject.queryMethod" value="precisely" onclick="show(1)"/>精确查询
		<input type="radio" name="queryObject.queryMethod" value="vaguely" onclick="show(2)"/>模糊查询		
		<div id="1" style="display:none;">
		<s:radio  label="查询目标" name="queryObject.queryDestination" onclick="show(2)" 
			list="#{'1':'ID','2':'用户名','3':'年龄','4':'身份'}" theme="simple" />
		</div>
		<div id="2" style="display:none;">
		<input name="queryObject.queryValue" onclick="show(3)" />
		</div>
		<div id="3" style="display:none;">
		<input type="submit" value="查询" />
		</div>
	  </s:form>
	</div>

</body>
</html>