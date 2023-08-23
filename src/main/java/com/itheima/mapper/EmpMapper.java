package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author jsc
 * @version 1.0
 */
@Mapper
public interface EmpMapper {

    @Select("select count(*) from emp")
    Long getTotalEmps();

    @Select("select * from emp limit #{start},#{pageSize}")
    List<Emp> findEmpsByPage(Integer start, Integer pageSize);

    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void deleteEmps(String[] ids);


    @Insert("insert into emp (username, name, gender,image, job, entrydate, dept_id, create_time, update_time) values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void addEmp(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp findEmpById(Integer id);

//    @Update("update emp set username = #{username},name = #{name},gender = #{gender},image = #{image},job = #{job}," +
//            "entrydate = #{entrydate},dept_id = #{deptId},update_time = #{updateTime} where id = #{id}")
    void updateEmp(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp login(Emp emp);


    @Delete("delete from emp where dept_id = #{id}")
    void deleteEmpsByDeptId(Integer id);



}
