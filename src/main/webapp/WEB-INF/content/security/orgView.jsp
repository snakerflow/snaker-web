<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>部门管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	</head>

	<body>
		<form id="inputForm" action="" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1">
						<span>部门名称：</span>
					</td>
					<td class="td_table_2" colspan="3">
						${org.name }&nbsp;
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>上级部门：</span>
					</td>
					<td class="td_table_2" colspan="3">
						${org.parentOrg.name }&nbsp;
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>部门描述：</span>
					</td>
					<td class="td_table_2" colspan="3">
						${org.description }&nbsp;
					</td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
