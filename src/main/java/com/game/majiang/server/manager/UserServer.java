package com.game.majiang.server.manager;

import com.game.majiang.base.dao.ManagerBalanceDao;
import com.game.majiang.base.dao.RechargeDao;
import com.game.majiang.base.dao.UserDao;
import com.game.majiang.base.entity.Manager;
import com.game.majiang.base.entity.ManagerBalance;
import com.game.majiang.base.entity.Recharge;
import com.game.majiang.base.entity.User;
import com.game.majiang.constants.ConfigConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sujianfeng on 2017/6/16.
 */
@Service
public class UserServer {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ManagerBalanceDao managerBalanceDao;
    @Autowired
    private RechargeDao rechargeDao;

    public Page<User> findAll(final String[] condition, Pageable pageable, final List<ManagerBalance> managerBalanceList, final Manager sessionManager) {
        return userDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //productNumber
                if (StringUtils.isNotBlank(condition[0])) {
                    if (!(condition[0].equals("-1"))) {
                        list.add(cb.equal(root.get("productNumber").as(Integer.class), Integer.parseInt(condition[0])));
                    }
                }
                //userid
                if (StringUtils.isNotBlank(condition[1])) {
                    list.add(cb.equal(root.get("userId").as(String.class), condition[1]));
                }

                if (sessionManager.getLevel() != 0) {
                    //userid
                    if (StringUtils.isBlank(condition[1])) {
                        list.add(cb.equal(root.get("userId").as(String.class), "0"));
                    }
                }


                //定义一个Expression
                Expression<Integer> exp = root.get("productNumber").as(Integer.class);
                List<Integer> intList = new ArrayList<>();
                for (ManagerBalance managerBalance : managerBalanceList) {
                    intList.add(managerBalance.getProductNumber());
                }
                list.add(exp.in(intList));

                Predicate[] predicate = new Predicate[list.size()];
                return cb.and(list.toArray(predicate));
            }
        }, pageable);

    }

    @Transactional
    public boolean recharge(ManagerBalance managerBalance, User user, int balance) {
        try {
            //存
            user.setBalance(user.getBalance() + balance);
            userDao.saveAndFlush(user);
            //取
            managerBalance.setBalance(managerBalance.getBalance() - balance);
            managerBalanceDao.saveAndFlush(managerBalance);
            //记录
            Recharge recharge = new Recharge();
            recharge.setManagerNumber(managerBalance.getManagerNumber());
            recharge.setManagerName(managerBalance.getManagerName());
            recharge.setProductNumber(user.getProductNumber());
            recharge.setProductName(user.getProductName());
            recharge.setRechargeType(ConfigConstants.RECHARGE_TYPE_USER);
            recharge.setRechargeId(user.getUserId());
            recharge.setRechargeName(user.getNickName());
            recharge.setCreateTime(new Date());
            recharge.setBalance(balance);
            rechargeDao.saveAndFlush(recharge);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findUserByUserId(int userId) {
        return userDao.findByUserId(userId);
    }
}
