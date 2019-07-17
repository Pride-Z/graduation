package com.czxy.person.service;

import com.czxy.person.dao.ExpenseMapper;
import com.czxy.person.domain.Expense;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class ExpenseService {
    @Resource
    private ExpenseMapper expenseMapper;

    /**
     * 创建报销单
     *
     * @param location
     * @param message
     */
    public void addExp(String submitter, String location, String message, String time, double price) {
        expenseMapper.addExp(submitter, location, message, time, price);

    }

    /**
     * 审批报销单
     *
     * @param id
     */
    public void updateExp(int id) {
        expenseMapper.updateExp(id);

    }

    /**
     * 拒绝报销单
     *
     * @param id
     */
    public void updateRefuse(int id) {
        expenseMapper.updateRefuse(id);

    }

    /**
     * 查询报销单
     *
     * @return
     */
    public List<Expense> findExp() {
        return expenseMapper.findExp();
    }


    public List<Expense> findUser(String telephone) {

        return expenseMapper.findUser(telephone);
    }


}
