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
                    查看投诉建议信息
                </h4>
            </div>
            <div class="box-body">

                    <input type="hidden" id="suggestion_id" name="suggestion_id" value="${suggestion.suggestion_id!''}"/>

                    <div class="box-line">
                        <span class="title mid-inline-block">标题：</span>
                        ${suggestion.title!'' }
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">理由：</span>
                        ${suggestion.reason!'' }
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">状态：</span>
                        <#if suggestion.status==0>
                            未处理
                        <#elseif suggestion.status==1>
                            已处理
                        </#if>
                    </div>

                    <div class="box-line">
                        <span class="title mid-inline-block">回复：</span>
                        <#if suggestion.status==0>
                            <textarea id="answer" style="width: 40%;height: 100px;resize: none;" name="answer"></textarea>
                        <#elseif suggestion.status==1>
                            ${suggestion.answer!'' }
                        </#if>

                    </div>



                            <div class="box-line">
                                    <span class="title mid-inline-block"></span>
                                <#if suggestion.status==0>
                                    <button onclick="updateSuggestion()" class="btn btn-red">
                                        <span>提交</span>
                                    </button>
                                <#elseif suggestion.status==1>
                                   <button onclick="closeWin()" class="btn btn-red">
                                       <span>关闭</span>
                                   </button>
                                </#if>

                                    </div>
                                    </div>
                                    </div>
                                    </div>
                                    </section>

<script>

        function updateSuggestion(){
            var answer=$("#answer").val();
            var suggestion_id=$("#suggestion_id").val();
            $.ajax({
                url:"../suggestion/updateSuggestion",
                type:"post",
                data:{suggestion_id:suggestion_id,answer:answer},
                dataType:"json",
                success:function(data){
                    if(data.result=='0'){
                        layer.alert("保存成功",function (index) {
                            //layer.close(index);
                            window.parent.location.reload();
                        });

                    }else {
                        return false;
                    }
                },
                error : function() {
                    alert('保存异常');
                }

            });
        }
        function closeWin() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

                            </script>
</html>