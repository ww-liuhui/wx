require(["jquery", "jquery.blueimp-gallery", "slick"], function (e) {


    //菜单切换
    var show = $("#detail-menu-1");
    var active_menu = $("#detail-menu");
    $("#menu .column").click(function () {
        var id = $(this).attr("id");
        $(active_menu).removeClass("active");
        $(this).addClass("active");
        active_menu = $(this);
        $(show).hide();
        $("#" + id + "-1").show();
        show = $("#" + id + "-1");
        if(id == "around-menu"){    //周边
            // 百度地图API功能
            var map = new BMap.Map("allmap");
            var point = new BMap.Point(long, lat);
            map.centerAndZoom(point, 14);
            var marker = new BMap.Marker(point);  // 创建标注
            map.addOverlay(marker);               // 将标注添加到地图中
            marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        }else if(id == "comment-menu"){     //评论
            $("#comment_list").html("");
            var gardenComment = getGardenComment(gid,20,0);
            if (gardenComment != null && gardenComment != "undefined" && gardenComment.length > 0) {
                for (var j = 0; j < gardenComment.length; j++) {
                    $("#comment_list").append('<li class="list-item section-loupan"><div class="item-link"><div class="info" style="margin-left: 4px;line-height: 2;width: 100%;"><img src="'+gardenComment[j].memberAvatar+'" class="img-head" style="margin-right: 10px;margin-top: 6px;"><div class="name">'+gardenComment[j].memberName+'<span class="comment-time">'+gardenComment[j].commentTime+'</span></div><div class="comment"><span>'+gardenComment[j].comment+'</span></div></div><div class="clearfix"></div></div></li>');
                }
            } else {
                $("#comment_list").append('暂无评论。');
            }
        }
    });
    //获取地址参数
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)return unescape(r[2]);
        return null;
    }
    //替换字符串
    function replaceB(str,oldstr,newstr){
        while(str.indexOf(oldstr)>=0){
            str = str.replace(oldstr,newstr);
        }
        return str;
    }
    //根据ID获取楼盘详情
    function getGardenDetailById(id) {
        var gardenDetail = null;
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/getGardenDetail?id=" + id,
            dataType: "json",
            async: false,
            success: function (data) {
                gardenDetail = data.body.data;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
        return gardenDetail;
    }
    //根据楼盘ID获取楼盘评论
    function getGardenComment(id,page_size,page_index) {
        var gardenComment = null;
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/getGardenComment?id="+id+"&page_size="+page_size+"&page_index="+page_index,
            dataType: "json",
            async: false,
            success: function (data) {
                gardenComment = data.body.data;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
        return gardenComment;
    }
    //获取参数device：设备类型
    var device = getQueryString("device");

    var w = document.body.clientWidth;
    var h = 422/750*w - 10;

    //ipad
    if(device=="ipad"){
        $("#home").hide();
        $(".galley").height(h>300?300:h);
        $(".img-responsive").height(h>300?300:h);
    }else{
        $(".galley").height(h>182?182:h);
        $(".img-responsive").height(h>182?182:h);
        $("#home").show();
        $(".footer").show();
    }
    var imh = $(".img-menu").height();
    $("#place_height").height(imh);

    //获取参数ID
    var gid = getQueryString("id");
    var gardenDetail = getGardenDetailById(gid);
    //设置楼盘信息
    //$("#title").text("为屋-楼盘详情-" + gardenDetail.gardenInfo.gardenName);
    //经纬度
    var long = gardenDetail.gardenInfo.longitude;
    var lat = gardenDetail.gardenInfo.latitude;
    //街景
    var panorama = new BMap.Panorama('jiejing');
    panorama.setPosition(new BMap.Point(long,lat));
    var panoramaService = new BMap.PanoramaService();
    panoramaService.getPanoramaByLocation(new BMap.Point(long,lat), function(data){
        if (data == null) {
            console.log('no data');
            //alert("暂无街景");
            return;
        }
        $("#streetscape").attr("href","http://map.baidu.com/mobile/webapp/index/streetview/pid="+data.id+"&ss_panoType=street");
        $("#streetscape").attr("onclick","");
    });


    //轮播图片
    var imglist = gardenDetail.lstImage;
    if (imglist != null && imglist != "undefined" && imglist.length > 0) {
        for (var j = 0; j < imglist.length; j++) {
            var remoteUrl = null;
            var localUrl = null;

            remoteUrl = imglist[j].remoteUrl;
            localUrl = imglist[j].localUrl;

            $("#img_list").append('<a href="' + localUrl+'" data-gallery="galley-img" class="galley-div"><img src="' + remoteUrl + '" class="img-responsive"></a>');
        }
    } else {
        $("#img_list").append('<a href="img/garden_default_large@2x.png" data-gallery="galley-img" class="galley-div"><img src="img/garden_default_large@2x.png" class="img-responsive"></a>');
    }
    $("#garden_name").text(gardenDetail.gardenInfo.gardenName);
    $("#gardenPrice").text(gardenDetail.gardenInfo.gardenPrice);
    $("#preferential").text(gardenDetail.gardenInfo.preferential);
    $("#addr").text(gardenDetail.gardenInfo.gardenAddress);
    $("#map-addr").attr("href","http://api.map.baidu.com/marker?location="+lat+","+long+"&title="+gardenDetail.gardenInfo.gardenName+"&content="+gardenDetail.gardenInfo.gardenAddress+"&output=html");
    $("#gardenDeveloper").text(gardenDetail.gardenInfo.gardenDeveloper);
    $("#gardenOpenDate").text(gardenDetail.gardenInfo.gardenOpenDate);
    $("#gardenLeadDate").text(gardenDetail.gardenInfo.gardenLeadDate);
    $("#renovation").text(gardenDetail.gardenInfo.renovation);
    $("#propertyRightY").text(gardenDetail.gardenInfo.propertyRightY);
    $("#propertyType").text(gardenDetail.gardenInfo.propertyType);
    $("#propertyMoney").text(gardenDetail.gardenInfo.propertyMoney);
    $("#plotRatio").text(gardenDetail.gardenInfo.plotRatio);
    $("#greeningRate").text(gardenDetail.gardenInfo.greeningRate);
    $("#propertyComp").text(gardenDetail.gardenInfo.propertyComp);
    $("#introduction").html(replaceB(gardenDetail.gardenInfo.introduction,"\n","<br>"));
    //标签
    var mark_list = gardenDetail.gardenInfo.gardenMark;
    if (mark_list != null && mark_list != "undefined" && mark_list.length > 0) {
        for (var k = 0; k < (mark_list.length > 3 ? 3 : mark_list.length); k++) {

            if (k == 0) {
                $("#mark_list").append('<span class="btn mark-blue">' + mark_list[k] + '</span>');
            } else if (k == 1) {
                $("#mark_list").append('<span class="btn mark-green">' + mark_list[k] + '</span>');
            } else {
                $("#mark_list").append('<span class="btn mark-red">' + mark_list[k] + '</span>');
            }
        }
    }
    //户型
    var ht_list = gardenDetail.lstGardenHT;
    if (ht_list != null && ht_list != "undefined" && ht_list.length > 0) {
        for (var j = 0; j < ht_list.length; j++) {
            $("#ht_list").append('<li class="list-item section-loupan" id="' + ht_list[j].houseId + '"><a' +
                ' href="housetype.html?id=' + ht_list[j].houseId + '&name='+gardenDetail.gardenInfo.gardenName+'&device='+device+'" class="item-link"><img src="' + ht_list[j].htImgUrl + '"class="layout-img"><div class="right"><div class="price">' + ht_list[j].htPrice + '万起</div><div class="text-1">' + ht_list[j].symbol + '</div><div><span class="text-2" style="margin-right: 18px;">' + ht_list[j].htName + '</span><span class="text-2">' + ht_list[j].htArea + '</span></div><div class="marks"></div></div><div class="clearfix"></div></a></li>');
            var marks = ht_list[j].houseMark;
            if (marks != null && marks != "undefind" && marks.length > 0) {
                for (var k = 0; k < (marks.length > 3 ? 3 : marks.length); k++) {
                    if (k == 0) {
                        $("#" + ht_list[j].houseId + " .marks").append('<span class="btn mark-blue">' + marks[k] + '</span>');
                    } else if (k == 1) {
                        $("#" + ht_list[j].houseId + " .marks").append('<span class="btn mark-green">' + marks[k] + '</span>');
                    } else {
                        $("#" + ht_list[j].houseId + " .marks").append('<span class="btn mark-red">' + marks[k] + '</span>');
                    }
                }
            }

        }
    }

    //地图周边
    $("#travel").html(replaceB(gardenDetail.gardenInfo.travel,"\n","<br>"));
    $("#schoolSupport").html(replaceB(gardenDetail.gardenInfo.schoolSupport,"\n","<br>"));
    $("#lifeSupport").html(replaceB(gardenDetail.gardenInfo.lifeSupport,"\n","<br>"));

    //帮帮
    var borker_list = gardenDetail.lstGardenBroker;
    if (borker_list != null && borker_list != "undefined" && borker_list.length > 0) {
        //web IM地址
        var imurl = "setmobile.html?gid="+gid+"&gname="+gardenDetail.gardenInfo.gardenName+"&pic="+gardenDetail.gardenInfo.gardenImgUrl;
        for (var j = 0; j < borker_list.length; j++) {
            var avatarUrl = borker_list[j].avatarUrl;
            if (avatarUrl == null || avatarUrl == "undefined" || avatarUrl.length == 0) {
                avatarUrl = "img/logo.png";
            }
            imurl = imurl+"&bcode="+borker_list[j].code;
            var praiseRate = Math.round(borker_list[j].praiseRate * 10000)/100;
            if (praiseRate < 10) {
                praiseRate = "没达到评分标准";
                $("#broker_list").append('<li class="list-item section-loupan"><a href='+imurl+' class="item-link"' +
                    ' style="float:left;"><img src="' + avatarUrl + '"class="img-head"><div class="info"><div class="name">' + borker_list[j].name + '</div><div class="rate-red"><span>好评率：</span><span>' + praiseRate + ' </span></div></div><div class="clearfix"></div></a><a style="float: right;margin-right: 6px" href="tel:' + borker_list[j].mobile + '"><img src="img/icon_telephone.png" class="img-phone"></a></li>');
            } else if (praiseRate < 60) {
                $("#broker_list").append('<li class="list-item section-loupan"><a href='+imurl+' class="item-link" style="float:left;"><img src="' + avatarUrl + '"class="img-head"><div class="info"><div class="name">' + borker_list[j].name + '</div><div class="rate-red"><span>好评率：</span><span>' + praiseRate + '%  </span></div></div><div class="clearfix"></div></a><a style="float: right;margin-right: 6px" href="tel:' + borker_list[j].mobile + '"><img src="img/icon_telephone.png" class="img-phone"></a></li>');
            } else {
                $("#broker_list").append('<li class="list-item section-loupan"><a href='+imurl+' class="item-link" style="float:left;"><img src="' + avatarUrl + '"class="img-head"><div class="info"><div class="name">' + borker_list[j].name + '</div><div class="rate-green"><span>好评率：</span><span>' + praiseRate + '%  </span></div></div><div class="clearfix"></div></a><a style="float: right;margin-right: 6px" href="tel:' + borker_list[j].mobile + '"><img src="img/icon_telephone.png" class="img-phone"></a></li>');
            }
        }
    }

    var url = window.location.href;
    //获取微信js接口权限
    $.ajax({
        type: "get",
        url: "http://" + host + "/wxapi/validate",
        data:{
            url:url
        },
        dataType: "json",
        async: false,
        success: function (data) {
            if (data.head.code == 1) {
                var backStr = data.body.data;
                var arr = new Array();
                arr = backStr.split(",");
                wx.config({
                    debug : false,
                    appId : arr[0].trim(),
                    timestamp : arr[1].trim(),
                    nonceStr : arr[2].trim(),
                    signature : arr[3].trim(),
                    jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
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

    wx.ready(function() {
        var shareData = {
            title : '为屋帮帮-'+gardenDetail.gardenInfo.gardenName+"-"+gardenDetail.gardenInfo.gardenPrice+"元/平米",
            desc : '为屋更为你。',
            link : url,
            imgUrl : gardenDetail.gardenInfo.gardenImgUrl
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
            menuList : [ 'menuItem:copyUrl', 'menuItem:openWithSafari',
                'menuItem:openWithQQBrowse' ]
            // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮
        });

    });


    e(function () {
        e(".galley .galley-div").show(), e(".total_span").text(e(".galley .galley-div").length), e(".galley").slick({useCSS: !0}), e(".galley").on("beforeChange", function (a, t, n, r) {
            console.log(r + 1), e(".current_span").text(r + 1)
        });

    }), function () {

    }()
});