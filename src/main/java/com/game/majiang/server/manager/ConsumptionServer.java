package com.game.majiang.server.manager;

import com.game.majiang.base.dao.ConsumptioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sujianfeng on 2017/7/2.
 */
@Service
public class ConsumptionServer {
    @Autowired
    private ConsumptioDao consumptioDao;


    public int getCurBalance() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

        String curDate = simpleDateFormat.format(new Date());

        return consumptioDao.getCurBalance(curDate);

    }

    public int getAllBalance() {

        return consumptioDao.getALlBalance();
    }

    public int getYesBalance() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Date date = new Date();
        date.setDate(date.getDate() - 1);

        String beforeDate = simpleDateFormat.format(date);
        String afterDate = simpleDateFormat1.format(date);

        return consumptioDao.getYesBalance(beforeDate, afterDate);

    }
}
