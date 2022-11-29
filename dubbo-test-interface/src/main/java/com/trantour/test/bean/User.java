package com.trantour.test.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: YL
 * @date: Created in 2022/11/29 15:11
 * @version: 1.0
 * @modified By:
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Date createTime;
}
