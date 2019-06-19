<!DOCTYPE html>
<html>
<head>
	<title>资源更新</title>
	<#include "../base.ftl">
<body class="childrenBody">
<form class="layui-form" style="width:80%;" >
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">资源名</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input title"
				   lay-verify="required" name="title" value="${(module.title)!}" id="title"
				   placeholder="请输入资源名">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">资源样式</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input titleStyle"
				    name="titleStyle" value="${(module.titleStyle)!}" id="titleStyle"
				   placeholder="请输入资源样式">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">url</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input url"
				   name="url" value="${(module.url)!}"
				   id="url"
				   placeholder="资源地址">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-col-xs6">
			<label class="layui-form-label">当前层级</label>
			<div class="layui-input-block">
				<select name="grade" id="grade" lay-filter="grade">
					<option value="" >请选择</option>
					<#if module??>
						<option value="0" <#if module.grade==0>selected</#if>>一级</option>
						<option value="1" <#if module.grade==1>selected</#if>>二级</option>
						<option value="2" <#if module.grade==2>selected</#if>>三级</option>
						<#else>
							<option value="0"  >一级</option>
							<option value="1" >二级</option>
							<option value="2" >三级</option>
					</#if>

				</select>
			</div>
		</div>
		<div class="layui-col-xs6" id="parentIdStr" style="display: none">
			<label class="layui-form-label">上级菜单</label>
			<div class="layui-input-block">
				<select name="parentId" id="parentId" lay-filter="parentId" >
				</select>
			</div>
		</div>
	</div>

	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">操作码</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input userEmail"
				     name="optValue" value="${(module.optValue)!}" id="optValue"
				   placeholder="操作码">
		</div>
	</div>

	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-lg" lay-submit=""
					lay-filter="addModule">确认</button>
			<button  class="layui-btn layui-btn-lg layui-btn-normal">取消</button>
		</div>
	</div>
	<input type="hidden" name="id"  value="${(module.id)!}">
	<input type="hidden"  id="pid" value="${(module.parentId)!}">
</form>
<script type="text/javascript" src="${ctx}/static/js/module/moduleUpdate.js"></script>
</body>
</html>