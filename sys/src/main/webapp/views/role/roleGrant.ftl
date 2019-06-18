
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${ctx}/static/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/static/zTree_v3-3.5.32/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/static/zTree_v3-3.5.32/js/jquery.ztree.excheck.js"></script>

</head>
	<div id="test1" class="ztree"></div>
	<input id="roleId" value="${roleId!}" type="hidden">
	<script type="text/javascript" src="${ctx}/static/js/role/roleGrant.js"></script>
	<script type="text/javascript">
		var ctx="${ctx}";
	</script>
</html>