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
    <#--<script src="../activity/vendor/jquery.jqGrid.min.js"></script>-->
    <script src="../activity/js/addActivity.js"></script>
    <script src="../activity/js/addLottery.js"></script>
    <script src="../jqgrid/js/jquery.jqGrid.src.js"></script>
    <script src="../jqgrid/js/i18n/grid.locale-cn.js"></script>
    <style>
        .nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active{
            border: none;
            color: red;
        }
        .nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover{

            color: #555;
            cursor: default;
            background-color: #fff;
            border: none;
            /*border: 1px solid #ddd;*/
            border-bottom:2px solid rgb(251,78,80);
        }
    </style>

</head>
<section class="content" height="100%" >
<div class="row">
  <div class="col-xs-12" >
    <div class="box-header">
      <h4 class="box-title">订单管理</h4>


    </div>
      <div class="ibox">
      <div class="tabs-container">
      <ul id="status_ul" class="nav nav-tabs" style="border-bottom: 0px solid #e7eaec;">
          <li class="active"><a data-toggle="tab" onclick="searchStatus(null)"
                          aria-expanded="true" tabindex='-1'>所有订单</a></li>
          <!-- <li class=""><a data-toggle="tab" onclick="searchStatus(0)"
              aria-expanded="false" tabindex='0'>待接单</a></li> -->
          <#list orderStauteEnums as orderStaute>
               <li class=""><a data-toggle="tab" onclick="searchStatus(${orderStaute.index})"
                                     aria-expanded="true" tabindex='1'>${orderStaute.getName(orderStaute.index)}</a></li>
          </#list>


          <#--<li class=""><a data-toggle="tab" onclick="searchStatus(2)"
                          aria-expanded="false" tabindex='2'>待付款</a></li>
          <li class=""><a data-toggle="tab" onclick="searchStatus(3)"
                          aria-expanded="false" tabindex='3'>待收件</a></li>
          <li class=""><a data-toggle="tab" onclick="searchStatus(4)"
                          aria-expanded="false">待评价</a></li>
          <li class=""><a data-toggle="tab" onclick="searchStatus(5)"
                          aria-expanded="false">已完成</a></li>
          <!--<li class=""><a data-toggle="tab" onclick="searchStatus(6)"
              aria-expanded="false">已签收</a></li> &ndash;&gt;
          <li class=""><a data-toggle="tab" onclick="searchStatus(7)"
                          aria-expanded="false" tabindex="9">已取消</a></li>-->
          <!-- <li class=""><a data-toggle="tab" onclick="searchStatus(8)"
              aria-expanded="false" tabindex='8'>已作废</a></li> -->
      </ul>
      </div>
      </div>
    <div class="box-body">
        <input id="order_id" type="text" placeholder="输入订单编号">

        <button id="searchBtn"  class="btn btn-small btn-red left-gap">
            <span>搜索</span>
        </button>


        <div class="ibox" style="margin-top: 5px;">
            <div class="ibox-content">

                <div  class=" jqGrid_wrapper sk-spinner sk-spinner-fading-circle">
                    <table id="table_list"></table>
                    <div id="pager_list"></div>
                </div>
            </div>
        </div>
      <!--<table id="userInfoTable" class="table table-bordered table-hover">
        <thead>
          <tr>
            <th>ID</th>
            <th>登录名</th>
            <th>密码</th>
            <th>创建日期</th>
            <th>使用权限</th>
            <th>操作</th>
          </tr>
        </thead>
      </table>-->
    </div>
  </div>
</div>
</section>


