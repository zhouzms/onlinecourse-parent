package com.zms.domin;

import java.io.Serializable;

/**
 * @author zms
 * Date: 2019/11/24 16:41
 */
public class User implements Serializable {
    /**
     * 用户id ,非自增
     */
    private String id;
    /**
     * 编号
     */
    private String number;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 用户创建时间
     */
    private String createTime;
    /**
     * 登录时间
     */
    private String loginTime;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户家庭住址
     */
    private String address;
    /**
     * 用户性别
     */
    private int sex;
    /**
     * 登录ip地址
     */
    private String ip;
    /**
     * 用户状态 1-可用 0-删除
     */
    private int status;
    /**
     * 用户类型 1-管理员 2-老师 3-学生
     */
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", createTime=" + createTime +
                ", loginTime=" + loginTime +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", sex=" + sex +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
