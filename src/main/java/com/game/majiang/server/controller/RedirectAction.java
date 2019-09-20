package com.game.majiang.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by len on 2016/12/15.
 */
@Controller
public class RedirectAction {
    private final Logger logger = Logger.getLogger(RedirectAction.class);

    @RequestMapping("/{url}.html")
    public String redirectUrl(@PathVariable("url") String url) {
        logger.info("redirect to :" + url);
        return url;
    }
}
