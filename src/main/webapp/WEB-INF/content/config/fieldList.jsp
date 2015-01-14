<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>字段管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body>
	<form id="mainForm" action="${ctx}/config/field" method="get">
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					字段管理
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>表名称：</span>
				</td>
				<td class="td_table_2" colspan="3">
					&nbsp;TBL_${form.name }
				</td>
			</tr>
			<tr>
				<td class="td_table_1">
					<span>表单名称：</span>
				</td>
				<td class="td_table_2" colspan="3">
					&nbsp;${form.displayName }
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align=center width=20% class="td_list_1" nowrap>
					名称
				</td>
				<td align=center width=70% class="td_list_1" nowrap>
					标题
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					<input type="checkbox" title="全选" id="selectAll">
				</td>
			</tr>
			<c:forEach items="${fields}" var="field">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${field.name}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${field.title}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						<label class="checkbox">
							<input type="checkbox" name="orderIndexs" value="${field.id}" ${field.queryFlag == 1 ? 'checked' : '' }>
						</label>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<script type="text/javascript">
	$("#selectAll").click(function(){
		var status = $(this).attr("checked");
		if(status) {
			$("input[name='orderIndexs']").attr("checked",true);
		} else {
			$("input[name='orderIndexs']").attr("checked",false);
		}
	    
	});
	</script>
	</body>
</html>
