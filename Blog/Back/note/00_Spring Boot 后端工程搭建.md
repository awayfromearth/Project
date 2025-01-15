# 一、搭建Spring Boot 多模块工程(Spring Initializer)

## 1.1、IDEA搭建工程骨架

![](images/00_00.png)

0. 通过设置修改初始化链接为阿里云：http://start.aliyun.com(否则无法使用Java8)
1. 选择 Spring Boot 项目初始化容器
2. 配置父项目名称
3. 选择项目存放位置
4. 选择Maven构建项目
5. 填写组织名称，通常为公司域名倒写
6. 选择JDK
7. 选择Java版本
8. Next -> create
9. 删除无用文件，最终目录如下：
   ![](images/00_01.png)

10. 整理pom.xml

    - Spring Boot 版本切换成2.6.3

    - 项目信息

    - 多模块项目父工程打包模式必须指定为pom

    - 子模块管理

    - 版本号统一管理

    - 统一依赖管理

    - 统一插件管理

    - 配置阿里云的Maven镜像，提升下载速度

    - 最终代码如下：

      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
          <modelVersion>4.0.0</modelVersion>
      
          <parent>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-parent</artifactId>
              <version>2.6.3</version>
              <relativePath />
          </parent>
      
          <groupId>com.cm</groupId>
          <artifactId>weblog-springboot</artifactId>
          <version>${revision}</version>
          <name>weblog-springboot</name>
          <description>前后端分离博客项目</description>
      
          <!-- 多模块项目父工程打包模式必须指定为pom -->
          <packaging>pom</packaging>
      
          <!-- 子模块管理-->
          <modules></modules>
      
          <!-- 版本号统一管理 -->
          <properties>
              <!-- 项目版本号 -->
              <revision>0.0.1-SNAPSHOT</version>
              <java.version>1.8</java.version>
              <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      
              <!-- Maven 相关-->
              <maven.complier.source>${java.version}</maven.complier.source>
              <maven.complier.target>${java.version}</maven.complier.target>
          </properties>
      
          <!-- 统一依赖管理 -->
          <dependencyManagement>
      
          </dependencyManagement>
      
          <build>
              <!-- 统一插件管理 -->
              <pluginManagement>
      
              </pluginManagement>
          </build>
      
          <!-- 阿里云镜像 -->
          <repositories>
              <repository>
                  <id>aliyunmaven</id>
                  <name>aliyun</name>
                  <url>https://maven.aliyun.com/repository/public</url>
              </repository>
          </repositories>
      </project>
      ```

      

## 1.2、Web模块：入口模块

0. IDEA新建weblog-web子模块
1. 勾选lombok和Spring Web
2. 删除多余文件
3. 父项目pom.xml中添加该子模块及spring-boot-maven-plugin插件
4. 整理web模块的pom.xml，仅保留spring-boot-starter-web、lombok、spring-boot-starter-test依赖以及spring-boot-maven-plugin插件

## 1.3、Admin模块：后台管理功能模块

0. IDEA新建weblog-module-admin子模块
1. 勾选lombok
2. 删除多余文件
   ![最终目录](images/00_02.png)
3. 父项目配置子模块
4. 整理pom.xml：lombok依赖和spring-boot-starter-test依赖

## 1.4、Common模块：通用功能模块

0. IDEA 新建weblog-module-common模块

1. 勾选lombok

2. 删除多余文件

3. 依赖：lombok、guava、spring-boot-starter-test

4. 父项目配置子模块、统一版本管理、配置依赖

5. 子模块之间的依赖关系：weblog-web依赖于weblog-module-common和weblog-module-admin、weblog-module-admin依赖于weblog-module-common

最终xml文件配置如下

- weblog-springboot:

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
  
      <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>2.6.3</version>
          <relativePath />
      </parent>
  
      <groupId>com.cm</groupId>
      <artifactId>weblog-springboot</artifactId>
      <version>${revision}</version>
      <name>weblog-springboot</name>
      <description>前后端分离博客项目</description>
  
      <packaging>pom</packaging>
  
      <!-- 子模块管理-->
      <modules>
          <!-- 入口模块 -->
          <module>weblog-web</module>
          <!-- Admin 后台管理功能模块 -->
          <module>weblog-module-admin</module>
          <!-- Common 通用功能模块 -->
          <module>weblog-module-common</module>
      </modules>
  
      <!-- 统一版本号管理-->
      <properties>
          <!-- 项目版本号-->
          <revision>0.0.1-SNAPSHOT</revision>
          <java.version>1.8</java.version>
          <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  
          <!-- Maven相关 -->
          <maven.complier.source>${java.version}</maven.complier.source>
          <maven.complier.target>${java.version}</maven.complier.target>
  
          <!-- 依赖包版本 -->
          <lombok.version>1.18.28</lombok.version>
          <guava.version>31.1-jre</guava.version>
          <commons-lang3.version>3.12.0</commons-lang3.version>
      </properties>
  
      <!-- 统一依赖管理-->
      <dependencyManagement>
          <dependencies>
              <dependency>
                  <groupId>com.cm</groupId>
                  <artifactId>weblog-module-admin</artifactId>
                  <version>${revision}</version>
              </dependency>
  
              <dependency>
                  <groupId>com.cm</groupId>
                  <artifactId>weblog-module-common</artifactId>
                  <version>${revision}</version>
              </dependency>
  
              <!-- 常用工具库 -->
              <dependency>
                  <groupId>com.google.guava</groupId>
                  <artifactId>guava</artifactId>
                  <version>${guava.version}</version>
              </dependency>
  
              <dependency>
                  <groupId>org.apache.commons</groupId>
                  <artifactId>commons-lang3</artifactId>
                  <version>${commons-lang3.version}</version>
              </dependency>
          </dependencies>
      </dependencyManagement>
  
      <build>
          <!-- 统一插件管理-->
          <pluginManagement>
              <plugins>
                  <plugin>
                      <groupId>org.springframework.boot</groupId>
                      <artifactId>spring-boot-maven-plugin</artifactId>
                      <configuration>
                          <excludes>
                              <exclude>
                                  <groupId>org.projectlombok</groupId>
                                  <artifactId>lombok</artifactId>
                              </exclude>
                          </excludes>
                      </configuration>
                  </plugin>
              </plugins>
          </pluginManagement>
      </build>
  
      <!-- 阿里云镜像-->
      <repositories>
          <repository>
              <id>aliyunmaven</id>
              <name>aliyun</name>
              <url>https://maven.aliyun.com/repository/public</url>
          </repository>
      </repositories>
  </project>
  ```

