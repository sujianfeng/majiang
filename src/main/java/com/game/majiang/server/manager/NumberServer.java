package com.game.majiang.server.manager;

import com.game.majiang.base.dao.NumberCodeDao;
import com.game.majiang.base.entity.NumberCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by yyc on 2017/6/10.
 */
@Service
public class NumberServer {

    @Autowired
    private NumberCodeDao numberCodeDao;

    /**
     * 根据类型获得新的产品编号
     * @param type
     * @return
     */
    public int getNumberByType(int type){

        int random = new Random().nextInt(10);
        random = random > 0 ? random : 10;
        NumberCode numberCode= numberCodeDao.findByType(type);
        int newNumber=numberCode.getNumberValue()+random;
        numberCode.setNumberValue(newNumber);
        numberCodeDao.saveAndFlush(numberCode);
        return newNumber;
    }
}
