package com.czxy.fore.controller;

import com.czxy.person.domain.Dept;
import com.czxy.person.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/deptfore")
public class DeptForeController {
    @Autowired
    private RestTemplate restTemplate;

    public static final String DEPTFORE_URL="http://localhost:8092/dept/";

    /**
     *
     * @return
     */
    @GetMapping("/findDeptFirst")
    public ResponseEntity<List<Dept>> findDeptFirst(){

        String url = DEPTFORE_URL+"findDeptFirst";
        RequestEntity<Void> requestEntity = new RequestEntity<>(HttpMethod.GET, URI.create(url));
        ResponseEntity<List<Dept>> exchange = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Dept>>() {
        });
        return exchange;
    }

    /**
     *
     * @return
     */
    @GetMapping("find")
    public ResponseEntity<List<Location>> find(){
        String url =DEPTFORE_URL+"findList";
        RequestEntity<Void> requestEntity = new RequestEntity<>(HttpMethod.GET, URI.create(url));
        ResponseEntity<List<Location>> exchange = restTemplate.exchange(requestEntity ,new ParameterizedTypeReference<List<Location>>(){
        });
        return exchange;

    }


    @GetMapping("/findDeptSecond")
    public ResponseEntity<List<Dept>> findDeptSecond(Integer parentid){

        String url = DEPTFORE_URL+"findDeptSecond?parentid="+parentid;
        RequestEntity<Void> requestEntity = new RequestEntity<>(HttpMethod.GET, URI.create(url));
        ResponseEntity<List<Dept>> exchange = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Dept>>() {
        });
        return exchange;
    }
    @GetMapping("/findDeptThird")
    public ResponseEntity<List<Dept>> findDeptThird(Integer parentid){

        String url = DEPTFORE_URL+"findDeptThird?parentid="+parentid;
        RequestEntity<Void> requestEntity = new RequestEntity<>(HttpMethod.GET, URI.create(url));
        ResponseEntity<List<Dept>> exchange = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Dept>>() {
        });
        return exchange;
    }




}

