<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>鉴定你是师大人吗</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<!--        <link href="bootstrap.min.css" rel="stylesheet">
        <script src="jquery.min.js"></script>
        <script src="bootstrap.min.js"></script>-->
<style>
.head img {
	width: 100%;
}

img {
	max-width: 100%;
}

.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: #858585;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}
</style>
<script>
	var descok = "鉴定你是师大人吗";//分享内容

	var Allquestion = Array();
	//问题1
	Allquestion[0] = Array();
	Allquestion[0]['tid'] = "1";
	Allquestion[0]['title'] = "1、云南师范大学的前身是？";
	Allquestion[0]['ans'] = Array();
	Allquestion[0]['ans'][0] = {
		"name" : "西南联大",
		"score" : 10
	};
	Allquestion[0]['ans'][1] = {
		"name" : "西南大学 ",
		"score" : 0
	};
	Allquestion[0]['ans'][2] = {
		"name" : "昆明学院",
		"score" : 0
	};
	//问题2
	Allquestion[1] = Array();
	Allquestion[1]['tid'] = "2";
	Allquestion[1]['title'] = "2、师大的校花是？";
	Allquestion[1]['ans'] = Array();
	Allquestion[1]['ans'][0] = {
		"name" : "木棉",
		"score" : 0
	};
	Allquestion[1]['ans'][1] = {
		"name" : "薰衣草",
		"score" : 0
	};
	Allquestion[1]['ans'][2] = {
		"name" : "玉兰",
		"score" : 10
	};
	//问题3
	Allquestion[2] = Array();
	Allquestion[2]['tid'] = "3";
	Allquestion[2]['title'] = "3、师大西区的“女神楼”是？";
	Allquestion[2]['ans'] = Array();
	Allquestion[2]['ans'][0] = {
		"name" : "2栋",
		"score" : 0
	};
	Allquestion[2]['ans'][1] = {
		"name" : "3栋",
		"score" : 0
	};
	Allquestion[2]['ans'][2] = {
		"name" : "4栋",
		"score" : 10
	};
	//问题4
	Allquestion[3] = Array();
	Allquestion[3]['tid'] = "4";
	Allquestion[3]['title'] = "4、师大南湖里面的天鹅有几只？";
	Allquestion[3]['ans'] = Array();
	Allquestion[3]['ans'][0] = {
		"name" : "2只 ",
		"score" : 0
	};
	Allquestion[3]['ans'][1] = {
		"name" : "3只 ",
		"score" : 10
	};
	Allquestion[3]['ans'][2] = {
		"name" : "4只 ",
		"score" : 0
	};
	//问题5
	Allquestion[4] = Array();
	Allquestion[4]['tid'] = "5";
	Allquestion[4]['title'] = "5、师大九州良彝影院的位置在？";
	Allquestion[4]['ans'] = Array();
	Allquestion[4]['ans'][0] = {
		"name" : "同析1号楼 ",
		"score" : 0
	};
	Allquestion[4]['ans'][1] = {
		"name" : "同析2号楼 ",
		"score" : 10
	};
	Allquestion[4]['ans'][2] = {
		"name" : "同析3号楼 ",
		"score" : 0
	};
	//问题6
	Allquestion[5] = Array();
	Allquestion[5]['tid'] = "6";
	Allquestion[5]['title'] = "6、师大游泳馆外墙的颜色是？";
	Allquestion[5]['ans'] = Array();
	Allquestion[5]['ans'][0] = {
		"name" : "红色",
		"score" : 0
	};
	Allquestion[5]['ans'][1] = {
		"name" : "橘色",
		"score" : 0
	};
	Allquestion[5]['ans'][2] = {
		"name" : "黄色",
		"score" : 10
	};
	//问题7
	Allquestion[6] = Array();
	Allquestion[6]['tid'] = "7";
	Allquestion[6]['title'] = "7、师大图书馆进馆有几个闸门？";
	Allquestion[6]['ans'] = Array();
	Allquestion[6]['ans'][0] = {
		"name" : "3个  ",
		"score" : 10
	};
	Allquestion[6]['ans'][1] = {
		"name" : "4个  ",
		"score" : 0
	};
	Allquestion[6]['ans'][2] = {
		"name" : "5个  ",
		"score" : 0
	};
	//问题8
	Allquestion[7] = Array();
	Allquestion[7]['tid'] = "8";
	Allquestion[7]['title'] = "8、2014年西区食堂发生的“最坑爹”事件是？";
	Allquestion[7]['ans'] = Array();
	Allquestion[7]['ans'][0] = {
		"name" : "食堂大妈打饭少",
		"score" : 0
	};
	Allquestion[7]['ans'][1] = {
		"name" : "食堂饭菜涨价",
		"score" : 0
	};
	Allquestion[7]['ans'][2] = {
		"name" : "食堂停业",
		"score" : 10
	};
	//问题9
	Allquestion[8] = Array();
	Allquestion[8]['tid'] = "9";
	Allquestion[8]['title'] = "9、从小南门出去的街，俗名叫什么？";
	Allquestion[8]['ans'] = Array();
	Allquestion[8]['ans'][0] = {
		"name" : "女人街",
		"score" : 0
	};
	Allquestion[8]['ans'][1] = {
		"name" : "购物天堂",
		"score" : 0
	};
	Allquestion[8]['ans'][2] = {
		"name" : "佳美屌丝街",
		"score" : 10
	};
	//问题10
	Allquestion[9] = Array();
	Allquestion[9]['tid'] = "10";
	Allquestion[9]['title'] = "10、师大西区的体育馆的外形是？";
	Allquestion[9]['ans'] = Array();
	Allquestion[9]['ans'][0] = {
		"name" : "来自大山里的山茶花",
		"score" : 0
	};
	Allquestion[9]['ans'][1] = {
		"name" : "来自星星的飞碟",
		"score" : 10
	};
	//问题11
	Allquestion[10] = Array();
	Allquestion[10]['tid'] = "11";
	Allquestion[10]['title'] = "11、云师大女生单身的比例是多少？";
	Allquestion[10]['ans'] = Array();
	Allquestion[10]['ans'][0] = {
		"name" : "50%",
		"score" : 0
	};
	Allquestion[10]['ans'][1] = {
		"name" : "70%",
		"score" : 10
	};
	Allquestion[10]['ans'][2] = {
		"name" : "90%",
		"score" : 0
	};
	Allquestion[10]['ans'][3] = {
		"name" : "95%",
		"score" : 0
	};
	//问题12
	Allquestion[11] = Array();
	Allquestion[11]['tid'] = "12";
	Allquestion[11]['title'] = "12、下列比赛哪个不是在云师大举办的？";
	Allquestion[11]['ans'] = Array();
	Allquestion[11]['ans'][0] = {
		"name" : "CUBA大学生篮球比赛",
		"score" : 0
	};
	Allquestion[11]['ans'][1] = {
		"name" : "汉语桥",
		"score" : 0
	};
	Allquestion[11]['ans'][2] = {
		"name" : "希望之星英语演讲比赛",
		"score" : 10
	};
	Allquestion[11]['ans'][3] = {
		"name" : "不知道",
		"score" : 0
	};
	//问题13
	Allquestion[12] = Array();
	Allquestion[12]['tid'] = "13";
	Allquestion[12]['title'] = "13、红烛广场的雕像是谁？";
	Allquestion[12]['ans'] = Array();
	Allquestion[12]['ans'][0] = {
		"name" : "鲁迅",
		"score" : 0
	};
	Allquestion[12]['ans'][1] = {
		"name" : "闻一多",
		"score" : 10
	};
	Allquestion[12]['ans'][2] = {
		"name" : "杨林校长",
		"score" : 0
	};
	Allquestion[12]['ans'][3] = {
		"name" : "呵呵",
		"score" : 0
	};
	//问题14
	Allquestion[13] = Array();
	Allquestion[13]['tid'] = "14";
	Allquestion[13]['title'] = "14、师大的哪个馆可以攀岩？";
	Allquestion[13]['ans'] = Array();
	Allquestion[13]['ans'][0] = {
		"name" : "东区体育馆",
		"score" : 0
	};
	Allquestion[13]['ans'][1] = {
		"name" : "西区游泳馆",
		"score" : 10
	};
	Allquestion[13]['ans'][2] = {
		"name" : "西区体育馆",
		"score" : 10
	};
	Allquestion[13]['ans'][3] = {
		"name" : "都不能",
		"score" : 0
	};
	//问题15
	Allquestion[14] = Array();
	Allquestion[14]['tid'] = "15";
	Allquestion[14]['title'] = "15、图书馆的杂志期刊室在几楼？";
	Allquestion[14]['ans'] = Array();
	Allquestion[14]['ans'][0] = {
		"name" : "三楼",
		"score" : 0
	};
	Allquestion[14]['ans'][1] = {
		"name" : "四楼",
		"score" : 0
	};
	Allquestion[14]['ans'][2] = {
		"name" : "五楼",
		"score" : 10
	};
	Allquestion[14]['ans'][3] = {
		"name" : "不知道",
		"score" : 0
	};

	var Allans = Array();
	//答案A
	Allans[0] = Array();
	Allans[0]['tip'] = "A";
	Allans[0]['title'] = "运气真好，一题都没答对";
	Allans[0]['content'] = "零蛋也能是真爱，不知道大学城怎么嗨起来那就看这里，搜索酷玩大学，关注我们，带你玩转大学城。";
	//答案B
	Allans[1] = Array();
	Allans[1]['tip'] = "B";
	Allans[1]['title'] = "不及格";
	Allans[1]['content'] = "你已经暴露了，你是潜伏在师大附近的卧底，快快找你师大的朋友来解救你。";
	//答案C
	Allans[2] = Array();
	Allans[2]['tip'] = "C";
	Allans[2]['title'] = "勉强合格";
	Allans[2]['content'] = "可以任性的游走于师大，基本了解师大。";
	//答案D
	Allans[3] = Array();
	Allans[3]['tip'] = "D";
	Allans[3]['title'] = "合格的师大人";
	Allans[3]['content'] = "不是学霸就是安静的美男子（美女子），师大就是你的后花园。";
	//答案E
	Allans[4] = Array();
	Allans[4]['tip'] = "E";
	Allans[4]['title'] = "极品师大人";
	Allans[4]['content'] = "对师大了如指掌，师大绝对是你的真爱！";

	$(function() {
		$.getJSON("http://115.29.231.215/index.php/Wap/Hdyue/getWelcomeNum", {
			diaoId : 12
		}, function(data) {
			if (data['info'] == "ok") {
				var str = "总共有" + data['data'] + "人测试过!!!";
			}
			$('#pNum').html(str);
		});
	});

	function reloadme() {
		location.reload();
	}

	function start(obj, id) {
		if (id == 0) {
			$(obj).removeClass().addClass("img-thumbnail");
			setTimeout(function() {
				nextQuestion(id, 0)
			}, 500);
		}
	}
	var myscore = 0;
	function selectMe(obj, id, score) {
		$(obj).parent().removeClass().addClass("danger");
		setTimeout(function() {
			nextQuestion(id, score)
		}, 500);
	}
	function nextQuestion(nowid, score) {
		myscore += score;
		var questionLeng = Allquestion.length;
		var question = "";
		if (nowid >= questionLeng) {
			var anstr = '<table class="table"><tbody>';

			var resTitle = "测试结果为:";
			var result = "";

			var ansId = 0;
			if (myscore = 0) {
				ansId = 0;
			} else if (myscore <= 50) {
				ansId = 1;
			} else if (myscore <= 90) {
				ansId = 2;
			} else if (myscore <= 140) {
				ansId = 3;
			} else {
				ansId = 4;
			}
			resTitle = "评估结果:<br/><br/>" + Allans[ansId]['title'];
			descok = Allans[ansId]['title'];
			result = Allans[ansId]['content'];

			anstr += '<tr><td  class="text-center"><button type="button" class="btn btn-primary btn-lg btn-block" onclick="show()">晒一晒你的评估结果</button></td></tr>';
			anstr += '<tr><td  class="text-center"><a type="button" class="btn btn-success btn-lg btn-block" target="_blank" href="http://mp.weixin.qq.com/s?__biz=MzAwMzA3NTEzOQ==&mid=201365312&idx=1&sn=49cd7c09b92b8668767b4dae9693ad0e#rd">关注酷玩大学，大学不再无聊~~</a></td></tr>';
			anstr += '<tr><td  class="text-center"><button type="button" class="btn btn-success btn-lg btn-block" onclick="reloadme()">再测一次</button></td></tr>';
			anstr += '<tr><td  class="text-center"><a type="button" class="btn btn-danger btn-lg btn-block" target="_blank" href="http://115.29.231.215/index.php?g=Wap&m=Index&a=lists&classid=748&token=lpolnf1409216792">更多测试</a></td></tr>';
			anstr += '</tbody></table>';
			question = '<h4 class="text-center">'
					+ resTitle
					+ '</h4><img width="100%" src="share/fx.png"><h5 class="text-center" style="line-height:18px;">'
					+ result + '</h5>' + anstr;
		} else {
			var tid = Allquestion[nowid]['tid'];
			var title = Allquestion[nowid]['title'];

			var anstr = '<table class="table"><tbody>';
			for (var i = 0; i < Allquestion[nowid]['ans'].length; i++) {
				//                        var classId = parseInt(4*Math.random());
				var className = "active";
				switch (i) {
				case 0:
					className = "active";
					break;
				case 1:
					className = "success";
					break;
				case 2:
					className = "warning";
					break;
				case 3:
					className = "active";
					break;
				case 4:
					className = "warning";
					break;
				}
				anstr += '<tr class='+ className +' style="margin-top:14px"><td class="text-center" onclick="selectMe(this,'
						+ Allquestion[nowid]['tid']
						+ ','
						+ Allquestion[nowid]['ans'][i]["score"]
						+ ');">'
						+ Allquestion[nowid]['ans'][i]["name"] + '</td></tr>';
			}
			anstr += '</tbody></table>';
			question = '<h4 id="'+ tid +'" class="text-center">' + title
					+ '</h4>' + anstr;
		}
		$(".span12").html(question);
	}

	function show() {
		var fade = document.getElementById('fade');
		fade.style.display = 'block';
		share();//分享初始化
	}
	function hide() {
		var fade = document.getElementById('fade');
		fade.style.display = 'none';
	}
