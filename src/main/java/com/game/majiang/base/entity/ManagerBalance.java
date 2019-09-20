package com.game.majiang.base.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/6/4.
 */
@Entity
@Table(name = "mj_manager_balance")
@DynamicUpdate
public class ManagerBalance extends BaseEntity{

    @Column(name = "manager_number")
    private int managerNumber;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manager_username")
    private String managerUserName;

    @Column(name = "level")
    private int level;

    @Column(name = "parant_number")
    private int parantNumber;

    @Column(name = "parant_name")
    private String parantName;

    @Column(name = "product_number")
    private int productNumber;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "balance")
    private int balance;
    @Column(name = "cost")
    private int cost;

    public int getManagerNumber() {
        return managerNumber;
    }

    public void setManagerNumber(int managerNumber) {
        this.managerNumber = managerNumber;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParantNumber() {
        return parantNumber;
    }

    public void setParantNumber(int parantNumber) {
        this.parantNumber = parantNumber;
    }

    public String getParantName() {
        return parantName;
    }

    public void setParantName(String parantName) {
        this.parantName = parantName;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getManagerUserName() {
        return managerUserName;
    }

    public void setManagerUserName(String managerUserName) {
        this.managerUserName = managerUserName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ManagerBalance{");
        sb.append("id=").append(id);
        sb.append(", managerNumber=").append(managerNumber);
        sb.append(", exdata1='").append(exdata1).append('\'');
        sb.append(", managerName='").append(managerName).append('\'');
        sb.append(", exdata2='").append(exdata2).append('\'');
        sb.append(", exdata3='").append(exdata3).append('\'');
        sb.append(", managerUserName='").append(managerUserName).append('\'');
        sb.append(", level=").append(level);
        sb.append(", parantNumber=").append(parantNumber);
        sb.append(", parantName='").append(parantName).append('\'');
        sb.append(", productNumber=").append(productNumber);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
