package com.cm.weblog.common.aspect;

import com.cm.weblog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ClassName: ApiOperationLogAspect
 * Package: com.cm.weblog.common.aspect
 * Description: 自定义日志切面类
 *
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {
    /*
    * 以自定义注解 ApiOperationLog 为切点
    * 凡是添加@ ApiOperationLog 的方法都会执行环绕中的代码
    * */
    @Pointcut("@annotation(com.cm.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog() {}

    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();

            // 处理请求开始的时候, 将请求的跟踪标识放入MDC 中，traceId 表示跟踪 ID， 值这里直接用的 UUID
            MDC.put("traceId", UUID.randomUUID().toString());

            // 获取请求的类和方法
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();

            // 获取请求入参
            Object[] args = joinPoint.getArgs();
            // 转成JSON
            String argsJsonStr = Arrays.stream(args).map(toJsonString()).collect(Collectors.joining(", "));

            // 获取功能描述信息
            String description = getApiOperationLogDescription(joinPoint);

            // 打印入参信息
            log.info("====== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} =================================== ",
                    description, argsJsonStr, className, methodName);

            // 执行切点方法
            Object result = joinPoint.proceed();

            // 执行耗时
            long executionTime = System.currentTimeMillis() - startTime;

            // 打印出参等相关信息
            log.info("====== 请求结束: [{}], 耗时: {}ms, 出参: {} =================================== ",
                    description, executionTime, JsonUtil.toJsonString(result));

            return result;
        } finally {
            MDC.clear();
        }
    }

    /**
     * 转 JSON 字符串
     * @return JSON
     */
    private Function<Object, String> toJsonString() {
        return arg -> JsonUtil.toJsonString(arg);
    }

    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {
        // 获取 MethodSignature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // 获取被注解的 Method
        Method method = methodSignature.getMethod();

        // 获取注解
        ApiOperationLog annotation = method.getAnnotation(ApiOperationLog.class);

        // 获取 description 属性
        return annotation.description();
    }
}
