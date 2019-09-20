package com.game.majiang.base.dao;

import com.game.majiang.base.entity.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by sujianfeng on 2017/6/4.
 */
@Repository
public interface RechargeDao  extends JpaRepository<Recharge, Integer>, JpaSpecificationExecutor<Recharge> {
}
