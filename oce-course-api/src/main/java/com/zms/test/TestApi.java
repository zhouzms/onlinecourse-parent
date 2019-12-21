package com.zms.test;

import com.zms.pojo.test.Test;

import java.util.List;

/**
 * @author zms
 * Date: 2019/11/23 16:29
 */
public interface TestApi {
    /**
     * 测试流程
     * @return
     */
    List<Test> getAll();
}
