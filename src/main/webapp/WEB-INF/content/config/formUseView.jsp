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
            $.ajax({
                type: 'POST',
                url : '${ctx}/config/form/formData',
                //dataType : 'json',
                data : {'formId' : '${form.id}','orderId':'${orderId}'},
                success : function(data){
                	$("input:text").each(function(){
                		this.value=data[this.name];
                	});
                	$("input:radio").each(function(){
                		if(this.value==data[this.name]) {
                			this.checked = true;
                		}
                	});
                	$("input:checkbox").each(function(){
                		if(this.value==data[this.name]) {
                			this.checked = true;
                		}
                	});
                	$("textarea").each(function(){
                		this.value=data[this.name];
                	});
                	$("select").each(function(){
                		$("select[name='" + this.name + "'] option").each(function(){
                			if($(this).val() == data[this.name]){
                				$(this).attr("selected", true);
                			}
                		});
                	});
                }
            });
	    });
	    </script>
	</head>

	<body>
	<div class="container">
	<div class="row">
		<form id="inputForm" action="" method="post"  target="mainFrame">
			${form.parseHtml }
		</form>
	</div>
	</div>
	</body>
</html>
