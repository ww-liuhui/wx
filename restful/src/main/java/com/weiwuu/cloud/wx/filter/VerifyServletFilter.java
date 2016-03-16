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
        //��ԭ��ServletRequestת����Http��request
        HttpServletRequest req = (HttpServletRequest) request;
        //��ԭ��ServletResponseת����Http��Response
        HttpServletResponse res = (HttpServletResponse) response;


        //�����Ӧͷ,�������е�����ʱ�վ������
        //res.addHeader("Access-Control-Allow-Origin",this.verifyFactory.getName());
        res.addHeader("Access-Control-Allow-Origin","*");

        //�����ӦͷX-Requested-With
        res.addHeader("Access-Control-Allow-Header","Origin,X-Requested-With,Content-Type,Accept");
        //��������������ķ���
        res.addHeader("Access-Control-Allow-Methods","GET,PUT,POST,DELETE,OPTIONS");
        //Ŀ����Դִ��
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