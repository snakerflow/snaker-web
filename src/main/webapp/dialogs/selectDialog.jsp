<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String title = request.getParameter("title");
%>
<html>
<head>
<title>请选择</title>
<meta http-equiv="x-ua-compatible" content="IE=6">
</head>
<%
	String parsmeters = "?";
	java.util.Enumeration paras = request.getParameterNames();
	while (paras.hasMoreElements())
	{
   		String name = (String)paras.nextElement();
 		String value = (String)request.getParameter(name);
     	parsmeters += "&" + name + "=" + value;
	}
%>

<frameset frameborder="no" framespacing="0" border="0"  rows="60, *">
    <frame marginwidth="0" marginheight="0" src="select_search.jsp<%=parsmeters%>" name="select_search" scrolling="no" border="1" >
    <frameset name="select_frameset" frameborder="no" framespacing="0" border="0" cols="5px,120px,180px,130px" rows="*">
    	<frame marginwidth="1" marginheight="5" src="empty.html" name="empty" noresize>
        <frame marginwidth="1" marginheight="5" src="select_tree.jsp<%=parsmeters%>" name="select_tree" noresize scrolling="yes" frameborder="yes">
        <frame marginwidth="1" marginheight="5" src="select_waitfor.jsp<%=parsmeters%>" name="select_waitfor" scrolling="no" noresize>
        <frame marginwidth="1" marginheight="5" src="select_result.jsp<%=parsmeters%>" name="select_result" scrolling="no" noresize>
    </frameset>
</frameset>
</html>