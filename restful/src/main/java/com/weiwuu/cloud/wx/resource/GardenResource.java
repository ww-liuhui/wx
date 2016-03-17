package com.weiwuu.cloud.wx.resource;


import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.weiwuu.cloud.wx.dao.GardenDAO;
import com.weiwuu.cloud.wx.entity.CreateGroupRequest;
import com.weiwuu.cloud.wx.entity.ResponseContent;
import com.weiwuu.cloud.wx.entity.response.CityResponse;
import com.weiwuu.cloud.wx.entity.response.ClientResponse;
import com.weiwuu.cloud.wx.factory.ApiFactory;
import com.weiwuu.cloud.wx.factory.RedisServiceFactory;
import com.weiwuu.cloud.wx.service.GardenService;
import com.weiwuu.cloud.wx.util.wx.DES;
import com.weiwuu.cloud.wx.util.wx.KeFuUtil;
import com.weiwuu.cloud.wx.util.wx.TokenThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Path("/garden")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GardenResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GardenResource.class);
    private final String WX_USER_UNIONID = "wx:user:unionid:[unionid]";
    private final String WX_USER_OPENID = "wx:user:openid:[openid]";
    private final String WX_USER_LIST = "wx:user:list";
    private static final String WX_USER_USERID = "wx:user:userid:[userid]";
    private static final String WX_USER_BIND_UNIONID = "wx:user:type:[typeid]:unionid:[unionid]";

    private final GardenService gs;
    private final ApiFactory apiFactory;
    @NotNull
    private final JedisPool jedisPool;

    public GardenResource(GardenDAO dao, ApiFactory apiFactory, JedisPool jedisPool)
    {
        this.gs = new GardenService(dao);
        this.apiFactory = apiFactory;
        this.jedisPool = jedisPool;
    }

    /***
     * 获取楼盘过滤条件
     *
     * @param city_id
     * @return
     */
    @GET
    @Path("getFilters")
    public ResponseContent importGarden(@QueryParam("city_id") String city_id)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() + "/garden/getHotFilter?cityId=" + city_id;
            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);

            return rc.build(1, "success", 1, clientResponse.getBody().getData());
        }
        catch (Exception e)
        {
            LOGGER.error("获取过滤条件失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "获取过滤条件失败", 0, null);
    }

    /***
     * 获取城市楼盘列表
     *
     * @param city_id
     * @param page_size
     * @param page_index
     * @return
     */
    @GET
    @Path("getGardenList")
    public ResponseContent getGardenList(@QueryParam("city_id") int city_id, @QueryParam("page_size") int page_size, @QueryParam("page_index") int page_index)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() + "/garden/bycity?cityid=" + city_id + "&PageSize=" + page_size + "&PageIndex=" + page_index;
            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);

            return rc.build(1, "success", clientResponse.getHead().getItemCount(), clientResponse.getBody().getData());
        }
        catch (Exception e)
        {
            LOGGER.error("获取过滤条件失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "获取过滤条件失败", 0, null);
    }

    /***
     * 根据过滤信息获取楼盘列表
     *
     * @param city_id
     * @param type_id
     * @param range_id
     * @param district_id
     * @param mark_id
     * @param page_size
     * @param page_index
     * @return
     */
    @GET
    @Path("getFilterGardenList")
    public ResponseContent getFilterGardenList(@QueryParam("city_id") int city_id, @QueryParam("type_id") int type_id, @QueryParam("range_id") int range_id, @QueryParam("district_id") int district_id, @QueryParam("mark_id") int mark_id, @QueryParam("page_size") int page_size, @QueryParam("page_index") int page_index)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() + "/garden/hotsale?typeid=" + type_id + "&rangeid=" + range_id + "&districtid=" + district_id + "&markid=" + mark_id + "&cityid=" + city_id + "&PageSize=" + page_size + "&PageIndex=" + page_index;
            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);

            return rc.build(1, "success", clientResponse.getHead().getItemCount(), clientResponse.getBody().getData());
        }
        catch (Exception e)
        {
            LOGGER.error("获取楼盘列表失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "获取楼盘列表失败", 0, null);
    }

    /***
     * 根据楼盘ID获取楼盘详情
     *
     * @param id
     * @return
     */
    @GET
    @Path("getGardenDetail")
    public ResponseContent getGardenDetail(@QueryParam("id") int id)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() + "/garden/gardenInfoById?gardenid=" + id;
            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);

            return rc.build(1, "success", 1, clientResponse.getBody().getData());
        }
        catch (Exception e)
        {
            LOGGER.error("获取楼盘详情失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "获取楼盘详情失败", 0, null);
    }

    /***
     * 获取楼盘评论
     *
     * @param id
     * @param page_size
     * @param page_index
     * @return
     */
    @GET
    @Path("getGardenComment")
    public ResponseContent getGardenComment(@QueryParam("id") @NotNull int id, @QueryParam("page_size") @DefaultValue("20") int page_size, @QueryParam("page_index") @DefaultValue("0") int page_index)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() + "/garden/lstGardenComment?gardenId=" + id + "&pageIndex=" + page_index + "&pageSize=" + page_size;

            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);

            return rc.build(1, "success", 1, clientResponse.getBody().getData());
        }
        catch (Exception e)
        {
            LOGGER.error("获取楼盘评论失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "获取楼盘评论失败", 0, null);
    }

    /***
     * 获取户型评论
     *
     * @param id
     * @param page_size
     * @param page_index
     * @return
     */
    @GET
    @Path("getHouseComment")
    public ResponseContent getHouseComment(@QueryParam("id") @NotNull int id, @QueryParam("page_size") @DefaultValue("20") int page_size, @QueryParam("page_index") @DefaultValue("0") int page_index)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() + "/garden/lstHouseComment?houseId" + id + "&pageIndex=" + page_index + "&pageSize=" + page_size;

            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);

            return rc.build(1, "success", 1, clientResponse.getBody().getData());
        }
        catch (Exception e)
        {
            LOGGER.error("获取户型评论失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "获取户型评论失败", 0, null);
    }

    /***
     * 根据户型ID获取户型详情
     *
     * @param id
     * @return
     */
    @GET
    @Path("getHouseTypeDetail")
    public ResponseContent getHouseTypeDetail(@QueryParam("id") @NotNull int id)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() + "/garden/houseInfo?houseId=" + id;
            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);

            return rc.build(1, "success", 1, clientResponse.getBody().getData());
        }
        catch (Exception e)
        {
            LOGGER.error("获取户型详情失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "获取户型详情失败", 0, null);
    }

    /***
     * 根据关键词获取楼盘列表
     *
     * @param city_id
     * @param key
     * @param page_size
     * @param page_index
     * @return
     */
    @GET
    @Path("getGardenListByKey")
    public ResponseContent getGardenListByKey(@QueryParam("city_id") @NotNull int city_id, @QueryParam("key") @NotNull String key, @QueryParam("page_size") @DefaultValue("10") int page_size, @QueryParam("page_index") @DefaultValue("0") int page_index)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            key = java.net.URLEncoder.encode(key, "utf-8");
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() + "/garden/bykey?key=" + key + "&cityid=" + city_id + "&PageSize=" + page_size + "&PageIndex=" + page_index;
            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);

            return rc.build(1, "success", 1, clientResponse.getBody().getData());
        }
        catch (Exception e)
        {
            LOGGER.error("根据关键词获取楼盘列表失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "根据关键词获取楼盘列表失败", 0, null);
    }

    /***
     * 获取开通城市列表
     *
     * @return
     */
    @GET
    @Path("getCityList")
    public ResponseContent getCityList()
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            List<CityResponse> cityList = null;
            cityList = gs.getCityList();
            return rc.build(1, "success", cityList.size(), cityList);
        }
        catch (Exception e)
        {
            LOGGER.error("获取城市列表失败" + e.getMessage());
            //e.printStackTrace();
        }
        return rc.build(0, "获取城市列表失败", 0, null);
    }

    /***
     * 根据城市ID获取经济人列表
     *
     * @param city_id
     * @return
     */
    @GET
    @Path("getBrokerList")
    public ResponseContent getBrokerList(@QueryParam("city_id") @NotNull int city_id)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() + "/broker/brokerList?cityId=" + city_id + "&PageSize=100000&PageIndex=0";
            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);

            return rc.build(1, "success", 1, clientResponse.getBody().getData());
        }
        catch (Exception e)
        {
            LOGGER.error("根据城市ID获取经济人列表失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "根据城市ID获取经济人列表失败", 0, null);
    }

    /***
     * 获取验证码
     *
     * @param mobile
     * @param typeId
     * @return
     */
    @GET
    @Path("sendSMSRegister")
    public ResponseContent sendSMSRegister(@QueryParam("mobile") @NotNull String mobile, @QueryParam("typeId") @NotNull int typeId)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            int validCode = (int) (Math.random() * 9000 + 1000);//四位随机验证码
            Client client = ClientBuilder.newClient();
            String url = this.apiFactory.getHost() + "/member/sendSMSRegister?mobile=" + mobile + "&validCode=" +
                    validCode + "&typeId=" + typeId;
            WebTarget target = client.target(url);
            Response response = target.request().get();
            ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);
            if (clientResponse != null && clientResponse.getHead().getCode() == 1)
            {
                return rc.build(1, "success", 1, validCode);
            }

        }
        catch (Exception e)
        {
            LOGGER.error("获取验证码失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "获取验证码失败", 0, null);
    }

    /***
     * 推荐
     *
     * @param mobile 被推荐人手机号
     * @param code   推荐人手机号
     * @return
     */
    @GET
    @Path("recomRegister")
    @Consumes("application/octet-stream")
    public ResponseContent<Integer> recomRegister(@QueryParam("mobile") @NotNull String mobile, @QueryParam("code") @DefaultValue("") String code)
    {
        String key = "weiwuu16";
        ResponseContent rc = new ResponseContent();
        Jedis jedis = jedisPool.getResource();
        int flag = 0;
        try
        {
            if (code != null && code.length() > 0)
            {
                //解密code
                code = DES.decryptDES(code, key);
                flag = gs.recomRegister(mobile, code);
                return rc.build(1, "success", 1, flag);
            }

        }
        catch (Exception e)
        {
            LOGGER.error("推荐失败：" + e.getMessage());
            return rc.build(0, "fail", 0, 0);
        }
        finally
        {
            RedisServiceFactory.returnResource(jedisPool, jedis);
        }
        return rc.build(0, "fail", 0, 0);
    }

    /***
     * 创建群
     * @param cgr
     * @return
     */
    @POST
    @Path("creategroup")
    public ResponseContent createGroup(CreateGroupRequest cgr,@QueryParam("name") @NotNull String name,@QueryParam("avatar_url") @NotNull String avatar_url)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            int flag = 0;
            //添加用户
            flag = gs.createMember(cgr.getMcode(),name,avatar_url);
            if(flag==1||flag==2){
                Client client = ClientBuilder.newClient();
                String url = this.apiFactory.getImhost() + "/im/rc/creategroup";
                WebTarget target = client.target(url);
                Response response = target.request().post(Entity.entity(cgr, MediaType.APPLICATION_JSON_TYPE));
                ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);
                String groupid = (String)((LinkedHashMap)clientResponse.getBody().getData()).get("groupId");

                return rc.build(1, "success", 1,groupid);
            }else if(flag==3){
                return rc.build(3, "该号码已经注册过经纪人或置业顾问！", 0,null);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("创建群失败" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "创建群失败", 0, null);
    }

    /***
     * 预约看房
     * @param name
     * @param tel
     * @param time
     * @param addr_from
     * @param addr_to
     * @return
     */
    @GET
    @Path("createOrder")
    public ResponseContent createOrder(@QueryParam("name") @NotNull String name,@QueryParam("tel") @NotNull String tel,@QueryParam("time") @NotNull String time,@QueryParam("addr_from") String addr_from,@QueryParam("addr_to") String addr_to)
    {
        ResponseContent rc = new ResponseContent();
        try
        {
            int flag = gs.createOrder(name, tel, time, addr_from, addr_to);
            //通知管理员
            //通知约车信息给帮帮
            try
            {
                String jsonMsg = null;
                List<String> userOpenidList = new ArrayList<String>();
                userOpenidList.add("od7fZwp81A5ijHAYptbjceP_nxVY");
                userOpenidList.add("od7fZwrOggtol4vEB75GELng4gQA");
                userOpenidList.add("od7fZwn3um2OC_VYLhvMsmJJcZSU");
                String content = "姓名："+name+"\n电话："+tel+"\n看房时间："+time+"\n出发地点："+addr_from+"\n目的楼盘："+addr_to;
                for (String userOpenid:userOpenidList){
                    try
                    {
                        jsonMsg = KeFuUtil.makeTextCustomMessage(userOpenid,content);
                        KeFuUtil.sendCustomMessage(TokenThread.accessToken.getToken(), jsonMsg);
                    }catch (Exception e){
                    }
                }
            }
            catch (Exception e)
            {
                //                e.printStackTrace();
            }
            return rc.build(1, "success", 1, flag);
        }
        catch (Exception e)
        {
            LOGGER.error("预约看房失败" + e.getMessage());
            //e.printStackTrace();
        }
        return rc.build(0, "预约看房失败", 0, null);
    }

    /***
     * 抽奖
     * @param id
     * @param tel
     * @param name
     * @return
     */
    @GET
    @Path("lucky")
    public ResponseContent lucky(@QueryParam("id") @DefaultValue("0") int id,@QueryParam("tel") @DefaultValue("") String tel,@QueryParam("name") @DefaultValue("") String name)
    {
        ResponseContent rc = new ResponseContent();
        int code = 0;
        try
        {
            code = gs.lucky(id,tel,name);
            if(code>0){
                return rc.build(1, "恭喜您，中奖了！", 1, code);
            }else if(code==0){
                return rc.build(0, "很遗憾，没有中奖。", 0, null);
            }

        }
        catch (Exception e)
        {
            LOGGER.error("很遗憾，没有中奖" + e.getMessage());
            e.printStackTrace();
        }
        return rc.build(0, "很遗憾，没有中奖", 0, null);
    }
}