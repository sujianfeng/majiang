package com.game.majiang.server.manager;

import com.game.majiang.base.dao.ManagerBalanceDao;
import com.game.majiang.base.dao.ManagerDao;
import com.game.majiang.base.dao.ProductDao;
import com.game.majiang.base.dao.RechargeDao;
import com.game.majiang.base.entity.Manager;
import com.game.majiang.base.entity.ManagerBalance;
import com.game.majiang.base.entity.Product;
import com.game.majiang.base.entity.Recharge;
import com.game.majiang.constants.ConfigConstants;
import com.game.majiang.constants.ManagerConstants;
import com.game.majiang.constants.NumberConstants;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
@Service
public class ManagerServer {
    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private ManagerBalanceDao managerBalanceDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private NumberServer numberServer;

    @Autowired
    private RechargeDao rechargeDao;
    /**
     * 登录请求
     * @param request
     * @param userName
     * @param password
     * @return
     */
    public JSONObject login(HttpServletRequest request, String userName, String password){
        Manager manager=managerDao.findByUserName(userName);
        JSONObject returnObj=new JSONObject();
        int code=0;
        if(manager==null){
            returnObj.put("msg","账号不存在");
            code=2;

        }else if(manager.getPassword().equals(password)){
            request.getSession().setAttribute(ConfigConstants.MANAGER_KEY,manager);
            code=1;
        }else{
            returnObj.put("msg","密码错误");
            code=2;
        }
        returnObj.put("code",code);
        return returnObj;
    }

    /**
     * 添加代理
     * @param request
     * @param name
     * @param userName
     * @param password
     * @param model
     * @return
     */
    public String addManager(HttpServletRequest request,String name,String userName,String password, Model model){
        Manager manager=(Manager)request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
        JSONObject jsonObject=new JSONObject();
        int code=0;
        Manager createManager=null;
        if(manager.getLevel()== ManagerConstants.TWO_LEVEL_AGENT){
            code=2;
        }else{
            createManager=createManager(name,userName,password,manager.getNumber(),manager.getName(),manager.getLevel()+1);
            createManager=managerDao.save(createManager);
        }
        jsonObject.put("code",code);
        jsonObject.put("createManager",createManager);
        return jsonObject.toString();
    }

    /**
     * 修改密码
     * @param request
     * @param password
     * @return
     */
    public String updateManagerPassword(HttpServletRequest request,String password){
        Manager manager=(Manager)request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
        JSONObject jsonObject=new JSONObject();
        int code=1;
        manager.setPassword(password);
        managerDao.save(manager);
        request.getSession().removeAttribute(ConfigConstants.MANAGER_KEY);
        jsonObject.put("code",code);
        return jsonObject.toString();
    }

    /**
     * 删除代理
     * @param request
     * @param number
     * @return
     */
    public String deleteManager(HttpServletRequest request,int number){
        Manager manager=(Manager)request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
        JSONObject jsonObject=new JSONObject();
        int code=0;
        Manager updateManager=managerDao.findByNumber(number);
        if(updateManager.getParantNumber()!= manager.getNumber()){
            code=2;
        }else{
            managerDao.delete(updateManager);
        }
        jsonObject.put("code",code);
        return jsonObject.toString();
    }

    /**
     * 下级代理查询
     * @param request
     * @return
     */
    public String getManager(HttpServletRequest request){
        Manager manager=(Manager)request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
        JSONObject jsonObject=new JSONObject();
        int code=0;
        List<Manager> managerList=new ArrayList<>();
        if(manager.getLevel()== ManagerConstants.ADMIN_MANAGER){
            managerList=managerDao.findByOrderByUpdateTime();
        }else{
            managerList=managerDao.findByParantNumberOrderByUpdateTime(manager.getNumber());
        }
        jsonObject.put("code",code);
        jsonObject.put("managerList",managerList);
        return jsonObject.toString();
    }


    /**
     * 获取下级代理的产品信息
     * @param request
     * @return
     */
    public String getManagerBalance(HttpServletRequest request){
        Manager manager=(Manager)request.getSession().getAttribute("user");
        JSONObject jsonObject=new JSONObject();
        int code=0;
        List<ManagerBalance> managerBalanceList=new ArrayList<>();
        if(manager.getLevel()== ManagerConstants.ADMIN_MANAGER){
            managerBalanceList=managerBalanceDao.findAll();
        }else{
            managerBalanceList=managerBalanceDao.findByManagerNumber(manager.getNumber());
        }
        jsonObject.put("code",code);
        jsonObject.put("managerList",managerBalanceList);
        return jsonObject.toString();
    }

