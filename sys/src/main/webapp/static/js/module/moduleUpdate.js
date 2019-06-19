layui.use(['form','layer'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addModule)",function(data){
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        //弹出loading
        $.post(ctx+"/module/saveOrUpdate",data.field,function(res){
            if(res.code==200){
                setTimeout(function(){
                    top.layer.close(index);
                    top.layer.msg("操作成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                },500);
            }else{
                layer.msg(res.msg, {icon: 5});
            }
        });
        return false;
    });


    form.on('select(grade)',function (data) {
        var grade=data.value;
        if(grade==1||grade==2){
            /**
             * 获取1|2 层级父级菜单并执行填充
             */
            getParent(grade);
        }else{
            $("#parentId").html("");
            $("#parentIdStr").hide();
        }
        
    });
    
    function getParent(grade) {
        $.post(ctx+"/module/moduleList",{grade:(grade-1)},function (data) {
            if(data.count>0){
                var mList=data.data;
                var ops="<option value='' >请选择</option>";
                var pid =$("#pid").val();
                for(var i=0;i<mList.length;i++){
                    if( pid && pid == mList[i].id){
                        ops=ops+"<option value="+mList[i].id+" selected='selected'>"+mList[i].title+"</option>"
                    }else{
                        ops=ops+"<option value="+mList[i].id+" >"+mList[i].title+"</option>"
                    }
                }
                $("#parentId").html(ops);
                form.render();
                $("#parentIdStr").show();

            }
        })
    }


    getParent($("#grade").val());
    

});