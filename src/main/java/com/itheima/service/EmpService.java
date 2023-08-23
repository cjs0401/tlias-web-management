package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;

/**
 * @author jsc
 * @version 1.0
 */
public interface EmpService {

    /**
     * 分页查询
     *
     * @param page     页码
     * @param pageSize 记录数
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    PageBean findEmpsByPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * batch delete emps
     * @param ids
     */
    void deleteEmps(String[] ids);

    /**
     * add emp
     * @param emp
     */
    void addEmp(Emp emp);

    /**
     * find emp
     * @param id
     * @return
     */
    Emp findEmpById(Integer id);

    /**
     * 根据id 更新emp
     * @param emp
     */
    void updateEmp(Emp emp);

    /**
     * 用户登录
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
