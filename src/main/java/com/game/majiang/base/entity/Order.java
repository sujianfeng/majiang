package com.game.majiang.base.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sujianfeng on 2017/5/27.
 */
@Entity
@Table(name = "mj_order")
@DynamicUpdate
public class Order  extends BaseEntity {
}
