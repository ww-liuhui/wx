
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>把玫瑰的浪漫带到生活里</title>
<style>
* {
	margin: 0;
	padding: 0 -webkit-touch-callout: none;
	/* prevent callout to copy image, etc when tap to hold */
	-webkit-text-size-adjust: none;
	/* prevent webkit from resizing text to fit */
	/* make transparent link selection, adjust last value opacity 0 to 1.0 */
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	/*-webkit-user-select: none;*/
	/* prevent copy paste, to allow, change 'none' to 'text' */
	/* -webkit-tap-highlight-color: rgba(0,0,0,0); */
}

html, body {
	height: 100%;
	width: 100%;
	overflow: hidden
}

.container {
	position: relative;
	display: inline-block;
	width: 100%;
	height: 100%;
}

.container img {
	width: 100%;
	height: 100%
}

#robot {
	position: absolute;
	top: 0px;
	left: 0px;
	z-index: 1;
	-webkit-box-shadow: 0px 0px 20px 0px #707070;
	-moz-box-shadow: 0px 0px 20px 0px #707070;
	box-shadow: 0px 0px 20px 0px #707070;
}

#redux {
	position: absolute;
	top: 0px;
	left: 0px;
	z-index: 2;
}

.spinner {
	margin: 0 auto;
	width: 50px;
	height: 60px;
	text-align: center;
	font-size: 10px;
}

.spinner>div {
	background-color: #67CF22;
	height: 100%;
	width: 6px;
	display: inline-block;
	-webkit-animation: stretchdelay 1.2s infinite ease-in-out;
	animation: stretchdelay 1.2s infinite ease-in-out;
	margin: 0 3px;
}

.spinner .rect2 {
	-webkit-animation-delay: -1.1s;
	animation-delay: -1.1s;
}

.spinner .rect3 {
	-webkit-animation-delay: -1.0s;
	animation-delay: -1.0s;
}

.spinner .rect4 {
	-webkit-animation-delay: -0.9s;
	animation-delay: -0.9s;
}

@
-webkit-keyframes stretchdelay { 0%, 40%, 100% {
	-webkit-transform: scaleY(0.4)
}

20%
{
-webkit-transform
:
 
scaleY
(1
.0
)
 
}
}
@
keyframes stretchdelay { 0%, 40%, 100% {
	transform: scaleY(0.4);
	-webkit-transform: scaleY(0.4);
}

20%
{
transform
:
 
scaleY
(1
.0
);

                   
-webkit-transform
:
 
scaleY
(1
.0
);

               
}
}
canvas {
	border: none
}

.swiper-container {
	width: 100%;
	color: #fff;
	text-align: center;
}

.swiper-slide {
	position: relative;
}

.swiper-slide .m_bk {
	position: absolute;
	left: 0;
	top: 0;
	z-index: 1
}

.swiper-slide .m_zi {
	position: absolute;
	z-index: 2;
	-webkit-transition: All 1.0s ease-out;
	opacity: 0
}

.drc {
	position: absolute;
	top: 0;
	-webkit-transition: All 1.0s ease-out;
	opacity: 0;
	z-index: 1
}

#drc-left {
	left: -50%
}

#drc-right {
	right: -50%
}

.c-ht {
	position: absolute;
	left: 0;
	top: 0;
	z-index: 2;
	-webkit-transition: All 1.0s ease-out;
	opacity: 0;
}

.xin {
	position: absolute;
	top: 40%;
	left: 50%;
	margin-left: -15%;
	-webkit-transition: All 1.0s ease-out;
	opacity: 0;
	-webkit-animation: xindong 2s 0s infinite linear normal none;
	z-index: 3;
}

@
-webkit-keyframes xindong { 0%{
	-webkit-transform: scale(1, 1);
}

25%{
-webkit-transform
:scale
(1
.2
,
1
.2
);

            
}
50%{
-webkit-transform
:scale
(1
,
1);
}
75%{
-webkit-transform
:scale
(1
.2
,
1
.2
);

            
}
100%{
-webkit-transform
:scale
(1
,
1);
}
}
#zi2 {
	top: 0%;
	left: 25%;
}

#zi3 {
	top: 15%;
	left: 0%;
}

#cloud {
	position: absolute;
	z-index: 2;
	top: 5%;
	-webkit-transform: translate(100%, 0);
	-webkit-animation: move 10s 5s infinite linear normal none;
}

