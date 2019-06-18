var zTreeObj;
$(function () {
    loadModuleInfo();
});


function loadModuleInfo() {
    $.ajax({
        type:"post",
        url:ctx+"/module/allModules",
        data:{
          roleId:$("#roleId").val()
        },
        dataType:"json",
        success:function (data) {
            // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                view:{
                    showLine: false
                    // showIcon: false
                },
                check: {
                    enable: true,
                    chkboxType: { "Y": "ps", "N": "ps" }
                },
                callback: {
                    onCheck: zTreeOnCheck
                }
            };
            var zNodes =data;
            zTreeObj=$.fn.zTree.init($("#test1"), setting, zNodes);
        }
    })

}

function zTreeOnCheck(event, treeId, treeNode) {
    var nodes= zTreeObj.getCheckedNodes(true);
    if(nodes.length>0){
        var mids=[],roleId=$("#roleId").val();
        for(var i=0;i<nodes.length;i++){
            mids[i]=nodes[i].id;
        }

        /**
         * 执行授权
         */
        doGrant(roleId,mids);

    }
}


/**
 * 执行授权
 */
function doGrant(roleId,mids) {
    $.ajax({
        type:"post",
        url:ctx+"/role/doGrant",
        data:{
            mids:mids,
            roleId:roleId
        },
        dataType:"json"
    })

}

