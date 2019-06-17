layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;



    //登录按钮
    form.on("submit(login)",function(data){
        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        var  _this =this;
        setTimeout(function(){
            doLogin(_this);
        },1000);
        return false;
    });

    function doLogin(_this){
        $.ajax({
            type:"post",
            url:ctx+"/user/doLogin",
            data:{
                userName:"admin",
                userPwd:"123456"
            },
            /*data:{
                userName:$("#userName").val(),
                userPwd:$("#userPwd").val()
            },*/
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    window.location.href=ctx+"/index";
                }else{
                    layer.msg(data.msg, {icon: 5});
                    $(_this).text("登录").removeAttr("disabled").removeClass("layui-disabled");
                }
            }
        })
    }


    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
});
