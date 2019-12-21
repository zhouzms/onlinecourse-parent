package com.zms.dao.api;

import com.zms.domin.PermissionTable;
import com.zms.domin.RoleTable;
import com.zms.domin.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zms
 * Date: 2019/11/24 16:51
 */
public interface UserDao {
    /**
     * 通过编号查询User
     * @param number 编号
     * @return
     */
    User queryByNumber(@Param("number") String number);

    /**
     * 获取type相应的角色
     * @param type
     * @return
     */
    List<RoleTable> queryByRoleId(int type);

    /**
     * 获取角色对应的权限
     * @param roleid
     * @return
     */
    List<PermissionTable> queryByPermisionId(int roleid);

    /**
     * 更新
     * @param currentUser
     */
    void update(User currentUser);

    /**
     * 查询用户可以更改的信息
     * @param number
     * @return
     */
    User queryUpdateUserMsgDao(String number);
}
