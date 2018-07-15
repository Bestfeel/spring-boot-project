package com.github.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.entity.User;
import com.github.mapper.UserMapper;
import com.github.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author feel
 * @since 2017-10-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findByName(String name) {


        return baseMapper.findByName(name);
    }
}
