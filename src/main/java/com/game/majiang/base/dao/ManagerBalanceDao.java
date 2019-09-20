package com.game.majiang.base.dao;

import com.game.majiang.base.entity.ManagerBalance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
@Repository
public interface ManagerBalanceDao extends JpaRepository<ManagerBalance,Integer>{

    List<ManagerBalance> findByManagerNumber(int parantNumber);

    Page<ManagerBalance> findAll(Specification<ManagerBalance> spec, Pageable pageable);

    ManagerBalance findByManagerNumberAndProductNumber(int managerNumber, int productNumber);

    String BATCHCHARGE_SQL = "update mj_manager_balance set balance = balance + :balance where level = :level";

    @Modifying
    @Query(value = BATCHCHARGE_SQL, nativeQuery = true)
    int batchCharge(@Param("level") Integer level, @Param("balance") Integer balance);
}
