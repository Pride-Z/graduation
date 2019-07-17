package com.czxy.person.controller;

import com.czxy.person.domain.Person;
import com.czxy.person.domain.vo.EasyUIResult;
import com.czxy.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    /**
     * 查询所有
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<EasyUIResult<Person>> findPersonByPage(Integer page, Integer rows){
        try {
            EasyUIResult<Person> result= personService.list(page,rows);
            return new ResponseEntity<>(result,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findPersonByPage")
    public ResponseEntity<EasyUIResult<Person>> list(Integer page, Integer rows){
        try {
            EasyUIResult<Person> result= personService.list(page,rows);
            return new ResponseEntity<>(result,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 保存数据
     * @param person
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Person person){
        try {
            personService.save(person);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Person person){
        try {
            personService.update(person);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/del")
    public ResponseEntity<Void> delete(String ids){
        try {
            personService.deletes(ids);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
