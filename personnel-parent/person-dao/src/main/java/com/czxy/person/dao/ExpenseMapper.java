package com.czxy.person.dao;

import com.czxy.person.domain.Expense;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ExpenseMapper extends Mapper<Expense> {


    /**
     * 新建报销单
     *
     * @param location
     * @param submitter
     * @param message
     */
    @Insert("INSERT INTO expense VALUES(id,#{submitter},#{location},#{message},#{time},#{price},'新创建')")
    void addExp(String submitter, String location, String message, String time, double price);

    /**
     * 审批报销单
     *
     * @param id
     */
    @Update("UPDATE expense set `STATUS`='已审批' WHERE id = #{id}")
    void updateExp(int id);


    /**
     * 拒绝报销单
     *
     * @param id
     */
    @Update("UPDATE expense set `STATUS`='拒绝' WHERE id = #{id}")
    void updateRefuse(int id);


    /**
     * 查询报销单
     *
     * @return
     */
    @Select("SELECT * FROM `expense` where STATUS ='新创建'")
    List<Expense> findExp();

    /**
     * 查询自己的报销单
     */
    @Select("SELECT e.id,e.submitter,e.location, e.message,e.time,e.price,e.status from expense e, user u WHERE  u.name=e.submitter  and u.telephone=#{telephone}")
    List<Expense> findUser(String telephone);

}
