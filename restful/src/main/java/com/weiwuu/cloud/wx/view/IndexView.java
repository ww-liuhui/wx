package com.weiwuu.cloud.wx.view;

import com.google.common.base.Charsets;
import io.dropwizard.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexView extends View
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexView.class);

    public IndexView() {
        super("/assets/kunming/index.ftl", Charsets.UTF_8);
    }

}
