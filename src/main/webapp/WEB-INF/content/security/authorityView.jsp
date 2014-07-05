<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>权限管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	</head>

	<body>
		<form id="inputForm" action="" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1">
						<span>权限名称：</span>
					</td>
					<td class="td_table_2" colspan="3">
						${authority.name }&nbsp;
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>权限描述：</span>
					</td>
					<td class="td_table_2" colspan="3">
						${authority.description }&nbsp;
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
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td align=center width=45% class="td_list_1" nowrap>
						<a href="javascript:sort('name','asc')">资源名称</a>
					</td>
					<td align=center width=45% class="td_list_1" nowrap>
						<a href="javascript:sort('source','asc')">资源</a>
					</td>
				</tr>
				<c:forEach items="${authority.resources}" var="resource">
					<tr>
						<td class="td_list_2" align=left nowrap>
							${resource.name}&nbsp;
						</td>
						<td class="td_list_2" align=left nowrap>
							${resource.source}&nbsp;
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</body>
</html>
