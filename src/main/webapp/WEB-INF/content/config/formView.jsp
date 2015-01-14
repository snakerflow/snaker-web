<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>表单管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script type="text/javascript" charset="utf-8" src="${ctx }/styles/js/jquery-1.8.3.min.js"></script>
	</head>

	<body>
		<form id="inputForm" action="" method="post">
			<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
				<tr>
					<td class="td_table_top" align="center">
						表单查看
					</td>
				</tr>
			</table>
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>表单名称：</span>
					</td>
					<td class="td_table_2">
						&nbsp;${form.name }
					</td>
					<td class="td_table_1">
						<span>表单类别：</span>
					</td>
					<td class="td_table_2">
						<frame:select name="type" type="select" configName="formType" cssClass="input_select" value="${form.type }" displayType="1"/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>显示名称：</span>
					</td>
					<td class="td_table_2" colspan="3">
						&nbsp;${form.displayName }
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
