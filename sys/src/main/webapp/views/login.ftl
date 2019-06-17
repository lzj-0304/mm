<html class="loginHtml">
<head>
	<#include "base.ftl">
	<link rel="stylesheet" href="${ctx}/static/css/public.css" media="all" />
	<title>登录--layui后台管理模板 2.0</title>
</head>
<body class="loginBody">
	<form class="layui-form">
		<div class="login_face"><img src="${ctx}/static/images/face.jpg"
									 class="userAvatar"></div>
		<div class="layui-form-item input-item">
			<label for="userName">用户名</label>
			<input type="text" placeholder="请输入用户名" name="userName"
				   autocomplete="off"
				   id="userName" class="layui-input" >
		</div>
		<div class="layui-form-item input-item">
			<label for="password">密码</label>
			<input type="password" placeholder="请输入密码" name="userPwd"
				   autocomplete="off"
				   id="userPwd" class="layui-input" >
		</div>
		<div class="layui-form-item input-item" id="imgCode">
			<label for="code">验证码</label>
			<input type="text" placeholder="请输入验证码" autocomplete="off" id="code" class="layui-input">
			<img src="${ctx}/static/images/code.jpg">
		</div>
		<div class="layui-form-item">
			<button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
		</div>
		<div class="layui-form-item layui-row">
			<a href="javascript:;" class="seraph icon-qq layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
			<a href="javascript:;" class="seraph icon-wechat layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
			<a href="javascript:;" class="seraph icon-sina layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
		</div>
	</form>
	<script type="text/javascript" src="${ctx}/static/js/login.js"></script>
</body>
</html>