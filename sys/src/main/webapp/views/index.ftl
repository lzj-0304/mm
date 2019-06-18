<html>
<head>
	<#include "base.ftl">
	<title>乐购商城-后台管理</title>
	<link rel="stylesheet" href="${ctx}/static/css/index.css" media="all" />
</head>
<body class="main_body">
	<div class="layui-layout layui-layout-admin">
		<!-- 顶部 -->
		<div class="layui-header header">
			<div class="layui-main mag0">
				<a href="#" class="logo">乐购-后台管理</a>
				<!-- 显示/隐藏菜单 -->
				<a href="javascript:;" class="seraph hideMenu icon-caidan"></a>
			    <!-- 顶部右侧菜单 -->
			    <ul class="layui-nav top_menu">
					<li class="layui-nav-item" id="userInfo">
						<a href="javascript:;"><img
									src="${ctx}/static/images/face.jpg"
									class="layui-nav-img userAvatar"
									width="35" height="35">
							<cite class="adminName">
								<#if session["userInfo"]??>
									${session["userInfo"].userName}
									<#else >
							   </#if>
							</cite>
						</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;"
								   data-url="${ctx}/page/user/userInfo.html"><i class="seraph icon-ziliao" data-icon="icon-ziliao"></i><cite>个人资料</cite></a>
							</dd>
							<dd><a href="javascript:;"
								   data-url="${ctx}/page/user/changePwd.html"><i class="seraph icon-xiugai" data-icon="icon-xiugai"></i><cite>修改密码</cite></a>
							</dd>
							<dd><a href="javascript:;" class="showNotice"><i class="layui-icon">&#xe645;</i><cite>系统公告</cite><span class="layui-badge-dot"></span></a>
							</dd>
							<dd><a href="${ctx}/user/exit" class="signOut"><i
											class="seraph
							icon-tuichu"></i><cite>退出</cite></a>
							</dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<!-- 左侧导航 -->
		<div class="layui-side layui-bg-black">
			<div class="user-photo">
				<a class="img" title="我的头像" ><img src="${ctx}/static/images/face
				.jpg" class="userAvatar"></a>
				<p>你好！<span class="userName">
						<#if session["userInfo"]??>
								${session["userInfo"].userName}
								<#else >
						</#if>
					</span>,
					欢迎登录</p>
			</div>
			<!-- 搜索 -->
			<div class="layui-form component">
				<select name="search" id="search" lay-search lay-filter="searchPage">
					<option value="">搜索页面或功能</option>
					<option value="1">layer</option>
					<option value="2">form</option>
				</select>
				<i class="layui-icon">&#xe615;</i>
			</div>
			<div class="navBar layui-side-scroll" id="navBar">
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item layui-this">
						<a href="javascript:;" data-url="${ctx}/page/main.html"><i class="layui-icon" data-icon=""></i><cite>后台首页</cite></a>
					</li>
					<#if session["permissions"]??>
						<#if session["permissions"]?seq_contains("10")>
							<li class="layui-nav-item">
								<a href="javascript:;">系统管理</a>
									  <#if session["permissions"]?seq_contains("1010")>
										<dl class="layui-nav-child">
											<a href="javascript:;" data-url="${ctx}/user">
												<i class="layui-icon" data-icon=""></i><cite>用户管理</cite>
											</a>
										</dl>
									  </#if>
								       <#if session["permissions"]?seq_contains("1020")>
											<dl class="layui-nav-child">
												<a href="javascript:;" data-url="${ctx}/role">
													<i class="layui-icon" data-icon=""></i><cite>角色管理</cite>
												</a>
											</dl>
									  </#if>
									   <#if session["permissions"]?seq_contains("1030")>
											<dl class="layui-nav-child">
												<a href="javascript:;" data-url="${ctx}/module">
													<i class="layui-icon" data-icon=""></i><cite>资源管理</cite>
												</a>
											</dl>
										</#if>
							</li>
						</#if>
					</#if>
				</ul>
			</div>
		</div>
		<!-- 右侧内容 -->
		<div class="layui-body layui-form">
			<div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
				<ul class="layui-tab-title top_tab" id="top_tabs">
					<li class="layui-this" lay-id=""><i class="layui-icon">&#xe68e;</i> <cite>后台首页</cite></li>
				</ul>
				<ul class="layui-nav closeBox">
				  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="layui-icon caozuo">&#xe643;</i> 页面操作</a>
				    <dl class="layui-nav-child">
					  <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i> 刷新当前</a></dd>
				      <dd><a href="javascript:;" class="closePageOther"><i class="seraph icon-prohibit"></i> 关闭其他</a></dd>
				      <dd><a href="javascript:;" class="closePageAll"><i class="seraph icon-guanbi"></i> 关闭全部</a></dd>
				    </dl>
				  </li>
				</ul>
				<div class="layui-tab-content clildFrame">
					<div class="layui-tab-item layui-show">
						<iframe src="${ctx}/main"></iframe>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<div class="layui-footer footer">
			<p><span>copyright @2020 乐字节</span></p>
		</div>
	</div>



	<script type="text/javascript" src="${ctx}/static/js/index.js"></script>
</body>
</html>