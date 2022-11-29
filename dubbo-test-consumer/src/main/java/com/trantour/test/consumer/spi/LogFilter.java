package com.trantour.test.consumer.spi;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @description:
 * @author: YL
 * @date: Created in 2022/11/29 15:41
 * @version: 1.0
 * @modified By:
 */
@Slf4j
@Activate(group = CommonConstants.CONSUMER)
public class LogFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("开始调用，接口：{}，参数：{}", invocation.getMethodName(), JSON.toJSONString(invocation.getArguments()));
        Result result = invoker.invoke(invocation);
        log.info("调用完成，结果：{}", JSON.toJSONString(result.getValue()));
        return result;
    }
}
