package com.itheima.controller;

import com.itheima.aop.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jsc
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> depts = deptService.list();
        return Result.success(depts);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result deleteDeptById(@PathVariable Integer id) {
        log.info("删除部门：" + id);
        deptService.deleteDeptById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        log.info("添加 部门：" + dept.getName());
        deptService.addDept(dept);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result findDeptBuyId(@PathVariable Integer id) {
        log.info("查找 部门：" + id);
        Dept dept = deptService.findDeptBuyId(id);
        return Result.success(dept);

    }

    @Log
    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        log.info("更新部门：" + dept.getName());
        deptService.updateDept(dept);
        return Result.success();
    }
}
