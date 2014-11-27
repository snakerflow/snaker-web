<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>请假流程</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/snaker/snaker.util.js" type="text/javascript"></script>
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
					<td class="td_table_1"><span>请假人名称：</span></td>
					<td class="td_table_2" colspan="3">
						&nbsp;${item['apply.operator']}
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>请假理由：</span></td>
					<td class="td_table_2" colspan="3">
						&nbsp;${item['reason'] }
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>请假天数：</span></td>
					<td class="td_table_2" colspan="3">
						&nbsp;${item['day'] }天
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>部门经理：</span></td>
					<td class="td_table_2">
						&nbsp;${item['approveDept.operator']}
					</td>
					<td class="td_table_1"><span>总经理：</span></td>
					<td class="td_table_2">
						&nbsp;${item['approveBoss.operator']}
					</td>
				</tr>
			</table>
			</c:forEach>
		</form>
	</body>
</html>
