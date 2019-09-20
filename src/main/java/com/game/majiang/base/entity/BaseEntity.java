package com.game.majiang.base.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sujianfeng on 2017/3/29.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    protected Integer id;

    @Column(name = "exdata1")
    protected String exdata1;
    @Column(name = "exdata2")
    protected String exdata2;
    @Column(name = "exdata3")
    protected String exdata3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExdata1() {
        return exdata1;
    }

    public void setExdata1(String exdata1) {
        this.exdata1 = exdata1;
    }

    public String getExdata2() {
        return exdata2;
    }

    public void setExdata2(String exdata2) {
        this.exdata2 = exdata2;
    }

    public String getExdata3() {
        return exdata3;
    }

    public void setExdata3(String exdata3) {
        this.exdata3 = exdata3;
    }
}
