define(["jquery", "Backbone", "underscore", "storageapi"], function (i, a, t) {

    //当前楼盘
    var presentG = null;

    $storage = i.localStorage;
    var g, r, u = {}, N = {cityId: "273", cityName: "昆明"};
    var city_id = p_city_id;
    var city_name = u[city_id];
    var flag = n("flag") == "undefined" ? 0 : n("flag") == false ? 0 : n("flag");
    localStorage.city_id = city_id;
    //切换城市
    //获取楼盘过滤条件
    var filter = getFilters(city_id);
    if (filter != null && filter != "undefined") {
        //区域
        var districtList = filter.lstDistrict;
        for (var j = 0; j < districtList.length; j++) {
            $("#districts-div ul").append('<li data-toggle="districts-select" class="sub-item" data-value="' + districtList[j].districtId + '">' + districtList[j].name + '</li>');
        }
        //户型
        var houseTypeList = filter.houseTypes;
        for (var j = 0; j < houseTypeList.length; j++) {
            $("#housetype-div ul").append('<li data-toggle="housetype-select" class="sub-item" data-value="' + houseTypeList[j].Id + '">' + houseTypeList[j].Name + '</li>');
        }
        //价格
        var priceRangeList = filter.priceRanges;
        for (var j = 0; j < priceRangeList.length; j++) {
            $("#price-div ul").append('<li data-toggle="price-select" class="sub-item" data-value="' + priceRangeList[j].Id + '">' + priceRangeList[j].MinPrice + ' - ' + priceRangeList[j].MaxPrice + '</li>');
        }
        //标签
        var markList = filter.lstMark;
        for (var j = 0; j < markList.length; j++) {
            $("#mark-div ul").append('<li data-toggle="mark-select" class="sub-item" data-value="' + markList[j].Id + '">' + markList[j].markName + '</li>');
        }
    }

    //楼盘列表
    var gardenList = getGardenList(city_id, p_page_size, p_page_index);
    if (gardenList != null && gardenList != "undefined" && gardenList.length > 0) {
        for (var j = 0; j < gardenList.length; j++) {
            $("#gardenList-div ul").append('<li class="list-item section-loupan" id="' + gardenList[j].gardenId + '"><img src="' + gardenList[j].gardenImg + '"class="layout-img"><div class="right"><div class="price">' + gardenList[j].startPrice + '万起</div><div class="text-1">' + gardenList[j].gardenName + '</div><div><span class="text-2">' + gardenList[j].gardenType + '</span><span style="float: right"class="text-price">' + gardenList[j].gardenPrice + '元/平方米</span></div><div class="marks"></div> </div><div class="clearfix"></div></li>');
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

        var pre = window.document.getElementById(gid);
        if(pre!="undefined" && pre!=null){
            //跳转到指定锚点
            $("html,body").animate({scrollTop:$(pre).offset().top-100},1000)
            $(pre).addClass("list-select");
            presentG = $(pre);
        }

        $("#gardenList-div ul li").click(function(){
            var id = $(this).attr("id");
            var detail = window.parent.document.getElementById("detail");
            $(detail).attr("src","detail.html?id="+id+"&device=ipad");
            $(presentG).removeClass("list-select");
            $(this).addClass("list-select");
            presentG = $(this);
        });


    }

    function cc(i, a, t) {
        var c;
        if (t) {
            var y = new Date;
            y.setTime(y.getTime() + 24 * t * 60 * 60 * 1e3), c = "; expires=" + y.toGMTString()
        } else c = "";
        var n;
        if (!/^[\d\.]+$/.test(location.host)) {
            var m = location.host.split(".");
            m.length > 2 && (n = "domain=." + m.slice(m.length - 2).join("."))
        }
        document.cookie = encodeURIComponent(i) + "=" + encodeURIComponent(a) + c + ";path=/" + (n ? ";" + n : "")
    }
    function c(i, a, t) {
        var c;
        if (t) {
            var y = new Date;
            y.setTime(y.getTime() + 24 * t * 60 * 60 * 1e3), c = "; expires=" + y.toGMTString()
        } else c = "";
        var n;
        if (!/^[\d\.]+$/.test(location.host)) {
            var m = location.host.split(".");
            m.length > 2 && (n = "domain=." + m.slice(m.length - 2).join("."))
        }
        document.cookie = encodeURIComponent(i) + "=" + encodeURIComponent(a) + c + ";path=/" + (n ? ";" + n : "")
    }

    function y(i) {
        for (var a = encodeURIComponent(i) + "=", t = document.cookie.split(";"), c = 0; c < t.length; c++) {
            for (var y = t[c]; " " === y.charAt(0);)y = y.substring(1, y.length);
            if (0 === y.indexOf(a))
                return decodeURIComponent(y.substring(a.length, y.length));
            //return y.substring(a.length, y.length);

        }
        return null;
    }

    function n(i) {

        var a = y(i);
        //return null != a ? (a = a, $storage.remove(i), $storage.set(i, a), a) : $storage.isSet(i) ? $storage.get(i) : !1
        return null != a ? (a = decodeURIComponent(a), $storage.remove(i), $storage.set(i, a), a) : $storage.isSet(i) ? $storage.get(i) : !1
    }

    function m(i) {
        c("city_id", i.city_id, 365), null != i.city_name && c("city_name", i.city_name, 365),c("flag", 1, 1), $storage.removeAll(), $storage.set(i)
    }

    function e(a, t, b) {

        function mm(i) {
            c("city_id", i.city_id, 365), null != i.city_name && c("city_name", i.city_name, 365),c("flag", 1, 1),  $storage.removeAll(), $storage.set(i)
        }

        function y(y) {
            var n = y.coords;

            i.ajax({
                url: "http://api.map.baidu.com/geocoder/v2/",
                data: {
                    ak: "A6NoSO9Uz4bHLz2erZw3hNGo",
                    location: n.latitude + "," + n.longitude,
                    output: "json",
                    pois: 1
                },
                dataType: "jsonp",
                async:false
            }).done(function (i) {
                var t = i.result.addressComponent.city;
                var cid = u[t];
                t = t.substr(0, t.length - 1);

                //
                if (flag==0 && cid!=city_id && window.confirm("你当前城市是在" + t + "，需要切换城市吗？")) {
                    mm({city_id: cid, city_name: t,flag:1})
                    window.location.href = window.location;
                }


                return b && !cid ? void alert("该城市暂未开通，敬请期待。") : void ("function" == typeof a && a({
                    cityId: cid,
                    cityName: t
                }));

            }).fail(function () {
                "function" == typeof t && t()

            }).always(function () {
                i(".loading").hide()
            }), console.log(y), console.log("Your current position is:"), console.log("Latitude : " + n.latitude), console.log("Longitude: " + n.longitude), console.log("More or less " + n.accuracy + " meters.")
        }

        var m = {enableHighAccuracy: true, timeout: 3000, maximumAge: 0};
        navigator.geolocation.getCurrentPosition(y, n, m)
    }

    function o() {
        i("[data-city-id-href]").each(function (a, t) {
            var c = i(t), y = c.data("cityIdHref").replace("{cityId}", n("city_id"));
            c.attr("href", y)
        })
    }

    function getCityList() {
        var city_list = null;
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/getCityList",
            dataType: "json",
            async: false,
            success: function (data) {
                city_list = data.body.data;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
        return city_list;
    }

    function p(i) {
        var a = {
            code: "00000",
            msg: null,
            data: getCityList()
        };
        "function" == typeof i && i(a)
    }

    function d() {
        var i = window.location, a = i.origin + i.pathname;
        window.location.href = a
    }

    //获取城市楼盘列表
    function getGardenList(city_id, page_size, page_index) {
        var gardenList = [];
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/getGardenList?city_id=" + city_id + "&page_size=" + page_size + "&page_index=" + page_index,
            dataType: "json",
            async: false,
            success: function (data) {
                gardenList = data.body.data;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
        return gardenList;
    }

    //获取楼盘过滤条件
    function getFilters(city_id) {
        var filters = null;
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/getFilters?city_id=" + city_id,
            dataType: "json",
            async: false,
            success: function (data) {
                filters = data.body.data;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
        return filters;
    }


    i(function () {

        i(document).on("click.city.data-api", '[data-toggle^="city"]', function (a) {
            a.preventDefault(), i(".wrapper-city").removeClass("hide").addClass("show"), i(document.body).addClass("modal-open")
        }), i(document).on("click.city-close.data-api", '[data-toggle^="city-close"]', function (a) {
            a.preventDefault(), i(".wrapper-city").removeClass("show").addClass("hide"), i(document.body).removeClass("modal-open")
        }), i(document).on("click.city-select.data-api", '[data-toggle^="city-select"]', function (a) {
            a.preventDefault(), i(".wrapper-city").removeClass("show").addClass("hide"), i(document.body).removeClass("modal-open"), i(".loading").show(), i(this).data("cityId") &&(m({
                city_id: i(this).data("cityId"),
                city_name: i(this).data("cityName"),
                flag:1
            }), d())
        }), i(document).on("click.letter-select.data-api", '[data-toggle^="letter-select"]', function (a) {
            a.preventDefault();
            var t = i(this).data("letter"), c = i("#alpha_" + t).offset().top - i(".wrapper-city-fixed .section").offset().top, y = i(".wrapper-city-fixed").scrollTop();
            i(".wrapper-city-fixed").animate({scrollTop: c}, Math.abs(y - c) / 2)
        }), i(document).on("click.city-refresh.data-api", '[data-toggle^="city-refresh"]', function (a) {
            a.preventDefault(), i(".loading").show(), e(function (i) {

                window.confirm("你当前城市是在" + i.cityName + "，需要切换城市吗？") && (m({
                    city_id: i.cityId,
                    city_name: i.cityName,
                    flag:1
                }), d())
            }, function () {
                g.set("currentCity", {cityId: "273", cityName: "昆明"}), alert("定位失败");
            }, !0)
        }), i("#now_city").text(city_name).removeClass("fade").attr("data-city-id", city_id), p(function (c) {

            function y(i) {
                return i ? i.charAt(0).toLowerCase() : ""
            }

            for (var p = c.data, d = {}, h = 97; 122 >= h; h++)d[String.fromCharCode(h)] = [];
            for (var h = 0, L = p.length; L > h; h++) {
                var P = y(p[h].cityNamePinyin);
                d[P].push(p[h]), u[p[h].cityName] = p[h].cityId
            }
            for (var h in d)d[h].length || delete d[h];

            var s = a.Model.extend({defaults: {currentCity: N, alphaMap: d}}), l = a.View.extend({
                tagName: "div",
                className: "wrapper-city hide",
                template: t.template(i("#city-selection-tpl").html()),
                events: {"click .btn-dismiss": "close", "click .btn-share": "share"},
                initialize: function () {
                    t.bindAll(this, "render"), this.listenTo(this.model, "change", this.render)
                },
                render: function () {
                    return this.$el.html(this.template(this.model.toJSON())), this
                },
                close: function () {
                    this.$el.hide()
                }
            });
            g = new s, r = new l({model: g}), i(document.body).append(r.render().el), n("city_name") && n("city_id") ? (o(), e(function (i) {
                g.set("currentCity", {cityId: i.cityId, cityName: i.cityName});

            }, function () {
                g.set("currentCity", {cityId: "273", cityName: "昆明"})
            })) : e(function (i) {

                m({city_id: i.cityId, city_name: i.cityName,flag:1}), o()

            })
        })
    })
});