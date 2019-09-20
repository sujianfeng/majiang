package com.game.majiang.server.controller.consumption;

import com.game.majiang.constants.ConfigConstants;
import com.game.majiang.constants.PageEnum;
import com.game.majiang.server.manager.ConsumptionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sujianfeng on 2017/7/2.
 */
@Controller
@RequestMapping("/consumption")
public class ConsumptionAction {
    @Autowired
    private ConsumptionServer consumptionServer;

    @RequestMapping("/list")
    public String data(HttpServletRequest request, HttpServletResponse response) {

        int curBalance = consumptionServer.getCurBalance();
        int allBalance = consumptionServer.getAllBalance();
        int yesbalance = consumptionServer.getYesBalance();

        //页面数据存储
        request.setAttribute(ConfigConstants.CURBALANCE_KEY, curBalance);
        request.setAttribute(ConfigConstants.ALLBALANCE_KEY, allBalance);
        request.setAttribute(ConfigConstants.YESBALANCE_KEY, yesbalance);

        return PageEnum.PAGE_CONSUMPTION.getPage();
    }
}
