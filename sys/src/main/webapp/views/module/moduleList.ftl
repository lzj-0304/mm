<!DOCTYPE html>
<html>
<head>
	<title>资源管理</title>
	<#include "../base.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="moduleName"
						   class="layui-input
					searchVal" placeholder="资源名称" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="pid" class="layui-input
					searchVal" placeholder="父级ID" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="grade" class="layui-input
					searchVal" placeholder="层级" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="optValue" class="layui-input
					searchVal" placeholder="操作码" />
				</div>
				<a class="layui-btn search_btn" data-type="reload"><i
							class="layui-icon">&#xe615;</i> 搜索</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addNews_btn"><i
							class="layui-icon">&#xe608;</i>  添加资源</a>
			</div>
		</form>
	</blockquote>
	<table id="moduleList" class="layui-table"  lay-filter="modules"></table>

	<!--操作-->
	<script id="moduleListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
</form>
<script type="text/javascript" src="${ctx}/static/js/module/moduleList.js"></script>

</body>
</html>