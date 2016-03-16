var city_id = 273;

//unionid = getCookie("unionid");
var nickname = getCookie("nickname");
if(nickname!=null&&nickname!="undefined"){
    $("#name").val(nickname);
}

$("#order").click(function(){
    var name = $("#name").val();
    var tel = $("#tel").val();
    var time = $("#time").val();
    var addr_from = $("#addr_from").val();
    var addr_to = $("#addr_to").val();
    if(name!=null&&tel!=null&&tel.length==11&&!isNaN(tel)&&time!=null){
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/createOrder?name=" + name+"&tel="+tel+"&time="+time+"&addr_from="+addr_from+"&addr_to="+addr_to,
            dataType: "json",
            async: false,
            success: function (data) {
                alert("恭喜，预约成功！");
                $("#message").show();

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
    }else{
        alert("输入有误。");
    }



});



//设置时间
$('#time').mobiscroll().datetime({
    theme: "android-ics light",
    mode: "scroller",
    display: "bottom",
    animate:"slidedown",
    width: "40px",
    minDate: new Date(),
    //maxDate: new Date(2017,1,31,23,59),
    timeWheels: 'HHii',
    timeFormat: 'HH:ii',
    lang:"zh",
    setText: '确定',
    cancelText: '取消',
    dateFormat: 'yyyy-mm-dd'
});
$('#time').trigger('change');

//微信分享
var title = "为屋帮帮免费专车看房";
var thumbURL = "http://"+host+"/wx/img/callcar.jpg";
var shareDesc = "为屋更为你";
var shareUrl = window.location.href;
