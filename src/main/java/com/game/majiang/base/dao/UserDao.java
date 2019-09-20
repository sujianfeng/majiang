package com.game.majiang.base.dao;

import com.game.majiang.base.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by sujianfeng on 2017/6/4.
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Page<User> findAll(Specification<User> spec, Pageable pageable);

    User findByUserId(int userId);

    String BATCHCHARGE_SQL = "update mj_user set balance = balance + :balance";

    @Modifying
    @Query(value = BATCHCHARGE_SQL, nativeQuery = true)
    int batchCharge(@Param("balance") Integer balance);
}
