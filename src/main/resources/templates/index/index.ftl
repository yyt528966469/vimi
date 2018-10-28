<html >
<head >
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/x-icon" href="activity/img/log.png">
    <title>活动后台管理系统</title>
    <link rel="stylesheet" href="activity/vendor/bootstrap.min.css">
    <link rel="stylesheet" href="activity/vendor/ui.jqgrid.css">
    <link rel="stylesheet" href="activity/vendor/bootstrap-datetimepicker.min.css">

    <script src="activity/vendor/jquery-1.10.2.min.js"></script>
    <script src="js/echarts.min.js"></script>
    <script src="activity/vendor/jquery.jqGrid.min.js"></script>
    <script src="activity/vendor/grid.locale-cn.js"></script>
    <script src="activity/vendor/layer/layer.min.js"></script>
    <script src="activity/vendor/bootstrap.min.js"></script>
    <style>
        a{
            color: black;
            text-decoration: none;

        }
        .list_top{
            width: 100%;
            height: 300px;
            display: inline-block;
           /* border: 1px solid black;*/
            margin-bottom:20px ;
            background-color:white;
        }
        .list_bottom{
            width: 100%;
            height: 500px;

            background-color:white;

        }

        #nav_tabs>li{
            border: none;
            border-right: 2px solid #ccc;
            margin-bottom: 0px;
            width:124px;
            text-align: center;

        }
        #nav_tabs>li>a{
/*border: none;*/
/*color: black;*/

        }
        .nav-tabs>li.active>a, .nav-tabs>li.active>a:focus,
        .nav-tabs>li.active>a:hover{
            color: red;
            /*border: 1px solid red;*/
            border: none;
            border-bottom: 2px solid red;
            background-color: white;

        }


     .ui-jqgrid-sortable{
    font-family: "微软雅黑";
    font-weight: lighter;
    font-size: 14px;
}
        #jqgh_table_list_rn{
            font-family: "微软雅黑";
            font-weight: lighter;
            font-size: 14px;
        }
        #main div{
            width: 100% !important;

        }
.yonghu{

    width: 50%;
    height: 300px;
    display: inline-block;
}
        .aaaa{
            display: initial
            right: 262px;
            top: 88px;
            width: 195px;
        }

        .bbbb{
            right: 50px;
        }
        .ui-jqgrid{
            border: none;
        }
        .ui-jqgrid-labels{
            background-color: #f8f8f8;
        }
        /*.table-bordered>tbody>tr>td, .table-bordered>tbody>tr>th, .table-bordered>tfoot>tr>td, .table-bordered>tfoot>tr>th, .table-bordered>thead>tr>td, .table-bordered>thead>tr>th{*/
            /*border: none;*/
        /*}*/
        th{
            text-align: center;
        }
        td{
            text-align: center;
        }
        .ui-jqgrid .table-bordered, .ui-jqgrid .table-bordered td, .ui-jqgrid .table-bordered th.ui-th-ltr{
            margin-bottom: 8px;
        }
        #btn{
            background-color: rgb(251, 78, 80);
            border: none;
        }
        #btn:hover{
            background-color:rgb(251, 78, 80);
        }
        jqgrow ui-row-ltr:hover{
background-color: white;
        }



    </style>
