package com.gizwits.service;

import com.baomidou.mybatisplus.service.IService;
import com.gizwits.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author feel
 * @since 2017-10-10
 */
public interface IUserService extends IService<User> {

    User findByName(String name);

}
