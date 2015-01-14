<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>表单管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body>
	<form id="mainForm" action="${ctx}/config/form" method="get">
		<input type="hidden" name="lookup" value="${lookup}" />
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="order" id="order" value="${page.order}"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					表单管理
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>表单名称：</span>
				</td>
				<td class="td_table_2" colspan="3">
					<input type="text" class="input_240" name="name" value="${name }"/>
				</td>
			</tr>
		</table>
		<table align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="left">
					<c:if test="${empty lookup}">
					<input type='button' onclick="addNew('${ctx}/config/form/create')" class='button_70px' value='新建'/>
					</c:if>
					<input type='submit' class='button_70px' value='查询'/>
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align=center width=20% class="td_list_1" nowrap>
					表单名称
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					显示名称
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					创建人
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					创建时间
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					类别
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<c:forEach items="${page.result}" var="form">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${form.name}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${form.displayName}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${form.creator}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${form.createTime}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						<frame:select name="type" type="select" configName="formType" displayType="1" value="${form.type}" />&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
					<c:choose>
                        <c:when test="${empty lookup}">
						<a href="${ctx}/config/form/delete/${form.id }" class="btnDel" title="删除" onclick="return confirmDel();">删除</a>
						<a href="${ctx}/config/form/update/${form.id }" class="btnEdit" title="编辑">编辑</a>
						<a href="${ctx}/config/form/designer/${form.id }" class="btnForm" title="设计">设计</a>
						<a href="${ctx}/config/form/view/${form.id }" class="btnView" title="查看">查看</a>
						<a href="${ctx}/config/form/use/${form.id }" class="btnFormEdit" title="录入数据">录入数据</a>
						</c:when>
                        <c:otherwise>
                        <a href="javascript:void(0)" class="btnSelect" onclick="confirmForm('${form.id}')" title="选择">选择</a>
                        </c:otherwise>
                    </c:choose>
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }" lookup="${lookup }"/>
		</table>
	</form>
    <script>
        function confirmForm(formId) {
            window.returnValue = formId;
            window.close();
        }
    </script>
	</body>
</html>
