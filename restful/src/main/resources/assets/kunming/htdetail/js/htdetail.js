var city_id = 273;
//获取参数
var params = getUrlVars();
var ht_id = params["id"];
var gname = decodeURI(params["gname"]);
var houseTypeDetail = getHouseTypeDetail(ht_id);

$("#gname").text(gname);
$("#htname").text(houseTypeDetail.houseInfo.symbol+" | "+houseTypeDetail.houseInfo.htName+" | "+houseTypeDetail.houseInfo.area);
$("#htname1").text(houseTypeDetail.houseInfo.symbol);
var saleStatusText = houseTypeDetail.houseInfo.saleStatus==0?"待售":houseTypeDetail.houseInfo.saleStatus==1?"在售":"售罄";
$("#saleStatus").text(saleStatusText);
$("#saleStatus").attr("class","onsale"+houseTypeDetail.houseInfo.saleStatus);
//标签
var mark_list = houseTypeDetail.houseInfo.houseMark;
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
$("#price").text(houseTypeDetail.houseInfo.price);
$("#preferential").html(houseTypeDetail.houseInfo.preferential==null?"具体折扣以实际成交为准！":replaceB(houseTypeDetail.houseInfo.preferential,"\n","<br>"));
$("#details").text(houseTypeDetail.houseInfo.details);
$("#downPayment").text(houseTypeDetail.houseInfo.downPayment);
$("#downPayment2").text(houseTypeDetail.houseInfo.downPayment2);

$("#gname2").text(gname);
$("#htName2").text(houseTypeDetail.houseInfo.htName);
$("#price2").text(houseTypeDetail.houseInfo.price);
$("#area").text(houseTypeDetail.houseInfo.area);
$("#toward").text(houseTypeDetail.houseInfo.toward);
$("#layerHigh").text(houseTypeDetail.houseInfo.layerHigh);
$("#downPayment3").text(houseTypeDetail.houseInfo.downPayment);
$("#ladderHousehold").text(houseTypeDetail.houseInfo.ladderHousehold);
$("#totalHouseNum").text(houseTypeDetail.houseInfo.totalHouseNum);
$("#inSalesVolume").text(houseTypeDetail.houseInfo.inSalesVolume);
//特色详情
var ht_hf_list = houseTypeDetail.lstFeature;
if (ht_hf_list != null && ht_hf_list != "undefined" && ht_hf_list.length > 0) {
    $("#ht_hf_detail").show();
    for (var j = 0; j < ht_hf_list.length; j++) {
        $("#ht_hf_list").append('<li class="list-item section-loupan"><div class="ht-hf-item hf-detail-'+ht_hf_list[j].iconType+'"></div><div><div class="ht-hf-item-name">'+ht_hf_list[j].title+'</div><br/><br/><br/><div class="ht-hf-item-detail">'+ht_hf_list[j].info+'</div></div><div class="clearfix"></div></li>');

    }
}
var ht_list = houseTypeDetail.otherHouse;
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
            $("#ht_list").append('<li data-id="' + ht_list[j].houseId + '" style="display:none;"><a class="type-img" href="javascript:;" event-track data-event-label="户型详情页"><img src="' + ht_list[j].imgUrl + '" alt="' + ht_list[j].symbol + '"></a><div class="detail"><a class="name" href="htdetail.html?id=' + ht_list[j].houseId + '&gname=' + gname + '" target="_blank" event-track data-event-label="户型详情页">' + ht_list[j].symbol + ' | ' + ht_list[j].htName +' | '+ ht_list[j].area +'</a><span class="onsale' + ht_list[j].saleStatus + '">'+saleStatusText+'</span><div class="rcontent"><div class="total">总价：<span>' + ht_list[j].price + '万起</span></div></div><div style="height: 24px;"></div><p class="virture">' + ht_list[j].htInfo + '</p><div style="height: 24px;"></div><div class="tag">'+markshtml+'</div></div></li>');
        }else{
            $("#ht_list").append('<li data-id="' + ht_list[j].houseId + '"><a class="type-img" href="javascript:;" event-track data-event-label="户型详情页"><img src="' + ht_list[j].imgUrl + '" alt="' + ht_list[j].symbol + '"></a><div class="detail"><a class="name" href="htdetail.html?id=' + ht_list[j].houseId + '&gname=' + gname + '" target="_blank" event-track data-event-label="户型详情页">' + ht_list[j].symbol + ' | ' + ht_list[j].htName +' | '+ ht_list[j].area +'</a><span class="onsale' + ht_list[j].saleStatus + '">'+saleStatusText+'</span><div class="rcontent"><div class="total">总价：<span>' + ht_list[j].price + '万起</span></div></div><div style="height: 24px;"></div><p class="virture">' + ht_list[j].info + '</p><div style="height: 24px;"></div><div class="tag">'+markshtml+'</div></div></li>');
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





//轮播图片
var imglist = houseTypeDetail.lstImage;
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