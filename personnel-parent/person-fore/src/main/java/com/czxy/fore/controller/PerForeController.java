package com.czxy.fore.controller;

import com.czxy.person.domain.Person;
import com.czxy.person.domain.vo.EasyUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/perfore")
public class PerForeController {

    @Autowired
    private RestTemplate restTemplate;

    public static final String PERSON_URL="http://localhost:8092/person/";

    @GetMapping("/findPersonByPage")
    public ResponseEntity<EasyUIResult<Person>> findPersonByPage(Integer page,Integer rows){
        String url = PERSON_URL+"findPersonByPage?page="+page+"&rows="+rows;

        try {
            EasyUIResult<Person> entity = restTemplate.getForObject(url, EasyUIResult.class);
            return new ResponseEntity<>(entity,HttpStatus.OK);
        } catch (RestClientException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/save")
    public ResponseEntity<Void> save(Person person){
        try {
            String url = PERSON_URL+"save";
            ResponseEntity<Person> entity =  restTemplate.postForEntity(url,person,null);
            return  new ResponseEntity<>(entity.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/update")
    public ResponseEntity<Void> update(Person person){
        try {
            String url = PERSON_URL+"update";
            restTemplate.put(url,person);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/del")
    public ResponseEntity<Void> delete(String ids){
        try {
            String url = PERSON_URL +"del?ids="+ids;
            restTemplate.delete(url,ids);
            // restTemplate.getForEntity(url,null);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
