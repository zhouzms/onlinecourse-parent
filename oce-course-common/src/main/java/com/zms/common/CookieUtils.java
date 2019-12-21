package com.zms.common;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zms
 * Date: 2019/12/16 21:18
 */
public class CookieUtils {
    public static Cookie savaCookies(HttpServletResponse response,String key,Integer time){
        //创建Cookie
        Cookie cookie = new Cookie(key, key);
        //设置Cookie的最大生命周期,否则浏览器关闭后Cookie即失效
        cookie.setMaxAge(time);
        cookie.setPath("/");
        //将Cookie加到response中
        response.addCookie(cookie);
        return cookie;
    }
    public static void setCookieTime(Cookie cookieTime,HttpServletResponse response){
        cookieTime.setMaxAge(0);
        response.addCookie(cookieTime);
    }
}
