package cn.demo.service.impl;

import cn.demo.entry.User;
import cn.demo.mapper.IUserMapper;
import cn.demo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2020/8/26 1:42
 * @Created by XJC·AW
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserMapper userMapper;

    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }
}