</script>
<script type="text/javascript">
	function shareToFre(desc) {
		if (!desc) {
			desc = "师大人鉴定结果：" + result;
		} else {
			desc = "";
		}
		// 朋友圈
		var data = {
			"imgUrl" : "http://115.29.231.215/diaocha1/shida.jpg",
			"link" : "http://115.29.231.215/diaocha1/diaocha_shida.html",
			"desc" : desc,
			"title" : "师大人鉴定结果：" + result
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
			"imgUrl" : "http://115.29.231.215/diaocha1/shida.jpg",
			"link" : "http://115.29.231.215/diaocha1/diaocha_shida.html",
			"desc" : result,
			"title" : desc
		};
		weixinShareTimeline(data, callbacks);
		weixinSendAppMessage(data2, callbacks);
	}
	document.addEventListener('WeixinJSBridgeReady', function() {
		shareToFre();
	}, false);
	// 分享到朋友圈
	function weixinShareTimeline(data, callbacks) {
		callbacks = callbacks || {};
		var shareTimeline = function(theData) {
			WeixinJSBridge.invoke('shareTimeline', {
				"appid" : theData.appId ? theData.appId : '',
				"img_url" : theData.imgUrl,
				"link" : theData.link,
				"desc" : theData.title,
				"title" : theData.desc, // 注意这里要分享出去的内容是desc
				"img_width" : "120",
				"img_height" : "120"
			}, function(resp) {
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
		WeixinJSBridge.on('menu:share:timeline', function(argv) {
			if (callbacks.async && callbacks.ready) {
				if (!callbacks.__dataLoadedFuncInited) {
					var loadedCb = callbacks.dataLoaded || new Function();
					callbacks.dataLoaded = function(newData) {
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
		var sendAppMessage = function(theData) {
			WeixinJSBridge.invoke('sendAppMessage', {
				"appid" : theData.appId ? theData.appId : '',
				"img_url" : theData.imgUrl,
				"link" : theData.link,
				"desc" : theData.desc,
				"title" : theData.title,
				"img_width" : "120",
				"img_height" : "120"
			}, function(resp) {
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
		WeixinJSBridge.on('menu:share:appmessage', function(argv) {
			if (callbacks.async && callbacks.ready) {
				if (!callbacks.__dataLoadedFuncInited) {
					var loadedCb = callbacks.dataLoaded || new Function();
					callbacks.dataLoaded = function(newData) {
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
</head>
<body>
	<div class="head">
		<a target="_blank"
			href="http://mp.weixin.qq.com/s?__biz=MzAwMzA3NTEzOQ==&mid=201365312&idx=1&sn=49cd7c09b92b8668767b4dae9693ad0e#rd"><img
			src="head.png"></a>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<br />
			<div class="span12">
				<table class="table">
					<caption>
						<h5 id="pNum" class="text-danger text-center">&nbsp;</h5>
						<p id="backTime" class="text-danger text-center">点击下图开始测试你是否是师大人</p>
					</caption>
					<tbody>
						<tr>
							<td><p class="text-center text-primary">
									<img class="img-rounded" src="shida.jpg" width="95%"
										onclick="start(this,0)">
								</p></td>
						</tr>
					</tbody>
				</table>
				<p class="text-center">
					<a type="button"
						class="btn btn-success text-center btn-lg btn-block"
						target="_blank"
						href="http://115.29.231.215/index.php?g=Wap&m=Index&a=lists&classid=748&token=lpolnf1409216792">更多测试</a>
				</p>
			</div>
		</div>
	</div>

	<div id="fade" class="black_overlay" onclick="hide()">
		<img src="share.png" style="width:100%" />
	</div>
</body>
</html>
