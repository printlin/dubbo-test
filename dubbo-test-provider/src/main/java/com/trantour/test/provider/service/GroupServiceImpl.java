package com.trantour.test.provider.service;

import com.trantour.test.service.IGroupService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: YL
 * @date: Created in 2022/11/29 16:34
 * @version: 1.0
 * @modified By:
 */
@DubboService
@Component
public class GroupServiceImpl implements IGroupService {
    @Value("${dubbo.application.name}")
    private String appName;

    @Override
    public String join(Integer userId, String groupId) {
        return userId+"_"+groupId+"_"+appName;
    }
}
