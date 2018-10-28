<html >
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
            <div class="box-body">
                <form id="myform">


                    <div class="box-line">
                        <span class="title mid-inline-block">订单号：</span>
                        ${t_order.order_id!''}

                        <span style="margin-left: 50px;" class="title mid-inline-block">下单时间：</span>
                    ${t_order.order_time?string('yyyy-MM-dd HH:mm:ss')}
                    </div>
                    <#--<div class="box-line">
                        <span class="title mid-inline-block">桌号：</span>
                    ${t_order.table_num}

                        <span style="margin-left: 100px;" class="title mid-inline-block">人数：</span>
                    ${t_order.person_num}
                    </div>-->
                    <div class="box-line">
                        <span class="title mid-inline-block">订单状态：</span>
                    ${t_order.status_name}
                        <span style="margin-left: 150px;" class="title mid-inline-block">订单金额：</span>
                    <em style="color: red">￥${t_order.real_price}</em>
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">备注：</span>
                    ${t_order.remarks!''}

                    </div>
                    <div class="box-line">
                        -------------------------商品信息--------------------------
                    </div>
                    <div class="box-line">
                        <#--<span class="title mid-inline-block">商品列表：</span>-->
                        <table style="width: 50%;">
                            <tr >
                                <th>商品名</th>
                                <th>数量</th>
                                <th>单价</th>
                                <th>总额</th>
                            </tr>
                            <#list t_order.commodity_infoList as commodity_info>
                                <tr>
                                    <td>${commodity_info.commodity_name}</td>
                                    <td>${commodity_info.comm_num}</td>
                                    <td>${commodity_info.commodity_price}</td>
                                    <td>${commodity_info.sum_price}</td>
                                </tr>
                            </#list>

                        </table>
                    </div>
                    <div class="box-line">
                        -------------------------其他信息--------------------------
                    </div>
                    <#if t_order.t_order_arrive??>
                        <div class="box-line">
                            <span class="title mid-inline-block">联系方式：</span>
                            ${t_order.t_order_arrive.phone!''}
                        </div>
                        <div class="box-line">
                            <span class="title mid-inline-block">预约时间：</span>
                            ${t_order.t_order_arrive.arrive_time?string('yyyy-MM-dd HH:mm:ss')}
                        </div>
                    </#if>
                    <#if t_order.t_order_scavenging??>
                        <div class="box-line">
                            <span class="title mid-inline-block">桌号：</span>
                            ${t_order.t_order_scavenging.table_num!''}号
                        </div>
                        <div class="box-line">
                            <span class="title mid-inline-block">人数：</span>
                            ${t_order.t_order_scavenging.person_num}
                        </div>
                    </#if>


                </form>
                <div class="box-line">
                    <span class="title mid-inline-block"></span>
                    <button onclick="getStoreEdit()" class="btn btn-red">
                        <span>关闭</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>

<script>

function getStoreEdit() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}



</script>
</html>