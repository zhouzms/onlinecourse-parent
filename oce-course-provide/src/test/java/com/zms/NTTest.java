package com.zms;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zms
 * Date: 2019/11/23 22:25
 */
public class NTTest {
    private final  static Logger logger= LoggerFactory.getLogger(NTTest.class);
    protected  <T> T initParent(Class<T> t){
        ApplicationConfig applicationConfig=new ApplicationConfig();
        ReferenceConfig<T> referenceConfig=new ReferenceConfig<>();
        RegistryConfig registryConfig=new RegistryConfig();
        //设置名称
        applicationConfig.setName("consume-test");
        //设置注册中心
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        registryConfig.setCheck(false);
        registryConfig.setTimeout(4000);
        //设置引用中心对象
        referenceConfig.setInterface(t);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setRetries(3);
        return referenceConfig.get();
    }
}