<script>
    $(document).ready(function () {
        $.jgrid.defaults.styleUI = 'Bootstrap';
        // Examle data for jqGrid
        var searchString='';

        //绑定表格数据
        $("#table_list").jqGrid({
            postData:{eat_type:1},
            url:"../order/list",
            datatype: "json",
            height: "calc(100% - 185px)",
            autowidth: true,
            shrinkToFit: true,
            multiselect: false,
            multiboxonly:true,
            rownumbers: true,
            loadComplete:function(){
                $("#table_list").jqGrid('setLabel','rn', 'ID', {'text-align':'left'});
            },
            beforeSelectRow: beforeSelectRow,
            rowList: [10, 20, 30],
            root:"rows",
            colNames: ['ID','状态','订单编号','金额','电话','取餐时间','状态','创建时间','操作'],
            colModel: [
                {
                    name: 'order_id',
                    index: 'order_id',
                    hidden:true,
                    key:"ture"
                },
                {
                    name: 'status',
                    index: 'status',
                    hidden:true
                },
                {
                    name: 'order_id',
                    index: 'order_id',
                    align : "center",
                    width: 33
                },
                {
                    name: 'real_price',
                    index: 'real_price',
                    align : "center",
                    width: 10
                },
                {
                    name: 't_order_arrive.phone',
                    index: 't_order_arrive.phone',
                    align : "center",
                    width: 20
                },

                {
                    name: 't_order_arrive.arrive_time',
                    index: 't_order_arrive.arrive_time',
                    width: '31',
                    align : "center",
                    formatter: function(cellvalue,options,rowObject) {
                        var date=new Date(cellvalue);
                        var time=date.getFullYear()+"-";
                        time=time+toNum(date.getMonth()+1)+"-";
                        time=time+toNum(date.getDate())+" ";
                        time=time+toNum(date.getHours())+":";
                        time=time+toNum(date.getMinutes())+":";
                        time=time+toNum(date.getSeconds());
                        return time;
                    }
                },
                {
                    name: 'status_name',
                    index: 'status_name',
                    width: 11,
                    align : "center",
                    formatter: function(cellvalue,options,rowObject) {
                        //var id = rowObject.activit_id;

                        return cellvalue;
                    }
                },
                {
                    name: 'order_time',
                    index: 'order_time',
                    width: '31',
                    align : "center",
                    formatter: function(cellvalue,options,rowObject) {
                        var date=new Date(cellvalue);
                        var time=date.getFullYear()+"-";
                        time=time+toNum(date.getMonth()+1)+"-";
                        time=time+toNum(date.getDate())+" ";
                        time=time+toNum(date.getHours())+":";
                        time=time+toNum(date.getMinutes())+":";
                        time=time+toNum(date.getSeconds());
                        return time;
                    }

                },
                {
                    name: 'oper',
                    index: 'oper',
                    width: '30',
                    align : "center",
                    formatter: function(cellvalue,options,rowObject) {
                        var v_result = "";
                        var order_id=rowObject.order_id;
                        var status = rowObject.status;
                        v_result += " &nbsp; <a   class='a_click' style='width:65px;border:none; cursor: pointer;'   onClick='toDetails(\"" + order_id + "\");'> 详情</a>";
                        if(status==2){
                            v_result += " &nbsp; <a  class='a_click' style='width:65px;border:none; cursor: pointer;'    onClick='updateStatus(\"" + order_id + "\",3);'> 发货</a>";
                        }
                        return v_result;
                    }
                }
            ],
            sortable:true,
            sortname: 'order_time',
            sortorder: 'desc',
            pager: "#pager_list",
            caption: "订单管理",
        });

    });

    //绑定查询事件
    $("#searchBtn").click(function(){
        var order_id = $("#order_id").val();
        $("#table_list").jqGrid('setGridParam',{
            url:"../order/list",
            postData:{order_id:order_id,eat_type:1}, //发送数据
            page:1
        }).trigger("reloadGrid"); //重新载入
    });
    
    function searchStatus(status) {
        $("#table_list").jqGrid('setGridParam',{
            url:"../order/list",
            postData:{status:status,eat_type:1}, //发送数据
            page:1
        }).trigger("reloadGrid"); //重新载入
    }

    //单选空间
    function beforeSelectRow()
    {
        $("#jqgridId").jqGrid('resetSelection');
        return(true);
    }

    function toNum(num){
       /* <![CDATA[*/
        if(num < 10){
            num="0"+num;
        }
        /*]]>*/
        return num;
    }
    
    function toDetails(order_id) {
        layer.open({
            type: 2,
            title: '订单详情',
            shadeClose: true,
            shade: 0.8,
            area: ['90%', '90%'],
            content: '../order/toStoreDetails?order_id='+order_id //iframe的url
        });
    }


    
    function updateStatus(order_id,status) {
        var title="发货";
        if(status==3){
            title="发货";
        }
        layer.confirm("是否确认"+title,function () {
            $.ajax({
                url:"../order/updateStatus",
                type:"post",
                data:{order_id:order_id,status:status},
                dataType:"json",
                success:function(data){
                    if(data.result=='0'){
                        layer.alert(title+"成功",function (index) {
                            $("#table_list").jqGrid("setGridParam",{}).trigger("reloadGrid");
                            layer.close(index);
                        });
                    }
                }

            });
        });
    }

    function deleteOrg(org_id) {
        layer.confirm("是否确认删除",function () {
            $.ajax({
                url:"../eduorg/deleteOrg",
                type:"post",
                data:{org_id:org_id},
                dataType:"json",
                success:function(data){
                    if(data.result=='0'){
                        layer.alert("删除成功",function (index) {
                            $("#table_list").jqGrid("setGridParam",{}).trigger("reloadGrid");
                            layer.close(index);
                        });
                    }
                }

            });
        });
    }

       /* if(confirm("是否确认"+result+"该用户")){

        }*/
</script>
</html>