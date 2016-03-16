var city_id = 273;
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
var gid = params["id"];
var keywords = decodeURI(params["keyword"]);
if(keywords!=null&&keywords!="undefined"&&keywords.length>0){
    $("#keyword").val(keywords);
}

var gardenDetail = getGardenDetailById(gid);
setVal(gardenDetail);
function setVal(gardenDetail){
    $("#keywords").attr("content",gardenDetail.gardenInfo.gardenName);
    $("#description").attr("content",gardenDetail.gardenInfo.introduction);
    $("#gname1").text(gardenDetail.gardenInfo.gardenName);
    $("#gname2").text(gardenDetail.gardenInfo.gardenName);
    //标签
    var mark_list = gardenDetail.gardenInfo.gardenMark;
    if (mark_list != null && mark_list != "undefined" && mark_list.length > 0) {
        for (var k = 0; k < mark_list.length; k++) {

            if (k == 0) {
                $("#mark_list").append('<span class="gr">' + mark_list[k] + '</span>');
            } else if (k == 1) {
                $("#mark_list").append('<span class="or">' + mark_list[k] + '</span>');
            } else if (k == 2) {
                $("#mark_list").append('<span class="bl">' + mark_list[k] + '</span>');
            }else{
                $("#mark_list").append('<span class="pp">' + mark_list[k] + '</span>');
            }
        }
    }
    $("#gardenPrice").text(gardenDetail.gardenInfo.gardenPrice);
    $("#preferential").text(gardenDetail.gardenInfo.preferential.length==0?"以实际成交为准！":gardenDetail.gardenInfo.preferential);
    $("#gardenOpenDate").text(gardenDetail.gardenInfo.gardenOpenDate);
    $("#gardenLeadDate").text(gardenDetail.gardenInfo.gardenLeadDate);
    $("#gardenAddress").text(gardenDetail.gardenInfo.gardenAddress);
    $("#phoneHost").text(gardenDetail.gardenInfo.phoneHost);
    $("#phoneExt").text(gardenDetail.gardenInfo.phoneExt);

    $("#gardenOpenDate2").text(gardenDetail.gardenInfo.gardenOpenDate);
    $("#gardenLeadDate2").text(gardenDetail.gardenInfo.gardenLeadDate);
    $("#gardenAddress2").text(gardenDetail.gardenInfo.gardenAddress);
    $("#gardenDeveloper").text(gardenDetail.gardenInfo.gardenDeveloper);

    $("#propertyType").text(gardenDetail.gardenInfo.propertyType);
    $("#propertyRightY").text(gardenDetail.gardenInfo.propertyRightY);
    $("#renovation").text(gardenDetail.gardenInfo.renovation);
    $("#buildArea").text(gardenDetail.gardenInfo.buildArea);
    $("#plotRatio").text(gardenDetail.gardenInfo.plotRatio);
    $("#planArea").text(gardenDetail.gardenInfo.planArea);
    $("#planHouse").text(gardenDetail.gardenInfo.planHouse);
    $("#carNum").text(gardenDetail.gardenInfo.carNum);
    $("#greeningRate").text(gardenDetail.gardenInfo.greeningRate);
    $("#propertyMoney").text(gardenDetail.gardenInfo.propertyMoney);
    $("#propertyComp").text(gardenDetail.gardenInfo.propertyComp);
    $("#introduction").html(replaceB(gardenDetail.gardenInfo.introduction,"\n","<br>"));

    var ht_list = gardenDetail.lstGardenHT;
    if (ht_list != null && ht_list != "undefined" && ht_list.length > 0) {
        for (var j = 0; j < ht_list.length; j++) {
            var markshtml = "";
            var marks = ht_list[j].houseMark;
            if (marks != null && marks != "undefind" && marks.length > 0) {
                for (var k = 0; k < (marks.length > 3 ? 3 : marks.length); k++) {
                    if (k == 0) {
                        markshtml = markshtml + '<span class="gr">' + marks[k] + '</span>';
                    } else if (k == 1) {
                        markshtml = markshtml + '<span class="or">' + marks[k] + '</span>';
                    } else if (k == 2)  {
                        markshtml = markshtml + '<span class="bl">' + marks[k] + '</span>';
                    }else{
                        markshtml = markshtml + '<span class="pp">' + marks[k] + '</span>';
                    }
                }
            }
            var saleStatusText = ht_list[j].saleStatus==0?"待售":ht_list[j].saleStatus==1?"在售":"售罄";
            if(j>4){
                $("#ht_list").append('<li data-id="' + ht_list[j].houseId + '" style="display:none;"><a class="type-img" href="javascript:;" event-track data-event-label="户型详情页"><img src="' + ht_list[j].htImgUrl + '" alt="' + ht_list[j].symbol + '"></a><div class="detail"><a class="name" href="htdetail.html?id=' + ht_list[j].houseId + '&gname=' + gardenDetail.gardenInfo.gardenName + '" target="_blank" event-track data-event-label="户型详情页">' + ht_list[j].symbol + ' | ' + ht_list[j].htName +' | '+ ht_list[j].htArea +'</a><span class="onsale' + ht_list[j].saleStatus + '">'+saleStatusText+'</span><div class="rcontent"><div class="total">总价：<span>' + ht_list[j].htPrice + '万起</span></div></div><div style="height: 24px;"></div><p class="virture">' + ht_list[j].htInfo + '</p><div style="height: 24px;"></div><div class="tag">'+markshtml+'</div></div></li>');
            }else{
                $("#ht_list").append('<li data-id="' + ht_list[j].houseId + '"><a class="type-img" href="javascript:;" event-track data-event-label="户型详情页"><img src="' + ht_list[j].htImgUrl + '" alt="' + ht_list[j].symbol + '"></a><div class="detail"><a class="name" href="htdetail.html?id=' + ht_list[j].houseId + '&gname=' + gardenDetail.gardenInfo.gardenName + '" target="_blank" event-track data-event-label="户型详情页">' + ht_list[j].symbol + ' | ' + ht_list[j].htName +' | '+ ht_list[j].htArea +'</a><span class="onsale' + ht_list[j].saleStatus + '">'+saleStatusText+'</span><div class="rcontent"><div class="total">总价：<span>' + ht_list[j].htPrice + '万起</span></div></div><div style="height: 24px;"></div><p class="virture">' + ht_list[j].htInfo + '</p><div style="height: 24px;"></div><div class="tag">'+markshtml+'</div></div></li>');
            }


        }
    }
//点击显示更多户型
    var isShow = 0;
    $("#houseTypeViewMore").click(function(){
        if(isShow == 0){
            $("#ht_list").children().show();
            $("#houseTypeViewMore").text("收起户型");
            isShow = 1;
        }else{
            var children = $("#ht_list").children();
            for(var i=0;i<children.length;i++){
                if(i>4){
                    $("#ht_list").children().eq(i).hide();
                }
            }
            $("#houseTypeViewMore").text("更多户型");
            isShow = 0;
        }
    });

    $("#travel").html(replaceB(gardenDetail.gardenInfo.travel,"\n","<br>"));
    $("#schoolSupport").html(replaceB(gardenDetail.gardenInfo.schoolSupport,"\n","<br>"));
    $("#lifeSupport").html(replaceB(gardenDetail.gardenInfo.lifeSupport,"\n","<br>"));

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




//轮播图片
var imglist = gardenDetail.lstImage;
if (imglist != null && imglist != "undefined" && imglist.length > 0) {
    for (var j = 0; j < imglist.length; j++) {
        var remoteUrl = null;
        var localUrl = null;

        remoteUrl = imglist[j].remoteUrl;
        localUrl = imglist[j].localUrl;

        $("#big_pic").append('<li><img src="' + remoteUrl + '" /></li>');
        if(j==0){
            $("#small_pic").append('<li style="filter: 100; opacity: 1;"><img src="' + localUrl + '" /></li>');
        }else{
            $("#small_pic").append('<li><img src="' + localUrl + '" /></li>');
        }

    }
} else {
    $("#big_pic").append('<li><img src="img/garden_default_large@2x.png" /></li>');
}

//轮播
function getStyle(obj, attr)
{
    if(obj.currentStyle)
    {
        return obj.currentStyle[attr];
    }
    else
    {
        return getComputedStyle(obj, false)[attr];
    }
}

function startMove(obj, attr, iTarget)
{
    clearInterval(obj.timer);
    obj.timer=setInterval(function (){
        var iCur=0;

        if(attr=='opacity')
        {
            iCur=parseInt(parseFloat(getStyle(obj, attr))*100);
        }
        else
        {
            iCur=parseInt(getStyle(obj, attr));
        }

        var iSpeed=(iTarget-iCur)/8;
        iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);

        if(iCur==iTarget)
        {
            clearInterval(obj.timer);
        }
        else
        {
            if(attr=='opacity')
            {
                obj.style.filter='alpha(opacity:'+(iCur+iSpeed)+')';
                obj.style.opacity=(iCur+iSpeed)/100;
            }
            else
            {
                obj.style[attr]=iCur+iSpeed+'px';
            }
        }
    }, 30)
}
function getByClass(oParent, sClass)
{
    var aEle=document.getElementsByTagName('*');
    var i=0;
    var aResult=[];

    for(i=0;i<aEle.length;i++)
    {
        if(aEle[i].className==sClass)
        {
            aResult.push(aEle[i]);
        }
    }

    return aResult;
}
//轮播图片
var oDiv=document.getElementById('playeimages');
var oBtnPrev=getByClass(oDiv, 'prev')[0];
var oBtnNext=getByClass(oDiv, 'next')[0];
var oMarkLeft=getByClass(oDiv, 'mark_left')[0];
var oMarkRight=getByClass(oDiv, 'mark_right')[0];

