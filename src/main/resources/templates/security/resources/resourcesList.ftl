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
</head>
<section class="content" height="100%" >
<div class="row">
  <div class="col-xs-12">
    <div class="box-header">
      <h4 class="box-title">菜单管理</h4>
      <button id="" onclick="addRes('')" class="btn btn-red pull-right">
        <img src="../activity/img/add.png"/>
        <span>新增菜单</span>
      </button>
    </div>
    <div class="box-body">
        <div class="ibox" style="">
            <div class="ibox-content">

                <div class=" jqGrid_wrapper sk-spinner sk-spinner-fading-circle">
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
            postData:{name: searchString},
            url:"../resources/list",
            datatype: "json",
            mtype: 'POST',
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
            colNames: ['ID','菜单名称','父菜单','菜单类型','图标','操作'],
            colModel: [
                {
                    name: 'res_id',
                    index: 'res_id',
                    hidden:true,
                    key:"ture"
                },
                {
                    name: 'name',
                    index: 'name',
                    width: 20
                },
                {
                    name: 'par_name',
                    index: 'par_name',
                    width: 20,
                    formatter: function(cellvalue,options,rowObject) {
                        var result=cellvalue;
                        if(!cellvalue){
                            result="none";
                        }
                        return result;
                    }
                },
                {
                    name: 'res_type',
                    index: 'res_type',
                    width: '30',
                    formatter:'select',
                    formatoptions:{value:{ 0:'目录',1:'菜单'}}

                },
                {
                    name: 'img',
                    index: 'img',
                    width: 20
                },
                {
                    name: 'oper',
                    index: 'oper',
                    width: '30',
                    formatter: function(cellvalue,options,rowObject) {
                        var id=rowObject.res_id;
                       var v_result="";
                        /* <![CDATA[*/

                            v_result += " &nbsp;&nbsp;&nbsp; <button  style='width: 70px;' class=\"btn btn-info\" type=\"button\"  onClick=\"editRes('" + id + "');\"><i class=\"glyphicon glyphicon-pencil\"></i> 编辑</button>";
                            //v_result+="&nbsp;&nbsp;&nbsp; <button class=\"btn btn-success btn-outline\" type=\"button\"  onClick=\"deletePrize('"+ id+ "','0');\"><i class=\"fa fa-expeditedssl\"></i> 删除</button>";



                            v_result += "&nbsp;&nbsp;&nbsp; <button  style='width: 70px;' class=\"btn btn-success btn-outline\" type=\"button\"  onClick=\"deleteRes('" + id + "');\"><i class=\"fa fa-expeditedssl\"></i> 删除</button>";

                        /*]]>*/
                        return v_result;
                    }
                }
            ],
            sortable:true,
            sortname: 'res_id',
            sortorder: 'desc',
            pager: "#pager_list",
            caption: "中奖名单管理",
        });

    });



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

    //单选空间
    function beforeSelectRow()
    {
        $("#jqgridId").jqGrid('resetSelection');
        return(true);
    }


    function addRes() {
        //if(activit_id)
        //$("#myiframe").load("resources/addRes");
        window.location.href="../resources/addRes";
    }

    function editRes(res_id) {
        //$("#myiframe").load("resources/editRes?res_id="+res_id)
        window.location.href="../resources/editRes?res_id="+res_id;
    }
    
    function deleteRes(id) {
        layer.confirm("确认删除？",function () {
            $.ajax({
                url: "../resources/deleteRes",
                type: "post",
                data: {id: id},
                dataType: "json",
                success: function (data) {
                    if(data.code==200){
                        layer.alert("删除成功",function (index) {
                            layer.close(index);
                            $("#table_list").jqGrid('setGridParam').trigger("reloadGrid");
                        });
                    }else {
                        layer.alert("删除失败");
                    }

                }
            });
        })
    }


</script>
</html>