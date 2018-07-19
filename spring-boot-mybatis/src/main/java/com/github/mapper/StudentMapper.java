package com.github.mapper;

import com.github.config.SimpleSelectInExtendedLanguageDriver;
import com.github.config.SimpleUpdateExtendedLanguageDriver;
import com.github.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

/**
 * @author guojie.yf
 * @date 2018/7/19
 * @email guojie.yf@alibaba-inc.com
 */
@Mapper
public interface StudentMapper {
    @Select("select * from student where id = #{id}")
    Student findById(@Param("id") long id);

    @Select("select * from student")
    List<Student> findAll();

    /**
     * 自定义 LanguageDriver
     *
     * @param ids
     * @return
     */
    @Lang(SimpleSelectInExtendedLanguageDriver.class)
    @Select("select  * from student where id IN (#{ids})")
    List<Student> findAllById(@Param("ids") Collection<Long> ids);


    @Lang(SimpleUpdateExtendedLanguageDriver.class)
    @Update("update  student  (#{student})   where name=#{name}")
    int updateStudent(Student student);

}