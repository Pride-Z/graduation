package com.czxy.person.dao;

import com.czxy.person.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {


    /**
     * 查询用户
     *
     * @return
     */
    @Select("SELECT * FROM user ")
    List<User> findUser();

    /**
     * 删除用户
     *
     * @param id
     */
    @Delete("DELETE from `user` WHERE `user`.uid= #{id}")
    void deleteUser(int id);

    /**
     * 管理员新增用户
     *
     * @param uid
     * @param name
     * @param telephone
     * @param mail
     */
    @Insert("INSERT INTO `user` VALUES (#{uid},#{name},#{telephone},'123456',#{mail},'1')")
    void addUser(int uid, String name, String telephone, String mail);


}
