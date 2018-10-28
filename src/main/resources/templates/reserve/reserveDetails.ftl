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

                    <input type="hidden" id="order_id" value="${t_reserve.id!''}">

                    <div class="box-line">
                        <span class="title mid-inline-block">预约单号：</span>
                        ${t_reserve.id!''}
                        <span style="left:272px;">
                        <span style="margin-left: 50px;" class="title mid-inline-block">创建时间：</span>
                    ${t_reserve.create_time?string('yyyy-MM-dd HH:mm:ss')}
                            </span>
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">订单状态：</span>
                    ${t_reserve.status_name}
                        <span style="left:272px;">
                        <span style="margin-left: 150;" class="title mid-inline-block">预约时间：</span>
                    <em style="color: red">${t_reserve.reserve_time?string('MM-dd HH:mm')}</em>
                        </span>
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">备注：</span>
                    ${t_reserve.remarks!''}

                    </div>


                    <div class="box-line">
                        -------------------------用户信息--------------------------
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">姓名：</span>
                    ${t_reserve.name!''}
                        <#if t_reserve.sex=='F'>
                            (先生)
                        <#elseif  t_reserve.sex=='M'>
                            (女士)
                        <#else >
                            保密
                        </#if>

                    </div>

                    <div class="box-line">
                        <span class="title mid-inline-block">电话：</span>
                    ${t_reserve.phone!''}
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">人数：</span>
                    ${t_reserve.person_num!''}
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">位置：</span>
                    ${t_reserve.position!''}
                    </div>
                    <#if t_reserve.status==0>
                    <div class="box-line">
                        <span class="title mid-inline-block">操作：</span>
                        <input type="radio" checked onclick="changeStatus(this)" value="1" name="status"> 成功
                        <input type="radio" onclick="changeStatus(this)" value="6" name="status"> 失败
                    </div>
                    <div class="box-line" id="reason" style="display: none">
                        <span class="title mid-inline-block">原因：</span>
                        <input type="text" placeholder="请输入失败原因"  id="reason_a" name="">

                    </div>
                    </#if>
                    <#if t_reserve.status!=0>
                    <div class="box-line">
                        <span class="title mid-inline-block">操作：</span>
                       <#if t_reserve.status==1>预订成功</#if>
                        <#if t_reserve.status==6>预订失败</#if>
                        <#if t_reserve.status==7>已取消</#if>
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">
                            <#if t_reserve.status==1>截止时间:</#if>
                            <#if t_reserve.status!=1>原因：</#if>
                            </span>
                        <#if t_reserve.status==1>保留10分钟</#if>

                        <#if t_reserve.status!=1>${t_reserve.reason!''}</#if>
                    </div>
                    </#if>

                </form>
                <div class="box-line">
                <#if t_reserve.status==0>
                    <span class="title mid-inline-block"></span>
                    <button onclick="updateStatus()" class="btn btn-red">
                        <span>确定</span>
                    </button>
                </#if>

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

function changeStatus(obj) {
    var value=obj.value;
    if(value==1){
        $("#reason").hide();
    }else {
        $("#reason").show();
    }

}


function updateStatus() {
    var arr=document.getElementsByName("status");
    var status=1;
    $(arr).each(function () {
        if(this.checked){
            status=this.value;
        }
    });
    var id=$("#order_id").val();
    var reason_a=$("#reason_a").val();
    $.ajax({
        url:"../reserve/updateStatus",
        type:"post",
        data:{id:id,status:status,reason:reason_a},
        dataType:"json",
        success:function(data){
            console.log(data.code);
            if(data.code==200){
                layer.alert("处理成功",function () {
                    window.parent.location.reload();
                })
            }
        }
    });
}



</script>
</html>