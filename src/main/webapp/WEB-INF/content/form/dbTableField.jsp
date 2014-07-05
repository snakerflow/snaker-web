<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <title>表管理</title>
    <%@ include file="/common/meta.jsp"%>
    <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
    <script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
</head>

<body>
<form id="inputForm" action="${ctx }/form/dbtable/deploy" method="post">
    <input type="hidden" name="id" id="id" value="${dbtable.id }"/>
    <table width="100%" border="0" align="center" cellpadding="0"
           class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
        <tr>
            <td class="td_table_top" align="center">
                表管理
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
                ${dbtable.name }
            </td>
        </tr>
        <tr>
            <td class="td_table_1">
                <span>显示名称：</span>
            </td>
            <td class="td_table_2" colspan="3">
                <input type="text" class="input_240" id="displayName" name="displayName"
                       value="${dbtable.displayName }" />
            </td>
        </tr>
    </table>
    <table align="center" border="0" cellpadding="0"
           cellspacing="0">
        <tr>
            <td align="left">
                <%--<shiro:hasPermission name="FIELDEDIT">--%>
                    <input type='button' onclick="addNew('${ctx}/form/dbtable/field/create?tableId=${dbtable.id }')" class='button_70px' value='添加'/>
                    <input type='submit' class='button_70px' value='发布'/>
                <%--</shiro:hasPermission>--%>
            </td>
        </tr>
    </table>
    <table class="table_all" align="center" border="0" cellpadding="0"
           cellspacing="0">
        <tr>
            <td align=center width=10% class="td_list_1" nowrap>
                字段名称
            </td>
            <td align=center width=15% class="td_list_1" nowrap>
                显示名称
            </td>
            <td align=center width=8% class="td_list_1" nowrap>
                是否必填
            </td>
            <td align=center width=8% class="td_list_1" nowrap>
                数据类型
            </td>
            <td align=center width=8% class="td_list_1" nowrap>
                数据长度
            </td>
            <td align=center width=8% class="td_list_1" nowrap>
                小数位数
            </td>
            <td align=center width=8% class="td_list_1" nowrap>
                数据格式
            </td>
            <td align=center width=20% class="td_list_1" nowrap>
                默认值
            </td>
            <td align=center width=15% class="td_list_1" nowrap>
                操作
            </td>
        </tr>
        <c:forEach items="${dbtable.fields}" var="item">
            <tr>
                <td class="td_list_2" align=left nowrap>
                        ${item.name}&nbsp;
                </td>
                <td class="td_list_2" align=left nowrap>
                        ${item.displayName}&nbsp;
                </td>
                <td class="td_list_2" align=left nowrap>
                        <frame:select type="select" name="required" configName="yesNo" value="${item.required}" displayType="1"/>&nbsp;
                </td>
                <td class="td_list_2" align=left nowrap>
                        <frame:select type="select" name="type" configName="fieldType" value="${item.type}" displayType="1"/>&nbsp;
                </td>
                <td class="td_list_2" align=left nowrap>
                        ${item.dataLength}&nbsp;
                </td>
                <td class="td_list_2" align=left nowrap>
                        ${item.dataDigit}&nbsp;
                </td>
                <td class="td_list_2" align=left nowrap>
                        ${item.dataFormat}&nbsp;
                </td>
                <td class="td_list_2" align=left nowrap>
                        ${item.defaultValue}&nbsp;
                </td>
                <td class="td_list_2" align=left nowrap>
                    <a href="${ctx}/form/dbtable/field/edit/${item.id }" class="btnEdit" title="编辑">编辑</a>
                    <a href="${ctx}/form/dbtable/field/delete/${item.id }" class="btnDel" title="删除" onclick="return confirmDel();">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
