var imappkey = "qd46yzrf424qf";
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
//获取参数
var params = getUrlVars();
var gid = params["gid"];
var gname = decodeURI(params["gname"]);
var pic = params["pic"];
var bcode = params["bcode"];

var id = getCookie("mobile");
if (id == "undefined" || id == "" || id == null) {
    //设置ID
    id = "13759412892";
    addCookie("mobile",id, 72);
}
var portrait = getCookie("headIngUrl");
var name = getCookie("nickname");

//portrait = "http://stg-weiwuu-face.oss-cn-beijing.aliyuncs.com/18807489993m3664.jpg";
//name = "大灰";

var group = new Object();
group.gid = gid;
group.gname = gname;
group.pic = pic;
group.bcode = bcode;
group.mcode = id;
//群ID
var groupid = "";
//创建群
$.ajax({
    type: "post",
    url: "http://" + host + "/garden/creategroup?name="+name+"&avatar_url="+portrait,
    dataType: "json",
    contentType: 'application/json',
    data: JSON.stringify(group),
    async: false,
    success: function (data) {
        if(data.head.code==1){
            groupid = data.body.data;
        }else if(data.head.code==3){
            alert(data.head.message);
            //重新设置手机号

            //重新加载页面
            window.location.href = "../setmobile.html?gid="+gid+"&gname="+gname+"&pic="+pic+"&bcode="+bcode
        }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        alert("操作失败，请检查网络状态！！！！");
    }
});