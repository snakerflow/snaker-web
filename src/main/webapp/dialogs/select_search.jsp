<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
  <script type="text/javascript" src="${ctx }/styles/js/jquery-1.8.3.min.js"></script>
</head>
<script language="javascript">
function doSearch()
{
$.ajax({
	type:'post',
	url:'${ctx }/security/org/treeQuery',
	dataType:'Json',
	data: "name="+document.getElementById("findStr").value,
	success:function(msg){
		parent.frames['select_waitfor'].document.forms[0].waitforSelect.options.length=0;
		for (var i = 0; i < msg.length; i++)
		{ 
            parent.frames['select_waitfor'].document.forms[0].waitforSelect.options[i] = new Option(msg[i].name,i);
            parent.frames['select_waitfor'].document.forms[0].waitforSelect.options[i].value = msg[i].id;
		}
	},
	error:function(){
		
	}
	});
}
</script>

<body STYLE="background-color:#E3EEFB">
<form name="searchForm" >
<table bgcolor="#E3EEFB" width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="left">
                    <br>
                        &nbsp;&nbsp;请输入要查找名称:<input type="text" name="findStr" id="findStr" value="" style="width: 240px"/>
                        <span onclick="doSearch();"><img src="${ctx }/styles/images/view.png" width="18" height="17" border="0" style="cursor: hand"/></span>
                    <br>
                    </td>
                </tr>
            </table>
            <hr width="100%" />
        </td>
    </tr>
</table>
</form>
</body>
</html>
