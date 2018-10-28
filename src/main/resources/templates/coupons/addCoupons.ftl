<html xmlns:th="http://www.thymeleaf.org">
<head >
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/x-icon" href="../activity/img/log.png">
    <title>后台管理系统</title>
    <link rel="stylesheet" href="../activity/vendor/font-awesome.min.css">
    <link rel="stylesheet" href="../activity/vendor/AdminLTE.min.css">
    <link rel="stylesheet" href="../activity/vendor/bootstrap.min.css">
    <link rel="stylesheet" href="../activity/vendor/ui.jqgrid.css">
    <link rel="stylesheet" href="../activity/vendor/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="../activity/style/main.css">
    <link rel="stylesheet" href="../activity/style/addActivity.css">
    <link rel="stylesheet" href="../activity/style/addLottery.css">

    <link href="../activity/vendor/bootstrap-fileinput/css/fileinput.min.css" rel="stylesheet">
    <link href="../activity/vendor/bootstrap-fileinput/css/fileinput-rtl.min.css" rel="stylesheet">
    <script src="../activity/vendor/jquery-1.10.2.min.js"></script>
    <script src="../activity/vendor/bootstrap.min.js"></script>
    <script src="../activity/vendor/moment.min.js"></script>
    <script src="../activity/vendor/moment-with-locales.min.js"></script>
    <script src="../activity/vendor/bootstrap-datetimepicker.min.js"></script>
    <script src="../activity/vendor/grid.locale-cn.js"></script>
    <script src="../activity/vendor/layer/layer.min.js"></script>
    <script src="../activity/vendor/layer/laydate/laydate.js"></script>
    <script src="../activity/js/main.js"></script>
    <script src="../activity/js/activityList.js"></script>
    <script src="../activity/vendor/jquery.jqGrid.min.js"></script>
    <script src="../activity/js/addActivity.js"></script>
    <script src="../activity/js/addLottery.js"></script>

    <script src="../activity/vendor/bootstrap-fileinput/js/fileinput.min.js"></script>
    <script src="../activity/vendor/bootstrap-fileinput/js/locales/zh.js"></script>
</head>
<section class="content" height="100%" >
<div class="row">
  <div class="col-xs-12">
    <div class="box-header">
      <h4 class="box-title">
              修改优惠券
          </h4>
    </div>
    <div class="box-body">
        <form id="myform">

            <input type="hidden" name="coupons_id" value="${t_coupons.coupons_id!''}"/>
      <div class="box-line">
        <span class="title mid-inline-block">面值：</span>
        <input class="long"  maxlength="5" aria-required="true" required=""  name="coupons_price"  value="${t_coupons.coupons_price!'' }" placeholder="请输入面值"/><span style="color: red" id="nameInfo"></span>
      </div>
            <div class="box-line">
                <span class="title mid-inline-block">起送金额：</span>
                <input class="long"  maxlength="5" aria-required="true" required=""  name="qs_je"  value="${t_coupons.qs_je!'' }" placeholder="请输入起送金额"/><span style="color: red" id="nameInfo"></span>
            </div>

      <div class="box-line">
        <span class="title mid-inline-block">有效期：</span>
        <input class="long" maxlength="5" aria-required="true" required="" name="effective_day" value="${t_coupons.effective_day!'' }" placeholder="请输入有效期"/> 天
      </div>



      <div class="box-line">
        <span class="title mid-inline-block"></span>
        <button class="btn btn-red">
          <span>保存</span>
        </button>
      </div>
        </form>
    </div>
  </div>
</div>
</section>

<script>
    $("#myform").submit(function () {

        $.ajax({
            url:"../coupons/saveCoupons",
            type:"post",
            data:$("#myform").serialize(),
            dataType:"json",
            success:function(data){
                if(data.result=='0'){
                    layer.alert("保存成功",function (index) {
                        window.location.href="../coupons/main";
                    });
                }else {
                    return false;
                }
            },
            error : function() {
                alert('保存异常');
            }

        });
        return false;
    });



</script>
</html>