package com.czxy.fore.controller;

import com.czxy.person.domain.User;
import com.czxy.person.service.UserService;
import com.czxy.person.util.GetRandomCodeUtil;
import com.czxy.person.util.MailUtil;
import com.czxy.person.util.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserService userService;

    /**
     * 发送手机验证码
     *
     * @param telephone
     * @return
     */
    @GetMapping("sendSms")
    public ResponseEntity<Void> smsUtils(String telephone) {
        //获取随机生成的手机验证码code
        String code = GetRandomCodeUtil.getNumber();
        //将验证码存到redis中
        redisTemplate.opsForValue().set(telephone, code);
        //发送短信验证码
        try {
            //阿里云市场
            SendMailUtil.sendMail(code, telephone);
            //阿里控制台
//            SmsUtil.sendSms(telephone,code);
            System.out.println(code);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 发送邮件激活
     *
     * @param user
     * @param checkcode
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(User user, String checkcode) {

        String code = redisTemplate.opsForValue().get(user.getTelephone());
        if (!code.equals(checkcode)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userService.save(user);
        //获取邮件
        //保存完用户之后再去发送邮件 进行激活
        System.out.println(user.getMail());
        //生成随机的邮件激活码
        String activeCode = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(user.getTelephone(), activeCode);
        String activeUrl = "http://localhost:8292/user/activeMail?telephone=" + user.getTelephone() + "&activeCode=" + activeCode;
//        String activeUrl = "http://localhost:8292";
        //组装邮件内容
        String content = "<a href='" + activeUrl + "'>您已成功绑定!</a>";
        //发送邮件代码
        try {
            MailUtil.sendMail(user.getMail(), "账号绑定", content);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);


    }

    /**
     * 邮件激活
     *
     * @param telephone
     * @param activeCode
     * @return
     */
    @GetMapping("/activeMail")
    public ResponseEntity<String> activeMail(String telephone, String activeCode) {
        System.out.println("进入激活");
        //判断激活码是否存在
        String code = redisTemplate.opsForValue().get(telephone);
        if (!code.equals(activeCode)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        //判断用户是否激活
        User user = userService.findUserByTelephone(telephone);
        user.setType(1);
        if (user.getType() != null) {
            //证明已经激活
            userService.update(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * 登陆
     */
    @RequestMapping("/login")
    public ResponseEntity<String> login(HttpSession session, User user, String checkcode) {
        session.setAttribute("user", user);
        String validateCode = (String) session.getAttribute("key");
        //判断验证码是否正确
        if (validateCode.equals(checkcode)) {
            //登陆
            User loginUser = userService.findUserByTelephone(user.getTelephone());
            if (!loginUser.getPassword().equals(user.getPassword())) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (user.getTelephone().equals("admin")) {
                return new ResponseEntity<String>("admin", HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
