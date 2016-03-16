//if (typeof dcodeIO === 'undefined' || !dcodeIO.ProtoBuf) {
//    throw(new Error("ProtoBuf.js is not present. Please see www/index.html for manual setup instructions."));
//}
//// Initialize ProtoBuf.js
//var ProtoBuf = dcodeIO.ProtoBuf;

var city_id = 273;

//获取地址参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
}
//获取参数
var code = getQueryString("code");
var openid = getQueryString("openid");
//下载
$("#download").click(function(){
    var mobile = $("#mobile").val();
    if(mobile!=null&&mobile.length==11&&!isNaN(mobile)){
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/recomRegister?mobile=" + mobile+"&code="+code+"&openid="+openid,
            dataType: "json",
            async: false,
            success: function (data) {
                if(data.head.code==1){
                    if(data.body.data==1){
                        alert("成功推荐，下载领礼包！");
                        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.weiwuu.fairy";
                    }else if(data.body.data==2){
                        alert("已经被推荐，下载领礼包！");
                        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.weiwuu.fairy";
                    }else{
                        alert("推荐失败，下载也有礼哦！");
                        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.weiwuu.fairy";
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
    }else{
        alert("手机号输入有误！");
    }

});

//微信分享
var title = "为屋帮帮推荐有礼";
var thumbURL = "http://"+host+"/wx/img/logo.png";
var shareDesc = "为屋更为你！";
var shareUrl = window.location.href;
