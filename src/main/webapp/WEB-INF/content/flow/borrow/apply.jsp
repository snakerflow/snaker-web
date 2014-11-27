<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>借款流程</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/snaker/snaker.util.js" type="text/javascript"></script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/flow/borrow/applySave" method="post" target="mainFrame">
			<input type="hidden" name="processId" value="${processId }" />
			<input type="hidden" name="orderId" value="${orderId }" />
			<input type="hidden" name="taskId" value="${taskId }" />
			<input type="hidden" name="borrow.id" value="${borrow.id }" />
			<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1"><span>借款人：</span></td>
					<td class="td_table_2">
						&nbsp;<shiro:principal/>
					</td>
					<td class="td_table_1"><span>借款日期：</span></td>
					<td class="td_table_2">
						&nbsp;${empty operateTime ? borrow.operateTime : operateTime }
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>借款数额：</span></td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="amount" name="amount" value="${borrow.amount }" />元
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>借款理由：</span></td>
					<td class="td_table_2" colspan="3">
						<textarea class="input_textarea_320" id="description" name="description">${borrow.description }</textarea>
					</td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<input type="submit" class="button_70px" name="submit" value="提交">
						&nbsp;&nbsp;
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
