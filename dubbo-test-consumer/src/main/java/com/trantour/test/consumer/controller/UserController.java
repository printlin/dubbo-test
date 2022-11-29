package com.trantour.test.consumer.controller;

import com.trantour.test.bean.User;
import com.trantour.test.service.IGroupService;
import com.trantour.test.service.IUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: YL
 * @date: Created in 2022/11/29 15:23
 * @version: 1.0
 * @modified By:
 */
@RestController
public class UserController {
    @DubboReference
    private IUserService userService;
    @DubboReference(loadbalance = "groupLoadBalance")
    private IGroupService groupService;

    @GetMapping("/login")
    public User login(String username, String password){
        return userService.login(username, password);
    }

    @GetMapping("/join")
    public String join(Integer userId, String groupId){
        return groupService.join(userId, groupId);
    }
}
