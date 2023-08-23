package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jsc
 * @version 1.0
 */
@Service
public class DeptServiceImpl implements DeptService {


    private DeptMapper deptMapper;


    private EmpMapper empMapper;


    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    public DeptServiceImpl(DeptMapper deptMapper, EmpMapper empMapper, DeptLogService deptLogService) {
        this.deptMapper = deptMapper;
        this.empMapper = empMapper;
        this.deptLogService = deptLogService;
    }

    @Transactional
    @Override
    public void deleteDeptById(Integer id) {
        try {
            deptMapper.deleteDeptById(id);
            empMapper.deleteEmpsByDeptId(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是 " + id + "号部门");
            deptLogService.insertLog(deptLog);
        }
    }

    @Override
    public void addDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }

    @Override
    public Dept findDeptBuyId(Integer id) {
        return deptMapper.findDeptBuyId(id);
    }

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
}
