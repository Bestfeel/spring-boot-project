package com.github.test;

import com.github.entity.Student;
import com.github.main.App;
import com.github.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * @author guojie.yf
 * @date 2018/7/19
 * @email guojie.yf@alibaba-inc.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class AppTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void queryStudent() throws Exception {


        Student student = studentMapper.findById(2L);

        System.out.println(student);


        List<Student> all = studentMapper.findAll();

        System.out.println(all);


        List<Student> students = studentMapper.findAllById(Arrays.asList(1L, 3L));


        System.out.println(students);

    }

    @Test
    public void updateStudent() throws Exception {

        int res = studentMapper.updateStudent(new Student("feel", 100));

        System.out.println(res);

    }
}
