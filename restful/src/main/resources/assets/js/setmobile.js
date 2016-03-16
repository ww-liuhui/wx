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
//开始咨询
$("#start").click(function(){
    var validCode = $("#validCode").val().trim();
    if(validCode==validCodeR){
        var mobile = $("#mobile").val();
        if(mobile!=null&&mobile!="undefined"&&mobile.length==11&&!isNaN(mobile)){//如果验证码正确
            //将手机号存入cookie
            addCookie("mobile",mobile, 72);
            window.location.href = "im/index.html?gid="+gid+"&gname="+gname+"&pic="+pic+"&bcode="+bcode;
        }else{
            alert("请认真填写哦~");
        }

    }else{
        alert("验证码错误！");
    }
});

//微信分享
var title = "为屋帮帮，我要买房";
var thumbURL = "http://"+host+"/wx/img/logo.png";
var shareDesc = "为屋帮帮，无服务不买房。";
var shareUrl = "list.html";
