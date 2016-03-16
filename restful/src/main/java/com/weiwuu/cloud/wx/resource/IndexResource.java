package com.weiwuu.cloud.wx.resource;

import com.weiwuu.cloud.wx.view.IndexView;
import io.dropwizard.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;

@Path("/")
public class IndexResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexResource.class);

    public IndexResource()
    {
    }

    @GET
    @Produces({"text/html;charset=UTF-8"})
    public View index()
    {
        return new IndexView();
    }

}
