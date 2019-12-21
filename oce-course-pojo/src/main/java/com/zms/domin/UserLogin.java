package com.zms.domin;

import java.io.Serializable;
import java.util.Map;

/**
 * @author zms
 * Date: 2019/12/1 16:25
 */
public class UserLogin implements Serializable {
    private String username;
    private String password;
    private String rememberMe;
    private Map<String,String> map;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
