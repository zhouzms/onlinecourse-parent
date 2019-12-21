package com.zms.dao.api;

import com.zms.pojo.test.Test;

import java.util.List;

/**
 * @author zms
 * Date: 2019/11/23 16:37
 */

public interface TestDao {
    /**
     * 测试dao
     * @return
     */
    List<Test> getAllDao();
}