- weblog-web

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
  
      <parent>
          <groupId>com.cm</groupId>
          <artifactId>weblog-springboot</artifactId>
          <version>${revision}</version>
      </parent>
  
      <groupId>com.cm</groupId>
      <artifactId>weblog-web</artifactId>
      <name>weblog-web</name>
      <description>weblog-web(入口项目，负责博客前台展示、打包也在这个模块负责)</description>
  
      <dependencies>
          <dependency>
              <groupId>com.cm</groupId>
              <artifactId>weblog-module-common</artifactId>
          </dependency>
  
          <dependency>
              <groupId>com.cm</groupId>
              <artifactId>weblog-module-admin</artifactId>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
          </dependency>
  
          <dependency>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
              <optional>true</optional>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
              <scope>test</scope>
          </dependency>
      </dependencies>
  
      <build>
          <plugins>
              <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
              </plugin>
          </plugins>
      </build>
  
  </project>
  ```

- weblog-admin

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
  
      <parent>
          <groupId>com.cm</groupId>
          <artifactId>weblog-springboot</artifactId>
          <version>${revision}</version>
      </parent>
  
      <groupId>com.cm</groupId>
      <artifactId>weblog-module-admin</artifactId>
      <name>weblog-module-admin</name>
      <description>weblog-admin (负责管理后台相关功能)</description>
  
      <dependencies>
          <dependency>
              <groupId>com.cm</groupId>
              <artifactId>weblog-module-common</artifactId>
          </dependency>
  
          <dependency>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
              <optional>true</optional>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
              <scope>test</scope>
          </dependency>
      </dependencies>
  </project>
  ```

