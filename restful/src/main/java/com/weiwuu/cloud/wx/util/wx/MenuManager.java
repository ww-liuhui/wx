package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.domain.wx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 * 
 * @author liuhui
 * @date 2013-08-08
 */
public class MenuManager
{
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxa33c55ef7bf07dc0";
		// 第三方用户唯一凭证密钥
		String appSecret = "b0b0fd34ebb1ca464ef5f5d621055177";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		System.out.println("Token:" + at.getToken());
		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
//

//
//		CommonButton btn12 = new CommonButton();
//		btn12.setName("弹出拍照或相册");
//		btn12.setType("pic_photo_or_album");
//		btn12.setKey("12");
//
//		CommonButton btn13 = new CommonButton();
//		btn13.setName("弹出微信相册发图器");
//		btn13.setType("pic_weixin");
//		btn13.setKey("13");
//
//		CommonButton btn21 = new CommonButton();
//		btn21.setName("扫码推事件");
//		btn21.setType("scancode_push");
//		btn21.setKey("211");
//
//		CommonButton btn22 = new CommonButton();
//		btn22.setName("推事件弹出：消息接收中");
//		btn22.setType("scancode_waitmsg");
//		btn22.setKey("212");
//

		//

		//
		ViewButton btn11 = new ViewButton();
		btn11.setName("帮帮是谁");
		btn11.setType("view");
		btn11.setUrl("http://mp.weixin.qq.com/s?__biz=MzI2OTA0ODE4Mw==&mid=402439906&idx=1&sn=c35028c906c3be545e22b7aa8f64c2a4#rd");

		//
		ViewButton btn12 = new ViewButton();
		btn12.setName("帮帮服务");
		btn12.setType("view");
		btn12.setUrl("http://mp.weixin.qq.com/s?__biz=MzI2OTA0ODE4Mw==&mid=402439376&idx=1&sn=c2c4466114c11ee688ff2d9108e30bb7#rd");

		CommonButton btn13 = new CommonButton();
		btn13.setName("咨询帮帮");
		btn13.setType("click");
		btn13.setKey("咨询帮帮");

//		ViewButton btn12 = new ViewButton();
//		btn12.setName("历史回顾");
//		btn12.setType("view");
//		btn12.setUrl("http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzI2OTA0ODE4Mw==#wechat_webview_type=1&wechat_redirect");
		//
		ViewButton btn21 = new ViewButton();
		btn21.setName("楼盘查询");
		btn21.setType("view");
		btn21.setUrl("http://wx.weiwuu.com/wx/list.html");

		ViewButton btn22 = new ViewButton();
		btn22.setName("预约看房");
		btn22.setType("view");
		btn22.setUrl("http://wx.weiwuu.com/wx/order.html");

//		CommonButton btn22 = new CommonButton();
//		btn22.setName("预约看房");
//		btn22.setType("click");
//		btn22.setKey("专车预约");

		ViewButton btn23 = new ViewButton();
		btn23.setName("APP下载");
		btn23.setType("view");
		btn23.setUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.weiwuu.fairy");



		ViewButton btn25 = new ViewButton();
		btn25.setName("预约验房");
		btn25.setType("view");
		btn25.setUrl("http://form.mikecrm.com/f.php?t=II6tDJ");

		ViewButton btn31 = new ViewButton();
		btn31.setName("每日资讯");
		btn31.setType("view");
		btn31.setUrl("http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzI2OTA0ODE4Mw==#wechat_webview_type=1&wechat_redirect");

		CommonButton btn32 = new CommonButton();
		btn32.setName("帮帮故事");
		btn32.setType("click");
		btn32.setKey("帮帮故事");

		CommonButton btn33 = new CommonButton();
		btn33.setName("我来帮帮");
		btn33.setType("click");
		btn33.setKey("我来帮帮");
//		CommonButton btn12 = new CommonButton();
//		btn12.setName("附近找房");
//		btn12.setType("location_select");
//		btn12.setKey("searchAround");

		//  http://a.app.qq.com/o/simple.jsp?pkgname=com.weiwuu.atlas  帮帮版本

//		CommonButton btn33 = new CommonButton();
//		btn33.setName("暖冬行动");
//		btn33.setType("click");
//		btn33.setKey("vipParty");

//		ComplexButton mainBtn1 = new ComplexButton();
//		mainBtn1.setName("我要看房");
//		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });
//
//		ComplexButton mainBtn2 = new ComplexButton();
//		mainBtn2.setName("扫码");
//		mainBtn2.setSub_button(new Button[] { btn21, btn22 });
//
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("找帮帮");
		mainBtn1.setSub_button(new Button[] { btn11,btn12,btn13});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("去看房");
		mainBtn2.setSub_button(new Button[] { btn21,btn22,btn23});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("有意思");
		mainBtn3.setSub_button(new Button[] {btn31});
		/**
		 *
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1,mainBtn2,mainBtn3});

		return menu;
	}
}
