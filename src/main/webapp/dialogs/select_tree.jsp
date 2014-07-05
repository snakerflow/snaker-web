<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" session="true" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" href="${ctx }/styles/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="${ctx }/styles/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="${ctx }/styles/zTreeStyle/jquery.ztree.core-3.1.min.js"></script>  
    <script type="text/javascript">
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			async: {
				enable: true,
				url:"${ctx }/security/tree/${param['type'] }?parentId=${param['parentId']}",
				autoParam: ["id"]
			},
			callback: {
				onClick: onClick
			}
		};
		$(document).ready(function(){
			$.fn.zTree.init($("#tree"), setting);
		});
		
		function onClick(event, treeId, treeNode, clickFlag)
		{
			parent.frames['select_waitfor'].document.forms[0].waitforSelect.options.length=0;
			if (treeNode.isParent)
			{
				var childrenNodes = treeNode.children;
				if (childrenNodes)
				{ 
					for (var i = 0; i < childrenNodes.length; i++)
					{ 
			            parent.frames['select_waitfor'].document.forms[0].waitforSelect.options[i] = new Option(childrenNodes[i].name,i);
			            parent.frames['select_waitfor'].document.forms[0].waitforSelect.options[i].value = childrenNodes[i].id;
					}
				}
			}
		}	
	</script>  
</head>

<body>
<div>
	<div style="width:240px;height:400px;text-align:left;">
		<ul id="tree" class="ztree"></ul>
	</div>
</div>
</body>
</html>