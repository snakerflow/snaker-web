<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	    <link href="${ctx }/styles/bootstrap/2.2.2/css/bootstrap.css" rel="stylesheet" type="text/css" />
	    <link href="${ctx }/styles/css/site.css" rel="stylesheet" type="text/css" />
	    <script type="text/javascript" charset="utf-8" src="${ctx }/styles/js/jquery-1.8.3.min.js"></script>
	    <script type="text/javascript">
	    $('document').ready(function(){
	    	$("#submit").click(function(){
	    		$("#inputForm").submit();
	    	});
	    });
	    </script>
	</head>

	<body>
	<div class="container">
	<div class="row">
		<form id="inputForm" action="${ctx }/config/form/submit" method="post"  target="mainFrame">
			<input type="hidden" name="formId" id="formId" value="${form.id }"/>
			<input type="hidden" name="processId" value="${processId }" />
			<input type="hidden" name="orderId" value="${orderId }" />
			<input type="hidden" name="taskId" value="${taskId }" />
			${form.parseHtml }
		</form>
	</div>
	<div class="row">
		<p style="text-align: left;">
			<button type="submit" id="submit" value="save" class="btn btn-success">确定保存</button>
		</p>
	</div>
	</div>
	</body>
</html>
