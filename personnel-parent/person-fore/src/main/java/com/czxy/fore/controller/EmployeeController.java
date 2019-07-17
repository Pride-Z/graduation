package com.czxy.fore.controller;

import com.czxy.person.domain.Employee;
import com.czxy.person.domain.Expense;
import com.czxy.person.domain.User;
import com.czxy.person.service.EmployeeService;
import com.czxy.person.service.ExpenseService;
import com.czxy.person.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/Emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserService userService;


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
    @RequestMapping("/add")
    public void addEmp(int id, String name, String sex, int ages, String phone, String email, String department, String createdate, String location) {
        employeeService.addEmp(id, name, sex, ages, phone, email, department, createdate, location);

    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping("/find")
    public List<Employee> findEmp() {

        return employeeService.findEmp();
    }

    /**
     * 查询当前用户
     *
     * @return
     */
    @RequestMapping("/findUser")
    public List<Employee> findUserEmp(HttpSession session) {
        User u = (User) session.getAttribute("user");
        String telephone = u.getTelephone();
        return employeeService.finduserEmp(telephone);
    }

    /**
     * 删除信息
     *
     * @param id
     */
    @RequestMapping("/del")
    public void delEmp(@RequestBody int[] id) {
      for (int i : id){
          employeeService.delEmp(i);
      }

    }

    /**
     * 根据ID修改用户信息
     *
     * @param id
     */
    @RequestMapping("/update")
    public void updateEmp(int id, String name, String phone, String email, String department, String location) {

        employeeService.updateEmp(id, name, phone, email, department, location);

    }
//===========================================================================//

    /**
     * 新建报销单
     *
     * @param location
     * @param message
     */
    @RequestMapping("/addExp")
    public void addExp(HttpSession session, String location, String message, String time, double price) {

        User u = (User) session.getAttribute("user");

        String telephone = u.getTelephone();

        User loginUser = userService.findUserByTelephone(telephone);

        String submitter = loginUser.getName();

        expenseService.addExp(submitter, location, message, time, price);

    }


    /**
     * 审批报销单
     *
     * @param id
     */
    @RequestMapping("/agree")
    public void updateExp(@RequestBody int[] id) {
        for(int i:id){
            expenseService.updateExp(i);
        }
    }

    /**
     * 拒绝报销单
     *
     * @param id
     */
    @RequestMapping("/refuse")
    public void updateRefuse(@RequestBody int[] id) {
        for (int i : id){
            expenseService.updateRefuse(i);
        }

    }

    /**
     * 查询报销单
     *
     * @return
     */
    @GetMapping("/selectExp")
    public List<Expense> findExp() {
        return expenseService.findExp();
    }

    /**
     * 查询当前用户的报销单
     *
     * @param session
     * @return
     */
    @GetMapping("/selectUser")
    public List<Expense> findUser(HttpSession session) {
        User u = (User) session.getAttribute("user");
        String telephone = u.getTelephone();
        return expenseService.findUser(telephone);

    }


}
