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
   <#-- <script src="../activity/vendor/jquery.jqGrid.min.js"></script>-->
    <script src="../activity/js/addActivity.js"></script>
    <script src="../activity/js/addLottery.js"></script>
    <script src="../jqgrid/js/jquery.jqGrid.src.js"></script>
    <script src="../jqgrid/js/i18n/grid.locale-cn.js"></script>
</head>
<section class="content" height="100%" >
<div class="row">
  <div class="col-xs-12">
    <div class="box-header">
      <h4 class="box-title">广告管理</h4>

      <button id=""  onclick="toAddOrEdit('')" class="btn btn-red pull-right">
        <img src="../activity/img/add.png"/>
        <span>新增广告</span>
      </button>

    </div>
    <div class="box-body">
        <div class="ibox" style="">
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
            postData:{name: searchString},
            url:"../tadver/list",
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
            colNames: ['ID','广告名称','顺序','状态','创建时间','操作'],
            colModel: [
                {
                    name: 'adver_id',
                    index: 'adver_id',
                    hidden:true,
                    key:"ture"
                },
                {
                    name: 'adver_name',
                    index: 'adver_name',
                    align : "center",
                    width: 25
                },
                {
                    name: 'sort',
                    index: 'sort',
                    align : "center",
                    width: 15
                },
                {
                    name: 'status',
                    index: 'status',
                    align : "center",
                    width: 10,
                    formatter: function(cellvalue,options,rowObject) {
                        //var id = rowObject.activit_id;

                        var result='';
                        if(cellvalue==0){
                            result="<i class=\"fa fa-fw fa-ban text-red\"></i>";
                        }else {
                            result="<i class=\"fa fa-fw fa-check-circle text-green\"></i>";
                        }
                        return result;
                    }
                },
                {
                    name: 'create_time',
                    index: 'create_time',
                    align : "center",
                    width: '30',
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
                    align : "center",
                    width: '40',
                    formatter: function(cellvalue,options,rowObject) {

                        var adver_id =rowObject.adver_id;
                        var status=rowObject.status;
                            var v_result = "";
                            v_result += "<a  class='a_click' style='width:65px;border:none; cursor: pointer;'  onClick='toAddOrEdit(\"" + adver_id + "\");'> 编辑</a>";
                        v_result += " &nbsp;&nbsp; <a  class='a_click' style='width:65px;border:none; cursor: pointer;'  onClick='deleteAdver(\"" + adver_id + "\");'> 删除</a>";
                        if (status==0){
                            v_result += "&nbsp;&nbsp; <a  class='a_click' style='width:65px;border:none; cursor: pointer;'  onClick='updateStatus(\"" + adver_id + "\",\"1\");'> 上架</a>";
                        }else {
                            v_result += "&nbsp;&nbsp; <a  class='a_click' style='width:65px;border:none; cursor: pointer;'  onClick='updateStatus(\"" + adver_id + "\",\"0\");'> 下架</a>";
                        }

                        return v_result;
                    }
                }
            ],
            sortable:true,
            sortname: 'sort',
            sortorder: 'asc',
            pager: "#pager_list",
            caption: "广告管理",
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

    function toNum(num){
       /* <![CDATA[*/
        if(num < 10){
            num="0"+num;
        }
        /*]]>*/
        return num;
    }

    function toAddOrEdit(adver_id) {
        //if(activit_id)
        //$("#myiframe").load("sysuser/editUser?user_id="+user_id);
        window.location.href="../tadver/toAddOrEdit?adver_id="+adver_id;
    }
    
    function updateStatus(adver_id,status) {
        var title="上架";
        if(status==0){
            title="下架";
        }

        layer.confirm("是否确认"+title+"该广告",function () {
            $.ajax({
                url:"../tadver/updateStatus",
                type:"post",
                data:{adver_id:adver_id,status:status},
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

    function deleteAdver(adver_id) {
        layer.confirm("是否确认删除",function () {
            $.ajax({
                url:"../tadver/deleteAdver",
                type:"post",
                data:{adver_id:adver_id},
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