- weblog-common

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
  
      <parent>
          <groupId>com.cm</groupId>
          <artifactId>weblog-springboot</artifactId>
          <version>${revision}</version>
      </parent>
  
      <groupId>com.cm</groupId>
      <artifactId>weblog-module-common</artifactId>
      <name>weblog-common</name>
      <description>weblog-common(存放一些通用的功能)</description>
  
      <dependencies>
          <dependency>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
              <optional>true</optional>
          </dependency>
          
          <!-- 常用工具库 -->
          <dependency>
              <groupId>com.google.guava</groupId>
              <artifactId>guava</artifactId>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
              <scope>test</scope>
          </dependency>
      </dependencies>
  </project>
  
  ```

## 1.5、测试

> 在测试前要检查好IDEA中maven的配置

对weblog-springboot执行maven的clean、package命令(将跳过单元测试勾选上)

打包成功后会在weblog-web的target目录下生成weblog-web-0.0.1-SNAPSHOT.jar

**测试启动**

在weblog-web下找到启动类WeblogWebApplication运行main方法

**启动成功**

![](images/00_03.png)

# 二、多环境配置

- dev：本地开发环境，兼顾测试，测试完成后直接上线
- prod：生产环境

在weblog-web/src/main/resources下新建：

- application.yml

  ```yml
  spring:
  	profiles:
  		# 默认激活dev环境
  		active: dev
  ```

- application-dev.yml

- application-prod.yml

# 三、整合Logback日志

> 由于 Spring Boot 默认使用 Logback，所以当你在 `pom.xml` 中加入 `spring-boot-starter-web` 依赖时，它会自动包含 Logback 相关依赖，无需额外添加

## 3.1、自定义Logback配置

在weblog-web/src/main/resources下新建logback-weblog.xml：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>
    <jmxConfigurator />
    <include resource="org/springframework/boot/logging/logback/defaults.xml" />

    <!-- 应用名称 -->
    <property scope="context" name="appName" value="weblog" />
    <!-- 自定义日志输出路径及日志名称前缀 -->
    <property name="LOG_FILE" value="/app/weblog/logs/${appName}.%d{yyyy-MM-dd}" />
    <!--<property name="LOG_FILE" value="D:\\GitRepository\\Project\\Blog\\Back\\weblog-springboot\\logs\\${appName}.%d{yyyy-MM-dd}"/>-->
    <!-- 格式化输出：%d 表示日期，%thread 表示线程名，%-5level：级别从左显示 5 个字符宽度 %logger{50}: 表示记录日志的类的名称（即日志记录器的名称）最多显示 50 个字符的类名，如果类名超过 50 个字符，它会截断 %msg：日志消息，%n 是换行符-->
    <property name="FILE_LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss:SSS} [%thread] %-5level %logger{50} - %msg%n" />

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${LOG_FILE}-%i.log</FileNamePattern>
            <MaxHistory>30</MaxHistory>
            <TimeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </TimeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- dev环境（仅输出到控制台）-->
    <springProfile name="dev">
        <include resource="org/springframework/boot/logging/logback/console-appender.xml" />
        <root level="INFO">
            <appender-ref ref="CONSOLE" />
        </root>
    </springProfile>

    <!-- prod环境（仅输出到控制台）-->
    <springProfile name="prod">
        <include resource="org/springframework/boot/logging/logback/console-appender.xml" />
        <root level="INFO">
            <appender-ref ref="FILE" />
        </root>
    </springProfile>
</configuration>
```

打印日志到文件只需在生产环境开启，所以在application-prod.yml中配置：

```yml
logging:
	config: classpath:logback-weblog.xml
```

## 3.2、测试

**测试dev环境**

在WeblogWebApplicationTests类中新建一个testLog()，并给类添加@Slf4j注解

```java
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
        log.error("测试 Error 日志");

        String author = "cm";
        log.info("测试占位符日志，作者：{}", author);
    }
}
```

**测试prod环境**

修改application.yml激活prod环境

修改logback-weblog.xml文件中的日志输出路径

```xml
<property name="LOG_FILE" value="D:\\GitRepository\\Project\\Blog\\Back\\weblog-springboot\\logs\\${appName}.%d{yyyy-MM-dd}"/>
```

> 测试完把环境及配置改回来