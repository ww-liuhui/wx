<#-- @ftlvariable name="" type="com.weiwuu.cloud.wx.view.IndexView" -->
<!DOCTYPE html>
<html>
<head>
    <!-- Standard Meta -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <!-- Site Properities -->
    <meta name="generator" content="" />
    <title>为屋后台管理登陆</title>

    <meta name="description" content="为屋后台管理" />
    <meta name="keywords" content="为屋后台管理"/>

    <!-- 引用 -->
    <link rel="shortcut icon" type="image/x-icon" href="/web/img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="/web/css/semantic.min.css" />
    <link rel="stylesheet" type="text/css" href="/web/css/style.css" />
</head>

<body style="margin: 1px">
<div class="ui orange segment">
    <div class="ui form grid" style="margin-left: 1px;margin-top: 30px;">
        <div class="row">
            <div class="three wide column"> </div>
            <div class="ten wide column">
                <div class="ui raised segment">
                    <div class="ui ribbon label" style="font-size:large;">登录</div><div style="font-size:small;margin: 20px;">为屋后台管理</div>
                    <div class="ui orange ribbon label">用户名</div>
                    <p>
                    <div class="ui left labeled icon input small field">
                        <i class="left user icon"></i>
                        <input placeholder="输入用户名" name="code" type="text" style="width:100%;margin-right: -10px" id="code">
                    </div>
                    </p>
                    <div class="ui orange ribbon label">密码</div>
                    <p>
                    <div class="ui left labeled icon input small field">
                        <input placeholder="输入密码" name="password" type="password" style="width: 100%;margin-right: -10px" id="password">
                        <i class="lock icon"></i>
                    </div>
                    </p>
                </div>
                <a class="circular ui fluid orange submit button"  id="login">登录</a>
            </div>
            <div class="three wide column"> </div>
        </div>
    </div>

</div>
<div class="ui bottom attached orange progress">
    <div class="bar" style="width: 0%;" id="bar"></div>
</div>

</body>
<script src="/web/js/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="/web/js/semantic.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/web/js/cookie.js?111"></script>
<script type="text/javascript" src="/web/js/login.js?541"></script>
</html>