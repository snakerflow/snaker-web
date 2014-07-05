<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Home</title>
<%@ include file="/common/meta.jsp"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<link href="${ctx}/styles/css/index.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/styles/css/shCoreDefault.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${ctx}/styles/js/shCore.js" type="text/javascript"></script>
	<script src="${ctx}/styles/js/shBrushXml.js" type="text/javascript"></script>
	<script src="${ctx}/styles/js/shBrushJava.js" type="text/javascript"></script>
	<script type="text/javascript">SyntaxHighlighter.all();</script>
</head>

<body style="padding-left: 5px">
	<br/>
	<h3><font color="red">概述</font></h3>
    <p>Snaker是一个基于Java的开源工作流引擎，适用于企业应用中常见的业务流程。本着轻量、简单、灵巧理念设计，定位于简单集成，多环境支持</p>
    <br>
	<p>轻量:snaker-core.jar大小不超过200K，代码行数7000行左右，强大的扩展支持，不依赖于具体的ORM框架</p>
	<p>简单:表设计简单，流程组件简单[start/end/task/custom/subprocess/decision/fork/join]</p>
	<p>灵巧:暴露大量可扩展接口，支持流程设计器、流程引擎的组件模型自定义</p>
	<p>开源协议:<a href="http://www.apache.org/licenses/LICENSE-2.0.html" target="_blank">Apache License Version 2.0</a></p>
	<br/>
	<h3><font color="red">整合</font></h3>
    <p>maven项目中，直接在pom文件中添加snaker的依赖即可:</p>
	<pre class="brush: xml;">
		&lt;dependency&gt;
			&lt;groupId&gt;com.github.snakerflow&lt;/groupId&gt;
			&lt;artifactId&gt;snaker-core&lt;/artifactId&gt;
			&lt;version&gt;1.5.0&lt;/version&gt;
		&lt;/dependency&gt;
	</pre>
	<br/>
	<h3><font color="red">联系</font></h3>
	<p>QQ群:<font color="red">293568574</font></p>
	<p>邮箱:<font color="red">snakerflow@163.com</font></p>
	<br/>
	<h3><font color="red">资源</font></h3>
    <p>流程引擎源码(github):<br><a href="https://github.com/snakerflow/snakerflow" target="_blank">https://github.com/snakerflow/snakerflow</a></p>
	<p>演示应用源码(github):<br><a href="https://github.com/snakerflow/snaker-web" target="_blank">https://github.com/snakerflow/snaker-web</a></p>
	<p>插件源码(github):<br><a href="https://github.com/snakerflow/snaker-designer" target="_blank">https://github.com/snakerflow/snaker-designer</a></p>
	<br>
	<p>流程引擎源码(oscgit):<br><a href="http://git.oschina.net/yuqs/snakerflow" target="_blank">http://git.oschina.net/yuqs/snakerflow</a></p>
	<p>演示应用源码(oscgit):<br><a href="http://git.oschina.net/yuqs/snaker-web" target="_blank">http://git.oschina.net/yuqs/snaker-web</a></p>
	<p>插件源码(oscgit):<br><a href="http://git.oschina.net/yuqs/snaker-designer" target="_blank">http://git.oschina.net/yuqs/snaker-designer</a></p>
</body>
</html>