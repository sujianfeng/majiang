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
@Table(name = "mj_manager")
@DynamicUpdate
public class Manager extends BaseEntity{

    @Column(name = "number")
    private int number;

    @Column(name = "name")
    private String name;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "parant_number")
    private int parantNumber;

    @Column(name = "parant_name")
    private String parantName;

    @Column(name = "level")
    private int level;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public Manager(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Manager() {
    }


    public Manager(int number, String name, String userName, String password, int parantNumber, String parantName, int level, Date createTime, Date updateTime) {
        this.number = number;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.parantNumber = parantNumber;
        this.parantName = parantName;
        this.level = level;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getParantNumber() {
        return parantNumber;
    }

    public void setParantNumber(int parantNumber) {
        this.parantNumber = parantNumber;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public String getParantName() {
        return parantName;
    }

    public void setParantName(String parantName) {
        this.parantName = parantName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Manager{");
        sb.append("id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", exdata1='").append(exdata1).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", exdata2='").append(exdata2).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", exdata3='").append(exdata3).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", parantNumber=").append(parantNumber);
        sb.append(", parantName=").append(parantName);
        sb.append(", level=").append(level);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append('}');
        return sb.toString();
    }
}
