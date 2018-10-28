<html lang="en">
<head>
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
    <link rel="stylesheet" href="../jqgrid/css/css/redmond/jquery-ui-1.8.16.custom.css?aa=1">
    <link rel="stylesheet" href="../jqgrid/css/ui.jqgrid.css">

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
    <script src="../jqgrid/js/jquery.jqGrid.src.js"></script>
    <script src="../jqgrid/js/i18n/grid.locale-cn.js"></script>
</head>
<section class="content" height="100%" >
<div class="row">
  <div class="col-xs-12">
    <div class="box-header">
      <#--<h4 class="box-title">用户管理</h4>-->

      <button id=""  onclick="editRecharge('')" class="btn btn-red pull-right">
        <img src="../activity/img/add.png"/>
        <span>新增</span>
      </button>

    </div>
    <div class="box-body">

      <table id="userInfoTable" class="table table-bordered table-hover">
        <thead>
          <tr>
            <th>ID</th>
            <th>面值</th>
            <th>支付金额</th>
            <th>操作</th>
          </tr>
        <#list rechargeList as recharge>
            <tr>
                <td>${recharge_index+1}</td>
                <td>${recharge.price}</td>
                <td>${recharge.pay_price}</td>
                <td><a href="###" onclick="editRecharge('${recharge.id}')">编辑</a>&nbsp;&nbsp;&nbsp; <a href="###" onclick="deleteRecharge('${recharge.id}')">删除</a></td>
            </tr>
        </#list>
        </thead>
      </table>
    </div>
  </div>
</div>
</section>


<script>

    //绑定查询事件
   /* $("#searchBtn").click(function(){
        var start_time = $("#start_time").val();
        var end_time = $("#end_time").val();
        $("#table_list").jqGrid('setGridParam',{
            url:"../prizerecord/list",
            postData:{end_time:end_time,start_time:start_time}, //发送数据
            page:1
        }).trigger("reloadGrid"); //重新载入
    });*/


    function toNum(num){
       /* <![CDATA[*/
        if(num < 10){
            num="0"+num;
        }
        /*]]>*/
        return num;
    }
    
    function editRecharge(id) {
        window.location.href="../uservip/addOrEditRecgarge?id="+id;
    }

    function deleteRecharge(id) {
        layer.confirm("是否确认删除",function () {
            $.ajax({
                url:"../uservip/deleteRecharge",
                type:"post",
                data:{id:id},
                dataType:"json",
                success:function(data){
                    if(data.result=='0'){
                        layer.alert("删除成功",function (index) {

                            window.location.reload();
                        });
                    }
                }

            });
        });
    }


</script>
</html>