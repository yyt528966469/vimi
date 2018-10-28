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
    <script src="../js/LodopFuncs.js"></script>
    <object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
        <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="../js/install_lodop32.exe"></embed>
    </object>
</head>
<section class="content" height="100%" >
    <div class="row">
        <div class="col-xs-12">

            <div class="box-body">
                <form id="myform">

                    <div class="box-line">
                        <span class="title mid-inline-block">订单号：</span>
                        ${t_order.order_id!''}
                        <span style="left:272px;">
                        <span style="margin-left: 50px;" class="title mid-inline-block">下单时间：</span>
                    ${t_order.order_time?string('yyyy-MM-dd HH:mm:ss')}</span>
                    </div>
                    <div class="box-line" >
                        <span class="title mid-inline-block">订单状态：</span>
                    ${t_order.status_name}
                        <span style="left:272px;">
                        <span style="margin-left: 150px;" class="title mid-inline-block">订单金额：</span>
                    <em style="color: red">￥${t_order.real_price}</em>
                        </span>
                    </div>
                    <div class="box-line">
                        <span class="title mid-inline-block">备注：</span>
                    ${t_order.remarks!''}

                    </div>

                    <div class="box-line">
                        -------------------------商品信息--------------------------
                    </div>
                    <div class="box-line">
                        <#--<span class="title mid-inline-block">商品列表：</span>-->
                        <table style="width: 50%;">
                            <tr >
                                <th>商品名</th>
                                <th>数量</th>
                                <th>单价</th>
                                <th>总额</th>
                            </tr>
                            <#list t_order.commodity_infoList as commodity_info>
                                <tr>
                                    <td>${commodity_info.commodity_name}</td>
                                    <td>${commodity_info.comm_num}</td>
                                    <td>${commodity_info.commodity_price}</td>
                                    <td>${commodity_info.sum_price}</td>
                                </tr>
                            </#list>

                        </table>
                    </div>
                   <div class="box-line">
                        -------------------------其他信息--------------------------
                    </div>
                    <#--<div class="box-line">
                        <span class="title mid-inline-block">姓名：</span>
                    ${t_order.t_order_address.name!''}
                    </div>-->
                    <#if t_order.t_order_arrive??>
                        <div class="box-line">
                            <span class="title mid-inline-block">联系方式：</span>
                        ${t_order.t_order_arrive.phone!''}
                        </div>
                        <div class="box-line">
                            <span class="title mid-inline-block">预约时间：</span>
                        ${t_order.t_order_arrive.arrive_time?string('yyyy-MM-dd HH:mm:ss')}
                        </div>
                    </#if>
                    <#if t_order.t_order_scavenging??>
                        <div class="box-line">
                            <span class="title mid-inline-block">桌号：</span>
                            ${t_order.t_order_scavenging.table_num!''}号
                        </div>
                        <div class="box-line">
                            <span class="title mid-inline-block">人数：</span>
                            ${t_order.t_order_scavenging.person_num}
                        </div>
                    </#if>

                </form>
                <div class="box-line">
                    <span class="title mid-inline-block"></span>
                    <button onclick="updateStatus('${t_order.order_id}')" class="btn btn-red">
                        <span>接单</span>
                    </button>

                    <button onclick="closeWin()" class="btn">
                        <span>关闭</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>

<script>

