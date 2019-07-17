package com.czxy.person.service;

import com.czxy.person.dao.DeptMapper;
import com.czxy.person.domain.Dept;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DeptService {
    @Resource
    private DeptMapper deptMapper;

    /**
     * 一级查询
     * @return
     */
    public List<Dept> findDeptFirst() {
       return  deptMapper.findDeptFirst();
    }

    /**
     * 二级
     * @param
     * @return
     */
    public List<Dept> findDeptSecond(Integer parentid){
        return deptMapper.findDeptSecond(parentid);

    }

    /**
     * 三级
     * @return
     */
    public List<Dept> findDeptThird(Integer parentid) {
        return deptMapper.findDeptThird(parentid);
    }


}
