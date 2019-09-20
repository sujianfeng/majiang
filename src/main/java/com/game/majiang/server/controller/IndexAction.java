package com.game.majiang.server.controller;

import com.game.majiang.constants.PageEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by len on 2016/12/20.
 */
@Controller
public class IndexAction {

    @RequestMapping("/")
    public String index() {
        return PageEnum.LOGIN.getPage();
    }
}
