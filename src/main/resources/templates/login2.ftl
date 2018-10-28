<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <title>微课堂后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">


    <link href="activity/style/forget_pwd.css" rel="stylesheet" type="text/css" />

</head>

<body class="background">
<h1>微课堂后台管理系统<sup>2018</sup></h1>

<div class="login" style="margin-top:50px;">

    <div class="header">
        <div class="switch" id="switch" style="text-align: center;left: 146px">
            <a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
           <#-- <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">忘记密码</a>-->
            <#--<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>-->
        </div>
    </div>


    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">

        <div class="web_login" id="web_login">
            <div class="login-box">
                <div class="login_form">
                    <form action="" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm"
                          method="post"><input type="hidden" name="did" value="0"/>
                        <input type="hidden" name="to" value="log"/>
                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="u">帐号：</label>
                            <div class="inputOuter" id="uArea">
                                <input type="text" id="username" name="username" class="inputstyle"/>
                                <span id="nameInfo" style="color: red"></span>
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea">
                            <label class="input-tips" for="p">密码：</label>
                            <div class="inputOuter" id="pArea">
                                <input type="password" id="password" name="password" class="inputstyle"/>
                            </div>
                        </div>
                        <div style="padding-left:50px;margin-top:20px;">
                            <input type="button" onclick="userLogin()" value="登 录" style="width:150px;" class="button_blue"/></div>
                    </form>
                </div>

            </div>

        </div>

    </div>

    <!--忘记密码-->
    <#--<div class="qlogin" id="qlogin" style="display: none;">
        <div class="web_forget">
            <!--<form name="form2" id="regUser" accept-charset="utf-8"  action="" method="post">&ndash;&gt;
            <!--<input type="hidden" name="to" value="reg"/>&ndash;&gt;
            <!--<input type="hidden" name="did" value="0"/>&ndash;&gt;
            <div class="reg_form" id="reg-ul">
                <!-- <div id="userCue" class="cue">快速注册请注意格式</div> &ndash;&gt;
                <div class="forget_item">
                    <label class="forget_item_name">手机号：</label>
                    <input class="forget_item_input input_phone" type="text" id="phone" maxlength="11" />
                    <button class="forget_item_send" id = "forget_send" value="发送验证码">发送验证码</button>
                </div>
                <div class="forget_item">
                    <label class="forget_item_name">验证码：</label>
                    <input class='forget_item_input' type="text" maxlength="6" id='veri_code'/>
                </div>
                <div class="forget_item">
                    <label class="forget_item_name">新密码：</label>
                    <input class="forget_item_input" type="password" id="new_pwd"  name="new_pwd" maxlength="16"/>
                </div>
                <div class="forget_item">
                    <label class="forget_item_name">确认密码：</label>
                    <input class="forget_item_input" type="password" id="confirm_pwd" name="confirm_pwd" maxlength="16"/>
                </div>
                <div class="forget_item" style="padding-bottom: 0">
                    <button type="button" id="reg" class="submit" value="提交">提&nbsp;交</button>
                </div>
            </div>
            <!--</form>&ndash;&gt;
        </div>
    </div>-->

</div>
<script src="activity/vendor/jquery-3.1.1.min.js"></script>
<script src="activity/vendor/bootstrap.min.js"></script>
<script src="activity/vendor/layer/layer.min.js"></script>
<script type="text/javascript" src="activity/js/login.js"></script>
<script type="text/javascript" src="activity/js/forget_pwd.js?vecode=12"></script>
<script>
    $("body").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
            userLogin();
        }
    });

    /* $("body").keydown(function() {
         if (event.keyCode == "13") {//keyCode=13是回车键
             userLogin();
         }
     });*/

    if (top.location != location){
        top.location.href = location.href;
    }
    function userLogin() {

        var username=$("#username").val();
        if(!username){
            //$("#nameInfo").text("请输入用户名");
            return;
        }
        var password=$("#password").val();
        if(!password){
            //$("#wordInfo").text("请输入密码");
            return;
        }
        $.ajax({
            url:"checkUser",
            type:"post",
            data:{username:username,password:password},
            dataType:"json",
            success:function(data){

                if(data.result=='0'){
                    //alert("登录成功");
                    window.location.href="index";
                }else if(data.result=='1'){
                    $("#nameInfo").text("该用户名不存在");
                    //
                }else if(data.result=='2'){
                    $("#nameInfo").text("用户名或密码错误");
                }else if(data.result=='3'){
                    $("#nameInfo").text("该账号被禁用！");
                }
            }
        });
    }





    var jzmm= getCookie("jzmm");
    //alert(jzmm);
    if(jzmm){
        $("#jzmm").attr("checked",true);
        var user_name= getCookie("user_name");
        var user_word= getCookie("user_word");
        $("#username").val(user_name)
        $("#password").val(user_word)
    }
    $("#jzmm").click(function () {
        var checked=this.checked;
        if(checked){
            setCookie("jzmm","1",7);
            var username=$("#username").val();
            var password=$("#password").val();
            if(username){
                setCookie("user_name",username,7);
            }
            if(password){
                setCookie("user_word",password,7);
            }
        }else {
            delCookie("jzmm");
        }
    });

    function setCookie(c_name,value,expiredays)
    {
        var exdate=new Date()
        exdate.setDate(exdate.getDate()+expiredays)
        document.cookie=c_name+ "=" +escape(value)+
                ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
    }

    function getCookie(c_name)
    {
        if (document.cookie.length>0)
        {
            c_start=document.cookie.indexOf(c_name + "=")
            if (c_start!=-1)
            {
                c_start=c_start + c_name.length+1
                c_end=document.cookie.indexOf(";",c_start)
                if (c_end==-1) c_end=document.cookie.length
                return unescape(document.cookie.substring(c_start,c_end))
            }
        }
        return ""
    }

    function delCookie(name)
    {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval=getCookie(name);
        if(cval!=null)
            document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    }


</script>
</body>

</html>