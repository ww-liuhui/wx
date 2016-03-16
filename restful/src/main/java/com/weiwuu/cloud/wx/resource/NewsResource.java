package com.weiwuu.cloud.wx.resource;

import com.weiwuu.cloud.wx.dao.GardenDAO;
import com.weiwuu.cloud.wx.domain.Member;
import com.weiwuu.cloud.wx.entity.ResponseContent;
import com.weiwuu.cloud.wx.factory.RedisServiceFactory;
import com.weiwuu.cloud.wx.service.GardenService;
import com.weiwuu.protobuf.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Set;


/**
 * Created by hui on 2015/12/8.
 */
@Path("/news")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NewsResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsResource.class);
    // Redis Key's Prefix
    private static final String NEWS_ID = "news:serial:number";
    private static final String NEWS_TAG_ID = "news:tag:serial:number";
    private static final String NEWS_TOPIC_ID = "news:topic:serial:number";
    private static final String NEWS_FOLLOW_ID = "news:follow:serial:number";
    private static final String NEWS_TYPE_ID = "news:type:serial:number";
    private static final String NEWS = "news:master:";
    private static final String NEWS_LIST = "news:master:list:city:[cityId]";
    private static final String NEWS_MASTER = "news:master:type:[typeId]:city:[cityId]";
    private static final String NEWS_ROTATE = "news:rotate:type:[typeId]:city:[cityId]";

    private static final String NEWS_TAG_LIST = "news:tag:list:[tagId]:city:[cityId]";
    private static final String NEWS_TOPIC_LIST = "news:topic:list:[topicId]:city:[cityId]";
    private static final String NEWS_RELATE_LIST = "news:relate:list:[newsId]:city:[cityId]";

    private static final String NEWS_TOTAL_COUNT = "news:total:count";
    private static final String NEWS_TAG = "news:tag:";
    private static final String NEWS_TOPIC = "news:topic:";
    private static final String NEWS_TYPE = "news:type:";
    private static final String NEWS_FOLLOW = "news:follow:";

    private static final String NEWS_FOLLOW_LIST = "news:follow:list:[newsId]";
    private static final String NEWS_IDS_STATISTICS = "news:[newsId]:statistics";
    private static final String NEWS_SUBSCRIBE = "news:subscribe";

    @NotNull
    private final JedisPool jedisPool;

    private final GardenService gs;

    public NewsResource(JedisPool jedisPool,GardenDAO dao)
    {
        this.jedisPool = jedisPool;
        this.gs = new GardenService(dao);
    }


    /***
     * 根据ID获取资讯
     *
     * @return
     */
    @GET
    @Path("getNewsById")
    @Produces("application/octet-stream")
    public byte[] getNewsById(@QueryParam("id") @NotNull int id)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            byte[] newsVal = null;
            newsVal = jedis.get((NEWS + id).getBytes());
            return newsVal;
        }
        catch (Exception e)
        {
            LOGGER.error("获取资讯失败：" + e.getMessage());
            return null;
        }
        finally
        {
            RedisServiceFactory.returnResource(jedisPool, jedis);
        }

    }

    /***
     * 添加评论
     * @param request
     * @return
     */
    @POST
    @Path("createNewsFollow")
    @Consumes("application/octet-stream")
    public ResponseContent createNewsFollow(@Context HttpServletRequest request)
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
            NewsFollow newsFollow = NewsFollow.parseFrom(bytes);
            long id = jedis.incr(NEWS_FOLLOW_ID);
            newsFollow = newsFollow.toBuilder().setId((int) id).build();
            String time = newsFollow.getIssueTime().replace("-", "").replace(" ", "").replace(":", "");
            long score = 0;
            try
            {
                score = Long.parseLong(time);
            }
            catch (Exception e)
            {
            }
            byte[] newsFollowBytes = newsFollow.toByteArray();
            String nif = NEWS_FOLLOW_LIST.replace("[newsId]",newsFollow.getNewsId()+"");
            jedis.set((NEWS_FOLLOW + id).getBytes(), newsFollowBytes);
            jedis.zadd(nif,score,newsFollow.getId()+"");

            return rc.build(1, "success", 1, id);

        }
        catch (Exception e)
        {
            LOGGER.error("添加评论失败：" + e.getMessage());
            e.printStackTrace();
            return rc.build(0, "fail", 0, null);
        }
        finally
        {
            RedisServiceFactory.returnResource(jedisPool, jedis);
        }

    }

    /***
     * 获取评论列表
     * @return
     */
    @GET
    @Path("getFollowList")
    @Produces("application/octet-stream")
    public byte[] getFollowList(@QueryParam("id") @DefaultValue("0") int id)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            NewsList.Builder builder = NewsList.newBuilder();

            String nif = NEWS_FOLLOW_LIST.replace("[newsId]",id+"");

            Set<String> ids = jedis.zrange(nif, 0,-1);
            byte[] followVal = null;
            NewsFollow follow = null;
            int authorId = 0;
            Member member = null;
            for (String itemId : ids)
            {
                followVal = jedis.get((NEWS_FOLLOW + itemId).getBytes());
                follow = NewsFollow.parseFrom(followVal);
                //根据用户ID，获取用户名称和头像
                authorId = follow.getAuthorId();
                LOGGER.info("authorId:"+authorId);
                if(authorId>0){
                    member = gs.getMember(authorId);
                    LOGGER.info("member:"+ member.getName()+","+ member.getAvatarUrl());
                    if(member !=null){
                        follow = follow.toBuilder().setAuthorNickName(member.getName()).setAuthorAvatarURL(member
                                .getAvatarUrl()).build();
                    }
                }
                LOGGER.info("follow:" + follow.getAuthorNickName() + "," + follow.getAuthorAvatarURL());
                builder.addNewsFollowList(follow);
            }
            NewsList list = builder.build();
            builder.clear();
            builder = null;

            byte[] rst = list.toByteArray();

            return rst;

        }
        catch (Exception e)
        {
            LOGGER.error("获取评论列表失败：" + e.getMessage());
            //            e.printStackTrace();
            return null;
        }
        finally
        {
            RedisServiceFactory.returnResource(jedisPool, jedis);
        }

    }

    /***
     * 更新评论：喜欢数
     * @param id
     * @return
     */
    @PUT
    @Path("editFollowLike")
    @Consumes("application/octet-stream")
    public ResponseContent editFollowLike(@QueryParam("id") @DefaultValue("0") int id)
    {
        ResponseContent rc = new ResponseContent();
        Jedis jedis = jedisPool.getResource();
        String data = null;
        try
        {
            byte[] followVal = null;
            NewsFollow follow = null;

            followVal = jedis.get((NEWS_FOLLOW + id).getBytes());
            follow = NewsFollow.parseFrom(followVal);
            follow = follow.toBuilder().setAssentCount(follow.getAssentCount()+1).build();

            byte[] newsFollowBytes = follow.toByteArray();
            jedis.set((NEWS_FOLLOW + id).getBytes(), newsFollowBytes);

            return rc.build(1, "success", 1, 1);

        }
        catch (Exception e)
        {
            LOGGER.error("更新喜欢数失败：" + e.getMessage());
            e.printStackTrace();
            return rc.build(0, "fail", 0, null);
        }
        finally
        {
            RedisServiceFactory.returnResource(jedisPool, jedis);
        }

    }
    /***
     * 更新评论：反对数
     * @param id
     * @return
     */
    @PUT
    @Path("editFollowUnlike")
    @Consumes("application/octet-stream")
    public ResponseContent editFollowUnlike(@QueryParam("id") @DefaultValue("0") int id)
    {
        ResponseContent rc = new ResponseContent();
        Jedis jedis = jedisPool.getResource();
        String data = null;
        try
        {
            byte[] followVal = null;
            NewsFollow follow = null;

            followVal = jedis.get((NEWS_FOLLOW + id).getBytes());
            follow = NewsFollow.parseFrom(followVal);
            follow = follow.toBuilder().setOpposeCount(follow.getOpposeCount() + 1).build();

            byte[] newsFollowBytes = follow.toByteArray();
            jedis.set((NEWS_FOLLOW + id).getBytes(), newsFollowBytes);

            return rc.build(1, "success", 1, 1);

        }
        catch (Exception e)
        {
            LOGGER.error("更新反对数失败：" + e.getMessage());
            e.printStackTrace();
            return rc.build(0, "fail", 0, null);
        }
        finally
        {
            RedisServiceFactory.returnResource(jedisPool, jedis);
        }

    }
}
