<html >
<head >
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/x-icon" href="../activity/img/log.png">
    <title>活动后台管理系统</title>
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
                    我的店铺
                </h4>
            </div>
            <div class="box-body">
                <form id="myform">

                    <input type="hidden" name="store_id" value="${store_info.store_id!''}"/>

                    <div class="box-line">
                        <span class="title mid-inline-block">店铺名称：</span>
                        ${store_info.store_name!'' }
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">店铺简介：</span>
                        ${store_info.store_intro!'' }
                    </div>
                <#--<div class="box-line">
                    <span class="title mid-inline-block">机构负责人：</span>
                    <input class="long" maxlength="25" aria-required="true" required="" name="responsibility" value="${store_info.responsibility!'' }" placeholder="请输入负责人姓名"/>
                </div>-->
                    <div class="box-line">
                        <span class="title mid-inline-block">电话：</span>
                        ${store_info.phone!'' }
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">微信号：</span>
                        ${store_info.wechat!'' }
                    </div>

                    <div class="box-line">
                        <span class="title mid-inline-block">营业时间：</span>
                    ${store_info.start_str!'' }-${store_info.end_str!'' }
                    </div>

                    <div class="box-line">
                        <span class="title mid-inline-block">店铺图片：</span>
                        <label for="cover" style="width: 25%;">
                            <img src="${store_info.img_src!''}">
                        </label>

                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">店铺坐标：</span>
                        经度：${store_info.longitude!'' }&nbsp;&nbsp;&nbsp;&nbsp;
                        纬度： ${store_info.latitude!'' }
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">店铺地址：</span>
                        ${store_info.province!'' }
                        ${store_info.city!'' }
                        ${store_info.county!'' }
                        ${store_info.address!'' }
                    </div>

                    <div class="box-line">
                        <span class="title mid-inline-block">包装费：</span>
                        ￥${t_base.packing_price!'0' }
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">配送费：</span>
                       ￥${t_base.distribution_price!'0'}
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">桌号：</span>
                        ${t_base.table_start!'0'}号-${t_base.table_end!'0'}号
                    </div>


                </form>
                <div class="box-line">
                    <span class="title mid-inline-block"></span>
                    <button onclick="getStoreEdit()" class="btn btn-red">
                        <span>修改</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>

<script>

function getStoreEdit() {
    window.location.href="../store/getStoreEdit";
}



</script>
</html>