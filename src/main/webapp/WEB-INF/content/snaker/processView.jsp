<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>流程状态</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="${ctx}/styles/css/snaker.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/raphael-min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/jquery-ui-1.8.4.custom/js/jquery.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/jquery-ui-1.8.4.custom/js/jquery-ui.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/snaker/snaker.designer.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/snaker/snaker.model.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/snaker/snaker.editors.js" type="text/javascript"></script>

<script type="text/javascript">
    function addTaskActor(taskName) {
        var url = '${ctx}/snaker/task/actor/add?orderId=${order.id}&taskName=' + taskName;
        var returnValue = window.showModalDialog(url,window,'dialogWidth:1000px;dialogHeight:600px');
        if(returnValue) {
            $('#currentActorDIV').append(',' + returnValue);
        }
    }
	function display(process, state) {
		/** view*/
		$('#snakerflow').snakerflow($.extend(true,{
			basePath : "${ctx}/styles/js/snaker/",
            ctxPath : "${ctx}",
            orderId : "${order.id}",
			restore : eval("(" + process + ")"),
			editable : false
			},eval("(" + state + ")")
		));
	}
</script>
</head>
	<body>
		<table width="98%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					流程状态
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px" width="98%">
			<tr>
				<td align="left" class="td_list_2" colspan="4" style="font-weight: bold">
					流程名称：<font color="red">${order.processName }</font>&nbsp;&nbsp;
					流程编号：<font color="red">${order.orderNo }</font>&nbsp;&nbsp;
					流程创建时间：<font color="red">${order.createTime }</font>
				</td>
			</tr>
			<tr>
				<td align=center width=30% class="td_list_1" nowrap>
					任务名称
				</td>
				<td align=center width=30% class="td_list_1" nowrap>
					任务创建时间
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					任务完成时间
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					任务处理人
				</td>
			</tr>
			<c:forEach items="${tasks}" var="item">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${item.displayName}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.createTime}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.finishTime}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${item.operator }&nbsp;
					</td>
				</tr>
			</c:forEach>
		</table>
		<table align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="left">
					<input type='button' class='button_70px' value='返回' onclick="history.back()"/>
				</td>
			</tr>
		</table>
		<table class="properties_all" align="center" border="1" cellpadding="0" cellspacing="0" style="margin-top: 0px">
			<div id="snakerflow" style="border: 1px solid #d2dde2; margin-top:10px; margin-left:10px; width:98%;">
			</div>
		</table>
		<script type="text/javascript">
		$.ajax({
				type:'GET',
				url:"${ctx}/snaker/process/json",
				data:"processId=${processId}&orderId=${order.id}",
				async: false,
				globle:false,
				error: function(){
					alert('数据处理错误！');
					return false;
				},
				success: function(data){
					display(data.process, data.state);
				}
			});
		</script>
	</body>
</html>
