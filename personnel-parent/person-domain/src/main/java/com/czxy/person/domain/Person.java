package com.czxy.person.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
    @Id
    private Integer pid;
    private String  pname;
    private Integer did;
    private Dept dept;
    private Dept deptdi;
    private Dept deptzhong;
    private Dept deptding;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Dept getDeptdi() {
        return deptdi;
    }

    public void setDeptdi(Dept deptdi) {
        this.deptdi = deptdi;
    }

    public Dept getDeptzhong() {
        return deptzhong;
    }

    public void setDeptzhong(Dept deptzhong) {
        this.deptzhong = deptzhong;
    }

    public Dept getDeptding() {
        return deptding;
    }

    public void setDeptding(Dept deptding) {
        this.deptding = deptding;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
}
