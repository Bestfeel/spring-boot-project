package com.github.service.impl;

import com.github.entity.User;
import com.github.main.App;
import com.github.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by feel on 2017/10/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class UserServiceImplTest {


    @Autowired
    private IUserService iUserService;

    @Test
    public void findByName() throws Exception {

        User feel = iUserService.findByName("feel");


        System.out.println(feel);

    }

}