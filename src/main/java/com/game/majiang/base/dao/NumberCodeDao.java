package com.game.majiang.base.dao;

import com.game.majiang.base.entity.NumberCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by sujianfeng on 2017/6/4.
 */
@Repository
public interface NumberCodeDao extends JpaRepository<NumberCode, Integer>, JpaSpecificationExecutor<NumberCode> {

    NumberCode findByType(int type);
}