@
-webkit-keyframes move { 0%{
	-webkit-transform: translateX(100%);
}

100%{
-webkit-transform
:translateX
(-200%);

            
}
}
#zi41 {
	top: 0%;
	left: 15%;
}
#zi42 {
	bottom: 15%;
	right: 0%;
}
#zi5 {
	top: 0%;
	left: 15%
}
#zi6 {
	top: 10%;
	left: -10%
}

.light {
	position: absolute;
	left: 0;
	top: 0;
	z-index: 2;
	display: none
}

#zi7 {
	top: 0%;
	left: 20%;
}

#zi8 {
	top: 15%;
	left: 0%
}

#zi9 {
	left: 15%;
	bottom: 0%
}

.fx {
	width: 80%;
	height: 30%;
	position: absolute;
	left: 50%;
	margin-left: -40%;
	top: 5%
}

.hd {
	padding-bottom: 5%
}

.names {
	border: 1px solid #BBC68B;
	background: #fff;
	height: 25px;
	width: 85px;
	margin-left: 22%;
	margin-top: -1%
}

.textarea {
	width: 100%;
	height: 75px;
	resize: none;
	border: 1px solid #BBC68B;
	background: #fff
}

.ft {
	padding-top: 5%
}

.ft img {
	margin: 0 5%
}

.m_15_logo {
	position: absolute;
	left: 50%;
	margin-left: -25%;
	bottom: 5%
}

.btn_music {
	display: inline-block;
	width: 35px;
	height: 35px;
	background: url('images/play.png') no-repeat center center;
	background-size: 100% auto;
	position: absolute;
	z-index: 100;
	left: 15px;
	top: 30px;
	overflow: hidden;
}

.btn_music.on {
	background-image: url("images/stop.png");
}

.share {
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.7);
	position: absolute;
	left: 0;
	top: 0;
	z-index: 5;
	display: none
}

.share img {
	position: absolute;
	right: 9%;
	top: 3%;
	z-index: 6
}

.xin {
	position: absolute;
	top: 40%;
	left: 50%;
	margin-left: -15%;
	-webkit-transition: All 1.0s ease-out;
	opacity: 0;
	-webkit-animation: xindong 2s 0s infinite linear normal none;
	z-index: 3;
}

.arr-bottom {
	position: absolute;
	z-index: 5;
	left: 50%;
	margin-left: -5%;
	-webkit-transition: All 1.0s ease-out;
}

.arr-bottom {
	bottom: 5%;
	-webkit-animation: arrBottom 1s 0s infinite linear normal none;
}

@
-webkit-keyframes arrBottom { 0%{
	-webkit-transform: translateY(-20%);
	opacity: 1
}
100%{
-webkit-transform
:translateY
(10%);

                
opacity
:
 
0
}
}
</style>
<link rel="stylesheet" href="css/idangerous.swiper.css">
<!-- <script src="js/base-loading.js?556228"></script> -->
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/idangerous.swiper-2.1.min.js"></script>
<script type="text/javascript" src="js/audio.js"></script>
<script type="text/javascript" src="js/wScratchPad.js?12"></script>
<script type="text/javascript">
	window.addEventListener("DOMContentLoaded", function() {
		playbox.init("playbox");
	}, false);
</script>

