package com.zms.common;

import java.util.Random;

/**
 * @author zms
 * Date: 2019/12/19 22:53
 */
public class UUIDUtils {
    public static String getUUID(){
        String time = GetTimeUtils.getCurrentTime();
        Random random=new Random();
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<4;i++){
            stringBuilder.append(random.nextInt(10));
        }
        return time+stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtils.getUUID());
    }
}
