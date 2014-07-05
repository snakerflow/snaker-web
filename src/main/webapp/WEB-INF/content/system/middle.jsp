<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<link href="${ctx}/styles/css/style.css" type="text/css"
	rel="stylesheet" />
<title>显示/隐藏左侧导航栏</title>
</head>
<script language="JavaScript">
function switchLayout(){
	if(parent.myFrame.cols == "178,7,*") {
		parent.myFrame.cols="0,7,*";
		document.getElementById("arrow").src="${ctx}/styles/images/switch_right.gif";
		document.getElementById("arrow").alt="打开左侧导航栏";
	} else {
		parent.myFrame.cols="178,7,*";
		document.getElementById("arrow").src="${ctx}/styles/images/switch_left.gif";
		document.getElementById("arrow").alt="隐藏左侧导航栏";
	}
}

function MyLoad() {
	if(window.parent.location.href.indexOf("MainUrl")>0) {
		window.top.middleFrame.document.getElementById("arrow").src="${ctx}/styles/images/switch_right.gif";
	}
}
</script>
<body onload="MyLoad()">
<div id="switchpic"><a href="javascript:switchLayout()"><img src="${ctx}/styles/images/switch_left.gif" alt="隐藏左侧导航栏" id="arrow" /></a></div>
</body>
</html>
