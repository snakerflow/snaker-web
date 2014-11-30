<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>流程定义</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body>
	<form id="mainForm" action="${ctx}/snaker/process/list" method="get">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					流程定义
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>流程名称：</span>
				</td>
				<td class="td_table_2">
					<input type="text" class="input_240" name="displayName" value="${param['displayName']}"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td class="td_table_1">
					<span>状态：</span>
				</td>
				<td class="td_table_2">
					<input type="radio" name="state" value="1" ${param['state'] == null || param['state'] == 1 ? 'checked' : '' }/>启用
					<input type="radio" name="state" value="0" ${param['state'] == 0 ? 'checked' : '' }/>禁用	
				</td>
			</tr>
			 -->
		</table>
		<table align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="left">
				<shiro:hasPermission name="PROCESSDEPLOY">
					<input type='button' onclick="addNew('${ctx}/snaker/process/designer')" class='button_70px' value='设计'/>
					<input type='button' onclick="addNew('${ctx}/snaker/process/deploy')" class='button_70px' value='部署'/>
					<input type='button' onclick="addNew('${ctx}/snaker/process/init')" class='button_70px' value='初始化'/>
				</shiro:hasPermission>
					<input type='submit' class='button_70px' value='查询'/>
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td align=center width=15% class="td_list_1" nowrap>
					名称
				</td>
				<td align=center width=35% class="td_list_1" nowrap>
					流程显示名称
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					状态
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					版本号
				</td>
				<td align=center width=30% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<c:forEach items="${page.result}" var="process">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${process.name}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${process.displayName}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${process.state == 1 ? '启用' : '禁用'}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${process.version}&nbsp;
					</td>					
					<td class="td_list_2" align=left nowrap>
						<a href="${ctx}${process.instanceUrl }?processId=${process.id }&processName=${process.name }" class="btnStart" title="启动流程">启动流程</a>
						<shiro:hasPermission name="PROCESSDEPLOY">
						<a href="${ctx}/snaker/process/edit/${process.id }" class="btnEdit" title="编辑">编辑</a>
						<a href="${ctx}/snaker/process/designer?processId=${process.id }" class="btnDesigner" title="设计">设计</a>
						<a href="${ctx}/snaker/process/delete/${process.id }" class="btnDel" title="删除" onclick="return confirmDel();">删除</a>
						</shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }"/>
		</table>
	</form>
	</body>
</html>
