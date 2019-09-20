package com.game.majiang.base.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/4.
 */
@Entity
@Table(name = "mj_consumption")
@DynamicUpdate
public class Consumption extends BaseEntity{

    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "product_number")
    private int productNumber;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "balance")
    private int balance;

    @Column(name = "create_time")
    private Date createTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Consumption{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", exdata1='").append(exdata1).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", exdata2='").append(exdata2).append('\'');
        sb.append(", exdata3='").append(exdata3).append('\'');
        sb.append(", productNumber=").append(productNumber);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
