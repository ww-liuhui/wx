package com.weiwuu.cloud.wx.util.wx;

/**
 * Created by hui on 2015/11/16.
 */

import com.weiwuu.cloud.wx.domain.wx.AccessToken;
import com.weiwuu.cloud.wx.domain.wx.WeixinUserInfo;
import com.weiwuu.cloud.wx.domain.wx.WeixinUserList;
import com.weiwuu.cloud.wx.factory.RedisServiceFactory;
import com.weiwuu.protobuf.WXUser;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时刷新微信access_token
 *
 * @author liu
 * @date 2015-11-16
 */
public class TokenThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenThread.class);

    // 订阅号
    public static String appid = "";
    public static String appsecret = "";
    public static AccessToken accessToken = null;
    public static String ticket = null;
    public static String weixinOauth2Token = null;

    // 服务号
    public static String appid2 = "";
    public static String appsecret2 = "";
    public static AccessToken accessToken2 = null;
    public static String ticket2 = null;
    public static String weixinOauth2Token2 = null;

    public static JedisPool jedisPool = null;
    public void run() {
        while (true) {
            try {

                accessToken = WeixinUtil.getAccessToken(appid, appsecret);
                JSONObject json = null;
                // 拼接请求地址
                String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
                requestUrl = requestUrl.replace("ACCESS_TOKEN",accessToken.getToken());
                json = WeixinUtil.httpRequest(requestUrl, "GET", null);
                ticket = json.get("ticket").toString();

                accessToken2 = WeixinUtil.getAccessToken(appid2, appsecret2);
                JSONObject json2 = null;
                // 拼接请求地址
                String requestUrl2 = "https://api.weixin.qq" +
                        ".com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
                requestUrl2 = requestUrl2.replace("ACCESS_TOKEN",accessToken2.getToken());
                json2 = WeixinUtil.httpRequest(requestUrl2, "GET", null);
                ticket2 = json2.get("ticket").toString();

                //更新关注用户列表
                String WX_USER_UNIONID = "wx:user:unionid:[unionid]";
                String WX_USER_OPENID = "wx:user:openid:[openid]";
                String WX_USER_LIST = "wx:user:list";
                Jedis jedis = jedisPool.getResource();
                WeixinUserList weixinUserList = UserList.getUserList(accessToken.getToken(), "");
                List<String> openIdList = new ArrayList<String>();
                openIdList = weixinUserList.getOpenIdList();
                try
                {
                    String flag = null;
                    for (String openId : openIdList) {
                        flag = jedis.get(WX_USER_OPENID.replace("[openid]",openId));
                        if(null==flag||flag.length()==0){
                            WeixinUserInfo userInfo = UserInfo.getUserInfo(accessToken.getToken(), openId);
                            if (userInfo != null) {
                                WXUser.Builder builder = WXUser.newBuilder();

                                builder.setUnionid(userInfo.getUnionid());
                                builder.setSubscribe(userInfo.getSubscribe());
                                builder.setSubscribeTime(userInfo.getSubscribeTime());
                                builder.setNickname(userInfo.getNickname());
                                builder.setSex(userInfo.getSex());
                                builder.setCountry(userInfo.getCountry());
                                builder.setProvince(userInfo.getProvince());
                                builder.setCity(userInfo.getCity());
                                builder.setLanguag(userInfo.getLanguag());
                                builder.setHeadImgUrl(userInfo.getHeadImgUrl());
                                builder.setRemark(userInfo.getRemark());
                                builder.setGroupid(userInfo.getGroupid());
                                builder.setOpenId(userInfo.getOpenId());

                                WXUser wxUser = builder.build();

                                byte[] bytes = wxUser.toByteArray();
                                Transaction tc = jedis.multi();
                                tc.set(WX_USER_UNIONID.replace("[unionid]",userInfo.getUnionid()).getBytes(),bytes);
                                tc.set(WX_USER_OPENID.replace("[openid]",userInfo.getOpenId()),userInfo.getUnionid());
                                tc.zadd(WX_USER_LIST,Long.parseLong(userInfo.getSubscribeTime()), userInfo.getUnionid());
                                tc.exec();
                            }
                        }
                    }
                    LOGGER.error("更新关注用户列表成功！");
                }catch (Exception e){
                    LOGGER.error("更新关注用户列表失败："+e.getMessage());
                    e.printStackTrace();
                }finally
                {
                    RedisServiceFactory.returnResource(jedisPool, jedis);
                }

                if (null != accessToken && null != accessToken2) {
                    //log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getToken());
                    // 休眠7000秒
                    Thread.sleep((accessToken.getExpiresIn() - 1200) * 1000);
                } else {
                    // 如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }



            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    LOGGER.error("更新accessToken失败："+e.getMessage());
                }
                //log.error("{}", e);
            }
        }
    }
}
