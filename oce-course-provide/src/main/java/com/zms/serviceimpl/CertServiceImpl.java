package com.zms.serviceimpl;

import com.zms.dao.api.CertDao;
import com.zms.domin.Cert;
import com.zms.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zms
 * Date: 2019/12/20 23:02
 */
@Service
public class CertServiceImpl implements CertService {
    @Autowired
    private CertDao certDao;
    @Override
    public List<Cert> getAllParentCertDate(Cert cert) {
        return certDao.queryCertList(cert);
    }

    @Override
    public List<Cert> getAllChildCertById(Cert cert) {
        return certDao.queryCertList(cert);
    }
}
