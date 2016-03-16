require(["jquery", "jquery.blueimp-gallery", "slick"], function (e) {

    //菜单切换
    var show = $("#detail-menu-1");
    var active_menu = $("#detail-menu");
    $("#menu .ht-column").click(function () {
        var id = $(this).attr("id");
        $(active_menu).removeClass("active");
        $(this).addClass("active");
        active_menu = $(this);
        $(show).hide();
        $("#" + id + "-1").show();
        show = $("#" + id + "-1");
        if(id == "comment-menu"){     //评论
            $("#comment_list").html("");
            var houseComment = getHouseComment(ht_id,20,0);
            if (houseComment != null && houseComment != "undefined" && houseComment.length > 0) {
                for (var j = 0; j < houseComment.length; j++) {
                    $("#comment_list").append('<li class="list-item section-loupan"><div class="item-link"><div class="info" style="margin-left: 4px;line-height: 2;width: 100%;"><img src="'+houseComment[j].memberAvatar+'" class="img-head" style="margin-right: 10px;margin-top: 6px;"><div class="name">'+houseComment[j].memberName+'<span class="comment-time">'+houseComment[j].commentTime+'</span></div><div class="comment"><span>'+houseComment[j].comment+'</span></div></div><div class="clearfix"></div></div></li>');
                }
            } else {
                $("#comment_list").append('暂无评论。');
            }
        }
    });
    //获取地址参数
    function getUrlVars(){
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?')+1).split('&');
        for(var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }
    //替换字符串
    function replaceB(str,oldstr,newstr){
        while(str.indexOf(oldstr)>=0){
            str = str.replace(oldstr,newstr);
        }
        return str;
    }
    //根据户型ID获取户型详情
    function getHouseTypeDetail(id) {
        var houseTypeDetail = null;
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/getHouseTypeDetail?id=" + id,
            dataType: "json",
            async: false,
            success: function (data) {
                houseTypeDetail = data.body.data;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
        return houseTypeDetail;
    }
    //根据户型ID获取户型评论
    function getHouseComment(id,page_size,page_index) {
        var gardenComment = null;
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/getHouseComment?id="+id+"&page_size="+page_size+"&page_index="+page_index,
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

    //获取参数
    var params = getUrlVars();
    var ht_id = params["id"];
    var device = params["device"];
    var gname = decodeURI(params["name"]);

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

    var houseTypeDetail = getHouseTypeDetail(ht_id);
    //轮播图片
    var imglist = houseTypeDetail.lstImage;
    if (imglist != null && imglist != "undefined" && imglist.length > 0) {
        for (var j = 0; j < imglist.length; j++) {
            var remoteUrl = null;
            var localUrl = null;

            remoteUrl = imglist[j].remoteUrl;
            localUrl = imglist[j].localUrl;
            $("#img_list").append('<a href="' + localUrl + '" data-gallery="galley-img" class="galley-div"><img src="' + remoteUrl + '" class="img-responsive"></a>');
        }
    } else {
        $("#img_list").append('<a href="img/garden_default_large@2x.png" data-gallery="galley-img" class="galley-div"><img src="img/garden_default_large@2x.png" class="img-responsive"></a>');
    }
    $("#symbol").text(houseTypeDetail.houseInfo.symbol);
    $("#info").html(replaceB(houseTypeDetail.houseInfo.info,"\n","<br>"));
    $("#housePrice").text(houseTypeDetail.houseInfo.price);
    $("#preferential").text(houseTypeDetail.houseInfo.preferential);
    $("#gname").text(gname);
    $("#ht_name").text(houseTypeDetail.houseInfo.htName);
    $("#ht_price").text(houseTypeDetail.houseInfo.price+"万起");
    $("#ht_inSalesVolume").text(houseTypeDetail.houseInfo.inSalesVolume);
    $("#ht_area").text(houseTypeDetail.houseInfo.area);
    $("#ht_toward").text(houseTypeDetail.houseInfo.toward);
    $("#ht_layerHigh").text(houseTypeDetail.houseInfo.layerHigh);
    $("#ht_ladderHousehold").text(houseTypeDetail.houseInfo.ladderHousehold);
    $("#ht_pm1").text(houseTypeDetail.houseInfo.downPayment.length>0?("商贷--"+houseTypeDetail.houseInfo.downPayment):"");
    $("#ht_pm2").text(houseTypeDetail.houseInfo.downPayment2.length>0?("  /  公积金贷--"+houseTypeDetail.houseInfo.downPayment2):"");
    //$("#ht_inSalesVolume").text(houseTypeDetail.houseInfo.inSalesVolume);
    //$("#ht_volume").text(houseTypeDetail.houseInfo.volume);
    //标签
    var mark_list = houseTypeDetail.houseInfo.houseMark;
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

    //特色详情
    var ht_hf_list = houseTypeDetail.lstFeature;
    if (ht_hf_list != null && ht_hf_list != "undefined" && ht_hf_list.length > 0) {
        $("#ht_hf_detail").show();
        for (var j = 0; j < ht_hf_list.length; j++) {
            $("#ht_hf_list").append('<li class="list-item section-loupan"><div class="ht-hf-item hf-detail-'+ht_hf_list[j].iconType+'"></div><div><div class="ht-hf-item-name">'+ht_hf_list[j].title+'</div><br/><br/><div class="ht-hf-item-detail">'+ht_hf_list[j].info+'</div></div><div class="clearfix"></div></li>');

        }
    }

    //户型
    var ht_list = houseTypeDetail.otherHouse;
    if (ht_list != null && ht_list != "undefined" && ht_list.length > 0) {
        for (var j = 0; j < ht_list.length; j++) {
            $("#ht_list").append('<li class="list-item section-loupan" id="' + ht_list[j].houseId + '"><a href="housetype.html?id=' + ht_list[j].houseId + '&name='+gname+'&device='+device+'" class="item-link"><img src="' + ht_list[j].imgUrl + '"class="layout-img"><div class="right"><div class="price">' + ht_list[j].price + '万起</div><div class="text-1">' + ht_list[j].symbol + '</div><div><span class="text-2" style="margin-right: 18px;">' + ht_list[j].htName + '</span><span class="text-2">' + ht_list[j].area + '</span></div><div class="marks"></div></div><div class="clearfix"></div></a></li>');
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
    //帮帮
    var borker_list = houseTypeDetail.lstGardenBroker;
    if (borker_list != null && borker_list != "undefined" && borker_list.length > 0) {
        //web IM地址
        var imurl = "setmobile.html?gid="+houseTypeDetail.houseInfo.gardenId+"&gname="+gname+"&pic="+houseTypeDetail.houseInfo.imgUrl;
        for (var j = 0; j < borker_list.length; j++) {
            var avatarUrl = borker_list[j].avatarUrl;
            if (avatarUrl == null || avatarUrl == "undefined" || avatarUrl.length == 0) {
                avatarUrl = "img/logo.png";
            }
            imurl = imurl+"&bcode="+borker_list[j].code;
            var praiseRate = Math.round(borker_list[j].praiseRate * 10000)/100;
            if (praiseRate < 10) {
                praiseRate = "没达到评分标准";
                $("#broker_list").append('<li class="list-item section-loupan"><a href='+imurl+' class="item-link" style="float:left;"><img src="' + avatarUrl + '"class="img-head"><div class="info"><div class="name">' + borker_list[j].name + '</div><div class="rate-red"><span>好评率：</span><span>' + praiseRate + ' </span></div></div><div class="clearfix"></div></a><a style="float: right;margin-right: 6px" href="tel:' + borker_list[j].mobile + '"><img src="img/icon_telephone.png" class="img-phone"></a></li>');
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
            title : '为屋-'+gname+'-'+houseTypeDetail.houseInfo.symbol+'-'+houseTypeDetail.houseInfo.htName+"-"+houseTypeDetail.houseInfo.area+"平米-"+houseTypeDetail.houseInfo.price+"万起",
            desc : '为屋更为你。',
            link : url,
            imgUrl : houseTypeDetail.houseInfo.imgUrl
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