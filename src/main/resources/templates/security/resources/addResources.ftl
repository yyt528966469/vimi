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
      <h4 class="box-title">
              新增菜单
          </h4>
    </div>
    <div class="box-body">
        <form id="myform">

            <input type="hidden" name="res_id" value="${sysResources.res_id!''}"/>
      <div class="box-line">
        <span class="title mid-inline-block">菜单类型：</span>
        <select id="res_type" name="res_type"  onclick="changeType()">
            <option value="0" <#if sysResources.res_type??><#if sysResources.res_type==0 >selected="selected"</#if></#if>">目录</option>
            <option value="1" <#if sysResources.res_type??><#if sysResources.res_type==1 >selected="selected"</#if></#if>" >菜单</option>
        </select>
      </div>
            <div class="box-line">
                <span class="title mid-inline-block">菜单名称：</span>
                <input class="long"    name="name"  value="${sysResources.name!''}" placeholder=""/>
            </div>
            <div class="box-line">
                <span class="title mid-inline-block">父菜单：</span>
                <select   id="parent_id"  name="parent_id">
                    <option value="">==请选择==</option>
                    <#if sysResources.parent_id??>
                        <#list resList as res>
                            <option value="${res.res_id}" <#if res.res_id==sysResources.parent_id>selected="selected"</#if> >${res.name}</option>
                        </#list>
                        <#else >
                        <#list resList as res>
                            <option value="${res.res_id}">${res.name}</option>
                        </#list>
                    </#if>

                </select>
            </div>
            <div class="box-line">
                <span class="title mid-inline-block">菜单事件：</span>
                <input class="long"    id="res_url" name="res_url"   value="${sysResources.res_url!''}" placeholder="请输入登录名"/>
            </div>
            <div class="box-line">
                <span class="title mid-inline-block">菜单图标：</span>
                <input class="long"   name="img"   value="${sysResources.img!''}" placeholder="请输入登录名" />
            </div>


      <div class="box-line">
        <span class="title mid-inline-block"></span>
        <button class="btn btn-red">
          <span>保存</span>
        </button>
      </div>
        </form>
    </div>
  </div>
</div>
</section>
<script>
    changeType();
    function changeType(){
        var val=$("#res_type").val();
        var r_obj=$("#res_url").parent();
        var p_obj=$("#parent_id").parent();
        if(val==1){
            r_obj.show();

            p_obj.show();
        }else {
            p_obj.hide();
            p_obj.removeAttr("maxlength");
            r_obj.hide();
        }
    }
    $("#myform").submit(function () {
        var text=$("#nameInfo").text();
        if(text){
            return false;
        }
        $.ajax({
            url:"../resources/saveResInfo",
            type:"post",
            data:$("#myform").serialize(),
            dataType:"json",
            success:function(data){
                console.log(data)
                if(data.code==200){
                    layer.alert("保存成功",function (index) {
                        $("#myiframe").load("resources/main");
                        layer.close(index);
                        //window.location.href="resources/main";
                    });

                }else {
                    return false;
                }
            },
            error : function() {
                alert('保存异常');
            }

        });
        return false;
    });

</script>
</html>