if (typeof dcodeIO === 'undefined' || !dcodeIO.ProtoBuf) {
    throw(new Error("ProtoBuf.js is not present. Please see www/index.html for manual setup instructions."));
}
// Initialize ProtoBuf.js
var ProtoBuf = dcodeIO.ProtoBuf;

var appKey = "weiwuu";
var appSecret = "weiwuu2016";

//喜欢
function like(obj) {
    $(obj).children("i").addClass("green bounceIn");
    var num = $(obj).children("span").text().trim() - 0;
    $(obj).children("span").text(num + 1);

    //更新喜欢数
    var nid = $(obj).parent().parent().attr("id");
    $.ajax({
        type: "put",
        url: "http://" + host + "/news/editFollowLike?id=" + nid,
        dataType: "json",
        async: true,
        success: function (data) {
            if (data.head.code == 1) {

                $(obj).attr("onclick", "");
                $(obj).next().attr("onclick", "");

            } else {
                alert("操作失败，请检查网络状态");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("操作失败，请检查网络状态！");
        }
    });

}
//不喜欢
function unlike(obj) {
    $(obj).children("i").addClass("red bounceIn");
    var num = $(obj).children("span").text().trim() - 0;
    $(obj).children("span").text(num + 1);

    //更新反对数
    var nid = $(obj).parent().parent().attr("id");
    $.ajax({
        type: "put",
        url: "http://" + host + "/news/editFollowUnlike?id=" + nid,
        dataType: "json",
        async: true,
        success: function (data) {
            if (data.head.code == 1) {

                $(obj).attr("onclick", "");
                $(obj).prev().attr("onclick", "");
            } else {
                alert("操作失败，请检查网络状态");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("操作失败，请检查网络状态！");
        }
    });
}

//获取地址参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
}
//获取参数ID
var id = getQueryString("id");

var title = "帮帮找房";
var thumbURL = "";
var shareDesc = "为屋更为你";
//根据ID获取资讯详情
function getNewsById(id) {
    var newsObj = null;
    var News = ProtoBuf.loadProtoFile("./protobuf/News.proto").build("News");
    var xhr = ProtoBuf.Util.XHR();
    xhr.open(
        "GET",
        "http://" + host + "/news/getNewsById?id=" + id,
        true
    );
    xhr.responseType = "arraybuffer";
    xhr.onload = function (evt) {
        newsObj = News.decode(xhr.response);
        //设置编辑框数据
        $("#title").text(newsObj.get("caption"));
        $(".detail_title").text(newsObj.get("caption"));
        $(".detail_author").text(newsObj.get("author"));
        $(".detail_time").text(newsObj.get("issueTime"));
        $(".detail_content").html(newsObj.get("content"));

        title = newsObj.get("caption");
        thumbURL = newsObj.get("thumbURL");
        shareDesc = newsObj.get("shareDesc");

    }
    xhr.send(null);
}
//加载数据
getNewsById(id);
//加载评论
getFollowList(id);

//用户信息
var userId = 0;
var userName = "游客";
var userHeadPic = "img/my.png";

//发表评论
$("#send").click(function () {

    var content = $("#content").val();
    if (content != null && content.length > 0) {
        try {
            //app
            WeiWuJSBridge.invoke('getUserInfo', '{\"appKey\":\"' + appKey + '\", \"appSecret\":\"' + appSecret + '\"}', 'createCommentApp');

        } catch (err) {
            //web
            createCommentWeb();

        }
    }

});
//web 添加评论
function createCommentWeb() {
    var content = $("#content").val();
    var day = new Date();
    var time = dateFormat(day);
    //更新服务器数据
    var NewsFollow = ProtoBuf.loadProtoFile("./protobuf/NewsFollow.proto").build("NewsFollow");
    var nf = new NewsFollow();
    nf.setNewsId(parseInt(id));
    nf.setIssueTime(time);
    nf.setAuthorId(userId);
    nf.setAuthorNickName(userName);
    nf.setAuthorAvatarURL(userHeadPic);
    nf.setContent(content);

    nf.setAssentCount(0);
    nf.setOpposeCount(0);
    nf.setReportCount(0);

    //console.info(nt.toBase64());
    $.ajax({
        type: "post",
        url: "http://" + host + "/news/createNewsFollow",
        dataType: "json",
        async: false,
        contentType: 'application/octet-stream',
        data: nf.toBase64(),
        success: function (data) {
            if (data.head.code == 1) {
                var followId = data.body.data;

                $("#pl_list").prepend('<div class="pl_item" id="' + followId + '"><img class="pl_item_img" src="' + userHeadPic + '"/><div class="pl_item_nickname">' + userName + '</div><div class="pl_item_time">' + time.substring(11,16) + '</div><div class="pl_item_content">' + content + '</div><div class="item_end"><span class="item_like" onclick="like(this)"><i class="large thumbs outline up icon"></i><span>0</span></span><span class="item_unlike" onclick="unlike(this)"><i class="large thumbs outline down icon"></i><span>0</span></span></div><div class="ui divider"></div></div>');

                $("#content").val("");
                location.hash = "#" + followId;
            } else {
                alert("操作失败，请检查网络状态");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("操作失败，请检查网络状态！");
        }
    });
}
//根据ID获取评论列表
function getFollowList(id) {
    var NewsList = ProtoBuf.loadProtoFile("./protobuf/NewsList.proto").build("NewsList");
    var xhr = ProtoBuf.Util.XHR();
    xhr.open(
        "GET",
        "http://" + host + "/news/getFollowList?id=" + id,
        true
    );
    xhr.responseType = "arraybuffer";
    xhr.onload = function (evt) {
        var nl = NewsList.decode(xhr.response);

        var newsFollowList = nl.get("newsFollowList");
        console.info(newsFollowList);
        for (var i = 0; i < newsFollowList.length; i++) {
            var assentCount = newsFollowList[i].assentCount == null ? 0 : newsFollowList[i].assentCount;
            var opposeCount = newsFollowList[i].opposeCount == null ? 0 : newsFollowList[i].opposeCount;
            $("#pl_list").prepend('<div class="pl_item" id="' + newsFollowList[i].id + '"><img class="pl_item_img" src="' + newsFollowList[i].authorAvatarURL + '"/><div class="pl_item_nickname">' + newsFollowList[i].authorNickName + '</div><div class="pl_item_time">' + newsFollowList[i].issueTime.substring(0,16) + '</div><div class="pl_item_content">' + newsFollowList[i].content + '</div><div class="item_end"><span class="item_like" onclick="like(this)"><i class="large thumbs outline up icon"></i><span>' + assentCount + '</span></span><span class="item_unlike" onclick="unlike(this)"><i class="large thumbs outline down icon"></i><span>' + opposeCount + '</span></span></div><div class="ui divider"></div></div>');
        }
    }
    xhr.send(null);

}
//时间格式化
function dateFormat(day) {
    var year = day.getFullYear();
    var month = (day.getMonth() + 1) >= 10 ? (day.getMonth() + 1) : "0" + (day.getMonth() + 1);
    var date = day.getDate() >= 10 ? day.getDate() : "0" + day.getDate();

    var hour = day.getHours() >= 10 ? day.getHours() : "0" + day.getHours();
    var min = day.getMinutes() >= 10 ? day.getMinutes() : "0" + day.getMinutes();
    var sec = day.getSeconds() >= 10 ? day.getSeconds() : "0" + day.getSeconds();

    var dateF = year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;

    return dateF;
}

var url = window.location.href;
//获取微信js接口权限
$.ajax({
    type: "get",
    url: "http://" + host + "/wxapi/validate",
    data: {
        url: url
    },
    dataType: "json",
    async: false,
    success: function (data) {
        if (data.head.code == 1) {
            var backStr = data.body.data;
            var arr = new Array();
            arr = backStr.split(",");
            wx.config({
                debug: false,
                appId: arr[0].trim(),
                timestamp: arr[1].trim(),
                nonceStr: arr[2].trim(),
                signature: arr[3].trim(),
                jsApiList: ['checkJsApi', 'onMenuShareTimeline',
                    'onMenuShareAppMessage', 'hideMenuItems', 'showMenuItems',
                    'translateVoice', 'startRecord', 'stopRecord',
                    'onRecordEnd', 'playVoice', 'pauseVoice', 'stopVoice',
                    'uploadVoice', 'downloadVoice', 'chooseImage',
                    'previewImage', 'uploadImage', 'downloadImage',
                    'getNetworkType', 'openLocation', 'getLocation',
                    'hideOptionMenu', 'showOptionMenu', 'closeWindow']
            });
        }

    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        alert("操作失败，请检查网络状态！");
    }
});

wx.ready(function () {
    var shareData = {
        title: title,
        desc: shareDesc,
        link: url,
        imgUrl: thumbURL
    };
    wx.onMenuShareAppMessage({
        title: shareData.title, // 分享标题
        desc: shareData.desc, // 分享描述
        link: shareData.link, // 分享链接
        imgUrl: shareData.imgUrl, // 分享图标
        type: '', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
    wx.onMenuShareTimeline({
        title: shareData.title, // 分享标题
        link: shareData.link, // 分享链接
        imgUrl: shareData.imgUrl, // 分享图标
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });

    wx.hideMenuItems({
        menuList: ['menuItem:copyUrl', 'menuItem:openWithSafari',
            'menuItem:openWithQQBrowse']
        // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮
    });

});

$("#sendbutton").click(function(){
    window.location.href = "#divider";
    $("#content").focus();
});