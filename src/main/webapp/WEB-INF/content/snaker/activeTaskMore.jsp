<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body>
	<form id="mainForm" action="${ctx}/snaker/task/active/more" method="get">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="taskType" id="taskType" value="${taskType}"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					${taskType == 0 ? '待办任务' : '协办任务' }
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td align=center width=15% class="td_list_1" nowrap>
					流程名称
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					流程编号
				</td>
				<td align=center width=15% class="td_list_1" nowrap>
					流程启动时间
				</td>
				<td align=center width=15% class="td_list_1" nowrap>
					任务名称
				</td>
				<td align=center width=15% class="td_list_1" nowrap>
					任务创建时间
				</td>
				
				<td align=center width=10% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<c:forEach items="${page.result}" var="item">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${item.processName}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.orderNo}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.orderCreateTime}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.taskName}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.taskCreateTime}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						<a href="${ctx}/snaker/process/display?processId=${item.processId }&orderId=${item.orderId} " class="btnView" title="查看">查看</a>
						<a href="${ctx}${empty item.instanceUrl ? item.actionUrl : item.instanceUrl }?processId=${item.processId }&taskId=${item.taskId}&orderId=${item.orderId} " class="btnEdit" title="处理">处理</a>
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }"/>
		</table>
		</form>
	</body>
</html>
