package com.czxy.person.dao;

import com.czxy.person.domain.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface EmployeeMapper extends Mapper<Employee> {
    /**
     * 添加信息
     *
     * @param name
     * @param sex
     * @param ages
     * @param phone
     * @param email
     * @param department
     * @param createdate
     * @param location
     */
    @Insert("INSERT INTO employee VALUES(#{id},#{name},#{sex},#{ages},#{phone},#{email},#{department},#{createdate},#{location})")
    void addEmp(int id, String name, String sex, int ages, String phone, String email, String department, String createdate, String location);

    /**
     * 查询信息
     */
    @Select("select * from employee")
    List<Employee> findEmp();


    /**
     * 查询当前信息
     */
    @Select("select * from user u,employee e where u.mail=e.email and telephone=#{telephone};")
    List<Employee> finduserEmp(String telephone);


    /**
     * 删除
     *
     * @param id
     */
    @Delete("DELETE FROM employee WHERE employee.id=#{id}")
    void delEmp(int id);

    /**
     * 根据ID更新员工信息
     *
     * @param name
     * @param phone
     * @param email
     * @param department
     * @param location
     */
    @Update("UPDATE employee SET name=#{name},phone=#{phone},email=#{email},department=#{department},location=#{location}WHERE id=#{id}")
    void updateEmp(int id, String name, String phone, String email, String department, String location);

}
