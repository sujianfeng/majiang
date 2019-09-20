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
@Table(name = "mj_user")
@DynamicUpdate
public class User extends BaseEntity {
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "balance")
    private Integer balance;
    @Column(name = "wxid")
    private String wxid;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_number")
    private Integer productNumber;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "recent_login")
    private Date recentLogin;
    @Column(name = "cost")
    private Integer cost;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRecentLogin() {
        return recentLogin;
    }

    public void setRecentLogin(Date recentLogin) {
        this.recentLogin = recentLogin;
    }


    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", exdata1='").append(exdata1).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", exdata2='").append(exdata2).append('\'');
        sb.append(", wxid='").append(wxid).append('\'');
        sb.append(", exdata3='").append(exdata3).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", productNumber=").append(productNumber);
        sb.append(", sex=").append(sex);
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", recentLogin=").append(recentLogin);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
