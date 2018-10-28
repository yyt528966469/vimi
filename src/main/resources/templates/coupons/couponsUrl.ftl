<head>
    <link rel="stylesheet" href="../activity/vendor/font-awesome.min.css">
    <link rel="stylesheet" href="../activity/vendor/AdminLTE.min.css">
    <link rel="stylesheet" href="../activity/vendor/bootstrap.min.css">
    <link rel="stylesheet" href="../activity/vendor/bootstrap-switch.css">
    <link rel="stylesheet" href="../activity/vendor/ui.jqgrid.css">
    <link rel="stylesheet" href="../activity/vendor/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="../activity/style/main.css">
    <link rel="stylesheet" href="../activity/style/addActivity.css">
    <link rel="stylesheet" href="../activity/style/addLottery.css">

    <link href="../activity/vendor/bootstrap-fileinput/css/fileinput.min.css" rel="stylesheet">
    <link href="../activity/vendor/bootstrap-fileinput/css/fileinput-rtl.min.css" rel="stylesheet">
    <style>
        .text {
            display: none;
        }
        .on{
            color:#333;
        }
        .off{
            color:#eee;
        }
        .circle {
            position: absolute;
            display: inline-block;
        }

        #radio ~label {
            background-color: grey;
        }

        #radio ~label .circle {
            left: 0;
            transition: all 0.3s;
            --webkit-transition: all 0.3s;
        }

        #radio ~label .on {
            display: none;
        }

        #radio ~ label .off {
            display: inline-block;
        }

        #radio:checked ~ label {
            background: lime;
        }

        #radio:checked ~label .circle {
            left: 50px;
        }
        #radio:checked ~label .on {
            display: inline-block;
        }

        #radio:checked ~ label .off {
            display: none;
        }

        label {
            display: inline-block;
            position: relative;
            height: 30px;
            width: 80px;
            border-top-left-radius: 15px 50%;
            border-bottom-left-radius: 15px 50%;
            border-top-right-radius: 15px 50%;
            border-bottom-right-radius: 15px 50%;
            box-shadow: 0 0 2px black;
        }

        label .circle {
            display: inline-block;
            height: 26px;
            width: 26px;
            border-radius: 50%;
            border: 2px solid #333;
            background-color: #eee;
        }

        label .text {
            text-indent: 30px;
            line-height: 28px;
            font-size: 18px;
            font-family: sans-serif;
            text-shadow: 0 0 2px #ddd;
        }
    </style>
</head>
<body>
<section class="content" height="100%" >
<div class="row">
  <div class="col-xs-12">
    <div >
        活动链接：
        <textarea readonly style="width: 80%;outline:none;resize:none;" id="contents">../get_coupon/get_coupon?url=${coupons_id}</textarea>
        <br>
        <br>
      <button  id="addActivity" onclick="copyUrl2()" class="btn btn-red pull-center">

        <span>复制链接</span>
      </button>
        <#--<input type="button" id="test_id" onclick="copyUrl2(this)" value="复制">
        <textarea readonly  id="contents" >11111</textarea>-->
    </div>



  </div>
</div>
</section>
<script src="../activity/vendor/jquery-1.10.2.min.js"></script>
<script src="../activity/vendor/moment.min.js"></script>
<script src="../activity/vendor/moment-with-locales.min.js"></script>

<script src="../activity/vendor/grid.locale-cn.js"></script>
<script src="../activity/vendor/layer/layer.min.js"></script>
<script src="../activity/vendor/layer/laydate/laydate.js"></script>




<script>




    function copyUrl2()
    {

        var e=document.getElementById("contents");//对象是contents
        e.select(); //选择对象
        tag=document.execCommand("Copy"); //执行浏览器复制命令
        if(tag){
            alert('复制内容成功');
        }

    }



</script>
</body>