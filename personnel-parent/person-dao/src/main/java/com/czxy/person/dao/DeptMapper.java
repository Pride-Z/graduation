package com.czxy.person.dao;

import com.czxy.person.domain.Dept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface DeptMapper extends Mapper<Dept> {
//    @Select("select * from dept where parentid = 0")
    @Select("select * from dept")
    List<Dept> findDeptFirst();

    @Select("select * from dept where parentid = #{parentid}")
    List<Dept> findDeptSecond(@Param("parentid") Integer parentid);

    @Select("select * from dept where parentid = #{parentid}")
    List<Dept> findDeptThird(@Param("parentid") Integer parentid);



}
