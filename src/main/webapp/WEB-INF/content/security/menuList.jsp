<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>菜单管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body>
	<form id="mainForm" action="${ctx}/security/menu?lookup=${lookup }" method="get">
		<input type="hidden" name="lookup" value="${lookup}" />
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="order" id="order" value="${page.order}"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					菜单管理
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>菜单名称：</span>
				</td>
				<td class="td_table_2">
					<input type="text" class="input_240" name="filter_LIKES_name" value="${param['filter_LIKES_name']}"/>
				</td>
			</tr>
		</table>
		<table align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="left">
				<c:choose>
					<c:when test="${empty lookup}">
					<shiro:hasPermission name="MENUEDIT">
					<input type='button' onclick="addNew('${ctx}/security/menu/create')" class='button_70px' value='新建'/>
					</shiro:hasPermission>
					</c:when>
					<c:otherwise>
					<input type='button' onclick="javascript:bringback('','')" class='button_70px' value='重置'/>
					</c:otherwise>
				</c:choose>
					<input type='submit' class='button_70px' value='查询'/>
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align=center width=45% class="td_list_1" nowrap>
					菜单名称
				</td>
				<td align=center width=45% class="td_list_1" nowrap>
					上级菜单名称
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<c:forEach items="${page.result}" var="menu">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${menu.name}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${menu.parentMenu.name}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
				    <c:choose>
				    <c:when test="${empty lookup}">
				    <shiro:hasPermission name="MENUDELETE">
						<a href="${ctx}/security/menu/delete/${menu.id }" class="btnDel" title="删除" onclick="return confirmDel();">删除</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="MENUEDIT">
						<a href="${ctx}/security/menu/update/${menu.id }" class="btnEdit" title="编辑">编辑</a>
					</shiro:hasPermission>
						<a href="${ctx}/security/menu/view/${menu.id }" class="btnView" title="查看">查看</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0)" class="btnSelect" title="选择" onclick="bringback('${menu.id}','${menu.name }')">选择</a>
					</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }" lookup="${lookup }"/>
		</table>
	</form>
	</body>
</html>
