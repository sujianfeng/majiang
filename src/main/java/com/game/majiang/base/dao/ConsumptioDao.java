package com.game.majiang.base.dao;

import com.game.majiang.base.entity.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/6/4.
 */
@Repository
public interface ConsumptioDao extends JpaRepository<Consumption,Integer>{

    String SQL_CUR_BALANCE = "select case when sum(balance) is null then 0 else sum(balance) end as sum_balance from mj_consumption where create_time > :curDate";

    String SQL_YES_BALANCE = "select case when sum(balance) is null then 0 else sum(balance) end as sum_balance from mj_consumption where create_time between :beforeDate and :afterDate";

    String SQL_ALL_BALANCE = "select case when sum(balance) is null then 0 else sum(balance) end as sum_balance from mj_consumption";

    @Query(value = SQL_CUR_BALANCE, nativeQuery = true)
    int getCurBalance(@Param("curDate") String curDate);

    @Query(value = SQL_ALL_BALANCE, nativeQuery = true)
    int getALlBalance();

    @Query(value = SQL_YES_BALANCE, nativeQuery = true)
    int getYesBalance(@Param("beforeDate") String beforeDate, @Param("afterDate") String afterDate);
}
