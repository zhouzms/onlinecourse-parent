package com.zms.domin;

import java.io.Serializable;

/**
 * @author zms
 * Date: 2019/12/16 23:31
 */
public class PermissionTable implements Serializable {
    /**
     * 权限id
     */
    private int id;
    /**
     * 角色id
     */
    private int roleid;
    /**
     * 权限表达式
     */
    private String permison;
    /**
     * 权限描述
     */
    private String context;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getPermison() {
        return permison;
    }

    public void setPermison(String permison) {
        this.permison = permison;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
