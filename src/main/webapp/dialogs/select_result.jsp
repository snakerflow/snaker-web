<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" session="true" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
</head>
<body STYLE="background-color:#E3EEFB">
<form name="resultForm">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
	    <td align="left" >
	        &nbsp;已选择
		</td>
    </tr>
    <tr align="left">
        <td align="left">
        	<div id="content" style="overflow:auto; height:420; width:235; background:white; border-style:window-inset;scrollbar-face-color:silver;SCROLLBAR-TRACK-COLOR:lightgrey "> 
            <select name="resultSelect" class="input_select_320" style="height:420;width:235;margin:-2px;position:absolute;clip: rect(3 900 1000 3)"  multiple>
            </select>
          	</div>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input  onclick="confirmEvent()" type="button" value="确 定" class="Button2" name="confirmBtn">
            <input  onclick="javascript:{window.close();}" type="button" value="关 闭" class="Button2" name="closeBtn">
        </td>
    </tr>
</table>
</form>
</body>
</html>

<SCRIPT language=JavaScript>
function confirmEvent()
{
    var temp = document.forms[0].resultSelect;
    var ret = "";
    
    var onlyone = false;
    <%
    String strOnlyone = request.getParameter("onlyone");
    if( strOnlyone != null && strOnlyone.equals("true") )
    {
    %>
    	onlyone = true;
    <%
    }
    %>

    if( onlyone && temp.length>1 )
    {
    	alert("对不起,一次只能配置一个用户");
    	return;
   	}

    for( i = 0 ; i < temp.length; i++ )
    {
        ret += temp[i].value + "(" + temp[i].text + ")";
        if( i != temp.length - 1 )
            ret += ";";
    }
    
    parent.window.returnValue = ret;
    parent.window.close();
}
</SCRIPT>


