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
      <h4 class="box-title">机构管理</h4>


    </div>
    <div class="box-body">
        <input id="org_name" type="text" placeholder="输入机构名称">

        <button id="searchBtn"  class="btn btn-small btn-red left-gap">
            <span>搜索</span>
        </button>
        <button id=""  onclick="toAddOrEdit('')" class="btn btn-red pull-right">
            <img src="../activity/img/add.png"/>
            <span>新增机构</span>
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
            postData:{name: searchString},
            url:"../eduorg/list",
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
            colNames: ['ID','审核类型','机构名称','类型','负责人','电话','审核状态','状态','创建时间','操作'],
            colModel: [
                {
                    name: 'org_id',
                    index: 'org_id',
                    hidden:true,
                    key:"ture"
                },
                {
                    name: 'examine_type',
                    index: 'examine_type',
                    hidden:true
                },
                {
                    name: 'org_name',
                    index: 'org_name',
                    width: 25
                },
                {
                    name: 't_org_type.type_name',
                    index: 't_org_type.type_name',
                    width: 15
                },
                {
                    name: 'responsibility',
                    index: 'responsibility',
                    width: 15,
                },
                {
                    name: 'phone',
                    index: 'phone',
                    width: 20,
                },
                {
                    name: 'examine_status',
                    index: 'examine_status',
                    width: 13
                },
                {
                    name: 'status',
                    index: 'status',
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
                    width: '40',
                    formatter: function(cellvalue,options,rowObject) {

                        var org_id =rowObject.org_id;
                        var status=rowObject.status;
                        var examine_type=rowObject.examine_type;
                        var examine_status=rowObject.examine_status;
                            var v_result = "";
                            v_result += "<button  style='width: 65px;' class='btn btn-info' type='button'  onClick='toAddOrEdit(\"" + org_id + "\");'><i class='glyphicon glyphicon-pencil'></i> 编辑</button>";
                        v_result += " &nbsp; <button  style='width: 65px;' class='btn btn-info' type='button'  onClick='deleteOrg(\"" + org_id + "\");'><i class='glyphicon glyphicon-pencil'></i> 删除</button>";
                        if(examine_status==0){
                            if(examine_type==0){
                                v_result += " &nbsp; <button  style='width: 65px;' class='btn btn-info' type='button'  onClick='toExamine(\"" + org_id + "\",\"0\");'><i class='glyphicon glyphicon-pencil'></i> 审核</button>";
                            }else {
                                v_result += " &nbsp; <button  style='width: 65px;' class='btn btn-info' type='button'  onClick='toExamine(\"" + org_id + "\",\"1\");'><i class='glyphicon glyphicon-pencil'></i> 审核</button>";
                            }
                        }else if (examine_status==1) {
                            if (status==0){
                                v_result += "&nbsp; <button  style='width: 65px;' class='btn btn-info' type='button'  onClick='updateStatus(\"" + org_id + "\",\"1\");'><i class='glyphicon glyphicon-pencil'></i> 启用</button>";
                            }else {
                                v_result += "&nbsp; <button  style='width: 65px;' class='btn btn-info' type='button'  onClick='updateStatus(\"" + org_id + "\",\"0\");'><i class='glyphicon glyphicon-pencil'></i> 禁用</button>";
                            }
                        }

                        return v_result;
                    }
                }
            ],
            sortable:true,
            sortname: 'create_time',
            sortorder: 'desc',
            pager: "#pager_list",
            caption: "中奖名单管理",
        });

    });

    //绑定查询事件
    $("#searchBtn").click(function(){
        var org_name = $("#org_name").val();
        $("#table_list").jqGrid('setGridParam',{
            url:"../eduorg/list",
            postData:{org_name:org_name}, //发送数据
            page:1
        }).trigger("reloadGrid"); //重新载入
    });

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

    function toAddOrEdit(org_id) {
        //if(activit_id)
        //$("#myiframe").load("sysuser/editUser?user_id="+user_id);
        window.location.href="../eduorg/toAddOrEdit?org_id="+org_id;
    }
    
    function toExamine(org_id,type) {
        var url="";
        if(type==0){
            url="../eduorg/toExamine?org_id="+org_id
        }else {
            url="../eduorg/toUpdateExamine?org_id="+org_id
        }
        layer.open({
            type: 2,
            title: '审核',
            shadeClose: true,
            shade: 0.8,
            area: ['90%', '90%'],
            content: url //iframe的url
        });
        
    }
    
    function updateStatus(org_id,status) {
        var title="启用";
        if(status==0){
            title="禁用";
        }

        layer.confirm("是否确认"+title+"该机构",function () {
            $.ajax({
                url:"../eduorg/updateStatus",
                type:"post",
                data:{org_id:org_id,status:status},
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