function closeWin() {
    window.parent.frames.refush();
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function updateStatus(order_id) {
    layer.confirm("是否确认接单？",function () {
        $.ajax({
            url:"../order/updateStatus",
            type:"post",
            data:{order_id:order_id,status:2},
            dataType:"json",
            success:function(data){
                if(data.result=='0'){
                    printOrder(order_id);
                    layer.alert("接单成功",function (index) {
                        layer.close(index);
                        closeWin();
                    });
                }
            }
        });
    });
}


var LODOP; //声明为全局变量
function printPreview(){
    //创建小票打印页
    CreatePrintPage();
    //打印预览
    LODOP.PREVIEW();
}
/**
 * 样例函数,服务器确认订单后执行
 */
function printOrder(order_id) {
    $.ajax({
        url:"../order/getDdOrderInfo",
        type:"post",
        data:{order_id:order_id},
        async:false,
        dataType:"json",
        success:function(data){
            var json=data.printOrder;
            CreatePrintPage(json);
        }
    });
    //创建小票打印页
    //开始打印
    LODOP.PRINT();

};
function CreatePrintPage(json) {
    //json 创建模拟服务器响应的订单信息对象
    /*var json = {"title":"XXXXX订单信息", "name":"张三", "phone": "138123456789", "orderTime": "2012-10-11 15:30:15",
        "orderNo": "20122157481315", "shop":"XX连锁", "total":25.10,"totalCount":6,
        "goodsList":[
            {"name":"菜心(无公害食品)", "price":5.00, "count":2, "total":10.08},
            {"name":"菜心(无公害食品)", "price":5.00, "count":2, "total":10.02},
            {"name":"旺菜", "price":4.50, "count":1, "total":4.50},
            {"name":"黄心番薯(有机食品)", "price":4.50, "count":1, "total":4.50}
        ]
    }*/

    var hPos=10,//小票上边距
            pageWidth=580,//小票宽度
            rowHeight=15,//小票行距
            //获取控件对象
            LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
    //初始化
    LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_名片");
    //添加小票标题文本
    LODOP.ADD_PRINT_TEXT(hPos,70,pageWidth,rowHeight,json.title);
    //上边距往下移
    hPos+=rowHeight;
    if(json.address==1){//区分到店自取或店内点餐
        LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"就餐类型:");
        LODOP.ADD_PRINT_TEXT(hPos,60,pageWidth,rowHeight,"到店自取");
        /*LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"姓名:");
        LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,json.name);*/
        hPos+=rowHeight; //电话换行
        LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"电话:");
        LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,json.phone);
    }else {
        LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"就餐类型:");
        LODOP.ADD_PRINT_TEXT(hPos,60,pageWidth,rowHeight,"店内点餐");
        /*LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"姓名:");
        LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,json.name);*/
        hPos+=rowHeight; //电话换行
        LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"桌号:");
        LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,json.table_num);
        LODOP.ADD_PRINT_TEXT(hPos,60,pageWidth,rowHeight,"人数:");
        LODOP.ADD_PRINT_TEXT(hPos,90,pageWidth,rowHeight,json.person_num);
    }

   /* hPos+=rowHeight;
    LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"地址:");
    var address=json.address;
    var count=parseInt((address.length)/11);

    for(var i=0;i<count;i++){
        if(i>0){
            hPos+=rowHeight;
        }
        LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,address.substr(i,(i+1)*11));
        address=address.substr((i+1)*11,address.length);
    }
    if(address.length>0){
        hPos+=rowHeight;
        LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,address);
    }*/
    /*if(address.length<12){

        LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,address);
    }*/
    //LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,json.address);
    hPos+=rowHeight;
    LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"下单时间:");
    LODOP.ADD_PRINT_TEXT(hPos,60,pageWidth,rowHeight,json.orderTime);
    hPos+=rowHeight;
    LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"订单编号:");
    LODOP.ADD_PRINT_TEXT(hPos,60,pageWidth,rowHeight,json.orderNo);
    hPos+=rowHeight;
    LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"店铺名称:");
    LODOP.ADD_PRINT_TEXT(hPos,60,pageWidth,rowHeight,json.shop);
    hPos+=rowHeight;
    LODOP.ADD_PRINT_LINE(hPos,2, hPos, pageWidth,2, 1);
    hPos+=5;
    LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"商品名称");
    LODOP.ADD_PRINT_TEXT(hPos,70,pageWidth,rowHeight,"单价");
    LODOP.ADD_PRINT_TEXT(hPos,110,pageWidth,rowHeight,"数量");
    LODOP.ADD_PRINT_TEXT(hPos,140,pageWidth,rowHeight,"小计");
    hPos+=rowHeight;
    //遍历json的商品数组
    for(var i=0;i<json.goodsList.length;i++){

        if(json.goodsList[i].name.length<4){
            LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,json.goodsList[i].name);
        }else {
            //商品名字过长,其他字段需要换行
            LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,json.goodsList[i].name);
            hPos+=rowHeight;
        }
        LODOP.ADD_PRINT_TEXT(hPos,70,pageWidth,rowHeight,json.goodsList[i].price);
        LODOP.ADD_PRINT_TEXT(hPos,115,pageWidth,rowHeight,json.goodsList[i].count);
        LODOP.ADD_PRINT_TEXT(hPos,140,pageWidth,rowHeight,json.goodsList[i].total);
        hPos+=rowHeight;
    }
    /*hPos+=rowHeight;

    LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"包装费:");
    LODOP.ADD_PRINT_TEXT(hPos,140,pageWidth,rowHeight,json.packing_price);
    hPos+=rowHeight;

    LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"配送费:");
    LODOP.ADD_PRINT_TEXT(hPos,140,pageWidth,rowHeight,json.distribution_price);*/
    //商品遍历打印完毕,空一行
    hPos+=rowHeight;
    //合计
    LODOP.ADD_PRINT_TEXT(hPos,80,pageWidth,rowHeight,"合计:"+json.totalCount);
    LODOP.ADD_PRINT_TEXT(hPos,130,pageWidth,rowHeight,"￥"+json.total);
    hPos+=rowHeight;

    LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"备注:");
    var remarks=json.remarks;
    var count=parseInt((remarks.length)/11);
    //console.log(count,8888);
    for(var i=0;i<count;i++){
        if(i>0){
            hPos+=rowHeight;
        }
        LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,remarks.substr(0,11));
        remarks=remarks.substr(11,remarks.length);
        //console.log(remarks,i);
    }
    if(remarks.length>0){
        hPos+=rowHeight;
        LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,remarks);
    }
    //LODOP.ADD_PRINT_TEXT(hPos,30,pageWidth,rowHeight,json.remarks);
    hPos+=rowHeight;
    LODOP.ADD_PRINT_TEXT(hPos,2,pageWidth,rowHeight,(new Date()).toLocaleDateString()+" "+(new Date()).toLocaleTimeString())
    hPos+=rowHeight;
    LODOP.ADD_PRINT_TEXT(hPos,25,pageWidth,rowHeight,"谢谢惠顾,欢迎下次光临!");

    //初始化打印页的规格
    LODOP.SET_PRINT_PAGESIZE(3,pageWidth,45,"订单信息");

};



</script>
</html>