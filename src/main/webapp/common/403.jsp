<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<title>403 - 缺少权限</title>
</head>

<body>
<div>
	<div><h1>你没有访问该页面的权限.</h1></div>
	<div><a href="javascript:void(0)" onclick="history.back()">返回</a>
	<a href="javascript:void(0)" onclick="window.parent.location.href='${ctx }/logout'">退出登录</a></div>
</div>
</body>
</html>