<html lang="en">
<head>
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
      <h4 class="box-title">管理员管理</h4>

      <button id=""  onclick="addUser('')" class="btn btn-red pull-right">
        <img src="../activity/img/add.png"/>
        <span>新增管理员</span>
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
            url:"../sysuser/list",
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
            colNames: ['ID','等级','用户名','创建时间','状态','操作'],
            colModel: [
                {
                    name: 'user_id',
                    index: 'user_id',
                    hidden:true,
                    key:"ture"
                },
                {
                    name: 'rank',
                    index: 'rank',
                    hidden:true,
                },
                {
                    name: 'username',
                    index: 'username',
                    width: 20
                },
                {
                    name: 'createTime',
                    index: 'createTime',
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
                    name: 'status',
                    index: 'status',
                    width: '30',
                    formatter:'select',
                    formatoptions:{value:{ 0:'启用',1:'禁用'}}

                },
                {
                    name: 'oper',
                    index: 'oper',
                    width: '30',
                    formatter: function(cellvalue,options,rowObject) {

                        var id = rowObject.user_id;
                        var status=rowObject.status;
                        var rank=rowObject.rank;
                        //var rank="";
                        var v_result ="";
                        /* <![CDATA[*/
                            if(status==0){

                                if(rank==9){
                                    v_result = "<button style='width: 70px;' class='btn btn-default' type='button' ><i class='fa fa-expeditedssl'></i> 禁用</button>";
                                }else {
                                    v_result = "<button style='width: 70px;' class='btn btn-danger' type='button' onClick='updateStatus(\""+ id+ "\",\"1\");'><i class='fa fa-expeditedssl'></i> 禁用</button>";
                                }
                            }else{
                                v_result="<button  style='width: 70px;' class='btn btn-success btn-outline' type='button'  onClick='updateStatus(\""+ id+ "\",\"0\");'><i class='fa fa-unlock'></i> 启用</button>";
                            }

                            v_result += " &nbsp;&nbsp;&nbsp; <button  style='width: 70px;' class='btn btn-info' type='button'  onClick='editUser(\"" + id + "\");'><i class='glyphicon glyphicon-pencil'></i> 编辑</button>";

                        /*]]>*/
                        return v_result;
                    }
                }
            ],
            sortable:true,
            sortname: 'prizeTime',
            sortorder: 'asc',
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

    function toNum(num){
       /* <![CDATA[*/
        if(num < 10){
            num="0"+num;
        }
        /*]]>*/
        return num;
    }

    function editUser(user_id) {
        //if(activit_id)
        //$("#myiframe").load("sysuser/editUser?user_id="+user_id);
        window.location.href="../sysuser/editUser?user_id="+user_id;
    }

    function addUser() {
        //if(activit_id)
        //$("#myiframe").load("sysuser/addUser");
        window.location.href="../sysuser/addUser";
    }

    function updateStatus(user_id,status) {
        var result="禁用";
        if(status=='0'){
            result="开启";
        }
        layer.confirm("是否确认"+result+"该用户",function () {
            $.ajax({
                url:"../sysuser/updateStatus",
                type:"post",
                data:{user_id:user_id,status:status},
                dataType:"json",
                success:function(data){
                    if(data.result=='0'){
                        layer.alert(result+"成功",function (index) {
                            $("#table_list").jqGrid("setGridParam",{}).trigger("reloadGrid");
                            layer.close(index);
                        });
                    }
                }

            });
        });
       /* if(confirm("是否确认"+result+"该用户")){

        }*/
    }
</script>
</html>