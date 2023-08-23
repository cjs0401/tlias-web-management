package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jsc
 * @version 1.0
 */
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean findEmpsByPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        Long total = empMapper.getTotalEmps();
//        Integer start = (page - 1) * pageSize;
//        设置分页参数
          PageHelper.startPage(page,pageSize);
          log.info("分页查询，参数： {},{}",page,pageSize);
//          执行查询
          List<Emp> emps = empMapper.list(name,gender,begin,end);
//          封装查询结果
          Page<Emp> p = (Page<Emp>)emps;
//        List<Emp> emps = empMapper.findEmpsByPage(start,pageSize);
//        获取查询结果
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteEmps(String[] ids) {
        empMapper.deleteEmps(ids);
    }

    @Override
    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
    }

    @Override
    public Emp findEmpById(Integer id) {
        return empMapper.findEmpById(id);
    }

    @Override
    public void updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.login(emp);
    }
}
