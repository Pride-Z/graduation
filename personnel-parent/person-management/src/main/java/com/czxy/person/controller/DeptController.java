package com.czxy.person.controller;

import com.czxy.person.domain.Dept;
import com.czxy.person.domain.Location;
import com.czxy.person.service.DeptService;
import com.czxy.person.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private LocationService locationService;

    /**
     *
     *
     * @return
     */
    @GetMapping("findList")
    public List<Location> findList(){
        List<Location> list = locationService.find();
        return list;
    }



    /**
     * 一级查询
     *
     * @return
     */
    @GetMapping("/findDeptFirst")
    public List<Dept> findDeptFirst() {
        List<Dept> list1 = deptService.findDeptFirst();
        return list1;
    }

    /**
     * 二级查询
     *
     * @return
     */
    @GetMapping("/findDeptSecond")
    public List<Dept> findDeptSecond(Integer parentid) {
        List<Dept> list2 = deptService.findDeptSecond(parentid);
        return list2;
    }

    /**
     * 三级查询
     *
     * @return
     */
    @GetMapping("/findDeptThird")
    public List<Dept> findDeptThird(Integer parentid) {
        List<Dept> list3 = deptService.findDeptThird(parentid);
        return list3;
    }
}
