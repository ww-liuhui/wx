package com.weiwuu.cloud.wx.resource;

import com.weiwuu.cloud.wx.dao.GardenDAO;
import com.weiwuu.cloud.wx.domain.wx.SNSUserInfo;
import com.weiwuu.cloud.wx.domain.wx.WeixinOauth2Token;
import com.weiwuu.cloud.wx.domain.wx.WeixinUserInfo;
import com.weiwuu.cloud.wx.entity.ResponseContent;
import com.weiwuu.cloud.wx.entity.response.ClientResponse;
import com.weiwuu.cloud.wx.entity.wx.resp.Article;
import com.weiwuu.cloud.wx.factory.ApiFactory;
import com.weiwuu.cloud.wx.factory.RedisServiceFactory;
import com.weiwuu.cloud.wx.service.GardenService;
import com.weiwuu.cloud.wx.service.WXCoreService;
import com.weiwuu.cloud.wx.util.wx.*;
import com.weiwuu.protobuf.CallCar;
import com.weiwuu.protobuf.CallCarManager;
import com.weiwuu.protobuf.NewsFollow;
import com.weiwuu.protobuf.WXUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import sun.misc.BASE64Decoder;

import javax.crypto.spec.DESKeySpec;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;


@Path("/wxapi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WXResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WXResource.class);
    private static final String basePath = "http://wx.weiwuu.com/wx";

    private static final String WX_USER_UNIONID = "wx:user:unionid:[unionid]";
    private static final String WX_USER_OPENID = "wx:user:openid:[openid]";
    private static final String WX_USER_USERID = "wx:user:userid:[userid]";
    private static final String WX_USER_LIST = "wx:user:list";
    private static final String WX_USER_BIND_CODE = "wx:user:type:[typeid]:code:[code]";
    private static final String WX_USER_BIND_UNIONID = "wx:user:type:[typeid]:unionid:[unionid]";

    private static final String CALL_CAR_ID = "wx:callcar:serial:number";
    private static final String CALL_CAR = "wx:callcar:master:";
    private static final String CALL_CAR_LIST = "wx:callcar:list:city:[cityId]";
    private static final String CALL_CAR_STATUS = "wx:callcar:list:city:[cityId]:status:[status]";
    private static final String CALL_CAR_USER = "wx:callcar:list:user:[unionid]";

    private static final String CALL_CAR_MANAGER_ID = "wx:callcar:manager:serial:number";
    private static final String CALL_CAR_MANAGER = "wx:callcar:manager:master:";
    private static final String CALL_CAR_MANAGER_LIST = "wx:callcar:manager:list:city:[cityId]";

    private final ApiFactory apiFactory;
    private final String token;
    private final String encodingAesKey;
    private WXCoreService wxCoreService;
    @NotNull
    private final JedisPool jedisPool;

    public WXResource(ApiFactory apiFactory, String appId, String appSecret, String token, String encodingAesKey, GardenDAO dao, JedisPool jedisPool, String appId2, String appSecret2)
    {
        this.apiFactory = apiFactory;
        this.token = token;
        this.encodingAesKey = encodingAesKey;
        this.wxCoreService = new WXCoreService(apiFactory, dao);
        this.jedisPool = jedisPool;

        TokenThread.appid = appId;
        TokenThread.appsecret = appSecret;
        TokenThread.appid2 = appId2;
        TokenThread.appsecret2 = appSecret2;
        TokenThread.jedisPool = jedisPool;
        // 未配置appid、appsecret时给出提示
        if ("".equals(appId) || "".equals(appSecret))
        {
            LOGGER.error("appid and appsecret configuration error, please check carefully.");
        }
        else
        {
            // 启动定时获取access_token的线程
            new Thread(new TokenThread()).start();
        }
    }

    /***
     * 验证服务器地址的有效性
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GET
    @Path("getin")
    public String getinGet(@QueryParam("signature") String signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce, @QueryParam("echostr") String echostr)
    {
        try
        {
            if (signature != null && !signature.equals(""))
            {
                // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
                if (SignUtil.checkSignature(token, signature, timestamp, nonce))
                {
                    return echostr;
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("验证服务器地址的有效性:失败" + e.getMessage());
        }
        return null;
    }

    /***
     * 处理微信服务器发来的消息
     *
     * @param request
     * @return
     */
    @POST
    @Path("getin")
    @Consumes(MediaType.WILDCARD)
    public String getinPost(@Context HttpServletRequest request, @QueryParam("signature") String signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce, @QueryParam("encrypt_type") String encrypt_type, @QueryParam("msg_signature") String msg_signature)
    {
        String respMessage = "";
        try
        {
            if (encrypt_type.equals("aes"))
            {
                // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
                request.setCharacterEncoding("UTF-8");
                // 调用核心业务类接收消息、处理消息
                respMessage = wxCoreService.processRequest(request, msg_signature, timestamp, nonce, token, encodingAesKey, TokenThread.appid, jedisPool);

                // 数据加密
                try
                {
                    WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, TokenThread.appid);
                    respMessage = pc.encryptMsg(respMessage, timestamp, nonce);
                    return respMessage;
                }
                catch (AesException e)
                {
                    LOGGER.error("处理微信服务器发来的消息:加密失败" + e.getMessage());
                    return "";
                }
            }

        }
        catch (Exception e)
        {
            LOGGER.error("处理微信服务器发来的消息:失败" + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    /***
     * 处理微信发来的消息
     *
     * @param request
     * @return
     */
    @POST
    @Path("getins")
    @Consumes(MediaType.WILDCARD)
    public String getins(@Context HttpServletRequest request, @QueryParam("signature") String signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce, @QueryParam("apitoken") String apitoken, @QueryParam("fromthirdapi") String fromthirdapi)
    {

        String respMessage = "";
        try
        {
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding("UTF-8");
            // 调用核心业务类接收消息、处理消息
            try
            {
                respMessage = wxCoreService.processRequest(request, signature, timestamp, nonce, token, encodingAesKey, TokenThread.appid, jedisPool);
                return respMessage;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            LOGGER.error("处理微信服务器发来的消息:失败" + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    /***
     * 通过config接口注入权限验证配置
     *
     * @param url
     * @return
     */
    @GET
    @Path("validate")
    public ResponseContent validate(@QueryParam("url") String url)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            StringBuilder strGroup = new StringBuilder();
            String nonceStr = null;
            long timestamp = 0;
            String signature = null;
            String ticket = null;
            String appid = TokenThread.appid;
            ticket = TokenThread.ticket;
            long time = new Date().getTime();
            //生产随机字符串
            nonceStr = UUID.randomUUID().toString();
            //时间戳
            timestamp = Math.round(time / 1000);
            StringBuilder str = new StringBuilder();
            str = str.append("jsapi_ticket=").append(ticket).append("&noncestr=").append(nonceStr).append("&timestamp=").append(timestamp).append("&url=").append(url);
            System.out.println("str:" + str);
            //签名
            signature = Sha1Util.getSha1(str.toString());
            System.out.println("signature:" + signature);
            //组装返回数据
            strGroup.append(appid).append(",").append(timestamp).append(",").append(nonceStr).append(",").append(signature);
            System.out.println("strGroup:" + strGroup);
            return rc.build(1, "success", 1, strGroup);
        }
        catch (Exception e)
        {
            LOGGER.error("验证配置接口失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "验证配置接口失败", 0, null);
    }

    /***
     * 获取微信授权页面地址
     *
     * @param url
     * @return
     */
    @GET
    @Path("getoauthurl")
    public ResponseContent getoauthurl(@QueryParam("url") String url, @QueryParam("scope") String scope)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            String oauthurl = null;
            if (scope.trim().equals("snsapi_userinfo"))
            {
                oauthurl = OAuthUtil.getUrlInfo(url, TokenThread.appid2);
            }
            else if (scope.trim().equals("snsapi_base"))
            {
                oauthurl = OAuthUtil.getUrlBase(url, TokenThread.appid2);
            }
            else
            {
                return rc.build(0, "scope值有误", 0, null);
            }

            return rc.build(1, "success", 1, oauthurl);
        }
        catch (Exception e)
        {
            LOGGER.error("获取微信授权页面地址失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "获取微信授权页面地址失败", 0, null);
    }

    /***
     * 微信授权后的回调请求处理
     *
     * @param code
     * @return
     */
    @GET
    @Path("oauth")
    public ResponseContent oauth(@QueryParam("code") String code)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            // 用户同意授权
            if (!"authdeny".equals(code))
            {
                // 获取网页授权access_token
                //                WeixinOauth2Token weixinOauth2Token = OAuthUtil
                //                        .getOauth2AccessToken(TokenThread.appid,TokenThread.appsecret, code);

                WeixinOauth2Token weixinOauth2Token = OAuthUtil.getOauth2AccessToken(TokenThread.appid2, TokenThread.appsecret2, code);

                // 网页授权接口访问凭证
                String accessToken = weixinOauth2Token.getAccessToken();
                // 用户标识
                String openId = weixinOauth2Token.getOpenId();
                // 获取用户信息
                SNSUserInfo snsUserInfo = OAuthUtil.getSNSUserInfo(accessToken, openId);

                return rc.build(1, "success", 1, snsUserInfo);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("回调请求处理失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "回调请求处理失败", 0, null);
    }

    /***
     * 添加临时素材
     *
     * @param type 素材类型
     * @return
     */
    @GET
    @Path("addTemporaryMedia")
    public ResponseContent addTemporaryMedia(@QueryParam("type") String type)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            Client client = ClientBuilder.newClient();
            String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
            url = url.replace("ACCESS_TOKEN", TokenThread.accessToken.getToken()).replace("TYPE", "image");
            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);

            return rc.build(1, "success", 1, clientResponse.getBody().getData());
        }
        catch (Exception e)
        {
            LOGGER.error("添加临时素材失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "添加临时素材失败", 0, null);
    }


    /***
     * 添加约车
     *
     * @param request
     * @return
     */
    @POST
    @Path("callCar")
    @Consumes("application/octet-stream")
    public ResponseContent callCar(@Context HttpServletRequest request)
    {
        ResponseContent rc = new ResponseContent();
        Jedis jedis = jedisPool.getResource();
        String data = null;
        try
        {
            ServletInputStream servletInputStream = request.getInputStream();
            data = IOUtils.toString(servletInputStream, "utf-8");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(data);
            CallCar callCar = CallCar.parseFrom(bytes);
            long id = jedis.incr(CALL_CAR_ID);
            callCar = callCar.toBuilder().setId((int) id).build();
            String unionid = jedis.get(WX_USER_OPENID.replace("[openid]", callCar.getWxuid()));
            String userId = null;
            //分配帮帮
            if (callCar.getBbid() == null || callCar.getBbid().length() == 0)
            {
                Client client = ClientBuilder.newClient();
                String url = this.apiFactory.getHost() + "/broker/brokerList?cityId=" + callCar.getCityId() + "&PageSize=100000&PageIndex=0";
                WebTarget target = client.target(url);
                Response response = target.request().get();
                ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);
                ArrayList list = (ArrayList) clientResponse.getBody().getData();
                int data_length = list.size();
                if (list.size() > 0)
                {
                    Random random = new Random();
                    int index = random.nextInt(data_length);
                    LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap) list.get(index);
                    userId = linkedHashMap.get("Id") + "";
                    callCar = callCar.toBuilder().setBbid(userId).setBbname(linkedHashMap.get("name")).setBbtel(linkedHashMap.get("mobile")).build();
                }
            }
            else
            {
                userId = callCar.getBbid();
            }

            Transaction tc = jedis.multi();
            byte[] callCarBytes = callCar.toByteArray();

            tc.set((CALL_CAR + id).getBytes(), callCarBytes);

            long score = new Date().getTime();

            String ccl = CALL_CAR_LIST.replace("[cityId]", callCar.getCityId() + "");
            tc.zadd(ccl, score, id + "");

            String ccs = CALL_CAR_STATUS.replace("[cityId]", callCar.getCityId() + "").replace("[status]", callCar.getStatus() + "");
            tc.zadd(ccs, score, id + "");


            if (unionid != null && unionid.length() > 0)
            {
                String ccu = CALL_CAR_USER.replace("[unionid]", unionid);
                tc.zadd(ccu, score, id + "");
            }
            tc.exec();

            //获取约车管理员
            String jsonMsg = null;
            String listKey = CALL_CAR_MANAGER_LIST.replace("[cityId]", callCar.getCityId() + "");
            Set<String> idlist = jedis.zrange(listKey, 0, -1);//获取管理员列表
            byte[] bytesData = null;
            String unionid_m = null;

            List<Article> articleList = new ArrayList<>();
            String description = "姓名：" + callCar.getName() + "\n\n" + "电话：" + callCar.getTel() + "\n\n" + "时间：" + callCar.getTime() + "\n\n" + "出发地点：" + callCar.getAddrFrom() + "\n\n" + "目标楼盘：" + callCar.getAddrTo();
            //LOGGER.debug("描述信息："+description);
            Article article = new Article("新的约车信息，请尽快核实并派车！", description, "", basePath + "/order.html?wxuid=" + callCar.getWxuid());
            articleList.add(article);

            //通知约车信息给帮帮
            try
            {
                String userKey = WX_USER_USERID.replace("[userid]", userId);
                String userOpenid = jedis.get(userKey);
                jsonMsg = KeFuUtil.makeNewsCustomMessage(userOpenid, articleList);
                KeFuUtil.sendCustomMessage(TokenThread.accessToken.getToken(), jsonMsg);
            }
            catch (Exception e)
            {
                //                e.printStackTrace();
            }
            //通知约车信息给管理员
            for (String ccmid : idlist)
            {
                try
                {
                    jsonMsg = null;
                    bytesData = jedis.get((CALL_CAR_MANAGER + ccmid).getBytes());//获取管理员信息
                    CallCarManager callCarManager = CallCarManager.parseFrom(bytesData);
                    unionid_m = callCarManager.getUnionid();
                    bytesData = jedis.get(WX_USER_UNIONID.replace("[unionid]", unionid_m).getBytes());
                    WXUser wxUser = WXUser.parseFrom(bytesData);

                    jsonMsg = KeFuUtil.makeNewsCustomMessage(wxUser.getOpenId(), articleList);
                    KeFuUtil.sendCustomMessage(TokenThread.accessToken.getToken(), jsonMsg);
                }
                catch (Exception e)
                {
                    //                    e.printStackTrace();
                    continue;
                }
            }

            return rc.build(1, "success", 1, id);

        }
        catch (Exception e)
        {
            jedis.decr(CALL_CAR_ID);
            LOGGER.error("添加约车失败：" + e.getMessage());
            e.printStackTrace();
            return rc.build(0, "fail", 0, null);
        }
        finally
        {
            RedisServiceFactory.returnResource(jedisPool, jedis);
        }
    }

    /***
     * 根据openId获取该用户的最新约车信息
     *
     * @return
     */
    @GET
    @Path("getOrderByWxuid")
    @Produces("application/octet-stream")
    public byte[] getOrderByWxuid(@QueryParam("wxuid") @NotNull String wxuid)
    {
        Jedis jedis = jedisPool.getResource();
        Set<String> set = null;
        try
        {
            byte[] val = null;
            String unionid = jedis.get(WX_USER_OPENID.replace("[openid]", wxuid));
            if (unionid != null && unionid.length() > 0)
            {
                String ccu = CALL_CAR_USER.replace("[unionid]", unionid);
                set = jedis.zrevrange(ccu, 0, 0);
            }
            for (String id : set)
            {
                val = jedis.get((CALL_CAR + id).getBytes());
            }
            return val;
        }
        catch (Exception e)
        {
            LOGGER.error("获取约车信息失败：" + e.getMessage());
            return null;
        }
        finally
        {
            RedisServiceFactory.returnResource(jedisPool, jedis);
        }

    }

    /***
     * 用户绑定，我来帮帮
     *
     * @param mobile
     * @param openid
     * @param typeId
     * @return
     */
    @GET
    @Path("comeBB")
    @Consumes("application/octet-stream")
    public ResponseContent callCar(@QueryParam("mobile") @NotNull String mobile, @QueryParam("openid") @NotNull String openid, @QueryParam("typeId") @NotNull int typeId)
    {
        ResponseContent rc = new ResponseContent();
        Jedis jedis = jedisPool.getResource();
        String unionid = null;
        try
        {
            String openid_key = WX_USER_OPENID.replace("[openid]", openid);
            unionid = jedis.get(openid_key);

            byte[] val = null;
            if (unionid != null && unionid.length() > 0)
            {

                String wuu = WX_USER_UNIONID.replace("[unionid]", unionid);
                val = jedis.get(wuu.getBytes());
                WXUser wxUser = WXUser.parseFrom(val);
                wxUser = wxUser.toBuilder().setType(typeId).setTel(mobile).build();
                val = wxUser.toByteArray();
                Transaction tc = jedis.multi();
                //添加数据
                String ubc = WX_USER_BIND_CODE.replace("[typeid]", typeId + "").replace("[code]", mobile);
                tc.set(ubc, unionid);
                String ubu = WX_USER_BIND_UNIONID.replace("[typeid]", typeId + "").replace("[unionid]", unionid);
                tc.set(ubu, mobile);
                //更新用户信息
                tc.set(wuu.getBytes(), val);
                tc.exec();

                //绑定帮帮
                if (typeId == 2)
                {
                    wxCoreService.bindBB(mobile, openid, jedis);
                }

                return rc.build(1, "success", 1, 1);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("用户绑定失败：" + e.getMessage());
            e.printStackTrace();
            return rc.build(0, "fail", 0, null);
        }
        finally
        {
            RedisServiceFactory.returnResource(jedisPool, jedis);
        }
        return rc.build(0, "fail", 0, null);
    }

    /***
     * 根据openId获取用户信息
     *
     * @return
     */
    @GET
    @Path("getUserInfo")
    @Produces("application/octet-stream")
    public byte[] getUserInfo(@QueryParam("openid") @NotNull String openid)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            byte[] val = null;
            String unionid = jedis.get(WX_USER_OPENID.replace("[openid]", openid));
            if (unionid != null && unionid.length() > 0)
            {
                String wuu = WX_USER_UNIONID.replace("[unionid]", unionid);
                val = jedis.get(wuu.getBytes());
            }

            return val;
        }
        catch (Exception e)
        {
            LOGGER.error("获取用户信息失败：" + e.getMessage());
            return null;
        }
        finally
        {
            RedisServiceFactory.returnResource(jedisPool, jedis);
        }

    }
}
