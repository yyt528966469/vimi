<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script src="js/jquery-1.7.2.min.js"></script>
    <script src="js/index.js"></script>
    <script src="activity/vendor/layer/layer.min.js"></script>

</head>
<style>
    .userIm{
        width: 6%;
        height: 33px;
        float: right;
        margin-top: 17px;
        margin-right: 30px;
        background: rgba(251,251,251,0.1);

    }
    .top h1{
        margin-top: 10px;
        margin-left: 20px;
    }
    .list_right{
        width: 79%;
        height: 90%;
        /*height: 600px;*/
        /*border: 1px solid red;*/
        margin-top:68px;
        position: absolute;
        right: 50px;
        /*float: left;
        margin-left:18%;*/
        margin-top: 88px;
    }

    .list-box ul{
        width: 60%;margin-left:70px ;

    }
    .list-box li{
        background-color: white;
    }
    /*.item {
                height: 40px;


                white-space: nowrap;

                background-color:rgb(230,230,230);
                margin-bottom: 10px;
                font-size: 14px;
            }
            .item li{
                display: inline-block;
                white-space: nowrap;
                width: 20%;

                line-height: 38px;
                margin-left: 20px;
            }
            .item1 {
                height: 40px;
                font-size: 14px;

                white-space: nowrap;
                border: 1px solid #ccc;
                margin-bottom: 10px;
            }
            .item1 li{
                display: inline-block;
                white-space: nowrap;
                width: 20%;
                color: #b2b2b2;
                line-height: 38px;
                margin-left: 20px;
            }
            style="background-color: #f8f8f8;"
            * */
</style>
<body style="background-color: #f8f8f8;">
<!-- top -->
<div class="top">
    <div>
        <h1>薇明科技</h1>
        <select onchange="changeLogout(this)" class="userIm">
            <option value="0">${ACTIVITY_WEB_LAST_USERNAME}

            </option>
            <option value="1">退出登录

            </option>
        </select>
    </div>
</div>





<!-- list -->
<div style="width: 98%;display: inline-block;">
    <div class="list" >
        <!--<img src="images/logo.png">-->
        <ul class="list-box" id="list-box" ">
        <li url="echats" class="list-box-1" style="">
            <span>首页</span>

        </li>
				<#list list as res>


		<li class="list-box-2">
            <span>${res.name}</span>
            <ul>
				<#list res.children as child>
				<li url="${child.res_url}">
					${child.name}
                </li>
				</#list>
			<#--<li>
                机构分类

            </li>-->
            </ul>
        </li>
				</#list>



        </ul>

    </div>
    <div class="list_right">

        <iframe id="iframe-content"   height="100%" frameborder="no" border="0″ marginwidth="0″ marginheight="0" allowtransparency="yes"  src="echats" width="100%"    ></iframe>


    </div>
    <div style="clear: both;"></div>
</div>
<script>
    /*function changeFrameHeight() {
        var ifm = document.getElementById("iframe-content");
        ifm.height = document.documentElement.clientHeight;
        alert(document.documentElement.clientHeight);

    }*/
    //			window.onresize = function() {
    //				changeFrameHeight();
    //
    //			}
    //			jQuery(".set-content").slide({autoPlay:false,trigger:"click",delayTime:700,pnLoop:false});


    function setIframeHeight(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
            }
        }
    };

    window.onload = function () {
        setIframeHeight(document.getElementById('external-frame'));
    };
    function changeLogout(obj) {
        var value=obj.value;
        console.log(value);
        if(value==1){
            userlogout();
        }
    }

    function userlogout() {
        layer.confirm("您确定退出登录",function () {
            window.location.href="logout";
        });

    }
</script>
</body>

</html>