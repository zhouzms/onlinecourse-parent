package com.zms.serviceimpl;

import com.zms.dao.api.UserDao;
import com.zms.domin.PermissionTable;
import com.zms.domin.RoleTable;
import com.zms.domin.User;
import com.zms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zms
 * Date: 2019/11/24 17:04
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User queryUserServiceByNumber(String number) {
        return userDao.queryByNumber(number);
    }

    @Override
    public List<RoleTable> queryRoleByUsername(int type) {
        return userDao.queryByRoleId(type);
    }

    @Override
    public List<PermissionTable> queryPermisionByRoleId(int roleid) {
        return userDao.queryByPermisionId(roleid);
    }

    @Override
    public void updateLoginTime(User currentUser) {
         userDao.update(currentUser);
    }

    @Override
    public User queryAndUpdateUser(String number) {
        return userDao.queryUpdateUserMsgDao(number);
    }

    @Override
    public int updatePasswordById(User user) {
        return userDao.update(user);
    }
    @Override
    public int insertUser(User currentUser) {
        return userDao.insertUser(currentUser);
    }
}
