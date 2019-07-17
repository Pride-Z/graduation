package com.czxy.fore.controller;


import com.czxy.person.domain.User;
import com.czxy.person.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/User")
public class UpdateController {
    @Autowired
    private UpdateService updateService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 修改密码
     *
     * @param password
     * @param telephone
     * @return
     */
    @PutMapping("/update")
    public HttpStatus update(String password, String telephone, User user, String checkcode) {

        try {
            String code = redisTemplate.opsForValue().get(user.getTelephone());
            if (!code.equals(checkcode)) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            } else {
                updateService.UpdateMapper(password, telephone);
            }
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.OK;
    }

    /**
     * 用户修改密码
     *
     * @param password
     * @param session
     * @return
     */
    @GetMapping("/change")
    public HttpStatus change(String password, HttpSession session) {

        User u = (User) session.getAttribute("user");

        String telephone = u.getTelephone();
        try {

            updateService.UpdateMapper(password, telephone);
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.OK;


    }


}