    /**
     * 给下级代理添加或修改产品
     * @param request
     * @param managerNumber
     * @param productNumber
     * @param balance
     * @return
     */
    public String addOrUpdateManagerBalance(HttpServletRequest request,int id,int managerNumber,int productNumber,int balance){
        Manager userManager=(Manager)request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
        JSONObject jsonObject=new JSONObject();
        int code=0;
        Manager letterManager=managerDao.findByNumber(managerNumber);
        Product product=productDao.findByNumber(productNumber);

        ManagerBalance managerBalance=null;
        if(letterManager.getParantNumber()!=userManager.getNumber()){
            code=1;
        }else if(userManager.getLevel()!= ManagerConstants.TWO_LEVEL_AGENT){
            if(id>0){
                managerBalance= managerBalanceDao.findOne(id);
                managerBalance.setBalance(managerBalance.getBalance()+balance);
            }else{
                managerBalance=createManagerBalance(letterManager.getNumber(),letterManager.getName(),letterManager.getLevel(),letterManager.getParantNumber(),letterManager.getParantName(),product.getNumber(),product.getName(),balance);
            }
            managerBalanceDao.save(managerBalance);
            code=2;
        }else{
            code=1;
        }
        jsonObject.put("code",code);
        jsonObject.put("managerBalance",managerBalance);
        return jsonObject.toString();
    }


    /**
     * 删除下级代理的产品
     * @param request
     * @param id
     * @return
     */
    public String deleteManagerBalance(HttpServletRequest request,int id){
        Manager userManager=(Manager)request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
        JSONObject jsonObject=new JSONObject();
        int code=0;
        if(userManager.getLevel()== ManagerConstants.ADMIN_MANAGER){
            managerBalanceDao.delete(id);
        }else{
            ManagerBalance managerBalance=managerBalanceDao.findOne(id);
            if(managerBalance.getParantNumber()!=userManager.getNumber()){
                code=2;
            }else{
                managerBalanceDao.delete(id);
            }
        }
        jsonObject.put("code",code);
        return jsonObject.toString();
    }

    /**
     * 创建代理
     * @param name
     * @param userName
     * @param password
     * @param parantNumber
     * @param parantName
     * @param level
     * @return
     */
    public Manager createManager(String name,String userName,String password,int parantNumber,String parantName,int level){
        Manager manager=new Manager();
        manager.setName(name);
        manager.setUserName(userName);
        manager.setPassword(password);
        manager.setParantNumber(parantNumber);
        manager.setParantName(parantName);
        manager.setLevel(level);
        int number=1;
        if(level==1){
            number=numberServer.getNumberByType(NumberConstants.ONE_LEVEL_MANAGER_TYPE);
        }else if(level==2){
            number=numberServer.getNumberByType(NumberConstants.TWO_LEVEL_MANAGER_TYPE);
        }
        manager.setNumber(number);
        manager.setCreateTime(new Date());
        manager.setUpdateTime(new Date());
        return manager;
    }

    /**
     * 创建代理产品关系
     * @param managerNumber
     * @param managerName
     * @param level
     * @param parantNumber
     * @param parantName
     * @param prodcutNumber
     * @param productName
     * @param balance
     * @return
     */
    public ManagerBalance createManagerBalance(int managerNumber,String managerName,int level,int parantNumber,String parantName,int prodcutNumber,String productName,int balance){
        ManagerBalance managerBalance=new ManagerBalance();
        managerBalance.setManagerNumber(managerNumber);
        managerBalance.setManagerName(managerName);
        managerBalance.setLevel(level);
        managerBalance.setParantNumber(parantNumber);
        managerBalance.setParantName(parantName);
        managerBalance.setProductNumber(prodcutNumber);
        managerBalance.setProductName(productName);
        managerBalance.setBalance(balance);
        return managerBalance;
    }

    public Page<Manager> findAll(final String[] condition, Pageable pageable, final Manager manager) {
        return managerDao.findAll(new Specification<Manager>() {
            @Override
            public Predicate toPredicate(Root<Manager> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //productName
                if (StringUtils.isNotBlank(condition[0])) {
                    list.add(cb.like(root.get("name").as(String.class), '%' + condition[0] + '%'));
                }
                //productNumber
                //if (StringUtils.isNotBlank(condition[1])) {
                //    list.add(cb.like(root.get("number").as(String.class), '%' + condition[1] + '%'));
                //}
                //productNumber
                if (StringUtils.isNotBlank(condition[1])) {
                    if (!(condition[1].equals("-1"))) {
                        list.add(cb.equal(root.get("level").as(Integer.class), Integer.parseInt(condition[1])));
                    }
                }
                //过滤admin
                list.add(cb.notEqual(root.get("level").as(Integer.class), 0));
                if (manager.getLevel() == 1) {
                    //过滤一级代理
                    list.add(cb.notEqual(root.get("level").as(Integer.class), 1));

                    if (StringUtils.isBlank(condition[0])) {
                        //这种情况下，只显示当前1级代理下面的二级代理
                        list.add(cb.equal(root.get("parantNumber").as(Integer.class), manager.getNumber()));
                    }
                }

                Predicate[] predicate = new Predicate[list.size()];
                return cb.and(list.toArray(predicate));
            }
        }, pageable);
    }

