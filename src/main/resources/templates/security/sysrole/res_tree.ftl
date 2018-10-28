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
    <link rel="stylesheet" href="../activity/vendor/zTreeStyle.css"/>
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

    <script src="../activity/vendor/jquery.ztree.core.js"></script>
    <script src="../activity/vendor/jquery.ztree.excheck.js"></script>

</head>
<section class="content" height="100%" >
<div class="row">
  <div class="col-xs-12">
    <div class="box-header">
      <h4 class="box-title">权限管理</h4>

    </div>
      <div class="zTreeDemoBackground left">
          <ul id="treeDemo" class="ztree"></ul>
      </div>
        <div style="text-align: center;margin-top: 10px;">
      <button id="" onclick="addRoleRes()"  class="btn btn-red">
          <img src="../activity/img/add.png"/>
          <span>保存</span>
      </button>
        </div>
      <input type="hidden" id="role_id" value="${role_id}">
    <!--<div class="box-body">
        <div class="ibox" style="">
            <div class="ibox-content">

               &lt;!&ndash; <div class="jqGrid_wrapper" class="sk-spinner sk-spinner-fading-circle">
                    <table id="table_list"></table>
                    <div id="pager_list"></div>
                </div>&ndash;&gt;
            </div>
        </div>

    </div>-->


  </div>
</div>
</section>



<script>
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback:{
            beforeCheck:true,
            onCheck:onCheck
        }

    };
    var zNodes =null;
    var ids="";
    /*var zNodes =[
        { id:1, pId:0, name:"随意勾选 1", open:true},
        { id:11, pId:1, name:"随意勾选 1-1", open:true},
        { id:111, pId:11, name:"随意勾选 1-1-1"},
        { id:112, pId:11, name:"随意勾选 1-1-2"},
        { id:12, pId:1, name:"随意勾选 1-2", open:true},
        { id:121, pId:12, name:"随意勾选 1-2-1"},
        { id:122, pId:12, name:"随意勾选 1-2-2"},
        { id:2, pId:0, name:"随意勾选 2",  open:true},
        { id:21, pId:2, name:"随意勾选 2-1"},
        { id:22, pId:2, name:"随意勾选 2-2", open:true},
        { id:221, pId:22, name:"随意勾选 2-2-1"},
        { id:222, pId:22, name:"随意勾选 2-2-2"},
        { id:23, pId:2, name:"随意勾选 2-3"}
    ];*/

    var code;

    function setCheck() {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            py = "p",
            sy = "s",
            pn = "",
            sn = "s",
            type = { "Y":py + sy, "N":pn + sn};
        zTree.setting.check.chkboxType = type;
        showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
    }
    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>"+str+"</li>");
    }

    $(document).ready(function(){
        var role_id= "${role_id!''}";
        $.ajax({
            url:"../resources/loadDataJsTree",
            type:"post",
            data:{role_id:role_id},
            dataType:"json",
            async:false,
            success:function(data){
                //alert("["+data[0]+"]");
                //zNodes=JSON.stringify(data);
                //console.log(data);
                zNodes=data;
            }
        });
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        setCheck();
        /*$("#py").bind("change", setCheck);
        $("#sy").bind("change", setCheck);
        $("#pn").bind("change", setCheck);
        $("#sn").bind("change", setCheck);*/


    });

    function onCheck(e,treeId,treeNode) {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
           var  v = "";
        /* <![CDATA[*/
        ids="";
        for (var i = 0; i < nodes.length; i++) {
            v = nodes[i].name + ",";
            //ids=ids && ",";
            ids=ids+nodes[i].id+",";
            console.log("节点id:" + nodes[i].id + "节点名称" + v); //获取选中节点的值
        }
        if(ids){
            ids=ids.substring(0,ids.length-1);
        }
        //alert(ids);
        /*]]>*/
    }
    
    function addRoleRes() {
        onCheck(null,null,null);
        var role_id= "${role_id!''}";
        $.ajax({
            url: "../sysrole/saveRoleRes",
            type: "post",
            data: {ids:ids,role_id:role_id},
            dataType: "json",
            success: function (data) {
                if(data.result.code==200){
                    layer.alert("保存成功",function (index) {
                        layer.close(index);
                        //$("#myiframe").load("sysrole/main");
                        window.parent.location.reload();
                    })
                }

            }
        });

    }
</script>
</html>