package com.weiwuu.cloud.wx.service;

import com.weiwuu.cloud.wx.action.lbs.UserLocation;
import com.weiwuu.cloud.wx.action.music.BaiduMusicService;
import com.weiwuu.cloud.wx.dao.GardenDAO;
import com.weiwuu.cloud.wx.domain.wx.WeixinUserInfo;
import com.weiwuu.cloud.wx.entity.response.ClientResponse;
import com.weiwuu.cloud.wx.entity.wx.resp.*;
import com.weiwuu.cloud.wx.factory.ApiFactory;
import com.weiwuu.cloud.wx.factory.RedisServiceFactory;
import com.weiwuu.cloud.wx.util.wx.*;
import com.weiwuu.protobuf.CallCarManager;
import com.weiwuu.protobuf.WXUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * 微信核心服务类
 * Created by hui on 2015/11/16.
 */
public class WXCoreService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GardenService.class);
    private final String WX_USER_UNIONID = "wx:user:unionid:[unionid]";
    private final String WX_USER_OPENID = "wx:user:openid:[openid]";
    private final String WX_USER_LIST = "wx:user:list";
    private static final String WX_USER_USERID = "wx:user:userid:[userid]";
    private static final String WX_USER_BIND_UNIONID = "wx:user:type:[typeid]:unionid:[unionid]";

    private static final String CALL_CAR_MANAGER_ID = "wx:callcar:manager:serial:number";
    private static final String CALL_CAR_MANAGER = "wx:callcar:manager:master:";
    private static final String CALL_CAR_MANAGER_LIST = "wx:callcar:manager:list:city:[cityId]";
    private static final String CALL_CAR_MANAGER_OPENID = "wx:callcar:manager:openid:[openid]";

    private final GardenDAO dao;
    private final ApiFactory apiFactory;
    String basePath = "http://wx.weiwuu.com/wx";

    public WXCoreService(ApiFactory apiFactory, GardenDAO dao)
    {
        this.dao = dao;
        this.apiFactory = apiFactory;
    }

    public static void main(String[] args)
    {
        //        String string="测试<>《》！*(^)$%~!@#$…&%￥—+=、。，；‘’“”：·`文本";
        //        System.out.println(string.replaceAll("\\pP|\\pS", ""));
    }

    public String bindBB(String mobile,String openid,Jedis jedis){
        String respContent = null;
        //根据电话获取帮帮信息
        int brokerId = dao.geUseridByMobile(mobile);
        if (brokerId > 0)
        {
            String userKey = WX_USER_USERID.replace("[userid]", brokerId + "");
            jedis.set(userKey, openid);
            respContent = "恭喜你，帮帮绑定成功！";
        }
        else
        {
            respContent = "绑定失败，帮帮不存在！";
        }
        return respContent;
    }
    /***
     * 根据关键词获取楼盘
     *
     * @param key
     * @return
     */
    public List<Article> getGardenByKey(String key)
    {
        try
        {
            List<Article> list = new ArrayList<Article>();
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() +
                    "/garden/bykey?key=" + key + "&cityname=&PageSize=10&PageIndex=0";
            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);
            Object obj = clientResponse.getBody().getData();
            if (obj != null)
            {
                JSONArray jsonArray = JSONArray.fromObject(obj);
                JSONObject jsonObject = null;
                Article article = null;
                String gardenName = null;
                String picUrl = null;
                String gardenType = null;
                int gardenPrice = 0;
                int gardenId = 0;
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    jsonObject = jsonArray.getJSONObject(i);
                    gardenName = jsonObject.getString("gardenName");
                    gardenId = jsonObject.getInt("gardenId");
                    picUrl = jsonObject.getString("gardenImg");
                    gardenPrice = jsonObject.getInt("gardenPrice");
                    gardenType = jsonObject.getString("gardenType");
                    article = new Article(gardenName + "\n" + gardenPrice + "元/平米    |    " + gardenType, "", picUrl, basePath + "/detail" +
                            ".html?id=" + gardenId);
                    System.out.println("gardenName：" + gardenName);
                    list.add(article);
                }
            }
            return list;
        }
        catch (Exception e)
        {
        }
        return null;
    }

    /***
     * 获取周边楼盘
     *
     * @param lng
     * @param lat
     * @return
     */
    public List<Article> getGardenByNearby(String lng, String lat)
    {
        try
        {
            List<Article> list = new ArrayList<Article>();
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() +
                    "/garden/bynearby?Long=" + lng + "&Lat=" + lat + "&Radius=3000&PageSize=10&PageIndex=0";
            WebTarget target = client.target(url);
            Response response = target.request().get();

            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);
            Object obj = clientResponse.getBody().getData();
            if (obj != null)
            {
                JSONArray jsonArray = JSONArray.fromObject(obj);
                JSONObject jsonObject = null;
                Article article = null;
                String gardenName = null;
                String gardenType = null;
                String picUrl = null;
                int gardenPrice = 0;
                int gardenId = 0;
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    try
                    {
                        jsonObject = jsonArray.getJSONObject(i);
                        gardenName = jsonObject.getString("gardenName");
                        gardenId = jsonObject.getInt("gardenId");
                        picUrl = jsonObject.getString("gardenImg");
                        gardenPrice = jsonObject.getInt("gardenPrice");
                        gardenType = jsonObject.getString("gardenType");
                        article = new Article(gardenName + "\n" + gardenPrice + "元/平米    |    " + gardenType, "", picUrl, basePath + "/detail" +
                                ".html?id=" + gardenId);
                    }
                    catch (Exception e)
                    {
                        LOGGER.error("获取周边楼盘失败：" + e.getMessage());
                        continue;
                    }

                    list.add(article);
                }
            }
            return list;
        }
        catch (Exception e)
        {
            LOGGER.error("获取周边楼盘失败：" + e.getMessage());
        }
        return null;
    }

    /**
     * 处理微信发来的请求
     * @param request
     * @return
     */
    public String processRequest(HttpServletRequest request, String msg_signature, String timestamp, String nonce, String token, String encodingAesKey, String appId, JedisPool jedisPool)
    {
        String respMessage = null;

        Jedis jedis = jedisPool.getResource();
        try
        {
            // 默认返回的文本消息内容
            String respContent = "您有什么疑问，帮帮小跑为您服务。";

            // 安全模式下解密
            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
            String fromXML = MessageUtil.parseXmlToString(request);// 将request请求解析为xml字符串
            String xmlDoc = pc.decryptMsg(msg_signature, timestamp, nonce, fromXML);
            // xml请求解析
            Map<String, String> requestMap = WeixinUtil.xmlElements(xmlDoc);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 开发者帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // 消息创建时间
            String createTime = requestMap.get("CreateTime");
            // 消息ID
            String msgId = requestMap.get("MsgId");

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);


            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT))
            {

                // 消息内容
                String content = requestMap.get("Content").trim();

                //根据关键词获取楼盘
                List<Article> list = getGardenByKey(content);
                // 如果楼盘存在，则组装图文信息
                if (null != list && 0 < list.size())
                {
                    // 回复图文消息
                    NewsMessage articMessage = new NewsMessage();
                    articMessage.setToUserName(fromUserName);
                    articMessage.setFromUserName(toUserName);
                    articMessage.setCreateTime(new Date().getTime());
                    articMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    articMessage.setFuncFlag(0);
                    articMessage.setArticleCount(list.size());
                    articMessage.setArticles(list);
                    return MessageUtil.messageToXml(articMessage);
                }
                else
                {
                    if (content.startsWith("咨询帮帮") || content.startsWith("客服"))
                    {
                        String jsonMsg = null;
                        //自动回复
                        jsonMsg = KeFuUtil.makeTextCustomMessage(fromUserName, "您有什么疑问，帮帮小跑为您服务。");
                        KeFuUtil.sendCustomMessage(TokenThread.accessToken.getToken(), jsonMsg);
                        // 转接到客服系统
                        BaseMessage baseMessage = new BaseMessage();
                        baseMessage.setToUserName(fromUserName);
                        baseMessage.setFromUserName(toUserName);
                        baseMessage.setCreateTime(new Date().getTime());
                        baseMessage.setMsgType(MessageUtil.TRANSFER_CUSTOMER_SERVICE);
                        return MessageUtil.messageToXml(baseMessage);
                    }
                    else if (content.startsWith("推荐有礼"))
                    {
                        //判定是否绑定
                        String unionid = null;
                        String openid_key = WX_USER_OPENID.replace("[openid]", fromUserName);
                        unionid = jedis.get(openid_key);
                        if(null!=unionid&&unionid.length()>0){

                            // 回复图文消息
                            NewsMessage articMessage = new NewsMessage();
                            articMessage.setToUserName(fromUserName);
                            articMessage.setFromUserName(toUserName);
                            articMessage.setCreateTime(new Date().getTime());
                            articMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                            articMessage.setFuncFlag(0);
                            Article article1 = null;

                            String ubu = WX_USER_BIND_UNIONID.replace("[typeid]", "[1,2]").replace("[unionid]", unionid);
                            Set<String> keys = jedis.keys(ubu);
                            if (keys != null & keys.size() > 0)   //已绑定
                            {
                                String code = null;
                                for(String key:keys){
                                    code = jedis.get(key);
                                }
                                code = DES.encryptDES(code,DES.key);
                                article1 = new Article("推荐有礼", "", "http://cms.weiwuu.com//uploads/w/wxwtzq1452242028/material/22289ff81ad6104f1b00e9f15c9304c1889eaa32.jpg", basePath + "/tuijian" +
                                        ".html?code=" + code);
                            }
                            else    //未绑定
                            {
                                article1 = new Article("推荐有礼-为了能将礼品送到您手中，请先绑定。", "", "http://cms.weiwuu.com//uploads/w/wxwtzq1452242028/material/22289ff81ad6104f1b00e9f15c9304c1889eaa32.jpg", basePath + "/comeBB.html?openid=" + fromUserName);
                            }
                            List<Article> listA = new ArrayList<>();
                            listA.add(article1);
                            articMessage.setArticleCount(listA.size());
                            articMessage.setArticles(listA);
                            return MessageUtil.messageToXml(articMessage);
                        }else{
                            respContent = "";
                        }
                    }
                    else if (content.startsWith("绑定约车管理员+"))  //绑定约车管理员+[城市名称]+[姓名]+[电话]
                    {
                        try
                        {
                            //验证约车管理员
                            int groupId = Group.getGroupidByOpenid(TokenThread.accessToken.getToken(), fromUserName);
                            //约车管理员ID：101
                            if (groupId == 101)
                            {
                                String[] infos = content.split("\\+");
                                //根据城市名称获取城市ID
                                int cityId = dao.getCityIdByName(infos[1]);
                                //根据openid获取unionid
                                String openidKey = WX_USER_OPENID.replace("[openid]", fromUserName);
                                String unionid = jedis.get(openidKey);
                                //将管理员信息保存到数据库
                                long id = jedis.incr(CALL_CAR_MANAGER_ID);
                                CallCarManager.Builder builder = CallCarManager.newBuilder();
                                builder.setId((int) id);
                                builder.setCityId(cityId);
                                builder.setName(infos[2]);
                                builder.setTel(infos[3]);
                                builder.setUnionid(unionid);
                                CallCarManager callCarManager = builder.build();
                                builder.clear();
                                builder = null;

                                //
                                String ccmpKey = CALL_CAR_MANAGER_OPENID.replace("[openid]", fromUserName);
                                String managerId = jedis.get(ccmpKey);
                                Transaction tc = jedis.multi();
                                if (managerId == null || managerId.length() == 0)
                                {
                                    tc.set(ccmpKey, id + "");
                                    tc.set((CALL_CAR_MANAGER + id).getBytes(), callCarManager.toByteArray());//添加管理员信息
                                    String listKey = CALL_CAR_MANAGER_LIST.replace("[cityId]", cityId + "");
                                    tc.zadd(listKey, new Date().getTime(), id + "");//将管理员信息添加到列表
                                    respContent = "恭喜你，已经成为约车管理员！";
                                }
                                else
                                {
                                    tc.set((CALL_CAR_MANAGER + managerId).getBytes(), callCarManager.toByteArray());//添加管理员信息
                                    String listKey = CALL_CAR_MANAGER_LIST.replace("[cityId]", cityId + "");
                                    tc.zadd(listKey, new Date().getTime(), managerId);//将管理员信息添加到列表
                                    tc.decr(CALL_CAR_MANAGER_ID);
                                    respContent = "恭喜你，更新约车管理员信息成功！";
                                }
                                tc.exec();

                            }
                            else
                            {
                                respContent = "您没有成为约车管理员的权限！";
                            }
                        }
                        catch (Exception e)
                        {

                        }
                        finally
                        {
                            RedisServiceFactory.returnResource(jedisPool, jedis);
                        }

                    }
                    else if (content.startsWith("帮帮绑定+")) //帮帮绑定+[电话]
                    {
                        try
                        {
                            String[] infos = content.split("\\+");

                            respContent = bindBB(infos[1],fromUserName,jedis);

                        }
                        catch (Exception e)
                        {

                        }
                        finally
                        {
                            RedisServiceFactory.returnResource(jedisPool, jedis);
                        }

                    }
                    else
                    {
                        //调用小黄鸭机器人接口
                        //respContent = ChatService.chat(fromUserName, createTime, content);

                        boolean flag = true;
                        if (flag)
                        {
                            // 转接到客服系统
                            BaseMessage baseMessage = new BaseMessage();
                            baseMessage.setToUserName(fromUserName);
                            baseMessage.setFromUserName(toUserName);
                            baseMessage.setCreateTime(new Date().getTime());
                            baseMessage.setMsgType(MessageUtil.TRANSFER_CUSTOMER_SERVICE);
                            return MessageUtil.messageToXml(baseMessage);
                        }
                        else
                        {
                            // 转接到指定客服
                            CustomerMessage customerMessage = new CustomerMessage();
                            customerMessage.setToUserName(fromUserName);
                            customerMessage.setFromUserName(toUserName);
                            customerMessage.setCreateTime(new Date().getTime());
                            customerMessage.setMsgType(MessageUtil.TRANSFER_CUSTOMER_SERVICE);
                            // 设置转接的指定客服
                            String kfAccount = "bang0011@weiwuu2015";// 指定的客服账号
                            TransInfo transInfo = new TransInfo();
                            transInfo.setKfAccount(kfAccount);
                            customerMessage.setTransInfo(transInfo);
                            return MessageUtil.messageToXml(customerMessage);
                        }


                        //                        respContent = "";
                    }
                }


            }
            // 图片消息
            //            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE))
            //            {
            //                // 取得图片地址
            //                String picUrl = requestMap.get("PicUrl");
            //                // 处理图片消息请求
            //                // 1.人脸检测
            //                String result = FaceService.detect(picUrl);
            //                if(result.length()>0){
            //                    respContent = result;
            //                }else{
            //                    respContent = "";
            //                }
            //            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION))
            {
                // 地理位置经度
                String lat = requestMap.get("Location_X");
                // 地理位置纬度
                String lng = requestMap.get("Location_Y");
                // 地理位置信息
                String label = requestMap.get("Label");
                // 地图缩放大小
                String scale = requestMap.get("Scale");

                // 坐标转换后的经纬度
                String bd_lng = null;
                String bd_lat = null;
                // 调用接口转换坐标
                UserLocation userLocation = BaiDuMapUtil.convertCoord(lng, lat);
                if (null != userLocation)
                {
                    bd_lng = userLocation.getBd_lng();
                    bd_lat = userLocation.getBd_lat();

                }
                // 保存用户地理位置

                //获取周边楼盘
                List<Article> articleList = getGardenByNearby(bd_lng, bd_lat);
                // 周边未搜索到楼盘
                if (null == articleList || 0 == articleList.size())
                {
                    respContent = String.format("/难过，您发送的位置附近未搜索到”%s“信息", "楼盘");
                }
                else
                {
                    // 回复图文消息
                    NewsMessage articMessage = new NewsMessage();
                    articMessage.setToUserName(fromUserName);
                    articMessage.setFromUserName(toUserName);
                    articMessage.setCreateTime(new Date().getTime());
                    articMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    articMessage.setFuncFlag(0);
                    articMessage.setArticleCount(articleList.size());
                    articMessage.setArticles(articleList);
                    return MessageUtil.messageToXml(articMessage);
                }
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK))
            {
                // 消息标题
                String title = requestMap.get("Title");
                // 消息描述
                String description = requestMap.get("Description");
                // 消息链接
                String url = requestMap.get("Url");

                // 处理链接消息请求
                // respContent = "您发送的是链接消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO))
            {
                // 媒体文件标识
                String mediaId = requestMap.get("MediaId");
                // 视频消息缩略图的媒体ID
                String thumbMediaId = requestMap.get("ThumbMediaId");

                // 处理视频消息请求
                // respContent = "您发送的是视频消息！";
            }
            // 小视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO))
            {
                // 媒体文件标识
                String mediaId = requestMap.get("MediaId");
                // 视频消息缩略图的媒体ID
                String thumbMediaId = requestMap.get("ThumbMediaId");

                // 处理视频消息请求
                // respContent = "您发送的是视频消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE))
            {
                // 媒体文件标识
                String mediaId = requestMap.get("MediaId");
                // 文件格式
                String format = requestMap.get("Format");
                // 语音识别结果
                String recognition = requestMap.get("Recognition");
                recognition = recognition.replaceAll("\\pP|\\pS", "");
                //根据关键词获取楼盘
                List<Article> list = getGardenByKey(recognition);

                // 如果楼盘存在，则组装图文信息
                if (null != list && 0 < list.size())
                {
                    // 回复图文消息
                    NewsMessage articMessage = new NewsMessage();
                    articMessage.setToUserName(fromUserName);
                    articMessage.setFromUserName(toUserName);
                    articMessage.setCreateTime(new Date().getTime());
                    articMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    articMessage.setFuncFlag(0);
                    articMessage.setArticleCount(list.size());
                    articMessage.setArticles(list);
                    return MessageUtil.messageToXml(articMessage);
                }

            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT))
            {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE))
                {
                    // 事件KEY值
                    String eventKey = requestMap.get("EventKey");
                    if (eventKey != null && !eventKey.equals(""))
                    {
                        eventKey = eventKey.replace("qrscene_", "");//二维码参数值
                    }
                    // 用于换取二维码图片
                    String ticket = requestMap.get("Ticket");

                    //存储用户信息
                    try
                    {
                        //获取该用户基本信息
                        WeixinUserInfo userInfo = UserInfo.getUserInfo(TokenThread.accessToken.getToken(), fromUserName);
                        if (userInfo != null)
                        {
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
                            builder.clear();
                            builder = null;

                            byte[] bytes = wxUser.toByteArray();

                            jedis.set(WX_USER_UNIONID.replace("[unionid]", userInfo.getUnionid()).getBytes(), bytes);
                            jedis.set(WX_USER_OPENID.replace("[openid]", userInfo.getOpenId()), userInfo.getUnionid());
                            jedis.zadd(WX_USER_LIST, Long.parseLong(userInfo.getSubscribeTime()), userInfo.getUnionid());
                        }
                    }
                    catch (Exception e)
                    {

                    }
                    finally
                    {
                        RedisServiceFactory.returnResource(jedisPool, jedis);
                    }


                    respContent = "您好，欢迎来到帮帮家园！\n" +
                            "帮帮，是您身边贴心可靠的购房帮手，我们致力于从购房者利益出发，为您提供：\n" +
                            "尊贵畅通的看房体验| 专业精准的置业建议| 安心无忧的接房保障\n" +
                            "全城楼盘随心选看，均可预约专车~~\n" +
                            "即刻<a href='http://a.app.qq.com/o/simple.jsp?pkgname=com.weiwuu.fairy'>下载APP</a>，就能在线咨询/预约帮帮/预约验房，更多惊喜服务等你开启！";
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE))
                {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，可以记录取消关注的时间
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN))
                {
                    // 事件KEY值
                    String eventKey = requestMap.get("EventKey");
                    // 用于换取二维码图片
                    String ticket = requestMap.get("Ticket");

                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置 (高级接口)
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION))
                {
                    // 纬度
                    String latitude = requestMap.get("Latitude");
                    // 经度
                    String longitude = requestMap.get("Longitude");
                    // 精度
                    String precision = requestMap.get("Precision");

                    // 坐标转换后的经纬度
                    String bd_lng = null;
                    String bd_lat = null;
                    // 调用接口转换坐标
                    UserLocation userLocation = BaiDuMapUtil.convertCoord(longitude, latitude);
                    if (null != userLocation)
                    {
                        bd_lng = userLocation.getBd_lng();
                        bd_lat = userLocation.getBd_lat();
                    }
                    // 保存用户地理位置
                    //                    Nearby.saveUserLocation(fromUserName, longitude, latitude, bd_lng, bd_lat);
                    //                    respContent = "";
                }

                // 扫码推事件
                else if (eventType.equals(MessageUtil.SCANCODE_PUSH))
                {
                    // 事件KEY值，由开发者在创建菜单时设定
                    String eventKey = requestMap.get("EventKey");
                    // 扫描类型，一般是qrcode
                    String scanType = requestMap.get("ScanType");

                    // 扫描结果，即二维码对应的字符串信息
                    String scanResult = requestMap.get("ScanResult");

                    System.out.println("扫描类型：" + scanType + ",扫描结果：" + scanResult);

                }
                // 扫码推事件且弹出“消息接收中”提示框
                else if (eventType.equals(MessageUtil.SCANCODE_WAITMSG))
                {
                    // 扫描类型，一般是qrcode
                    String scanType = requestMap.get("ScanType");

                    // 扫描结果，即二维码对应的字符串信息
                    String scanResult = requestMap.get("ScanResult");

                    System.out.println("扫描类型：" + scanType + ",扫描结果：" + scanResult);
                    respContent = scanType + "," + scanResult.replace("EAN_13", "");
                }
                // 弹出系统拍照发图的事件推送
                else if (eventType.equals(MessageUtil.PIC_SYSPHOTO))
                {
                    // 图片数量
                    String count = requestMap.get("Count");
                    // 图片的MD5值
                    String picMd5Sum = requestMap.get("PicMd5Sum");
                    System.out.println("数量：" + count + ",MD5:" + picMd5Sum);

                    respContent = "数量：" + count + ",MD5:" + picMd5Sum;
                }
                // 弹出拍照或者相册发图
                else if (eventType.equals(MessageUtil.PIC_PHOTO_OR_ALBUM))
                {
                    // 图片数量
                    String count = requestMap.get("Count");
                    // 图片的MD5值
                    String picMd5Sum = requestMap.get("PicMd5Sum");
                    System.out.println("数量：" + count + ",MD5:" + picMd5Sum);

                    respContent = "数量：" + count + ",MD5:" + picMd5Sum;
                }
                // 弹出地理位置选择器的事件推送
                else if (eventType.equals(MessageUtil.LOCATION_SELECT))
                {
                    // X坐标信息
                    String location_X = requestMap.get("Location_X");
                    // Y坐标信息
                    String location_Y = requestMap.get("Location_Y");
                    // 地理位置的字符串信息
                    String label = requestMap.get("Label");

                    //                    System.out.println("X：" + location_X + ",Y:" + location_Y + ",地理位置的字符串信息:" + label);
                    respContent = "X：" + location_X + ",Y:" + location_Y + ",地理位置的字符串信息:" + label;
                }

                // 点击菜单跳转链接时的事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW))
                {
                    // 事件KEY值，设置的跳转URL
                    String eventKey = requestMap.get("EventKey");

                }
                // 点击菜单拉取消息时的事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK))
                {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = requestMap.get("EventKey");

                    if (eventKey.equals("咨询帮帮"))
                    {
                        String jsonMsg = null;
                        //自动回复
                        jsonMsg = KeFuUtil.makeTextCustomMessage(fromUserName, "您有什么疑问，帮帮小跑为您服务。");
                        KeFuUtil.sendCustomMessage(TokenThread.accessToken.getToken(), jsonMsg);
                        // 转接到客服系统
                        BaseMessage baseMessage = new BaseMessage();
                        baseMessage.setToUserName(fromUserName);
                        baseMessage.setFromUserName(toUserName);
                        baseMessage.setCreateTime(new Date().getTime());
                        baseMessage.setMsgType(MessageUtil.TRANSFER_CUSTOMER_SERVICE);
                        return MessageUtil.messageToXml(baseMessage);
                    }
                    else if (eventKey.equals("帮帮故事"))
                    {
                        // 回复图文消息
                        NewsMessage articMessage = new NewsMessage();
                        articMessage.setToUserName(fromUserName);
                        articMessage.setFromUserName(toUserName);
                        articMessage.setCreateTime(new Date().getTime());
                        articMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                        articMessage.setFuncFlag(0);

                        Article article1 = new Article("帮帮故事：用心做最好", "他们是一群普通的年轻人，朝气，积极乐观；\n" +
                                "他们是一群平凡的年轻人， 吃苦，乐于分享；\n" +
                                "他们秉承为用户至上，愿意帮助他人；\n" +
                                "他们围绕以人为核心，希望折腾点有意思的。\n" +
                                "他们就是”帮帮“，为优质用户提供优质的购房服务！", "http://mmbiz.qpic.cn/mmbiz/JMPiavfxDQsoQNsoaXiadKicYerjgXM2NOCiaSeOjia1dnKrIyHZynC0u7gtgpoCwz3tGZdyNIceI8UZwNAZH9sDCRQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5", "http://mp.weixin.qq.com/s?__biz=MzI2OTA0ODE4Mw==&mid=401639913&idx=1&sn=bbabd247aaa58abb566c4cf830c87979#rd");
                        Article article2 = new Article("帮验房：给你的新房挑刺（附验房报告）", "业主是一位年轻女士，在医院工作，买了金地的房子，通知交房就来看，差点傻眼了，据说用惨不忍睹来形容，后来不断的发现问题，并安排整改，总算还稍微对得上眼。", "https://mmbiz.qlogo.cn/mmbiz/JMPiavfxDQsoQNsoaXiadKicYerjgXM2NOCgQOY3ngIpWEXu0kEBOQIeiagQRy9LDx33v8Ff76lm4rblm9COPNRpSw/0?wx_fmt=jpeg", "http://mp.weixin.qq.com/s?__biz=MzI2OTA0ODE4Mw==&mid=401639913&idx=2&sn=eb9b684db8cbd69e51266381db62f795#rd");
                        Article article3 = new Article("无服务不买房 买房找帮帮", "2016帮帮2.0启程，为更好为购房者提供更丰富、全面、优质的购房体验，让整个买房过程更爽，2016年伊始，借助暖冬行动，将帮帮VIP升级服务包带给需要他的人。从1月6日开始，帮帮将出现在昆明主城各个角落，带给您或者您身边的朋友不一样的惊喜。", "https://mmbiz.qlogo.cn/mmbiz/JMPiavfxDQsoQNsoaXiadKicYerjgXM2NOCTmP6dnoPzCd9MIkNMDibZmZqXuEnViaRGYu1icZLU8Nt5ichIQrrqeQUhw/0?wx_fmt=jpeg", "http://mp.weixin.qq.com/s?__biz=MzI2OTA0ODE4Mw==&mid=401639913&idx=3&sn=20b293d249965d27f2d30adb3b09aa2e#rd");
                        List<Article> list = new ArrayList<>();
                        list.add(article1);
                        list.add(article2);
                        list.add(article3);
                        articMessage.setArticleCount(list.size());
                        articMessage.setArticles(list);
                        return MessageUtil.messageToXml(articMessage);
                    }
                    else if (eventKey.equals("我来帮帮"))
                    {
                        try
                        {
                            // 回复图文消息
                            NewsMessage articMessage = new NewsMessage();
                            articMessage.setToUserName(fromUserName);
                            articMessage.setFromUserName(toUserName);
                            articMessage.setCreateTime(new Date().getTime());
                            articMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                            articMessage.setFuncFlag(0);
                            Article article1 = null;

                            //判定是否绑定
                            String unionid = null;
                            String openid_key = WX_USER_OPENID.replace("[openid]", fromUserName);
                            unionid = jedis.get(openid_key);
                            if(unionid!=null&&unionid.length()>0){
                                String ubu1 = WX_USER_BIND_UNIONID.replace("[typeid]", "1").replace("[unionid]", unionid);
                                String rs1 = jedis.get(ubu1);
                                if (rs1 == null || rs1.length() == 0){
                                    String ubu2 = WX_USER_BIND_UNIONID.replace("[typeid]", "2").replace("[unionid]", unionid);
                                    String rs2 = jedis.get(ubu2);
                                    if (rs2 == null || rs2.length() == 0){
                                        article1 = new Article("我来帮帮-我的信息", "", "http://cms.weiwuu" + ".com//uploads/w/wxwtzq1452242028/material/22289ff81ad6104f1b00e9f15c9304c1889eaa32.jpg", basePath + "/bbinfo.html?openid=" + fromUserName);
                                    }else{
                                        article1 = new Article("我来帮帮-我要绑定", "", "http://cms.weiwuu.com//uploads/w/wxwtzq1452242028/material/22289ff81ad6104f1b00e9f15c9304c1889eaa32.jpg", basePath + "/comeBB.html?openid=" + fromUserName);
                                    }
                                }else{
                                    article1 = new Article("我来帮帮-我的信息", "", "http://cms.weiwuu" + ".com//uploads/w/wxwtzq1452242028/material/22289ff81ad6104f1b00e9f15c9304c1889eaa32.jpg", basePath + "/bbinfo.html?openid=" + fromUserName);
                                }

                                List<Article> list = new ArrayList<>();
                                list.add(article1);
                                articMessage.setArticleCount(list.size());
                                articMessage.setArticles(list);
                                return MessageUtil.messageToXml(articMessage);
                            }
                            respContent = "";
                        }catch (Exception e){
                            LOGGER.error("我来帮帮失败："+e.getMessage());
                        }finally
                        {
                            RedisServiceFactory.returnResource(jedisPool,jedis);
                        }
                    }
                    else if (eventKey.equals("connectAs"))
                    {
                        respContent = "咨询电话：0871-64603146";
                    }
                    else if (eventKey.equals("vipParty"))
                    {
                        respContent = "";
                    }
                    else if (eventKey.equals("专车预约"))
                    {
                        // 回复图文消息
                        NewsMessage articMessage = new NewsMessage();
                        articMessage.setToUserName(fromUserName);
                        articMessage.setFromUserName(toUserName);
                        articMessage.setCreateTime(new Date().getTime());
                        articMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                        articMessage.setFuncFlag(0);

                        Article article = new Article("免费专车看房预约", "", basePath + "/img/callcar.jpg", basePath +
                                "/order.html?wxuid=" + fromUserName);

                        List<Article> list = new ArrayList<>();
                        list.add(article);
                        articMessage.setArticleCount(list.size());
                        articMessage.setArticles(list);
                        return MessageUtil.messageToXml(articMessage);
                    }
                    else if (eventKey.equals("推荐有礼"))
                    {
                        try
                        {
                            //判定是否绑定
                            String unionid = null;
                            String openid_key = WX_USER_OPENID.replace("[openid]", fromUserName);
                            unionid = jedis.get(openid_key);
                            if(null!=unionid&&unionid.length()>0){

                                // 回复图文消息
                                NewsMessage articMessage = new NewsMessage();
                                articMessage.setToUserName(fromUserName);
                                articMessage.setFromUserName(toUserName);
                                articMessage.setCreateTime(new Date().getTime());
                                articMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                                articMessage.setFuncFlag(0);
                                Article article1 = null;

                                String ubu = WX_USER_BIND_UNIONID.replace("[typeid]", "[1,2]").replace("[unionid]", unionid);
                                Set<String> keys = jedis.keys(ubu);
                                if (keys != null & keys.size() > 0)   //已绑定
                                {
                                    String code = null;
                                    for(String key:keys){
                                        code = jedis.get(key);
                                    }
                                    code = DES.encryptDES(code,DES.key);
                                    article1 = new Article("推荐有礼", "", "http://cms.weiwuu.com//uploads/w/wxwtzq1452242028/material/22289ff81ad6104f1b00e9f15c9304c1889eaa32.jpg", basePath + "/tuijian" +
                                            ".html?code=" + code);
                                }
                                else    //未绑定
                                {
                                    article1 = new Article("推荐有礼-为了能将礼品送到您手中，请先绑定。", "", "http://cms.weiwuu.com//uploads/w/wxwtzq1452242028/material/22289ff81ad6104f1b00e9f15c9304c1889eaa32.jpg", basePath + "/comeBB.html?openid=" + fromUserName);
                                }
                                List<Article> listA = new ArrayList<>();
                                listA.add(article1);
                                articMessage.setArticleCount(listA.size());
                                articMessage.setArticles(listA);
                                return MessageUtil.messageToXml(articMessage);
                            }else{
                                respContent = "";
                            }
                        }catch (Exception e){
                            LOGGER.error("推荐有礼失败："+e.getMessage());
                        }finally
                        {
                            RedisServiceFactory.returnResource(jedisPool,jedis);
                        }


                    }
                }
            }
            if (null != respContent && 0 < respContent.length())
            {
                textMessage.setContent(respContent);
                respMessage = MessageUtil.messageToXml(textMessage);
            }
            else
            {
                respMessage = "";
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return respMessage;
    }


}