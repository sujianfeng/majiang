package com.game.majiang.base.dao;

import com.game.majiang.base.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sujianfeng on 2017/6/4.
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    List<Product> findAll(Specification<Product> spec);

    Product findByNumber(int number);
}
