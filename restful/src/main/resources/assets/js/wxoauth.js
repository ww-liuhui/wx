function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}

var appid = "wx7a3e90e6805f58d3";
var unionid = getCookie("unionid");
if(isWeiXin()){
    if (unionid == "undefined" || unionid == "" || unionid == null) {  //用户没有登陆
        var redirect_uri = window.location.href;
        redirect_uri = redirect_uri.replace(/:/g,"%3A").replace(/\//g,"%2F");
        var wxoauthurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        //获取地址参数
        function getUrlVars() {
            var vars = [], hash;
            var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            for (var i = 0; i < hashes.length; i++) {
                hash = hashes[i].split('=');
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
            return vars;
        }
        //获取参数ID
        var params = getUrlVars();
        var code = params["code"];

        if (code == "undefined" || code == "" || code == null) {
            window.location.href = wxoauthurl;
        }else{
            //获取用户信息
            $.ajax({
                type: "get",
                url: "http://" + host + "/wxapi/oauth?code=" + code,
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.head.code == 1) {
                        var userinfo = data.body.data;
                        addCookie("unionid", userinfo.unionid, 72);
                        addCookie("openId", userinfo.openId, 72);
                        addCookie("nickname", userinfo.nickname, 72);
                        addCookie("sex", userinfo.sex, 72);
                        addCookie("headIngUrl", userinfo.headIngUrl, 72);
                        addCookie("country", userinfo.country, 72);
                        addCookie("province", userinfo.province, 72);
                        addCookie("city", userinfo.city, 72);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("操作失败，请检查网络状态！");
                }
            });
        }

    } else {  //用户登陆过
        //业务
    }
}
