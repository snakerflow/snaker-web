<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" session="true" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
</head>
<%
List result = new LinkedList();
String type = request.getParameter("type");
%>
<body STYLE="background-color:#E3EEFB">
<form name="waitforForm">
<table width="100%"  border="0" cellpadding="0" cellspacing="0" style="padding-left: 5px">
    <tr>
	    <td align="left">
	        &nbsp;待选择
		</td>
	    <td>
	        &nbsp;
		</td>
    </tr>
    <tr>
        <td align='left'>
        	<div align='left' id="content" style="overflow:auto; height:420; width:270; background:white; border-style:window-inset;scrollbar-face-color:silver;SCROLLBAR-TRACK-COLOR:lightgrey ">
            <select name="waitforSelect" ondblclick="insertOption()"  style="height:420;width:270;margin:-2px;position:absolute;clip: rect(3 900 1000 3)" multiple >
            <%
            if(null != result && !result.isEmpty())
            {
              	for( int i = 0 ; i < result.size(); i++ )
              	{
                  	String[] rs = (String[])result.get(i);
            %>
            	<option value=<%=rs[0]%> ><%=rs[1]%></option>
            <%
                }
            }
            %>
            </select>
           </div>
        </td>
        <td align='right'>
          	<DIV align='right' style="padding-right: 5px">
                <INPUT onclick="insertOption()" type=button style="width:30" value=">" name=add><br><br>
                <INPUT onclick="insertAllOption()" type="button" style="width:30" value=">>" name="AddAll"><br><br>
                <INPUT onclick="deleteOption()" type="button" style="width:30" value="<" name="delete"><br><br>
                <INPUT onclick="deleteAllOption()" type="button" style="width:30" value="<<" name="delete"><br><br>
          </DIV>
        </td>
    </tr>
</table>
</body>
</html>


<SCRIPT language=javascript>

function insertOption()
{
    var k;
    var str = "";
    var compname = "";
    var len;
    for(k = 0; k < parent.frames['select_waitfor'].document.forms[0].waitforSelect.length; k++)
    {
        if(parent.frames['select_waitfor'].document.forms[0].waitforSelect.options[k].selected)
        {
            compname = parent.frames['select_waitfor'].document.forms[0].waitforSelect.options[k].text;
            otext = parent.frames['select_waitfor'].document.forms[0].waitforSelect.options[k].text;
            ovalue = parent.frames['select_waitfor'].document.forms[0].waitforSelect.options[k].value;

            olength = parent.frames['select_result'].document.forms[0].resultSelect.length;
            len = olength;
            tag = 0;
            for( i = 0; i < olength; i++)
            {
                if( ovalue == parent.frames['select_result'].document.forms[0].resultSelect.options[i].value)
                {
                    tag=1;
                }
            }
            if(tag==0)
            {
                parent.frames['select_result'].document.forms[0].resultSelect.options[len] = new Option(otext,olength);
                parent.frames['select_result'].document.forms[0].resultSelect.options[len].value = ovalue;
                parent.frames['select_result'].document.forms[0].resultSelect.options[len].selected = true;
            }
            len = len + 1;
        }
    }
}

function insertAllOption(form)
{
    len = parent.frames['select_result'].document.forms[0].resultSelect.length;

    olength=parent.frames['select_waitfor'].document.forms[0].waitforSelect.length;
    oldSbLength = parent.frames['select_result'].document.forms[0].resultSelect.length;

    for(i = 0; i < olength; i++)
    {
        otext=parent.frames['select_waitfor'].document.forms[0].waitforSelect.options[i].text;
        ovalue=parent.frames['select_waitfor'].document.forms[0].waitforSelect.options[i].value;

        nlength = parent.frames['select_result'].document.forms[0].resultSelect.length;
        tag = 0;
        for( j = 0 ; j < nlength; j++ )
        {
            if( parent.frames['select_result'].document.forms[0].resultSelect.options[j].value == ovalue)
                tag = 1;
        }
        if( tag == 0 )
        {
            parent.frames['select_result'].document.forms[0].resultSelect.options[nlength]=new Option(otext,olength);
            parent.frames['select_result'].document.forms[0].resultSelect.options[nlength].value=ovalue;
            parent.frames['select_result'].document.forms[0].resultSelect.options[nlength].selected=true;
            nlength++;
        }

    }
}


//按删除按钮时触发的函数
function deleteOption(form)
{
    olength=parent.frames['select_result'].document.forms[0].resultSelect.length;
    var s=olength - 1;
    var j=0;
    for(i=s; i>=0; i--)
    {
        if (parent.frames['select_result'].document.forms[0].resultSelect.options[i].selected)
        {
            parent.frames['select_result'].document.forms[0].resultSelect.options[i]=null;
            j=j+1;
        }
    }
    parent.frames['select_result'].document.forms[0].resultSelect.length=olength-j;
}

//按全部删除时触发的函数
function deleteAllOption(form)
{
    olength = parent.frames['select_result'].document.forms[0].resultSelect.length;
    for( i = 1; i < olength; i++)
    {
        parent.frames['select_result'].document.forms[0].resultSelect.options[i] = null;
    }
    parent.frames['select_result'].document.forms[0].resultSelect.length=0;
}
</SCRIPT>