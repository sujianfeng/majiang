package com.game.majiang.server.manager;

import com.game.majiang.base.dao.ProductDao;
import com.game.majiang.base.entity.Manager;
import com.game.majiang.base.entity.Product;
import com.game.majiang.constants.ManagerConstants;
import com.game.majiang.constants.NumberConstants;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by yyc on 2017/6/10.
 */
@Service
public class ProductServer {
    @Autowired
    private NumberServer numberServer;

    @Autowired
    private ProductDao productDao;

    public Page<Product> findAll(final String[] condition, Pageable pageable) {
        return productDao.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //productName
                if (StringUtils.isNotBlank(condition[0])) {
                    list.add(cb.like(root.get("name").as(String.class), '%' + condition[0] + '%'));
                }
                //productNumber
                //if (StringUtils.isNotBlank(condition[1])) {
                //    list.add(cb.like(root.get("number").as(String.class), '%' + condition[1] + '%'));
                //}

                Predicate[] predicate = new Predicate[list.size()];
                return cb.and(list.toArray(predicate));
            }
        }, pageable);
    }
    /**
     * 添加或者修改产品
     * @param name
     * @return
     */
    public String addProduct(HttpServletRequest request, String name,int numer){
        Manager userManager=(Manager)request.getSession().getAttribute("user");
        JSONObject jsonObject=new JSONObject();
        int code=0;
        int productNumber=0;
        if(userManager.getLevel()== ManagerConstants.ADMIN_MANAGER){
            Product product=null;
            if(numer>0){
                product=productDao.findByNumber(numer);
            }else{
                product=createProduct(name);
            }
            product=productDao.save(product);
            productNumber=product.getNumber();
            code=1;
        }else{
            code=2;
        }
        jsonObject.put("code",code);
        jsonObject.put("productNumber",productNumber);
        return jsonObject.toString();
    }

    /**
     * 创建产品
     * @param name
     * @return
     */
    public Product createProduct(String name){
        Product product=new Product();
        product.setName(name);
        product.setNumber(numberServer.getNumberByType(NumberConstants.PRODUCT_MANAGER_TYPE));
        product.setCreateTime(new Date());
        return product;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product getProductById(Integer id) {
        return productDao.getOne(id);
    }

    public List<Product> findAllByProduct(final Product product) {
        return productDao.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //productName
                if (StringUtils.isNotBlank(product.getName())) {
                    list.add(cb.equal(root.get("name").as(String.class), product.getName()));
                }
                Predicate[] predicate = new Predicate[list.size()];
                return cb.and(list.toArray(predicate));
            }
        });
    }

    public Product saveProduct(Product dbProduct) {
        return productDao.saveAndFlush(dbProduct);
    }
}
