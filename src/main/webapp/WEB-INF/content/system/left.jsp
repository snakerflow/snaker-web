<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Left</title>
<%@ include file="/common/meta.jsp"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<link href="${ctx}/styles/css/index.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
</head>

<body>
<div class="sidebar">
  <frame:menu />
</div>
<script type="text/javascript">
    $("li").click(function() {
    	removeCSS();
        $(this).addClass("current").siblings().removeClass("current");
    }).hover(function() {
        $(this).addClass("lihover");
    },
    function() {
        $(this).removeClass("lihover");
    })
    function removeCSS(){
    	$("li").each(function(){
			$(this).removeClass("current").siblings().removeClass("current");
		});
    }
</script>
<script type="text/javascript">
	function Show_left() {
	if (document.getElementById("oaleft").style.display == "none") {
		document.getElementById("oaleft").style.display = "block";
		document.getElementById("leftimg").style.background = 'url(../images/switch_left.gif)';
	} else {
		document.getElementById("oaleft").style.display = "none";
		document.getElementById("leftimg").style.background = 'url(../images/switch_right.gif)';
	}
}
</script>
</body>
</html>