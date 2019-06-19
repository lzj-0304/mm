layui.use(['table','layer',"form"],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    //资源列表展示
    var  tableIns = table.render({
        elem: '#moduleList',
        url : ctx+'/module/moduleList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "moduleListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'title', title: '资源名称', minWidth:50, align:"center"},
            {field: 'grade', title: '层级', align:'center'},
            {field: 'optValue', title: '操作码',  align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#moduleListBar',fixed:"right",align:"center"}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("moduleListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                moduleName: $("input[name='moduleName']").val(),  //资源名
                pid: $("input[name='pid']").val(),  // 父级id
                grade: $("input[name='grade']").val(),  //层级
                optValue: $("input[name='optValue']").val()  // 操作码
            }
        })
    });


    //打开添加用户页面
    $(".addNews_btn").click(function(){
        updateModule();
    });







    /**
     * 行监听
     */
    table.on('tool(modules)', function(obj){
        var layEvent = obj.event;
        if(layEvent === 'edit') {
            updateModule(obj.data.id);
        }else if(layEvent === 'del') {
            layer.confirm('确定删除当前资源？', {icon: 3, title: '资源管理'}, function (index) {
                $.get(ctx+"/module/del",{mid:obj.data.id},function (data) {
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
    function updateModule(mid){
        var url  =  ctx+"/module/moduleUpdate";
        if(mid){
            url = url+"?mid="+mid;
        }
        layui.layer.open({
            title : "资源管理-资源信息更新",
            type : 2,
            area:["750px","500px"],
            maxmin:true,
            content : url
        });
    }


});
