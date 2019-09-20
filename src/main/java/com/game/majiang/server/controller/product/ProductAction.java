package com.game.majiang.server.controller.product;

import com.game.majiang.base.entity.Manager;
import com.game.majiang.base.entity.ManagerBalance;
import com.game.majiang.base.entity.Product;
import com.game.majiang.base.util.CloudUtil;
import com.game.majiang.constants.ConfigConstants;
import com.game.majiang.constants.NumberConstants;
import com.game.majiang.constants.PageEnum;
import com.game.majiang.server.manager.ManagerServer;
import com.game.majiang.server.manager.NumberServer;
import com.game.majiang.server.manager.ProductServer;
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
import java.util.Date;
import java.util.List;

/**
 * Created by sujianfeng on 2017/6/13.
 */
@Controller
@RequestMapping("/product")
public class ProductAction {
    private final Logger logger = Logger.getLogger(ProductAction.class);
    @Autowired
    private ProductServer productServer;
    @Autowired
    private NumberServer numberServer;

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
        condition = (condition == null || condition.length == 0) ? new String[]{""} : condition;

        CloudUtil.logCondition(condition);

        Page<Product> appPage = productServer.findAll(condition, pageable);

        //页面数据存储
        request.setAttribute(ConfigConstants.PAGE_KEY, appPage);
        request.setAttribute(ConfigConstants.CONDITION_KEY, condition);

        return PageEnum.PRODUCT_LIST.getPage();
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", defaultValue = "0") Integer id) {
        Product product = id == 0 ? new Product() : productServer.getProductById(id);
        logger.info(product);
        request.setAttribute(ConfigConstants.PRODUCT_KEY, product);
        return PageEnum.PRODUCT_EDIT.getPage();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String saveProduct(HttpServletRequest request, HttpServletResponse response, Product product) {
        String operation = product.getId() != null ? "编辑" : "新增";
        logger.info(product);
        if (!checkProduct(new Product(product.getId(), product.getName()))) {
            return CloudUtil.buildAjaxOperationJson(false, operation + "产品失败:产品名称重复").toString();
        }
        Product dbProduct = new Product();
        if (product.getId() != null) {
            //编辑
            dbProduct = productServer.getProductById(product.getId());
            dbProduct.setName(product.getName());
            dbProduct.setUpdateTime(new Date());
            dbProduct = productServer.saveProduct(dbProduct);
        } else {
            //新增
            dbProduct.setName(product.getName());
            dbProduct.setCreateTime(new Date());
            dbProduct.setUpdateTime(new Date());
            dbProduct.setNumber(numberServer.getNumberByType(NumberConstants.PRODUCT_MANAGER_TYPE));
            dbProduct = productServer.saveProduct(dbProduct);


            Manager sessionManager = (Manager)request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
            ManagerBalance managerBalance = new ManagerBalance();
            managerBalance.setBalance(ConfigConstants.ADMIN_BALANCE);
            managerBalance.setProductName(dbProduct.getName());
            managerBalance.setProductNumber(dbProduct.getNumber());
            managerBalance.setParantName(sessionManager.getName());
            managerBalance.setParantNumber(sessionManager.getNumber());
            managerBalance.setManagerName(sessionManager.getName());
            managerBalance.setManagerNumber(sessionManager.getNumber());
            managerBalance.setLevel(sessionManager.getLevel());

            managerServer.saveManagerBalance(managerBalance);
        }


        return CloudUtil.buildAjaxOperationJson(dbProduct != null, dbProduct != null ? operation + "产品成功" : operation + "产品失败").toString();
    }

    private boolean checkProduct(Product product) {
        List<Product> list = productServer.findAllByProduct(product);
        if (list.size() > 0) {
            if (product.getId() == null) {
                return false;
            } else {
                for (Product dbProduct : list) {
                    if (dbProduct.getId().intValue() != product.getId().intValue()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
