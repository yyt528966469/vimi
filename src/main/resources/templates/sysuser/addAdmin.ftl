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

    <script src="../activity/vendor/bootstrap-fileinput/js/fileinput.min.js"></script>
    <script src="../activity/vendor/bootstrap-fileinput/js/locales/zh.js"></script>
</head>
<section class="content" height="100%" >
<div class="row">
  <div class="col-xs-12">
    <div class="box-header">
      <h4 class="box-title">
              新增管理员
          </h4>
    </div>
    <div class="box-body">
        <form id="myform">
            <input type="hidden" name="user_id" value="${user.user_id!''}"/>
      <div class="box-line">
        <span class="title mid-inline-block">登录名：</span>
        <input class="long"  maxlength="20" aria-required="true" required=""  name="username" onblur="checkUsername(this)" value="${user.username!'' }" placeholder="请输入登录名"/><span style="color: red" id="nameInfo"></span>
      </div>
      <div class="box-line">
        <span class="title mid-inline-block">密码：</span>
        <input class="long" maxlength="25" aria-required="true" required="" name="password" value="" placeholder="请输入密码"/>
      </div>
            <div class="box-line">
                <span class="title mid-inline-block">用户角色：</span>
               <select name="roles[0].id">
                   <option>==请选择==</option>
                   <#if user.roles??>
                        <#list roleList as role>
                            <option value="${role.id}" <#if role.id= user.roles[0].id >selected="selected"</#if> >${role.name}</option>
                        </#list>
                        <#else >
                            <#list roleList as role>
                            <option value="${role.id}">${role.name}</option>
                            </#list>

                   </#if>

               </select>
            </div>
      <div class="box-line">
        <span class="title mid-inline-block"></span>
        <button class="btn btn-red">
          <span>新增管理员</span>
        </button>
      </div>
        </form>
    </div>
  </div>
</div>
</section>

<script>
    $("#myform").submit(function () {
        var text=$("#nameInfo").text();
        if(text){
            return false;
        }
        $.ajax({
            url:"../sysuser/saveUserInfo",
            type:"post",
            data:$("#myform").serialize(),
            dataType:"json",
            success:function(data){
                if(data.result=='0'){
                    layer.alert("保存成功",function (index) {
                        layer.close(index);
                        //$("#myiframe").load("../sysuser/main");
                        window.location.href="../sysuser/main";
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


    function checkUsername(obj) {
        $("#nameInfo").text("");
        var username=$.trim(obj.value);
        if(!username){//为空不请求后台
            return;
        }
        var user_id=$("#user_id").val();
        var o_username="${user.username !''}";
        //alert(o_username);
        if(username==o_username){//编辑时
            return;
        }
        /*if(user_id){

        }*/
        $.ajax({
            url:"../sysuser/checkUsername",
            type:"post",
            data:{username:username},
            dataType:"json",
            success:function(data){
                if(data.result=='1'){//该用户已存在
                    $("#nameInfo").text("该用户已存在");
                    //window.location.href="../sysuser/list";
                }
            }

        })
    }
</script>
</html>