package com.weiwuu.cloud.wx.filter;

import com.weiwuu.cloud.wx.factory.VerifyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Created by hui on 2015/5/22.
 */
public class VerifyServletFilter implements Filter {
    // Other methods in interface ommited for brevity
    private static final Logger LOGGER = LoggerFactory.getLogger(VerifyServletFilter.class);
    private VerifyFactory verifyFactory;

    @NotNull
    private JedisPool jedisPool;
    public VerifyServletFilter(JedisPool jedisPool)
    {
        this.jedisPool = jedisPool;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        LOGGER.debug("The resource is inited");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //将原生ServletRequest转换成Http的request
        HttpServletRequest req = (HttpServletRequest) request;
        //将原生ServletResponse转换成Http的Response
        HttpServletResponse res = (HttpServletResponse) response;


        //添加响应头,允许所有的域访问本站点请求
        //res.addHeader("Access-Control-Allow-Origin",this.verifyFactory.getName());
        res.addHeader("Access-Control-Allow-Origin","*");

        //添加响应头X-Requested-With
        res.addHeader("Access-Control-Allow-Header","Origin,X-Requested-With,Content-Type,Accept");
        //添加允许跨域请求的方法
        res.addHeader("Access-Control-Allow-Methods","GET,PUT,POST,DELETE,OPTIONS");
        //目标资源执行
        chain.doFilter(request, response);

    }

    @Override
    public void destroy()
    {

    }

    public VerifyServletFilter(VerifyFactory verifyFactory)
    {
        this.verifyFactory = verifyFactory;
    }

    public VerifyFactory getVerifyFactory()
    {
        return verifyFactory;
    }

    public void setVerifyFactory(VerifyFactory verifyFactory)
    {
        this.verifyFactory = verifyFactory;
    }
}