</head>
<body>
	<span id="playbox" class="btn_music"
		onClick="playbox.init(this).play();"><audio src="music/1.mp3"
			loop="loop" id="audio"></audio></span>
	<div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<img src="images/m_1.gif" width="100%" height="100%" alt=""
					class="m_bk" id="m_bk" />
			</div>
			<div class="swiper-slide">
				<img src="images/1.jpg" width="100%" height="100%" alt=""
					class="m_bk" /> <img src="images/11.png" width="50%" alt=""
					class="m_zi" id="zi2"/>
			</div>
			<div class="swiper-slide">
				<img src="images/2.jpg" width="100%" height="100%" alt="" /> <img
					src="images/21.png" width="70%" alt="" class="m_zi" id="zi3" />
			</div>
			<div class="swiper-slide">
				<img src="images/3.jpg" width="100%" height="100%" alt="" /> <img
					src="images/31.png" width="70%" alt="" class="m_zi" id="zi41" /><img
					src="images/32.png" width="30%" alt="" class="m_zi" id="zi42" />
			</div>
			<div class="swiper-slide">
				<img src="images/4.jpg" width="100%" height="100%" alt=""
					class="m_bk" /> <img src="images/41.png" width="70%" alt=""
					class="m_zi" id="zi5" />
			</div>
			<div class="swiper-slide">
				<img src="images/5.jpg" width="100%" height="100%" alt=""
					class="m_bk" /> <img src="images/51.png" width="90%" alt=""
					class="m_zi" id="zi6" />
			</div>
			<div class="swiper-slide">
				<img src="images/6.jpg" width="100%" height="100%" alt=""
					class="m_bk" /> <img src="images/61.png" width="60%" alt=""
					class="m_zi" id="zi7" />
			</div>
			<div class="swiper-slide">
				<img src="images/7.jpg" width="100%" height="100%" alt=""
					class="m_bk" /> <img src="images/71.png" width="60%" alt=""
					class="m_zi" id="zi8" />
			</div>
			<div class="swiper-slide">
				<img src="images/8.jpg" width="100%" height="100%" alt=""
					class="m_bk" /> <a href="http://detail.koudaitong.com/show/goods?alias=15l3vytoc&amp%253Bspm=h554261&amp%253Bredirect_count=1"><img src="images/81.png" width="70%" alt=""
					class="m_zi" id="zi9" /></a>
			</div>
		</div>
		<div class="share">
			<img src="images/text.png" width="50%" alt="" />
		</div>
		<img src="images/bottoms.png?3332" width="10%" alt=""
			class="arr-bottom" />
	</div>
	<script>
		var _wHeight = document.documentElement.clientHeight;
		$('.swiper-container').css({
			'height' : _wHeight + 'px'
		});

		$('.textarea,.names').click(function(event) {
			event.stopPropagation()
			$(this).focus()
		});

		$('#last').click(function() {
			$('.textarea,.names').blur();
		});

		var mySwiper = new Swiper('.swiper-container', {
			//pagination: '.pagination',
			paginationClickable : true,
			noSwiping : true,
			mode : 'vertical',
			onSlideChangeEnd : function(swiper) {

				switch (mySwiper.activeIndex) {
				case 0:
					animatedObj.rest()
					break;
				case 1:
					animatedObj.rest()
					animatedObj.starMove('#zi2', '0px', '50%')
					break;
				case 2:
					animatedObj.rest()
					animatedObj.starMove('#zi3', '20%', '0px')
					break;
				case 3:
					animatedObj.rest()
					animatedObj.starMove('#zi41', '0px', '50%')
					animatedObj.starMove('#zi42', '-34%', '0px')
					break;
				case 4:
					animatedObj.rest()
					animatedObj.starMove('#zi5', '0px', '40%')
					break;
				case 5:
					animatedObj.rest()
					animatedObj.starMove('#zi6', '16%', '0px')
					break;
				case 6:
					animatedObj.rest()
					animatedObj.starMove('#zi7', '0px', '40%')
					break;
				case 7:
					animatedObj.rest()
					animatedObj.starMove('#zi8', '30%', '0px')
					break;
				case 8:
					animatedObj.rest()
					animatedObj.starMove('#zi9', '0px', '-80%')
					break;
				}
			}
		});

		var animatedObj = {
			starMove : function(o, x, y) {
				$(o).css({
					'transform' : 'translate(' + x + ',' + y + ')',
					'opacity' : '1'
				})
			},
			rest : function() {
				$('.light').hide();
				$('.m_zi,.drc').css({
					'transform' : 'translate(0px,0px)',
					'opacity' : '0'
				})
				$('.c-ht,.xin').css({
					'opacity' : '0'
				})
			}
		}

		$('.shareBtn').click(function() {
			$('.share').show()
		})

		$('.share').click(function() {
			$(this).hide();
		})
	</script>
	<script type="text/javascript">
		function shareToFre(desc) {
	        if (!desc) {
	            desc = '把玫瑰的浪漫带到生活里 ';
	        } else {
	            desc = "";
	        }
	        // 朋友圈
	        var data = {
	            "imgUrl" : "http://ynmit.com/rose/images/2.jpg",
	            "link": "http://ynmit.com/rose/rose.html",
	            "desc": desc,
	            "title": '把玫瑰的浪漫带到生活里'
	        };
	        // share to frends
	        //var data = data;
	        var callbacks = {};
	        callbacks.ok = callbacks.confirm = function() {
	            model.shared = true;
	            addShare();
	        };
	        
	        // 分享给朋友
	        var data2 = {
	            "imgUrl" : "http://ynmit.com/rose/images/2.jpg",
	            "link": "http://ynmit.com/rose/rose.html",
	            "desc": '把玫瑰的浪漫带到生活里',
	            "title": desc
	        };
	        weixinShareTimeline(data, callbacks);
	        weixinSendAppMessage(data2, callbacks);     
	    }
	    document.addEventListener('WeixinJSBridgeReady', function(){
	        shareToFre();
	    }, false);
	    // 分享到朋友圈
	    function weixinShareTimeline(data, callbacks) {
	        callbacks = callbacks || {};
	        var shareTimeline = function (theData) {
	            WeixinJSBridge.invoke('shareTimeline', {
	                "appid":theData.appId ? theData.appId : '',
	                "img_url":theData.imgUrl,
	                "link":theData.link,
	                "desc":theData.title,
	                "title":theData.desc, // 注意这里要分享出去的内容是desc
	                "img_width":"120",
	                "img_height":"120"
	            }, function (resp) {
	                switch (resp.err_msg) {
	                    // share_timeline:cancel 用户取消
	                    case 'share_timeline:cancel':
	                        callbacks.cancel && callbacks.cancel(resp);
	                        break;
	                    // share_timeline:fail　发送失败
	                    case 'share_timeline:fail':
	                        callbacks.fail && callbacks.fail(resp);
	                        break;
	                    // share_timeline:confirm 发送成功
	                    case 'share_timeline:confirm':
	                        callbacks.confirm && callbacks.confirm(resp);
	                        break;
	                    // 发送成功
	                    case 'share_timeline:ok':
	                        callbacks.ok && callbacks.ok(resp);
	                        break;
	                }
	                // 无论成功失败都会执行的回调
	                callbacks.all && callbacks.all(resp);
	            });
	        };
	        WeixinJSBridge.on('menu:share:timeline', function (argv) {
	            if (callbacks.async && callbacks.ready) {
	                if(!callbacks.__dataLoadedFuncInited) {
	                    var loadedCb = callbacks.dataLoaded || new Function();
	                    callbacks.dataLoaded = function (newData) {
	                        loadedCb(newData);
	                        shareTimeline(newData);
	                    };
	                    callbacks.__dataLoadedFuncInited = true;
	                }
	                // 然后就绪
	                callbacks.ready && callbacks.ready(argv);
	            } else {
	                // 就绪状态
	                callbacks.ready && callbacks.ready(argv);
	                shareTimeline(data);
	            }
	        });
	    }
	    // 分享给朋友
	    function weixinSendAppMessage(data, callbacks) {
	        callbacks = callbacks || {};
	        var sendAppMessage = function (theData) {
	            WeixinJSBridge.invoke('sendAppMessage', {
	                "appid":theData.appId ? theData.appId : '',
	                "img_url":theData.imgUrl,
	                "link":theData.link,
	                "desc":theData.desc,
	                "title":theData.title,
	                "img_width":"120",
	                "img_height":"120"
	            }, function (resp) {
	                switch (resp.err_msg) {
	                    // send_app_msg:cancel 用户取消
	                    case 'send_app_msg:cancel':
	                        callbacks.cancel && callbacks.cancel(resp);
	                        break;
	                    // send_app_msg:fail　发送失败
	                    case 'send_app_msg:fail':
	                        callbacks.fail && callbacks.fail(resp);
	                        break;
	                    // send_app_msg:confirm 发送成功
	                    case 'send_app_msg:confirm':
	                        callbacks.confirm && callbacks.confirm(resp);
	                        break;
	                }
	                // 无论成功失败都会执行的回调
	                callbacks.all && callbacks.all(resp);
	            });
	        };
	        WeixinJSBridge.on('menu:share:appmessage', function (argv) {
	            if (callbacks.async && callbacks.ready) {
	                if(!callbacks.__dataLoadedFuncInited) {
	                    var loadedCb = callbacks.dataLoaded || new Function();
	                    callbacks.dataLoaded = function (newData) {
	                        loadedCb(newData);
	                        sendAppMessage(newData);
	                    };
	                    callbacks.__dataLoadedFuncInited = true;
	                }
	                // 然后就绪
	                callbacks.ready && callbacks.ready(argv);
	            } else {
	                // 就绪状态
	                callbacks.ready && callbacks.ready(argv);
	                sendAppMessage(data);
	            }
	        });
	    }
		//隐藏微信浏览器底部导航栏
		document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		    WeixinJSBridge.call('hideToolbar');
		});
	</script>
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1253758057'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1253758057' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>