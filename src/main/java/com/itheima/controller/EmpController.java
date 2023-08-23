package com.itheima.controller;

import com.itheima.aop.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @author jsc
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result findEmpsByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询,参数：{},{},{},{},{},{}",name,gender,begin,end,page,pageSize);
        PageBean pageBean = empService.findEmpsByPage(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result deleteEmps(@PathVariable String[] ids) {
        log.info("删除员工：" + ids.toString());
        empService.deleteEmps(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result addEmp(@RequestBody Emp emp) {
        log.info("add emp : {}" ,emp);
        empService.addEmp(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findEmpById(@PathVariable Integer id) {
        log.info("查找员工通过id：{}",id);
        Emp emp = empService.findEmpById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp) {
        log.info("更新员工：{}",emp);
        empService.updateEmp(emp);
        return Result.success();
    }
}
