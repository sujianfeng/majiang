package com.game.majiang.server.controller.manager;

import com.game.majiang.base.util.CloudUtil;
import com.game.majiang.server.manager.SysConfigServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by sujianfeng on 2017/6/17.
 */
@Controller
@RequestMapping("/sysconfig")
public class SysConfigAction {
    private final Logger logger = Logger.getLogger(SysConfigAction.class);
    @Autowired
    private SysConfigServer sysConfigServer;

    @RequestMapping("/batch")
    @ResponseBody
    public String batch(HttpServletRequest request, HttpServletResponse response) {
        logger.info("进入批量操作");
        boolean result = false;
        try {
            Enumeration<String> rnames = request.getParameterNames();
            for (Enumeration<String> e = rnames; e.hasMoreElements(); ) {
                String thisName = e.nextElement().toString();
                String thisValue = request.getParameter(thisName);
                logger.info("paramMap:" + thisName + "=" + thisValue);
            }
            String rechargeType = request.getParameter("rechargeType");
            String rechargeCount = request.getParameter("rechargeCount");
            String[] rechargeTypes = rechargeType.split(",");
            logger.info(rechargeType);
            logger.info(rechargeCount);
            for (String rt : rechargeTypes) {
                if(StringUtils.isNotBlank(rt)) {
                    sysConfigServer.recharge(rt, rechargeCount);
                }
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CloudUtil.buildAjaxOperationJson(result, result ? "发卡成功" : "发卡失败").toString();
    }
}
