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
              新增菜单
          </h4>
    </div>
    <div class="box-body">
        <form id="myform">

            <input type="hidden" name="id" value="${sys_role.id!'' }"/>

            <div class="box-line">
                <span class="title mid-inline-block">角色名称：</span>
                <input class="long"  maxlength="20" aria-required="true" required=""  name="name"   value="${sys_role.name!''}" placeholder="请输入角色名"/>
            </div>

        <#--    <div class="box-line">
                <span class="title top-inline-block">活动介绍：</span>
                <script id="myEditor" type="text/javascript" style="width:80%;height: 200px;" ></script>
                </div>-->

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
<!-- 配置文件 -->
<#--<script type="text/javascript" src="../activity/vendor/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 &ndash;&gt;
<script type="text/javascript" src="../activity/vendor/ueditor/ueditor.all.js"></script>

<script type="text/javascript" src="../activity/vendor/ueditor/lang/zh-cn/zh-cn.js"></script>-->
<script>
/*
    var _ueditor;

    $(document).ready(function () {
        _ueditor = UE.getEditor('myEditor',item);
    });
    /!* <![CDATA[*!/
    var item = {
        toolbars: [
            [
                'fullscreen', 'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                'directionalityltr', 'directionalityrtl', 'indent', '|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                'simpleupload', 'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
                'print', 'preview', 'searchreplace', 'drafts', 'help'
            ]
        ],
        autoHeightEnabled: false, //是否自动长高，默认true
        autoFloatEnabled: true, //是否保持toolbar的位置不动，默认true
        wordCount: true, //是否开启字数统计 默认true
        maximumWords: 100000, //允许的最大字符数 默认值：10000
        wordOverFlowMsg: "<span style='color:red;'>超出范围了！！！！！！！！</span>", //超出字数限制提示
        elementPathEnabled: false, //是否启用元素路径
        padding: 0,
        initialFrameWidth: "100%",//指定编辑器宽度

        fullscreen: false,//是否是全屏
        enableAutoSave: true,//自动保存
        saveInterval: 500, // 将其设置大点，模拟去掉自动保存功能
        isShow:true,
        allowDivTransToP: false,
        initialContent:'',//初始化UEditor内容
        textarea:"activit_intro"//后台接受UEditor的数据的参数名称

    };*/
    /*]]>*/
    $("#myform").submit(function () {

        $.ajax({
            url:"../sysrole/saveRole",
            type:"post",
            data:$("#myform").serialize(),
            dataType:"json",
            success:function(data){
                console.log(data)
                if(data.code==200){
                    layer.alert("保存成功",function (index) {
                        //layer.load();
                        layer.close(index)
                        window.location.href="../sysrole/main";
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