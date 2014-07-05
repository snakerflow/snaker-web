<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <title>表单管理</title>
    <%@ include file="/common/meta.jsp"%>
    <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
    <script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
</head>

<body>
<form id="inputForm" action="${ctx }/form/form/update" method="post">
    <input type="hidden" name="id" id="id" value="${id }"/>
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
                <input type="text" class="input_240" id="name" name="name"
                       value="${form.name }" />
            </td>
        </tr>
        <tr>
            <td class="td_table_1">
                <span>中文名称：</span>
            </td>
            <td class="td_table_2" colspan="3">
                <input type="text" class="input_240" id="displayName" name="displayName"
                       value="${form.displayName }" />
            </td>
        </tr>
        <tr>
            <td class="td_table_1">
                <span>表单类型：</span>
            </td>
            <td class="td_table_2" colspan="3">
                <frame:select type="select" name="type" configName="formType" value="${form.type}" cssClass="input_select"/>
            </td>
        </tr>
    </table>
    <table class="table_all" align="center" border="0" cellpadding="0"
           cellspacing="0">
        <tr>
            <td align=center width=10% class="td_list_1" nowrap>
                <input type="checkbox" title="全选" id="selectAll">
            </td>
            <td align=center width=40% class="td_list_1" nowrap>
                表名
            </td>
            <td align=center width=50% class="td_list_1" nowrap>
                中文名称
            </td>
        </tr>
        <c:forEach items="${tables}" var="item">
            <tr>
                <td class="td_list_2" align=center nowrap>
                    <label class="checkbox">
                        <input type="checkbox" name="orderIndexs" value="${item.id}" ${item.selected== 1 ? 'checked' : '' }>
                    </label>
                </td>
                <td class="td_list_2" align=left nowrap>
                        ${item.name}&nbsp;
                </td>
                <td class="td_list_2" align=left nowrap>
                        ${item.displayName}&nbsp;
                </td>
            </tr>
        </c:forEach>
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
