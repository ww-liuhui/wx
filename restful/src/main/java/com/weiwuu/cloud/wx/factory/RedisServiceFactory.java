package com.weiwuu.cloud.wx.factory;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by xu_gang on 2015-05-02.
 * mail: shinetimes@hotmail.com
 */
public class RedisServiceFactory
{
    @NotEmpty
    private String host = "localhost";

    @Min(2000)
    @Max(65330)
    private int port = 6379;

    private int timeout = 1000;
    @NotEmpty
    private String password = "111111";
    @Min(0)
    @Max(15)
    private int database = 0;
    public JedisPool build(Environment environment) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10000);
        poolConfig.setMinIdle(2);
        poolConfig.setMaxIdle(100);
        poolConfig.setFairness(true);

        JedisPool jedisPool = new JedisPool(poolConfig, this.host, this.port,this.timeout,this.password,this.database);

        environment.lifecycle().manage(new Managed() {
            @Override
            public void start() throws Exception {

            }

            @Override
            public void stop() throws Exception {
                if (!jedisPool.isClosed()) jedisPool.close();
            }
        });

        return jedisPool;
    }


    @JsonProperty
    public String getHost() {
        return host;
    }

    @JsonProperty
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    @JsonProperty
    public void setPort(int port) {
        this.port = port;
    }
    @JsonProperty
    public int getTimeout()
    {
        return timeout;
    }
    @JsonProperty
    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }
    @JsonProperty
    public String getPassword()
    {
        return password;
    }
    @JsonProperty
    public void setPassword(String password)
    {
        this.password = password;
    }
    @JsonProperty
    public int getDatabase()
    {
        return database;
    }
    @JsonProperty
    public void setDatabase(int database)
    {
        this.database = database;
    }

    /**
     * 返还到连接池
     *
     * @param pool
     * @param redis
     */
    public static void returnResource(JedisPool pool, Jedis redis) {
        if (redis != null) {
            pool.returnResourceObject(redis);
        }
    }
}
