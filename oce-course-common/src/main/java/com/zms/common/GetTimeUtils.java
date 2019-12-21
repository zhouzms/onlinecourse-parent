package com.zms.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zms
 * Date: 2019/12/19 22:07
 */
public class GetTimeUtils {
    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime(){
        Date time=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddhhmmss");
        return dateFormat.format(time);
    }
}
