//package Test;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.weiwuu.cloud.wx.ApiApplication;
//import com.weiwuu.cloud.wx.ApiConfiguration;
//import com.weiwuu.cloud.wx.entity.response.ClientResponse;
//import com.weiwuu.cloud.wx.factory.RedisServiceFactory;
//import com.weiwuu.protobuf.NewsFollow;
//import io.dropwizard.jackson.Jackson;
//import io.dropwizard.jersey.setup.JerseyEnvironment;
//import io.dropwizard.setup.Environment;
//import net.sf.json.JSONObject;
//import org.junit.Test;
//import org.mockito.Mockito;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.Transaction;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.Response;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
///**
// * Created by xu_gang on 2015-05-03.
// * mail: shinetimes@hotmail.com
// */
//public class ApplicationTest
//{
//    private final Environment environment = Mockito.mock(Environment.class);
//    private final JerseyEnvironment jersey = Mockito.mock(JerseyEnvironment.class);
//    private final ApiApplication application = new ApiApplication();
//    private final ApiConfiguration config = new ApiConfiguration();
//
//    JedisPoolConfig poolConfig = new JedisPoolConfig();
//
//    final ObjectMapper MAPPER = Jackson.newObjectMapper();
//
//    JedisPool jedisPool = new JedisPool(poolConfig, "123.57.149.184", 6379, 1000, "weiwui2015");
//
//    Jedis jedis = jedisPool.getResource();
//
//    private final String WX_USER_OPENID = "wx:user:openid:[openid]";
//    private final String WX_USER_LIST = "wx:user:list";
//    private static final String WX_USER_USERID = "wx:user:userid:[userid]";
//    private static final String WX_USER_BIND_UNIONID = "wx:user:type:[typeid]:unionid:[unionid]";
//
//    public boolean isMobileNO(String mobiles) {
//        Pattern p = Pattern.compile("^1\\d{10}$");
//        Matcher m = p.matcher(mobiles);
//        return m.matches();
//    }
//    @Test
//    public void testKey()
//    {
//        try
//        {
//            Set<String> set = jedis.keys("im:message:mid:*");
//            System.out.println(set.size());
//            String sendtime = null;
//            String time = null;
//            int i = 0;
//            int max = 1562;
//            for(String key:set){
//                if(max==i){
//                    break;
//                }
//                try
//                {
//                    Map<String,String> map = null;
//                    map = jedis.hgetAll(key);
//                    sendtime = map.get("sendtime");
////                    System.out.println("sendtime:"+sendtime);
//                    Date d = new Date(Long.parseLong(sendtime));
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    time = sdf.format(d);
////                    System.out.println("time:"+time);
//                    if(time.equals("2016-01-19")){
//                        System.out.println("mid:"+map.get("mid"));
//                    }
//                }catch (Exception e){
//
//                }
//                i++;
//            }
//        }catch (Exception e){
//
//        }finally
//        {
//            RedisServiceFactory.returnResource(jedisPool, jedis);
//        }
//
////        Jedis jedis2 = jedisPool.getResource();
////        Transaction tc = jedis.multi();
////        tc.set("test","test");
////        String rs = jedis2.get("test");
////        tc.exec();
////        System.out.println("result:" +rs);
//
////        Client client = ClientBuilder.newClient();
////        String url = "http://proapi.weiwuu.net:8080" + "/broker/brokerList?cityId=" + 273 + "&PageSize=100000&PageIndex=0";
////        WebTarget target = client.target(url);
////        Response response = target.request().get();
////        ClientResponse clientResponse = (ClientResponse) response.readEntity(ClientResponse.class);
////        ArrayList list = (ArrayList) clientResponse.getBody().getData();
////        int data_length = list.size();
////        if (list.size() > 0)
////        {
////            Random random = new Random();
////            int index = random.nextInt(data_length);
////            LinkedHashMap linkedHashMap = (LinkedHashMap) list.get(index);
////            System.out.println(linkedHashMap.get("Id")+" , "+linkedHashMap.get("mobile")+" , "+linkedHashMap.get(""));
////        }
//        //
//        //        Set<String> key = jedis.keys("im:group:gid_bcode_mcode:*_bcode001_*");
//        //        System.out.println(key);
//
//    }
//
//    @Test
//    public void list() throws IOException
//    {
//        //        final String NEWS_MASTER = "news:master:type:[typeId]:city:[cityId]";
//        //        final String NEWS_ROTATE = "news:rotate:type:[typeId]:city:[cityId]";
//        String NEWS_FOLLOW = "news:follow:";
//        try
//        {
//            //            byte[] followVal = null;
//            //            NewsFollow follow = null;
//            //            followVal = jedis.get((NEWS_FOLLOW + "52").getBytes());
//            //            follow = NewsFollow.parseFrom(followVal);
//            //            System.out.println("userId:"+follow.getAuthorId());
//            //            String newsList1 = null;
//            //            String newsList2 = null;
//            //            newsList1 = NEWS_MASTER.replace("[cityId]", 273 + "").replace("[typeId]", 1 + "");
//            //            newsList2 = NEWS_ROTATE.replace("[cityId]", 273 + "").replace("[typeId]", 1 + "");
//            //            jedis.zunionstore("test",newsList1,newsList2);
//
//            //            long count = jedis.zcard("news:master:list:city:273");
//            //            System.out.println(count);
//            //            List<News> list = new ArrayList<News>();
//            //            String newsList = "news:master:list:city:[cityId]".replace("[cityId]", 273 + "");
//            //            Set<String> ids = jedis.zrevrange(newsList,0*20,(0+1)*20-1);
//            //            byte[] newsVal = null;
//            //            newsVal = jedis.get(("news:master:"+6).getBytes());
//            //            for (String id : ids)
//            //            {
//            //                System.out.println("id:"+id);
//            //                newsVal = jedis.get(("news:master:"+id).getBytes());
//            //                list.add(News.parseFrom(newsVal));
//            //            }
//            //            System.out.println("newsVal:"+News.parseFrom(newsVal).getTagIdsList());
//            //            NewsType.Builder builder =NewsType.newBuilder();
//            //            long id = jedis.incr("news:type:serial:number");
//            //            builder.setId((int)id);
//            //            builder.setName("test4");
//            //            NewsType newsType = builder.build();
//            //
//            //            NewsType data = newsType.toBuilder().setId(222).build();
//            //            System.out.println("id:"+data.getId());
//            //            byte[] rst = newsType.toByteArray();
//            //            System.out.println(rst.length);
//            //            jedis.set(("news:type:"+id).getBytes(),rst);
//
//
//            //
//            //            GardenPriceItem.Builder builder =GardenPriceItem.newBuilder();
//            //            builder.setMonth("2015-03");
//            //            builder.setPrice(7600);
//            //            GardenPriceItem gardenPriceItem = builder.build();
//            //            System.out.println(gardenPriceItem.toString().length());
//            //            byte[] rst = gardenPriceItem.toByteArray();
//            //
//            //            System.out.println(rst.length);
//            //
//            //            BASE64Encoder encoder = new BASE64Encoder();
//            //            String outstr = encoder.encode(rst);
//            //            System.out.println(outstr.length());
//            //
//            //
//            //            BASE64Decoder decoder = new BASE64Decoder();
//            //            byte[] bytes = decoder.decodeBuffer("CgcyMDE1LTEyEM0C");
//            //            GardenPriceItem pri = GardenPriceItem.parseFrom(bytes);
//            //            System.out.println("price:"+pri.getPrice());
//            //
//            //
//            //            GardenPriceNews.Builder builder1 = GardenPriceNews.newBuilder();
//            //            builder1.setCaption("title test 2");
//            //            builder1.setContent("content test 2");
//            //            builder1.setDate("2015-03-18");
//            //            GardenPriceNews gardenPriceNews = builder1.build();
//            //
//            //            GardenPriceView.Builder builder2 = GardenPriceView.newBuilder();
//            //            builder2.setGardenId(1);
//            //            builder2.addPriceList(builder);
//            //            builder2.addPriceNews(builder1);
//            //            GardenPriceView gardenPriceView = builder2.build();
//            //
//            //            builder.clear();
//            //            builder = null;
//            //            builder1.clear();
//            //            builder1 = null;
//            //            builder2.clear();
//            //            builder2 = null;
//            //
//            //            byte[] output = gardenPriceView.toByteArray();
//            //            gardenPriceView = GardenPriceView.parseFrom(output);
//            //
//            //            System.out.println(gardenPriceView.getGardenId());
//            //            System.out.println(gardenPriceView.getPriceList(0).getPrice());
//            //            System.out.println(new String(gardenPriceView.getPriceNews(0).getContent().getBytes("utf-8")));
//            //
//            //            byte[] output1 = gardenPriceItem.toByteArray();
//            //            byte[] output2 = gardenPriceNews.toByteArray();
//            //
//            ////            jedis.zadd("garden:price:history:1".getBytes(),201503,output1);
//            //            jedis.zadd("garden:price:notices:1".getBytes(),20150318,output2);
//
//            //            Set<byte[]> set = jedis.zrangeByScore("garden:price:history:1".getBytes(), 201511, 201512);
//            //            GardenPriceItem gardenPriceItem1 = null;
//            //            for(byte[] item : set){
//            //                gardenPriceItem1 = GardenPriceItem.parseFrom(item);
//            //                System.out.println("GardenPriceItem:"+gardenPriceItem1.getMonth()+" , "+gardenPriceItem1.getPrice());
//            //            }
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
////            RedisServiceFactory.returnResource(jedisPool, jedis);
//        }
//
//    }
//
//    @Test
//    public void test1() throws IOException
//    {
//        try
//        {
//
//            //            Client client = ClientBuilder.newClient();
//            //            String url = "http://localhost:8010/price/getPriceList?gardenId=1";
//            //            WebTarget target = client.target(url);
//            //            Response response = target.request().get();
//            //            byte[] bytes = (byte[]) response.readEntity(byte[].class);
//            //            GardenPriceView gpi = GardenPriceView.parseFrom(bytes);
//            //            System.out.println(gpi.getGardenId());
//
//            //
//            //            GardenPriceView.Builder gpvb = GardenPriceView.newBuilder();
//            //            gpvb.setGardenId(1);
//            //
//            //            Set<byte[]> rs1 = jedis.zrevrange("garden:price:history:1".getBytes(), 0,-1);
//            //            GardenPriceItem gpi = null;
//            //            for(byte[] bytes : rs1){
//            //                gpi = GardenPriceItem.parseFrom(bytes);
//            //                System.out.println("GardenPriceItem:"+gpi.getMonth()+" , "+gpi.getPrice());
//            //                gpvb.addPriceList(gpi);
//            //            }
//            //
//            //            Set<byte[]> rs2 = jedis.zrevrange("garden:price:notices:1".getBytes(), 0,-1);
//            //            GardenPriceNews gpn = null;
//            //            for(byte[] bytes : rs2){
//            //                gpn = GardenPriceNews.parseFrom(bytes);
//            //                System.out.println("GardenPriceNews:"+gpn.getDate()+" , "+gpn.getContent());
//            //                gpvb.addPriceNews(gpn);
//            //            }
//            //
//            //            GardenPriceView gardenPriceView = gpvb.build();
//            //            gpvb.clear();
//            //            gpvb = null;
//            //            System.out.println("GardenPriceView:"+gardenPriceView.getGardenId()+" , " + gardenPriceView.getPriceListCount());
//            //            byte[] rst = gardenPriceView.toByteArray();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
////            RedisServiceFactory.returnResource(jedisPool, jedis);
//        }
//
//    }
//
//    public static void main(String[] args) throws Exception
//    {
//        //        Set<String> member_code_list = new HashSet<String>();
//        //        boolean flag = member_code_list.add("1");
//        //        System.out.println(flag);
//        //        boolean flag2 = member_code_list.add("1");
//
//
//        //        System.out.println(flag2);
//    }
//
//
//}
