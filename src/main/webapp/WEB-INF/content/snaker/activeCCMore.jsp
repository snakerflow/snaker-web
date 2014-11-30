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
	<form id="mainForm" action="${ctx}/snaker/task/active/ccmore" method="get">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					抄送实例
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
			<c:forEach items="${page.result}" var="item">
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
						<a href="${ctx}/snaker/all?processId=${item.processId }&orderId=${item.id}&type=cc " class="btnView" title="查看">查看</a>
						<a href="${ctx}/snaker/ccread?id=${item.id}&url=/snaker/task/active " class="btnRead" title="关闭">关闭</a>
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }"/>
		</table>
		</form>
	</body>
</html>
