package com.czxy.person.dao;

import com.czxy.person.domain.Location;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface LocationMapper extends Mapper<Location> {

    /**
     * 查询Base地
     * @return
     */
    @Select("SELECT * from location")
    List<Location> find();

}
