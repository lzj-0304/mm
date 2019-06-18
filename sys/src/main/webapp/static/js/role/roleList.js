layui.use(['table','layer',"form"],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;


    //用户列表展示
    var  tableIns = table.render({
        elem: '#roleList',
        url : ctx+'/role/roleList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "roleListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'roleName', title: '角色名', minWidth:50, align:"center"},
            {field: 'roleRemark', title: '备注', minWidth:100, align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#roleListBar',fixed:"right",align:"center"}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("roleListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                roleName: $("input[name='roleName']").val() //角色名
            }
        })
    });


    //打开添加用户页面
    $(".addNews_btn").click(function(){
        updateRole();
    });




    $(".addGrant").click(function () {
        /**
         * 获取选中的checkbox id
         */
        var checkStatus = table.checkStatus('roleListTable');
        if(checkStatus.data.length>1||checkStatus.data.length==0){
            layer.msg("请选择一条角色进行授权!",{icon:5});
            return;
        }
        var roleId=checkStatus.data[0].id;
        openGrantPage(roleId);
    });


    function openGrantPage(roleId){
        var url  =  ctx+"/role/roleGrant?roleId="+roleId;
        layui.layer.open({
            title : "角色管理-角色授权",
            type : 2,
            area:["500px","400px"],
            maxmin:true,
            content : url
        });
    }




    /**
     * 行监听
     */
    table.on('tool(roles)', function(obj){
        var layEvent = obj.event;
        if(layEvent === 'edit') {
            updateRole(obj.data.id);
        }else if(layEvent === 'del') {
            layer.confirm('确定删除当前角色？', {icon: 3, title: '角色管理'}, function (index) {
                $.get(ctx+"/role/del",{roleId:obj.data.id},function (data) {
                        if(data.code==200){
                            layer.msg("操作成功！");
                            tableIns.reload();
                        }else{
                            layer.msg(data.msg, {icon: 5});
                        }
                });
            })
        }
    });


    // 打开添加用户页面
    function updateRole(roleId){
        var url  =  ctx+"/role/roleUpdate";
        if(roleId){
            url = url+"?roleId="+roleId;
        }
        layui.layer.open({
            title : "角色管理-角色信息更新",
            type : 2,
            area:["400px","300px"],
            maxmin:true,
            content : url
        });
    }






});
