package com.zms;

import com.zms.domin.User;
import com.zms.service.UserService;
import com.zms.test.TestApi;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author zms
 * Date: 2019/11/23 15:43
 */
public class ProviderTest extends NTTest{
    private UserService userService =null;
    @Before
    public void init(){
        userService= this.initParent(UserService.class);
    }
    @Test
    public void test(){
        User admin = userService.queryUserServiceByNumber("admin");
        System.out.println(admin.toString());
    }
}
