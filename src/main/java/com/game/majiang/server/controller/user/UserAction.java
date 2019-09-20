package com.game.majiang.server.controller.user;

import com.game.majiang.base.entity.Manager;
import com.game.majiang.base.entity.ManagerBalance;
import com.game.majiang.base.entity.User;
import com.game.majiang.base.util.CloudUtil;
import com.game.majiang.constants.ConfigConstants;
import com.game.majiang.constants.PageEnum;
import com.game.majiang.server.manager.ManagerServer;
import com.game.majiang.server.manager.ProductServer;
import com.game.majiang.server.manager.UserServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sujianfeng on 2017/6/16.
 */
@Controller
@RequestMapping("/user")
public class UserAction {
    private final Logger logger = Logger.getLogger(UserAction.class);
    @Autowired
    private UserServer userServer;
    @Autowired
    private ProductServer productServer;
    @Autowired
    private ManagerServer managerServer;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = ConfigConstants.PAGE_SIZE) Integer size,
                       String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        condition = condition == null ? new String[]{"-1", "",} : condition;

        CloudUtil.logCondition(condition);


        Manager sessionManager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);

        List<ManagerBalance> managerBalanceList = managerServer.findAllBalanceByManager(sessionManager);

        Page<User> appPage = userServer.findAll(condition, pageable, managerBalanceList, sessionManager);

        //页面数据存储
        request.setAttribute(ConfigConstants.PAGE_KEY, appPage);
        request.setAttribute(ConfigConstants.CONDITION_KEY, condition);
        request.setAttribute(ConfigConstants.LIST_MANAGER_BALANCE_KEY,managerBalanceList);
        try {
            int condition0 = Integer.parseInt(condition[0]);
            request.setAttribute(ConfigConstants.CONDITION0, condition0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PageEnum.USER_LIST.getPage();
    }


    @RequestMapping("/recharge")
    @ResponseBody
    public String recharge(HttpServletRequest request, HttpServletResponse response) {
        Manager sessionManager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);

        String userId = request.getParameter("userId");
        String balance = request.getParameter("balance");


        if (!(CloudUtil.checkParams(userId, balance))) {
            return CloudUtil.buildAjaxOperationJson(false, "请求参数不完整").toString();
        }

        User user = userServer.findUserByUserId(Integer.parseInt(userId));

        boolean rechargeResult = false;
        //检查余额
        List<ManagerBalance> managerBalanceList = managerServer.findAllBalanceByManager(sessionManager);
        for (ManagerBalance managerBalance : managerBalanceList) {
            if (managerBalance.getProductNumber() == user.getProductNumber()) {
                if (Integer.parseInt(balance) > managerBalance.getBalance()) {
                    return CloudUtil.buildAjaxOperationJson(false, "余额不足").toString();
                } else {
                    //进行充值操作，扣除代理余额，增加用户余额
                    rechargeResult = userServer.recharge(managerBalance, user, Integer.parseInt(balance));
                }
                break;
            }
        }


        return CloudUtil.buildAjaxOperationJson(rechargeResult, rechargeResult ? "充值成功" : "充值失败").toString();
    }
}
