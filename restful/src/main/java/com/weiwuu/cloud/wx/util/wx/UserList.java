package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.domain.wx.WeixinUserInfo;
import com.weiwuu.cloud.wx.domain.wx.WeixinUserList;
import com.weiwuu.cloud.wx.factory.RedisServiceFactory;
import com.weiwuu.protobuf.WXUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.List;

/***
 * 获取关注者列表
 *
 * @author hui
 */
public class UserList
{
    //private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    /***
     * 获取关注者列表
     *
     * @param accessToken
     * @param nextOpenId
     * @return
     */
    public static WeixinUserList getUserList(String accessToken, String nextOpenId)
    {
        WeixinUserList weixinUserList = null;
        if (null == nextOpenId)
        {
            nextOpenId = "";
        }
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", nextOpenId);
        // 获取关注者列表
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
        System.out.println(requestUrl);
        // 如果请求成功
        if (null != jsonObject)
        {
            try
            {
                weixinUserList = new WeixinUserList();
                weixinUserList.setTotal(jsonObject.getInt("total"));
                weixinUserList.setCount(jsonObject.getInt("count"));
                weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
                JSONObject dataObject = (JSONObject) jsonObject.get("data");
                weixinUserList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"), List.class));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                weixinUserList = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                //log.error("获取关注者列表失败", errorCode, errorMsg);
            }
        }
        return weixinUserList;
    }

    public static void main(String[] args)
    {

        // 第三方用户唯一凭证
        String appId = "wxa33c55ef7bf07dc0";
        // 第三方用户唯一凭证密钥
        String appSecret = "b0b0fd34ebb1ca464ef5f5d621055177";
        // 获取接口访问凭证
        String accessToken = WeixinUtil.getAccessToken(appId, appSecret).getToken();
        WeixinUserList weixinUserList = getUserList(accessToken, "");
        System.out.println("accessToken:" + accessToken);
        List<String> openIdList = new ArrayList<String>();
        openIdList = weixinUserList.getOpenIdList();

        System.out.println(openIdList.size());
        System.out.println("next_openid：" + weixinUserList.getNextOpenId());
        System.out.println("总关注数：" + weixinUserList.getTotal());
        System.out.println("本次获取用户数：" + weixinUserList.getCount());
        try
        {
            for (String openId : openIdList)
            {

                WeixinUserInfo userInfo = UserInfo.getUserInfo(accessToken, openId);
                if (userInfo != null&&(userInfo.getNickname().equals("來自汪星的馬")||userInfo.getNickname().equals("大灰")||userInfo.getNickname().equals("新叶")))
                {
                    System.out.println(userInfo.getNickname()+":" + userInfo.getOpenId());
                }
            }

        }
        catch (Exception e)
        {

        }


        System.out.println("结束");
    }
}
