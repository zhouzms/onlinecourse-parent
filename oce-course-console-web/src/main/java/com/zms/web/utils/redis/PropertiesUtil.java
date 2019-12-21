package com.zms.web.utils.redis;

import com.hazelcast.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author zms
 * Date: 2019/12/21 11:18
 */
public class PropertiesUtil {
    private static final Logger log= LoggerFactory.getLogger(PropertiesUtil.class);
    private static Properties props;
    public static void loadProperties(String propertiesName){
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesName),"UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取异常",e);
        }
    }
    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtil.isNullOrEmpty(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtil.isNullOrEmpty(defaultValue)){
            value = defaultValue;
        }
        return value.trim();
    }

    public static void main(String[] args) {
        PropertiesUtil.loadProperties("redis.properties");
        String s = PropertiesUtil.getProperty("redis1.ip");
        System.out.println(s);
    }
}