</head>
<body style="width: 98%">
    <div class="row">

        <div class="col-xs-12" >

            <div class="box-body">
                <div class="list_top">
                    <div id='main' style='width:50%;height:300px;display: inline-block;float: left'></div>
                    <div class="yonghu" >
                        <div class="aaaa" style="height: 80px;width: 50%;display: inline-block;float: left;margin-top: 100px;">
                        <img src="images/persons.png" style="display: inline-block;float: left;margin-left: 20px;"/>
                            <div style="display: inline-block;float: left;" >
                                <div style="font-size: 30px;margin-left: 25px;" >${user_num!'0'}</div>
                                <div style="font-size: 20px;margin-left: 25px;">用户数量</div>
                            </div>
                        </div>
                        <div class="aaaa bbbb" style="height: 80px;width: 50%; display: inline-block;margin-top: 100px;">
                        <img src="images/person_b.png" style="display: inline-block;float: left;margin-left: 20px;"/>
                            <div style="display: inline-block;float: left;">
                                <div style="font-size: 30px;margin-left: 25px;" >${today_order!'0'}</div>
                                <div style="font-size: 20px;margin-left: 25px;">今日订单数</div>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="list_bottom">

                <div  style="width:93%;margin-left: 30px;">



                    <#--<embed src="images/web/dd.mp3">-->
                   <#-- <audio  autoplay="autoplay" id="auto" src="images/web/dd.mp3"></audio>-->
                    <ul id="nav_tabs" class="nav nav-tabs">
                        <li class="active" ><a onclick="searchStatus(1)" href="#">待接单</a></li>
                        <li><a href="#" onclick="searchStatus(2)">待发货</a></li>
                        <li><a href="#" onclick="searchStatus(7)">未完成</a></li>
                    </ul>
                    <div style="margin-top: 10px;"  class=" jqGrid_wrapper sk-spinner sk-spinner-fading-circle">
                        <table id="table_list" ></table>
                        <div id="pager_list"></div>
                    </div>
                </div>


                </div>
        </div>

        </div>
    </div>


