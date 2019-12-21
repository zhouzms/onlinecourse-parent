package com.zms.domin;

import java.io.Serializable;
import java.util.List;

/**
 * @author zms
 * Date: 2019/12/20 22:37
 */
public class Cert implements Serializable {
    /**
     * 菜单id
     */
    private int id;
    /**
     * 菜单名
     */
    private String certName;
    /**
     * 菜单跳转链接
     */
    private String certUrl;
    /**
     * 菜单图标
     */
    private String certPic;
    /**
     * 父级id
     */
    private int parentId;
    /**
     * 是否是叶子节点
     */
    private int isLeaf;
    /**
     * 角色类型 1-管理员 2-老师 3-学生
     */
    private int type;
    /**
     * 是否删除
     */
    private int flag;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 子菜单
     * @return
     */
    private List<Cert> childrenCert;

    public List<Cert> getChildrenCert() {
        return childrenCert;
    }

    public void setChildrenCert(List<Cert> childrenCert) {
        this.childrenCert = childrenCert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertUrl() {
        return certUrl;
    }

    public void setCertUrl(String certUrl) {
        this.certUrl = certUrl;
    }

    public String getCertPic() {
        return certPic;
    }

    public void setCertPic(String certPic) {
        this.certPic = certPic;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
