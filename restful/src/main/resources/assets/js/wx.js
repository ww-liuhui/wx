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
        link: shareUrl,
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