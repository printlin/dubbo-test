package com.trantour.test.provider.service;

import com.trantour.test.bean.User;
import com.trantour.test.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Random;

/**
 * @description:
 * @author: YL
 * @date: Created in 2022/11/29 15:14
 * @version: 1.0
 * @modified By:
 */
@DubboService
public class UserServiceImpl implements IUserService {
    private static final Random random = new Random();

    @Override
    public User login(String username, String password) {
        if(!"123456".equals(password)){
            return null;
        }
        User user = new User();
        user.setId(random.nextInt(1000));
        user.setUsername(username + "_" + user.getId());
        user.setSex(user.getId()%2 == 0 ? "男" : "女");
        return user;
    }
}
