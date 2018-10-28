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
              新增管理员
          </h4>
    </div>
    <div class="box-body">
        <form id="myform">

            <input type="hidden" name="org_id" value="${edu_org.org_id!''}"/>
            <div class="box-line">
                <span class="title mid-inline-block">机构类型：</span>
                <select name="org_type">
                    <option>==请选择==</option>
                    <#if edu_org.org_type?? >
                        <#list typeList as type>
                        <option value="${type.type_id}" <#if edu_org.org_type==type.type_id >selected="selected"</#if> >${type.type_name}</option>
                        </#list>
                        <#else >
                            <#list typeList as type>
                        <option value="${type.type_id}"  >${type.type_name}</option>
                            </#list>
                    </#if>

                </select>
            </div>
      <div class="box-line">
        <span class="title mid-inline-block">机构名称：</span>
        <input class="long"  maxlength="20" aria-required="true" required=""  name="org_name"  value="${edu_org.org_name!'' }" placeholder="请输入类型名"/><span style="color: red" id="nameInfo"></span>
      </div>
      <div class="box-line">
        <span class="title mid-inline-block">机构简介：</span>
        <input class="long" maxlength="25" aria-required="true" required="" name="org_intro" value="${edu_org.org_intro!'' }" placeholder="请输入顺序号"/>
      </div>
            <div class="box-line">
                <span class="title mid-inline-block">机构负责人：</span>
                <input class="long" maxlength="25" aria-required="true" required="" name="responsibility" value="${edu_org.responsibility!'' }" placeholder="请输入负责人姓名"/>
            </div>
            <div class="box-line">
                <span class="title mid-inline-block">负责人电话：</span>
                <input class="long" maxlength="25" aria-required="true" required="" name="phone" value="${edu_org.phone!'' }" placeholder="请输负责人电话"/>
            </div>

            <div class="box-line">
                <span class="title mid-inline-block">机构图片：</span>
                <label for="cover" style="width: 25%;">
                    <input id="image_file" name="image_file" dir=rtl class="file-loading" type="file" >
                    <input type="hidden" id="index_img" name="index_img" value="${edu_org.index_img!''}">
                    <span class="upload-prompt" style="color: red">支持jpg、png格式，大小不超过2.0M</span>
                </label>

            </div>
            <div class="box-line">
                <span class="title mid-inline-block">轮播图：</span>
                <label for="cover" style="width: 80%" id="ph_head">
                    <input id="photo_head" name="photo_head" dir=rtl multiple  class="file-loading" type="file" >
                    <span class="upload-prompt" style="color: red">支持jpg、png格式，大小不超过2.0M</span>
                </label>
            </div>
            <div class="box-line">
                <span class="title mid-inline-block">图文详情：</span>
                <label for="cover" style="width: 80%" id="ph_text">
                    <input id="photo_text" name="photo_text" dir=rtl multiple  class="file-loading" type="file" >
                    <span class="upload-prompt" style="color: red">支持jpg、png格式，大小不超过2.0M</span>

                </label>
            </div>
            <div class="box-line">
                <span class="title mid-inline-block">机构坐标：</span>
                <input class="long" maxlength="25" aria-required="true" required="" id="longitude" name="longitude" value="${edu_org.longitude!'' }" placeholder="经度"/>
                <input class="long" maxlength="25" aria-required="true" required="" id="latitude" name="latitude" value="${edu_org.latitude!'' }" placeholder="纬度"/>
                <input type="button" onclick="toMap()" value="点击">
            </div>
            <div class="box-line">
                <span class="title mid-inline-block">机构地址：</span>
                <input class="long" maxlength="25" aria-required="true" required="" id="province" name="province" value="${edu_org.province!'' }" placeholder="省份"/>
                <input class="long" maxlength="25" aria-required="true" required="" id="city" name="city" value="${edu_org.city!'' }" placeholder="城市"/>
                <input class="long" maxlength="25" aria-required="true" required="" id="county" name="county" value="${edu_org.county!'' }" placeholder="区(县)"/>
                <input class="long" maxlength="25" aria-required="true" required="" id="details_addr" name="details_addr" value="${edu_org.details_addr!'' }" placeholder="详细地址"/>
            </div>
            <div class="box-line">
                <span class="title mid-inline-block">备注：</span>
                <input class="long" maxlength="25" aria-required="true" required="" name="remarks" value="${edu_org.remarks!'' }" placeholder="请输备注"/>
            </div>

            <div class="box-line">
                <span class="title mid-inline-block">机构详情：</span>
                <label for="cover" style="width: 90%">
                <script id="myEditor" type="text/javascript" style="width:80%;height: 200px;" ></script>
                    </label>
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
            var _ueditor;
            $(document).ready(function () {
                _ueditor = UE.getEditor('myEditor',item);
            });
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
                initialContent:'${edu_org.details!''}',//初始化UEditor内容
                textarea:"details"//后台接受UEditor的数据的参数名称

            };
    $("#myform").submit(function () {

        $("#ph_head .file-preview-success").find("img").each(function(index){
            var obj=$(this);
            var title=obj.attr("title");
            $(".p_head").each(function(){
                var attr_name =$(this).attr("attr_name");
                if(title==attr_name){
                    var v=$(this).val();
                    $(this).val(v+','+index);
                }

            });
        });

        $("#ph_text .file-preview-success").find("img").each(function(index){
            var obj=$(this);
            var title=obj.attr("title");
            $(".p_text").each(function(){
                var attr_name =$(this).attr("attr_name");
                if(title==attr_name){
                    var v=$(this).val();
                    $(this).val(v+','+index);
                }

            });
        });

        $.ajax({
            url:"../eduorg/saveEduOrg",
            type:"post",
            data:$("#myform").serialize(),
            dataType:"json",
            success:function(data){
                if(data.result=='0'){
                    layer.alert("保存成功",function (index) {
                        layer.close(index);
                        window.location.href="../eduorg/main";
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
        var uploadImagePath = "../eduorg/uploadImages";
        initImageInput(imageInputId, uploadImagePath);




    });
    var v_img_url = '${edu_org.index_img!""}';

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

            $("#index_img").val(v_fileSrc);
        }).on("fileerror", function(event, data, msg) {  //一个文件上传失败
            console.log("图片上传失败！"+data.id);
            layer.alert(msg,{icon: 2});
        }).on("filecleared", function(event) {  //点击移除按钮 清除预览中的文件后触发的事件
            console.log("删除预览图片！"+event);
            $("#index_img").val("");//清除预览中的文件后同时将图片地址置为空
        });
    }

function toMap() {
    var lng= $("#longitude").val();
    var lat=$("#latitude").val();
    layer.open({
        type: 2,
        title: '点击获取经纬度',
        shadeClose: true,
        shade: 0.8,
        area: ['80%', '80%'],
        content: "../eduorg/toMap?lng="+lng+"&lat="+lat //iframe的url
    });
}

function getlngAndlat(lng,lat,province,city,district,street,streetNumber,obj) {
    $("#longitude").val(lng);
    $("#latitude").val(lat);
    console.log(obj);
    $("#province").val(province);
    $("#city").val(city);
    $("#county").val(district);
    $("#details_addr").val(street+streetNumber);
   //alert(province+"--"+city);
}

            var head_arry=[];
            var text_arry=[];
                     <#if edu_org.photoList??>
                         <#list  edu_org.photoList as photo>

                            var url='<img style="width:auto;height:auto;max-width:100%;max-height:100%;" class="file-preview-image" src="${photo.img_url}">';
                             var img_type='${photo.img_type}';
                             if(img_type==0){
                                 head_arry.push(url);
                             }else {
                                 text_arry.push(url);
                             }
                         </#list>
                     </#if>
            $("#photo_head").fileinput({
                language : 'zh',
                initialPreview:head_arry,
                uploadUrl : "../eduorg/uploadHead",
                showUpload: false, //是否显示上传按钮
                showRemove: true,  //是否显示移除按钮
                //showPreview :true, //是否显示预览
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
                maxFileCount:5, //表示允许同时上传的最大文件个数
                validateInitialCount:true,
                allowedPreviewTypes: ["image"], //预览文件类型
                allowedFileTypes: ["image"],
                allowedFileExtensions:  ["jpg", "png"],
                maxFileSize : 2048
            }).on('filebatchselected', function (event, files) {
                $(this).fileinput("upload");
            }).on("filepreupload", function(event, data, previewId, index) {     //上传中
                var form = data.form, files = data.files, extra = data.extra,
                        response = data.response, reader = data.reader;
                console.log('文件正在上传');
            }).on("fileuploaded", function(e, data) {
                var res = data.response;
                console.log(e);
                //alert(res.success);
                $("#ph_head").append('<input type="hidden" attr_name="'+res.filename+'" name="head_name" class="p_head" value="'+res.success+'" >');
                //$("#logo").attr("value", res.success);
            });


            $("#photo_text").fileinput({
                language : 'zh',
                initialPreview:text_arry,
                uploadUrl : "../eduorg/uploadText",
                showUpload: false, //是否显示上传按钮
                showRemove: true,  //是否显示移除按钮
                //showPreview :true, //是否显示预览
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
                maxFileCount:5, //表示允许同时上传的最大文件个数
                validateInitialCount:true,
                allowedPreviewTypes: ["image"], //预览文件类型
                allowedFileTypes: ["image"],
                allowedFileExtensions:  ["jpg", "png"],
                maxFileSize : 2048
            }).on('filebatchselected', function (event, files) {
                $(this).fileinput("upload");
            }).on("filepreupload", function(event, data, previewId, index) {     //上传中
                var form = data.form, files = data.files, extra = data.extra,
                        response = data.response, reader = data.reader;
                console.log('文件正在上传');
            }).on("fileuploaded", function(e, data) {
                var res = data.response;
                console.log(data);
                //alert(res.success);
                $("#ph_text").append('<input type="hidden" name="text_name" attr_name="'+res.filename+'" class="p_text" value="'+res.success+'" >');
            });
</script>
</html>