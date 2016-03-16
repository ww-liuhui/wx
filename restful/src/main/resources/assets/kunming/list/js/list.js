var city_id = 273;

var districtId = 0;
var houseTypeId = 0;
var priceRangeId = 0;
var markId = 0;
var pageIndex = 0;
var pageSize = 20;
var isfirst = true;
var islast = false;
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
var params = getUrlVars();
var keyword = decodeURI(params["keyword"]);
if(keyword!=null&&keyword!="undefined"&&keyword.length>0){
    $("#keyword").val(keyword);
    if (keyword != null && keyword != "undefined") {
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/getGardenListByKey?city_id=" + city_id + "&key=" + keyword + "&page_size=" + 20 + "&page_index=" + 0,
            dataType: "json",
            async: true,
            success: function (data) {
                if (data.head.code == 1) {
                    var filterList = data.body.data;
                    refereshList(filterList)
                } else {
                    alert(data.head.message);
                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("操作失败，请检查网络状态！");
            }
        });
    }
}else{
    //获取楼盘列表
    var gardenList = getGardenList(city_id, 20, 0);
    if (gardenList != null && gardenList != "undefined" && gardenList.length > 0) {
        //$("#gardenNum").html(gardenList.length);
        for (var j = 0; j < gardenList.length; j++) {
            var markHtml = "";
            var marks = gardenList[j].gardenMark;
            if (marks != null && marks != "undefind") {
                for (var k = 0; k < (marks.length > 3 ? 3 : marks.length); k++) {
                    if (k == 0) {
                        markHtml = markHtml+'<span class="gr">' + marks[k] + '</span>';
                    } else if (k == 1) {
                        markHtml = markHtml+'<span class="or">' + marks[k] + '</span>';
                    } else if (k == 2) {
                        markHtml = markHtml+'<span class="bl">' + marks[k] + '</span>';
                    } else if (k == 3) {
                        markHtml = markHtml+'<span class="pp">' + marks[k] + '</span>';
                    }
                }
            }
            var hotgarden = "";
            if(j<10){
                hotgarden = '<span class="ground">HOT</span>';
            }
            $("#gardenlist").append('<li><a class="detail-link" title="' + gardenList[j].gardenName + '" target="_blank" href="detail.html?id=' + gardenList[j].gardenId + '" ><div class="hsimg"><img width="200" height="150" src="' + gardenList[j].gardenImg + '" alt="' + gardenList[j].gardenName + '"></div><dl class="detail"><dt><div class="hsname">' + gardenList[j].gardenName + hotgarden +'</div></dt><dd class="clearfix"><div class="lf"><p>' + gardenList[j].gardenType + '</p><p></p></div><div class="fr"><span class="price">' + gardenList[j].gardenPrice + ' </span>元/平米<br><br><span class="price">' + gardenList[j].startPrice + ' </span>万起</div></dd><dd class="clearfix"></dd><dd class="tag">'+markHtml+'</dd></dl></a></li>');

        }
    }
}

