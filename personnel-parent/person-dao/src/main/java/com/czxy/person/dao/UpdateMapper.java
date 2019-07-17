package com.czxy.person.dao;

import com.czxy.person.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UpdateMapper extends Mapper<User> {

    @Update("update user set user.password=#{password} where user.telephone=#{telephone}")
    //void update(String telephone,  String password);
    int update(@Param("password")String password, @Param("telephone")String telephone);
}
