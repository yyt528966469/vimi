<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    <script src="../activity/vendor/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lhj8IQajdGYvgY8K1UnxuKArxu5gnVU6"></script>
    <script src="../activity/vendor/layer/layer.min.js"></script>

    <title>单击获取点击的经纬度</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var lng="${lng}";
    var lat="${lat}";
    if(lng){
        map.centerAndZoom(new BMap.Point(lng,lat),12);
    }else {
        map.centerAndZoom("武汉",12);
    }


    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

    var point = new BMap.Point(lng,lat);
    map.centerAndZoom(point, 15);
    var marker = new BMap.Marker(point);  // 创建标注
    map.addOverlay(marker);               // 将标注添加到地图中
    marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

    // 用经纬度设置地图中心点
    function theLocation(){
        if(document.getElementById("longitude").value != "" && document.getElementById("latitude").value != ""){
            map.clearOverlays();
            var new_point = new BMap.Point(document.getElementById("longitude").value,document.getElementById("latitude").value);
            var marker = new BMap.Marker(new_point);  // 创建标注
            map.addOverlay(marker);              // 将标注添加到地图中
            map.panTo(new_point);
        }
    }
    var geoc = new BMap.Geocoder();
    //单击获取点击的经纬度
    map.addEventListener("click",function(e){
        //window.parent.frames.getlngAndlat(e.point.lng,e.point.lat)
        var lng=e.point.lng;
        var lat=e.point.lat;
        //alert(lng + "," + lat);


        var pt = e.point;
        geoc.getLocation(pt, function(rs){
            var addComp = rs.addressComponents;
            //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
            window.parent.frames.getlngAndlat(lng,lat,addComp.province,addComp.city,addComp.district,addComp.street,addComp.streetNumber,addComp);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        /*var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);*/
    });
</script>
