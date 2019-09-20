package com.game.majiang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sujianfeng on 2017/7/9.
 */
public class Test {
    @org.junit.Test
    public void test() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Date date = simpleDateFormat.parse("2017-07-01 11:11:11");
        date.setDate(date.getDate()-1);
        System.out.println(date.toLocaleString());
    }
}
