<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>委托授权管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/snaker/surrogate/update" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					委托授权管理
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>流程名称：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<select class="input_select" id="processName" name="processName">
							<option value='' selected>------请选择------</option>
							<c:forEach items="${processNames}" var="name">
								<option value='${name }' ${surrogate.processName == name ? 'selected' : '' }>${name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>授权人：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="operator" name="operator"
							value="${surrogate.operator }" />
					</td>
					<td class="td_table_1">
						<span>代理人：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="surrogate" name="surrogate"
							value="${surrogate.surrogate }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>开始时间：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="sdate" name="sdate"
							value="${surrogate.sdate }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
					</td>
					<td class="td_table_1">
						<span>结束时间：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="edate" name="edate"
							value="${surrogate.edate }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
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