<script>
    var v_status=1;
    $("#nav_tabs li").click(function () {
        $("#nav_tabs li").removeClass("active");
        $(this).addClass("active");

    });



    $(document).ready(function () {
        $.jgrid.defaults.styleUI = 'Bootstrap';
        // Examle data for jqGrid
        var searchString='';

        //绑定表格数据
        $("#table_list").jqGrid({
            postData:{status: 1},
            url:"order/list",
            datatype: "json",
            height: "calc(280px)",
            autowidth: true,
            shrinkToFit: true,
            multiselect: false,
            multiboxonly:true,
            gridComplete : gridComplete,
            rownumbers: true,
            loadComplete:function(){
                $("#table_list").jqGrid('setLabel','rn', 'ID', {'text-align':'left'});
            },
            beforeSelectRow: beforeSelectRow,
            rowNum:5,
            rowList: [5, 10, 15],
            root:"rows",
            colNames: ['ID','状态','订单编号','类型','金额','状态','下单时间','操作'],
            colModel: [
                {
                    name: 'order_id',
                    index: 'order_id',
                    hidden:true,
                    key:"ture"
                },
                {
                    name: 'status',
                    index: 'status',
                    hidden:true
                },
                {
                    name: 'order_id',
                    index: 'order_id',
                    width: 25
                },
                {
                    name: 'eat_type',
                    index: 'eat_type',
                    width: 15,
                    formatter:'select',
                    formatoptions:{value:{ 0:'外卖',1:'到店',2:'店内'}}
                },
                {
                    name: 'real_price',
                    index: 'real_price',
                    width: 12,
                    // align : "right"
                },
                {
                    name: 'status_name',
                    index: 'status_name',
                    width: 12,
                    formatter: function(cellvalue,options,rowObject) {
                        //var id = rowObject.activit_id;

                        return cellvalue;
                    }
                },
                {
                    name: 'order_time',
                    index: 'order_time',
                    width: '20',
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
                    width: '23',
                    formatter: function(cellvalue,options,rowObject) {
                        var v_result = "";
                        var order_id=rowObject.order_id;
                        var status = rowObject.status;
                        var eat_type = rowObject.eat_type;
                        /*v_result += " &nbsp; <button  style='width: 70px;' class='btn btn-info' type='button'  onClick='toDetails(\"" + order_id + "\");'><i class='glyphicon glyphicon-pencil'></i> 接单</button>";*/
                        if(status==1){
                            v_result += "  <button  style='width: 79px;' id='btn' class='btn btn-info aaa' type='button'  onClick='receipt(\"" + order_id + "\",\""+eat_type+"\");'>确认接单</button>";
                            v_result += " &nbsp; <button  style='width: 55px;' class='btn btn-info' type='button'  onClick='updateStatus(\"" + order_id + "\",7);'> 取消</button>";
                        }
                       // v_result += " &nbsp; <button  style='width: 70px;' class='btn btn-info' type='button'  onClick='toDetails(\"" + order_id + "\");'><i class='glyphicon glyphicon-pencil'></i> 详情</button>";
                        if(status==2){
                            v_result += "  <button  style='width: 79px;' class='btn btn-info' type='button'  onClick='updateStatus(\"" + order_id + "\",3);'> 发货</button>";
                            v_result += " &nbsp; <button  style='width: 55px;' class='btn btn-info' type='button'  onClick='updateStatus(\"" + order_id + "\",7);'> 取消</button>";
                        }
                        return v_result;
                    }
                }
            ],
            sortable:true,
            sortname: 'order_time',
            sortorder: 'desc',
            pager: "#pager_list",
            caption: "订单管理",
        });



    });




    function searchStatus(status) {
        v_status=status;
        $("#table_list").jqGrid('setGridParam',{
            url:"order/list",
            postData:{status:status}, //发送数据
            page:1
        }).trigger("reloadGrid"); //重新载入
    }
    //数据加载完毕时触发
    var newest_order_id = null;
    function gridComplete(){
        if(v_status==1){
            var order_info = $("#table_list").jqGrid("getRowData",1);
            //console.log(v_coupon_user);
            var order_id = order_info.order_id;
            if(order_id && order_id!=newest_order_id){
                newest_order_id = order_id;
                //console.log(v_coupon_user.order_id);
                //$("#hasDispatchOrder").remove();
                var vedio = $('<audio id="hasDispatchOrder" src="images/web/dd.mp3" autoplay="autoplay">');
                //$("body").append(vedio);
            }
        }

    }

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

    var dom = document.getElementById("main");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    var posList = [
        'left', 'right', 'top', 'bottom',
        'inside',
        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
    ];

    app.configParameters = {
        rotate: {
            min: -90,
            max: 90
        },
        align: {
            options: {
                left: 'left',
                center: 'center',
                right: 'right'
            }
        },
        verticalAlign: {
            options: {
                top: 'top',
                middle: 'middle',
                bottom: 'bottom'
            }
        },
        position: {
            options: echarts.util.reduce(posList, function (map, pos) {
                map[pos] = pos;
                return map;
            }, {})
        },
        distance: {
            min: 0,
            max: 100
        }
    };

    app.config = {
        rotate: 0,
        align: 'left',
        verticalAlign: 'middle',
        position: 'top',
        distance: 15,
        onChange: function () {
            var labelOption = {
                normal: {
                    rotate: app.config.rotate,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    position: app.config.position,
                    distance: app.config.distance
                }
            };
            myChart.setOption({
                series: [{
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }]
            });
        }
    };


    var labelOption = {
        normal: {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c}  {name|{a}}',
            fontSize: 16,
            rich: {
                name: {
                    textBorderColor: '#fff'
                }
            }
        }
    };





    $.ajax({
        url: "getInfo",
        type: "post",
        data: {},
        dataType: "json",
        success: function (data) {
			var weekList=data.weekList;
            var orderNumList=data.orderNumList;
            var priceList=data.priceList
            option = {
                color: [ '#ff3300', '#4cabce'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: [ '成交金额']
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {show: false},
                        data: weekList
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    /*{
                        name: '成交单数',
                        type: 'bar',
                        barGap: 0,
                        barWidth:30,
                        label: labelOption,
                        data: orderNumList
                    },*/
                    {
                        name: '成交金额',
                        type: 'bar',
                        barWidth:30,
                        label: labelOption,
                        data: priceList
                    }/*,
                    {
                        name: '访问量',
                        type: 'bar',
                        label: labelOption,
                        data: [150, 232, 201, 154, 190,40,50]
                    }*/

                ]
            };
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }
    });

    function updateStatus(order_id,status) {
        var title="启用";
        if(status==3){
            title="发货";
        }else if(status==7) {
            title="取消";
        }
        layer.confirm("是否确认"+title,function () {
            $.ajax({
                url:"order/updateStatus",
                type:"post",
                data:{order_id:order_id,status:status},
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
    
    
    function receipt(order_id,eat_type) {
        layer.open({
            type: 2,
            title: '订单详情',
            shadeClose: true,
            shade: 0.8,
            offset: ['50px', '100px'],
            area: ['70%', '85%'],
            content: 'order/orderReceiptDetails?order_id='+order_id+"&eat_type="+eat_type //iframe的url
        });
    }

    
    function refush() {
        $("#table_list").jqGrid("setGridParam",{}).trigger("reloadGrid");
    }

    window.setInterval(refush, 30000);

</script>
</body>
</html>