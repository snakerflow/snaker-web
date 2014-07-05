<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>配置管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	</head>

	<body>
		<form id="inputForm" action="" method="post">
			<input type="hidden" name="id" id="id" value="${dictionary.id }"/>
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1">
						<span>配置名称：</span>
					</td>
					<td class="td_table_2" colspan="3">
						${dictionary.name }&nbsp;
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>显示名称：</span>
					</td>
					<td class="td_table_2" colspan="3">
						${dictionary.cnName }&nbsp;
					</td>
				</tr>
			</table>
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td align=center width=15% class="td_list_1" nowrap>
						序号
					</td>
					<td align=center width=25% class="td_list_1" nowrap>
						编号
					</td>
					<td align=center width=60% class="td_list_1" nowrap>
						显示名称
					</td>
				</tr>

				<c:forEach var="item" items="${dictionary.dictionaryItems}" varStatus="s">
					<tr>
						<td class="td_list_2" align=left nowrap>
							${item.orderby }&nbsp;
						</td>
						<td class="td_list_2" align=left nowrap>
							${item.code }&nbsp;
						</td>					
						<td class="td_list_2" align=left nowrap>
							${item.name }&nbsp;
						</td>
					</tr>
				</c:forEach>
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
