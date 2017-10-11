package com.gizwits.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gizwits.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author feel
 * @since 2017-10-10
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select id,name,password from g_user where name like #{name}")
    User findByName(String name);

    List<User> selectUserList(Page page);
}