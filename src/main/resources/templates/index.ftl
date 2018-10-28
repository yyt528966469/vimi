<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>后台管理系统</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--link rel="shortcut icon" href="favicon.ico"-->
    <link href="activity/vendor/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/index/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/index/animate.css" rel="stylesheet">
    <link href="css/index/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id ="header" style="padding-left:30px;height:75px;width:auto;background-color:#2f4050">
    <p style="padding-top:15px;font-size:25px;color:white;font-family:'黑体'">薇明科技后台管理系统</p>
   <#-- <p style="padding-top:5px;font-size:18px;color:white;font-family:'黑体';margin-top:-20px">City Comprehensive Information Management System</p>-->
</div>
<!--主体-->

<div id="wrapper">
    <!--div class="dashbard-1" id="title">
        <img src="img/title.png">
    </div-->
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i></div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope">&nbsp;</span>
                </li>
                <li>
                    <a href="#"><i class="fa fa-cloud-download"></i> <span class="nav-label">首页</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="echats"><i class="fa fa-cloud-download"></i>首页</a></li>
                    </ul>
                </li>
                <#--<li>-->
                <#list list as res>

		<li >
            <#--<span></span>-->
            <a href="#"><i class="${res.img}"></i> <span class="nav-label">${res.name}</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level">
				<#list res.children as child>
				<#--<li >

                </li>-->
                <li url="${child.res_url}"><a class="J_menuItem" href="${child.res_url}"><i class="${child.img}"></i>${child.name}</a></li>
                </#list>
            <#--<li>
                机构分类

            </li>-->
            </ul>
        </li>
                </#list>


                <#--<li>
                    <a href="#"><i class="fa fa-cloud-download"></i> <span class="nav-label">基础数据管理</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="Basic_data_road.html">道路数据</a></li>
                        <li><a class="J_menuItem" href="Basic_data_bridge.html">桥梁数据</a></li>
                        <li><a class="J_menuItem" href="Basic_data_pipe.html">地下管线数据</a></li>
                    </ul>
                </li>-->
                <#--<li>
                    <a href="#"><i class="fa fa-calendar"></i> <span class="nav-label">日常项目管理</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="Daily_items.html">道路大中修</a></li>
                        <li><a class="J_menuItem" href="Daily_items1.html">排水管线改造</a></li>
                        <li><a class="J_menuItem" href="Daily_items2.html">慢行系统改造</a></li>
                        <li><a class="J_menuItem" href="">疏堵工程</a></li>
                        <li><a class="J_menuItem" href="">架空线入地</a></li>
                        <li><a class="J_menuItem" href="">微循环改造</a></li>
                        <li><a class="J_menuItem" href="daily_data.html">相关数据录入</a></li>
                    </ul>
                </li>
                <!--                     <li>
                                        <a href="#"><i class="fa fa-building-o"></i> <span class="nav-label">业务相关</span><span class="fa arrow"></span></a>
                                        <ul class="nav nav-second-level">
                                            <li><a class="J_menuItem" href="">占道审批</a></li>
                                            <li><a class="J_menuItem" href="">广告牌备案</a></li>
                                            <li><a class="J_menuItem" href="">各相关部门</a></li>
                                        </ul>
                                    </li> &ndash;&gt;
                <li>
                    <a href="#"><i class="fa fa-folder"></i> <span class="nav-label">状况备案管理</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="">各部门协调</a></li>
                        <li><a class="J_menuItem" href="Status_record_create.html">事件记录创建</a></li>
                        <li><a class="J_menuItem" href="Status_record_query.html">事件记录查询</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-folder"></i> <span class="nav-label">数据更新管理</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="">数据更新</a></li>
                        <li><a class="J_menuItem" href="Status_record_create.html">数据修改记录</a></li>
                    </ul>
                </li>-->
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row J_mainContent" id="content-main">
            <iframe id="J_iframe" src="echats"  width="100%" height="100%" frameborder="0" data-id=" seamless"></iframe>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--内容
    <div1 id="content">

    </div1>-->
</div>

<!-- 全局js -->
<script src="js/jquery-3.1.1.min.js"></script>
<script src="activity/vendor/bootstrap.min.js"></script>
<script src="js/index/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/index/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="activity/vendor/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="js/index/hAdmin.js?v=4.1.0"></script>
<script type="text/javascript" src="js/index/index.js"></script>

</body>

</html>
