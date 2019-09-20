package com.game.majiang.server.controller.manager;

import com.game.majiang.base.entity.Manager;
import com.game.majiang.base.entity.ManagerBalance;
import com.game.majiang.base.util.CloudUtil;
import com.game.majiang.constants.ConfigConstants;
import com.game.majiang.constants.ManagerConstants;
import com.game.majiang.constants.NumberConstants;
import com.game.majiang.constants.PageEnum;
import com.game.majiang.server.manager.ConfigServer;
import com.game.majiang.server.manager.ManagerServer;
import com.game.majiang.server.manager.NumberServer;
import org.apache.log4j.Logger;
import org.json.JSONObject;
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
 * Created by sujianfeng on 2017/6/10.
 */
@Controller
@RequestMapping("/manager")
public class ManagerAction {
    private final Logger logger = Logger.getLogger(ManagerAction.class);

    @Autowired
    private ConfigServer configServer;

    @Autowired
    private ManagerServer managerService;
    @Autowired
    private NumberServer numberServer;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response,
                        String username, String password, String code,
                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = ConfigConstants.PAGE_SIZE) Integer size,
                        String[] condition) {

        logger.info("登录用户：" + username);
        logger.info("登录密码：" + password);

        if (!CloudUtil.checkParams(username, password)) {
            return PageEnum.LOGIN.getPage();
        }

        JSONObject json= managerService.login(request,username, password);

        if (json.getInt("code")==2){
            request.setAttribute("errMsg", json.getString("msg"));
            return PageEnum.LOGIN.getPage();
        }


        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.ASC, "productNumber");
        Pageable pageable = new PageRequest(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        //condition = condition == null ? new String[]{"", "","-1"} : condition;

        //CloudUtil.logCondition(condition);

        Manager manager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);

        Page<ManagerBalance> appPage = managerService.findAllBalance(manager.getNumber(), pageable);

        //页面数据存储
        request.setAttribute(ConfigConstants.PAGE_KEY, appPage);
        request.setAttribute(ConfigConstants.CONDITION_KEY, condition);


        return PageEnum.INDEX.getPage();

    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = ConfigConstants.PAGE_SIZE) Integer size,
                       String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        condition = condition == null ? new String[]{"","-1"} : condition;

        CloudUtil.logCondition(condition);

        Manager sessionManager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);

        Page<Manager> appPage = managerService.findAll(condition, pageable,sessionManager);

        //页面数据存储
        request.setAttribute(ConfigConstants.PAGE_KEY, appPage);
        request.setAttribute(ConfigConstants.CONDITION_KEY, condition);

        return PageEnum.MANAGER_LIST.getPage();
    }

    @RequestMapping("/balance_list")
    public String balanceList(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                              @RequestParam(value = "size", defaultValue = ConfigConstants.PAGE_SIZE) Integer size,
                              String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.ASC, "productNumber");
        Pageable pageable = new PageRequest(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        //condition = condition == null ? new String[]{"", "","-1"} : condition;

        //CloudUtil.logCondition(condition);

        Manager manager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);

        Page<ManagerBalance> appPage = managerService.findAllBalance(manager.getNumber(), pageable);

        //页面数据存储
        request.setAttribute(ConfigConstants.PAGE_KEY, appPage);
        request.setAttribute(ConfigConstants.CONDITION_KEY, condition);

        return PageEnum.INDEX.getPage();
    }

    /**
     * 查看下级代理的余额
     *
     * @param request
     * @param response
     * @param page
     * @param size
     * @param condition
     * @return
     */
    @RequestMapping("/manager_balance_list")
    public String manager_balance_list(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = ConfigConstants.PAGE_SIZE) Integer size,
                                       String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.ASC, "productNumber");
        Pageable pageable = new PageRequest(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        condition = condition == null ? new String[]{"-1"} : condition;

        CloudUtil.logCondition(condition);
        //下级代理的列表
        Page<ManagerBalance> appPage = managerService.findAllBalance(Integer.parseInt(condition[0]), pageable);

        Manager sessionManager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);

        List<ManagerBalance> managerBalanceList = managerService.findAllBalanceByManager(sessionManager);

        //页面数据存储
        request.setAttribute(ConfigConstants.PAGE_KEY, appPage);
        request.setAttribute(ConfigConstants.CONDITION_KEY, condition);
        request.setAttribute(ConfigConstants.LIST_MANAGER_BALANCE_KEY, managerBalanceList);

        return PageEnum.MANAGER_BALANCE_LIST.getPage();
    }

    @RequestMapping("/recharge")
    @ResponseBody
    public String recharge(HttpServletRequest request, HttpServletResponse response) {
        Manager sessionManager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);

        String managerNumber = request.getParameter("managerNumber");
        String productNumber = request.getParameter("productNumber");
        String balance = request.getParameter("balance");


        if (!(CloudUtil.checkParams(managerNumber, productNumber, balance))) {
            return CloudUtil.buildAjaxOperationJson(false, "请求参数不完整").toString();
        }

        boolean rechargeResult = false;
        List<ManagerBalance> managerBalanceList = managerService.findAllBalanceByManager(sessionManager);
        for (ManagerBalance managerBalance : managerBalanceList) {
            if (managerBalance.getProductNumber() == Integer.parseInt(productNumber)) {
                if (Integer.parseInt(balance) > managerBalance.getBalance()) {
                    return CloudUtil.buildAjaxOperationJson(false, "余额不足").toString();
                } else {
                    //进行充值操作，扣除上级代理余额，增加下级代理余额
                    rechargeResult = managerService.recharge(managerBalance, Integer.parseInt(managerNumber), Integer.parseInt(productNumber), Integer.parseInt(balance));
                }
                break;
            }
        }
        return CloudUtil.buildAjaxOperationJson(rechargeResult, rechargeResult ? "充值成功" : "充值失败").toString();
    }





    @RequestMapping("/resetPwd")
    @ResponseBody
    public String resetPwd(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", defaultValue = "0") Integer id) {

        if (id != 0) {
            Manager manager = managerService.getManagerById(id);
            if (manager != null) {
                manager.setPassword(ManagerConstants.DEFAULT_PASSWORD);
                manager = managerService.saveManager(manager);
                return CloudUtil.buildAjaxOperationJson(manager != null, manager != null ? "重置密码成功" : "重置密码失败").toString();
            }else{
                return CloudUtil.buildAjaxOperationJson(false, "未找到相应的代理").toString();
            }
        }else{
            return CloudUtil.buildAjaxOperationJson(false, "参数有误").toString();
        }

    }

    @RequestMapping("/create")
    public String create(HttpServletRequest request, HttpServletResponse response) {
        Manager sessionManager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
        Manager manager = new Manager();
        manager.setLevel(sessionManager.getLevel() + 1);
        manager.setUserName(""+numberServer.getNumberByType(NumberConstants.USER_MANAGER_USERNAME));
        manager.setPassword(ManagerConstants.DEFAULT_PASSWORD);
        request.setAttribute(ConfigConstants.MANAGER_KEY,manager);
        return PageEnum.MANAGER_CREATE.getPage();
    }


    @RequestMapping("/save")
    @ResponseBody
    public String saveManager(HttpServletRequest request, HttpServletResponse response, Manager manager) {
        logger.info(manager);
        if (!checkManager(new Manager(manager.getId(), manager.getUserName()))) {
            return CloudUtil.buildAjaxOperationJson(false, "创建代理失败").toString();
        }
        Manager sessionManager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);

        Manager dbManager = managerService.createManager(manager.getName(), manager.getUserName(), ManagerConstants.DEFAULT_PASSWORD,
                sessionManager.getNumber(), sessionManager.getName(), sessionManager.getLevel() + 1);

        dbManager = managerService.saveManager(dbManager);


        return CloudUtil.buildAjaxOperationJson(dbManager != null, dbManager != null ? "创建代理成功" : "创建代理失败").toString();
    }


    private boolean checkManager(Manager manager) {
        List<Manager> list = managerService.findByManager(manager);
        if (list.size() > 0) {
            if (manager.getId() == null) {
                return false;
            } else {
                for (Manager dbManager : list) {
                    if (dbManager.getId().intValue() != manager.getId().intValue()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    @RequestMapping("/showConfig")
    public String showConfig(HttpServletRequest request) {
        request.setAttribute(ConfigConstants.BANLANCE,configServer.getStartBalance().getConfigValue());
        return PageEnum.CONFIG_EDIT.getPage();
    }

    @RequestMapping("/saveConfig")
    @ResponseBody
    public String saveConfig(HttpServletRequest request, @RequestParam(value = "value", defaultValue = "0") int value) {
        Manager manager= (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
        JSONObject jsonObject=configServer.updateStartBalance(manager,value+"");
        return jsonObject.toString();
    }

    @RequestMapping("/exit")
    public String exit(HttpServletRequest request, @RequestParam(value = "value", defaultValue = "0") int value) {
        request.getSession().removeAttribute(ConfigConstants.MANAGER_KEY);
        return PageEnum.LOGIN.getPage();
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(HttpServletRequest request, @RequestParam(value = "password", defaultValue = "0") String password) {
        boolean result = false;
        try {
            managerService.updateManagerPassword(request, password);
            result=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return CloudUtil.buildAjaxOperationJson(result, result ? "修改密码成功" : "修改密码失败").toString();
    }

    @RequestMapping("/clearBalance")
    @ResponseBody
    public String clearBalance(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") int id) {
        logger.info("id:" + id);
        Manager sessionManager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
        if (sessionManager.getLevel() == 0) {
            ManagerBalance managerBalance = managerService.getManagerBanalceById(id);
            if (managerBalance != null) {
                managerBalance.setBalance(0);
                managerService.saveManagerBalance(managerBalance);
                return CloudUtil.buildAjaxOperationJson(true, "重置成功").toString();
            } else {
                return CloudUtil.buildAjaxOperationJson(false, "未找到该配置").toString();
            }

        } else {
            return CloudUtil.buildAjaxOperationJson(false, "没有权限进行此操作").toString();
        }
    }

}
