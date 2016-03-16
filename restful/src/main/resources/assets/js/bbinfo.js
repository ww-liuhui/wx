if (typeof dcodeIO === 'undefined' || !dcodeIO.ProtoBuf) {
    throw(new Error("ProtoBuf.js is not present. Please see www/index.html for manual setup instructions."));
}
// Initialize ProtoBuf.js
var ProtoBuf = dcodeIO.ProtoBuf;

var city_id = 273;

//获取地址参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
}
//获取参数ID
var openid = getQueryString("openid");
getUserInfo();
//根据openid获取用户信息
function getUserInfo() {
    var obj = null;
    var WXUser = ProtoBuf.loadProtoFile("./protobuf/WXUser.proto").build("WXUser");
    var xhr = ProtoBuf.Util.XHR();
    xhr.open(
        "GET",
        "http://" + host + "/wxapi/getUserInfo?openid=" + openid +"&t=" + (new Date()).getTime().toString(),
        true
    );
    xhr.responseType = "arraybuffer";
    xhr.onload = function (evt) {
        try {
            obj = WXUser.decode(xhr.response);
            var type = obj.get("type");
            if(type==1){
                $("#iammember").show();
            }else if(type==2){
                $("#iambroker").show();
            }
            $("#headImgUrl").attr("src",obj.get("headImgUrl"));
            $("#nickname").val(obj.get("nickname"));
            $("#mobile").val(obj.get("tel"));
            $("#mobile").parent().attr("href","tel:"+obj.get("tel"));

        } catch (err) {
        }

    }
    xhr.send(null);
}


//微信分享
var title = "我来帮帮";
var thumbURL = "http://" + host + "/wx/img/logo.png";
var shareDesc = "为屋更为你";
var shareUrl = window.location.href;
