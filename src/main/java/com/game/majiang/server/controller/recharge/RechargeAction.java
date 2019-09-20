package com.game.majiang.server.controller.recharge;

import com.game.majiang.base.entity.Manager;
import com.game.majiang.base.entity.ManagerBalance;
import com.game.majiang.base.entity.Product;
import com.game.majiang.base.entity.Recharge;
import com.game.majiang.base.util.CloudUtil;
import com.game.majiang.constants.ConfigConstants;
import com.game.majiang.constants.PageEnum;
import com.game.majiang.server.manager.ManagerServer;
import com.game.majiang.server.manager.ProductServer;
import com.game.majiang.server.manager.RechargeServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yyc on 2017/6/16.
 */
@Controller
@RequestMapping("/recharge")
public class RechargeAction {
    @Autowired
    private RechargeServer rechargeServer;
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
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        condition = condition == null ? new String[]{"-1", "","-1"} : condition;

        CloudUtil.logCondition(condition);

        Manager sessionManager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);

        List<ManagerBalance> managerBalanceList = managerServer.findAllBalanceByManager(sessionManager);

        Page<Recharge> appPage = rechargeServer.findAll(condition, pageable,managerBalanceList,sessionManager);


        List<Product> listProduct = productServer.findAll();

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
        return PageEnum.RECHARGE_LIST.getPage();
    }
}
