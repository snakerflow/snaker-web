<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>我的任务</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body>
	<form id="mainForm" action="${ctx}/snaker/task/active" method="get">
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="border-bottom: 0px; margin-bottom: 0px">
			<tr>
				<td class="td_table_top" align="left">
					待办任务<font color="red">[共:${majorTotal }项]&nbsp;&nbsp;<a href="${ctx}/snaker/task/active/more?taskType=0">更多...</a></font>
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
			<c:forEach items="${majorWorks}" var="item">
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
						<a href="${ctx}/snaker/process/display?processId=${item.processId }&orderId=${item.orderId} " class="btnPict" title="查看流程图">查看流程图</a>
						<a href="${ctx}${empty item.instanceUrl ? item.actionUrl : item.instanceUrl }?processId=${item.processId }&taskId=${item.taskId}&orderId=${item.orderId} " class="btnEdit" title="处理">处理</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${majorTotal < 5 }">
			<c:forEach begin="0" end="${4 - fn:length(majorWorks)}">
				<tr>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
				</tr>
			</c:forEach>
			</c:if>
		</table>
		
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="border-bottom: 0px; margin-bottom: 0px">
			<tr>
				<td class="td_table_top" align="left">
					协办任务<font color="red">[共:${aidantTotal }项]&nbsp;&nbsp;<a href="${ctx}/snaker/task/active/more?taskType=1">更多...</a></font>
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
			<c:forEach items="${aidantWorks}" var="item">
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
						<a href="${ctx}/snaker/process/display?processId=${item.processId }&orderId=${item.orderId} " class="btnPict" title="查看流程图">查看流程图</a>
						<a href="${ctx}${empty item.instanceUrl ? item.actionUrl : item.instanceUrl }?processId=${item.processId }&taskId=${item.taskId}&orderId=${item.orderId} " class="btnEdit" title="处理">处理</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${aidantTotal < 3 }">
			<c:forEach begin="0" end="${2 - fn:length(aidantWorks)}">
				<tr>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
				</tr>
			</c:forEach>
			</c:if>
		</table>
		
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="border-bottom: 0px; margin-bottom: 0px">
			<tr>
				<td class="td_table_top" align="left">
					抄送任务<font color="red">[共:${ccorderTotal }项]&nbsp;&nbsp;<a href="${ctx}/snaker/task/active/ccmore">更多...</a></font>
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
					启动时间
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					实例创建人
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					实例状态
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<c:forEach items="${ccorderWorks}" var="item">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${item.processName}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.orderNo}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.createTime}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.creator}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.orderState == 0 ? '已结束' : '运行中'}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						<a href="${ctx}/snaker/process/display?processId=${item.processId }&orderId=${item.id} " class="btnPict" title="查看流程图">查看流程图</a>
						<a href="${ctx}${empty item.instanceUrl ? item.actionUrl : item.instanceUrl }?processId=${item.processId }&orderId=${item.id}&type=cc " class="btnView" title="查看">查看</a>
						<a href="${ctx}/snaker/ccread?id=${item.id}&url=/snaker/task/active " class="btnRead" title="关闭">关闭</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${ccorderTotal < 3 }">
			<c:forEach begin="0" end="${2 - fn:length(ccorderWorks)}">
				<tr>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
					<td class="td_list_2" align=left nowrap>&nbsp;</td>
				</tr>
			</c:forEach>
			</c:if>
		</table>
	</form>
	</body>
</html>
