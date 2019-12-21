package com.zms.domin;

import java.io.Serializable;

/**
 * @author zms
 * Date: 2019/12/16 23:29
 */
public class RoleTable implements Serializable {
    /**
     * 角色id
     */
    private int id;
    /**
     * 角色类型
     */
    private int type;
    /**
     * 角色名
     */
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
