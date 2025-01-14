package com.cm.weblog.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class WeblogWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testLog() {
        log.info("测试 INFO 日志");
        log.warn("测试 WARN 日志");
        log.error("测试 ERROR 日志");

        String author = "cm";
        log.info("测试占位符日志，作者：{}", author);
    }
}
