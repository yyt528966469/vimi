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
                    <input type="hidden" id="order_id" name="order_id" value="${order_id!''}"/>
                    <div class="box-line">
                        <span class="title mid-inline-block">物流公司：</span>
                        <select name="name">
                            <option >==请选择==</option>
                            <#list expressList as express>
                                <option value="${express.name}">${express.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">物流单号：</span>
                        <input class="long"  maxlength="20" aria-required="true" required=""  name="express_num"  value="" placeholder="请输入物流单号"/>
                    </div>
                </form>
                <div class="box-line">

                    <span class="title mid-inline-block"></span>
                    <button onclick="sureLogistics()" class="btn btn-red">
                        <span>确认</span>
                    </button>
                    <span class="title mid-inline-block"></span>
                    <button onclick="getStoreEdit()" class="btn">
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

function sureLogistics() {
    //var order_id=$("#order_id").val();
    $.ajax({
        url:"../order/sureLogistics",
        type:"post",
        data:$("#myform").serialize(),
        dataType:"json",
        success:function(data){

            if(data.result=='0'){
                layer.alert("发货成功",function () {
                    //$("#table_list").jqGrid("setGridParam",{}).trigger("reloadGrid");
                    //layer.close(index);
                    window.parent.location.reload();
                });
            }
        }

    });
}



</script>
</html>