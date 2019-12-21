package com.zms.test;

import com.zms.dao.api.TestDao;
import com.zms.pojo.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zms
 * Date: 2019/11/23 16:30
 */
@Service
public class ITestApi implements TestApi{
    @Autowired
    private TestDao dao;
    @Override
    public List<Test> getAll() {
        return dao.getAllDao();
    }
}
