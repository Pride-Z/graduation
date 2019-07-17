package com.czxy.person.service;

import com.czxy.person.dao.UserMapper;
import com.czxy.person.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Resource
    private UserMapper userMapper;

    public void save(User user) {
        userMapper.insert(user);
    }

    /**
     * 通过手机号查找用户
     *
     * @param telephone
     * @return
     */
    public User findUserByTelephone(String telephone) {
        User record = new User();
        record.setTelephone(telephone);
        User user = userMapper.selectOne(record);
        return user;
    }

    //修改状态
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 返回查询的用户
     *
     * @return
     */
    public List<User> findUser() {
        return userMapper.findUser();
    }

    /**
     * 删除离职用户
     *
     * @param id
     */
    public void delEmp(int id) {

        userMapper.deleteUser(id);

    }

    /**
     * @param uid
     * @param telephone
     * @param mail
     */
    public void addUser(int uid, String name, String telephone, String mail) {
        userMapper.addUser(uid, name, telephone, mail);

    }


}
