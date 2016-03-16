package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.domain.wx.WeixinGroup;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/***
 * 用户分组接口实现
 * 
 * @author hui
 *
 */
public class Group
{
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/***
	 * 查询分组
	 * 
	 * @param accessToken
	 * @return
	 */
	public static List<WeixinGroup> getGroups(String accessToken) {
		List<WeixinGroup> weixinGroupList = null;
		// 拼接地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 查询分组
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		// System.out.println(jsonObject.get("groups"));
		if (null != jsonObject) {
			try {
				weixinGroupList = JSONArray.toList(
						jsonObject.getJSONArray("groups"), WeixinGroup.class);
			} catch (Exception e) {
				e.printStackTrace();
				weixinGroupList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("查询分组失败", errorCode, errorMsg);
			}
		}
		return weixinGroupList;
	}

	/***
	 * 创建分组
	 * 
	 * @param accessToken
	 * @param groupName
	 * @return
	 */
	public static WeixinGroup createGroup(String accessToken, String groupName) {
		WeixinGroup weixinGroup = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"group\":{\"name\":\"%s\"}}";
		// 创建分组
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
				String.format(jsonData, groupName));
		if (null != jsonObject) {
			try {
				weixinGroup = new WeixinGroup();
				weixinGroup.setId(jsonObject.getJSONObject("group")
						.getInt("id"));
				weixinGroup.setName(jsonObject.getJSONObject("group")
						.getString("name"));
			} catch (Exception e) {
				weixinGroup = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取关注者列表失败", errorCode, errorMsg);
			}
		}
		return weixinGroup;
	}

	/***
	 * 修改分组
	 * 
	 * @param accessToken
	 * @param groupId
	 * @param groupName
	 * @return
	 */
	public static boolean updateGroup(String accessToken, int groupId,
			String groupName) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"group\":{\"id\":%d,\"name\":\"%s\"}}";
		// 修改分组名
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
				String.format(jsonData, groupId, groupName));
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("修改分组名成功！", errorCode, errorMsg);
			} else {
				// log.info("修改分组名失败！",errorCode,errorMsg);
				System.out.println("修改分组名失败！" + errorCode + "," + errorMsg);
			}
		}
		return result;
	}

	/***
	 * 移动用户分组
	 * 
	 * @param accessToken
	 * @param openId
	 * @param groupId
	 * @return
	 */
	public static boolean updateMemberGroup(String accessToken, String openId,
			int groupId) {
		boolean result = false;
		// 拼接请求地址
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"openid\":\"%s\",\"to_groupid\":%d}";
		// 移动用户分组
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
				String.format(jsonData, openId, groupId));
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("移动用户分组成功！", errorCode, errorMsg);
			} else {
				log.info("移动用户分组失败！", errorCode, errorMsg);
			}
		}
		return result;

	}

	/***
	 * 查询用户所在分组
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public static int getGroupidByOpenid(String accessToken,String openid) {
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"openid\":\"%s\"}";
		try
		{
			// 查询用户所在分组
			JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
					String.format(jsonData, openid));
			if (null != jsonObject) {
				int groupid = jsonObject.getInt("groupid");
				if(groupid>0){
					return groupid;
				}
			}
		}catch (Exception e){
			return 0;
		}
		return 0;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxa33c55ef7bf07dc0";
		// 第三方用户唯一凭证密钥
		String appSecret = "b0b0fd34ebb1ca464ef5f5d621055177";
		// 获取接口访问凭证a
		String accessToken = WeixinUtil.getAccessToken(appId, appSecret)
				.getToken();
		// 获取分组列表
		List<WeixinGroup> weixinGroups = getGroups(accessToken);
		// 循环输出各分组信息
		for (WeixinGroup weixinGroup : weixinGroups) {
			System.out.println(String.format("ID:%d 名称：%s 用户数： %d",
					weixinGroup.getId(), weixinGroup.getName(),
					weixinGroup.getCount()));
		}

		// 创建分组
		// WeixinGroup weixinGroup = createGroup(accessToken, "可回复用户");
		// System.out.println(String.format("成功创建分组：%s id:%d",
		// weixinGroup.getName(),weixinGroup.getId()));

		// 修改分组
		// updateGroup(accessToken, 101, "同事1");

		// 移动用户分组
		// updateMemberGroup(accessToken, "openId", 100);
	}

}
