<html >
<head >
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
<script type="text/javascript" src="../activity/vendor/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="../activity/vendor/ueditor/ueditor.all.js"></script>

<script type="text/javascript" src="../activity/vendor/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<section class="content" height="100%" >
<div class="row">
  <div class="col-xs-12">
    <div class="box-header">
      <h4 class="box-title">
              编辑广告
          </h4>
    </div>
    <div class="box-body">
        <form id="myform">
            <input type="hidden" name="adver_id" value="${t_adver.adver_id!''}"/>

      <div class="box-line">
        <span class="title mid-inline-block">广告标题：</span>
        <input class="long"  maxlength="20" aria-required="true" required=""  name="adver_name"  value="${t_adver.adver_name!'' }" placeholder="请输入标题"/><span style="color: red" id="nameInfo"></span>
      </div>

            <div class="box-line" id="div_adver_url" >
                <span class="title mid-inline-block">广告链接：</span>
                <input class="long"  maxlength="100" aria-required="true" required=""  name="adver_url"  value="${t_adver.adver_url!'' }" placeholder="请输入链接"/>
               <#-- <br/><span  style="color: red;padding-left:86px;">输入http://或https://格式链接</span>-->
            </div>

            <div class="box-line" id="div_adver_img" >
                <span class="title mid-inline-block">广告图片：</span>
                <label for="cover" style="width: 25%;">
                    <input id="image_file" name="image_file" dir=rtl class="file-loading" type="file" >
                    <input type="hidden" id="adver_img" name="adver_img" value="${t_adver.adver_img!''}">
                    <span class="upload-prompt" style="color: red;">支持jpg、png格式，大小不超过2.0M</span>
                </label>

            </div>

            <div class="box-line">
                <span class="title mid-inline-block">广告顺序：</span>
                <input class="long"  maxlength="20" aria-required="true" required=""  name="sort"  value="${t_adver.sort!'' }" placeholder="请输入顺序号"/>
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

    $("#myform").submit(function () {

        $.ajax({
            url:"../tadver/saveAdver",
            type:"post",
            data:$("#myform").serialize(),
            dataType:"json",
            success:function(data){
                if(data.result=='0'){
                    layer.alert("保存成功",function (index) {
                        layer.close(index);
                        window.location.href="../tadver/main";
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

    $(document).ready(function () {
        //上传封面图
        var imageInputId = "image_file";
        var uploadImagePath = "../tadver/uploadImages";
        initImageInput(imageInputId, uploadImagePath);

    });
    var v_img_url = '${t_adver.adver_img!""}';

    function initImageInput(inputId,uploadUrl) {
        var control = $("#" + inputId);
        //alert(control);
        control.fileinput({
            language: "zh", //设置语言
            theme: "fa",
            uploadUrl: uploadUrl,  //上传地址
            uploadAsync: true, //是否异步上传   默认异步上传
            uploadExtraData:function (previewId, index) {//上传文件时额外传递的参数设置 动态获取
                var filePathString = $("#filePathString").val();
                if(filePathString==null || filePathString==undefined) filePathString="";
                var obj = {"filePathString": filePathString};
                return obj;
            },
            showUpload: false, //是否显示上传按钮
            showRemove: true,  //是否显示移除按钮
            showPreview :true, //是否显示预览
            showCaption: true,//是否显示文件标题。默认为true。
            //browseClass:"btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            showClose: false, //是否在预览中显示关闭图标
            showUploadedThumbs: false,// 是否在预览窗口中持续显示上传的文件缩略图（用于ajax上传）
            //showBrowse: true, //是否显示文件浏览按钮

            //enctype: 'multipart/form-data',
            rtl: false,//预览显示从右往左  选择文件按钮 从左到右

            layoutTemplates:{
                actionDelete:"",//去除缩略图上面的删除按钮
                actionUpload:"",//去除缩略图上面的上传按钮
                //actionZoom:"",//去除缩略图上面的查看详情按钮
            },
            autoReplace: true, //是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉。
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //minFileCount: 0, //表示同时最小上传的文件个数
            maxFileCount:1, //表示允许同时上传的最大文件个数
            validateInitialCount:true,

            allowedPreviewTypes: ["image"], //预览文件类型
            allowedFileTypes: ["image"],
            allowedFileExtensions:  ["jpg","png"],
            maxFileSize : 2048, //单位为kb，如果为0表示不限制文件大小

            overwriteInitial: true,
            initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
            initialPreviewFileType: "image",
            initialPreview: v_img_url

        }).on("filebatchselected", function (event, files) {
            $(this).fileinput("upload");
        }).on("filepreupload", function(event, data, previewId, index) {     //上传中
            var form = data.form, files = data.files, extra = data.extra,
                    response = data.response, reader = data.reader;
            console.log("图片正在上传");
        }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
            console.log('文件上传成功！'+data);
            //获取后台返回的json对象
            var response_result = data.response;
            //获取json对象中的Object值
            var v_fileSrc = response_result.success;

            $("#adver_img").val(v_fileSrc);
        }).on("fileerror", function(event, data, msg) {  //一个文件上传失败
            console.log("图片上传失败！"+data.id);
            layer.alert(msg,{icon: 2});
        }).on("filecleared", function(event) {  //点击移除按钮 清除预览中的文件后触发的事件
            console.log("删除预览图片！"+event);
            $("#adver_img").val("");//清除预览中的文件后同时将图片地址置为空
        });
    }
</script>
</html>