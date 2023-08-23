package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author jsc
 * @version 1.0
 */
@Mapper
public interface DeptMapper {

    /**
     * 查询所有部门
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 根据部门id 删除
     * @param id
     */

    @Delete("delete from dept where id = #{id}")
    void deleteDeptById(Integer id);

    @Insert("insert into dept (name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void addDept(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept findDeptBuyId(Integer id);

    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void updateDept(Dept dept);
}
