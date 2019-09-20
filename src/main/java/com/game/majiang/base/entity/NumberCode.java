package com.game.majiang.base.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sujianfeng on 2017/6/4.
 */
@Entity
@Table(name = "mj_number")
@DynamicUpdate
public class NumberCode extends BaseEntity {
    @Column(name = "type")
    private Integer type;
    @Column(name = "description")
    private String  description;
    @Column(name = "number_value")
    private Integer  numberValue;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Integer numberValue) {
        this.numberValue = numberValue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Number{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", exdata1='").append(exdata1).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", exdata2='").append(exdata2).append('\'');
        sb.append(", numberValue='").append(numberValue).append('\'');
        sb.append(", exdata3='").append(exdata3).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
