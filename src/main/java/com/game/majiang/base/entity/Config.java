package com.game.majiang.base.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/6/4.
 */
@Entity
@Table(name = "mj_config")
@DynamicUpdate
public class Config extends BaseEntity{

    @Column(name = "type")
    private int type;
    @Column(name = "description")
    private String description;
    @Column(name = "config_value")
    private String configValue;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Config{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", exdata1='").append(exdata1).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", exdata2='").append(exdata2).append('\'');
        sb.append(", configValue='").append(configValue).append('\'');
        sb.append(", exdata3='").append(exdata3).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
