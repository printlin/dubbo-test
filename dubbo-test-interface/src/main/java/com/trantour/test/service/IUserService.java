package com.trantour.test.service;

import com.trantour.test.bean.User;

public interface IUserService {
    User login(String username, String password);
}
