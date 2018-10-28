var info={
    phone:'',
    veri_code:'',
    new_pwd:'',
    confirm_pwd:''
};
// 发送手机验证码
$("#forget_send").click(function(){
    if($(this).attr('value') == '发送验证码'){
        upTime_two(60);
        info.phone = document.getElementById("phone").value;
        if(info.phone == null || info.phone ==''){
            layer.alert("手机号不能为空");
            return false;
        }

        $.ajax({
            url:"getVeCode",
            type:"post",
            data:{phone:info.phone},
            dataType:"json",
            success:function(res){
                if(res.result =0){
                    layer.alert("成功");
                }else{
                    layer.alert("失败");
                }
            }
        })
    }
});

// 发送倒计时
function upTime_two(n){
    if(n>0){
        $("#forget_send").attr('disabled',true);
        $("#forget_send").html("重新发送("+n+")");
        $("#forget_send").css("background","#cccccc");
        setTimeout(function(){
            upTime_two(--n);
        },1000);
    }else {
        $("#forget_send").attr('disabled',false);
        $("#forget_send").css("background","#2176d6");
        $("#forget_send").html("发送验证码");
    }
}


$(document).ready(function () {

});
