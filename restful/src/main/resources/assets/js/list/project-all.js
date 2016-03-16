require(["jquery", "Backbone", "underscore", "h5-city", "storageapi"], function (t, e, a) {

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
            title : '为屋-我要找房',
            desc : '为屋更为你。',
            link : url,
            imgUrl : 'http://' + host + '/wx/img/logo.png'
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

    var page_index = 0;
    var page_size = 50;

    //当前区域，户型，价位，标签
    var district = 0;
    var price = 0;
    var housetype = 0;
    var mark = 0;
    //滚动条在Y轴上的滚动距离
    function getScrollTop() {
        var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
        if (document.body) {
            bodyScrollTop = document.body.scrollTop;
        }
        if (document.documentElement) {
            documentScrollTop = document.documentElement.scrollTop;
        }
        scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
        return scrollTop;
    }

    //文档的总高度
    function getScrollHeight() {
        var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
        if (document.body) {
            bodyScrollHeight = document.body.scrollHeight;
        }
        if (document.documentElement) {
            documentScrollHeight = document.documentElement.scrollHeight;
        }
        scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
        return scrollHeight;
    }

    //浏览器视口的高度
    function getWindowHeight() {
        var windowHeight = 0;
        if (document.compatMode == "CSS1Compat") {
            windowHeight = document.documentElement.clientHeight;
        } else {
            windowHeight = document.body.clientHeight;
        }
        return windowHeight;
    }

    //监控浏览器是否滚动到底部
    //判断是否还有数据要加载
    var lastnum = page_size;
    window.onscroll = function () {
        if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
            if (lastnum == page_size) {
                page_index = page_index + 1;
                //加载数据
                var cityId = localStorage.city_id;
                var gardenList = getFilterGardenList(cityId, page_size, page_index, housetype == 0 ? 0 : housetype.data("value"), price == 0 ? 0 : price.data("value"), district == 0 ? 0 : district.data("value"), mark == 0 ? 0 : mark.data("value"));
                if (gardenList != null && gardenList != "undefined" && gardenList.length > 0) {
                    lastnum = gardenList.length;
                    for (var j = 0; j < gardenList.length; j++) {
                        $("#gardenList-div ul").append('<li class="list-item section-loupan" id="' + gardenList[j].gardenId + '"><a href="detail.html?id=' + gardenList[j].gardenId + '" class="item-link" target="_black"><img src="' + gardenList[j].gardenImg + '"class="layout-img"><div class="right"><div class="price">' + gardenList[j].startPrice + '万起</div><div class="text-1">' + gardenList[j].gardenName + '</div><div><span class="text-2">' + gardenList[j].gardenType + '</span><span style="float: right"class="text-price">' + gardenList[j].gardenPrice + '元/平米</span></div><div class="marks"></div> </div><div class="clearfix"></div></a></li>');
                        var marks = gardenList[j].gardenMark;
                        if (marks != null && marks != "undefind") {
                            for (var k = 0; k < (marks.length > 3 ? 3 : marks.length); k++) {
                                if (k == 0) {
                                    $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-blue">' + marks[k] + '</span>');
                                } else if (k == 1) {
                                    $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-green">' + marks[k] + '</span>');
                                } else {
                                    $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-red">' + marks[k] + '</span>');
                                }
                            }
                        }
                    }
                } else {
                    lastnum = 0;
                }
            }
        }
    };
    //搜索楼盘
    $("#search").click(function () {
        var cityId = localStorage.city_id;
        var keyword = $("#keyword").val();
        if (keyword != null && keyword != "undefined") {
            page_index = 0;

            $.ajax({
                type: "get",
                url: "http://" + host + "/garden/getGardenListByKey?city_id=" + cityId + "&key=" + keyword + "&page_size=" + page_size + "&page_index=" + page_index,
                dataType: "json",
                success: function (data) {
                    if (data.head.code == 1) {
                        var gardenList = data.body.data;
                        $("#gardenList-div ul").html("");
                        if (gardenList != null && gardenList != "undefined" && gardenList.length > 0) {
                            for (var j = 0; j < gardenList.length; j++) {
                                $("#gardenList-div ul").append('<li class="list-item section-loupan" id="' + gardenList[j].gardenId + '"><a href="detail.html?id=' + gardenList[j].gardenId + '" class="item-link" target="_black"><img src="' + gardenList[j].gardenImg + '"class="layout-img"><div class="right"><div class="price">' + gardenList[j].startPrice + '万起</div><div class="text-1">' + gardenList[j].gardenName + '</div><div><span class="text-2">' + gardenList[j].gardenType + '</span><span style="float: right"class="text-price">' + gardenList[j].gardenPrice + '元/平米</span></div><div class="marks"></div> </div><div class="clearfix"></div></a></li>');
                                var marks = gardenList[j].gardenMark;
                                if (marks != null && marks != "undefind") {
                                    for (var k = 0; k < (marks.length > 3 ? 3 : marks.length); k++) {
                                        if (k == 0) {
                                            $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-blue">' + marks[k] + '</span>');
                                        } else if (k == 1) {
                                            $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-green">' + marks[k] + '</span>');
                                        } else {
                                            $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-red">' + marks[k] + '</span>');
                                        }
                                    }
                                }
                            }
                        }

                    } else {
                        alert(data.head.message);
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("操作失败，请检查网络状态！");
                }
            });
        }
    });

    //获取城市楼盘过滤列表
    function getFilterGardenList(city_id, page_size, page_index, type_id, range_id, district_id, mark_id) {
        var filterGardenList = [];
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/getFilterGardenList?city_id=" + city_id + "&page_size=" + page_size + "&page_index=" + page_index + "&type_id=" + type_id + "&range_id=" + range_id + "&district_id=" + district_id + "&mark_id=" + mark_id,
            dataType: "json",
            async: false,
            success: function (data) {
                filterGardenList = data.body.data;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
        return filterGardenList;
    }

    function i(t, e, a) {
        var i = t + "=" + escape(e);
        if (a > 0) {
            var d = new Date, s = 3600 * a * 1e3;
            d.setTime(d.getTime() + s), i += "; expires=" + d.toGMTString() + ";path=/"
        }
        document.cookie = i
    }

    function d(t) {
        return $storage.isSet(t) ? $storage.get(t) : !1
    }

    function s(t) {
        t = t.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var e = new RegExp("[\\?&]" + t + "=([^&#]*)"), a = e.exec(location.search);
        return null === a ? "" : decodeURIComponent(a[1].replace(/\+/g, " "))
    }

    function o() {
        var t = {districts: 0, price: 0, housetype: 0, mark: 0, from: ""};
        return t.districts = s("districts"), t.price = s("price"), t.housetype = s("housetype"), t.mark = s("mark"), t.from = s("from"), t.districts || delete t.districts, t.price || delete t.price, t.housetype || delete t.housetype, t.mark || delete t.mark, t.from || delete t.from, t
    }

    function n(t, e) {
        return t ? (e ? u[t] = e : delete u[t], u) : void 0
    }

    function r() {
        var e = window.location, a = t.param(u), i = e.origin + e.pathname + (a.length ? "?" + a : "");
        window.location.href != i && (window.location.href = i)
    }

    function c() {
        t(".modal-backdrop").hide(), t(".arrow").removeClass("open"), t(".classboard").hide()
    }

    function l() {
        var e = u;
        // e.districts ? t("[data-toggle=districts-select][data-value=" + e.districts + "]").addClass("on") :
        // t("[data-toggle=districts-select][data-value=0]").addClass("on"),e.housetype ? t("[data-toggle=housetype-select][data-value=" + e.housetype + "]").addClass("on") : t("[data-toggle=housetype-select][data-value=0]").addClass("on"), e.price ? t("[data-toggle=price-select][data-value=" + e.price + "]").addClass("on") : t("[data-toggle=price-select][data-value=0]").addClass("on"), d("district_filter_name") && t("#district_name").text(d("district_filter_name")), t("[data-toggle=district-nav][data-id=" + (e.district_id || 0) + "]").addClass("on"), t("[data-toggle=mark-select][data-value=" + (e.mark || 0) + "]").addClass("on")
    }

    var u = o();
    t(function () {
        l(), t(document).on("click.loupan-select.data-api", '[data-toggle^="loupan-select"]', function (e) {
            e.preventDefault(), $storage.set({
                district_id: t(this).data("districtId"),
                section_id: t(this).data("id"),
                district_filter_name: t(this).data("name")
            }), n("district_id", t(this).data("districtId")), n("section_id", t(this).data("id")), c(), r()
        });
        var i = e.Model.extend({
            defaults: {
                subItems: [],
                selected_data: {district_id: d("district_id") || 0, section_id: d("section_id") || 0},
                current_data: {district_id: 0, district_name: ""}
            }
        }), s = e.View.extend({
            tagName: "div",
            className: "",
            template: a.template(t("#sub-item-list").html()),
            initialize: function () {
                a.bindAll(this, "render"), this.listenTo(this.model, "change", this.render)
            },
            render: function () {
                return this.$el.html(this.template(this.model.toJSON())), this
            },
            close: function () {
                this.$el.hide()
            }
        });
        $districtItem = new i, $districtView = new s({model: $districtItem}), t(".flex-right").append($districtView.render().el), function () {
            if (u.district_id) {
                var e = t("[data-toggle=district-nav][data-id=" + u.district_id + "]");
                $districtItem.set({
                    subItems: e.data("sub"),
                    current_data: {district_id: e.data("id"), district_name: e.data("name")}
                })
            }
        }(), t(document).on("click.districts-select.data-api", '[data-toggle^="districts-select"]', function (e) {
            //点击元素
            $(district).removeClass("on");
            e.preventDefault(), n("districts", t(this).data("value")), c()//, r()
            t(this).addClass("on");
            district = t(this);
            //更新数据：切换区域
            var cityId = localStorage.city_id;
            var gardenList = getFilterGardenList(cityId, page_size, 0, housetype == 0 ? 0 : housetype.data("value"), price == 0 ? 0 : price.data("value"), district == 0 ? 0 : district.data("value"), mark == 0 ? 0 : mark.data("value"));
            $("#gardenList-div ul").html("");
            if (gardenList != null && gardenList != "undefined" && gardenList.length > 0) {
                for (var j = 0; j < gardenList.length; j++) {
                    $("#gardenList-div ul").append('<li class="list-item section-loupan" id="' + gardenList[j].gardenId + '"><a href="detail.html?id=' + gardenList[j].gardenId + '" class="item-link" target="_black"><img src="' + gardenList[j].gardenImg + '"class="layout-img"><div class="right"><div class="price">' + gardenList[j].startPrice + '万起</div><div class="text-1">' + gardenList[j].gardenName + '</div><div><span class="text-2">' + gardenList[j].gardenType + '</span><span style="float: right"class="text-price">' + gardenList[j].gardenPrice + '元/平米</span></div><div class="marks"></div> </div><div class="clearfix"></div></a></li>');
                    var marks = gardenList[j].gardenMark;
                    if (marks != null && marks != "undefind") {
                        for (var k = 0; k < (marks.length > 3 ? 3 : marks.length); k++) {
                            if (k == 0) {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-blue">' + marks[k] + '</span>');
                            } else if (k == 1) {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-green">' + marks[k] + '</span>');
                            } else {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-red">' + marks[k] + '</span>');
                            }
                        }
                    }
                }
            }

        }), t(document).on("click.price-select.data-api", '[data-toggle^="price-select"]', function (e) {
            //点击元素
            $(price).removeClass("on");
            e.preventDefault(), n("price", t(this).data("value")), c()//, r()
            t(this).addClass("on");
            price = t(this);
            //更新数据：切换价位
            var cityId = localStorage.city_id;
            var gardenList = getFilterGardenList(cityId, page_size, 0, housetype == 0 ? 0 : housetype.data("value"), price == 0 ? 0 : price.data("value"), district == 0 ? 0 : district.data("value"), mark == 0 ? 0 : mark.data("value"));
            $("#gardenList-div ul").html("");
            if (gardenList != null && gardenList != "undefined" && gardenList.length > 0) {
                for (var j = 0; j < gardenList.length; j++) {
                    $("#gardenList-div ul").append('<li class="list-item section-loupan" id="' + gardenList[j].gardenId + '"><a href="detail.html?id=' + gardenList[j].gardenId + '" class="item-link" target="_black"><img src="' + gardenList[j].gardenImg + '"class="layout-img"><div class="right"><div class="price">' + gardenList[j].startPrice + '万起</div><div class="text-1">' + gardenList[j].gardenName + '</div><div><span class="text-2">' + gardenList[j].gardenType + '</span><span style="float: right"class="text-price">' + gardenList[j].gardenPrice + '元/平米</span></div><div class="marks"></div> </div><div class="clearfix"></div></a></li>');
                    var marks = gardenList[j].gardenMark;
                    if (marks != null && marks != "undefind") {
                        for (var k = 0; k < (marks.length > 3 ? 3 : marks.length); k++) {
                            if (k == 0) {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-blue">' + marks[k] + '</span>');
                            } else if (k == 1) {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-green">' + marks[k] + '</span>');
                            } else {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-red">' + marks[k] + '</span>');
                            }
                        }
                    }
                }
            }
        }), t(document).on("click.housetype-select.data-api", '[data-toggle^="housetype-select"]', function (e) {
            //点击元素
            $(housetype).removeClass("on");
            e.preventDefault(), n("housetype", t(this).data("value")), c()//, r()
            t(this).addClass("on");
            housetype = t(this);
            //更新数据：切换户型
            var cityId = localStorage.city_id;
            var gardenList = getFilterGardenList(cityId, page_size, 0, housetype == 0 ? 0 : housetype.data("value"), price == 0 ? 0 : price.data("value"), district == 0 ? 0 : district.data("value"), mark == 0 ? 0 : mark.data("value"));
            $("#gardenList-div ul").html("");
            if (gardenList != null && gardenList != "undefined" && gardenList.length > 0) {
                for (var j = 0; j < gardenList.length; j++) {
                    $("#gardenList-div ul").append('<li class="list-item section-loupan" id="' + gardenList[j].gardenId + '"><a href="detail.html?id=' + gardenList[j].gardenId + '" class="item-link" target="_black"><img src="' + gardenList[j].gardenImg + '"class="layout-img"><div class="right"><div class="price">' + gardenList[j].startPrice + '万起</div><div class="text-1">' + gardenList[j].gardenName + '</div><div><span class="text-2">' + gardenList[j].gardenType + '</span><span style="float: right"class="text-price">' + gardenList[j].gardenPrice + '元/平米</span></div><div class="marks"></div> </div><div class="clearfix"></div></a></li>');
                    var marks = gardenList[j].gardenMark;
                    if (marks != null && marks != "undefind") {
                        for (var k = 0; k < (marks.length > 3 ? 3 : marks.length); k++) {
                            if (k == 0) {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-blue">' + marks[k] + '</span>');
                            } else if (k == 1) {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-green">' + marks[k] + '</span>');
                            } else {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-red">' + marks[k] + '</span>');
                            }
                        }
                    }
                }
            }
        }), t(document).on("click.mark-select.data-api", '[data-toggle^="mark-select"]', function (e) {
            //点击元素
            $(mark).removeClass("on");
            e.preventDefault(), n("mark", t(this).data("value")), c()//, r()
            t(this).addClass("on");
            mark = t(this);
            //更新数据：切换标签
            var cityId = localStorage.city_id;
            var gardenList = getFilterGardenList(cityId, page_size, 0, housetype == 0 ? 0 : housetype.data("value"), price == 0 ? 0 : price.data("value"), district == 0 ? 0 : district.data("value"), mark == 0 ? 0 : mark.data("value"));
            $("#gardenList-div ul").html("");
            if (gardenList != null && gardenList != "undefined" && gardenList.length > 0) {
                for (var j = 0; j < gardenList.length; j++) {
                    $("#gardenList-div ul").append('<li class="list-item section-loupan" id="' + gardenList[j].gardenId + '"><a href="detail.html?id=' + gardenList[j].gardenId + '" class="item-link" target="_black"><img src="' + gardenList[j].gardenImg + '"class="layout-img"><div class="right"><div class="price">' + gardenList[j].startPrice + '万起</div><div class="text-1">' + gardenList[j].gardenName + '</div><div><span class="text-2">' + gardenList[j].gardenType + '</span><span style="float: right"class="text-price">' + gardenList[j].gardenPrice + '元/平米</span></div><div class="marks"></div> </div><div class="clearfix"></div></a></li>');
                    var marks = gardenList[j].gardenMark;
                    if (marks != null && marks != "undefind") {
                        for (var k = 0; k < (marks.length > 3 ? 3 : marks.length); k++) {
                            if (k == 0) {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-blue">' + marks[k] + '</span>');
                            } else if (k == 1) {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-green">' + marks[k] + '</span>');
                            } else {
                                $("#" + gardenList[j].gardenId + " .marks").append('<span class="btn mark-red">' + marks[k] + '</span>');
                            }
                        }
                    }
                }
            }
        }), t(".modal-backdrop").on("click", c), t(document).on("click.drop-down.data-api", '[data-toggle^="drop-down"]', function (e) {
            e.preventDefault();
            var a = t(this).data("type") + "-div", i = !t(this).hasClass("open"), d = this;
            t(".arrow").each(function (e, a) {
                t(a);
                a == d ? i ? (t(this).addClass("open"), t(".modal-backdrop").show()) : (t(this).removeClass("open"), t(".modal-backdrop").hide()) : t(this).removeClass("open")
            }), t(".classboard").each(function (e, d) {
                var s = t(d);
                s.attr("id") == a ? s.toggle(i) : s.hide()
            })
        }), t(document).on("click.list-more.data-api", '[data-toggle^="list-more"]', function (e) {
            e.preventDefault();
            var a = t(e.target);
            a.hasClass("expanded") ? (a.removeClass("expanded"), t('.list-item[data-more="true"]', a.parents(".list-main")).addClass("hide"), a.text(a.data("moreText"))) : (a.addClass("expanded"), t('.list-item[data-more="true"]', a.parents(".list-main")).removeClass("hide"), a.text(a.data("lessText")));
            var i = a.parents(".section").offset().top - 85, d = t("body").scrollTop();
            t("body").animate({scrollTop: i}, Math.abs(d - i) / 2)
        })
    })
});