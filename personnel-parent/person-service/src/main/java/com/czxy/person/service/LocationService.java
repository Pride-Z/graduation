package com.czxy.person.service;


import com.czxy.person.dao.LocationMapper;
import com.czxy.person.domain.Location;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LocationService {
    @Resource
    private LocationMapper locationMapper;

    /**
     *
     * @return
     */
    public List<Location> find(){
        return  locationMapper.find();
    }



}
