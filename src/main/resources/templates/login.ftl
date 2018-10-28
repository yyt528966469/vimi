<!DOCTYPE html>
<html style="height: 100%;width: 100%;">

	<head>
		<meta charset="UTF-8">
		<title>登录注册</title>
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
        <script src="activity/vendor/jquery-3.1.1.min.js"></script>
        <script src="activity/vendor/bootstrap.min.js"></script>
        <script src="activity/vendor/layer/layer.min.js"></script>
		<script>


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
	</head>

	<body style="background-size: 100% 100%;width: 100%;height: 100%;">
		<div class="content">
			<div class="content_left">
				<img style="margin-top:100px;margin-left:50px;" src="images/aaa.jpg"/>
			</div>
			<div class="content_right">
				<div class="content_login">
					<p class="dengl">登 录</p>
					<div><i> <img src="images/tubiao1.png" /> </i><input class="input" id="username" type="text" placeholder="请输入您的账号" /></div>
					<div><i> <img src="images/tubiao2.png" /> </i><input class="input"  id="password" type="password" placeholder="请输入密码" /></div>
					<h3><a href="#" >忘记密码?</a></h3>
					<br />
					<button class="login" onclick="userLogin()" >登录</button>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>
	</body>
<script>
    $("body").keydown(function() {
        //alert("111")
        if (event.keyCode == "13") {//keyCode=13是回车键
            userLogin();
        }
    });
</script>
</html>