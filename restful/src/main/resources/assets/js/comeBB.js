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
var typeId = 0;//用户类型：1：买房人；2：帮帮
$("#iammember").click(function(){
    $("#iammember").addClass("orange");
    $("#iambroker").removeClass("orange");
    typeId = 1;
});

$("#iambroker").click(function(){
    $("#iambroker").addClass("orange");
    $("#iammember").removeClass("orange");
    typeId = 2;
});

var validCodeR = "";
//获取验证码
$("#getVerCode").click(function(){
    var mobile = $("#mobile").val();
    if(mobile!=null&&mobile!="undefined"&&mobile.length==11&&!isNaN(mobile)){
        $('#getVerCode').unbind("click");
        $("#getVerCode").removeClass("orange");
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/sendSMSRegister?mobile=" + mobile+"&typeId=1",
            dataType: "json",
            async: false,
            success: function (data) {
                validCodeR = data.body.data;
                //
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
    }else{
        alert("请输入正确的手机号");
    }

});
//我来帮帮
$("#comeBB").click(function(){
    var validCode = $("#validCode").val().trim();
    if(validCode==validCodeR){
        var mobile = $("#mobile").val();
        if(typeId>0&&mobile!=null&&mobile!="undefined"&&mobile.length==11&&!isNaN(mobile)){
            //如果验证码正确，绑定用户
            $.ajax({
                type: "get",
                url: "http://" + host + "/wxapi/comeBB?mobile="+mobile+"&openid="+openid+"&typeId="+typeId,
                dataType: "json",
                async: false,
                success: function (data) {
                    if(data.head.code==1){
                        if(confirm("欢迎加入，下载为屋帮帮APP？")){
                            window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.weiwuu.fairy";
                        }else{
                            window.location.href = "bbinfo.html?openid="+openid;
                        }
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("操作失败，请检查网络状态！");
                }
            });
        }else{
            alert("请认真填写哦~");
        }

    }else{
        alert("验证码错误！");
    }
});

//微信分享
var title = "我来帮帮，为屋帮帮欢迎你！";
var thumbURL = "http://"+host+"/wx/img/logo.png";
var shareDesc = "为屋更为你，为屋帮帮欢迎你！";
var shareUrl = window.location.href;
