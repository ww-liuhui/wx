package com.weiwuu.cloud.wx;

import com.weiwuu.cloud.wx.dao.GardenDAO;
import com.weiwuu.cloud.wx.factory.ApiFactory;
import com.weiwuu.cloud.wx.factory.VerifyFactory;
import com.weiwuu.cloud.wx.filter.VerifyServletFilter;
import com.weiwuu.cloud.wx.resource.GardenResource;
import com.weiwuu.cloud.wx.resource.IndexResource;
import com.weiwuu.cloud.wx.resource.NewsResource;
import com.weiwuu.cloud.wx.resource.WXResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;
import redis.clients.jedis.JedisPool;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ApiApplication extends Application<ApiConfiguration>
{
    public static void main(String[] args) throws Exception
    {
        new ApiApplication().run(args);
    }

    @Override
    public String getName()
    {
        return "weiwuu-wx restfull service";
    }

    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap)
    {
        // 启用读取环境变量的功能
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new AssetsBundle("/assets","/wx","list.html"));
//        bootstrap.addBundle(new AssetsBundle("/assets","/","kunming/list.html"));
    }

    @Override
    public void run(ApiConfiguration config, Environment environment) throws Exception
    {
        System.out.println("开始运行");
        final JedisPool jedisPool = config.getRedisServiceFactory().build(environment);
        final DBIFactory factory = new DBIFactory();
        DataSourceFactory database = config.getDataSourceFactory();
        final DBI jdbi = factory.build(environment, database, "mysql");

        System.out.println("注册服务开始");
        //
        ApiFactory apiFactory = config.getApiFactory();

        final GardenDAO gardenDAO = jdbi.onDemand(GardenDAO.class);

        environment.jersey().register(new GardenResource(gardenDAO,apiFactory,jedisPool));
        environment.jersey().register(new WXResource(apiFactory,config.getWxConfigFactory().getAppID(),config.getWxConfigFactory().getAppSecret(),config.getWxConfigFactory().getToken(),config.getWxConfigFactory().getEncodingAESKey(),gardenDAO,jedisPool,config
                .getWxConfigFactory().getAppID2(),config.getWxConfigFactory().getAppSecret2()));
        //资讯 new
        final NewsResource newsResource = new NewsResource(jedisPool,gardenDAO);
        environment.jersey().register(newsResource);
        //web客户端
        final IndexResource indexResource = new IndexResource();
        environment.jersey().register(indexResource);
        //添加过滤器  ---------------------------------------------------------------------------------
        VerifyFactory verifyFactory = config.getVerifyFactory();//过滤器参数
        environment.servlets().addFilter("VerifyServletFilter", new VerifyServletFilter(verifyFactory))
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        System.out.println("注册服务结束");
    }
}
