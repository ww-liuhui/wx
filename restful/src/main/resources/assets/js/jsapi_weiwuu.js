////添加资讯评论
//function createCommentApp(result) {
//
//    alert("result:" + result);
//    var res = eval(result);
//    alert("res.data:"+res.data);
//
//    if (res != null && res.data != null) {
//        alert("已登录");
//
//        var data = res.data;
//        userId = data.userId;
//        userName = data.userName;
//        userHeadPic = data.avatarUrl;
//
//        var content = $("#content").val();
//        var day = new Date();
//        var time = dateFormat(day);
//
//        //更新服务器数据
//        var NewsFollow = ProtoBuf.loadProtoFile("./protobuf/NewsFollow.proto").build("NewsFollow");
//        var nf = new NewsFollow();
//        nf.setNewsId(parseInt(id));
//        nf.setIssueTime(time);
//        nf.setAuthorId(userId);
//        nf.setAuthorNickName(userName);
//        nf.setAuthorAvatarURL(userHeadPic);
//        nf.setContent(content);
//
//        nf.setAssentCount(0);
//        nf.setOpposeCount(0);
//        nf.setReportCount(0);
//
//        //console.info(nt.toBase64());
//        $.ajax({
//            type: "post",
//            url: "http://" + host + "/news/createNewsFollow",
//            dataType: "json",
//            async: false,
//            contentType: 'application/octet-stream',
//            data: nf.toBase64(),
//            success: function (data) {
//                if (data.head.code == 1) {
//                    var followId = data.body.data;
//
//                    $("#pl_list").prepend('<div class="pl_item" id="' + followId + '"><img class="pl_item_img" src="' + userHeadPic + '"/><div class="pl_item_nickname">' + userName + '</div><div class="pl_item_time">' + time + '</div><div class="pl_item_content">' + content + '</div><div class="item_end"><span class="item_like" onclick="like(this)"><i class="large thumbs outline up icon"></i><span>0</span></span><span class="item_unlike" onclick="unlike(this)"><i class="large thumbs outline down icon"></i><span>0</span></span></div><div class="ui divider"></div></div>');
//
//                    $("#content").val("");
//                    location.hash="#"+followId;
//                } else {
//                    alert("操作失败，请检查网络状态");
//                }
//            },
//            error: function (XMLHttpRequest, textStatus, errorThrown) {
//                alert("操作失败，请检查网络状态！");
//            }
//        });
//
//    } else {
//        //跳转到登陆页面
//        WeiWuJSBridge.invoke('getRequestSigin', '{\"appKey\":\"' + appKey + '\", \"appSecret\":\"' + appSecret + '\"}', "toLogin");
//    }
//}

//添加资讯评论
function createCommentApp(result) {
    var res = eval(result);
    //alert("res.data:"+res.data);
    if (res != null && res.data != null) {
        userId = res.data.userId;
        if(userId==null || userId==0){
            //跳转到登陆页面
            WeiWuJSBridge.invoke('getRequestSigin', '{\"appKey\":\"' + appKey + '\", \"appSecret\":\"' + appSecret + '\"}', 'toLogin');
            return;
        }
        userName = res.data.userName;
        userHeadPic = res.data.avatarUrl;

        //alert("userName:"+userName);
        //alert("res.data.userName:"+res.data.userName);

        var content = $('#content').val();
        var day = new Date();
        var time = dateFormat(day);

        //更新服务器数据
        var NewsFollow = ProtoBuf.loadProtoFile('./protobuf/NewsFollow.proto').build('NewsFollow');
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
            type: 'post',
            url: 'http://' + host + '/news/createNewsFollow',
            dataType: 'json',
            async: false,
            contentType: 'application/octet-stream',
            data: nf.toBase64(),
            success: function (data) {
                if (data.head.code == 1) {

                    alert('评论成功。');
                    //2015-12-12 10:12:12
                    var followId = data.body.data;

                    $('#pl_list').prepend('<div class="pl_item" id="' + followId + '"><img class="pl_item_img" src="' + userHeadPic + '"/><div class="pl_item_nickname">' + userName + '</div><div class="pl_item_time">' + time.substring(11,16) + '</div><div class="pl_item_content">' + content + '</div><div class="item_end"><span class="item_like" onclick="like(this)"><i class="large thumbs outline up icon"></i><span>0</span></span><span class="item_unlike" onclick="unlike(this)"><i class="large thumbs outline down icon"></i><span>0</span></span></div><div class="ui divider"></div></div>');

                    $('#content').val('');
                    location.hash='#'+followId;
                } else {
                    alert('操作失败，请检查网络状态');
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert('操作失败，请检查网络状态！');
            }
        });

    } else {
        //跳转到登陆页面
        WeiWuJSBridge.invoke('getRequestSigin', '{\"appKey\":\"' + appKey + '\", \"appSecret\":\"' + appSecret + '\"}', 'toLogin');
    }
}

function toLogin(res) {
}