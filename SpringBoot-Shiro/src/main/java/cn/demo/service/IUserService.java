package cn.demo.service;

import cn.demo.entry.User;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2020/8/26 1:41
 * @Created by XJC·AW
 */
public interface IUserService {
    User findUserByName(String name);
}
