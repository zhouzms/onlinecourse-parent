package com.zms.web.filter;

import com.zms.domin.PermissionTable;
import com.zms.domin.RoleTable;
import com.zms.domin.User;
import com.zms.domin.UserLogin;
import com.zms.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zms
 * Date: 2019/11/24 15:58
 */
@Service
public class MyRealm  extends AuthorizingRealm {
    private static final Logger logger= LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //不走授权，需要加入切面
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        User user =(User)principalCollection.getPrimaryPrincipal();
        List<RoleTable> roles = userService.queryRoleByUsername(user.getType());
        Collection<String> r=new ArrayList<>();
        Collection<String> p=new ArrayList<>();
        if(roles!=null){
            for(RoleTable role:roles){
                r.add(role.getName());
                List<PermissionTable> permissions = userService.queryPermisionByRoleId(role.getId());
                for (PermissionTable permission:permissions){
                    p.add(permission.getPermison());
                }
            }
            info.addRoles(r);
            logger.info("角色"+r+"------");
            info.addStringPermissions(p);
            logger.info("权限"+p+"======");
        }
        return info;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username=(String)authenticationToken.getPrincipal();
        User user=null;
        //验证是否存在此用户
        try {
             user = userService.queryUserServiceByNumber(username);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getNumber()),getName());
        logger.info("---------从数据库中得到的密码---------");
        logger.info(user.getPassword());
        logger.info("---------加盐得到的密码---------");
        return info;
    }

    @Override
    public String getName() {
        return "MyRealm";
    }
}
