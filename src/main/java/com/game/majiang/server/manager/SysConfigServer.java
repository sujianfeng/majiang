package com.game.majiang.server.manager;

import com.game.majiang.base.dao.ManagerBalanceDao;
import com.game.majiang.base.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by sujianfeng on 2017/6/17.
 */
@Service
public class SysConfigServer {
    @Autowired
    private ManagerBalanceDao managerBalanceDao;
    @Autowired
    private UserDao userDao;

    @Transactional
    public void recharge(String rechargeType, String rechargeCount) {
        switch (rechargeType) {
            case "1":
                managerBalanceDao.batchCharge(Integer.parseInt(rechargeType), Integer.parseInt(rechargeCount));
                break;
            case "2":
                managerBalanceDao.batchCharge(Integer.parseInt(rechargeType), Integer.parseInt(rechargeCount));
                break;
            case "3":
                userDao.batchCharge(Integer.parseInt(rechargeCount));
                break;
        }
    }
}
