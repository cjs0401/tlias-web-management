package com.itheima.service;

import com.itheima.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jsc
 * @version 1.0
 */

public interface DeptService {

    /**
     * 查询全部部门
     * @return
     */
    List<Dept> list();

    /**
     * 删除部门
     * @param id
     */
    void deleteDeptById(Integer id);

    /**
     * 添加dept
     * @param dept
     */
    void addDept(Dept dept);

    /**
     * 查找dept by Id
     * @param id
     * @return
     */
    Dept findDeptBuyId(Integer id);

    /**
     * 更新dept
     * @param dept
     */
    void updateDept(Dept dept);
}