//获取楼盘过滤条件
var filter = getFilters(city_id);
if (filter != null && filter != "undefined") {
    //区域
    var districtList = filter.lstDistrict;
    for (var j = 0; j < districtList.length; j++) {
        if(j==0){
            $("#districtList").append('<a class="choose" value-id="' + districtList[j].districtId + '">' + districtList[j].name + '</a>');
        }else{
            $("#districtList").append('<a value-id="' + districtList[j].districtId + '">' + districtList[j].name + '</a>');
        }
    }
    //户型
    var houseTypeList = filter.houseTypes;
    for (var j = 0; j < houseTypeList.length; j++) {
        if(j==0){
            $("#houseTypeList").append('<a class="choose" value-id="' + houseTypeList[j].Id + '">' + houseTypeList[j].Name + '</li>');
        }else{
            $("#houseTypeList").append('<a value-id="' + houseTypeList[j].Id + '">' + houseTypeList[j].Name + '</li>');
        }
    }
    //价格
    var priceRangeList = filter.priceRanges;
    for (var j = 0; j < priceRangeList.length; j++) {
        if(j==0){
            $("#priceRangeList").append('<a class="choose" value-id="' + priceRangeList[j].Id + '">' + priceRangeList[j].MinPrice + ' - ' + priceRangeList[j].MaxPrice + '</a>');
        }else{
            $("#priceRangeList").append('<a value-id="' + priceRangeList[j].Id + '">' + priceRangeList[j].MinPrice + ' - ' + priceRangeList[j].MaxPrice + '</a>');
        }

    }
    //标签
    var markList = filter.lstMark;
    for (var j = 0; j < markList.length; j++) {
        if(j==0){
            $("#markList").append('<a class="choose" value-id="' + markList[j].Id + '">' + markList[j].markName + '</a>');
        }else{
            $("#markList").append('<a value-id="' + markList[j].Id + '">' + markList[j].markName + '</a>');
        }
    }

    //添加点击事件

    $("#districtList a").click(function(){
        $("#districtList a").removeClass("choose");
        $(this).addClass("choose");
        districtId = $(this).attr("value-id");
        //加载数据
        getFilterGardenList(city_id, 20, 0, houseTypeId, priceRangeId, districtId, markId);
    });
    $("#houseTypeList a").click(function(){
        $("#houseTypeList a").removeClass("choose");
        $(this).addClass("choose");
        houseTypeId = $(this).attr("value-id");
        //加载数据
        getFilterGardenList(city_id, 20, 0, houseTypeId, priceRangeId, districtId, markId);
    });
    $("#priceRangeList a").click(function(){
        $("#priceRangeList a").removeClass("choose");
        $(this).addClass("choose");
        priceRangeId = $(this).attr("value-id");
        //加载数据
        getFilterGardenList(city_id, 20, 0, houseTypeId, priceRangeId, districtId, markId);
    });
    $("#markList a").click(function(){
        $("#markList a").removeClass("choose");
        $(this).addClass("choose");
        markId = $(this).attr("value-id");
        //加载数据
        getFilterGardenList(city_id, 20, 0, houseTypeId, priceRangeId, districtId, markId);
    });
}
//刷新楼盘列表
function refereshList(filterList){
    $("#gardenlist").html("");
    //var filterList = getFilterGardenList(city_id, 20, 0, houseTypeId, priceRangeId, districtId, markId);
    if (filterList != null && filterList != "undefined" && filterList.length > 0) {
        for (var j = 0; j < filterList.length; j++) {
            var markHtml = "";
            var marks = filterList[j].gardenMark;
            if (marks != null && marks != "undefind") {
                for (var k = 0; k < (marks.length > 3 ? 3 : marks.length); k++) {
                    if (k == 0) {
                        markHtml = markHtml+'<span class="gr">' + marks[k] + '</span>';
                    } else if (k == 1) {
                        markHtml = markHtml+'<span class="or">' + marks[k] + '</span>';
                    } else if (k == 2) {
                        markHtml = markHtml+'<span class="bl">' + marks[k] + '</span>';
                    } else if (k == 3) {
                        markHtml = markHtml+'<span class="pp">' + marks[k] + '</span>';
                    }
                }
            }
            var hotgarden = "";
            if(j<10){
                hotgarden = '<span class="ground">热</span>';
            }
            $("#gardenlist").append('<li><a class="detail-link" title="' + filterList[j].gardenName + '" target="_blank" href="detail.html?id=' + filterList[j].gardenId + '" ><div class="hsimg"><img width="200" height="150" src="' + filterList[j].gardenImg + '" alt="' + filterList[j].gardenName + '"></div><dl class="detail"><dt><div class="hsname">' + filterList[j].gardenName + hotgarden +'</div></dt><dd class="clearfix"><div class="lf"><p>' + filterList[j].gardenType + '</p><p></p></div><div class="fr"><span class="price">' + filterList[j].gardenPrice + ' </span>元/平米<br><br><span class="price">' + filterList[j].startPrice + ' </span>万起</div></dd><dd class="clearfix"></dd><dd class="tag">'+markHtml+'</dd></dl></a></li>');

        }
    }
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
//获取过滤后城市楼盘列表
function getFilterGardenList(city_id, page_size, page_index, type_id, range_id, district_id, mark_id) {
    var filterGardenList = [];
    $.ajax({
        type: "get",
        url: "http://" + host + "/garden/getFilterGardenList?city_id=" + city_id + "&page_size=" + page_size + "&page_index=" + page_index + "&type_id=" + type_id + "&range_id=" + range_id + "&district_id=" + district_id + "&mark_id=" + mark_id,
        dataType: "json",
        async: true,
        success: function (data) {
            filterGardenList = data.body.data;
            if(filterGardenList.length<pageSize){
                $("#next").hide();
            }
            refereshList(filterGardenList);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("操作失败，请检查网络状态！");
        }
    });
    //return filterGardenList;
}

//搜索楼盘
$("#search").click(function () {
    var keyword = $("#keyword").val();
    if (keyword != null && keyword != "undefined") {
        $.ajax({
            type: "get",
            url: "http://" + host + "/garden/getGardenListByKey?city_id=" + city_id + "&key=" + keyword + "&page_size=" + 20 + "&page_index=" + 0,
            dataType: "json",
            async: true,
            success: function (data) {
                if (data.head.code == 1) {
                    var filterList = data.body.data;
                    refereshList(filterList)
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

$("#previous").click(function(){
    //if(!isfirst){
        pageIndex = pageIndex-1;
        if(pageIndex==0){
            $("#previous").hide();
        }
        getFilterGardenList(city_id, pageSize, pageIndex, houseTypeId, priceRangeId, districtId, markId);
        window.location.href = "#list-main";
    //}
});
$("#next").click(function(){
    //if(!islast){
        pageIndex = pageIndex+1;
        if(pageIndex>0){
            $("#previous").show();
        }
        getFilterGardenList(city_id, pageSize, pageIndex, houseTypeId, priceRangeId, districtId, markId);
        window.location.href = "#list-main";
    //}
});