var oSmallUl=getByClass(oDiv, 'small_pic')[0].getElementsByTagName('ul')[0];
var aSmallLi=oSmallUl.getElementsByTagName('li');
var oBigUl=getByClass(oDiv, 'big_pic')[0];
var aBigLi=oBigUl.getElementsByTagName('li');
var iNow=0;
var iMinZindex=2;
var i=0;

oSmallUl.style.width=aSmallLi.length*aSmallLi[0].offsetWidth+'px';

//上面的左右按钮
oBtnPrev.onmouseover=oMarkLeft.onmouseover=function ()
{
    startMove(oBtnPrev, 'opacity', 100);
}

oBtnPrev.onmouseout=oMarkLeft.onmouseout=function ()
{
    startMove(oBtnPrev, 'opacity', 0);
}

oBtnNext.onmouseover=oMarkRight.onmouseover=function ()
{
    startMove(oBtnNext, 'opacity', 100);
}

oBtnNext.onmouseout=oMarkRight.onmouseout=function ()
{
    startMove(oBtnNext, 'opacity', 0);
}

//小图点击，大图显示
for(i=0;i<aSmallLi.length;i++)
{
    aSmallLi[i].index=i;
    aSmallLi[i].onmouseover=function ()
    {
        startMove(this, 'opacity', 100);
    }
    aSmallLi[i].onmouseout=function ()
    {
        if(this.index!=iNow)
        {
            startMove(this, 'opacity', 60);
        }
    }

    aSmallLi[i].onclick=function ()
    {
        if(this.index==iNow)return;
        iNow=this.index;

        tab();
    }

    function tab()
    {
        for(i=0;i<aSmallLi.length;i++)
        {
            startMove(aSmallLi[i], 'opacity', 60);
        }
        startMove(aSmallLi[iNow], 'opacity', 100);
        aBigLi[iNow].style.zIndex=iMinZindex++;
        aBigLi[iNow].style.height=0;

        startMove(aBigLi[iNow], 'height', oBigUl.offsetHeight);

        if(iNow==0)
        {
            startMove(oSmallUl, 'left', 0);
        }
        else if(iNow==aSmallLi.length-1)
        {
            startMove(oSmallUl, 'left', -(iNow-2)*aSmallLi[0].offsetWidth);
        }
        else
        {
            startMove(oSmallUl, 'left', -(iNow-1)*aSmallLi[0].offsetWidth);
        }
    }

    oBtnPrev.onclick=function ()
    {
        iNow--;
        if(iNow==-1)
        {
            iNow=aSmallLi.length-1;
        }

        tab();
    }

    oBtnNext.onclick=function ()
    {
        iNow++;
        if(iNow==aSmallLi.length)
        {
            iNow=0;
        }

        tab();
    }
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
                    if(filterList!=null&&filterList.length>0){
                        if(filterList.length==1){
                            window.location.href = "detail.html?id="+filterList[0].gardenId+"&keyword="+keyword;
                        }else{
                            window.location.href = "list.html?keyword="+keyword;
                        }
                    }else{
                        alert("请输入正确的楼盘！");
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