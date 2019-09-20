package com.game.majiang.base.dao;

import com.game.majiang.base.entity.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
@Repository
public interface ManagerDao extends JpaRepository<Manager,Integer>{

    Manager findByUserName(String userName);

    Manager findByNumber(int number);

    List<Manager> findByOrderByUpdateTime();
    List<Manager> findByParantNumberOrderByUpdateTime(int parantNumber);

    Page<Manager> findAll(Specification<Manager> spec, Pageable pageable);

    List<Manager> findAll(Specification<Manager> spec);

    List<Manager> findByParantNumber(int number);
}
