package com.trantour.test.consumer.spi;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YL
 * @date: Created in 2022/11/29 15:55
 * @version: 1.0
 * @modified By:
 */
@Slf4j
public class GroupLoadBalance extends RandomLoadBalance {
    private static final Map<String, Integer> groupMapping = new HashMap<>();

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        log.info("进入自定义负载均衡，{}", url.getAbsolutePath());
        //获取调用方法名称
        String methodName = invocation.getMethodName();
        if(!"join".equals(methodName) || invocation.getArguments() == null
                || invocation.getArguments().length != 2){
            //直接采用默认策略
            log.info("不满足条件，直接采用默认策略");
            return super.select(invokers, url, invocation);
        }
        //获取群组参数
        String groupId = (String) invocation.getArguments()[1];
        Integer selected = groupMapping.get(groupId);
        if(selected != null){
            //查找已有的服务器
            for(Invoker<T> invoker : invokers){
                if(selected.equals(invoker.hashCode())){
                    log.info("查找到群组对应的服务器，直接返回");
                    return invoker;
                }
            }
        }
        //未找到已有的服务器则使用默认策略选择服务器
        log.info("当前群组没有对应服务器，使用默认策略选择服务器");
        Invoker<T> randomSelected = super.select(invokers, url, invocation);
        //保存群组对应的服务器
        groupMapping.put(groupId, randomSelected.hashCode());
        return randomSelected;
    }
}
