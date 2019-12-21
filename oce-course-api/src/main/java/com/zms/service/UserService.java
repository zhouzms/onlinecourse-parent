package com.zms.service;

import com.zms.domin.PermissionTable;
import com.zms.domin.RoleTable;
import com.zms.domin.User;

import javax.management.relation.Role;
import java.util.List;

/**
 * @author zms
 * Date: 2019/11/24 16:39
 */
public interface UserService {
    /**
     * 通过编号查询user部分信息
     * @param number
     * @return
     */
    User queryUserServiceByNumber(String number);

    /**
     * 通过用户获取其角色
     * @param type
     * @return
     */
    List<RoleTable> queryRoleByUsername(int type);

    /**
     * 通过角色id获取权限
     * @param roleid
     * @return
     */
    List<PermissionTable> queryPermisionByRoleId(int roleid);

    /**
     * 更新登录时间
     * @param currentUser
     */
    void updateLoginTime(User currentUser);

    /**
     * 查询用户可以更改的信息
     * @param number
     * @return
     */
    User queryAndUpdateUser(String number);

    /**
     * 更改密码
     * @param user
     * @return
     */
    int updatePasswordById(User user);
}
