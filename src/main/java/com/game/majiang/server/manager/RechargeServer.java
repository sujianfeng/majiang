package com.game.majiang.server.manager;

import com.game.majiang.base.dao.ManagerDao;
import com.game.majiang.base.dao.RechargeDao;
import com.game.majiang.base.entity.Manager;
import com.game.majiang.base.entity.ManagerBalance;
import com.game.majiang.base.entity.Recharge;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyc on 2017/6/16.
 */
@Service
public class RechargeServer {
    @Autowired
    private RechargeDao rechargeDao;
    @Autowired
    private ManagerDao managerDao;

    public Page<Recharge> findAll(final String[] condition, Pageable pageable, final List<ManagerBalance> managerBalanceList, final Manager sessionManager) {
        return rechargeDao.findAll(new Specification<Recharge>() {
            @Override
            public Predicate toPredicate(Root<Recharge> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //productNumber
                if (StringUtils.isNotBlank(condition[0])) {
                    if (!(condition[0].equals("-1"))) {
                        list.add(cb.equal(root.get("productNumber").as(Integer.class), Integer.parseInt(condition[0])));
                    }
                }
                //balance
                if (StringUtils.isNotBlank(condition[1])) {
                    list.add(cb.like(root.get("rechargeId").as(String.class), '%' + condition[1] + '%'));
                }

                if (StringUtils.isNotBlank(condition[2])) {
                    if (!(condition[2].equals("-1"))) {
                        list.add(cb.equal(root.get("rechargeType").as(Integer.class), Integer.parseInt(condition[2])));
                    }
                }

                //if (StringUtils.isNotBlank(condition[3])) {
                //    if (!(condition[3].equals("-1"))) {
                //        list.add(cb.equal(root.get("rechargeType").as(Integer.class), Integer.parseInt(condition[3])));
                //    }
                //}

                //过滤产品
                Expression<Integer> exp = root.get("productNumber").as(Integer.class);
                List<Integer> intList = new ArrayList<>();
                for (ManagerBalance managerBalance : managerBalanceList) {
                    intList.add(managerBalance.getProductNumber());
                }
                list.add(exp.in(intList));

                //过滤身份
                if (sessionManager.getLevel() == 1) {
                    List<Manager> managerList = managerDao.findByParantNumber(sessionManager.getNumber());
                    Expression<Integer> exp1 = root.get("managerNumber").as(Integer.class);
                    List<Integer> numList = new ArrayList<>();
                    for (Manager manager : managerList) {
                        numList.add(manager.getNumber());
                    }
                    numList.add(sessionManager.getNumber());
                    list.add(exp1.in(numList));
                }

                if (sessionManager.getLevel() == 2) {
                    list.add(cb.equal(root.get("managerNumber").as(Integer.class), sessionManager.getNumber()));
                }


                Predicate[] predicate = new Predicate[list.size()];
                return cb.and(list.toArray(predicate));
            }
        }, pageable);

    }
}
