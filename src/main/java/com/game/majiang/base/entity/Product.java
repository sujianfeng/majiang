package com.game.majiang.base.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by sujianfeng on 2017/5/27.
 */
@Entity
@Table(name = "mj_product")
@DynamicUpdate
public class Product extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "number")
    private Integer number;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {
    }

    public Product(String name, Integer number, Date createTime, Date updateTime) {
        this.name = name;
        this.number = number;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", exdata1='").append(exdata1).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append(", exdata2='").append(exdata2).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", exdata3='").append(exdata3).append('\'');
        sb.append(", updateTime=").append(updateTime);
        sb.append('}');
        return sb.toString();
    }
}
