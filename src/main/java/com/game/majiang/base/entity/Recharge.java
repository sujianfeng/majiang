package com.game.majiang.base.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by sujianfeng on 2017/6/4.
 */
@Entity
@Table(name = "mj_recharge")
@DynamicUpdate
public class Recharge extends BaseEntity {
    @Column(name = "manager_number")
    private Integer managerNumber;
    @Column(name = "manager_name")
    private String managerName;
    @Column(name = "product_number")
    private Integer productNumber;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "recharge_type")
    private Integer rechargeType;
    @Column(name = "recharge_id")
    private Integer rechargeId;
    @Column(name = "recharge_name")
    private String rechargeName;
    @Column(name = "balance")
    private Integer balance;
    @Column(name = "create_time")
    private Date createTime;

    public Integer getManagerNumber() {
        return managerNumber;
    }

    public void setManagerNumber(Integer managerNumber) {
        this.managerNumber = managerNumber;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(Integer rechargeType) {
        this.rechargeType = rechargeType;
    }

    public Integer getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Integer rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getRechargeName() {
        return rechargeName;
    }

    public void setRechargeName(String rechargeName) {
        this.rechargeName = rechargeName;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
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
        final StringBuffer sb = new StringBuffer("Recharge{");
        sb.append("id=").append(id);
        sb.append(", managerNumber=").append(managerNumber);
        sb.append(", exdata1='").append(exdata1).append('\'');
        sb.append(", exdata2='").append(exdata2).append('\'');
        sb.append(", managerName='").append(managerName).append('\'');
        sb.append(", exdata3='").append(exdata3).append('\'');
        sb.append(", productNumber=").append(productNumber);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", rechargeType=").append(rechargeType);
        sb.append(", rechargeId=").append(rechargeId);
        sb.append(", rechargeName='").append(rechargeName).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