    public Page<ManagerBalance> findAllBalance(final int number, Pageable pageable) {
        return managerBalanceDao.findAll(new Specification<ManagerBalance>() {
            @Override
            public Predicate toPredicate(Root<ManagerBalance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //productName
                list.add(cb.equal(root.get("managerNumber").as(Integer.class), number));

                Predicate[] predicate = new Predicate[list.size()];
                return cb.and(list.toArray(predicate));
            }
        }, pageable);
    }

    public Manager getManagerById(Integer id) {
        return managerDao.findOne(id);
    }

    public Manager saveManager(Manager manager) {
        return managerDao.saveAndFlush(manager);
    }

    public List<Manager> findByManager(final Manager manager) {
        return managerDao.findAll(new Specification<Manager>() {
            @Override
            public Predicate toPredicate(Root<Manager> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //channelcode
                if (StringUtils.isNotBlank(manager.getUserName())) {
                    list.add(cb.equal(root.get("userName").as(String.class), manager.getUserName()));
                }
                Predicate[] predicate = new Predicate[list.size()];
                return cb.and(list.toArray(predicate));
            }
        });
    }


    public List<ManagerBalance> findAllBalanceByManager(Manager sessionManager) {
        return managerBalanceDao.findByManagerNumber(sessionManager.getNumber());
    }

    @Transactional
    public boolean recharge(ManagerBalance managerBalance, int managerNumber, int productNumber, int balance) {
        try {


            ManagerBalance nextManagerBalance = managerBalanceDao.findByManagerNumberAndProductNumber(managerNumber, productNumber);

            if (nextManagerBalance == null) {
                //新的代理和产品关系
                Manager nextManager = managerDao.findByNumber(managerNumber);
                Product product = productDao.findByNumber(productNumber);

                nextManagerBalance = new ManagerBalance();
                nextManagerBalance.setBalance(balance);
                nextManagerBalance.setLevel(nextManager.getLevel());
                nextManagerBalance.setManagerNumber(nextManager.getNumber());
                nextManagerBalance.setManagerUserName(nextManager.getUserName());
                nextManagerBalance.setManagerName(nextManager.getName());
                nextManagerBalance.setParantNumber(nextManager.getParantNumber());
                nextManagerBalance.setParantName(nextManager.getParantName());
                nextManagerBalance.setProductNumber(product.getNumber());
                nextManagerBalance.setProductName(product.getName());
                nextManagerBalance.setCost(0);
                //存
                managerBalanceDao.saveAndFlush(nextManagerBalance);
                //取
                managerBalance.setBalance(managerBalance.getBalance() - balance);
                managerBalance.setCost(managerBalance.getCost() + balance);
                managerBalanceDao.saveAndFlush(managerBalance);
                //记录订购关系
                Recharge recharge = new Recharge();
                recharge.setManagerNumber(managerBalance.getManagerNumber());
                recharge.setManagerName(managerBalance.getManagerName());
                recharge.setProductNumber(product.getNumber());
                recharge.setProductName(product.getName());
                recharge.setRechargeType(ConfigConstants.RECHARGE_TYPE_MANAGER);
                recharge.setRechargeId(nextManagerBalance.getManagerNumber());
                recharge.setRechargeName(nextManagerBalance.getManagerName());
                recharge.setCreateTime(new Date());
                recharge.setBalance(balance);
                rechargeDao.saveAndFlush(recharge);

            } else {
                Product product = productDao.findByNumber(productNumber);

                nextManagerBalance.setBalance(nextManagerBalance.getBalance() + balance);
                //存
                managerBalanceDao.saveAndFlush(nextManagerBalance);
                //取
                managerBalance.setBalance(managerBalance.getBalance() - balance);
                managerBalance.setCost(managerBalance.getCost() + balance);
                managerBalanceDao.saveAndFlush(managerBalance);
                //记录订购关系
                Recharge recharge = new Recharge();
                recharge.setManagerNumber(managerBalance.getManagerNumber());
                recharge.setManagerName(managerBalance.getManagerName());
                recharge.setProductNumber(product.getNumber());
                recharge.setProductName(product.getName());
                recharge.setRechargeType(ConfigConstants.RECHARGE_TYPE_MANAGER);
                recharge.setRechargeId(nextManagerBalance.getManagerNumber());
                recharge.setRechargeName(nextManagerBalance.getManagerName());
                recharge.setCreateTime(new Date());
                recharge.setBalance(balance);
                rechargeDao.saveAndFlush(recharge);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ManagerBalance saveManagerBalance(ManagerBalance managerBalance) {
        return managerBalanceDao.saveAndFlush(managerBalance);
    }

    public ManagerBalance getManagerBanalceById(int id) {
        return managerBalanceDao.getOne(id);
    }
}
