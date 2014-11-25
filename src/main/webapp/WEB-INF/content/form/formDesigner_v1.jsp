<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>表单设计</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
        <link rel="stylesheet" href="${ctx }/styles/css/style.css" type="text/css" media="all" />
        <script type="text/javascript" charset="utf-8" src="${ctx }/styles/js/jquery-ui-1.8.4.custom/js/jquery.min.js"></script>
        <script type="text/javascript" charset="utf-8" src="${ctx }/styles/ueditor/ueditor.config.js"></script>
        <script type="text/javascript" charset="utf-8" src="${ctx }/styles/ueditor/ueditor.all.min.js"> </script>
        <script type="text/javascript" charset="utf-8" src="${ctx }/styles/ueditor/form.js"> </script>
        <script type="text/javascript" charset="utf-8" src="${ctx }/styles/ueditor/lang/zh-cn/zh-cn.js"></script>
	</head>

	<body>
    <table border="0" align="center" cellpadding="0"
           class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px;width:100%">
        <tr>
            <td class="td_table_top" align="center">
                表单设计
            </td>
        </tr>
    </table>
    <div>
        <script id="editor" type="text/plain" style="width:98%;height:500px;">${form.html}</script>
    </div>
    <script type="text/javascript">
        var formData = { hasEditor: "0", id:"${form.id}", ctx:"${ctx}" };
        var ue = UE.getEditor('editor');
    </script>
	</body>
</html>
