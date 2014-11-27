<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <title>动态添加参与者</title>
    <%@ include file="/common/meta.jsp"%>
    <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
    <script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${ctx}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>

<body>
<form id="inputForm" action="" method="post">
    <input type="hidden" name="orderId" id="orderId" value="${orderId }"/>
    <input type="hidden" name="taskName" id="taskName" value="${taskName }"/>
    <table width="100%" border="0" align="center" cellpadding="0"
           class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
        <tr>
            <td class="td_table_top" align="center">
                动态添加参与者
            </td>
        </tr>
    </table>
    <table class="table_all" align="center" border="0" cellpadding="0"
           cellspacing="0" style="margin-top: 0px">
        <tr>
            <td class="td_table_1">
                <span>添加参与者：</span>
            </td>
            <td class="td_table_2" colspan="3">
                <input type="text" class="input_240" id="operator" name="operator"/>
            </td>
        </tr>
    </table>
    <table align="center" border="0" cellpadding="0"
           cellspacing="0">
        <tr align="left">
            <td colspan="1">
                <input type="button" class="button_70px" name="submit" value="提交" onclick="addActor()">
                &nbsp;&nbsp;
                <input type="button" class="button_70px" name="reback" value="关闭"
                       onclick="window.close()">
            </td>
        </tr>
    </table>
    <script type="text/javascript">
        function addActor() {
            var orderId = $('#orderId').val();
            var taskName = $('#taskName').val();
            var operator = $('#operator').val();
            $.ajax({
                type:'POST',
                url:"${ctx}/snaker/task/actor/add",
                data:"orderId=" + orderId + "&taskName=" + taskName + "&operator=" + operator,
                async: false,
                globle:false,
                error: function(){
                    alert('数据处理错误！');
                    return false;
                },
                success: function(data){
                    window.returnValue = operator;
                    window.close();
                }
            });
        }
    </script>
</form>
</body>
</html>
