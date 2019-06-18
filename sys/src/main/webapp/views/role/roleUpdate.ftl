<!DOCTYPE html>
<html>
<head>
	<title>用户添加</title>
	<#include "../base.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;" >
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">角色名</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input"
				   lay-verify="required" name="roleName" value="${(role
			.roleName)!}"
				   placeholder="请输入角色名">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">备注</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input"
				    name="roleRemark" value="${(role.roleRemark)!}"
				   placeholder="角色备注">
		</div>
	</div>

	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-lg" lay-submit=""
					lay-filter="addRole">确认</button>
			<button  class="layui-btn layui-btn-lg layui-btn-normal">取消</button>
		</div>
	</div>
	<input type="hidden" name="id"  value="${(role.id)!}">
</form>
<script type="text/javascript" src="${ctx}/static/js/role/roleUpdate.js"></script>
</body>
</html>