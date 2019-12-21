package com.zms.service;

import com.zms.domin.Cert;

import java.util.List;

/**
 * @author zms
 * Date: 2019/12/20 22:55
 */
public interface CertService {
    /**
     * 通过角色类型获取所有的父菜单
     * @param
     * @return
     */
    List<Cert> getAllParentCertDate(Cert cert);

    /**
     * 通过父菜单id,获取其下的所有子菜单
     * @param
     * @return
     */
    List<Cert> getAllChildCertById(Cert cert);
}
