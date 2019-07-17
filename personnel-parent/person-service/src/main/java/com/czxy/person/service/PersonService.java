package com.czxy.person.service;

import com.czxy.person.dao.DeptMapper;
import com.czxy.person.dao.PersonMapper;
import com.czxy.person.domain.Dept;
import com.czxy.person.domain.Person;
import com.czxy.person.domain.vo.EasyUIResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class PersonService {
    @Resource
    private PersonMapper personMapper;

    @Resource
    private DeptMapper deptMapper;

    /**
     * 查询所有
     * @param page
     * @param rows
     * @return
     */
    public EasyUIResult<Person> findPersonByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Person> list = personMapper.select(null);
        for (Person person : list) {
            //底层列表
            Dept dept1 = deptMapper.selectByPrimaryKey(person.getDid());
            person.setDeptdi(dept1);
                    if (dept1.getParentid()!=0) {
                        //次级列表
                        Dept dept2 = deptMapper.selectByPrimaryKey(dept1.getParentid());
                        person.setDeptzhong(dept2);
                        if (dept2.getParentid()!=0) {
                            //顶层列表
                            Dept dept3 = deptMapper.selectByPrimaryKey(dept2.getParentid());
                            person.setDeptding(dept3);
                        }
            }

        }
        PageInfo<Person> info = new PageInfo<>(list);
        EasyUIResult<Person> result= new EasyUIResult<>();
        result.setRows(info.getList());
        result.setTotal(info.getTotal());
        return result;
    }

    /**
     * 保存
     * @param person
     */
    public void save(Person person) {
        personMapper.insert(person);
    }

    /**
     * 修改数据
     * @param person
     */
    public void update(Person person) {
        personMapper.updateByPrimaryKey(person);
    }

    public void deletes(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                personMapper.deleteByPrimaryKey(id);
            }

        }
    }

    public EasyUIResult<Person> list(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Person> list = personMapper.select(null);
        for (Person person : list) {
            //java
            Dept dept1 = deptMapper.selectByPrimaryKey(person.getDid());
            //上海
            Dept dept2 = deptMapper.selectByPrimaryKey(dept1.getParentid());
            dept1.setDept(dept2);
            //总部
            Dept dept3 = deptMapper.selectByPrimaryKey(dept2.getParentid());
            dept2.setDept(dept3);
            person.setDept(dept1);

        }
        PageInfo<Person> info = new PageInfo<>(list);
        EasyUIResult<Person> result= new EasyUIResult<>();
        result.setRows(info.getList());
        result.setTotal(info.getTotal());
        return result;
    }
}
