package com.cm.weblog.common.aspect;

import java.lang.annotation.*;

/**
 * ClassName: ApiOperationLog
 * Package: com.cm.weblog.common.aspect
 * Description: 自定义切面注解
 *
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    // API 功能描述
    String description() default "";
}
