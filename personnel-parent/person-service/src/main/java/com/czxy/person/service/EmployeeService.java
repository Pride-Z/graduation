package com.czxy.person.service;

import com.czxy.person.dao.EmployeeMapper;
import com.czxy.person.domain.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Resource
    private EmployeeMapper EmployeeMapper;

    /**
     * 新增信息
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

    public void addEmp(int id, String name, String sex, int ages, String phone, String email, String department, String createdate, String location) {

        EmployeeMapper.addEmp(id, name, sex, ages, phone, email, department, createdate, location);

    }

    /**
     * 查询信息
     *
     * @return
     */
    public List<Employee> findEmp() {

        return EmployeeMapper.findEmp();
    }

    public List<Employee> finduserEmp(String telephone) {

        return EmployeeMapper.finduserEmp(telephone);
    }

    /**
     * 删除用户信息（id）
     *
     * @param id
     */
    public void delEmp(int id) {
        EmployeeMapper.delEmp(id);
    }

    /**
     * 根据ID更新
     *
     * @param name
     * @param phone
     * @param email
     * @param department
     * @param location
     */

    public void updateEmp(int id, String name, String phone, String email, String department, String location) {

        EmployeeMapper.updateEmp(id, name, phone, email, department, location);


    }


}
