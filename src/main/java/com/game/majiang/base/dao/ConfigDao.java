package com.game.majiang.base.dao;

import com.game.majiang.base.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/6/4.
 */
@Repository
public interface ConfigDao extends JpaRepository<Config,Integer>{

    Config findByType(int type);
}
