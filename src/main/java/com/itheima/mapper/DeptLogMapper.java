package com.itheima.mapper;

import com.itheima.pojo.DeptLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author jsc
 * @version 1.0
 */

@Mapper
public interface DeptLogMapper {

    @Select("insert into dept_log (create_time,description) values (#{createTime},#{description})")
    void insertLog(DeptLog deptLog);
}
