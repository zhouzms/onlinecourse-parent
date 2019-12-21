package com.zms.common;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author zms
 * Date: 2019/12/1 21:28
 */
public class MD5Utils {
    public static String getMD5values(String password,String salt){
        return new Md5Hash(password,salt,3).toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5Utils.getMD5values("zms123","0795"));
    }
}
