package com.zms.dao.api;

import com.zms.domin.Cert;

import java.util.List;

/**
 * @author zms
 * Date: 2019/12/20 23:04
 */
public interface CertDao {
    /**
     * 通过角色类型查询父节点的菜单
     * @param cert
     * @return
     */
    List<Cert> queryCertList(Cert cert);
}
