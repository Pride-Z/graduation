package com.czxy.fore.controller;


import com.czxy.person.domain.User;
import com.czxy.person.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Info")
public class UserInfoController {
    @Autowired
    private UserService userService;


    /**
     * 查询用户
     *
     * @return
     */
    @RequestMapping("/find")
    public List<User> findUser() {

        return userService.findUser();

    }

    /**
     * 删除用户
     *
     * @param id
     */
    @RequestMapping("/del")
    public HttpStatus delEmp(@RequestBody int[] id) {
        for (int i : id) {
            userService.delEmp(i);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;

    }

    /**
     *
     * @param uid
     * @param name
     * @param telephone
     * @param mail
     */
    @RequestMapping("/add")
    public void addUser(int uid, String name, String telephone, String mail) {

            userService.addUser(uid, name, telephone, mail);


    }


}
