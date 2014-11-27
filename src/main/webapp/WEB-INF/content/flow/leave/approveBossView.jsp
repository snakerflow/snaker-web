<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>请假流程</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/snaker/dialog.js" type="text/javascript"></script>
	</head>

	<body>
		<form id="inputForm" action="" method="post" target="mainFrame">
			<input type="hidden" name="processId" value="${processId }" />
			<input type="hidden" name="orderId" value="${orderId }" />
			<input type="hidden" name="taskId" value="${taskId }" />
			<c:forEach items="${vars}" var="item">
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>总经理审批结果：</span>
				</td>
				<td class="td_table_2" colspan="3">
					&nbsp;${item['method'] == '0' ? '同意' : '' }${item['method'] == '-1' ? '不同意' : '' }
				</td>
			</tr>
			<tr>
				<td class="td_table_1">
					<span>总经理审批意见：</span>
				</td>
				<td class="td_table_2" colspan="3">
					&nbsp;${item['approveBoss.suggest'] }
				</td>
			</tr>
			</table>
			</c:forEach>
		</form>
	</body>
</html>
