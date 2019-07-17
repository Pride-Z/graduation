package com.czxy.person.service;


import com.czxy.person.dao.UpdateMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UpdateService {

    @Resource
    private UpdateMapper updateMapper;

    /**
     * 更新密码
     * @param
     */
    public void UpdateMapper (String password,String telephone){
        updateMapper.update(password,telephone);

    }



}
